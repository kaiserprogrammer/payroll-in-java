package payroll;

public class ChangeAddressTransaction extends ChangeEmployeeTransaction {

  private final String newAddress;

  public ChangeAddressTransaction(int empId, String newAddress, PayrollDatabase db) {
    super(empId, db);
    this.newAddress = newAddress;
  }

  @Override
  protected void change(Employee e) {
    e.address = newAddress;
  }
}
