package kr.or.dgit.jdbc_pool_c3p0_study.ui.table;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

@SuppressWarnings("serial")
public abstract class AbstractTablePanel<T> extends JPanel {
	protected JTable table;
	protected NonEditableModel model;
	protected int selectRowIndex;
	protected Object[] colNames;
	protected List<T> items;

	public AbstractTablePanel(String title) {
		initComponents(title);
		setColumnNames();
	}

	private void initComponents(String title) {
		setBorder(new TitledBorder(null, title, TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		scrollPane.setViewportView(table);
	}

	public void setPopupMenu(JPopupMenu popUpMenu) {
		table.setComponentPopupMenu(popUpMenu);
	}

	public void setItems(List<T> items) {
		this.items = items;
	}
	
	protected void tableCellAlignment(int align, int... idx) {
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(align);

		TableColumnModel model = table.getColumnModel();
		for (int i = 0; i < idx.length; i++) {
			model.getColumn(idx[i]).setCellRenderer(dtcr);
		}
	}

	protected void tableSetWidth(int... width) {
		TableColumnModel cModel = table.getColumnModel();
		for (int i = 0; i < width.length; i++) {
			cModel.getColumn(i).setPreferredWidth(width[i]);
		}
	}

	public Object getSelectedNo() {
		return table.getValueAt(table.getSelectedRow(), 0);
	}

	public JTable getTable() {
		return table;
	}

	protected void updateRow(T item) {
		items.set(items.indexOf(item), item);
		loadData();
	}

	protected void addRow(T item) {
		items.add(item);
		loadData();
	}
	
	public void removeRow() {
		items.remove(getItem());
		loadData();
	}
	
	protected abstract T getItem();
	
	public void loadData() {
		model = new NonEditableModel(toArray(), colNames);
		table.setModel(model);
		setAlignWith();
	}
	
	protected Object[][] toArray() {
		Object[][] results = new Object[items.size()][];
		for (int i = 0; i < items.size(); i++) {
			results[i] = toArray(items.get(i));
		}
		return results;
	}

	protected class NonEditableModel extends DefaultTableModel {

		public NonEditableModel(Object[][] data, Object[] columnNames) {
			super(data, columnNames);
		}

		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	}

	protected abstract Object[] toArray(T item);
	
	protected abstract void setAlignWith();
	
	protected abstract void setColumnNames();

}
