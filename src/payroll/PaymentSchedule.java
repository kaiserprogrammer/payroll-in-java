package payroll;

import org.joda.time.DateTime;

public interface PaymentSchedule {

  boolean isPayDate(DateTime payDate);

  DateTime getPayPeriodStartDate(DateTime payDate);

}
