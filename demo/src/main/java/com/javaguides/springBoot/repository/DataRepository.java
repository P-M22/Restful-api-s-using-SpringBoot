package com.javaguides.springBoot.repository;

import com.javaguides.springBoot.model.Request;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DataRepository extends MongoRepository<Request,String> {
}
