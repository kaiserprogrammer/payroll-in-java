package payroll;

public class ChangeCommissionTransaction extends ChangeClassificationTransaction{

  private final double baseRate;
  private final double commission;

  public ChangeCommissionTransaction(int empId, double baseRate, double commission, PayrollDatabase db) {
    super(empId, db);
    this.baseRate = baseRate;
    this.commission = commission;
  }

  @Override
  protected PaymentClassification getClassification() {
    return new CommissionedClassification(baseRate, commission);
  }

  @Override
  protected PaymentSchedule getSchedule() {
    return new BiWeeklySchedule();
  }
}
