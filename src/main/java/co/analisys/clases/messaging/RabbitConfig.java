package co.analisys.clases.messaging;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    // Unified topic exchange for all gym domain events
    public static final String EXCHANGE = "gimnasio.exchange";

    // Routing keys
    public static final String ROUTING_KEY_ENTRENADOR_CREADO = "entrenador.creado";
    public static final String ROUTING_KEY_CLASE_INSCRIPCION_CREADA = "clase.inscripcion.creada";
    public static final String ROUTING_KEY_CLASE_HORARIO_ACTUALIZADO = "clase.horario.actualizado";

    // Existing queue (still listening entrenador.creado for demo)
    public static final String QUEUE_CLASES = "clases.entrenador.creado.queue";

    @Bean
    public TopicExchange gimnasioExchange() {
        return new TopicExchange(EXCHANGE, true, false);
    }

    @Bean
    public Queue clasesQueue() {
        return new Queue(QUEUE_CLASES, false);
    }

    @Bean
    public Binding clasesBinding(Queue clasesQueue, TopicExchange gimnasioExchange) {
        return BindingBuilder.bind(clasesQueue).to(gimnasioExchange).with(ROUTING_KEY_ENTRENADOR_CREADO);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
