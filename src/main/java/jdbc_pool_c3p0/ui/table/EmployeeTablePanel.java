package jdbc_pool_c3p0.ui.table;

import javax.swing.SwingConstants;

import jdbc_pool_c3p0.dto.Employee;

@SuppressWarnings("serial")
public class EmployeeTablePanel extends AbstractTablePanel<Employee> {
	
	public EmployeeTablePanel() {
		super("사원");
	}

	@Override
	protected void setAlignWith() {
		tableCellAlignment(SwingConstants.CENTER, 0, 1, 2, 4, 5, 6, 7);
		tableCellAlignment(SwingConstants.RIGHT, 3);
		tableSetWidth(100, 200, 100, 150, 50, 100, 100, 150);
	}

	@Override
	protected Object[] toArray(Employee item) {
		return new Object[] { item.getEmpNo(), item.getEmpName(), item.getTitle().getCode(),
				String.format("%,d", item.getSalary()), item.isGender() ? "남" : "여", item.getHobby(),
				item.getDept().getDeptNo(), item.getJoinDate() };
	}

	@Override
	protected void setColumnNames() {
		colNames = new String[] { "사원번호", "사원명", "직책", "급여", "성별", "취미", "부서", "입사일" };
	}

	@Override
	public Employee getItem() throws Exception {
		int row = getSelectedRowIndex();
		return new Employee(String.valueOf(table.getValueAt(row, 0)));
	}

}
