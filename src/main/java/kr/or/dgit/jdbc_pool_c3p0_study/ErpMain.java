package kr.or.dgit.jdbc_pool_c3p0_study;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import kr.or.dgit.jdbc_pool_c3p0_study.domain.Department;
import kr.or.dgit.jdbc_pool_c3p0_study.domain.Employee;
import kr.or.dgit.jdbc_pool_c3p0_study.domain.Title;
import kr.or.dgit.jdbc_pool_c3p0_study.service.DepartmentService;
import kr.or.dgit.jdbc_pool_c3p0_study.service.EmployeeService;
import kr.or.dgit.jdbc_pool_c3p0_study.service.TitleService;
import kr.or.dgit.jdbc_pool_c3p0_study.ui.table.AbstractTablePanel;
import kr.or.dgit.jdbc_pool_c3p0_study.ui.table.DepartmentTablePanel;
import kr.or.dgit.jdbc_pool_c3p0_study.ui.table.EmployeeTablePanel;
import kr.or.dgit.jdbc_pool_c3p0_study.ui.table.TitleTablePanel;

@SuppressWarnings("serial")
public class ErpMain extends JFrame {

	private JPanel contentPane;
	private TitleService titleService;
	private DepartmentService departmentService;
	private EmployeeService employeeService;
	
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
        
        
        AbstractTablePanel<Employee> employeeTable = new EmployeeTablePanel();
        employeeService = new EmployeeService();
        employeeTable.setItems(employeeService.findEmployeeByAll());
        employeeTable.loadData();
        contentPane.add(employeeTable);
	}

}
