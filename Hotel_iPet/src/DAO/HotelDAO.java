package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Beans.HotelBeans;
import conexao.Conexao;

public class HotelDAO {
	
	public static ResultSet findAll() {
		String q = "SELECT NM_CUIDADOR, DS_CUIDADOR, NS_CUIDADOR FROM cuidadores";
		try {
			PreparedStatement ps = Conexao.conn.prepareStatement(q);
			return ps.executeQuery();
		} catch (SQLException e) {
			System.err.println("Cuidador não encontrado!");
			e.printStackTrace();
			return null;
		}
	}
}
