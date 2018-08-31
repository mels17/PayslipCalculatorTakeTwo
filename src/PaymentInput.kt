import java.math.BigDecimal

data class PaymentInput(val superRate: BigDecimal, val paymentStart: String, val paymentEnd: String) {
    fun combinePaymentStartAndEnd() = "$paymentStart - $paymentEnd"
}