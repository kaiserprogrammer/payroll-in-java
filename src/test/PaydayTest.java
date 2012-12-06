package test;

import static org.junit.Assert.*;

import org.joda.time.DateTime;
import org.junit.Test;

import payroll.*;
import payroll.PaydayTransaction;

public class PaydayTest {

  @Test
  public void paySingleSalaried() {
    int empId = 1;
    PayrollDatabase db = new PayrollDatabase();
    AddSalariedEmployee t = new AddSalariedEmployee(empId, "John", "Home", 1000.00, db);
    t.execute();

    DateTime payDate = new DateTime(2011, 11, 30, 0, 0);
    PaydayTransaction pt = new PaydayTransaction(payDate, db);
    pt.execute();

    Paycheck pc = pt.getPaycheck(empId);
    assertNotNull(pc);
    assertEquals(payDate, pc.payDate);
    assertEquals(1000.00, pc.grossPay, .001);
  }

  @Test
  public void notPayingSingleSalariedEmployeeOnWrongDate() {
    int empId = 1;
    PayrollDatabase db = new PayrollDatabase();
    AddSalariedEmployee t = new AddSalariedEmployee(empId, "Bob", "Home", 1000.00, db);
    t.execute();

    DateTime payDate = new DateTime(2011, 11, 29, 0, 0);
    PaydayTransaction pt = new PaydayTransaction(payDate, db);
    pt.execute();

    Paycheck pc = pt.getPaycheck(empId);
    assertNull(pc);
  }

  @Test
  public void noPayWithZeroTimecards() {
    PayrollDatabase db = new PayrollDatabase();
    int empId = 2;
    new AddHourlyEmployee(empId, "Jim", "Home", 12.75, db).execute();

    DateTime payDate = new DateTime(2001, 11, 30, 0, 0);
    PaydayTransaction pt = new PaydayTransaction(payDate, db);
    pt.execute();
    Paycheck pc = pt.getPaycheck(empId);
    assertNotNull(pc);
    assertEquals(payDate.minusDays(4), pc.startDate);
    assertEquals(payDate, pc.payDate);
    assertEquals(0, pc.grossPay, .001);
    assertEquals(0, pc.netPay, .001);
    assertEquals(0, pc.deductions, 0.001);
  }

  @Test
  public void payWithOneTimeCard() {
    PayrollDatabase db = new PayrollDatabase();
    int empId = 3;
    new AddHourlyEmployee(empId, "Jim", "Work", 12.75, db).execute();
    DateTime payDate = new DateTime(2001, 11, 30, 0, 0);
    new TimeCardTransaction(payDate, 2, empId, db).execute();
    PaydayTransaction pt = new PaydayTransaction(payDate, db);
    pt.execute();
    Paycheck pc = pt.getPaycheck(empId);
    assertNotNull(pc);
    assertEquals(25.50, pc.grossPay, 0.001);
    assertEquals(25.50, pc.netPay, 0.001);
  }

  @Test
  public void payForOverTime() {
    PayrollDatabase db = new PayrollDatabase();
    int empId = 4;
    new AddHourlyEmployee(empId, "Jim", "Work", 15.25, db).execute();
    DateTime payDate = new DateTime(2001, 11, 30, 0, 0);
    new TimeCardTransaction(payDate, 9.0, empId, db).execute();
    PaydayTransaction pt = new PaydayTransaction(payDate, db);
    pt.execute();
    Paycheck pc = pt.getPaycheck(empId);
    assertNotNull(pc);
    assertEquals((8 + 1.5) * 15.25, pc.grossPay, 0.001);
    assertEquals((8 + 1.5) * 15.25, pc.netPay, 0.001);
  }

  @Test
  public void noPayOnWrongDate() {
    PayrollDatabase db = new PayrollDatabase();
    int empId = 4;
    new AddHourlyEmployee(empId, "Jim", "Work", 15.25, db).execute();
    DateTime payDate = new DateTime(2001, 11, 29, 0, 0);
    new TimeCardTransaction(payDate, 9.0, empId, db).execute();
    PaydayTransaction pt = new PaydayTransaction(payDate, db);
    pt.execute();
    Paycheck pc = pt.getPaycheck(empId);
    assertNull(pc);
  }

  @Test
  public void payForTwoTimecards() {
    PayrollDatabase db = new PayrollDatabase();
    int empId = 4;
    new AddHourlyEmployee(empId, "Jim", "Work", 15.25, db).execute();
    DateTime payDate = new DateTime(2001, 11, 30, 0, 0);
    new TimeCardTransaction(payDate, 4.0, empId, db).execute();
    new TimeCardTransaction(payDate.minusDays(1), 3.0, empId, db).execute();
    PaydayTransaction pt = new PaydayTransaction(payDate, db);
    pt.execute();
    Paycheck pc = pt.getPaycheck(empId);
    assertNotNull(pc);
    assertEquals(15.25 * 7, pc.grossPay, 0.001);
    assertEquals(15.25 * 7, pc.netPay, 0.001);
  }

  @Test
  public void payOnlyForOnePeriod() {
    PayrollDatabase db = new PayrollDatabase();
    int empId = 4;
    new AddHourlyEmployee(empId, "Jim", "Work", 15.25, db).execute();
    DateTime payDate = new DateTime(2001, 11, 30, 0, 0);
    new TimeCardTransaction(payDate, 4.0, empId, db).execute();
    new TimeCardTransaction(payDate.minusDays(7), 3.0, empId, db).execute();
    PaydayTransaction pt = new PaydayTransaction(payDate, db);
    pt.execute();
    Paycheck pc = pt.getPaycheck(empId);
    assertNotNull(pc);
    assertEquals(15.25 * 4, pc.grossPay, 0.001);
    assertEquals(15.25 * 4, pc.netPay, 0.001);
  }

  @Test
  public void payCommissionedEmployeeWithOneSaleInRightPeriod() {
    PayrollDatabase db = new PayrollDatabase();
    int empId = 4;
    new AddCommissionedEmployee(empId, "Jim", "Work", 1000.0, 10.0, db).execute();
    DateTime payDate = new DateTime(2001, 11, 30, 0, 0);
    new SalesReceiptTransaction(payDate, 500.0, empId, db).execute();
    new SalesReceiptTransaction(payDate.minusDays(14), 500.0, empId, db).execute();
    PaydayTransaction pt = new PaydayTransaction(payDate, db);
    pt.execute();
    Paycheck pc = pt.getPaycheck(empId);
    assertNotNull(pc);
    assertEquals(1050, pc.grossPay, 0.001);
    assertEquals(1050, pc.netPay, 0.001);
  }

  @Test
  public void noPayCommissionedEmployeeOnWrongDate() {
    PayrollDatabase db = new PayrollDatabase();
    int empId = 4;
    new AddCommissionedEmployee(empId, "Jim", "Work", 1000.0, 10.0, db).execute();
    DateTime payDate = new DateTime(2001, 11, 23, 0, 0);
    PaydayTransaction pt = new PaydayTransaction(payDate, db);
    pt.execute();
    Paycheck pc = pt.getPaycheck(empId);
    assertNull(pc);
  }

  @Test
  public void payCommissionedWithNoSales() {
    PayrollDatabase db = new PayrollDatabase();
    int empId = 4;
    new AddCommissionedEmployee(empId, "Jim", "Work", 1000.0, 10.0, db).execute();
    DateTime payDate = new DateTime(2001, 11, 30, 0, 0);
    PaydayTransaction pt = new PaydayTransaction(payDate, db);
    pt.execute();
    Paycheck pc = pt.getPaycheck(empId);
    assertNotNull(pc);
    assertEquals(1000.0, pc.grossPay, 0.001);
    assertEquals(1000.0, pc.netPay, 0.001);
  }

  @Test
  public void deductServiceCharges() {
    PayrollDatabase db = new PayrollDatabase();
    int empId = 4;
    int memberId = 5050;
    double dues = 50.0;
    new AddCommissionedEmployee(empId, "Jim", "Work", 1000.0, 10.0, db).execute();
    DateTime payDate = new DateTime(2001, 11, 30, 0, 0);
    new SalesReceiptTransaction(payDate, 500.0, empId, db).execute();
    new ChangeMemberTransaction(empId, memberId, dues, db).execute();
    double charge = 20;
    new ServiceChargeTransaction(memberId, payDate, charge, db).execute();
    PaydayTransaction pt = new PaydayTransaction(payDate, db);
    pt.execute();
    Paycheck pc = pt.getPaycheck(empId);
    assertNotNull(pc);
    assertEquals(1050, pc.grossPay, 0.001);
    assertEquals(dues + charge, pc.deductions, 0.001);
    assertEquals(1050 - dues - charge, pc.netPay, 0.001);
  }

  @Test
  public void deductServiceChargesWhenSpanningMultiplePeriods() {
    PayrollDatabase db = new PayrollDatabase();
    int empId = 4;
    int memberId = 5050;
    double dues = 50.0;
    new AddCommissionedEmployee(empId, "Jim", "Work", 1000.0, 10.0, db).execute();
    DateTime payDate = new DateTime(2001, 11, 30, 0, 0);
    new SalesReceiptTransaction(payDate, 500.0, empId, db).execute();
    new ChangeMemberTransaction(empId, memberId, dues, db).execute();
    double charge = 20;
    new ServiceChargeTransaction(memberId, payDate.plusDays(1), charge, db).execute();
    new ServiceChargeTransaction(memberId, payDate, charge, db).execute();
    new ServiceChargeTransaction(memberId, payDate.minusDays(14), charge, db).execute();
    PaydayTransaction pt = new PaydayTransaction(payDate, db);
    pt.execute();
    Paycheck pc = pt.getPaycheck(empId);
    assertNotNull(pc);
    assertEquals(1050, pc.grossPay, 0.001);
    assertEquals(dues + charge, pc.deductions, 0.001);
    assertEquals(1050 - dues - charge, pc.netPay, 0.001);
  }
}
