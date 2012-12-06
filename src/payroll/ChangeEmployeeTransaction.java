package payroll;

public abstract class ChangeEmployeeTransaction implements Transaction {
    private final int empId;
    private PayrollDatabase db;

    public ChangeEmployeeTransaction(int empId, PayrollDatabase db) {
        this.empId = empId;
        this.db = db;
    }

    public void execute() {
        Employee e = db.getEmployee(empId);

        if (e != null) 
            change(e);
        else
            throw new RuntimeException("No such employee: " + empId);
    }

    protected abstract void change(Employee e);
}
