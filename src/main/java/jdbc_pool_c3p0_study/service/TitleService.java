package jdbc_pool_c3p0_study.service;

import java.util.List;

import jdbc_pool_c3p0_study.dao.TitleDao;
import jdbc_pool_c3p0_study.dao.TitleDaoImpl;
import jdbc_pool_c3p0_study.dto.Title;

public class TitleService {
	private TitleDao dao;

	public TitleService() {
		dao = new TitleDaoImpl();
	}

	public List<Title> findTitleByAll() {
		return dao.selectTitleByAll();
	}

	public Title findTitleByCode(Title title) {
		return dao.selectTitleByCode(title);
	}

	public int registTitle(Title title) {
		return dao.insertTitle(title);
	}

	public int unRegisterTitle(String code) {
		return dao.deleteTitle(code);
	}

	public int updateTitle(Title title) {
		return dao.updateTitle(title);
	}
}
