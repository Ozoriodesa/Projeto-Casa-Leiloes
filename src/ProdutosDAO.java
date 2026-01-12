/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.SQLException;


public class ProdutosDAO {
    
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }
    
    
    public boolean cadastrarProduto(ProdutosDTO produto) {
  
        Connection con = conectaDAO.getConnection();
        
        if (con == null) {
            System.out.println("Conexão NULL no DAO");
            return false;
        }
      try {    
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, produto.getNome());
        pst.setDouble(2, produto.getValor());
        pst.setString(3, produto.getStatus());
        pst.execute();
        
        return true;
        
    } catch (SQLException e) {
        System.out.println("Erro SQL: " + e.getMessage());
        return false;
    }
}

    

}
    

