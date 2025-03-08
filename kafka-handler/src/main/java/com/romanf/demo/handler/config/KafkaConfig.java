package com.romanf.demo.handler.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Bean(name = "handlerKafkaProperties")
    @ConfigurationProperties(prefix = "handler.kafka.config")
    KafkaProperties handlerKafkaProperties() {
        return new KafkaProperties();
    }

    @Bean(name = "handlerConsumerFactory")
    ConsumerFactory<String, String> handlerConsumerFactory(KafkaProperties handlerKafkaProperties) {

        Map<String, Object> kafkaConfig = new HashMap<>();
        kafkaConfig.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, handlerKafkaProperties.getBootstrapServers());
        kafkaConfig.put(ConsumerConfig.GROUP_ID_CONFIG, handlerKafkaProperties.getGroupId());
        kafkaConfig.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
        kafkaConfig.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);

        return new DefaultKafkaConsumerFactory<>(kafkaConfig, new StringDeserializer(), new StringDeserializer());
    }

    @Bean(name = "handlerListenerContainerFactory")
    KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> handlerListenerContainerFactory(
            ConsumerFactory<String, String> handlerConsumerFactory)
    {
        ConcurrentKafkaListenerContainerFactory<String, String> containerFactory =
                new ConcurrentKafkaListenerContainerFactory<>();

        containerFactory.getContainerProperties().setLogContainerConfig(false);
        containerFactory.setAutoStartup(true);
        containerFactory.setConsumerFactory(handlerConsumerFactory);

        return containerFactory;
    }
}
