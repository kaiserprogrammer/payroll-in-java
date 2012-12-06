package payroll;
import java.util.*;


public class PayrollDatabase {

    private static HashMap<Integer,Employee> employees = new HashMap<Integer,Employee>();
    private static HashMap<Integer,Employee> members = new HashMap<Integer, Employee>();

    public void addEmployee(int id, Employee employee) {
        employees.put(id, employee);
    }

    public Employee getEmployee(int empId) {
        return employees.get(empId);
    }

    public void deleteEmployee(int id) {
        employees.remove(id);
    }

    public void addUnionMember(int memberId, Employee e) {
        members.put(memberId, e);
    }

    public Employee getMember(int memberId) {
        return members.get(memberId);
    }

    public void removeUnionMember(int memberId) {
      members.remove(memberId);
    }

    public Collection<Employee> getAllEmployees() {
      return employees.values();
    }
}
