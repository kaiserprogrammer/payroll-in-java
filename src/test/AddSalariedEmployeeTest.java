package test;
import static org.junit.Assert.*;

import org.junit.Test;

import payroll.*;


public class AddSalariedEmployeeTest {

    @Test
    public void test() {
        int empId = 1;
        PayrollDatabase db = new PayrollDatabase();
        AddSalariedEmployee t = new AddSalariedEmployee(empId, "Bob", "Home", 1000.00, db);
        t.execute();

        Employee e = db.getEmployee(empId);
        assertEquals("Bob", e.name);

        PaymentClassification pc = e.classification;
        assertTrue(pc instanceof SalariedClassification);
        SalariedClassification sc = (SalariedClassification) pc;
        assertEquals(1000.00, sc.salary, .001);

        PaymentSchedule ps = e.schedule;
        assertTrue(ps instanceof MonthlySchedule);

        PaymentMethod pm = e.method;
        assertTrue(pm instanceof HoldMethod);
    }

}
