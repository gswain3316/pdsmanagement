package com.zerohunger.pdsmanagement.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.zerohunger.pdsmanagement.domain.Meta;

@Repository
public interface MetaRepository extends ReactiveMongoRepository<Meta, String> {

}
