import java.math.BigDecimal
import java.math.RoundingMode

class PayslipCalculator(val empl: Employee, val paymentInput: PaymentInput, val taxTable: TaxTable) {
    val NO_OF_MONTHS = 12
    val RATE = 100
    fun generate(): Payslip {
        val grossIncome = calculateGrossIncome(empl.salary)
        val incomeTax = calculateIncomeTax(empl.salary, taxTable)
        return Payslip(empl.combineFirstAndLastName(),
                paymentInput.combinePaymentStartAndEnd(),
                grossIncome,
                incomeTax,
                calculateNetIncome(grossIncome, incomeTax),
                calculateSuper(empl.salary, paymentInput.superRate));
    }

    private fun calculateGrossIncome(sal: BigDecimal) = removeDecimalPlaces(sal
            .divide(NO_OF_MONTHS.toBigDecimal(), RoundingMode.HALF_UP))


    private fun calculateSuper(sal: BigDecimal, superRate: BigDecimal) = removeDecimalPlaces(sal
            .multiply(superRate)
            .divide(RATE.toBigDecimal(), RoundingMode.HALF_UP))

    private fun calculateIncomeTax(sal: BigDecimal, taxTable: TaxTable): BigDecimal {
        val taxBracket = taxTable.getTaxEntry(sal)
        val incomeTax = removeDecimalPlaces(sal
                .minus(taxBracket.over.toBigDecimal())
                .multiply(taxBracket.cents.toBigDecimal())
                .add(taxBracket.base.toBigDecimal())
                .divide(NO_OF_MONTHS.toBigDecimal(), RoundingMode.HALF_UP))

        return incomeTax
    }

    private fun calculateNetIncome(grossIncome: BigDecimal, incomeTax: BigDecimal) = grossIncome - incomeTax


    private fun removeDecimalPlaces(input: BigDecimal) = input.setScale(0, BigDecimal.ROUND_HALF_UP)
}