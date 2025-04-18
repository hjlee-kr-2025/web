package com.gyshop.main.controller;

import java.util.HashMap;
import java.util.Map;

import com.gyshop.board.dao.BoardDAO;
import com.gyshop.board.service.BoardDeleteService;
import com.gyshop.board.service.BoardListService;
import com.gyshop.board.service.BoardUpdateService;
import com.gyshop.board.service.BoardViewService;
import com.gyshop.board.service.BoardWriteService;
import com.gyshop.boardreply.dao.BoardReplyDAO;
import com.gyshop.boardreply.service.BoardReplyDeleteService;
import com.gyshop.boardreply.service.BoardReplyListService;
import com.gyshop.boardreply.service.BoardReplyUpdateService;
import com.gyshop.boardreply.service.BoardReplyWriteService;
import com.gyshop.goods.dao.GoodsDAO;
import com.gyshop.goods.service.GoodsDeleteService;
import com.gyshop.goods.service.GoodsListService;
import com.gyshop.goods.service.GoodsUpdateService;
import com.gyshop.goods.service.GoodsViewService;
import com.gyshop.goods.service.GoodsWriteService;
import com.gyshop.image.dao.ImageDAO;
import com.gyshop.image.service.ImageDeleteService;
import com.gyshop.image.service.ImageListService;
import com.gyshop.image.service.ImageUpdateService;
import com.gyshop.image.service.ImageViewService;
import com.gyshop.image.service.ImageWriteService;
import com.gyshop.main.dao.DAO;
import com.gyshop.main.service.Service;
import com.gyshop.member.dao.GradeDAO;
import com.gyshop.member.dao.MemberDAO;
import com.gyshop.member.service.GradeDeleteService;
import com.gyshop.member.service.GradeListService;
import com.gyshop.member.service.GradeUpdateService;
import com.gyshop.member.service.GradeWriteService;
import com.gyshop.member.service.MemberChangePhotoService;
import com.gyshop.member.service.MemberCheckIdService;
import com.gyshop.member.service.MemberDeleteService;
import com.gyshop.member.service.MemberListService;
import com.gyshop.member.service.MemberLoginService;
import com.gyshop.member.service.MemberUpdateService;
import com.gyshop.member.service.MemberViewService;
import com.gyshop.member.service.MemberWriteService;
import com.gyshop.notice.dao.NoticeDAO;
import com.gyshop.notice.service.NoticeDeleteService;
import com.gyshop.notice.service.NoticeListService;
import com.gyshop.notice.service.NoticeUpdateService;
import com.gyshop.notice.service.NoticeViewService;
import com.gyshop.notice.service.NoticeWriteService;

public class Init {

	// Service 를 생성해서 저장하는 객체 <URI, Service>
	private static Map<String, Service> serviceMap = new HashMap<>();
	// dao 를 생성해서 저장하는 객체 <className, dao>
	private static Map<String, DAO> daoMap = new HashMap<>();
	
	// 자바에서 클래스를 사용하려면? 주소를 할당해야 합니다.
	// ==> 생성자를 사용한 이유
	// 웹프로그램에서는 한번만 생성(할당)해서 공유합니다.
	// 한번 new 한것을 key값으로 저장해서 사용하는 방식으로 구현했습니다.
	
	// 생성하는 내용은 static 메서드에 구현 (Init이 로드될때 처리)
	static {
		// 1. dao 생성
		// BoardDao 생성 (일반게시판) -> daoMap에 저장
		daoMap.put("boardDAO", new BoardDAO());
		
		// 2. service 생성
		serviceMap.put("/board/list.do", new BoardListService());
		serviceMap.put("/board/view.do", new BoardViewService());
		serviceMap.put("/board/write.do", new BoardWriteService());
		serviceMap.put("/board/update.do", new BoardUpdateService());
		serviceMap.put("/board/delete.do", new BoardDeleteService());
		
		// 3. 조립 : service에 dao를 할당
		serviceMap.get("/board/list.do").setDAO(daoMap.get("boardDAO"));
		serviceMap.get("/board/view.do").setDAO(daoMap.get("boardDAO"));
		serviceMap.get("/board/write.do").setDAO(daoMap.get("boardDAO"));
		serviceMap.get("/board/update.do").setDAO(daoMap.get("boardDAO"));
		serviceMap.get("/board/delete.do").setDAO(daoMap.get("boardDAO"));
		
		// 회원등급
		// 1. DAO 생성
		daoMap.put("gradeDAO", new GradeDAO());
		
		// 2. service 생성
		serviceMap.put("/grade/list.do", new GradeListService());
		serviceMap.put("/grade/write.do", new GradeWriteService());
		serviceMap.put("/grade/update.do", new GradeUpdateService());
		serviceMap.put("/grade/delete.do", new GradeDeleteService());
		
		// 3. 조립 : service에 dao 할당
		serviceMap.get("/grade/list.do").setDAO(daoMap.get("gradeDAO"));
		serviceMap.get("/grade/write.do").setDAO(daoMap.get("gradeDAO"));
		serviceMap.get("/grade/update.do").setDAO(daoMap.get("gradeDAO"));
		serviceMap.get("/grade/delete.do").setDAO(daoMap.get("gradeDAO"));
		
		// 회원관리
		// 1. dao 생성
		daoMap.put("memberDAO", new MemberDAO());
		
		// 2. service 생성
		serviceMap.put("/member/list.do", new MemberListService());
		serviceMap.put("/member/view.do", new MemberViewService());
		serviceMap.put("/member/write.do", new MemberWriteService());
		serviceMap.put("/member/update.do", new MemberUpdateService());
		serviceMap.put("/member/delete.do", new MemberDeleteService());
		serviceMap.put("/ajax/checkId.do", new MemberCheckIdService());
		serviceMap.put("/member/login.do", new MemberLoginService());
		serviceMap.put("/member/changePhoto.do",
				new MemberChangePhotoService());
		
		// 3. 조립 : service 에 dao 할당
		serviceMap.get("/member/list.do").setDAO(daoMap.get("memberDAO"));
		serviceMap.get("/member/view.do").setDAO(daoMap.get("memberDAO"));
		serviceMap.get("/member/write.do").setDAO(daoMap.get("memberDAO"));
		serviceMap.get("/member/update.do").setDAO(daoMap.get("memberDAO"));
		serviceMap.get("/member/delete.do").setDAO(daoMap.get("memberDAO"));
		serviceMap.get("/ajax/checkId.do").setDAO(daoMap.get("memberDAO"));
		serviceMap.get("/member/login.do").setDAO(daoMap.get("memberDAO"));
		serviceMap.get("/member/changePhoto.do").setDAO(daoMap.get("memberDAO"));
		
		// 공지사항
		// 1. dao 생성
		daoMap.put("noticeDAO", new NoticeDAO());
		
		// 2. 서비스 생성
		serviceMap.put("/notice/list.do", new NoticeListService());
		serviceMap.put("/notice/view.do", new NoticeViewService());
		serviceMap.put("/notice/write.do", new NoticeWriteService());
		serviceMap.put("/notice/update.do", new NoticeUpdateService());
		serviceMap.put("/notice/delete.do", new NoticeDeleteService());
		
		// 3. 조립: 서비스에 dao 할당
		serviceMap.get("/notice/list.do").setDAO(daoMap.get("noticeDAO"));
		serviceMap.get("/notice/view.do").setDAO(daoMap.get("noticeDAO"));
		serviceMap.get("/notice/write.do").setDAO(daoMap.get("noticeDAO"));
		serviceMap.get("/notice/update.do").setDAO(daoMap.get("noticeDAO"));
		serviceMap.get("/notice/delete.do").setDAO(daoMap.get("noticeDAO"));
		
		// 일반게시판댓글
		// 1. dao 생성
		daoMap.put("boardReplyDAO", new BoardReplyDAO());
		// 2. service 생성
		serviceMap.put("/boardreply/list.do", new BoardReplyListService());
		serviceMap.put("/boardreply/write.do", new BoardReplyWriteService());
		serviceMap.put("/boardreply/update.do", new BoardReplyUpdateService());
		serviceMap.put("/boardreply/delete.do", new BoardReplyDeleteService());
		// 3. 조립 : service 에 dao 할당
		serviceMap.get("/boardreply/list.do").setDAO(daoMap.get("boardReplyDAO"));
		serviceMap.get("/boardreply/write.do").setDAO(daoMap.get("boardReplyDAO"));
		serviceMap.get("/boardreply/update.do").setDAO(daoMap.get("boardReplyDAO"));
		serviceMap.get("/boardreply/delete.do").setDAO(daoMap.get("boardReplyDAO"));
		
		// 이미지게시판
		// 1. dao 생성
		daoMap.put("imageDAO", new ImageDAO());
		// 2. service 생성
		serviceMap.put("/image/list.do", new ImageListService());
		serviceMap.put("/image/view.do", new ImageViewService());
		serviceMap.put("/image/write.do", new ImageWriteService());
		serviceMap.put("/image/update.do", new ImageUpdateService());
		serviceMap.put("/image/delete.do", new ImageDeleteService());
		// 3. 조립 : service 에 dao를 할당
		serviceMap.get("/image/list.do").setDAO(daoMap.get("imageDAO"));
		serviceMap.get("/image/view.do").setDAO(daoMap.get("imageDAO"));
		serviceMap.get("/image/write.do").setDAO(daoMap.get("imageDAO"));
		serviceMap.get("/image/update.do").setDAO(daoMap.get("imageDAO"));
		serviceMap.get("/image/delete.do").setDAO(daoMap.get("imageDAO"));
		
		// 상품테이블
		// 1. dao생성
		daoMap.put("goodsDAO", new GoodsDAO());
		// 2. service생성
		serviceMap.put("/goods/list.do", new GoodsListService());
		serviceMap.put("/goods/view.do", new GoodsViewService());
		serviceMap.put("/goods/write.do", new GoodsWriteService());
		serviceMap.put("/goods/update.do", new GoodsUpdateService());
		serviceMap.put("/goods/delete.do", new GoodsDeleteService());
		// 3. 조립 : service에 dao할당
		serviceMap.get("/goods/list.do").setDAO(daoMap.get("goodsDAO"));
		serviceMap.get("/goods/view.do").setDAO(daoMap.get("goodsDAO"));
		serviceMap.get("/goods/write.do").setDAO(daoMap.get("goodsDAO"));
		serviceMap.get("/goods/update.do").setDAO(daoMap.get("goodsDAO"));
		serviceMap.get("/goods/delete.do").setDAO(daoMap.get("goodsDAO"));
		
	}
	
	// 어느 서비스를 실행할지 리턴하는 메서드(할당된 서비스 주소 리턴)
	public static Service get(String uri) {
		// uri 키값으로 service 주소를 가져옵니다.
		return serviceMap.get(uri);
	}
	
}
