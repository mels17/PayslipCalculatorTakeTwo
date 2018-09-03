package test.java;

import main.kotlin.ListTaxTable;
import main.kotlin.TaxTable;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class ListTaxTableTest {

    TaxTable taxTableEntries;

    @Before
    public void setUp() {
        taxTableEntries = new ListTaxTable(AppTest.taxTable);
    }

    @Test
    public void givenSal0ReturnTaxEntryWithAll0s() {
        Assert.assertEquals(AppTest.taxTable.get(0), taxTableEntries.getTaxEntry(BigDecimal.ZERO));
    }

    @Test
    public void givenSal10000ReturnFirstTaxEntry() {
        Assert.assertEquals(AppTest.taxTable.get(0), taxTableEntries.getTaxEntry(BigDecimal.valueOf(10000)));
    }

    @Test
    public void givenSal18200ReturnFirstTaxEntry() {
        Assert.assertEquals(AppTest.taxTable.get(0), taxTableEntries.getTaxEntry(BigDecimal.valueOf(10000)));
    }

    @Test
    public void givenDecimalSalReturnFirstTaxEntry() {
        Assert.assertEquals(AppTest.taxTable.get(0), taxTableEntries.getTaxEntry(BigDecimal.valueOf(100.90)));
    }

    @Test
    public void givenDecimalSalReturnFirstTaxbracketAfterRounding() {
        Assert.assertEquals(AppTest.taxTable.get(0), taxTableEntries.getTaxEntry(BigDecimal.valueOf(18200.05)));
    }

    @Test
    public void givenDecimalSalRoundUpToGiveSecondTaxBracket() {
        Assert.assertEquals(AppTest.taxTable.get(1), taxTableEntries.getTaxEntry(BigDecimal.valueOf(18200.99)));
    }

    @Test
    public void givenSalGivesTaxBracket() {
        Assert.assertEquals(AppTest.taxTable.get(3), taxTableEntries.getTaxEntry(BigDecimal.valueOf(90000)));
    }
}
