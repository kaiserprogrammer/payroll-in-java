package payroll;

import org.joda.time.DateTime;


public class WeeklySchedule implements PaymentSchedule {

  @Override
  public boolean isPayDate(DateTime payDate) {
    return payDate.getDayOfWeek() == 5;
  }

  @Override
  public DateTime getPayPeriodStartDate(DateTime payDate) {
    return payDate.minusDays(payDate.getDayOfWeek() - 1);
  }

}
