class PayslipCalculator {
    companion object {
        val listOfTaxEntries: List<TaxEntry> = listOf(TaxEntry(0, 18200, 0, 0.0, 0, 0),
                TaxEntry(18201, 37000, 0, 19.0, 1, 18200),
                TaxEntry(37001, 87000, 3572, 32.5, 1, 37000),
                TaxEntry(87001, 180000, 19822, 37.0, 1, 87000),
                TaxEntry(180001, Integer.MAX_VALUE, 54232, 45.0, 1, 180000))
    }

    fun generate(empl: Employee, paymentInput: PaymentInput, taxTable: List<TaxEntry>): Payslip {
        return;
    }

    //val abc = PayslipCalculator.listOfTaxEntries

}