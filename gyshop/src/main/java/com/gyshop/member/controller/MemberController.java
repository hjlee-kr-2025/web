package com.gyshop.member.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.gyshop.main.controller.Init;
import com.gyshop.member.vo.MemberVO;
import com.gyshop.util.exe.Execute;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class MemberController {

	public String execute(HttpServletRequest request) {
		// 이동할 페이지를 저장하는 변수 선언
		String jsp = null;
		System.out.println("MemberController.execute() -----");
		
		// 세션정보 가져오기
		HttpSession session = request.getSession();
		
		// 메뉴를 위한 uri
		String uri = request.getRequestURI();
		
		// 결과 저장용 변수
		Object result = null;
		
		/* 파일 업로드를 위한 설정
		 * -- 사진이 저장되는 폴더를 설정하고 크기를 제한합니다.
		 */
		String savePath = "/upload";
		// 파일의 절대위치를 지정
		String realSavePath
			= request.getServletContext().getRealPath(savePath);
		// 업로드 파일의 용량을 제한
		int sizeLimit = 20 * 1024 * 1024; // 20MByte

		// 실제경로의 폴더를 File클래스에 연결
		File realSavePathFile = new File(realSavePath);
		// 폴더가 실제로 존재하는지 확인
		if (!realSavePathFile.exists()) {
			// 없으면 만들어줍니다. - 한번에 두단계의 폴더는 만들어 주지 못합니다.
			System.out.println("폴더를 만들어 줍니다.");
			realSavePathFile.mkdir();
		}
		// 두번째 폴더
		savePath = "/upload/member";
		realSavePath
			= request.getServletContext().getRealPath(savePath);
		realSavePathFile = new File(realSavePath);
		if (!realSavePathFile.exists()) {
			// 없으면 만들어줍니다. - 한번에 두단계의 폴더는 만들어 주지 못합니다.
			realSavePathFile.mkdir();
		}
		System.out.println("realSavePath: " + realSavePath);
		// 여기까지 사진저장폴더 설정에 관련된 코드입니다.
		
		try {
			switch (uri) {
			case "/member/writeForm.do":
				System.out.println("회원가입 폼 ---");
				jsp = "member/writeForm";
				// "/WEB-INF/views/member/writeForm.jsp"
				break;
			case "/member/write.do":
				System.out.println("회원가입 처리---");
				
				MultipartRequest multi =
					new MultipartRequest(request, realSavePath,
							sizeLimit, "utf-8", 
							new DefaultFileRenamePolicy());
				/* 중복된 화일 처리방식
				 * new DefaultFileRenamePolicy()는
				 * 서버에 같은이름의 화일이 존재하면 이름에 숫자를 추가하여
				 * 중복을 방지합니다.
				 * */
				
				// 서비스로 전송하는 데이터 수집
				String id = multi.getParameter("id");
				String pw = multi.getParameter("pw");
				String name = multi.getParameter("name");
				String gender = multi.getParameter("gender");
				String birth = multi.getParameter("birth");
				String tel = multi.getParameter("tel");
				String email = multi.getParameter("email");
				String zipcode = multi.getParameter("zipcode");
				String addr1 = multi.getParameter("addr1");
				String addr2 = multi.getParameter("addr2");
				// 파일의 이름을 가져오려면 getFilesystemName()
				String photo = multi.getFilesystemName("photo");
				
				MemberVO vo = new MemberVO();
				vo.setId(id);
				vo.setPw(pw);
				vo.setName(name);
				vo.setGender(gender);
				vo.setBirth(birth);
				vo.setTel(tel);
				vo.setEmail(email);
				vo.setZipcode(zipcode);
				vo.setAddr1(addr1);
				vo.setAddr2(addr2);
				// 사진은 파일이름에 경로를 붙여서 DB에 저장
				System.out.println("photo = " + photo);
				if (photo!=null && !photo.equals("")) {
					vo.setPhoto(savePath + "/" + photo);
				}
				
				// 서비스 실행
				result = Execute.execute(Init.get(uri), vo);
				
				// 실행 결과
				if ((Integer)result != 0) {
					session.setAttribute("msg",
						"회원가입이 완료되었습니다. 환영합니다.");
				}
				// 일단 일반게시판의 리스트로 보냅니다.
				// 메인페이지가 완성되면 메인페이지의 경로로 변경합니다.
				jsp = "redirect:/board/list.do";
				break;
			default:
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		// 이동할 페이지 리턴
		return jsp;
	} // end of execute()
} 
