package test;

import static org.junit.Assert.*;

import org.joda.time.DateTime;
import org.junit.Test;

import payroll.*;

public class AddServiceChargeTest {

    @Test
    public void test() {
        int empId = 2;
        PayrollDatabase db = new PayrollDatabase();
        AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 15.25, db);
        t.execute();

        Employee e = db.getEmployee(empId);
        assertNotNull(e);

        int memberId = 86;
        new ChangeMemberTransaction(empId, memberId, 15.0, db).execute();

        DateTime date = new DateTime(2005, 7, 31, 0, 0);
        ServiceChargeTransaction sct = new ServiceChargeTransaction(memberId, date, 12.95, db);
        sct.execute();

        ServiceCharge sc = ((UnionAffiliation) (e.affiliation)).getServiceCharge(date);
        assertNotNull(sc);
        assertEquals(12.95, sc.charge, 0.001);
        assertEquals(date, sc.date);
    }

}
