package payroll;

public class AddSalariedEmployee extends AddEmployeeTransaction {

    private double salary;

    public AddSalariedEmployee(int empId, String name, String address, double salary, PayrollDatabase db) {
        super(empId, name, address, db);
        this.salary = salary;
    }

    @Override
    protected PaymentClassification MakeClassification() {
        return new SalariedClassification(salary);
    }

    @Override
    protected PaymentSchedule MakeSchedule() {
        return new MonthlySchedule();
    }
}
