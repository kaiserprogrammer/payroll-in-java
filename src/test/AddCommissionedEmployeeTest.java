package test;

import static org.junit.Assert.*;

import org.junit.Test;

import payroll.*;

public class AddCommissionedEmployeeTest {

    @Test
    public void test() {
        int empId = 1;
        PayrollDatabase db = new PayrollDatabase();
        AddCommissionedEmployee t = new AddCommissionedEmployee(empId, "Bob", "Home", 1300.0, 0.15, db);
        t.execute();

        Employee e = db.getEmployee(empId);

        PaymentClassification pc = e.classification;
        assertTrue(pc instanceof CommissionedClassification);
        CommissionedClassification cc = (CommissionedClassification) pc;
        assertEquals(1300.0, cc.baseRate, 0.001);
        assertEquals(0.15, cc.commission, 0.001);

        PaymentSchedule ps = e.schedule;
        assertTrue(ps instanceof BiWeeklySchedule);

        PaymentMethod pm = e.method;
        assertTrue(pm instanceof HoldMethod);
    }

}
