package crudCabelereiro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;



public class CabelereiroDAO {
	
	//CLASSE DAO AQUI INSERIMOS OS METODOS NECESSÁRIOS
	
	//MÉTODO DE INSERT
	public void inserir(Connection conn, Cabelereiro c) {
		String 
			sqlInsert = "INSERT INTO cliente "
					+ "(codCliente, nome, telefone, endereco, horario) values (?,?,?,?,?)";
		try(PreparedStatement stm = conn.prepareStatement(sqlInsert);){
			stm.setInt(1, c.getCodCliente());
			stm.setString(2, c.getNome());
			stm.setString(3, c.getTelefone());
			stm.setString(4, c.getEndereco());
			stm.setString(5, c.getHorario());
			stm.execute();
		}catch(Exception e){
			e.printStackTrace();
			String msg = e.getMessage();
			System.out.println(msg);
			try {
				conn.rollback();
			} catch(Exception e1) {
				System.out.println(e1.getMessage());
			}
		}
		
	}
	
	
	//METODO DE DELETE
	
	public void delete(Connection conn, Cabelereiro c) {
		String sqlDelete = "DELETE FROM cliente WHERE codCliente = ?";
		
		try(PreparedStatement stm = conn.prepareStatement(sqlDelete);){
			stm.setInt(1, c.getCodCliente());
			stm.execute();
		}catch(Exception e){
			e.printStackTrace();
			try {
				conn.rollback();
			}catch(Exception e1){
				String msg = e1.getMessage();
				System.out.println("Deu erro:  " + msg);
			}
		}
	}
	
	//METODO DE UPDATE
	public void update(Connection conn, String novoTelefone, Cabelereiro c) {
		String sqlUpdate = 
					"UPDATE cliente set telefone = ? where codCliente = ?";
		try(PreparedStatement stm = conn.prepareStatement(sqlUpdate);){
			stm.setString(1, novoTelefone);
			stm.setInt(2, c.getCodCliente());
			stm.execute();
		}catch(Exception e){
			e.printStackTrace();
			try {
				conn.rollback();
			}catch(Exception e1) {
				String msg = e1.getMessage();
				System.out.println(msg);
			}
		}
	}
	
	
	//MÉTODO DE SELECT
	
	public void select(Cabelereiro c) {
		Conexao conection = new Conexao();
		PreparedStatement stmt = null;
		String sqlInsert = "SELECT codCliente, nome, telefone, endereco, horario from cliente";

		try {
			Connection conn = conection.conectar();
			stmt = conn.prepareStatement(sqlInsert);
			ResultSet result = stmt.executeQuery();
			ResultSet rs = stmt.getResultSet();
			while (result.next()) {
				JOptionPane.showMessageDialog(null,
						rs.getInt("codCliente") + ", Nome: " + rs.getString("nome") + ", telefone: "
								+ rs.getString("telefone") + ", endereco: " + rs.getString("endereco")
								+ ", horario: " + rs.getString("horario"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e);

		} catch (Exception e) {
			try {
				conection.desconectar();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			System.out.println(e);
		}
	}
	
}
