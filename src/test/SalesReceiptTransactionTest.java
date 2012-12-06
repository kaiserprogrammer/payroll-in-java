package test;

import static org.junit.Assert.*;

import org.joda.time.DateTime;
import org.junit.Test;

import payroll.*;

public class SalesReceiptTransactionTest {

    @Test
    public void test() {
        int empId = 6;
        PayrollDatabase db = new PayrollDatabase();
        AddCommissionedEmployee t = new AddCommissionedEmployee(empId, "John", "Home", 2500.0, 3.2, db);
        t.execute();

        DateTime date = new DateTime(2005, 7, 31, 0, 0);
        SalesReceiptTransaction srt = new SalesReceiptTransaction(date, 1000.00, empId, db);
        srt.execute();

        Employee e = db.getEmployee(empId);
        CommissionedClassification cc = (CommissionedClassification) e.classification;

        SalesReceipt sr = cc.getSalesReceipt(date);
        assertNotNull(sr);
        assertEquals(date, sr.date);
        assertEquals(1000.0, sr.amount, 0.001);
    }
}
