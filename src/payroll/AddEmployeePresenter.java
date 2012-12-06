package payroll;

public class AddEmployeePresenter {

  private Integer empId;
  private String name;
  private String address;
  private boolean isHourly;
  private Double hourlyRate;
  private boolean hasSalary;
  private Double salary;
  private boolean hasCommission;
  private Double baseRate;
  private Double commission;
  private AddEmployeeView view;
  private TransactionContainer container;
  private PayrollDatabase db;

  private void updateView() {
    this.view.setSubmitEnabled(hasAllInformationCollected());
  }

  public boolean hasAllInformationCollected() {
    return (empId != null) &&
          (name != null && !name.isEmpty()) &&
          (address != null && !address.isEmpty()) &&
                ((isHourly && (hourlyRate != null)) ||
                (hasCommission && baseRate != null && commission != null) ||
                (hasSalary && salary != null));
  }

  public int getEmpId() {
    return empId;
  }

  public void setEmpId(int empId) {
    this.empId = empId;
    updateView();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
    updateView();
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
    updateView();
  }

  public boolean isHourly() {
    return isHourly;
  }

  public void setHourly(boolean isHourly) {
    this.isHourly = isHourly;
    updateView();
  }

  public double getHourlyRate() {
    return hourlyRate;
  }

  public void setHourlyRate(double hourlyRate) {
    this.hourlyRate = hourlyRate;
    updateView();
  }

  public boolean isHasSalary() {
    return hasSalary;
  }

  public void setHasSalary(boolean hasSalary) {
    this.hasSalary = hasSalary;
    updateView();
  }

  public double getSalary() {
    return salary;
  }

  public void setSalary(double salary) {
    this.salary = salary;
    updateView();
  }

  public boolean isHasCommission() {
    return hasCommission;
  }

  public void setHasCommission(boolean hasCommission) {
    this.hasCommission = hasCommission;
    updateView();
  }

  public double getBaseRate() {
    return baseRate;
  }

  public void setBaseRate(double baseRate) {
    this.baseRate = baseRate;
    updateView();
  }

  public double getCommission() {
    return commission;
  }

  public void setCommission(double commission) {
    this.commission = commission;
    updateView();
  }

  public AddEmployeePresenter(AddEmployeeView view,
        TransactionContainer container, PayrollDatabase db) {
          this.view = view;
          this.container = container;
          this.db = db;
  }

}
