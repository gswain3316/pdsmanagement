package com.zerohunger.pdsmanagement.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.zerohunger.pdsmanagement.domain.RawMaterial;

@Repository
public interface RawMaterialRepository extends MongoRepository<RawMaterial, String> {

}
