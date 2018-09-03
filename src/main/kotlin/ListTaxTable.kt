package main.kotlin

import java.math.BigDecimal

class ListTaxTable(val taxEntries: List<TaxEntry>): TaxTable {
    override fun getTaxEntry(sal: BigDecimal) : TaxEntry {
        val salWithNoDecimalPlaces = sal.setScale(0, BigDecimal.ROUND_HALF_UP)
        for(entry in taxEntries) {
            if (salWithNoDecimalPlaces.compareTo(entry.start.toBigDecimal()) >= 0 &&
                    salWithNoDecimalPlaces.compareTo(entry.end.toBigDecimal()) <= 0) return entry
        }
        return taxEntries.get(0)
    }
}
