class PayslipDriver(val myReader: MyReader, val myWriter: MyWriter, val taxTable: TaxTable,
                    val payslipFactory: PayslipFactory) {
    fun start() {
        writeWelcomeMessage()
        val employee = getEmployeeDetails()
        val payslip = payslipFactory.create(employee, taxTable)
        outputPayslip(payslip)
        writeFinalMessage()
    }

    fun getEmployeeDetails(): Employee {
        myWriter.write("Please input your name: ")
        val name = myReader.read()
        myWriter.write("Please input your surname: ")
        val surname = myReader.read()
        myWriter.write("Please enter your annual salary: ")
        val salary = myReader.read().toBigDecimal()
        myWriter.write("Please enter your super rate: ")
        val rate = myReader.read().toBigDecimal()
        myWriter.write("Please enter your payment start date: ")
        val startDate = myReader.read()
        myWriter.write("Please enter your payment end date: ")
        val endDate = myReader.read()

        return Employee(name, surname, salary, rate, startDate, endDate)
    }

    fun outputPayslip(payslip: Payslip) {
        myWriter.write("\nYour payslip has been generated.\n")
        myWriter.write(payslip.getStringOutput())
    }

    fun writeWelcomeMessage() = myWriter.write("Welcome to the payslip generator\n")

    fun writeFinalMessage() = myWriter.write("\nThank you for using MYOB!")
}