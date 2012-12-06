package payroll;

public class ChangeUnaffiliatedTransaction extends ChangeAffiliationTransaction {

  private PayrollDatabase db;

  public ChangeUnaffiliatedTransaction(int empId, PayrollDatabase db) {
    super(empId, db);
    this.db = db;
  }

  @Override
  protected void recordMembership(Employee e) {
    Affiliation af = e.affiliation;
    if (af instanceof UnionAffiliation) {
      UnionAffiliation uf = (UnionAffiliation) af;
      int memberId = uf.memberId;
      db.removeUnionMember(memberId);
    }
  }

  @Override
  protected Affiliation getAffiliation() {
    return Affiliation.NULL;
  }
}
