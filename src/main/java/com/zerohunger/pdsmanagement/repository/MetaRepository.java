package com.zerohunger.pdsmanagement.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.zerohunger.pdsmanagement.domain.Meta;

public interface MetaRepository extends ReactiveMongoRepository<Meta, String> {

}
