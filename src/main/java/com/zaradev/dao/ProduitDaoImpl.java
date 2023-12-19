package com.zaradev.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zaradev.bo.Produit;

public class ProduitDaoImpl implements IProduitDao{

	@Override
	public Produit save(Produit produit) {
		Connection connection = SingletonConnection.getConnection();
		try {
			
			PreparedStatement ps = connection.prepareStatement("INSERT INTO PRODUITS (DESIGNATION, PRIX, QUANTITE) VALUES(?,?,?)");
			ps.setString(1, produit.getDesignation());
			ps.setDouble(2,  produit.getPrix());
			ps.setInt(3, produit.getQuantite());
			
			ps.executeUpdate();
			ps.close();
			
			PreparedStatement ps2 = connection.prepareStatement("SELECT MAX(ID) AS MAX_ID FROM PRODUITS");
			ResultSet rs = ps2.executeQuery();
			
			if(rs.next()) {
				produit.setId(rs.getLong("MAX_ID"));
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return produit;
	}

	@Override
	public List<Produit> produitParMC(String mc) {
		List<Produit> produits = new ArrayList<>();
		Connection connection = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM PRODUITS WHERE DESIGNATION LIKE ?");
			ps.setString(1, mc);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Produit produit = new Produit();
				produit.setId(rs.getLong("ID"));
				produit.setDesignation(rs.getString("DESIGNATION"));
				produit.setPrix(rs.getDouble("PRIX"));
				produit.setQuantite(rs.getInt("QUANTITE"));
				
				produits.add(produit);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return produits;
	}

	@Override
	public Produit getProduit(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Produit update(Produit produit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteProduit(Long id) {
		// TODO Auto-generated method stub
		
	}

}
