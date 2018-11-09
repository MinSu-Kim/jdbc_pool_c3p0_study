package kr.or.dgit.jdbc_pool_c3p0;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;

import kr.or.dgit.jdbc_pool_c3p0.domain.Department;
import kr.or.dgit.jdbc_pool_c3p0.domain.Employee;
import kr.or.dgit.jdbc_pool_c3p0.domain.Title;
import kr.or.dgit.jdbc_pool_c3p0.service.DepartmentService;
import kr.or.dgit.jdbc_pool_c3p0.service.EmployeeService;
import kr.or.dgit.jdbc_pool_c3p0.service.TitleService;
import kr.or.dgit.jdbc_pool_c3p0.ui.table.AbstractTablePanel;
import kr.or.dgit.jdbc_pool_c3p0.ui.table.DepartmentTablePanel;
import kr.or.dgit.jdbc_pool_c3p0.ui.table.EmployeeTablePanel;
import kr.or.dgit.jdbc_pool_c3p0.ui.table.TitleTablePanel;

@SuppressWarnings("serial")
public class ErpMain extends JFrame {

	private JPanel contentPane;
	private TitleService titleService;
	private DepartmentService departmentService;
	private EmployeeService employeeService;
	private AbstractTablePanel<Employee> employeeTable;
	
	public ErpMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		setContentPane(contentPane);
		
        AbstractTablePanel<Title> titleTable = new TitleTablePanel();
        titleService = new TitleService();
        titleTable.setItems(titleService.findTitleByAll());
        titleTable.loadData();
        contentPane.add(titleTable);
        
        AbstractTablePanel<Department> departmentTable = new DepartmentTablePanel();
        departmentService = new DepartmentService();
        departmentTable.setItems(departmentService.findDepartmentByAll());
        departmentTable.loadData();
        contentPane.add(departmentTable);
        
        employeeTable = new EmployeeTablePanel();
        employeeService = new EmployeeService();
        employeeTable.setItems(employeeService.findEmployeeByAll());
        employeeTable.loadData();
        employeeTable.setPopupMenu(getEmpPopUpMenu());
        contentPane.add(employeeTable);

	}

	private JPopupMenu getEmpPopUpMenu() {
		JMenuItem delItem = new JMenuItem("삭제");
		delItem.addActionListener(e->{
			try {
				employeeService.unRegisterEmployee(employeeTable.getItem().getEmpNo());
				employeeTable.removeRow();
			} catch (RuntimeException e1) {
				JOptionPane.showMessageDialog(null, "해당 제품이 판매현황에 존재합니다.");
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		});
		
		JMenuItem updateItem = new JMenuItem("수정");
		updateItem.addActionListener(e->{
			Employee employee;
			try {
				employee = employeeService.findEmployeeByCode( employeeTable.getItem().getEmpNo());
				JOptionPane.showMessageDialog(null, employee);	
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		});
		
		JPopupMenu popUpMenu = new JPopupMenu();
		popUpMenu.add(delItem);
		popUpMenu.add(updateItem);
		return popUpMenu;
	}

}
