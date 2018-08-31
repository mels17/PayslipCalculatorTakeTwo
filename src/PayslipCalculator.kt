import java.lang.Math.round
import java.math.BigDecimal
import java.math.RoundingMode

class PayslipCalculator(val empl: Employee, val paymentInput: PaymentInput, val taxTable: List<TaxEntry>) {
    val NO_OF_MONTHS = 12
    val RATE = 100
    fun generate(): Payslip {
        return Payslip(empl.combineFirstAndLastName(),
                paymentInput.combinePaymentStartAndEnd(),
                calculateGrossIncome(empl.salary),
                0.toBigDecimal(),
                0.toBigDecimal(),
                calculateSuper(empl.salary, paymentInput.superRate));
    }

    private fun calculateGrossIncome(sal: BigDecimal) = removeDecimalPlaces(sal
            .divide(NO_OF_MONTHS.toBigDecimal(), RoundingMode.HALF_DOWN))


    private fun calculateSuper(sal:BigDecimal, superRate: BigDecimal) = removeDecimalPlaces(sal
            .multiply(superRate)
            .divide(RATE.toBigDecimal(), RoundingMode.HALF_DOWN))

    private fun removeDecimalPlaces(input: BigDecimal) = input.setScale(0, BigDecimal.ROUND_HALF_DOWN)
}