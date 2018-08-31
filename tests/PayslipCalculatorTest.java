import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PayslipCalculatorTest {

    public List<TaxEntry> taxTable = Arrays.asList(new TaxEntry(0, 18200, 0, 0.0, 0, 0),
            new TaxEntry(18201, 37000, 0, 19.0, 1, 18200),
            new TaxEntry(37001, 87000, 3572, 32.5, 1, 37000),
            new TaxEntry(87001, 180000, 19822, 37.0, 1, 87000),
            new TaxEntry(180001, Integer.MAX_VALUE, 54232, 45.0, 1, 180000));

    private Employee getEmployee(double salary) {
        return new Employee("first", "last", BigDecimal.valueOf(salary));
    }

    private PaymentInput getPaymentInput(double superRate) {
        return new PaymentInput(BigDecimal.valueOf(superRate), "start date", "end date");
    }
    @Test
    public void givenSal0AndSuper0_ReturnConcatenatedNamesAndPayPeriodsWithTheRest0() {
        List<TaxEntry> taxEntries = Arrays.asList(new TaxEntry(0, 0, 0, 0.0, 0, 0));

        Payslip expectedPayslip = new Payslip("first last", "start date - end date",
                BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);

        Payslip actualPayslip = new PayslipCalculator(getEmployee(0), getPaymentInput(0), taxEntries).generate();

        assertEquals(expectedPayslip, actualPayslip);
    }

    @Test
    public void givenSal120AndSuper0_ReturnGross10() {
        Payslip actualPayslip = new PayslipCalculator(getEmployee(120), getPaymentInput(0), taxTable).generate();

        assertEquals(BigDecimal.valueOf(10), actualPayslip.getGrossIncome());
    }

    @Test
    public void givenSal18200AndSuper0_ReturnGross1516AferRoundingUp() {
        Payslip actualPayslip = new PayslipCalculator(getEmployee(18200), getPaymentInput(0), taxTable).generate();

        assertEquals(BigDecimal.valueOf(1517), actualPayslip.getGrossIncome());
    }

    @Test
    public void givenSal_ReturnGross100AferRoundingDown() {
        Payslip actualPayslip = new PayslipCalculator(getEmployee(1201.2), getPaymentInput(0), taxTable).generate();

        assertEquals(BigDecimal.valueOf(100), actualPayslip.getGrossIncome());
    }


    @Test
    public void calculateSuperWhenSalaryAndSuperGiven() {
        Payslip actualPayslip = new PayslipCalculator(getEmployee(1200), getPaymentInput(5), taxTable).generate();

        assertEquals(BigDecimal.valueOf(60), actualPayslip.getSuperAmt());
    }

    @Test
    public void roundUpSuperWhenSalaryAndSuperWithDecimalPlacesGiven() {
        Payslip actualPayslip = new PayslipCalculator(getEmployee(120), getPaymentInput(5.55), taxTable).generate();

        assertEquals(BigDecimal.valueOf(7), actualPayslip.getSuperAmt());
    }

    @Test
    public void roundDownSuperWhenSalaryAndSuperWithDecimalPlacesGiven() {
        Payslip actualPayslip = new PayslipCalculator(getEmployee(120), getPaymentInput(5.01), taxTable).generate();

        assertEquals(BigDecimal.valueOf(6), actualPayslip.getSuperAmt());
    }
}
