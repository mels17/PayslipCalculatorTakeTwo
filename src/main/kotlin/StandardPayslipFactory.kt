package main.kotlin

import java.math.BigDecimal
import java.math.RoundingMode

class StandardPayslipFactory(): PayslipFactory {
    val NO_OF_MONTHS = 12
    val RATE = 100
    override fun create(empl: Employee, taxTable: TaxTable): Payslip {
        val grossIncome = calculateGrossIncome(empl.salary)
        val incomeTax = calculateIncomeTax(empl.salary, taxTable)
        return Payslip(empl.combineFirstAndLastName(),
                empl.combinePaymentStartAndEnd(),
                grossIncome,
                incomeTax,
                calculateNetIncome(grossIncome, incomeTax),
                calculateSuper(empl.salary, empl.superRate));
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