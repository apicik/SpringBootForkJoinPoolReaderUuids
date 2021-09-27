package com.example.springbootrestfjp.service;

import com.example.springbootrestfjp.entity.MessageThreadUuids;
import com.example.springbootrestfjp.service.multithreading.ForkJoinTask;
import com.example.springbootrestfjp.producer.LineProducer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

@Slf4j
@AllArgsConstructor
@Component
public class MessageService {

    private LineProducer lineProducer;

    public List<MessageThreadUuids> getMessageList() {
        ForkJoinPool pool = new ForkJoinPool();
        log.info("Start fork join pool()");
        List<MessageThreadUuids> messageFjpList = pool.invoke(new ForkJoinTask(0, 100, new ArrayList<>(), lineProducer.getUuidList()));
        log.info("Finish fork join pool()");
        return messageFjpList;
    }


}
