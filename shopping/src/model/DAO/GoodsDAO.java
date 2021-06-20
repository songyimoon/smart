package model.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.DTO.CartDTO;
import model.DTO.ProductCartDTO;
import model.DTO.ProductDTO;

public class GoodsDAO extends DataBaseInfo{
	
	final String COLUMNS = "PROD_NUM, PROD_NAME, PROD_PRICE, PROD_IMAGE, PROD_DETAIL, PROD_CAPACITY, "
							+ "PROD_SUPPLIER, PROD_DEL_FEE, RECOMMEND, EMPLOYEE_ID, CTGR";
	
	
	public List cartList(String memId) {
		List list = new ArrayList();
		sql=" select p.PROD_NUM, PROD_SUPPLIER, PROD_DEL_FEE, PROD_IMAGE, PROD_NAME, PROD_PRICE, " 
				+ " CART_PRICE, CART_QTY " 
				+ " from products p, cart c " 
				+ " where p.PROD_NUM = c.PROD_NUM and c.MEM_ID = ? ";
		getConnect();
		try {
			pstmt=conn.prepareStatement(sql);			pstmt.setString(1, memId);
			rs=pstmt.executeQuery();
			while (rs.next()) {
				ProductCartDTO dto=new ProductCartDTO();
				dto.setCartDTO(new CartDTO());
				dto.setProductDTO(new ProductDTO());
				dto.getCartDTO().setCartPrice(rs.getInt("CART_PRICE"));
				dto.getCartDTO().setCartQty(rs.getString("CART_QTY"));
				
				dto.getProductDTO().setProdSupplier(rs.getString("PROD_SUPPLIER"));
				dto.getProductDTO().setProdDelFee(rs.getString("PROD_DEL_FEE"));
				dto.getProductDTO().setProdImage(rs.getString("PROD_IMAGE"));
				dto.getProductDTO().setProdName(rs.getString("PROD_NAME"));
				dto.getProductDTO().setProdPrice(rs.getInt("PROD_PRICE"));
				// 객체안에 있는 객체의 멤버필드 안에 값을 저장해서 가져옴		
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return list;
	}
	
	
	public void cartInsert(CartDTO dto) {
		// 머지!!!
		// cart랑 products를 비교하는것임.
		// using뒤엔 무조건 있어야하는데
		// cart랑 products 둘 다 있으면 update를 하고
		// products 하나에만 있으면 insert 
		sql=" merge into cart c "
			+ " using (select PROD_NUM from products where PROD_NUM = ? ) p "
			+ " on (c.PROD_NUM = p.PROD_NUM and c.MEM_ID = ? ) "
			+ " when MATCHED then "
			+ "                     update set CART_QTY = CART_QTY + ? ,"
			+ "                                CART_PRICE = CART_PRICE + ? "
			+ " when not MATCHED then "
			+ " 					insert ( c.MEM_ID, c.PROD_NUM, c.CART_QTY, c.CART_PRICE ) "
			+ " values (?,?,?,?)";
		
		getConnect();
		try {
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getProdNum());
			pstmt.setString(2, dto.getMemId());
			pstmt.setString(3, dto.getCartQty());
			pstmt.setInt(4, dto.getCartPrice());
			
			pstmt.setString(5, dto.getMemId());
			pstmt.setString(6, dto.getProdNum());
			pstmt.setString(7, dto.getCartQty());
			pstmt.setInt(8, dto.getCartPrice());
			int i=pstmt.executeUpdate();
			System.out.println(i+"개 행이 저장되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void goodsDel(String prodNum) {
		sql = "delete from products where PROD_NUM = ?";
		getConnect();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, prodNum);
			int i = pstmt.executeUpdate();
			System.out.println(i+"개 행이 삭제되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
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
