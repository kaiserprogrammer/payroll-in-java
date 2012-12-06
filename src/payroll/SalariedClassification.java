package payroll;


public class SalariedClassification implements PaymentClassification {

    public double salary;

    public SalariedClassification(double salary) {
        this.salary = salary;
    }

    @Override
    public double calculatePay(Paycheck paycheck) {
      return salary;
    }
}
