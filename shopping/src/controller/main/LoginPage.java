package controller.main;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;

import model.DAO.LoginDAO;
import model.DTO.AuthInfo;

public class LoginPage {
	public void login(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(); // 로그인은 세션이 있어야만 가능하다
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
		LoginDAO dao = new LoginDAO();
		AuthInfo authInfo = dao.login(userId);
		if(authInfo == null) { // rs가 없으면?
			session.removeAttribute("pwFail"); 
			session.setAttribute("userFail", "아이디가 존재하지 않습니다.");
		} else { // 아이디는 존재하는데, (null은 아닌데)
			session.removeAttribute("userFail"); // 세션이 계속 유지되는 것 때문에 오류날 수 있으므로, 삭제해준다. 브라우저가 열려있는 동안에는 세션은 계속 존재한다. // 속성만 날린다
			if(userPw.equals(authInfo.getUserPw())) { // 비밀번호 일치하면
				session.removeAttribute("pwFail"); 
				session.setAttribute("authInfo", authInfo); // 사실상 로그인에 필요한 부분은 해당 정보임						
				// [2]자동로그인
				String autologin=request.getParameter("autologin");
				if(autologin != null && autologin.equals("auto")) {
					Cookie cookie = new Cookie("autoLogin",userId); // 만들어지는 이름은 객체 만들때 만들면 됨. 같은 이름은 오버라이드
					cookie.setPath("/");
					cookie.setMaxAge(60*60*24*30);
					response.addCookie(cookie);
				}				
				// [1]아이디저장
				// 제대로 로그인이 될 때 여기서 아이디 저장할 내용 만들기
				// 쿠키를 만들어달라고 요청
				String idStore=request.getParameter("idStore");
				if (idStore != null && idStore.equals("store")) {
					Cookie cookie = new Cookie("idStore",userId);
					cookie.setPath("/");
					cookie.setMaxAge(60*60*24*30); // 한달에 한번 
					// 웹 브라우저에 쿠키 전달
					response.addCookie(cookie);
				}else {
					Cookie cookie = new Cookie("idStore",userId);
					cookie.setPath("/");
					cookie.setMaxAge(0); // 시간이 0이 됨 -> 소멸
					response.addCookie(cookie);
				}				
			}else { // 아이디는 존재하는데 패스워드가 없다
				session.setAttribute("pwFail", "비밀번호가 틀렸습니다.");
			}		
		}
	}
}
