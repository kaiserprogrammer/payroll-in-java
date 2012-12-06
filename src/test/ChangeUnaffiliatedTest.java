package test;

import static org.junit.Assert.*;

import org.junit.Test;

import payroll.*;

public class ChangeUnaffiliatedTest {

  @Test
  public void test() {
    int empId = 8;
    PayrollDatabase db = new PayrollDatabase();
    AddHourlyEmployee t = new AddHourlyEmployee(empId, "Bill", "Home", 15.25, db);
    t.execute();

    int memberId = 7743;
    ChangeMemberTransaction cmt = new ChangeMemberTransaction(empId, memberId, 99.42, db);
    cmt.execute();
    Employee e = db.getEmployee(empId);
    assertNotNull(e);
    new ChangeUnaffiliatedTransaction(empId, db).execute();
    Affiliation af = e.affiliation;
    assertNotNull(af);
    assertEquals(Affiliation.NULL, af);
    Employee member = db.getMember(memberId);
    assertNull(member);
  }
}