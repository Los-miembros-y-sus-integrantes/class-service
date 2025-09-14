package co.analisys.clases.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static co.analisys.clases.messaging.RabbitConfig.QUEUE_CLASES;

@Component
public class EntrenadorEventListener {

    private static final Logger logger = LoggerFactory.getLogger(EntrenadorEventListener.class);

    @RabbitListener(queues = QUEUE_CLASES)
    public void onEntrenadorCreado(EntrenadorCreadoEvent event) {
        logger.info("[RabbitMQ] Entrenador recibido: id={} nombre={} especialidad={}", event.getId(), event.getNombre(), event.getEspecialidad());
        // Aquí podrías actualizar una caché en memoria si fuese necesario
    }
}
