package com.zerohunger.pdsmanagement.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.zerohunger.pdsmanagement.domain.RawMaterial;

public interface RawMaterialRepository extends MongoRepository<RawMaterial, String> {

}
