package com.gyshop.boardreply.service;

import com.gyshop.boardreply.dao.BoardReplyDAO;
import com.gyshop.boardreply.vo.BoardReplyVO;
import com.gyshop.main.dao.DAO;
import com.gyshop.main.service.Service;

public class BoardReplyWriteService implements Service {

	private BoardReplyDAO dao;
	
	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		Integer result = null;
		BoardReplyVO vo = (BoardReplyVO)obj;
		result = dao.write(vo);
		if (result != 0) {
			// 댓글수 가져오기
			Long count = dao.getCommentCount(vo.getNo());
			// 댓글수 업데이트
			Long[] args = new Long[] {count, vo.getNo()};
			result = dao.updateComment(args);
		}
		
		return result;
	}

	@Override
	public void setDAO(DAO dao) {
		// TODO Auto-generated method stub
		this.dao = (BoardReplyDAO)dao;
	}

}
