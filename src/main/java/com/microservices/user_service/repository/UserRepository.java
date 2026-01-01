package com.microservices.user_service.repository;

import com.microservices.user_service.model.User;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {

    private final DynamoDbClient dynamoDbClient;

    public UserRepository(DynamoDbClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }

    private static final String TABLE_NAME = "Users";

    public void save(User user) {
        Map<String, AttributeValue> item = new HashMap<>();
        item.put("id", AttributeValue.fromS(user.getId()));
        item.put("name", AttributeValue.fromS(user.getName()));
        item.put("email", AttributeValue.fromS(user.getEmail()));

        dynamoDbClient.putItem(
                PutItemRequest.builder()
                        .tableName(TABLE_NAME)
                        .item(item)
                        .build()
        );
    }

    public List<User> findAll() {
        ScanResponse response = dynamoDbClient.scan(
                ScanRequest.builder()
                        .tableName(TABLE_NAME)
                        .build()
        );

        return response.items().stream().map(item ->
                new User(
                        item.get("id").s(),
                        item.get("name").s(),
                        item.get("email").s()
                )
        ).toList();
    }
}
