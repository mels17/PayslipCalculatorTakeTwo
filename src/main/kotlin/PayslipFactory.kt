package main.kotlin

interface PayslipFactory {
    fun create(employee: Employee, taxTable: TaxTable) : Payslip
}