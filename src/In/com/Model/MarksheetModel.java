package In.com.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import In.com.Bean.Marksheetbean;
import In.com.Util.JDBCDataSource;

public class MarksheetModel {
	
	public static Integer nextPk() throws Exception {
		
		int pk = 0;
		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement ptst = conn.prepareStatement("select max(id) from marksheet");
		
		ResultSet rs = ptst.executeQuery();
		while(rs.next()) {
			pk = rs.getInt(1);
		}
		return pk + 1;
		
	}

	public static void insert(Marksheetbean bean) throws Exception {
		
		Marksheetbean exit = findbyroll(bean.getRoll_no());
		if(exit !=null) {
			throw new RuntimeException( "Already exists");
		}
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
		
		PreparedStatement ptst = conn.prepareStatement("insert into marksheet values (? , ?,? ,?,?,?)");
		ptst.setInt(1, nextPk());
		ptst.setInt(2, bean.getRoll_no());
		ptst.setString(3, bean.getName());
		ptst.setInt(4, bean.getPhysics());
		ptst.setInt(5, bean.getChemistry());
		ptst.setInt(6, bean.getMaths());
		
		int i = ptst.executeUpdate();
		System.out.println("data insert :-" +i);
	}
	
	public static void update(Marksheetbean bean) throws Exception {
		
//		Marksheetbean exist = findbyroll(bean.getRoll_no());
//		if(exist !=null) {
//			throw new RuntimeException( "Already exists");
//		}
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
		
		PreparedStatement ptst = conn.prepareStatement("update marksheet set roll_no = ?, name = ? where id = ? ");
		ptst.setInt(1, bean.getRoll_no());
		ptst.setString(2, bean.getName());
		ptst.setInt(3, bean.getId());
		
		int i = ptst.executeUpdate();
		System.out.println("data updated : "+i);
	}
	
	public static void delete(int id) throws Exception {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
		
		PreparedStatement ptst = conn.prepareStatement("delete from marksheet where id =?");
		ptst.setInt(1, id);
		
		int i = ptst.executeUpdate();
		System.out.println("data deleted : "+i);
	}
	
	public static void read() throws Exception {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
		
		PreparedStatement ptst = conn.prepareStatement("select * from marksheet");
		
		ResultSet rs = ptst.executeQuery();
		
		while(rs.next()) {
			
			System.out.print(rs.getInt(1));
			System.out.print("\t"+rs.getInt(2));
			System.out.print("\t"+rs.getString(3));
			System.out.print("\t"+rs.getInt(4));
			System.out.print("\t"+rs.getInt(5));
			System.out.println("\t"+rs.getInt(6));
		}
	}
	
	public Marksheetbean findbypk(int id) throws Exception {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
		
		PreparedStatement ptst = conn.prepareStatement("select * from marksheet where id = ?");
		ptst.setInt(1, id);
		
		ResultSet rs = ptst.executeQuery();
		
		Marksheetbean bean = null;
		
		while(rs.next())
		{
			bean = new Marksheetbean();
			bean.setId(rs.getInt(1));
			bean.setRoll_no(rs.getInt(2));
			bean.setName(rs.getString(3));
			bean.setPhysics(rs.getInt(4));
			bean.setChemistry(rs.getInt(5));
			bean.setMaths(rs.getInt(6));
			
		}	
		return bean;
		
	}
	
	public static Marksheetbean findbyroll(int roll_no) throws Exception {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
		
		PreparedStatement ptst = conn.prepareStatement("select roll_no from marksheet where id = ? ");
		ptst.setInt(1, roll_no);
		
		ResultSet rs = ptst.executeQuery();
		
		Marksheetbean b = null;
		while(rs.next()) {
			
			b = new Marksheetbean();
//			bean.setId(rs.getInt(1));
			b.setRoll_no(rs.getInt(1));
//			bean.setName(rs.getString(3));
//			bean.setPhysics(rs.getInt(4));
//			bean.setChemistry(rs.getInt(5));
//			bean.setMaths(rs.getInt(6));
		}
		return b;
		
	}
	
	public List readAll() throws Exception {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
		
		PreparedStatement ptst = conn.prepareStatement("select * from marksheet");
		
		ResultSet rs = ptst.executeQuery();
		
		List list = new ArrayList();
		while(rs.next()) {
			Marksheetbean bean = new Marksheetbean();
			bean.setId(rs.getInt(1));
			bean.setRoll_no(rs.getInt(2));
			bean.setName(rs.getString(3));
			bean.setPhysics(rs.getInt(4));
			bean.setChemistry(rs.getInt(5));
			bean.setMaths(rs.getInt(6));
			list.add(bean);
			
		}
		return list;
	}
	
	//In these method the problem is when we pass two value so it gives error.....
	
	public List search(Marksheetbean bean) throws Exception {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
		
		StringBuffer sql = new StringBuffer("select * from marksheet ");
		
		if(bean != null) {
			if(bean.getName()!= null && bean.getName().length() > 0) {
				sql.append("where name like '" +bean.getName() +"%'");
			}
			if(bean.getRoll_no() > 0) {
				sql.append("where roll_no = " + bean.getRoll_no());
			}
		}
		
		System.out.println("sql : " +sql);
		PreparedStatement ptst = conn.prepareStatement(sql.toString());
		
		ResultSet rs = ptst.executeQuery();
		
		List list = new ArrayList();
		
		while(rs.next()) {
			
			bean = new Marksheetbean();
			bean.setId(rs.getInt(1));
			bean.setRoll_no(rs.getInt(2));
			bean.setName(rs.getString(3));
			bean.setPhysics(rs.getInt(4));
			bean.setChemistry(rs.getInt(5));
			bean.setMaths(rs.getInt(6));
			list.add(bean);
			
		}
		return list;
		
	}
	
	//SQL injection
	
	public List search1(Marksheetbean bean) throws Exception {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
		
		StringBuffer sql = new StringBuffer("select * from marksheet where 1=1");
		if(bean != null) {
			
			if(bean.getName()!=null && bean.getName().length() >0) {
				
				sql.append(" and name =' " +bean.getName() +"'");
				
			}
			if(bean.getRoll_no()>0) {
				sql.append(" and roll_no = "+bean.getRoll_no());
			}
			if(bean.getPhysics()>0) {
				sql.append(" and physics = " + bean.getPhysics());
			}
			
		}
		System.out.println("sql : "+sql);
	
		PreparedStatement ptst = conn.prepareStatement(sql.toString());
		ResultSet rs = ptst.executeQuery();
		
		List list = new ArrayList();
		while(rs.next()) {
			bean = new Marksheetbean(); 
			bean.setId(rs.getInt(1));
			bean.setRoll_no(rs.getInt(2));
			bean.setName(rs.getString(3));
			bean.setPhysics(rs.getInt(4));
			bean.setChemistry(rs.getInt(5));
			bean.setMaths(rs.getInt(6));

			list.add(bean);
		}
		return list;
		
	}
	
	public List search2(Marksheetbean bean, int pageNo , int pageSize ) throws Exception {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
		
		StringBuffer sql = new StringBuffer("select * from marksheet where 1=1");
		
		if(bean != null) {
			
			if(bean.getName()!=null && bean.getName().length() >0) {
				
				sql.append(" and name ='"+bean.getName() +"'");
				
			}
			if(bean.getRoll_no()>0) {
				sql.append(" and roll_no = "+bean.getRoll_no());
			}
			
		}
		if(pageSize>0) {
			pageNo = (pageNo - 1)*pageSize;
			sql.append(" limit " +pageNo +"," +pageSize);
		}
		
		System.out.println(sql);
		PreparedStatement ptst = conn.prepareStatement(sql.toString());
		
		ResultSet rs = ptst.executeQuery();
		List list = new ArrayList();
		
		while (rs.next()) {
			bean = new Marksheetbean();
			bean.setId(rs.getInt(1));
			bean.setRoll_no(rs.getInt(2));
			bean.setName(rs.getString(3));
			bean.setPhysics(rs.getInt(4));
			bean.setChemistry(rs.getInt(5));
			bean.setMaths(rs.getInt(6));
			list.add(bean);
		}
		return list;
		
	}
	
}
