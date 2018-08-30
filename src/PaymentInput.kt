data class PaymentInput(val superRate: Int, val paymentStart: String, val paymentEnd: String) {
    fun combinePaymentStartAndEnd() = "$paymentStart - $paymentEnd"
}