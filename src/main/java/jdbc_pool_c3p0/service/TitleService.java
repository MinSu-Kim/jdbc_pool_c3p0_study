package jdbc_pool_c3p0.service;

import java.util.List;

import jdbc_pool_c3p0.dao.TitleDao;
import jdbc_pool_c3p0.dao.TitleDaoImpl;
import jdbc_pool_c3p0.dto.Title;

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
