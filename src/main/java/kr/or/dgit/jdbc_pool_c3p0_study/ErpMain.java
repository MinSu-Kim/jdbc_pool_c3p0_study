package kr.or.dgit.jdbc_pool_c3p0_study;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
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
        employeeTable.setPopupMenu(createEmployeePopUpMenu());
        contentPane.add(employeeTable);
	}
	
	private JPopupMenu createEmployeePopUpMenu() {
		JPopupMenu popMenu = new JPopupMenu();
		JMenuItem delItem = new JMenuItem("삭제");
		delItem.addActionListener(popUpEmployeeMenuListener);
		popMenu.add(delItem);

		JMenuItem updateItem = new JMenuItem("수정");
		updateItem.addActionListener(popUpEmployeeMenuListener);
		popMenu.add(updateItem);
		return popMenu;
	}

	ActionListener popUpEmployeeMenuListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("삭제")) {
				try {
					employeeService.unRegisterEmployee((String) employeeTable.getSelectedNo());
					employeeTable.removeRow();
				} catch (RuntimeException e1) {
					JOptionPane.showMessageDialog(null, "해당 제품이 판매현황에 존재합니다.");
				}
			}
			if (e.getActionCommand().equals("수정")) {
				Employee employee = employeeService.findEmployeeByCode((String) employeeTable.getSelectedNo());
				JOptionPane.showMessageDialog(null, employee);
			}
		}
	};

}
