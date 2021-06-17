package model.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.DTO.ProductDTO;
import oracle.net.aso.l;

public class GoodsDAO extends DataBaseInfo{
	
	final String COLUMNS = "PROD_NUM, PROD_NAME, PROD_PRICE, PROD_IMAGE, PROD_DETAIL, PROD_CAPACITY, "
							+ "PROD_SUPPLIER, PROD_DEL_FEE, RECOMMEND, EMPLOYEE_ID, CTGR";
	
	public void goodsUpdate(ProductDTO dto) {
		sql = "update products set PROD_NAME = ?, PROD_PRICE = ?, PROD_DETAIL = ?, PROD_CAPACITY = ?, PROD_SUPPLIER = ?, PROD_DEL_FEE = ?, RECOMMEND = ?"
				+ " where PROD_NUM = ?";
		getConnect();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, dto.getProdName());
			pstmt.setInt(2, dto.getProdPrice());
			pstmt.setString(3, dto.getProdDetail());
			pstmt.setString(4, dto.getProdCapacity());
			pstmt.setString(5, dto.getProdSupplier());
			pstmt.setString(6, dto.getProdDelFee());
			pstmt.setString(7, dto.getRecommend());
			pstmt.setString(8, dto.getProdNum());
			int i = pstmt.executeUpdate();
			System.out.println(i+"개 행이 수정되었습니다.");
			
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	public ProductDTO GoodsOne(String prodNum) {
		ProductDTO dto = null;
		sql="select " + COLUMNS + ","
				+ "case CTGR when 'wear' then '의류' "
				+ "when 'cosmetic' then '뷰티' "
				+ "when 'food' then '식품' "
				+ "when 'car' then '자동차용품' end CTGR1 from products" 
				+ " where PROD_NUM = ?"; 
		getConnect();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, prodNum);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				dto = new ProductDTO();
				dto.setCtgr(rs.getString("CTGR1"));
				dto.setEmployeeId(rs.getString("EMPLOYEE_ID"));
				dto.setProdCapacity(rs.getString("PROD_CAPACITY"));
				dto.setProdDelFee(rs.getString("PROD_DEL_FEE"));
				dto.setProdDetail(rs.getString("PROD_DETAIL"));
				dto.setProdImage(rs.getString("PROD_IMAGE"));
				dto.setProdName(rs.getString("PROD_NAME"));
				dto.setProdNum(rs.getString("PROD_NUM"));
				dto.setProdPrice(rs.getInt("PROD_PRICE"));
				dto.setProdSupplier(rs.getString("PROD_SUPPLIER"));
				dto.setRecommend(rs.getString("RECOMMEND"));
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			close();
		}
		return dto;
	}
	
	
	public List<ProductDTO> goodsList(){
		List<ProductDTO> list = new ArrayList<ProductDTO>();
		sql="select " + COLUMNS + ","
				+ "case CTGR when 'wear' then '의류' "
				+ "when 'cosmetic' then '뷰티' "
				+ "when 'food' then '식품' "
				+ "when 'car' then '자동차용품' end CTGR1 from products"; 
		getConnect();
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while (rs.next()) {
				ProductDTO dto = new ProductDTO();
				dto.setCtgr(rs.getString("CTGR1"));
				dto.setEmployeeId(rs.getString("EMPLOYEE_ID"));
				dto.setProdCapacity(rs.getString("PROD_CAPACITY"));
				dto.setProdDelFee(rs.getString("PROD_DEL_FEE"));
				dto.setProdDetail(rs.getString("PROD_DETAIL"));
				dto.setProdImage(rs.getString("PROD_IMAGE"));
				dto.setProdName(rs.getString("PROD_NAME"));
				dto.setProdNum(rs.getString("PROD_NUM"));
				dto.setProdPrice(rs.getInt("PROD_PRICE"));
				dto.setProdSupplier(rs.getString("PROD_SUPPLIER"));
				dto.setRecommend(rs.getString("RECOMMEND"));
				list.add(dto);
			}
		} catch (SQLException e) {		
			e.printStackTrace();
		}
		return list;
	}
	
	
	public void prodInsert(ProductDTO dto) {
		sql = "insert into products ( " + COLUMNS + " ) values (?,?,?,?,?,?,?,?,?,?,?)";
		getConnect();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, dto.getProdNum());
			pstmt.setString(2, dto.getProdName());
			pstmt.setInt(3, dto.getProdPrice());
			pstmt.setString(4, dto.getProdImage());
			pstmt.setString(5, dto.getProdDetail());
			pstmt.setString(6, dto.getProdCapacity());
			pstmt.setString(7, dto.getProdSupplier());
			pstmt.setString(8, dto.getProdDelFee());
			pstmt.setString(9, dto.getRecommend());
			pstmt.setString(10, dto.getEmployeeId());
			pstmt.setString(11, dto.getCtgr()); 
			int i = pstmt.executeUpdate();
			System.out.println(i+"개 행이 저장되었습니다.");
		} catch (SQLException e) {		
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	
	
	public String goodsNum() {
		String goodsNum = null;
		sql="select prod_seq.nextval from dual";
		getConnect();
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			rs.next();
			goodsNum = rs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return goodsNum;
	}
}
