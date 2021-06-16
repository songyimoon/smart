package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.DAO.MemberDAO;
import model.DTO.AuthInfo;
import model.DTO.MemberDTO;

public class MemberDetailPage {
	public void memberDetail(HttpServletRequest request) {
		HttpSession session = request.getSession();
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo"); // get 하면 obj타입으로 받아지기 때문에, 형 변환을 통해 받아주어야 한다.
		String memId = authInfo.getUserId(); // authInfo에 있는 userId를 빼오는것! dao로 보내서 db로부터 내 정보를 가져오면 되겠다. 세션에 있는 내 로그인 정보를 통해 내 정보를 가져오자
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = dao.memDetail(memId); // 회원 상세정보 가져올 때 사용했었던 dao내에 있는 memDetail메서드. 회원상세정보가 내 상세정보랑 같으니까 그대로 사용하자. 얘는 memberDTO를 반환해왔음.
		request.setAttribute("dto", dto);
		
	}
}
