package employee.presentation;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "employeeManagedBean")
@RequestScoped
public class EmployeeMB {
	@Inject EmployeeDAO dao;
	
	//Auxiliary fields for JSF
	private List<Employee> employeeList = new ArrayList<>();
	private Employee employee = new Employee();
	
	public List<Employee> getEmployeeList() {
		employeeList = dao.findEmployees();
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String addNewEmployee() {
		dao.addNew(employee);
		employeeList = dao.findEmployees();
		return "employeelist";
	}
	
	public String editEmployee (Long id) {
		Employee findEmployee = dao.findEmployee(id);
		if(findEmployee != null) { 
			employee = findEmployee;
			return "employeeedit";
		} else {
			return null;
		}
	}
	
	public String removeEmployee (Long id) {
		dao.remove(id);
		employeeList = dao.findEmployees();
		return "employeelist";
	}
	
	public String confirmEdition () {
		dao.updateEmployee(employee);
		employeeList = dao.findEmployees();
		return "employeelist";
	}
}