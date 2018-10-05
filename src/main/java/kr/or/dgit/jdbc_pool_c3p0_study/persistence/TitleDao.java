package kr.or.dgit.jdbc_pool_c3p0_study.persistence;

import java.util.List;

import kr.or.dgit.jdbc_pool_c3p0_study.domain.Title;

public interface TitleDao {
	List<Title> selectTitleByAll();
	Title selectTitleByCode(Title title);
	int insertTitle(Title title);
	int deleteTitle(String code);
	int updateTitle(Title title);
}
