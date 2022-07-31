package ru.viktorxdev.easypayment.web

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.viktorxdev.easypayment.PaymentQueueProducerService
import java.math.BigDecimal

@RestController
@RequestMapping("/api/v1")
class PaymentController(
    private val paymentQueueProducerService: PaymentQueueProducerService,
) {

    @PostMapping("/pay")
    fun getPaymentInfo(@RequestBody paymentInfo: PaymentInfo) =
        paymentQueueProducerService.putPaymentInQueue(
            accountId = paymentInfo.accountId,
            amount = paymentInfo.amount
        ).voidResult
}

data class PaymentInfo(
    val accountId: Long,
    val amount: BigDecimal
)