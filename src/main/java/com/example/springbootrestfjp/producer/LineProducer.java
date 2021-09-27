package com.example.springbootrestfjp.producer;

import com.example.springbootrestfjp.exception.InitializationException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@Slf4j
public class LineProducer {

    @Value("${path.to.file}")
    private String PATH_TO_FILE;

    @Getter
    private List<String> uuidList;

    @PostConstruct
    public void produce() {
        try (BufferedReader reader = new BufferedReader(new FileReader(PATH_TO_FILE))) {
            List<String> list = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
            uuidList = Collections.unmodifiableList(list);
        } catch (IOException e) {
            log.error("Эксепшн при чтении файла", e);
            throw new InitializationException("Чтение файла не удалось");
        }
    }
}
