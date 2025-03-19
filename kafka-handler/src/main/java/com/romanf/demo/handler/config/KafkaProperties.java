package com.romanf.demo.handler.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
public class KafkaProperties {

    private String bootstrapServers;
    private String incomingTopics;
    private String groupId;
    private Boolean autoCommit;
}
