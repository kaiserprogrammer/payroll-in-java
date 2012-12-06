package test;

import static org.junit.Assert.*;

import org.junit.Test;

import payroll.*;

public class AddHourlyEmployeeTest {

    @Test
    public void test() {
        int empId = 1;
        PayrollDatabase db = new PayrollDatabase();
        AddEmployeeTransaction t = new AddHourlyEmployee(empId, "Bob", "Home", 15.00, db);
        t.execute();

        Employee e = db.getEmployee(empId);
        PaymentClassification pc = e.classification;
        assertTrue(pc instanceof HourlyClassification);
        HourlyClassification hc = (HourlyClassification) pc;
        assertEquals(15.00, hc.hourlyRate, .001);

        PaymentSchedule ps = e.schedule;
        assertTrue(ps instanceof WeeklySchedule);

        PaymentMethod pm = e.method;
        assertTrue(pm instanceof HoldMethod);
    }

}
