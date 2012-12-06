package payroll;

public class ChangeSalariedTransaction extends ChangeClassificationTransaction{

  private final double salary;

  public ChangeSalariedTransaction(int empId, double salary, PayrollDatabase db) {
    super(empId, db);
    this.salary = salary;
  }

  @Override
  protected PaymentClassification getClassification() {
    return new SalariedClassification(salary);
  }

  @Override
  protected PaymentSchedule getSchedule() {
    return new MonthlySchedule();
  }
}
