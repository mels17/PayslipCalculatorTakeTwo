import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PayslipCalculatorTest {

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
