package payroll;

import org.joda.time.DateTime;

public class SalesReceipt {

    public final DateTime date;
    public final double amount;

    public SalesReceipt(DateTime date, double amount) {
        this.date = date;
        this.amount = amount;
    }
}
