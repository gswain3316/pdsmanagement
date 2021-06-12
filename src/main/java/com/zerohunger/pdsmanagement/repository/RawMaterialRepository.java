package com.zerohunger.pdsmanagement.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.zerohunger.pdsmanagement.domain.RawMaterial;

@Repository
public interface RawMaterialRepository extends ReactiveMongoRepository<RawMaterial, String> {

}
