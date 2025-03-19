package com.romanf.demo.handler.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumer {

    @KafkaListener(
            topics = "#{handlerKafkaProperties.incomingTopics}",
            groupId = "#{handlerKafkaProperties.groupId}",
            containerFactory = "handlerListenerContainerFactory"
    )
    public void listen(ConsumerRecord<String, String> record, Acknowledgment ack) {
        log.info("-----------");
        log.info("headers: " + record.headers());
        log.info("key: " + record.key());
        log.info("value: " + record.value());
        log.info("-----------");

        //Acknowledgment(manual commit) - добавляется для ручного коммита в кафку о том, что сообщение обработано консюмером
        //без него(auto commit) -  Kafka сама отмечает сообщения как обработанные
        ack.acknowledge();
    }
}
