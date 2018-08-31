import java.math.BigDecimal

data class Payslip(val employeeName: String, val payPeriod: String, val grossIncome: BigDecimal, val incomeTax: BigDecimal,
                   val netIncome: BigDecimal, val superAmt: BigDecimal) {
}