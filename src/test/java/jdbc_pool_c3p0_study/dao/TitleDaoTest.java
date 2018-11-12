package jdbc_pool_c3p0_study.dao;

import java.sql.SQLException;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import jdbc_pool_c3p0_study.dao.TitleDao;
import jdbc_pool_c3p0_study.dao.TitleDaoImpl;
import jdbc_pool_c3p0_study.dto.Title;
import jdbc_pool_c3p0_study.jdbc.LogUtil;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TitleDaoTest {
	static TitleDao dao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println();
		LogUtil.prnLog("Start TitleDaoTest");
		dao = new TitleDaoImpl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println();
		LogUtil.prnLog("End TitleDaoTest");
		dao = null;
	}

	@Before
	public void setUp() throws Exception {
		System.out.println();
	}
	
	@Test
	public void test01SelectTitleByAll() throws SQLException {
		List<Title> list = dao.selectTitleByAll();
		Assert.assertNotNull(list);
		for (Title e : list) {
			LogUtil.prnLog(e.toString());
		}

	}

	@Test
	public void test05SelectTitleByCode() throws SQLException {
		Title searchTitle = new Title();
		searchTitle.setCode("T001");
		Title title = dao.selectTitleByCode(searchTitle);
		LogUtil.prnLog(title.toString());
		Assert.assertNotNull(title);
	}

	@Test
	public void test02InsertEmployee() throws SQLException {
		Title newTitle = new Title("T005", "사원");
		int res = dao.insertTitle(newTitle);
		LogUtil.prnLog("result : " + res);
		Assert.assertEquals(1, res);
	}

	@Test
	public void test04DeleteTitle() throws SQLException {
		int res = dao.deleteTitle("T005");
		LogUtil.prnLog("result : " + res);
		Assert.assertEquals(1, res);
	}

	@Test
	public void test03UpdateTitle() throws SQLException {
		Title title = new Title("T005", "평사원");
		int res = dao.updateTitle(title);
		LogUtil.prnLog("result : " + res);
		Assert.assertEquals(1, res);
	}
}
