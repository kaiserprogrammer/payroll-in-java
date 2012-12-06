package test;

import static org.junit.Assert.*;

import org.junit.Test;

import payroll.*;

public class DeleteEmployeeTest {

    @Test
    public void test() {
        int empId = 4;
        PayrollDatabase db = new PayrollDatabase();
        AddCommissionedEmployee t = new AddCommissionedEmployee(empId, "Bill", "Home", 2500, 3.2, db);
        t.execute();

        Employee e = db.getEmployee(empId);
        assertNotNull(e);
        DeleteEmployeeTransaction dt = new DeleteEmployeeTransaction(empId, db);
        dt.execute();

        e = db.getEmployee(empId);
        assertNull(e);
    }
}
