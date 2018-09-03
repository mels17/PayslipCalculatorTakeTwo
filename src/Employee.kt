import java.math.BigDecimal

data class Employee(val firstName: String, val lastName: String, val salary: BigDecimal,
                    val superRate: BigDecimal, val paymentStart: String, val paymentEnd: String) {

    fun combineFirstAndLastName() = "$firstName $lastName"

    fun combinePaymentStartAndEnd() = "$paymentStart - $paymentEnd"
}