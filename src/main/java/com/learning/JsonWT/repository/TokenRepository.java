package com.learning.JsonWT.repository;

import com.learning.JsonWT.entity.Token;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TokenRepository extends MongoRepository<Token, ObjectId> {
    List<Token> findByUserId(String userName);

    @Query("{ 'token': ?0, 'refreshToken': true }")
    Token findByTokenAndIsRefreshTokenTrue(String refreshToken);

}
