package payroll;

public abstract class ChangeClassificationTransaction extends ChangeEmployeeTransaction {

  protected abstract PaymentClassification getClassification();
  protected abstract PaymentSchedule getSchedule();
  public ChangeClassificationTransaction(int empId, PayrollDatabase db) {
    super(empId, db);
  }

  @Override
  protected void change(Employee e) {
    e.classification = getClassification();
    e.schedule = getSchedule();
  }
}
