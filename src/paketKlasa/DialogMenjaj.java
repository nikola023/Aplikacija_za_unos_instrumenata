package paketKlasa;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class DialogMenjaj extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel txtNaziv;
	private JTextField txtProizvodjac;
	private JTextField txtModel;
	private JTextField txtMaterijal;
	private JTextField txtLager;
	private JTextField txtCena;
	private String id;
	@SuppressWarnings("unused")
	private String prviNaziv;
	@SuppressWarnings("unused")
	private String prviProiz;
	@SuppressWarnings("unused")
	private String prviModel;
	@SuppressWarnings("unused")
	private String prviMaterijal;
	@SuppressWarnings("unused")
	private int prviLager;
	@SuppressWarnings("unused")
	private int prvaCena;

	public void PopuniTxt(String naziv, String proiz, String model, String materijal, int lager, int cena, String id) {
		txtNaziv.setText(naziv);
		txtProizvodjac.setText(proiz);
		txtModel.setText(model);
		txtMaterijal.setText(materijal);
		txtLager.setText(String.valueOf(lager));
		txtCena.setText(String.valueOf(cena));
		this.id = id;
		this.prviNaziv = naziv;
		this.prviProiz = proiz;
		this.prviModel = model;
		this.prviMaterijal = materijal;
		this.prviLager = lager;
		this.prvaCena = cena;
	}

	public DialogMenjaj() {

		setBounds(100, 100, 450, 451);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		

		txtNaziv = new JLabel();
		txtNaziv.setBounds(100, 25, 175, 30);
		contentPanel.add(txtNaziv);

		txtProizvodjac = new JTextField();
		txtProizvodjac.setColumns(10);
		txtProizvodjac.setBounds(100, 85, 175, 30);
		contentPanel.add(txtProizvodjac);

		txtModel = new JTextField();
		txtModel.setColumns(10);
		txtModel.setBounds(100, 145, 175, 30);
		contentPanel.add(txtModel);

		txtMaterijal = new JTextField();
		txtMaterijal.setColumns(10);
		txtMaterijal.setBounds(100, 205, 175, 30);
		contentPanel.add(txtMaterijal);

		txtLager = new JFormattedTextField();
		txtLager.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char c = evt.getKeyChar();
				if (!Character.isDigit(c)) {
					evt.consume();
				}
			}
		});
		txtLager.setColumns(10);
		txtLager.setBounds(100, 265, 115, 30);
		contentPanel.add(txtLager);

		txtCena = new JFormattedTextField();
		txtCena.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char c = evt.getKeyChar();
				if (!Character.isDigit(c)) {
					evt.consume();
				}
			}
		});
		txtCena.setColumns(10);
		txtCena.setBounds(100, 325, 115, 30);
		contentPanel.add(txtCena);

		JLabel lblNaziv = new JLabel("Naziv");
		lblNaziv.setBounds(10, 25, 80, 30);
		contentPanel.add(lblNaziv);

		JLabel lblProizvodjac = new JLabel("Proizvodjac");
		lblProizvodjac.setBounds(10, 85, 80, 30);
		contentPanel.add(lblProizvodjac);

		JLabel lblModel = new JLabel("Model");
		lblModel.setBounds(10, 145, 80, 30);
		contentPanel.add(lblModel);

		JLabel lblMaterijal = new JLabel("Materijal");
		lblMaterijal.setBounds(10, 205, 80, 30);
		contentPanel.add(lblMaterijal);

		JLabel lblLager = new JLabel("Lager");
		lblLager.setBounds(10, 265, 80, 30);
		contentPanel.add(lblLager);

		JLabel lblCena = new JLabel("Cena");
		lblCena.setBounds(10, 325, 80, 30);
		contentPanel.add(lblCena);
		
		JLabel lblNewLabel = new JLabel("RSD");
		lblNewLabel.setBounds(218, 325, 46, 30);
		contentPanel.add(lblNewLabel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							Class.forName("com.mysql.jdbc.Driver");
							Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/seminarskioop",
									"root", "");
							Statement st = (Statement) con.createStatement();

							if (txtNaziv.getText().toString().equals("")
									|| txtProizvodjac.getText().toString().equals("")
									|| txtModel.getText().toString().equals("")
									|| txtMaterijal.getText().toString().equals("")
									|| txtLager.getText().toString().equals("")
									|| txtCena.getText().toString().equals("")) {

								JOptionPane.showMessageDialog(null, "Unesite sve podatke!");
							} else {
								String sql = "update instrument set proizvodjac = '"
										+ txtProizvodjac.getText().toString() + "',model = '"
										+ txtModel.getText().toString() + "', materijal = '"
										+ txtMaterijal.getText().toString() + "',lager = '"
										+ Integer.valueOf(txtLager.getText()) + "', cena = '"
										+ Integer.valueOf(txtCena.getText()) + "' where id = '"+id+"'";

								st.executeUpdate(sql);
								JOptionPane.showMessageDialog(null, "Podaci su uspesno izmenjeni!");
							}

						} catch (Exception e) {
							e.printStackTrace();
						}

					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						txtNaziv.setText(prviNaziv);
						txtProizvodjac.setText(prviProiz);
						txtModel.setText(prviModel);
						txtMaterijal.setText(prviMaterijal);
						txtLager.setText(String.valueOf(prviLager));
						txtCena.setText(String.valueOf(prvaCena));
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
