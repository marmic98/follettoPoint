package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ProductModel{

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

	static final String TABLE_NAME = "prodotto";

	
	public synchronized int doSave(ProductBean product) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL;
		int code = 0;
		
		insertSQL= "INSERT INTO " + ProductModel.TABLE_NAME
				+ " (nome, descrizione, prezzo, quantita, categoria, sconto, iva) VALUES (?, ?, ?, ?, ?, ?, ?);";
			
		

		try {
			connection = ds.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
			
			preparedStatement.setString(1, product.getName());
			preparedStatement.setString(2, product.getDescription());
			preparedStatement.setDouble(3, product.getPrice());
			preparedStatement.setInt(4, product.getQuantity());
			preparedStatement.setInt(5, product.getCategoria());
			preparedStatement.setDouble(6, product.getSconto());
			preparedStatement.setDouble(7, product.getIva());
			
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
	
	
	public synchronized void doEdit(ProductBean product) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String editSQL = "UPDATE " + TABLE_NAME
				+ " SET nome = '"+product.getName()+"', descrizione='"+product.getDescription()+"', prezzo="+product.getPrice()+", quantita="+product.getQuantity()+", categoria="+product.getCategoria()+", sconto="+product.getSconto()+", iva="+product.getIva()+" "
						+ "WHERE id ="+product.getCode()+";";
			
		

		try {
			connection = ds.getConnection();
			connection.setAutoCommit(false);
			Statement statement=  connection.createStatement();
			statement.executeUpdate(editSQL);
			connection.commit();
			
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
	
	

	
	public synchronized ProductBean doRetrieveByKey(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ProductBean bean = new ProductBean();

		String selectSQL = "SELECT * FROM " + ProductModel.TABLE_NAME + " WHERE id = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setCode(rs.getInt("id"));
				bean.setName(rs.getString("nome"));
				bean.setDescription(rs.getString("descrizione"));
				bean.setPrice(rs.getInt("prezzo"));
				bean.setQuantity(rs.getInt("quantita"));
				bean.setCategoria(rs.getInt("categoria"));
				bean.setSconto(rs.getDouble("sconto"));
				bean.setSconto(rs.getDouble("iva"));

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
	
	
	public synchronized Collection<SearchResult> doRetrieveByName(String nome) throws SQLException {
		
		if(nome.compareTo("") == 0)
			return null;
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<SearchResult> products = new LinkedList<SearchResult>();

		String selectSQL = "SELECT id, nome FROM "+ TABLE_NAME + " where nome LIKE '%"+ nome +"%';"; 
		

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				SearchResult bean = new SearchResult();

				bean.setId(rs.getInt("id"));
				bean.setNome(rs.getString("nome"));
				
				products.add(bean);
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
		return products;
	}

	
	public synchronized boolean doDelete(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + ProductModel.TABLE_NAME + " WHERE id = ?";

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

	
	public synchronized Collection<ProductBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<ProductBean> products = new LinkedList<ProductBean>();

		String selectSQL = "SELECT * FROM "+ TABLE_NAME; 

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ProductBean bean = new ProductBean();

				bean.setCode(rs.getInt("id"));
				bean.setName(rs.getString("nome"));
				bean.setDescription(rs.getString("descrizione"));
				bean.setPrice(rs.getInt("prezzo"));
				bean.setQuantity(rs.getInt("quantita"));
				bean.setCategoria(rs.getInt("categoria"));
				bean.setSconto(rs.getDouble("sconto"));
				bean.setSconto(rs.getDouble("iva"));
				products.add(bean);
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
		return products;
	}

}