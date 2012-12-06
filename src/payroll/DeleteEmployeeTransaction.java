package payroll;

public class DeleteEmployeeTransaction implements Transaction {

    private int id;
    private PayrollDatabase db;

    public DeleteEmployeeTransaction(int id, PayrollDatabase db) {
        this.id = id;
        this.db = db;
    }

    @Override
    public void execute() {
        db.deleteEmployee(id);
    }

}
