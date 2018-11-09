package kr.or.dgit.jdbc_pool_c3p0.ui.table;

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
	protected int selectRowIndex;
	protected Object[] colNames;
	protected List<T> items;
	private JScrollPane scrollPane;

	public AbstractTablePanel(String title) {
		initComponents(title);
		setColumnNames();
	}

	private void initComponents(String title) {
		setBorder(new TitledBorder(null, title, TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new BorderLayout(0, 0));

		scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		scrollPane.setViewportView(table);
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

	public void updateRow(T item) {
		items.set(items.indexOf(item), item);
		loadData();
	}

	public void addRow(T item) {
		items.add(item);
		loadData();
	}
	
	public void removeRow() throws Exception {
		items.remove(getItem());
		loadData();
	}
	
	public void loadData() {
		Object[][] rows = new Object[items.size()][];
		for (int i = 0; i < items.size(); i++) {
			rows[i] = toArray(items.get(i));
		}
		
		table.setModel(new NonEditableModel(rows, colNames));
		setAlignWith();
	}
	
	public void setPopupMenu(JPopupMenu popUpMenu) {
		scrollPane.setComponentPopupMenu(popUpMenu);
		table.setComponentPopupMenu(popUpMenu);
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

	public int getSelectedRowIndex() throws Exception {
		int row = table.getSelectedRow();
		if (row==-1) throw new Exception("해당 항목을 선택하세요");
		return row;
	}
	
	public abstract T getItem() throws Exception;
	
	protected abstract Object[] toArray(T item);
	
	protected abstract void setAlignWith();
	
	protected abstract void setColumnNames();

}
