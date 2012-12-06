package payroll;

import org.joda.time.DateTime;

public class TimeCard {

    public final double hours;
    public final DateTime date;

    public TimeCard(DateTime date, double hours) {
        this.date = date;
        this.hours = hours;
    }

}
