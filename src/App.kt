import main.kotlin.*
import java.util.*

var taxTable = Arrays.asList(TaxEntry(0, 18200, 0, 0.0, 0, 0),
        TaxEntry(18201, 37000, 0, 0.19, 1, 18200),
        TaxEntry(37001, 87000, 3572, 0.325, 1, 37000),
        TaxEntry(87001, 180000, 19822, 0.37, 1, 87000),
        TaxEntry(180001, Integer.MAX_VALUE, 54232, 0.45, 1, 180000))

fun main(args : Array<String>) {
    val reader = ConsoleReader()
    val writer = ConsoleWriter()
    val taxTable = ListTaxTable(taxTable)
    val payslipFactory = StandardPayslipFactory()
    PayslipDriver(reader, writer, taxTable, payslipFactory).start()
}