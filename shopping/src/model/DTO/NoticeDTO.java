package model.DTO;

import java.util.Date;

public class NoticeDTO {

	Integer noticeNo;
	String noticeSub;
	String noticeCon;
	Date noticeDate;
	String noticeKind;
	String noticeFile;
	String noticeHits;
	String employeeId;
	public Integer getNoticeNo() {
		return noticeNo;
	}
	public void setNoticeNo(Integer noticeNo) {
		this.noticeNo = noticeNo;
	}
	public String getNoticeSub() {
		return noticeSub;
	}
	public void setNoticeSub(String noticeSub) {
		this.noticeSub = noticeSub;
	}
	public String getNoticeCon() {
		return noticeCon;
	}
	public void setNoticeCon(String noticeCon) {
		this.noticeCon = noticeCon;
	}
	public Date getNoticeDate() {
		return noticeDate;
	}
	public void setNoticeDate(Date noticeDate) {
		this.noticeDate = noticeDate;
	}
	public String getNoticeKind() {
		return noticeKind;
	}
	public void setNoticeKind(String noticeKind) {
		this.noticeKind = noticeKind;
	}
	public String getNoticeFile() {
		return noticeFile;
	}
	public void setNoticeFile(String noticeFile) {
		this.noticeFile = noticeFile;
	}
	public String getNoticeHits() {
		return noticeHits;
	}
	public void setNoticeHits(String noticeHits) {
		this.noticeHits = noticeHits;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	
	
}
