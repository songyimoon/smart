package model.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.DTO.CartDTO;
import model.DTO.OrderListDTO;
import model.DTO.PaymentDTO;
import model.DTO.ProdReviewDTO;
import model.DTO.ProductCartDTO;
import model.DTO.ProductDTO;
import model.DTO.PurchaseDTO;
import model.DTO.ReviewDTO;


public class GoodsDAO extends DataBaseInfo{
	
	final String COLUMNS = "PROD_NUM, PROD_NAME, PROD_PRICE, PROD_IMAGE, PROD_DETAIL, PROD_CAPACITY, "
							+ "PROD_SUPPLIER, PROD_DEL_FEE, RECOMMEND, EMPLOYEE_ID, CTGR";
	
	
	public List<ProdReviewDTO> prodReviewSelect(String prodNum) {
		List<ProdReviewDTO> list = new ArrayList<ProdReviewDTO>();
		sql="select rpad(substr(p.MEM_ID,1,3),length(p.MEM_ID),'*') mem_id, REVIEW_CONTENT, REVIEW_DATE ,REVIEW_IMG "
				 + " from purchase p, review r " 
				 + " where p.PURCHASE_NUM = r.PURCHASE_NUM "
				 + " and r.PROD_NUM = ? ";
		getConnect();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, prodNum);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ProdReviewDTO dto = new ProdReviewDTO();
				dto.setMemId(rs.getString(1));
				dto.setReviewContent(rs.getString(2));
				dto.setReviewDate(rs.getDate(3));
				dto.setReviewImg(rs.getString(4));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		} 
		return list;
	}
	
	
	
	public void reviewUpdate(ReviewDTO dto) {
		sql="update review set REVIEW_CONTENT = ? "
				+ " where PROD_NUM = ? and PURCHASE_NUM = ? ";	
		getConnect();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, dto.getReviewContent());
			pstmt.setString(2, dto.getProdNum());
			pstmt.setString(3, dto.getPurchaseNum());
			int i = pstmt.executeUpdate();
			System.out.println(i+"개 행이 수정되었습니다. (리뷰)");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	
	public void reviewSelect(ReviewDTO dto) {
		sql = "select PURCHASE_NUM, PROD_NUM, REVIEW_DATE, REVIEW_CONTENT, REVIEW_IMG from review "
				+ " where PURCHASE_NUM = ? and PROD_NUM = ? ";
		getConnect();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, dto.getPurchaseNum());
			pstmt.setString(2, dto.getProdNum());
			rs=pstmt.executeQuery();
			if(rs.next()) {
				dto.setProdNum(rs.getString("PROD_NUM"));
				dto.setPurchaseNum(rs.getString("PURCHASE_NUM"));
				dto.setReviewDate(rs.getString("REVIEW_DATE"));
				dto.setReviewContent(rs.getString("REVIEW_CONTENT"));
				dto.setReviewImg(rs.getString("REVIEW_IMG"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
	}
	
	
	
	public void reviewInsert(ReviewDTO dto) {
		sql="insert into review (PURCHASE_NUM, PROD_NUM, REVIEW_DATE, REVIEW_CONTENT, REVIEW_IMG ) "
				+ "	values (?,?,sysdate,?,?)  ";
		getConnect();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, dto.getPurchaseNum());
			pstmt.setString(2, dto.getProdNum());
			pstmt.setString(3, dto.getReviewContent());
			pstmt.setString(4, dto.getReviewImg());
			int i = pstmt.executeUpdate();
			System.out.println(i+"개 행이 저장되었습니다.(리뷰)");
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			close();
		}
		
	}
	
	public void payment(PaymentDTO dto) {
		String num = " select to_char(sysdate,'yyyymmdd') || "
					+ " nvl2(max(PAYMENT_APPR_NUM),substr(max(PAYMENT_APPR_NUM),-6),100000)+1 "
					+ " from payment "
					+ "	where substr(PAYMENT_APPR_NUM,1,8) = to_char(sysdate,'yyyymmdd')";	
		// 날마다 갱신하는 쿼리문
		sql=" insert into payment ( PURCHASE_NUM, PAYMENT_METHOD, PAYMENT_APPR_PRICE, PAYMENT_APPR_NUM, PAYMENT_APPR_DATE, PAYMENT_NUMBER )"
				+ " values (?,?,?, ( "+num+" ) ,sysdate,? )";		
		getConnect();		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, dto.getPurchaseNum());
			pstmt.setString(2, dto.getPaymentMethod());
			pstmt.setString(3, dto.getPaymentApprPrice());
			pstmt.setString(4, dto.getPaymentNumber());
			int i = pstmt.executeUpdate();
			System.out.println(i+"개 행이 저장되었습니다.(결제)");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	
	
	public List<OrderListDTO> orderList(String memId){
		List<OrderListDTO> list = new ArrayList<OrderListDTO>();
		sql=" select p2.PURCHASE_DATE, p4.PAYMENT_APPR_NUM , p1.prod_num, "
			+ " p2.PURCHASE_NUM, p1.prod_name, p1.PROD_SUPPLIER, "
			+ " p2.PURCHASE_TOT_PRICE, p1.prod_image ,review_content "
			+ " from products p1, purchase p2, purchase_list p3, payment p4, review r "
			+ " where p3.prod_num = p1.prod_num "
			+ " and p3.PURCHASE_NUM = p2.PURCHASE_NUM "
			+ " and p3.PURCHASE_NUM = r.PURCHASE_NUM(+) "
			+ " and p3.prod_num = r.prod_num(+) "
			+ " and p2.PURCHASE_NUM = p4.PURCHASE_NUM(+) "
			+ " and p2.mem_id = ? "
			+ " order by PURCHASE_NUM desc ";
		
		getConnect();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				OrderListDTO dto = new OrderListDTO();
				dto.setPurchaseDate(rs.getString("PURCHASE_DATE"));
				dto.setPaymentApprNum(rs.getString("PAYMENT_APPR_NUM"));
				dto.setProdImage(rs.getString("PROD_IMAGE"));
				dto.setProdName(rs.getString("PROD_NAME"));
				dto.setProdNum(rs.getString("PROD_NUM"));
				dto.setProdSupplier(rs.getString("PROD_SUPPLIER"));
				dto.setPurchaseNum(rs.getString("PURCHASE_NUM"));
				dto.setPurchaseTotPrice(rs.getString("PURCHASE_TOT_PRICE"));
				dto.setReviewContent(rs.getString("REVIEW_CONTENT"));
				list.add(dto);
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}
	
	
		
	public void cartDel(String prodNum, String memId) {
		sql = " delete from cart "
			   + " where MEM_ID=? and PROD_NUM = ? ";
		getConnect();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			pstmt.setString(2, prodNum);
			int i = pstmt.executeUpdate();
			System.out.println(i+"개 행이 삭제되었습니다.(결제 후 카트에서 삭제)");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	public void cartProdDel(CartDTO dto) {
		sql="delete from cart where MEM_ID=? and PROD_NUM = ?";
		getConnect();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, dto.getMemId());
			pstmt.setString(2, dto.getProdNum());
			int i = pstmt.executeUpdate();
			System.out.println(i+"개 행이 삭제되었습니다.(카트상품삭제)");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	public void purchaseInsert(PurchaseDTO dto) {	
		sql = " insert into purchase (PURCHASE_NUM,MEM_ID,"
				+ " PURCHASE_TOT_PRICE, PURCHASE_ADDR, PURCHASE_METHOD,"
				+ " PURCAHSE_REQUEST,RECEIVER_NAME,RECEIVER_PHONE,"
				+ " PURCHASE_DATE) "
				+ " values(?,?,?,?,?,?,?,?,sysdate)";
		getConnect();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getPurchaseNum());
			pstmt.setString(2, dto.getMemId());
			pstmt.setString(3, dto.getPurchaseTotPrice());
			pstmt.setString(4, dto.getPurchaseAddr());
			pstmt.setString(5, dto.getPurchaseMethod());
			pstmt.setString(6, dto.getPurchaseRequest());
			pstmt.setString(7, dto.getReceiverName());
			pstmt.setString(8, dto.getReceiverPhone());
			int i = pstmt.executeUpdate();
			System.out.println(i+"개 행이 저장되었습니다.(구매 저장)");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}		
	}
	
	public void purchaseListInsert(String purchaseNum, String prodNum, String memId) {
		sql="insert into purchase_list (PURCHASE_NUM, PROD_NUM, PURCHASE_QTY, PURCHASE_PRICE ) "
				+ " select ?, PROD_NUM, CART_QTY, CART_PRICE "
				+ " from cart "
				+ " where PROD_NUM = ? and MEM_ID = ? ";
		//카트에 모든 정보가 있다. 구매리스트와 다른점은 구매번호가 있냐 없냐 뿐이므로, 구매번호만 추가해주면 구매리스트가 된다.
		getConnect();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, purchaseNum);
			pstmt.setString(2, prodNum);
			pstmt.setString(3, memId);
			int i = pstmt.executeUpdate();
			System.out.println(i+"개 행이 저장되었습니다.(구매리스트 저장)");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}		
	}
	
	
	public ProductCartDTO prodCart(String prodNum, String memId) {
		ProductCartDTO dto = null;	
		sql="select p.PROD_NUM , PROD_NAME, PROD_PRICE, "
				+ "	  PROD_SUPPLIER, PROD_DEL_FEE, PROD_IMAGE,"
				+ "       MEM_ID, CART_QTY, CART_PRICE" 
				+ " from products p, cart c" 
				+ " where p.PROD_NUM = c.PROD_NUM "
				+ " and MEM_ID = ? and c.PROD_NUM = ?"; 

		getConnect();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			pstmt.setString(2, prodNum);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				dto=new ProductCartDTO();
				dto.setCartDTO(new CartDTO());
				dto.setProductDTO(new ProductDTO());				
				dto.getProductDTO().setProdNum(rs.getString("PROD_NUM"));				
				dto.getCartDTO().setCartPrice(rs.getInt("CART_PRICE"));
				dto.getCartDTO().setCartQty(rs.getString("CART_QTY"));				
				dto.getProductDTO().setProdSupplier(rs.getString("PROD_SUPPLIER"));
				dto.getProductDTO().setProdDelFee(rs.getString("PROD_DEL_FEE"));
				dto.getProductDTO().setProdImage(rs.getString("PROD_IMAGE"));
				dto.getProductDTO().setProdName(rs.getString("PROD_NAME"));
				dto.getProductDTO().setProdPrice(rs.getInt("PROD_PRICE"));
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		}finally {
			close();
		}
		return dto;
	}
	// 장바구니는 모든 데이터를 다 가져오는데 cartList 
	// 구매는 선택한 것에 대한 데이터만 가져와야한다는 차이점이 있다.
	

	
	
	
	public void cartQtyDown(CartDTO dto) {
		sql="update cart set CART_QTY = CART_QTY - ?, CART_PRICE = CART_PRICE - ? where MEM_ID = ? and PROD_NUM = ? ";
		getConnect();	
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, 1); // 1개 감소니까 그냥 1을 써도 된다. sql문에 그냥 -1을 해도 상관없음.
			pstmt.setInt(2, dto.getCartPrice());
			pstmt.setString(3, dto.getMemId());
			pstmt.setString(4, dto.getProdNum());
			int i =pstmt.executeUpdate();
			System.out.println(i+"개 행이 수정되었습니다.(카트 수량 down)");
		} catch (SQLException e) {		
			e.printStackTrace();
		} finally {
			close();
		}	
	}
	
	
	public List cartList(String memId) {
		List list = new ArrayList();
		sql=" select p.PROD_NUM, PROD_SUPPLIER, PROD_DEL_FEE, PROD_IMAGE, PROD_NAME, PROD_PRICE, " 
				+ " CART_PRICE, CART_QTY " 
				+ " from products p, cart c " 
				+ " where p.PROD_NUM = c.PROD_NUM and c.MEM_ID = ? ";
		// cart에는 MEM_ID랑 c.PROD_NUM이 PRIMARY로 묶여있어서 인덱스임. 인덱스가 아닐 때 이렇게 가져오면 과부하걸린다.
		// 오라클에서 CART 제약조건 조회하면, 두개 필드가 묶여서 프라이머리키로 나옴
		getConnect();
		try {
			pstmt=conn.prepareStatement(sql);			pstmt.setString(1, memId);
			rs=pstmt.executeQuery();
			while (rs.next()) {
				ProductCartDTO dto=new ProductCartDTO();
				dto.setCartDTO(new CartDTO());
				dto.setProductDTO(new ProductDTO());
				// 추가
				dto.getProductDTO().setProdNum(rs.getString("PROD_NUM"));
				
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
		} finally {
			close();
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
			System.out.println(i+"개 행이 저장되었습니다.(카트 담기 혹은 수정 merge)");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	
	public void goodsDel(String prodNum) {
		sql = "delete from products where PROD_NUM = ?";
		getConnect();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, prodNum);
			int i = pstmt.executeUpdate();
			System.out.println(i+"개 행이 삭제되었습니다.(상품삭제)");
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
			System.out.println(i+"개 행이 수정되었습니다.(상품 수정)");		
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
		} finally {
			close();
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
			System.out.println(i+"개 행이 저장되었습니다.(상품 등록)");
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
