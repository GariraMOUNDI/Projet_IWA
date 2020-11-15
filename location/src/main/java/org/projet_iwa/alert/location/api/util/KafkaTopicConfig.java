package org.projet_iwa.alert.location.api.util;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

class KafkaTopicConfig {
    @Bean
    public NewTopic topic1() {
        return TopicBuilder.name("mytopic-1").build();
    }
    @Bean
    public NewTopic topic2() {
        return TopicBuilder.name("mytopic-2").build();
    }
}