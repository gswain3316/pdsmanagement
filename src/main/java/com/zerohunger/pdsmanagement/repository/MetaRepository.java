package com.zerohunger.pdsmanagement.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.zerohunger.pdsmanagement.domain.Meta;

@Repository
public interface MetaRepository extends MongoRepository<Meta, String> {

}
