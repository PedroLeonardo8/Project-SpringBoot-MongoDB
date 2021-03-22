package com.example.workshopspring.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.workshopspring.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{

}
