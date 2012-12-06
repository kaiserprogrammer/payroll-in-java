package test;

import static org.junit.Assert.*;

import org.joda.time.DateTime;
import org.junit.Test;

import payroll.*;

public class TimeCardTransactionTest {

    @Test
    public void test() {
        int empId = 5;
        PayrollDatabase db = new PayrollDatabase();
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Jimmy", "Home", 21.00, db);
        t.execute();

        DateTime date = new DateTime(2005, 7, 31, 0, 0);
        TimeCardTransaction tct = new TimeCardTransaction(date, 8.0, empId, db);
        tct.execute();

        Employee e = db.getEmployee(empId);
        assertNotNull(e);

        PaymentClassification pc = e.classification;
        assertTrue(pc instanceof HourlyClassification);
        HourlyClassification hc = (HourlyClassification) pc;

        TimeCard tc = hc.getTimeCard(date);
        assertNotNull(tc);
        assertEquals(8.0, tc.hours, 0.001);
        assertEquals(date, tc.date);
    }

}
