package payroll;

import org.joda.time.DateTime;


public class Employee {
    public String address;
    public String name;
    public int empId;
    public PaymentClassification classification;
    public PaymentSchedule schedule;
    public PaymentMethod method;
    public Affiliation affiliation = Affiliation.NULL;


    public Employee(int empId, String name, String address) {
        this.empId = empId;
        this.name = name;
        this.address = address;
    }


    public void payDay(Paycheck paycheck) {
      double grossPay = classification.calculatePay(paycheck);
      double deductions = affiliation.calculateDeductions(paycheck);
      double netPay = grossPay - deductions;
      paycheck.grossPay = grossPay;
      paycheck.deductions = deductions;
      paycheck.netPay = netPay;
      method.pay(paycheck);
    }


    public boolean isPayDate(DateTime payDate) {
      return schedule.isPayDate(payDate);
    }


    public DateTime getPayPeriodStartDate(DateTime payDate) {
      return schedule.getPayPeriodStartDate(payDate);
    }
}
