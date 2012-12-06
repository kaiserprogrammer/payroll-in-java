package payroll;

import java.util.*;

import org.joda.time.DateTime;




public class HourlyClassification implements PaymentClassification{

    public double hourlyRate;
    public HashMap<DateTime,TimeCard> timecards = new HashMap<DateTime,TimeCard>();

    public HourlyClassification(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public TimeCard getTimeCard(DateTime date) {
        return timecards.get(date);
    }

    public void addTimeCard(DateTime date, double workingHours) {
        timecards.put(date, new TimeCard(date, workingHours));
    }

    @Override
    public double calculatePay(Paycheck paycheck) {
      double salary = 0;
      for(DateTime date = paycheck.startDate; date.isBefore(paycheck.payDate.plusDays(1));date = date.plusDays(1)) {
        if (timecards.get(date) != null) {
          double hours = timecards.get(date).hours;
          salary += hourlyRate * hours;
          if (hours > 8.0)
            salary += hourlyRate * (hours - 8) / 2.0;
        }
      }
      return salary;
    }
}
