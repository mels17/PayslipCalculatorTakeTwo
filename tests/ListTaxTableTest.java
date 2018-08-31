import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class ListTaxTableTest {

    TaxTable taxTableEntries;

    @Before
    public void setUp() {
        taxTableEntries = new ListTaxTable(AppTest.taxTable);
    }

    @Test
    public void givenSal0ReturnTaxEntryWithAll0s() {
        assertEquals(AppTest.taxTable.get(0), taxTableEntries.getTaxEntry(BigDecimal.ZERO));
    }

    @Test
    public void givenSal10000ReturnFirstTaxEntry() {
        assertEquals(AppTest.taxTable.get(0), taxTableEntries.getTaxEntry(BigDecimal.valueOf(10000)));
    }

    @Test
    public void givenSal18200ReturnFirstTaxEntry() {
        assertEquals(AppTest.taxTable.get(0), taxTableEntries.getTaxEntry(BigDecimal.valueOf(10000)));
    }

    @Test
    public void givenDecimalSalReturnFirstTaxEntry() {
        assertEquals(AppTest.taxTable.get(0), taxTableEntries.getTaxEntry(BigDecimal.valueOf(100.90)));
    }

    @Test
    public void givenDecimalSalReturnFirstTaxbracketAfterRounding() {
        assertEquals(AppTest.taxTable.get(0), taxTableEntries.getTaxEntry(BigDecimal.valueOf(18200.05)));
    }

    @Test
    public void givenDecimalSalRoundUpToGiveSecondTaxBracket() {
        assertEquals(AppTest.taxTable.get(1), taxTableEntries.getTaxEntry(BigDecimal.valueOf(18200.99)));
    }

    @Test
    public void givenSalGivesTaxBracket() {
        assertEquals(AppTest.taxTable.get(3), taxTableEntries.getTaxEntry(BigDecimal.valueOf(90000)));
    }
}
