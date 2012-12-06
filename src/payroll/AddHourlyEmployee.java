package payroll;

public class AddHourlyEmployee extends AddEmployeeTransaction {

    private final double hourlyRate;

    public AddHourlyEmployee(int empId, String name, String address, double hourlyRate, PayrollDatabase db) {
        super(empId, name, address, db);
        this.hourlyRate = hourlyRate;
    }
    @Override
    protected PaymentClassification MakeClassification() {
        return new HourlyClassification(hourlyRate);
    }

    @Override
    protected PaymentSchedule MakeSchedule() {
        return new WeeklySchedule();
    }

}
