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

    @Test
    public void givenSal0AndSuper0_ReturnConcatenatedNamesAndPayPeriodsWithTheRest0() {
        Employee testEmployee = new Employee("first", "last", 0);
        PaymentInput paymentInput = new PaymentInput(0, "start date", "end date");
        List<TaxEntry> taxEntries = Arrays.asList(new TaxEntry(0, 0, 0, 0.0, 0, 0));

        Payslip expectedPayslip = new Payslip("first last", "start date - end date",
                0, 0, 0, 0);

        Payslip actualPayslip = new PayslipCalculator(testEmployee, paymentInput, taxEntries).generate();

        assertEquals(expectedPayslip, actualPayslip);
    }
}
