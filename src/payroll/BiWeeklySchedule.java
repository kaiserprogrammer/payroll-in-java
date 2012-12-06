package payroll;

import org.joda.time.DateTime;

public class BiWeeklySchedule implements PaymentSchedule {

  @Override
  public boolean isPayDate(DateTime payDate) {
    return payDate.getDayOfWeek() == 5 && payDate.getWeekOfWeekyear() % 2 == 0;
  }

  @Override
  public DateTime getPayPeriodStartDate(DateTime payDate) {
    return payDate.minusDays(payDate.getDayOfWeek() - 1 + 7);
  }

}
