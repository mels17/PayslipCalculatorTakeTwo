class PayslipCalculator(val empl: Employee, val paymentInput: PaymentInput, val taxTable: List<TaxEntry>) {
    fun generate(): Payslip {
        return Payslip(empl.combineFirstAndLastName(),
                paymentInput.combinePaymentStartAndEnd(),
                0, 0, 0, 0);
    }
}