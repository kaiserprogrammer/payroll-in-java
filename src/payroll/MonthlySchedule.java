package payroll;

import org.joda.time.DateTime;

public class MonthlySchedule implements PaymentSchedule {
  public boolean isPayDate(DateTime payDate) {
    return isLastDayOfMonth(payDate);
  }

  private boolean isLastDayOfMonth(DateTime date) {
    return date.plusMonths(1).getMonthOfYear() == date.plusDays(1).getMonthOfYear();
  }

  @Override
  public DateTime getPayPeriodStartDate(DateTime payDate) {
    return null;
  }
}
