package com.learning.JsonWT.entity;

import com.learning.JsonWT.enums.RoleName;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Set;

import static com.learning.JsonWT.constants.CollectionName.USER_COLLECTION;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Document(collection = USER_COLLECTION)
public class User {

    @MongoId
    String id;

    String username;

    String password;

    String email;

    Set<RoleName> roles;

}
