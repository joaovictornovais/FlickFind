package br.com.flickfind.catalog.configs;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${broker.queue.catalog.name}")
    private String queue;

    @Bean
    public Queue queue() {
        return new Queue(queue, true);
    }

}
