package ro.zerotohero.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.zerotohero.dao.EmployeeDao;
import ro.zerotohero.model.Employee;

@Service  
@Transactional 
public class EmployeeService {
	@Autowired
	private EmployeeDao employeeDao;

	@Transactional
	public List<Employee> findAll() {
		return employeeDao.findAll(); 
	}
	
	@Transactional
	public void save(Employee employee) {
		employeeDao.save(employee);		
	}
	
	@Transactional
	public Employee findById(int employeeId) {
		return employeeDao.findById(employeeId);
	}
	public void delete(Employee employee) {
		employeeDao.delete(employee);
	}
}
