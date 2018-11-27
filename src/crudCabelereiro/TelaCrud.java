package crudCabelereiro;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class TelaCrud extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCrud frame = new TelaCrud();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaCrud() {
		CabelereiroDAO cD = new CabelereiroDAO();
		Cabelereiro c = new Cabelereiro();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		this.setLocationRelativeTo(null); /*CENTRALIZA O CONTAINER*/
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new telaInsert().setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnInsert);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Conexao.conectar();
					int codigoDeletado = Integer.parseInt(JOptionPane.showInputDialog("Digite o codigo para ser deletado"));
					c.setCodCliente(codigoDeletado);
					cD.delete(Conexao.getConnection(), c);
					JOptionPane.showMessageDialog(null, "Deletado");
					Conexao.desconectar();
				} catch (NullPointerException | SQLException e2) {
					e2.printStackTrace();
				}
			}
		});
		contentPane.add(btnDelete);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Conexao.conectar();
					String novoTelefone = JOptionPane.showInputDialog("Digite o novo telefone");
					int codigo = Integer.parseInt(JOptionPane.showInputDialog("Digite o codigo do cliente"));
					c.setCodCliente(codigo);
					cD.update(Conexao.getConnection(), novoTelefone, c);
					JOptionPane.showMessageDialog(null, "Atualizado");
					Conexao.desconectar();
				} catch (NullPointerException | SQLException e2) {
					e2.printStackTrace();
				}
			}
		});
		contentPane.add(btnUpdate);
		
		JButton btnSelect = new JButton("Select");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Conexao.conectar();
					cD.select(c);
					Conexao.desconectar();
				} catch (NullPointerException | SQLException e2) {
					e2.printStackTrace();
				}
			}
		});
		contentPane.add(btnSelect);
		
	}

}
