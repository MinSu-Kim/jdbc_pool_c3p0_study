package kr.or.dgit.jdbc_pool_c3p0.ui.table;

import javax.swing.SwingConstants;

import kr.or.dgit.jdbc_pool_c3p0.domain.Department;

@SuppressWarnings("serial")
public class DepartmentTablePanel extends AbstractTablePanel<Department> {

	public DepartmentTablePanel() {
		super("부서");
	}

	@Override
	protected void setAlignWith() {
		tableCellAlignment(SwingConstants.CENTER, 0, 1, 2);
        tableSetWidth(100, 200, 100);            		
	}

	@Override
	public void setColumnNames() {
		colNames = new String[] { "부서 코드", "부서 명", "위치"};     		
	}

	@Override
	protected Object[] toArray(Department item) {
		return new Object[] {item.getDeptNo(), item.getDeptName(), item.getFloor()};
	}

	@Override
	public Department getItem() throws Exception {
		int row = getSelectedRowIndex();
		return new Department(String.valueOf(table.getValueAt(row, 0)));
	}

}
