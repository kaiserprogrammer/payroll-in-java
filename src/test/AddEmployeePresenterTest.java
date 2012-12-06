package test;

import static org.junit.Assert.*;

import org.junit.Test;

import payroll.*;

public class AddEmployeePresenterTest {

  private class MockAddEmployeeView implements AddEmployeeView {
    private boolean submitEnabled = false;

    @Override
    public void setSubmitEnabled(boolean enabled) {
      this.submitEnabled = enabled;
    }
  }

  private class MockTransactionContainer implements TransactionContainer {

  }

  @Test
  public void collectingAllNecessaryInfos() {
    PayrollDatabase db = new PayrollDatabase();
    MockAddEmployeeView view = new MockAddEmployeeView();
    MockTransactionContainer container = new MockTransactionContainer();
    AddEmployeePresenter presenter = new AddEmployeePresenter(view, container, db);
    assertFalse(presenter.hasAllInformationCollected());
    presenter.setEmpId(5);
    assertFalse(presenter.hasAllInformationCollected());
    presenter.setName("Bill");
    assertFalse(presenter.hasAllInformationCollected());
    presenter.setAddress("123 abc");
    assertFalse(presenter.hasAllInformationCollected());
    presenter.setHourly(true);
    assertFalse(presenter.hasAllInformationCollected());
    presenter.setHourlyRate(12.5);
    assertTrue(presenter.hasAllInformationCollected());

    presenter.setHourly(false);
    assertFalse(presenter.hasAllInformationCollected());
    presenter.setCommission(12.5);
    assertFalse(presenter.hasAllInformationCollected());
    presenter.setHasCommission(true);
    assertFalse(presenter.hasAllInformationCollected());
    presenter.setBaseRate(100.0);
    assertTrue(presenter.hasAllInformationCollected());

    presenter.setHasCommission(false);
    assertFalse(presenter.hasAllInformationCollected());
    presenter.setSalary(1200.);
    assertFalse(presenter.hasAllInformationCollected());
    presenter.setHasSalary(true);
    assertTrue(presenter.hasAllInformationCollected());
  }


  @Test
  public void updatingView() {
    PayrollDatabase db = new PayrollDatabase();
    MockAddEmployeeView view = new MockAddEmployeeView();
    MockTransactionContainer container = new MockTransactionContainer();
    AddEmployeePresenter presenter = new AddEmployeePresenter(view, container, db);
    assertFalse(view.submitEnabled);
    presenter.setEmpId(5);
    assertFalse(view.submitEnabled);
    presenter.setName("Bill");
    assertFalse(view.submitEnabled);
    presenter.setAddress("Work");
    assertFalse(view.submitEnabled);
    presenter.setHourly(true);
    assertFalse(view.submitEnabled);
    presenter.setHourlyRate(15.0);
    assertTrue(view.submitEnabled);
  }
}
