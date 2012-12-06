package payroll;

import org.joda.time.DateTime;

public class TimeCardTransaction implements Transaction {

    private final DateTime date;
    private final double workingHours;
    private final int empId;
    private PayrollDatabase db;

    public TimeCardTransaction(DateTime date, double workingHours, int empId, PayrollDatabase db) {
        this.date = date;
        this.workingHours = workingHours;
        this.empId = empId;
        this.db = db;
    }

    @Override
    public void execute() {
        Employee e = db.getEmployee(empId);
        if (e != null) {
            PaymentClassification pc = e.classification;
            HourlyClassification hc = (HourlyClassification) pc;
            hc.addTimeCard(date, workingHours);
        }
    }

}
