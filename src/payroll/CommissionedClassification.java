package payroll;

import java.util.*;

import org.joda.time.DateTime;




public class CommissionedClassification implements PaymentClassification {

    public double commission;
    public double baseRate;
    private HashMap<DateTime, SalesReceipt> salesReceipts = new HashMap<DateTime, SalesReceipt>();

    public CommissionedClassification(double baseRate, double commission) {
        this.baseRate = baseRate;
        this.commission = commission;
    }

    public SalesReceipt getSalesReceipt(DateTime date) {
        return salesReceipts.get(date);
    }

    public void addSalesReceipt(DateTime date, double amount) {
        salesReceipts.put(date, new SalesReceipt(date, amount));
    }

    @Override
    public double calculatePay(Paycheck paycheck) {
      double salary = baseRate;
      for(DateTime date = paycheck.startDate; date.isBefore(paycheck.payDate.plusDays(1));date = date.plusDays(1)) {
        if (salesReceipts.get(date) != null) {
          double amount = salesReceipts.get(date).amount;
          double commission = this.commission * amount / 100.0;
          salary += commission;
        }
      }
      return salary;
    }
}
