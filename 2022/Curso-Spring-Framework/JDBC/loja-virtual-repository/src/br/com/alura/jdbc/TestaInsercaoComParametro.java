package br.com.alura.jdbc;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestaInsercaoComParametro {
	public static void main(String[] args) throws SQLException {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		try(Connection con = connectionFactory.recuperarConexao()){
		
			con.setAutoCommit(false);
			
			try (
					// Prepared Statement evita SQLInjections sem precisar de qualquer tipo de validação de caracteres, apenas dos métodos dessa Interface
					// Ele vai sanitizar(tratar) a consulta, deixando apenas o que foi inserido no prepareStatement para consultar/inserir
					PreparedStatement stm = con.prepareStatement("INSERT INTO PRODUTO (nome, descricao) VALUES (?, ?)",
							Statement.RETURN_GENERATED_KEYS);
				){
				adicionarVariavel("SmartTV", "45 polegadas", stm);
				adicionarVariavel("Radio", "Radio de bateria", stm);
			
				con.commit();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("ROLLBACK EXECUTADO");
				con.rollback();
			}
		}
	}

	private static void adicionarVariavel(String nome, String descricao, PreparedStatement stm) throws SQLException {
		stm.setString(1, nome);
		stm.setString(2, descricao);
		
		
		if(nome.equals("Radio")) { 
			throw new RuntimeException("Não foi possível adicionar o produto"); 
		}

		boolean resultado = stm.execute();
		
		try(ResultSet rst = stm.getGeneratedKeys()){
			while(rst.next()) {
				Integer id = rst.getInt(1);
				System.out.println("O id criado foi: " + id);
			}
		}
	}
}
