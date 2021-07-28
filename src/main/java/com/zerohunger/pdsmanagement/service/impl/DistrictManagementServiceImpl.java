package com.zerohunger.pdsmanagement.service.impl;

import java.util.Date;
import java.util.Optional;

import com.zerohunger.pdsmanagement.constants.OrderRequestStatus;
import com.zerohunger.pdsmanagement.domain.GovBody;
import com.zerohunger.pdsmanagement.domain.GovBodyRawMaterialAvailability;
import com.zerohunger.pdsmanagement.domain.OrderGrant;
import com.zerohunger.pdsmanagement.domain.OrderRequest;
import com.zerohunger.pdsmanagement.domain.RequestStatus;
import com.zerohunger.pdsmanagement.dto.OrderGrantService;
import com.zerohunger.pdsmanagement.dto.OrderRequestService;
import com.zerohunger.pdsmanagement.exception.EntityNotFoundException;
import com.zerohunger.pdsmanagement.exception.OrderGrantSaveError;
import com.zerohunger.pdsmanagement.exception.OrderRequestSaveError;
import com.zerohunger.pdsmanagement.repository.DistrictAvailabilityRepository;
import com.zerohunger.pdsmanagement.repository.DistrictRepository;
import com.zerohunger.pdsmanagement.repository.OrderGrantRepository;
import com.zerohunger.pdsmanagement.repository.OrderRequestRepository;
import com.zerohunger.pdsmanagement.repository.RequestStatusRepository;
import com.zerohunger.pdsmanagement.service.DistrictManagementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class DistrictManagementServiceImpl implements DistrictManagementService {

    @Autowired
    private DistrictAvailabilityRepository districtAvailabilityRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private OrderGrantRepository orderGrantRepository;

    @Autowired
    private OrderRequestRepository orderRequestRepository;

    @Autowired
    private RequestStatusRepository requestStatusRepository;

    private static String DistrictInCamelCase = "";

    private static Boolean isStateIndicator = false;

    @Override
    public Mono<GovBodyRawMaterialAvailability> getRationAvailability(String districtName) {
        // get data from database using district name
        log.info(String.format("Getting Ration Availability for District: %s", districtName));
        DistrictInCamelCase = StringUtils.capitalize(districtName);
        isStateIndicator = false;
        Optional<GovBodyRawMaterialAvailability> data = Optional
                .ofNullable(districtAvailabilityRepository.findGovBodyByStateNameAndIsStateIndicator(DistrictInCamelCase, isStateIndicator));
        if (data.isPresent()) {
            log.info("Ration Availability Service Completed !");
            return Mono.just(data.get());
        } else {
            log.error("Ration Availability Service Failed !");
            return Mono.error(new EntityNotFoundException("District Not Found " + districtName));
        }
    }

    @Override
    public Mono<GovBody> getDistrictCapacity(String districtName) {
        log.info(String.format("Getting Ration Capacity for District: %s", districtName));
        DistrictInCamelCase = StringUtils.capitalize(districtName);
        isStateIndicator = false;
        Optional<GovBody> data = Optional.ofNullable(districtRepository.findGovBodyByStateNameAndIsStateIndicator(DistrictInCamelCase, isStateIndicator));
        if (data.isPresent()) {
            log.info("Ration Capacity of District Service Completed !");
            return Mono.just(data.get());
        } else {
            log.error("Ration Capacity of District Service Failed !");
            return Mono.error(new EntityNotFoundException("District Not Found " + districtName));
        }
    }

    @Override
    public Mono<OrderRequest> requestforRation(OrderRequestService orderRequest) {
        log.info(String.format("Requesting Ration for District: %s", orderRequest.getRequestingDistrictName()));
        DistrictInCamelCase = StringUtils.capitalize(orderRequest.getRequestingDistrictName());
        isStateIndicator = false;
        // check district name is present is database or not
        Optional<GovBody> data = Optional.ofNullable(districtRepository.findOneByDistrictName(DistrictInCamelCase));
        if (data.isPresent()) {
            // create new order request
            Date date = new Date();
            OrderRequest orderRequestEntity = new OrderRequest(orderRequest.getRequestingStateName(),
                    orderRequest.getRawMaterialName(), orderRequest.getQuantity(), orderRequest.getUnits(), true, date,
                    date, orderRequest.getRequestingDistrictName(), isStateIndicator);
            // save new order request
            Optional<OrderRequest> dbRes = Optional.ofNullable(orderRequestRepository.save(orderRequestEntity));
            if (dbRes.isPresent()) {
                log.info("Ration Request Service Completed !");
                // update request status to pending
                requestStatusRepository.save(new RequestStatus(dbRes.get().getId(), OrderRequestStatus.PENDING, 0.0,
                        dbRes.get().getQuantity()));
                return Mono.just(dbRes.get());
            } else {
                log.error("Ration Request Service Failed !");
                return Mono.error(
                        new OrderRequestSaveError("Request for Ration Service Error ! - Order Request Save Failed"));
            }
        } else {
            log.error("Ration Request Service Failed ! District Name not Found "
                    + orderRequest.getRequestingDistrictName());
            return Mono.error(
                    new EntityNotFoundException("District Not Found " + orderRequest.getRequestingDistrictName()));
        }
    }

    @Override
    public Mono<OrderGrant> grantOrderNote(OrderGrantService grantOrder) {
        log.info(String.format("Granting Order Note for District: %s", grantOrder.getGrantingDistrictName()));
        DistrictInCamelCase = StringUtils.capitalize(grantOrder.getGrantingDistrictName());
        isStateIndicator = false;
        // check district name is present is database or not
        Optional<GovBody> data = Optional.ofNullable(districtRepository.findOneByDistrictName(DistrictInCamelCase));
        // check if request is present in database
        Optional<OrderRequest> relatedOrderRequest = orderRequestRepository.findById(grantOrder.getRequestId());
        if (data.isPresent() && relatedOrderRequest.isPresent()) {
            // check if granting district name and requesting district name belong to same
            // state
            if (grantOrder.getGrantingStateName().equals(relatedOrderRequest.get().getRequestingStateName())) {
                // create new order grant
                Date date = new Date();
                OrderGrant orderGrantEntity = new OrderGrant(grantOrder.getGrantingStateName(),
                        grantOrder.getRequestId(), grantOrder.getQuantityGranted(), date, date,
                        grantOrder.getGrantingDistrictName(), isStateIndicator);
                // save new order grant
                Optional<OrderGrant> dbRes = Optional.ofNullable(orderGrantRepository.save(orderGrantEntity));
                if (dbRes.isPresent()) {
                    log.info("Order Grant Service Completed !");
                    updateOrderRequestAndRequestStatusOnGrantOrderNote(dbRes.get(), relatedOrderRequest.get());
                    return Mono.just(dbRes.get());
                } else {
                    log.error("Order Grant Service Failed !");
                    return Mono.error(new OrderGrantSaveError("Order Grant Service Error ! - Order Grant Save Failed"));
                }
            } else {
                log.error("Order Grant Service Failed ! Requesting and Granting Districts do not belong to same State "
                        + grantOrder.getGrantingStateName());
                return Mono.error(new OrderGrantSaveError(
                        "Order Grant Service Error ! - Requesting and Granting Districts do not belong to same State"));
            }
        } else {
            log.error("Ration Grant Service Failed ! District Name or Request ID is not Found " + grantOrder.getGrantingDistrictName());
            return Mono.error(new EntityNotFoundException("District " + grantOrder.getGrantingDistrictName()
                    + " or Request ID " + grantOrder.getRequestId() + " Not Found"));

        }
    }

    @Override
    public Mono<RequestStatus> getOrderStatus(String requestId) {
        log.info(String.format("Getting Order Status for Request Id: %s", requestId));
        Optional<RequestStatus> data = requestStatusRepository.findOneByRequestId(requestId);
        if (data.isPresent()) {
            log.info("Order Status Service Completed !");
            return Mono.just(data.get());
        } else {
            log.error("Order Status Service Failed !");
            return Mono.error(new EntityNotFoundException("Request Id Not Found " + requestId));
        }
    }

    private void updateOrderRequestAndRequestStatusOnGrantOrderNote(OrderGrant orderGrant, OrderRequest orderRequest) {
        // update order request status and orderRequest quantity
        Optional<RequestStatus> requestStatusOptional = requestStatusRepository.findOneByRequestId(orderRequest.getId());
        RequestStatus requestStatus = requestStatusOptional.get();
        if (orderRequest.getQuantity().equals(orderGrant.getQuantity())) {
            orderRequest.setIsActive(false);
            requestStatus.setStatus(OrderRequestStatus.FULFILLED);
        } else {
            requestStatus.setStatus(OrderRequestStatus.PARTIALY_FULFILLED);
        }
        Double remainingQuantity = orderRequest.getQuantity() - orderGrant.getQuantity();
        orderRequest.setQuantity(remainingQuantity);
        requestStatus.setQuantityFulfilled(orderGrant.getQuantity());
        requestStatus.setQuantityRemaining(remainingQuantity);
        orderRequestRepository.save(orderRequest);
        requestStatusRepository.save(requestStatus);
    }

}
