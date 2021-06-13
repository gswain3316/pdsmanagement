package com.zerohunger.pdsmanagement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.zerohunger.pdsmanagement.domain.Availability;
import com.zerohunger.pdsmanagement.domain.Meta;
import com.zerohunger.pdsmanagement.domain.StateAvailability;
import com.zerohunger.pdsmanagement.repository.MetaRepository;
import com.zerohunger.pdsmanagement.repository.OrderGrantRepository;
import com.zerohunger.pdsmanagement.repository.OrderRequestRepository;
import com.zerohunger.pdsmanagement.repository.RawMaterialRepository;
import com.zerohunger.pdsmanagement.repository.StateAvailabilityRepository;
import com.zerohunger.pdsmanagement.repository.StateRepository;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class PdsmanagementLocalApplication implements CommandLineRunner {

	@Autowired
	private OrderGrantRepository orderGrantRepository;

	@Autowired
	private MetaRepository metaRepository;

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private RawMaterialRepository materialRepository;

	@Autowired
	private StateAvailabilityRepository stateAvailabilityRepository;

	@Autowired
	private OrderRequestRepository orderRequestrRepository;

	public static void main(String[] args) {
		SpringApplication.run(PdsmanagementLocalApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//Meta metaData = new Meta("Gaurav Swain", "gswain3316@gmail.com");
		List<Availability> availabilityList = new ArrayList<>();
		availabilityList.add(new Availability("60c4d1bb5e55143acc400612", 4356.30));
		availabilityList.add(new Availability("60c4d1bb5e55143acc400613", 2389.67));
		availabilityList.add(new Availability("60c4d1bb5e55143acc400614", 7490.67));
		availabilityList.add(new Availability("60c4d1bb5e55143acc400615", 4528.67));
		//metaRepository.save(metaData);
		// stateRepository.save(new State("Madhya ", metaData, 256789.89));
		// materialRepository.save(new RawMaterial(RationItem.RICE));
		//stateAvailabilityRepository.save(new StateAvailability("Assam", availabilityList));
		// orderRequestrRepository.save(new OrderRequest("60c4cff45e55143acc400608",
		// "60c4d1bb5e55143acc400612", 3478.08, "KG", true, new Date(), new Date()));
		// orderGrantRepository.save(new OrderGrant("60c4cff45e55143acc4005f5",
		// "60c4d7deac9709541d7ef152", 3200.00, new Date(), new Date()));
	}

}
