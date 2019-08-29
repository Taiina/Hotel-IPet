package frames;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import DAO.HotelDAO;
import conexao.Conexao;

public class Cuidadores extends JFrame {

	private JPanel contentPane;
	private JTextField txtDs;
	private JButton btnVai;
	private JButton btnVolta;
	private JTextField txtNomeCliente;
	private MaskFormatter mascaras;
	private JFormattedTextField data;
	private JFormattedTextField data2;
	private JTextField txtNome;
	int one = -1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cuidadores frame = new Cuidadores();
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
	public Cuidadores() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 660, 440);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton sair = new JButton("");
		sair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		txtNomeCliente = new JTextField();
		txtNomeCliente.setFont(new Font("Lucida Bright", Font.PLAIN, 16));
		txtNomeCliente.setText("Digite seu nome...");
		txtNomeCliente.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtNomeCliente.setText("");
			}
		});

		// https://www.devmedia.com.br/java-swing-conheca-os-componentes-jtextfield-e-jformattedtextfield/30981
		try {
			mascaras = new MaskFormatter("##/##");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		JButton btnFinalizar = new JButton("Finalizar");
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome = txtNomeCliente.getText();
				String dataComeco = data.getText();
				String dataFinal = data2.getText();
				String cuidador = txtNome.getText();
				DAO.HotelDAO.Agendar(nome, dataComeco, dataFinal, cuidador);
				
			}
		});

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(424, 178, 191, 137);
		contentPane.add(scrollPane_1);

		txtDs = new JTextField();
		scrollPane_1.setViewportView(txtDs);
		txtDs.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(424, 139, 191, 28);
		contentPane.add(scrollPane);

		txtNome = new JTextField();
		scrollPane.setViewportView(txtNome);
		txtNome.setColumns(10);
		btnFinalizar.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		btnFinalizar.setBounds(182, 344, 101, 23);
		contentPane.add(btnFinalizar);

		btnVai = new JButton("");
		btnVai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				one++;
				ResultSet rs = HotelDAO.findAll(one);

				try {
					if (rs.next()) {
						String nome = rs.getString("NM_Cuidador");
						String ds = rs.getString("DS_Cuidador");

						txtNome.setText(nome);
						txtDs.setText(ds);

					} else {
						one = -1;
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnVai.setContentAreaFilled(false);
		btnVai.setOpaque(false);
		btnVai.setFocusPainted(false);
		btnVai.setBorderPainted(false);

		btnVai.setIcon(new ImageIcon(Cuidadores.class.getResource("/imagens/3847912-128(2).png")));
		btnVai.setBounds(523, 325, 89, 51);
		contentPane.add(btnVai);

		btnVolta = new JButton("");
		btnVolta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				one--;
				ResultSet rs = HotelDAO.findAll(one);

				try {
					if (rs.next()) {
						String nome = rs.getString("NM_Cuidador");
						String ds = rs.getString("DS_Cuidador");

						txtNome.setText(nome);
						txtDs.setText(ds);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}

		});
		btnVolta.setContentAreaFilled(false);
		btnVolta.setOpaque(false);
		btnVolta.setFocusPainted(false);
		btnVolta.setBorderPainted(false);

		btnVolta.setIcon(new ImageIcon(Cuidadores.class.getResource("/imagens/3847912-128(1).png")));
		btnVolta.setBounds(424, 325, 89, 51);
		contentPane.add(btnVolta);

		JLabel lblCuidadores = new JLabel("Cuidadores");
		lblCuidadores.setFont(new Font("Lucida Bright", Font.PLAIN, 20));
		lblCuidadores.setBounds(461, 108, 117, 20);
		contentPane.add(lblCuidadores);

		data2 = new JFormattedTextField(mascaras);
		data2.setFont(new Font("Lucida Bright", Font.PLAIN, 16));
		data2.setBounds(311, 166, 54, 19);
		contentPane.add(data2);
		
		data = new JFormattedTextField(mascaras);
		data.setFont(new Font("Lucida Bright", Font.PLAIN, 16));
		data.setBounds(209, 166, 54, 19);
		contentPane.add(data);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Cuidadores.class.getResource("/imagens/Sem t\u00EDtulo.png")));
		lblNewLabel.setBounds(399, 108, 2, 268);
		contentPane.add(lblNewLabel);
		txtNomeCliente.setBounds(166, 130, 210, 23);
		contentPane.add(txtNomeCliente);
		txtNomeCliente.setColumns(10);

		JLabel lblAt = new JLabel("at\u00E9");
		lblAt.setFont(new Font("Lucida Bright", Font.PLAIN, 20));
		lblAt.setBounds(273, 165, 36, 20);
		contentPane.add(lblAt);

		JLabel lblData = new JLabel("Data:   De");
		lblData.setFont(new Font("Lucida Bright", Font.PLAIN, 20));
		lblData.setBounds(97, 165, 100, 20);
		contentPane.add(lblData);

		JLabel label = new JLabel("Nome:");
		label.setFont(new Font("Lucida Bright", Font.PLAIN, 20));
		label.setBounds(97, 131, 73, 20);
		contentPane.add(label);
		sair.setContentAreaFilled(false);
		sair.setOpaque(false);
		sair.setFocusPainted(false);
		sair.setBorderPainted(false);
		sair.setIcon(new ImageIcon(Cuidadores.class.getResource("/imagens/183189-128(1).png")));
		sair.setBounds(10, 325, 63, 51);
		contentPane.add(sair);

		JLabel lblCuidador = new JLabel("Hotel IPet");
		lblCuidador.setFont(new Font("Bauhaus 93", Font.PLAIN, 50));
		lblCuidador.setBounds(231, 37, 247, 65);
		contentPane.add(lblCuidador);

		JLabel background = new JLabel("");
		background.setIcon(new ImageIcon(Cuidadores.class.getResource("/imagens/Background.jpg")));
		background.setBounds(0, 0, 644, 401);
		contentPane.add(background);
	}

}