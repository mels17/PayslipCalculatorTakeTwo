import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PayslipCalculatorTest {

    public List<TaxEntry> taxTable = Arrays.asList(new TaxEntry(0, 18200, 0, 0.0, 0, 0),
            new TaxEntry(18201, 37000, 0, 19.0, 1, 18200),
            new TaxEntry(37001, 87000, 3572, 32.5, 1, 37000),
            new TaxEntry(87001, 180000, 19822, 37.0, 1, 87000),
            new TaxEntry(180001, Integer.MAX_VALUE, 54232, 45.0, 1, 180000));

    private Employee getEmployee(int salary) {
        return new Employee("first", "last", salary);
    }

    private PaymentInput getPaymentInput(int superRate) {
        return new PaymentInput(superRate, "start date", "end date");
    }
    @Test
    public void givenSal0AndSuper0_ReturnConcatenatedNamesAndPayPeriodsWithTheRest0() {
        List<TaxEntry> taxEntries = Arrays.asList(new TaxEntry(0, 0, 0, 0.0, 0, 0));

        Payslip expectedPayslip = new Payslip("first last", "start date - end date",
                0, 0, 0, 0);

        Payslip actualPayslip = new PayslipCalculator(getEmployee(0), getPaymentInput(0), taxEntries).generate();

        assertEquals(expectedPayslip, actualPayslip);
    }

    @Test
    public void givenSal120AndSuper0_ReturnGross10() {

    }
}
