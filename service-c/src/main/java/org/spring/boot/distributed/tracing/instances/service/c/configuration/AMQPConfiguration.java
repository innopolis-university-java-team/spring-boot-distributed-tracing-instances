package org.spring.boot.distributed.tracing.instances.service.c.configuration;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * The type Amqp configuration.
 *
 * @author Alexander A. Kropotin
 * @project service -c
 * @created 2021 -08-09 20:43 <p>
 */
@FieldDefaults(
        level = AccessLevel.PRIVATE
)
@Configuration
public class AMQPConfiguration {

    @Value("${amqp.rabbit.binder.user}")
    String user;

    @Value("${amqp.rabbit.binder.password}")
    String password;

    @Value("${amqp.rabbit.binder.brokers}")
    String brokers;

    @Value("${amqp.rabbit.bindings.message-request.destination}")
    String messageRequest;

    /**
     * Connection factory connection factory.
     *
     * @return the connection factory
     */
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(brokers);
        connectionFactory.setUsername(user);
        connectionFactory.setPassword(password);

        return connectionFactory;
    }

    /**
     * Rabbit template rabbit template.
     *
     * @param connectionFactory the connection factory
     * @return the rabbit template
     */
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }

    /**
     * Some data events queue.
     *
     * @return the queue
     */
    @Bean
    public Queue someDataExistenceRequest() {
        return new Queue(messageRequest, false);
    }
}
