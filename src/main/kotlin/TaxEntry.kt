package main.kotlin

data class TaxEntry(val start: Int, val end: Int, val base: Int, val cents: Double,
                    val dollar: Int, val over: Int) {
}