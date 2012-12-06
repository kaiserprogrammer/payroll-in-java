package test;

import static org.junit.Assert.*;

import org.junit.Test;

import payroll.*;

public class ChangeCommissionTransactionTest {

  @Test
  public void test() {
    int empId = 500;
    PayrollDatabase db = new PayrollDatabase();
    AddEmployeeTransaction t = new AddSalariedEmployee(empId, "Bill", "Home", 2500.00, db);
    t.execute();

    ChangeCommissionTransaction cct = new ChangeCommissionTransaction(empId, 1500.00, 3.5, db);
    cct.execute();

    Employee e = db.getEmployee(empId);
    PaymentClassification pc = e.classification;
    assertTrue(pc instanceof CommissionedClassification);
    CommissionedClassification cc = (CommissionedClassification) pc;
    assertEquals(1500.0, cc.baseRate, 0.001);
    assertEquals(3.5, cc.commission, 0.001);

    assertTrue(e.schedule instanceof BiWeeklySchedule);
  }

}
