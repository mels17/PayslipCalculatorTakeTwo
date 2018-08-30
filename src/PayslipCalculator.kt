class PayslipCalculator(val empl: Employee, val paymentInput: PaymentInput, val taxTable: List<TaxEntry>) {
    fun generate(): Payslip {
        return Payslip(empl.firstName + " " + empl.lastName,
                paymentInput.paymentStart + " - " + paymentInput.paymentEnd,
                0, 0, 0, 0);
    }
}