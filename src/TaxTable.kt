import java.math.BigDecimal

interface TaxTable {
    fun getTaxEntry(sal: BigDecimal): TaxEntry
}