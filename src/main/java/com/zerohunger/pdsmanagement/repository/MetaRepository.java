package com.zerohunger.pdsmanagement.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.zerohunger.pdsmanagement.domain.Meta;

public interface MetaRepository extends MongoRepository<Meta, String> {

}
