package test;

import static org.junit.Assert.*;

import org.junit.Test;

import payroll.*;

public class ChangeSalariedTransactionTest {

  @Test
  public void test() {
    int empId = 15;
    PayrollDatabase db = new PayrollDatabase();
    AddEmployeeTransaction t = new AddHourlyEmployee(empId, "John", "Home", 17.52, db);
    t.execute();

    ChangeSalariedTransaction cst = new ChangeSalariedTransaction(empId, 1700.50, db);
    cst.execute();

    Employee e = db.getEmployee(empId);
    PaymentClassification pc = e.classification;
    assertTrue(pc instanceof SalariedClassification);
    SalariedClassification sc = (SalariedClassification) pc;
    assertEquals(1700.50, sc.salary, 0.001);
    assertTrue(e.schedule instanceof MonthlySchedule);
  }

}
