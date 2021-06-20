package controller.notice;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model.DAO.NoticeDAO;
import model.DTO.AuthInfo;
import model.DTO.NoticeDTO;

public class NoticeJoinPage {
	public void noticeJoin(HttpServletRequest request) {
		String filePath = "notice/upload";
		String realPath = request.getServletContext().getRealPath(filePath);
		System.out.println(realPath);
		int fileSize=1024*1024*5;
		MultipartRequest multi=null;
		HttpSession session = request.getSession();
		AuthInfo authInfo=(AuthInfo)session.getAttribute("authInfo");
		String emp_no=authInfo.getGrade();  
		String storeFileName;
		String images="";
		
		try {
			multi=new MultipartRequest(request, realPath, fileSize, "utf-8", new DefaultFileRenamePolicy());
			storeFileName = multi.getFilesystemName("noticeFile");
			images = storeFileName;		
		} catch (IOException e) {
			e.printStackTrace();
		}
		NoticeDTO dto = new NoticeDTO();
		dto.setEmployeeId(emp_no);
		dto.setNoticeCon(multi.getParameter("noticeCon"));
		
		String noticeDate1 = multi.getParameter("noticeDate");
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Date noticeDate = null;	
		try {
			noticeDate=sf.parse(noticeDate1);
		} catch (ParseException e) {
			e.printStackTrace();
		}	
		dto.setNoticeDate(noticeDate);
		dto.setNoticeFile(images);
		dto.setNoticeHits(multi.getParameter("noticeHits"));
		dto.setNoticeKind(multi.getParameter("noticekind"));
		dto.setNoticeNo(multi.getParameter("noticeNo"));
		dto.setNoticeSub(multi.getParameter("noticeSub"));
		NoticeDAO dao = new NoticeDAO();
		dao.noticeInsert(dto);
		
		
		
		
		
	}
}
