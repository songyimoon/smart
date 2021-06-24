package model.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.DTO.ClientSalesDTO;
import model.DTO.CustomerTotalDTO;
import model.DTO.DeliveryDTO;

public class SalesDAO extends DataBaseInfo{
	
	
	
	public void deliverySelect(DeliveryDTO dto) {
		// 물음표 순서는 나오는 순서대로임
		String delFee = " select sum(PROD_DEL_FEE) "
						+ " from purchase_list pl, products pr " 
						+ " where pl.prod_num = pr.prod_num "
				 		+ " and pl.purchase_num = ? ";
		sql="merge into delivery d " 
				+ " using (select purchase_num from purchase where purchase_num = ? ) p " 
				+ " on (d.purchase_num = p.purchase_num) " 
				+ " when MATCHED then " 
				+ " update set DELIVERY_COM = ? , DELIVERY_NUM = ?, DELEVERY_EXP_DATE = ?, ARRIVAL_EXP_DATE = ? , PRODUCT_DEL_FEE = ("+delFee+") " 
				+ " when not MATCHED then "
				+ " insert ( DELIVERY_COM, DELIVERY_NUM, DELEVERY_EXP_DATE, ARRIVAL_EXP_DATE, PRODUCT_DEL_FEE, PURCHASE_NUM ) " 
				+ " values (?,?,?,?, ("+delFee+"),? ) ";
		getConnect();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, dto.getPurchaseNum());
			pstmt.setString(2, dto.getDeliveryCom());
			pstmt.setString(3, dto.getDeliveryNum());
			pstmt.setString(4, dto.getDeliveryExpDate());
			pstmt.setString(5, dto.getArrivalExpDate());
			pstmt.setString(6, dto.getPurchaseNum());
			pstmt.setString(7, dto.getDeliveryCom());
			pstmt.setString(8, dto.getDeliveryNum());
			pstmt.setString(9, dto.getDeliveryExpDate());
			pstmt.setString(10, dto.getArrivalExpDate());
			pstmt.setString(11, dto.getPurchaseNum());
			pstmt.setString(12, dto.getPurchaseNum());
		
			int i = pstmt.executeUpdate();
			System.out.println(i+"개 행이 입력되었습니다. (배송)");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	} 
		
		
	
	
	
	
	public DeliveryDTO deliverySelect(String purchaseNum) {
		DeliveryDTO dto = null;
		sql = " select PURCHASE_NUM, DELIVERY_COM, DELIVERY_NUM, DELEVERY_EXP_DATE, ARRIVAL_EXP_DATE, PRODUCT_DEL_FEE "
				+ " from DELIVERY "
				+ " where PURCHASE_NUM = ? ";
		getConnect();		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, purchaseNum);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				dto = new DeliveryDTO();
				dto.setArrivalExpDate(rs.getString(5));
				dto.setDeliveryCom(rs.getString(2));
				dto.setDeliveryDelFee(rs.getString(6));
				dto.setDeliveryExpDate(rs.getString(4));
				dto.setDeliveryNum(rs.getString(3));
				dto.setPurchaseNum(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return dto;
	}
	
	
	
	public List<CustomerTotalDTO> customerTotal(){
		List<CustomerTotalDTO> list = new ArrayList<CustomerTotalDTO>();
		sql=" select m.MEM_ID, MEM_NAME, count(*), sum(PURCHASE_TOT_PRICE), avg(PURCHASE_TOT_PRICE) "
			 + " from member m, purchase pu " 
			 + " where m.MEM_ID = pu.mem_id " 
			 + " group by m.MEM_ID, m.MEM_NAME " ;
		getConnect();
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				CustomerTotalDTO dto = new CustomerTotalDTO();
				dto.setMemId(rs.getString(1));
				dto.setMemName(rs.getString(2));
				dto.setCount(rs.getString(3));
				dto.setSumPrice(rs.getString(4));
				dto.setAvg(rs.getString(5));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}
	

	public List<ClientSalesDTO> salesList(String memId){
		List<ClientSalesDTO> list = new ArrayList<ClientSalesDTO>();
		sql="select m.MEM_ID, MEM_NAME, MEM_PHONE, pr.PROD_NUM, PROD_NAME, pu.PURCHASE_NUM, PURCHASE_DATE, "
				+ " RECEIVER_NAME, RECEIVER_PHONE, PURCHASE_ADDR, PURCHASE_QTY, PURCHASE_PRICE, DELIVERY_NUM " 
				+ " from member m, products pr, purchase pu, purchase_list pl, delivery d " 
				+ " where m.MEM_ID(+) = pu.MEM_ID " 
				+ " and pu.PURCHASE_NUM = pl.PURCHASE_NUM " 
				+ " and pl.PROD_NUM = pr.PROD_NUM "
				+ " and pu.purchase_num = d.purchase_num(+) ";

		if(memId != null) {
			sql += " and m.MEM_ID = ? ";
		}
		getConnect();
		try {
			pstmt=conn.prepareStatement(sql);
			if(memId != null) {
				pstmt.setString(1, memId);
			}
			rs=pstmt.executeQuery();
			while(rs.next()) {
				ClientSalesDTO dto = new ClientSalesDTO();
				dto.setMemId(rs.getString("MEM_ID"));
				dto.setMemName(rs.getString("MEM_NAME"));
				dto.setMemPhone(rs.getString("MEM_PHONE"));
				dto.setProdNum(rs.getString("PROD_NUM"));
				dto.setProdName(rs.getString("PROD_NAME"));
				dto.setPurchaseNum(rs.getString("PURCHASE_NUM"));
				dto.setPurchaseDate(rs.getDate("PURCHASE_DATE"));
				dto.setReceiverName(rs.getString("RECEIVER_NAME"));
				dto.setReceiverPhone(rs.getString("RECEIVER_PHONE"));
				dto.setPurchaseAddr(rs.getString("PURCHASE_ADDR"));
				dto.setPurchaseQty(rs.getString("PURCHASE_QTY"));
				dto.setPurchasePrice(rs.getString("PURCHASE_PRICE"));
				dto.setDeliveryNum(rs.getString("DELIVERY_NUM"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}
	
}
