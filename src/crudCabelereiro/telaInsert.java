package crudCabelereiro;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class telaInsert extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodCliente;
	private JTextField txtNome;
	private JTextField txtTelefone;
	private JTextField txtEndereço;
	private JTextField txtHorario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaInsert frame = new telaInsert();
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
	public telaInsert() {
		Cabelereiro c = new Cabelereiro();
		CabelereiroDAO cD = new CabelereiroDAO();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 426, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		this.setLocationRelativeTo(null); /*CENTRALIZA O CONTAINER*/
		
		JLabel lblNewLabel = new JLabel("Codigo Cliente:");
		contentPane.add(lblNewLabel);
		
		txtCodCliente = new JTextField();
		contentPane.add(txtCodCliente);
		txtCodCliente.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nome:");
		contentPane.add(lblNewLabel_1);
		
		txtNome = new JTextField();
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblTelefone = new JLabel("Telefone");
		contentPane.add(lblTelefone);
		
		txtTelefone = new JTextField();
		txtTelefone.setColumns(10);
		contentPane.add(txtTelefone);
		
		JLabel lblEndereo = new JLabel("Endere\u00E7o:");
		contentPane.add(lblEndereo);
		
		txtEndereço = new JTextField();
		txtEndereço.setColumns(10);
		contentPane.add(txtEndereço);
		
		JLabel lblHorario = new JLabel("Horario:");
		contentPane.add(lblHorario);
		
		txtHorario = new JTextField();
		txtHorario.setColumns(10);
		contentPane.add(txtHorario);
		
		JButton btnInserir = new JButton("Inserir");
		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int codigo = Integer.parseInt(txtCodCliente.getText());
				String nome = txtNome.getText();
				String telefone = txtTelefone.getText();
				String endereco = txtEndereço.getText();
				String horario = txtHorario.getText();
				
				c.setCodCliente(codigo);
				c.setNome(nome);
				c.setTelefone(telefone);
				c.setEndereco(endereco);
				c.setHorario(horario);
				
				try {
					Conexao.conectar();
					cD.inserir(Conexao.getConnection(), c);
					Conexao.desconectar();
				} catch (NullPointerException | SQLException e2) {
					e2.printStackTrace();
				}
				
				JOptionPane.showMessageDialog(null, "Inserido");
			}
		});
		contentPane.add(btnInserir);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaCrud().setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnVoltar);
		this.setLocationRelativeTo(null); /*CENTRALIZA O CONTAINER*/
		
	}

}
