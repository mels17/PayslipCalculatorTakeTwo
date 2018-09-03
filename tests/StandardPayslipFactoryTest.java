import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class StandardPayslipFactoryTest {

    public ListTaxTable listTaxTable = new ListTaxTable(AppTest.taxTable);

    private Employee getEmployee(double salary, double superRate) {
        return new Employee("first", "last",
                BigDecimal.valueOf(salary), BigDecimal.valueOf(superRate),
                "start date", "end date");
    }

    @Test
    public void givenSal0AndSuper0_ReturnConcatenatedNamesAndPayPeriodsWithTheRest0() {
        List<TaxEntry> taxEntries = Arrays.asList(new TaxEntry(0, 0, 0, 0.0, 0, 0));

        Payslip expectedPayslip = new Payslip("first last", "start date - end date",
                BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);

        Payslip actualPayslip = new StandardPayslipFactory().create(getEmployee(0, 0), new ListTaxTable(taxEntries));

        assertEquals(expectedPayslip, actualPayslip);
    }

    @Test
    public void givenSal120AndSuper0_ReturnGross10() {
        Payslip actualPayslip = new StandardPayslipFactory().create(getEmployee(120, 0), listTaxTable);

        assertEquals(BigDecimal.valueOf(10), actualPayslip.getGrossIncome());
    }

    @Test
    public void givenSal18200AndSuper0_ReturnGross1516AferRoundingUp() {
        Payslip actualPayslip = new StandardPayslipFactory().create(getEmployee(18200, 0), listTaxTable);

        assertEquals(BigDecimal.valueOf(1517), actualPayslip.getGrossIncome());
    }

    @Test
    public void givenSal_ReturnGross100AferRoundingDown() {
        Payslip actualPayslip = new StandardPayslipFactory().create(getEmployee(1201.2, 0), listTaxTable);

        assertEquals(BigDecimal.valueOf(100), actualPayslip.getGrossIncome());
    }


    @Test
    public void calculateSuperWhenSalaryAndSuperGiven() {
        Payslip actualPayslip = new StandardPayslipFactory().create(getEmployee(1200, 5), listTaxTable);

        assertEquals(BigDecimal.valueOf(60), actualPayslip.getSuperAmt());
    }

    @Test
    public void roundUpSuperWhenSalaryAndSuperWithDecimalPlacesGiven() {
        Payslip actualPayslip = new StandardPayslipFactory().create(getEmployee(120, 5.55), listTaxTable);

        assertEquals(BigDecimal.valueOf(7), actualPayslip.getSuperAmt());
    }

    @Test
    public void roundDownSuperWhenSalaryAndSuperWithDecimalPlacesGiven() {
        Payslip actualPayslip = new StandardPayslipFactory().create(getEmployee(120, 5.01), listTaxTable);

        assertEquals(BigDecimal.valueOf(6), actualPayslip.getSuperAmt());
    }

    @Test
    public void givenSalInTheFirstTaxBracketReturn0() {
        Payslip actualPayslip = new StandardPayslipFactory().create(getEmployee(10000, 5), listTaxTable);

        assertEquals(BigDecimal.valueOf(0), actualPayslip.getIncomeTax());
    }

    @Test
    public void givenSalInTheSecondTaxBracketCalculateIncomeTax() {
        Payslip actualPayslip = new StandardPayslipFactory().create(getEmployee(20000, 5), listTaxTable);

        assertEquals(BigDecimal.valueOf(29), actualPayslip.getIncomeTax());
    }

    @Test
    public void givenSalInLastTaxBracketCalculateIncomeTax() {
        Payslip actualPayslip = new StandardPayslipFactory().create(getEmployee(500000, 5), listTaxTable);

        assertEquals(BigDecimal.valueOf(16519), actualPayslip.getIncomeTax());
    }

    @Test
    public void givenSalAndSuperCalculateNetIncome() {
        Payslip actualPayslip = new StandardPayslipFactory().create(getEmployee(500000, 5), listTaxTable);

        assertEquals(BigDecimal.valueOf(25148), actualPayslip.getNetIncome());
    }

    @Test
    public void givenSal1200Return100NetIncome() {
        Payslip actualPayslip = new StandardPayslipFactory().create(getEmployee(1200, 5), listTaxTable);

        assertEquals(BigDecimal.valueOf(100), actualPayslip.getNetIncome());
    }
}
