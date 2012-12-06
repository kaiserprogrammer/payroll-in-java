package payroll;

import org.joda.time.DateTime;

public class Paycheck {

  public final DateTime payDate;
  public DateTime startDate;
  public double grossPay;
  public double deductions;
  public double netPay;

  public Paycheck(DateTime startDate, DateTime payDate) {
    this.startDate = startDate;
    this.payDate = payDate;
  }
}
