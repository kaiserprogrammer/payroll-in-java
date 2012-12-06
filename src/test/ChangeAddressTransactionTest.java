package test;

import static org.junit.Assert.*;

import org.junit.Test;

import payroll.*;

public class ChangeAddressTransactionTest {

  @Test
  public void test() {
    int empId = 11;
    PayrollDatabase db = new PayrollDatabase();
    AddSalariedEmployee t = new AddSalariedEmployee(empId, "John", "before", 1000.00, db);
    t.execute();

    ChangeAddressTransaction cat = new ChangeAddressTransaction(empId, "after", db);
    cat.execute();

    Employee e = db.getEmployee(empId);
    assertEquals("after", e.address);
  }

}
