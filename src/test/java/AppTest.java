package test.java;

import main.kotlin.TaxEntry;

import java.util.Arrays;
import java.util.List;

public class AppTest {

    public static List<TaxEntry> taxTable = Arrays.asList(new TaxEntry(0, 18200, 0, 0.0, 0, 0),
            new TaxEntry(18201, 37000, 0, 0.19, 1, 18200),
            new TaxEntry(37001, 87000, 3572, 0.325, 1, 37000),
            new TaxEntry(87001, 180000, 19822, 0.37, 1, 87000),
            new TaxEntry(180001, Integer.MAX_VALUE, 54232, 0.45, 1, 180000));
//    @Test
//    public void nothing() {
//        main.kotlin.Employee employee =  new main.kotlin.Employee("first", "last", 12345);
//        PaymentInput paymentInput = new PaymentInput(5, "abc", "def");
//        main.kotlin.Payslip payslip = new main.kotlin.StandardPayslipFactory(employee, paymentInput).generatePayslipOutput();
//    }

//    @Test
//    public void wholeApplicationTest() {
//        main.kotlin.MyReader reader = new main.kotlin.ConsoleReader();
//        List<main.kotlin.TaxEntry> taxEntries = null;
//
//        main.kotlin.Payslip payslip = new main.kotlin.PayslipDriver(reader, taxEntries).generatePayslipOutput();
//    }
}
