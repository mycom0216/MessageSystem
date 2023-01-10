package com.smhrd.model;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.smhrd.db.SqlSessionManager;

public class MessageDAO {
	// 필드 ---< 공장 꺼내오기
	private SqlSessionFactory sqlSessionFactory = SqlSessionManager.getSqlSession();
	
	
	
	
	public int join(MessageDTO dto) {
		// 1. sqlSession열어주기
		SqlSession session = sqlSessionFactory.openSession(true);
		// 2. session사용해서 insert 진행
		int row = session.insert("com.smhrd.model.MessageDAO.join", dto);
		// 3. 자원반납
		session.close();
		// 4. 결과값 반환
		
		
		return row;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
