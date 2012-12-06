package payroll;


public abstract class Affiliation {
    public static Affiliation NULL = new NoAffiliation();

    private static class NoAffiliation extends Affiliation {

      @Override
      public double calculateDeductions(Paycheck paycheck) {
        return 0;
      }
    }

    public abstract double calculateDeductions(Paycheck paycheck);
}
