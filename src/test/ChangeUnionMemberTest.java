package test;

import static org.junit.Assert.*;

import org.junit.Test;

import payroll.*;

public class ChangeUnionMemberTest {

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
    Affiliation af = e.affiliation;
    assertNotNull(af);
    assertTrue(af instanceof UnionAffiliation);
    UnionAffiliation uf = (UnionAffiliation) af;
    assertEquals(99.42, uf.dues, 0.001);
    Employee member = db.getMember(memberId);
    assertNotNull(member);
    assertEquals(e, member);
  }

}
