package model.DAO;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.DTO.NoticeDTO;

public class NoticeDAO extends DataBaseInfo{

   final String COLUMNS = "NOTICE_NO, NOTICE_SUB, NOTICE_CON, NOTICE_DATE, NOTICE_KIND, NOTICE_FILE, NOTICE_HITS, EMPLOYEE_ID";
   
   
   public void noticeInsert(NoticeDTO dto) {
      sql = "insert into notice ( " + COLUMNS +  " ) values (?,?,?,?,?,?,?,?)";
      getConnect();
      try {
         pstmt=conn.prepareStatement(sql);
         pstmt.setString(1, dto.getNoticeNo());
         pstmt.setString(2, dto.getNoticeSub());
         pstmt.setString(3, dto.getNoticeCon());
         long noticeDate=dto.getNoticeDate().getTime();
         pstmt.setDate(4, new Date(noticeDate));
         pstmt.setString(5, dto.getNoticeKind());
         pstmt.setString(6, dto.getNoticeFile());
         pstmt.setString(7, dto.getNoticeHits());
         pstmt.setString(8, dto.getEmployeeId());
         int i = pstmt.executeUpdate();
         System.out.println(i+"개 행이 저장되었습니다.");
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close();
      }
      
   }
   
   
   
   
   public List<NoticeDTO> noticeList(){
      List<NoticeDTO> list = new ArrayList<NoticeDTO>();
      sql = "select " + COLUMNS + " from notice";
      getConnect();
      try {
         pstmt=conn.prepareStatement(sql);
         rs=pstmt.executeQuery();
         while (rs.next()) {
            NoticeDTO dto = new NoticeDTO();
            dto.setEmployeeId(rs.getString("EMPLOYEE_ID"));
            dto.setNoticeCon(rs.getString("NOTICE_CON"));
            dto.setNoticeDate(rs.getDate("NOTICE_DATE"));
            dto.setNoticeFile(rs.getString("NOTICE_FILE"));
            dto.setNoticeHits(rs.getString("NOTICE_HITS"));;
            dto.setNoticeKind(rs.getString("NOTICE_KIND"));
            dto.setNoticeNo(rs.getString("NOTICE_NO"));
            dto.setNoticeSub(rs.getString("NOTICE_SUB"));
            list.add(dto);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close();
      }
      return list;
   }
   
   public String noticeNo() {
      String noticeNo = null;
      sql="select notice_seq.nextval from dual";
      getConnect();
      try {
         pstmt=conn.prepareStatement(sql);
         rs=pstmt.executeQuery();
         rs.next();
         noticeNo = rs.getString(1);
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close();
      }
      return noticeNo;
   }
   
}