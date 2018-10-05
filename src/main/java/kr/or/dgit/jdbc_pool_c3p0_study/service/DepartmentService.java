package kr.or.dgit.jdbc_pool_c3p0_study.service;

import java.util.List;

import kr.or.dgit.jdbc_pool_c3p0_study.domain.Department;
import kr.or.dgit.jdbc_pool_c3p0_study.persistence.DepartmentDao;
import kr.or.dgit.jdbc_pool_c3p0_study.persistence.DepartmentDaoImpl;

public class DepartmentService {
	private DepartmentDao dao;
	
	public DepartmentService() {
		dao =new DepartmentDaoImpl();
	}

	public List<Department> findDepartmentByAll() {
		return dao.selectDepartmentByAll();
	}
	
	public Department findDepartmentByCode(String deptno) {
		return dao.selectDepartmentByCode(deptno);
	}
	
	public int registerDepartment(Department department) {
		return dao.insertDepartment(department);
	}
	
	public int unRegisterDepartment(String deptNo) {
		return dao.deleteDepartment(deptNo);
	}
	
	public int updateDepartment(Department department) {
		return dao.updateDepartment(department);
	}
	
}
