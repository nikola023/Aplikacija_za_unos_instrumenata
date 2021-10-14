package paketKlasa;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import paketKorisnik.NoviKorisnik;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.util.regex.Pattern;

public class Register extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtIme;
	private JTextField txtPrezime;
	private JTextField txtJmbg;
	private JTextField txtDrzava;
	private JTextField txtGrad;
	private JTextField txtUsername;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */

	public void cistac() {
		txtIme.setText("");
		txtPrezime.setText("");
		txtJmbg.setText("");
		txtDrzava.setText("");
		txtGrad.setText("");
		txtUsername.setText("");
		passwordField.setText("");
	}

	public Register() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Runtime.getRuntime().exit(1);
			}
		});
		
		setBounds(100, 100, 450, 500);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		{
			JLabel lblNaslov = new JLabel("Registracija");
			lblNaslov.setFont(new Font("Algerian", Font.BOLD, 18));
			lblNaslov.setHorizontalAlignment(SwingConstants.CENTER);
			lblNaslov.setBounds(10, 11, 414, 35);
			contentPanel.add(lblNaslov);
		}
		{
			JLabel lblIme = new JLabel("Ime");
			lblIme.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblIme.setHorizontalAlignment(SwingConstants.LEFT);
			lblIme.setBounds(25, 65, 80, 25);
			contentPanel.add(lblIme);
		}
		{
			txtIme = new JTextField();
			txtIme.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txtIme.setBounds(150, 65, 160, 25);
			contentPanel.add(txtIme);
			txtIme.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 411, 434, 50);
			contentPanel.add(buttonPane);
			buttonPane.setLayout(null);
			{
				JButton btnRegister = new JButton("Registruj se");
				btnRegister.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String ime = txtIme.getText().toString();
						String prezime = txtPrezime.getText();
						String id = txtJmbg.getText();
						String drzava = txtDrzava.getText();
						String grad = txtGrad.getText();
						String username = txtUsername.getText();
						String password = new String(passwordField.getPassword());
						if (ime.equals("") || prezime.equals("") || id.equals("") || drzava.equals("")
								|| grad.equals("") || username.equals("") || password.equals("")) {
							JOptionPane.showMessageDialog(null, "Unesite sve podatke!");
						} else {
							if (Pattern.matches("[a-zA-Z]+", ime) && Pattern.matches("[a-zA-Z]+", prezime)
									&& Pattern.matches("[a-zA-Z]+", drzava) && Pattern.matches("[a-zA-Z]+", grad)) {
								try {
									long jmbg = Long.parseLong(id);
									NoviKorisnik nu = new NoviKorisnik(ime, prezime, jmbg, drzava, grad, username, password);
									if (nu.isRegistrovan()) {
										cistac();
										MainMenu main = new MainMenu();
										setVisible(false);
										main.setVisible(true);
										
									}
								} catch (NumberFormatException nfe) {
									JOptionPane.showMessageDialog(null, "JMBG mora biti broj!");
								}
							} else {
								JOptionPane.showMessageDialog(null,
										"Ime, prezime, drzava i grad ne smeju sadrzati brojeve!");
							}

						}

					}
				});
				btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 14));
				btnRegister.setBounds(30, 10, 110, 30);
				btnRegister.setActionCommand("OK");
				buttonPane.add(btnRegister);
				getRootPane().setDefaultButton(btnRegister);
			}
			{
				JButton btnCancel = new JButton("Ponisti");
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cistac();
					}
				});
				btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 14));
				btnCancel.setBounds(160, 10, 110, 30);
				btnCancel.setActionCommand("Cancel");
				buttonPane.add(btnCancel);
			}
			
			JButton btnNazad = new JButton("Nazad");
			btnNazad.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					StartupMenu sm = new StartupMenu();
					setVisible(false);
					sm.setVisible(true);
				}
			});
			btnNazad.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnNazad.setActionCommand("Cancel");
			btnNazad.setBounds(290, 10, 110, 30);
			buttonPane.add(btnNazad);
		}
		{
			JLabel lblPrezime = new JLabel("Prezime");
			lblPrezime.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblPrezime.setHorizontalAlignment(SwingConstants.LEFT);
			lblPrezime.setBounds(25, 105, 80, 25);
			contentPanel.add(lblPrezime);
		}
		{
			txtPrezime = new JTextField();
			txtPrezime.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txtPrezime.setColumns(10);
			txtPrezime.setBounds(150, 105, 160, 25);
			contentPanel.add(txtPrezime);
		}

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 44, 415, 1);
		contentPanel.add(separator);

		JLabel lblJmbg = new JLabel("JMBG");
		lblJmbg.setHorizontalAlignment(SwingConstants.LEFT);
		lblJmbg.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblJmbg.setBounds(25, 145, 80, 25);
		contentPanel.add(lblJmbg);

		txtJmbg = new JTextField();
		txtJmbg.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtJmbg.setColumns(10);
		txtJmbg.setBounds(150, 145, 160, 25);
		contentPanel.add(txtJmbg);

		JLabel lblDrzava = new JLabel("Drzava");
		lblDrzava.setHorizontalAlignment(SwingConstants.LEFT);
		lblDrzava.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDrzava.setBounds(25, 185, 80, 25);
		contentPanel.add(lblDrzava);

		txtDrzava = new JTextField();
		txtDrzava.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtDrzava.setColumns(10);
		txtDrzava.setBounds(150, 185, 160, 25);
		contentPanel.add(txtDrzava);

		JLabel lblGrad = new JLabel("Grad");
		lblGrad.setHorizontalAlignment(SwingConstants.LEFT);
		lblGrad.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGrad.setBounds(25, 225, 110, 25);
		contentPanel.add(lblGrad);

		txtGrad = new JTextField();
		txtGrad.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtGrad.setColumns(10);
		txtGrad.setBounds(150, 225, 160, 25);
		contentPanel.add(txtGrad);

		JLabel lblUsername = new JLabel("Korisnicko ime");
		lblUsername.setHorizontalAlignment(SwingConstants.LEFT);
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsername.setBounds(25, 265, 110, 25);
		contentPanel.add(lblUsername);

		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtUsername.setColumns(10);
		txtUsername.setBounds(150, 265, 160, 25);
		contentPanel.add(txtUsername);

		JLabel lblPassword = new JLabel("Lozinka");
		lblPassword.setHorizontalAlignment(SwingConstants.LEFT);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(25, 305, 110, 25);
		contentPanel.add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.setBounds(150, 305, 160, 25);
		contentPanel.add(passwordField);

		JCheckBox chckbxShowPassword = new JCheckBox("Prikazi lozinku");
		passwordField.setEchoChar('*');
		chckbxShowPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxShowPassword.isSelected()) {
					passwordField.setEchoChar((char) 0);
				} else {
					passwordField.setEchoChar('*');
				}
			}
		});
		chckbxShowPassword.setBounds(316, 305, 104, 25);
		contentPanel.add(chckbxShowPassword);
	}
}
