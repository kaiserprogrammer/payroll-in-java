package payroll;

public abstract class AddEmployeeTransaction implements Transaction {

    private int empId;
    private String name;
    private String address;
    private PayrollDatabase db;

    public AddEmployeeTransaction (int empId, String name, String address, PayrollDatabase db) {
        this.empId = empId;
        this.name = name;
        this.address = address;
        this.db = db;
    }

    protected abstract PaymentClassification MakeClassification();
    protected abstract PaymentSchedule MakeSchedule();

    public void execute() {
        PaymentClassification pc = MakeClassification();
        PaymentSchedule ps = MakeSchedule();
        PaymentMethod pm = new HoldMethod();

        Employee e = new Employee(empId, name, address);
        e.classification = pc;
        e.schedule = ps;
        e.method = pm;
        db.addEmployee(empId, e);
    }
}
