package model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.DTO.EmployeeDTO;

public class EmployeeDAO {
	
	final String COLUMMS = "EMPLOYEE_ID, EMP_USERID, EMP_PW, EMP_NAME, HIRE_DATE, JOB_ID, PH_NUMBER, OFFICE_NUMBER, EMP_EMAIL, EMP_ADDRESS";
	static String jdbcDriver; // 너무 길어서 만든 변수
	static String jdbcUrl; // too
	static Connection conn;
	String sql;
	PreparedStatement pstmt; // sql문을 실행시키기 위함
	Integer result;
	ResultSet rs;
	
	static {
		jdbcDriver="oracle.jdbc.driver.OracleDriver";
		jdbcUrl="jdbc:oracle:thin:@localhost:1521:xe";		
	}
	public static void getConnect() {
		try {
			Class.forName(jdbcDriver); // 사용하고자 하는 라이브러리가 있는지를 확인하는 것
			conn = DriverManager.getConnection(jdbcUrl,"msy","oracle"); // conn에 데이터베이스 연결
		} catch (Exception e) {
			e.printStackTrace();
		} 						
	}
	
	public List<EmployeeDTO> getEmpList() {
		List<EmployeeDTO> list = new ArrayList<EmployeeDTO>();
		sql = "select * from employees";
		getConnect();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				EmployeeDTO dto = new EmployeeDTO();
				dto.setEmployeeId(rs.getString("EMPLOYEE_ID"));
				dto.setEmpUserid(rs.getString(2));
				dto.setEmpPw(rs.getString("EMP_PW"));
				dto.setEmpName(rs.getString(4));
				dto.setHireDate(rs.getString("HIRE_DATE"));
				dto.setJobId(rs.getString("JOB_ID"));
				dto.setPhNumber(rs.getString("PH_NUMBER"));
				dto.setOfficeNumber(rs.getString("OFFICE_NUMBER"));
				dto.setEmpEmail(rs.getString("EMP_EMAIL"));
				dto.setEmpAddress(rs.getString("EMP_ADDRESS"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}
	public int getEmpNo() {
		getConnect();
		sql="select nvl(max(employee_id), 10000)+1 from employees";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next(); // BOF를 지나쳐서.. 내려가렴.... 
			result = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
	public void empInsert(EmployeeDTO dto){
		sql= "insert into employees ("+COLUMMS+")" + "values(?,?,?,?,?,?,?,?,?,?)";
		getConnect();
		try {
			pstmt = conn.prepareStatement(sql); // spl문을 db로 전달한다. // try catch
			pstmt.setString(1, dto.getEmployeeId()); // parameterIndex는 값 있는건 따지지 않고 ? 갯수로만 인덱스를 따짐
			pstmt.setString(2, dto.getEmpUserid());
			pstmt.setString(3, dto.getEmpPw());
			pstmt.setString(4, dto.getEmpName());
			pstmt.setString(5, dto.getHireDate());
			pstmt.setString(6, dto.getJobId());
			pstmt.setString(7, dto.getPhNumber());
			pstmt.setString(8, dto.getOfficeNumber());
			pstmt.setString(9, dto.getEmpEmail());
			pstmt.setString(10, dto.getEmpAddress());
			result=pstmt.executeUpdate(); // executeUpdate는 integer를 반환한다.
			System.out.println(result+"개행이 저장되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
	private void close() { // 여기서만 쓸꺼니까 private로 메서드 생성. 닫는 메서드!
		if(rs != null)
			try {rs.close();} 
			catch (SQLException e) {}
		if(pstmt != null)
			try {pstmt.close();} 
			catch (SQLException e) {}
		if(conn != null)
			try {conn.close();} 
			catch (SQLException e) {}
	}
}
