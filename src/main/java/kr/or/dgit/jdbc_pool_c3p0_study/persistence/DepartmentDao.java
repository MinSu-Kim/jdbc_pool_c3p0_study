package kr.or.dgit.jdbc_pool_c3p0_study.persistence;

import java.util.List;

import kr.or.dgit.jdbc_pool_c3p0_study.domain.Department;

public interface DepartmentDao {
	List<Department> selectDepartmentByAll();
	Department selectDepartmentByCode(String deptNo);
	int insertDepartment(Department department);
	int deleteDepartment(String deptNo);
	int updateDepartment(Department department);
}
