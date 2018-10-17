package kr.or.dgit.jdbc_pool_c3p0.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import kr.or.dgit.jdbc_pool_c3p0.domain.Title;
import kr.or.dgit.jdbc_pool_c3p0.service.TitleService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TitleServiceTest {
	static final Logger log = LogManager.getLogger();

	static TitleService service;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		service = new TitleService();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		service = null;
	}

	@Test
	public void test0TitleByAll() throws SQLException {
		List<Title> list = service.findTitleByAll();
		Assert.assertNotNull(list);
		for (Title t : list) {
			log.trace(t);
		}

	}

	@Test
	public void test1TitleByCode() throws SQLException {
		Title searchTitle = new Title();
		searchTitle.setCode("T001");
		Title title = service.findTitleByCode(searchTitle);
		log.trace(title);
		Assert.assertNotNull(title);
	}

	@Test
	public void test2RegisterTitle() throws SQLException {
		Title insertTitle = new Title("T005", "사원");
		int res = service.registTitle(insertTitle);
		log.trace("result : " + res);
		Assert.assertEquals(1, res);
	}

	@Test
	public void test4UnRegisterTitle() throws SQLException {
		int res = service.unRegisterTitle("T005");
		log.trace("result : " + res);
		Assert.assertEquals(1, res);
	}

	@Test
	public void test3UpdateTitle() throws SQLException {
		Title title = new Title("T005", "평사원");
		int res = service.updateTitle(title);
		log.trace("result : " + res);
		Assert.assertEquals(1, res);
	}
}
