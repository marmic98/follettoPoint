package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class OrderModel{

	private static DataSource ds;

	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/storage");

		} catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}

	static final String TABLE_NAME = "ordine";

	
	public synchronized int doSave(OrderBean order) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		
		
		String insertSQL = "INSERT INTO " + TABLE_NAME
				+ " (email, stato, data, importo, carta, dataSpedizione) VALUES (?, ?, current_date(), ?, ?, DATE_ADD(current_date(), INTERVAL 5 DAY ));";
		int code = 0;
		

		try {
			connection = ds.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, order.getEmail());
			preparedStatement.setInt(2, order.getStato());
			
			preparedStatement.setDouble(3, order.getImporto());
			preparedStatement.setString(4, order.getCarta());
			
			
			preparedStatement.executeUpdate();
			
		
			connection.commit();
			
			ResultSet keys = preparedStatement.getGeneratedKeys();
			keys.next();
			code = keys.getInt(1);
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return code;
		
		
	}
	
	public synchronized void doSaveContiene(List <ProductCartBean> products, int idOrdine) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO contiene (idProdotto, idOrdine, quantita) VALUES (?, ?, ?);";
		
		

		try {
			connection = ds.getConnection();
			connection.setAutoCommit(false);
			
			Iterator<ProductCartBean> it = products.iterator();
			while (it.hasNext()) {
				ProductCartBean p = it.next();
				
				preparedStatement = connection.prepareStatement(insertSQL);
				preparedStatement.setInt(1, p.getProduct().getCode());
				preparedStatement.setInt(2, idOrdine);
				preparedStatement.setInt(3, p.getQuantityCart());
				preparedStatement.executeUpdate();
				connection.commit();
			}
				
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		//
		
		try {
			connection = ds.getConnection();
			connection.setAutoCommit(false);
			
			Iterator<ProductCartBean> it = products.iterator();
			while (it.hasNext()) {
				ProductCartBean p = it.next();
				String updateSQL = "update product set quantita = quantita - " + p.getQuantityCart()
								+ " where id = " + p.getProduct().getCode();
				
				preparedStatement = connection.prepareStatement(updateSQL);
				
				preparedStatement.executeUpdate();
				connection.commit();
			}
				
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
	}

	
	public synchronized OrderBean doRetrieveByKey(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		OrderBean bean = new OrderBean();

		String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setId(rs.getInt("id"));
				bean.setEmail(rs.getString("email"));
				bean.setStato(rs.getInt("stato"));
				bean.setData(rs.getDate("data"));
				bean.setImporto(rs.getDouble("importo"));
				bean.setCarta(rs.getString("carta"));
				bean.setDataSpedizione(rs.getDate("dataSpedizione"));

			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return bean;
	}
	

	
	public synchronized boolean doDelete(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, code);

			result = preparedStatement.executeUpdate();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return (result != 0);
	}

	
	public synchronized Collection<OrderBean> doRetrieveAll(String order, String email) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<OrderBean> orders = new LinkedList<OrderBean>();

		String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE email = '" + email + "'";
		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				OrderBean bean = new OrderBean();

				bean.setId(rs.getInt("id"));
				bean.setEmail(rs.getString("email"));
				bean.setStato(rs.getInt("stato"));
				bean.setData(rs.getDate("data"));
				bean.setImporto(rs.getDouble("importo"));
				bean.setCarta(rs.getString("carta"));
				bean.setDataSpedizione(rs.getDate("dataSpedizione"));
				orders.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return orders;
	}

}