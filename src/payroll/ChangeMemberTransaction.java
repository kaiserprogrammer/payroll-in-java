package payroll;

public class ChangeMemberTransaction extends ChangeAffiliationTransaction {

  private final int memberId;
  private final double dues;
  private PayrollDatabase db;

  public ChangeMemberTransaction(int empId, int memberId, double dues, PayrollDatabase db) {
    super(empId, db);
    this.memberId = memberId;
    this.dues = dues;
    this.db = db;
  }

  @Override
  protected void recordMembership(Employee e) {
    db.addUnionMember(memberId, e);
  }

  @Override
  protected Affiliation getAffiliation() {
    return new UnionAffiliation(memberId, dues);
  }

}
