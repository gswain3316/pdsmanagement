package com.zerohunger.pdsmanagement.service;

import com.zerohunger.pdsmanagement.domain.GovBody;
import com.zerohunger.pdsmanagement.domain.GovBodyRawMaterialAvailability;
import com.zerohunger.pdsmanagement.domain.OrderGrant;
import com.zerohunger.pdsmanagement.domain.OrderRequest;
import com.zerohunger.pdsmanagement.domain.RequestStatus;
import com.zerohunger.pdsmanagement.dto.OrderGrantService;
import com.zerohunger.pdsmanagement.dto.OrderRequestService;

import reactor.core.publisher.Mono;

public interface DistrictManagementService {

    Mono<GovBodyRawMaterialAvailability> getRationAvailability(String districtName);

    Mono<GovBody> getDistrictCapacity(String districtName);

    Mono<OrderRequest> requestforRation(OrderRequestService orderRequest);

    Mono<OrderGrant> grantOrderNote(OrderGrantService grantOrder);

    Mono<RequestStatus> getOrderStatus(String requestId);
    
}
