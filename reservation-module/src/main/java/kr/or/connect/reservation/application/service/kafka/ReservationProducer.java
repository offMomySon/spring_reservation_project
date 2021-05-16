package kr.or.connect.reservation.application.service.kafka;

import kr.or.connect.reservation.core.presentation.dto.ReservationRequestResult;
import kr.or.connect.reservation.infrastructure.config.kafka.KafkaConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Service
public class ReservationProducer {
    private final KafkaConfig kafkaConfig;
    private final KafkaTemplate<String, ReservationRequestResult> kafkaTemplate;

    @Autowired
    public ReservationProducer(KafkaConfig kafkaConfig, KafkaTemplate<String, ReservationRequestResult> kafkaTemplate) {
        this.kafkaConfig = kafkaConfig;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(ReservationRequestResult requestResult) {
        log.info(String.format("Produce message : %s", requestResult));
        Message<ReservationRequestResult> meesage = MessageBuilder
                .withPayload(requestResult)
                .setHeader(KafkaHeaders.TOPIC, kafkaConfig.getTopicName())
                .build();

        ListenableFuture<SendResult<String, ReservationRequestResult>> future = kafkaTemplate.send(meesage);

        future.addCallback(new ListenableFutureCallback<>() {

            @Override
            public void onSuccess(SendResult<String, ReservationRequestResult> stringObjectSendResult) {
                log.info("Sent message=[" + stringObjectSendResult.getProducerRecord().value().toString() +
                        "] with offset=[" + stringObjectSendResult.getRecordMetadata().offset() + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                log.info("Unable to send message=[] due to : " + ex.getMessage());
            }
        });

    }
}
