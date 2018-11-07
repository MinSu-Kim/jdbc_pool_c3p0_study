package kr.or.dgit.jdbc_pool_c3p0.ui.table;

import javax.swing.SwingConstants;

import kr.or.dgit.jdbc_pool_c3p0.domain.Title;

@SuppressWarnings("serial")
public class TitleTablePanel extends AbstractTablePanel<Title>{

	public TitleTablePanel() {
		super("직책");
	}

	@Override
	protected void setAlignWith() {
        tableCellAlignment(SwingConstants.CENTER, 0, 1);
        tableSetWidth(100, 200);            		
	}

	@Override
	public void setColumnNames() {
		colNames = new String[] { "직책 코드", "직책 명"};        		
	}

	public Object[] toArray(Title title) {
		return new Object[] {title.getCode(), title.getName()};
	}

	@Override
	public Title getItem() throws Exception {
		int row = getSelectedRowIndex();
		return new Title(String.valueOf(table.getValueAt(row, 0)));
	}

}
