package payroll;

import org.joda.time.DateTime;

public class SalesReceiptTransaction implements Transaction {

    private final DateTime date;
    private final double amount;
    private final int empId;
    private PayrollDatabase db;

    public SalesReceiptTransaction(DateTime date, double amount, int empId, PayrollDatabase db) {
        this.date = date;
        this.amount = amount;
        this.empId = empId;
        this.db = db;
    }

    @Override
    public void execute() {
        Employee e = db.getEmployee(empId);
        CommissionedClassification cc = (CommissionedClassification) e.classification;
        cc.addSalesReceipt(date, amount);
    }

}
