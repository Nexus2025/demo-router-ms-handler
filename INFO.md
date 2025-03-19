Kafka

**Включение ручного коммита для консюмера:**

В конфиге настроить kafkaConfig.put(ConsumerConfig.GROUP_ID_CONFIG, false);
В конфиге настроить containerFactory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
В консюмере коммитим ack.acknowledge();