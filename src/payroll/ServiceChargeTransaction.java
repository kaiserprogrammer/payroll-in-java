package payroll;

import org.joda.time.DateTime;

public class ServiceChargeTransaction implements Transaction {

    private final int memberId;
    private final DateTime date;
    private final double charge;
    private PayrollDatabase db;

    public ServiceChargeTransaction(int memberId, DateTime date, double charge, PayrollDatabase db) {
        this.memberId = memberId;
        this.date = date;
        this.charge = charge;
        this.db = db;
    }

    @Override
    public void execute() {
        Employee e = db.getMember(memberId);
        Affiliation af = e.affiliation;
        if (af instanceof UnionAffiliation) {
            UnionAffiliation uaf = (UnionAffiliation) af;
            uaf.addServiceCharge(new ServiceCharge(date, charge));
        }
    }

}
