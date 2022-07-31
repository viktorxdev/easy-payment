package ru.viktorxdev.easypayment

import mu.KotlinLogging
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class PaymentQueueProducerService(
    private val kafkaTemplate: KafkaTemplate<Long, String>,
) {

    private val log = KotlinLogging.logger { }

    fun putPaymentInQueue(accountId: Long, amount: BigDecimal) = runCatching {
        kafkaTemplate.sendDefault(accountId, amount.toPlainString())
        log.info { "put payment in queue: account_id = $accountId, amount = $amount" }
    }.getOrElse {
        log.error(it) { "fail to send kafka message" }
        throw AppException.KafkaException("fail to put payment in queue: account_id = $accountId, amount = $amount")
    }
}