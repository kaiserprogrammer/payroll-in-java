package payroll;

import java.util.*;

import org.joda.time.DateTime;

public class PaydayTransaction implements Transaction {

  private final DateTime payDate;
  private HashMap<Integer, Paycheck> paychecks = new HashMap<Integer, Paycheck>();
  private PayrollDatabase db;

  public PaydayTransaction(DateTime payDate, PayrollDatabase db) {
    this.payDate = payDate;
    this.db = db;
  }

  @Override
  public void execute() {
    Collection<Employee> employees = db.getAllEmployees();
    for (Employee e : employees) {
      if (e.isPayDate(payDate)) {
        DateTime startDate = e.getPayPeriodStartDate(payDate); 
        Paycheck pc = new Paycheck(startDate, payDate);
        paychecks.put(e.empId, pc);
        e.payDay(pc);
      }
    }
  }

  public Paycheck getPaycheck(int empId) {
    return paychecks.get(empId);
  }

}
