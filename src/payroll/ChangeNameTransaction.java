package payroll;

public class ChangeNameTransaction extends ChangeEmployeeTransaction {

    private final String newName;

    public ChangeNameTransaction(int empId, String newName, PayrollDatabase db) {
        super(empId, db);
        this.newName = newName;
    }

    @Override
    protected void change(Employee e) {
        e.name = newName;
    }

}
