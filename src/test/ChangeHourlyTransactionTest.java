package test;

import static org.junit.Assert.*;

import org.junit.Test;

import payroll.*;

public class ChangeHourlyTransactionTest {

  @Test
  public void test() {
    int empId = 3;
    PayrollDatabase db = new PayrollDatabase();
    AddCommissionedEmployee t = new AddCommissionedEmployee(empId, "John", "Home", 2500, 3.2, db);
    t.execute();

    ChangeHourlyTransaction cht = new ChangeHourlyTransaction(empId, 27.52, db);
    cht.execute();

    Employee e = db.getEmployee(empId);
    PaymentClassification pc = e.classification;
    assertTrue(pc instanceof HourlyClassification);
    HourlyClassification hc = (HourlyClassification) pc;
    assertEquals(27.52, hc.hourlyRate, 0.001);
    assertTrue(e.schedule instanceof WeeklySchedule);
  }

}
