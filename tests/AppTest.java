import org.junit.Test;

import java.util.List;

public class AppTest {
//    @Test
//    public void nothing() {
//        Employee employee =  new Employee("first", "last", 12345);
//        PaymentInput paymentInput = new PaymentInput(5, "abc", "def");
//        Payslip payslip = new PayslipCalculator(employee, paymentInput).generatePayslipOutput();
//    }

    @Test
    public void wholeApplicationTest() {
        InputGetter getter = new ConsoleInputGetter();
        List<TaxEntry> taxEntries = null;

        Payslip payslip = new PayslipGenerator(getter, taxEntries).generatePayslipOutput();
    }
}
