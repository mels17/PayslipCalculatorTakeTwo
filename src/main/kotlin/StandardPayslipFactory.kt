package main.kotlin

import java.math.BigDecimal
import java.math.RoundingMode

class StandardPayslipFactory(): PayslipFactory {
    val NO_OF_MONTHS = 12
    val RATE = 100
    override fun create(employee: Employee, taxTable: TaxTable): Payslip {
        val grossIncome = calculateGrossIncome(employee.salary)
        val incomeTax = calculateIncomeTax(employee.salary, taxTable)
        return Payslip(employee.fullName(),
                employee.payPeriod(),
                grossIncome,
                incomeTax,
                calculateNetIncome(grossIncome, incomeTax),
                calculateSuper(grossIncome, employee.superRate));
    }

    private fun calculateGrossIncome(sal: BigDecimal) = removeDecimalPlaces(sal
            .divide(NO_OF_MONTHS.toBigDecimal(), RoundingMode.HALF_UP))


    private fun calculateSuper(grossIncome: BigDecimal, superRate: BigDecimal)= removeDecimalPlaces(grossIncome
            .multiply(superRate)
            .divide(RATE.toBigDecimal(), RoundingMode.HALF_UP))

    private fun calculateIncomeTax(sal: BigDecimal, taxTable: TaxTable): BigDecimal {
        val taxBracket = taxTable.getTaxEntry(sal)
        return removeDecimalPlaces(sal
                .minus(taxBracket.over.toBigDecimal())
                .multiply(taxBracket.cents.toBigDecimal())
                .add(taxBracket.base.toBigDecimal())
                .divide(NO_OF_MONTHS.toBigDecimal(), RoundingMode.HALF_UP))
    }

    private fun calculateNetIncome(grossIncome: BigDecimal, incomeTax: BigDecimal) = grossIncome - incomeTax


    private fun removeDecimalPlaces(input: BigDecimal) = input.setScale(0, BigDecimal.ROUND_HALF_UP)
}