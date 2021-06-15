package controller.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.DAO.LoginDAO;
import model.DTO.AuthInfo;

public class LoginPage {
	public void login(HttpServletRequest request) {
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
			}else { // 아이디는 존재하는데 패스워드가 없다
				session.setAttribute("pwFail", "비밀번호가 틀렸습니다.");
			}
			
		}
	}
}
