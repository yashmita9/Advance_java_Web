package In.com.Model;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import In.com.Bean.Userbean;
import In.com.Util.JDBCDataSource;

public class UserModel {

	public static Integer nextPk() throws Exception {
		int pk = 0;
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
		
		PreparedStatement ptst = conn.prepareStatement("select max(id) from user");
		ResultSet rs = ptst.executeQuery();
		
		while(rs.next()) {
			pk = rs.getInt(1);
		}
		return pk +1;
	}
	
	public static void add(Userbean bean) throws Exception {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
		
		Userbean exist = findByLogin(bean.getLoginId());
		
		if(exist != null) {
			throw new RuntimeException("Login id already exist");
		}
		
		PreparedStatement ptst = conn.prepareStatement("insert into user values(?,?,?,?,?,?,?)");
		ptst.setInt(1, nextPk());
		ptst.setString(2, bean.getFirstName());
		ptst.setString(3, bean.getLastName());
		ptst.setString(4, bean.getLoginId());
		ptst.setString(5, bean.getPassward());
		ptst.setDate(6, new java.sql.Date(bean.getDob().getTime()));
	    ptst.setString(7, bean.getAddress());
	    
	    int i = ptst.executeUpdate();
	    System.out.println("data inserted : "+i);
	}
		
	public static Userbean findByLogin(String loginId) throws Exception {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
		
		PreparedStatement ptst = conn.prepareStatement("select * from user where login_id = ?");
		ptst.setString(1, loginId);
		
		ResultSet rs = ptst.executeQuery();
		Userbean bean = null;
		while(rs.next()) {
			bean = new Userbean();
			bean.setId(rs.getInt(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setLoginId(rs.getString(4));
			bean.setPassward(rs.getString(5));
			bean.setDob(rs.getDate(6));
			bean.setAddress(rs.getString(7));
		}
		return bean;
	}
	
	public static void update(Userbean bean) throws Exception {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
	
		Userbean exist = findByLogin(bean.getLoginId());
		if(exist != null && bean.getId() != exist.getId()) {
			throw new RuntimeException("login id already exist");
		}
		PreparedStatement ptst = conn.prepareStatement("update user set first_name = ?, last_name = ?,login_id =? , passward = ? , date_of_birth = ? , address = ? where id = ? ");
		ptst.setString(1, bean.getFirstName());
		ptst.setString(2, bean.getLastName());
		ptst.setString(3, bean.getLoginId());
		ptst.setString(4, bean.getPassward());
		ptst.setDate(5, new java.sql.Date(bean.getDob().getTime()));
	    ptst.setString(6, bean.getAddress());
	    ptst.setInt(7, bean.getId());
	    
	    int i = ptst.executeUpdate();
	    System.out.println("data updated : "+i);
	}
	
	public void delete(int id) throws Exception {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
		
		PreparedStatement ptst = conn.prepareStatement("delete from user where id = ?");
		ptst.setInt(1, id);
		
		int i = ptst.executeUpdate();
		System.out.println("data deleted : " + i);
	}
	
	public List search(Userbean bean , int pageNo , int pageSize) throws Exception {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
		
		StringBuffer sql = new StringBuffer("select * from user where 1=1");
		if(bean != null) {
			if(bean.getFirstName() != null && bean.getFirstName().length()>0) {
				
				sql.append(" and first_name like '" +bean.getFirstName() +"%'");
				
			}
			if(bean.getLoginId() != null && bean.getLoginId().length()>0) {
				sql.append(" and login_id '" +bean.getLoginId()+"'");
			}
			if(bean.getDob() != null && bean.getDob().getTime()>0) {
				sql.append("and date_of_birth like '"+bean.getDob() + "'");
			}
		}
		
		if(pageSize >0) {
			pageNo = (pageNo - 1)*pageSize;
			sql.append(" limit " + pageNo +" , " +pageSize);
		}
		System.out.println(sql);
		PreparedStatement ptst = conn.prepareStatement(sql.toString());
		
		ResultSet rs = ptst.executeQuery();
		List list = new ArrayList();
		
		while(rs.next()) {
			bean = new Userbean();
			bean.setId(rs.getInt(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setLoginId(rs.getString(4));
			bean.setPassward(rs.getString(5));
			bean.setDob(rs.getDate(6));
			bean.setAddress(rs.getString(7));
			list.add(bean);
		}
		return list;
		
	}
	
	public Userbean findByPk(int id) throws Exception {

		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");

		PreparedStatement pstmt = conn.prepareStatement("select * from user where id = ?");

		pstmt.setInt(1, id);

		ResultSet rs = pstmt.executeQuery();

		Userbean bean = null;

		while (rs.next()) {
			bean = new Userbean();
			bean.setId(rs.getInt(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setLoginId(rs.getString(4));
			bean.setPassward(rs.getString(5));
			bean.setDob(rs.getDate(6));
			bean.setAddress(rs.getString(7));
		}
		return bean;
	}

	public Userbean authenticate(String loginId , String password) throws Exception {
		
		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement ptmt = conn.prepareStatement("select * from user where login_id = ? and passward = ?");
		
		ptmt.setString(1, loginId);
		ptmt.setString(2, password);
		
		ResultSet rs = ptmt.executeQuery();
		Userbean bean = null;
		
		while(rs.next()) {
			bean = new Userbean();
			bean.setId(rs.getInt(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setLoginId(rs.getString(4));
			bean.setPassward(rs.getString(5));
			bean.setDob(rs.getDate(6));
			bean.setAddress(rs.getString(7));
		}
		
		JDBCDataSource.closeConnection(conn);
		
		return bean;
		
	}
}

