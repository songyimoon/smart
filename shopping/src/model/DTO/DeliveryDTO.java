package model.DTO;
import java.util.Date;


public class DeliveryDTO {
	String purchaseNum;
	String deliveryCom;
	String deliveryNum;
	Date deliveryExpDate;
	Date arrivalExpDate;
	String deliveryDelFee;
	
	public String getPurchaseNum() {
		return purchaseNum;
	}
	public void setPurchaseNum(String purchaseNum) {
		this.purchaseNum = purchaseNum;
	}
	public String getDeliveryCom() {
		return deliveryCom;
	}
	public void setDeliveryCom(String deliveryCom) {
		this.deliveryCom = deliveryCom;
	}
	public String getDeliveryNum() {
		return deliveryNum;
	}
	public void setDeliveryNum(String deliveryNum) {
		this.deliveryNum = deliveryNum;
	}
	public Date getDeliveryExpDate() {
		return deliveryExpDate;
	}
	public void setDeliveryExpDate(Date deliveryExpDate) {
		this.deliveryExpDate = deliveryExpDate;
	}
	public Date getArrivalExpDate() {
		return arrivalExpDate;
	}
	public void setArrivalExpDate(Date arrivalExpDate) {
		this.arrivalExpDate = arrivalExpDate;
	}
	public String getDeliveryDelFee() {
		return deliveryDelFee;
	}
	public void setDeliveryDelFee(String deliveryDelFee) {
		this.deliveryDelFee = deliveryDelFee;
	}
	
}