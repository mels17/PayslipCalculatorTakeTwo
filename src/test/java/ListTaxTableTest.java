package test.java;

import main.kotlin.ListTaxTable;
import main.kotlin.TaxEntry;
import main.kotlin.TaxTable;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class ListTaxTableTest {

    TaxTable taxTableEntries;

    public static List<TaxEntry> taxTable = Arrays.asList(new TaxEntry(0, 18200, 0, 0.0, 0, 0),
            new TaxEntry(18201, 37000, 0, 0.19, 1, 18200),
            new TaxEntry(37001, 87000, 3572, 0.325, 1, 37000),
            new TaxEntry(87001, 180000, 19822, 0.37, 1, 87000),
            new TaxEntry(180001, Integer.MAX_VALUE, 54232, 0.45, 1, 180000));

    @Before
    public void setUp() {
        taxTableEntries = new ListTaxTable(taxTable);
    }

    @Test
    public void givenSal0ReturnTaxEntryWithAll0s() {
        Assert.assertEquals(taxTable.get(0), taxTableEntries.getTaxEntry(BigDecimal.ZERO));
    }

    @Test
    public void givenSal10000ReturnFirstTaxEntry() {
        Assert.assertEquals(taxTable.get(0), taxTableEntries.getTaxEntry(BigDecimal.valueOf(10000)));
    }

    @Test
    public void givenSal18200ReturnFirstTaxEntry() {
        Assert.assertEquals(taxTable.get(0), taxTableEntries.getTaxEntry(BigDecimal.valueOf(10000)));
    }

    @Test
    public void givenDecimalSalReturnFirstTaxEntry() {
        Assert.assertEquals(taxTable.get(0), taxTableEntries.getTaxEntry(BigDecimal.valueOf(100.90)));
    }

    @Test
    public void givenDecimalSalReturnFirstTaxbracketAfterRounding() {
        Assert.assertEquals(taxTable.get(0), taxTableEntries.getTaxEntry(BigDecimal.valueOf(18200.05)));
    }

    @Test
    public void givenDecimalSalRoundUpToGiveSecondTaxBracket() {
        Assert.assertEquals(taxTable.get(1), taxTableEntries.getTaxEntry(BigDecimal.valueOf(18200.99)));
    }

    @Test
    public void givenSalGivesTaxBracket() {
        Assert.assertEquals(taxTable.get(3), taxTableEntries.getTaxEntry(BigDecimal.valueOf(90000)));
    }
}
