package payroll;

import org.joda.time.DateTime;

public class ServiceCharge {

    public final DateTime date;
    public final double charge;

    public ServiceCharge(DateTime date, double charge) {
        this.date = date;
        this.charge = charge;
    }
}
