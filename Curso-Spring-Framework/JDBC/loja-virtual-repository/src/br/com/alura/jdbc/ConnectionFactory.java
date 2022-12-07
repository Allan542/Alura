package br.com.alura.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {
	
	public DataSource dataSource;
	
	public ConnectionFactory() {
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost/loja_virtual?useTimezone=true&serverTimezone=UTC");
		comboPooledDataSource.setUser("root");
		comboPooledDataSource.setPassword("");
		
		comboPooledDataSource.setMaxPoolSize(15);
		
		this.dataSource = comboPooledDataSource;
	}
	
	public Connection recuperarConexao() throws SQLException {
//		Connection con = DriverManager
//				.getConnection("jdbc:mysql://localhost/loja_virtual?useTimezone=true&serverTimezone=UTC", "root", "");
//		// jdbc:SQL(ora,mysql)://IP(127.0.0.1)/Database?Requests
//		return con;
		return dataSource.getConnection();
	}
}
