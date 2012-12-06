package payroll;

public abstract class ChangeAffiliationTransaction extends ChangeEmployeeTransaction {

  public ChangeAffiliationTransaction(int empId, PayrollDatabase db) {
    super(empId, db);
  }

  @Override
  protected void change(Employee e) {
    recordMembership(e);
    e.affiliation = getAffiliation();
  }

  protected abstract void recordMembership(Employee e);
  protected abstract Affiliation getAffiliation();
}
