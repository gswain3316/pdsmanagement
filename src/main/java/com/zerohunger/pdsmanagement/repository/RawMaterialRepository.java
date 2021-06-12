package com.zerohunger.pdsmanagement.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.zerohunger.pdsmanagement.domain.RawMaterial;

public interface RawMaterialRepository extends ReactiveMongoRepository<RawMaterial, String> {

}
