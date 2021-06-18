package controller.goods;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import model.DAO.GoodsDAO;
import model.DTO.ProductDTO;

public class GoodsDeletePage {
	public void goodsDel(HttpServletRequest request) {
		String prodNum = request.getParameter("prodNum");
		GoodsDAO dao = new GoodsDAO();
		ProductDTO dto = dao.GoodsOne(prodNum);
		String filePath="goods/upload";
		String realPath=request.getServletContext().getRealPath(filePath); // 경로를 알아야 삭제를 하니까..realPath만들어줌
		// 파일 저장된 곳 Java>eclipse>metadata>plugins>org.eclipse.wst.server.core>tmp0>wtpwebapps>shopping>goods>upload
		String [] fileNames= dto.getProdImage().split(",");
		if(fileNames.length>0) { // DB만 삭제하는게 아니라 서버에 있는 파일들을 같이 삭제
			for(String fileName : fileNames) {
				String path = realPath + "/" + fileName; // 경로 + 파일네임
				File f = new File(path);
				if(f.exists()) f.delete();
			}
		}
		dao.goodsDel(prodNum);
	}
}
