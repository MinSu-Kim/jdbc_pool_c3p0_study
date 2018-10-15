package kr.or.dgit.jdbc_pool_c3p0_study;

import java.awt.EventQueue;

import kr.or.dgit.jdbc_pool_c3p0_study.jdbc.MyDataSource;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ErpMain frame = new ErpMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					MyDataSource.getInstance().close();
				}
			}
		});
	}

}
