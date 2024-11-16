package com.learning.JsonWT.repository;

import com.learning.JsonWT.entity.Role;
import com.learning.JsonWT.enums.RoleName;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends MongoRepository<Role, ObjectId> {
    Role findByName(RoleName name);
}
