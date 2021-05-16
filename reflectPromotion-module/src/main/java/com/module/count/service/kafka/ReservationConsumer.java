package com.module.count.service.kafka;

import com.module.count.domain.Product;
import com.module.count.infrastructure.config.repository.ProductRepository;
import kr.or.connect.reservation.core.presentation.dto.ReservationRequestResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;

@Slf4j
@Service
public class ReservationConsumer {

    private final ProductRepository productRepository;

    public ReservationConsumer(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    @KafkaListener(topics = "${kafka.topic-name}", groupId = "${kafka.group-id}", containerFactory = "pushEntityKafkaListenerContainerFactory")
    public void consume(@Payload ReservationRequestResult requestResult) {
        log.info(String.format("Consumed message : %s", requestResult));

        Product product = productRepository.findById(requestResult.getProductId())
                .orElseThrow(() -> {
                    throw new NoSuchElementException();
                });

        updateProductReservationCount(requestResult, product);

    }

    private void updateProductReservationCount(ReservationRequestResult requestResult, Product product) {
        Long totalReservedCount = requestResult.getPrices().stream()
                .map(price -> price.getCount())
                .reduce(0L, Long::sum);

        product.setReservedCount(product.getReservedCount() + totalReservedCount);
    }
}
