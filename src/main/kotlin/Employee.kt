package main.kotlin

import java.math.BigDecimal

data class Employee(val firstName: String, val lastName: String, val salary: BigDecimal,
                    val superRate: BigDecimal, val paymentStart: String, val paymentEnd: String) {

    fun fullName() = "$firstName $lastName"

    fun payPeriod() = "$paymentStart - $paymentEnd"
}