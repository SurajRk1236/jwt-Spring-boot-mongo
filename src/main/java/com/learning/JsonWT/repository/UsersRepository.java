package com.learning.JsonWT.repository;

import com.learning.JsonWT.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends MongoRepository<User, ObjectId> {

    User findByUsername(String username);

    User findByEmail(String email);
}
