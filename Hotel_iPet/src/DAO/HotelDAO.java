package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Beans.HotelBeans;
import conexao.Conexao;

public class HotelDAO {
	
	public static ResultSet findAll(int one) {
		Conexao c = new Conexao();
		String q = "SELECT NM_CUIDADOR, DS_CUIDADOR, NS_CUIDADOR FROM cuidadores where CD_Cuidador > " + one + " order by CD_Cuidador";
		try {
			System.out.println(q);
			PreparedStatement ps = Conexao.conn.prepareStatement(q);
			return ps.executeQuery();
		} catch (SQLException e) {
			System.err.println("Cuidador não encontrado!");
			e.printStackTrace();
			return null;
		}
	}
}
