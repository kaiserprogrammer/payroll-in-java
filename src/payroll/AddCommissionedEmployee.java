package payroll;

public class AddCommissionedEmployee extends AddEmployeeTransaction{

    private double baseRate;
    private double commission;

    public AddCommissionedEmployee(int empId, String name, String address, double baseRate, double commission, PayrollDatabase db) {
        super(empId, name, address, db);
        this.commission = commission;
        this.baseRate = baseRate;
    }

    @Override
    protected PaymentClassification MakeClassification() {
        return new CommissionedClassification(baseRate, commission);
    }

    @Override
    protected PaymentSchedule MakeSchedule() {
        return new BiWeeklySchedule();
    }
}
