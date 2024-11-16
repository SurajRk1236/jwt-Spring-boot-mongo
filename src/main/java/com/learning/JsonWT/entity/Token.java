package com.learning.JsonWT.entity;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

import static com.learning.JsonWT.constants.CollectionName.TOKEN_COLLECTION;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Document(collection = TOKEN_COLLECTION)
public class Token {

    String id;

    String token;

    @CreatedDate
    LocalDateTime createdAt;

    Date expiredAt;

    String userId;

    boolean refreshToken;
}
