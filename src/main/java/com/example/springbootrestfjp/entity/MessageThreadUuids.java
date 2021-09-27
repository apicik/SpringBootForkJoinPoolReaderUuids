package com.example.springbootrestfjp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MessageThreadUuids {

    private Long threadNumber;
    private String uuids;

    @Override
    public String toString() {
        return "Поток: " + threadNumber + ", Записи: \n" + uuids;
    }
}
