package com.gyshop.member.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.gyshop.main.controller.Init;
import com.gyshop.member.vo.LoginVO;
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
		LoginVO login = (LoginVO)session.getAttribute("login");
		String id = null;
		if (login != null) id = login.getId();
		
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
			case "/member/loginForm.do":
				System.out.println("로그인 폼 ---");
				jsp = "member/loginForm";
				// "/WEB-INF/views/member/loginForm.jsp"
				break;
			case "/member/login.do":
				System.out.println("로그인 처리 ---");
				// 로그인 데이터 수집 (서비스로 넘어갑니다)
				login = new LoginVO();
				login.setId(request.getParameter("id"));
				login.setPw(request.getParameter("pw"));
				// getParameter 안에 문자열로 적힌 값은
				// form태그안 input태그의 name 속성값과 같아야 합니다.
				
				// 서비스를 실행
				result = Execute.execute(Init.get(uri), login);
				
				// 결과 확인
				if (result == null) {
					session.setAttribute("msg",
						"로그인이 실패했습니다. 아이디와 패스워드를 확인해주세요.");
				}
				else {
					session.setAttribute("msg",
						"로그인이 되었습니다.");
					session.setAttribute("login", result);
					// 로그인이 되면 LoginVO 자료형의 데이터가
					// session에 login 키값으로 저장되도록 구성했습니다.
					// id, pw, name, grade, gradeName, photo
				}
				
				// 로그인 후에 넘어갈 페이지 지정
				jsp = "redirect:/board/list.do";
				break;
			case "/member/logout.do":
				System.out.println("로그아웃 처리 ---");
				// 우리가 로그인을 하면 session에 로그인정보를 담았습니다.
				// 로그아웃에서는 session 저장된 로그인정보를 제거하면 됩니다.
				session.removeAttribute("login");
				
				session.setAttribute("msg",
					"로그아웃 되었습니다.");
				// 페이지 이동
				jsp = "redirect:/board/list.do";
				break;
			case "/member/list.do":
				System.out.println("회원 리스트 ---");
				// 서비스로 넘어가는 데이터 세팅
				// 현재는 없습니다.
				
				// 서비스를 실행
				result = Execute.execute(Init.get(uri), null);
				// 결과값을 request에 담습니다.
				request.setAttribute("list", result);
				// 이동할 jsp 페이지 설정
				jsp = "member/list";
				// "WEB-INF/views/member/list.jsp"
				break;
			case "/member/view.do":
				System.out.println("회원정보 또는 내정보보기 ---");
				
				// 주소창에 view.do?id=xxx 통해서 id값이  request에
				// 담겨져서 이곳으로 넘어옵니다.
				id = request.getParameter("id");
				if (id == null || id.equals("")) {
					id = login.getId();// 로그인 id
				}
				
				// 서비스실행 (id가 넘어갑니다)
				result = Execute.execute(Init.get(uri), id);
				
				// DB에서 가져온 데이터를 request에 담습니다.
				request.setAttribute("vo", result);
				// jsp 로 이동합니다.
				jsp = "member/view";
				// "/WEB-INF/views/member/view.jsp"
				break;
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
				id = multi.getParameter("id");
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
			case "/member/updateForm.do":
				System.out.println("내정보 수정 폼---");
				// 기존정보를 가져와야 합니다. - MemberViewService()
				// 필요한정보 : id
				id = request.getParameter("id");
				
				// 서비스 실행
				result = Execute.execute(Init.get("/member/view.do"), id);
				
				// 받아온 정보 request 담아서 updateForm.jsp 파일로 넘깁니다.
				request.setAttribute("vo", result);
				jsp = "member/updateForm";
				// "/WEB-INF/views/member/updateForm.jsp"
				break;
			case "/member/update.do":
				System.out.println("내정보 수정 처리 ---");
				// updateForm에서 넘어온 데이터를 수집합니다.
				// update(수정)시 id와 pw로 수정대상을 확인합니다.
				// request에 담겨저 이곳에 넘어왔습니다.
				id = request.getParameter("id");
				pw = request.getParameter("pw");
				name = request.getParameter("name");
				gender = request.getParameter("gender");
				birth = request.getParameter("birth");
				tel = request.getParameter("tel");
				email = request.getParameter("email");
				zipcode = request.getParameter("zipcode");
				addr1 = request.getParameter("addr1");
				addr2 = request.getParameter("addr2");
				
				vo = new MemberVO();
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
				// 서비스 실행
				result = Execute.execute(Init.get(uri), vo);
				// 결과를 메시지로 보여줍니다.
				if ((Integer)result == 0) {
					session.setAttribute("msg",
						"수정되지 않았습니다. 다시 확인하시고 진행해주세요");
				}
				else {
					session.setAttribute("msg",
						"내정보가 수정되었습니다.");
				}
				// 내정보보기로 이동합니다.
				jsp = "redirect:view.do?id=" + vo.getId();
				break;
			case "/member/changePhoto.do":
				System.out.println("회원정보 사진(이미지) 교체 ----");
				/* 이미지 업로드 처리
				 * new MultipartRequest
				 * (request, 실제저장위치, 사이즈제한, encoding,
				 * 중복처리객체 - 다른이름으로)
				 */
				// request 를 MultipartRequest 에 담으면
				// request 정보가 multi로 넘어가고 request에는 삭제됩니다.
				multi =
					new MultipartRequest(request, realSavePath,
						sizeLimit, "utf-8",
						new DefaultFileRenamePolicy());
				// 데이터 수집
				id = multi.getParameter("id");
				pw = multi.getParameter("pw");
				photo = multi.getFilesystemName("imageFile");
				String deleteFileName = multi.getParameter("deleteFileName");
				
				vo = new MemberVO();
				vo.setId(id);
				vo.setPw(pw);
				vo.setPhoto(savePath + "/" + photo);
				
				// 서비스를 실행합니다. 서비스에 vo객체를 전달합니다.
				result = Execute.execute(Init.get(uri), vo);
				
				if ((Integer)result == 0) {
					// 사진 바꾸기 실패
					photo = vo.getPhoto();
					// upload한 파일을 지운다.
					File deleteFile = new File(request.getServletContext()
							.getRealPath(photo));
					if (deleteFile.exists()) deleteFile.delete();
					session.setAttribute("msg",
						"사진 바꾸기가 실패했습니다. 다시 시도해 주세요.");
				}
				else {
					// 사진 바꾸기 성공
					// 기존파일을 지웁니다.
					File deleteFile = new File(request.getServletContext()
							.getRealPath(deleteFileName));
					if (deleteFile.exists()) deleteFile.delete();
					session.setAttribute("msg",
						"사진 바꾸기가 성공했습니다.");
				}
				// 정보보기로 이동
				jsp = "redirect:view.do?id=" + vo.getId();	
				break;
			case "/member/delete.do":
				System.out.println("회원 탈퇴 처리 ----");
				System.out.println("회원상태를 탈퇴로 변경");
				// 정보수집
				id = request.getParameter("id");
				pw = request.getParameter("pw");
				vo = new MemberVO();
				vo.setId(id);
				vo.setPw(pw);
				// 서비스를 실행합니다.
				result = Execute.execute(Init.get(uri), vo);
				// 결과 확인 메시지
				if ((Integer)result == 0) {
					session.setAttribute("msg",
						"탈퇴 처리가 실패하였습니다. 확인 후 시도해주세요.");
				}
				else {
					session.setAttribute("msg",
						"탈퇴 처리가 되었습니다.");
					// 로그아웃
					session.removeAttribute("login");
				}
				jsp = "redirect:/board/list.do";
				break;
			case "/member/searchForm.do":
				System.out.println("아이디 비밀번호찾기 폼 -----");
				jsp="member/searchForm";
				// "/WEB-INF/views/member/searchForm.jsp"
				break;
			case "/member/searchId.do":
				System.out.println("아이디 찾기 -----");
				// 자료를 수집 (name, email, birth)
				name = request.getParameter("name");
				email = request.getParameter("email");
				birth = request.getParameter("birth");
				// 서비스로 넘어갈 데이터 세팅
				vo = new MemberVO();
				vo.setName(name);
				vo.setEmail(email);
				vo.setBirth(birth);
				// 서비스를 실행
				result = Execute.execute(Init.get(uri), vo);
				// 결과확인
				if (result == null) {
					session.setAttribute("msg",
						"해당정보로 id를 찾을 수 없습니다. 다시 확인하시고 시도해주세요.");
				}
				else {
					id = (String)result;
					session.setAttribute("msg",
						"아이디는 " + id + " 입니다.");
				}
				// 페이지 이동
				jsp = "redirect:searchForm.do";
				break;
			case "/member/searchPw.do":
				System.out.println("비밀번호 찾기 -----");
				// 데이터 수집 (id, name, email, birth)
				id = request.getParameter("id");
				name = request.getParameter("name");
				email = request.getParameter("email");
				birth = request.getParameter("birth");
				// 서비스로 넘어갈 데이터 세팅
				vo = new MemberVO();
				vo.setId(id);
				vo.setName(name);
				vo.setEmail(email);
				vo.setBirth(birth);
				// 서비스를 실행합니다.
				result = Execute.execute(Init.get(uri), vo);
				// 결과확인
				if (result == null) {
					session.setAttribute("msg",
						"비밀번호를 찾을 수 없습니다. 확인하시고 다시 시도해주세요.");
				}
				else {
					pw = (String)result;
					session.setAttribute("msg",
						"비밀번호는 " + pw + " 입니다.");
				}
				// 페이지이동
				jsp = "redirect:searchForm.do";
				break;
			
			default:
				request.setAttribute("uri", uri);
				jsp = "error/404";
				// "/WEB-INF/views/error/404.jsp"
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		// 이동할 페이지 리턴
		return jsp;
	} // end of execute()
} 
