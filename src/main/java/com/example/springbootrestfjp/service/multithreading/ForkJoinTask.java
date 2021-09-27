package com.example.springbootrestfjp.service.multithreading;

import com.example.springbootrestfjp.entity.MessageThreadUuids;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.StringJoiner;
import java.util.concurrent.RecursiveTask;

@Slf4j
@AllArgsConstructor
public class ForkJoinTask extends RecursiveTask<List<MessageThreadUuids>> {

    private Integer from;
    private Integer to;
    private List<MessageThreadUuids> messageFjpList;
    private List<String> uuidList;

    @Override
    protected List<MessageThreadUuids> compute() {
        if ((to - from) <= 10) {
            Long currentThread = Thread.currentThread().getId();
            log.info("Обрабатываем поток: {}", currentThread);
            StringJoiner stringJoiner = new StringJoiner(" ", "", "");
            for (int i = from; i < to; i++) {
                String uuid = uuidList.get(i);
                if (uuid.hashCode() % 2 == 0) {
                    stringJoiner.add(uuid);
                }
            }
            MessageThreadUuids result = new MessageThreadUuids(currentThread, stringJoiner.toString());
            log.info(result.toString());
            messageFjpList.add(result);
        } else {
            int middle = from + 10;
            log.info("Значение middle: {}", middle);
            ForkJoinTask firstAction = new ForkJoinTask(from, middle, messageFjpList, uuidList);
            ForkJoinTask secondAction = new ForkJoinTask(middle, to, messageFjpList, uuidList);
            firstAction.fork();
            secondAction.fork();

            firstAction.join();
            secondAction.join();
        }
        return messageFjpList;
    }

}
