import java.math.BigDecimal

data class Employee(val firstName: String, val lastName: String, val salary: BigDecimal) {
    fun combineFirstAndLastName() = "$firstName $lastName"
}