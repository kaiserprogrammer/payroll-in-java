package test;

import static org.junit.Assert.*;

import org.junit.Test;

import payroll.*;

public class ChangeNameTransactionTest {

    @Test
    public void test() {
        int empId = 2;
        PayrollDatabase db = new PayrollDatabase();
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 11.00, db);
        t.execute();

        ChangeNameTransaction cnt = new ChangeNameTransaction(empId, "Bob", db);
        cnt.execute();

        Employee e = db.getEmployee(empId);
        assertEquals("Bob", e.name);
    }

}
