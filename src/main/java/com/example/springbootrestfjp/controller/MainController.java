package com.example.springbootrestfjp.controller;

import com.example.springbootrestfjp.entity.MessageThreadUuids;
import com.example.springbootrestfjp.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class MainController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/")
    public List<MessageThreadUuids> greeting() {
        log.info("Старт produceMessageList");
        List<MessageThreadUuids> uidList = messageService.getMessageList();
        log.info("Конец работы produceMessageList");
        return uidList;
    }
}