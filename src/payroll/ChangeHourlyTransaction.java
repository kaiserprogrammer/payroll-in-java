package payroll;

public class ChangeHourlyTransaction extends ChangeClassificationTransaction {

  private final double hourlyRate;

  public ChangeHourlyTransaction(int empId, double hourlyRate, PayrollDatabase db) {
    super(empId, db);
    this.hourlyRate = hourlyRate;
  }

  @Override
  protected PaymentClassification getClassification() {
    return new HourlyClassification(hourlyRate);
  }

  @Override
  protected PaymentSchedule getSchedule() {
    return new WeeklySchedule();
  }

}
