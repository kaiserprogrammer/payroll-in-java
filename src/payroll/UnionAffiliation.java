package payroll;

import java.util.*;

import org.joda.time.DateTime;


public class UnionAffiliation extends Affiliation {

    private HashMap<DateTime, ServiceCharge> charges = new HashMap<DateTime, ServiceCharge>();
    public double dues;
    public int memberId;

    public UnionAffiliation(int memberId, double dues) {
      this.memberId = memberId;
      this.dues = dues;
    }

    public ServiceCharge getServiceCharge(DateTime date) {
        return charges.get(date);
    }

    public void addServiceCharge(ServiceCharge sc) {
        charges.put(sc.date, sc);
    }

    @Override
    public double calculateDeductions(Paycheck paycheck) {
      double deductions = dues;
      for(DateTime date = paycheck.startDate; date.isBefore(paycheck.payDate.plusDays(1));date = date.plusDays(1)) {
        if (charges.get(date) != null) {
          deductions += charges.get(date).charge;
        }
      }
      return deductions;
    }
}
