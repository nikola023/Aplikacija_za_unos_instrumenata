package paketKlasa;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import paketKorisnik.PostojeciKorisnik;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Login extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtUsername;
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { try { Login dialog = new Login();
	 * dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	 * dialog.setVisible(true); } catch (Exception e) { e.printStackTrace(); } }
	 */

	/**
	 * Create the dialog.
	 */
	public Login() {
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Runtime.getRuntime().exit(1);
			}
		});
		
		
		setBounds(100, 100, 400, 180);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);

		JLabel lblUsername = new JLabel("Korisnicko ime");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUsername.setHorizontalAlignment(SwingConstants.LEFT);
		lblUsername.setBounds(10, 15, 105, 30);
		contentPanel.add(lblUsername);

		txtUsername = new JTextField();
		txtUsername.setBounds(125, 15, 250, 30);
		contentPanel.add(txtUsername);
		txtUsername.setColumns(10);

		JLabel lblPassword = new JLabel("Lozinka");
		lblPassword.setHorizontalAlignment(SwingConstants.LEFT);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPassword.setBounds(10, 55, 105, 30);
		contentPanel.add(lblPassword);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 101, 434, 41);
			contentPanel.add(buttonPane);
			buttonPane.setLayout(null);
			{
				JButton btnLogin = new JButton("Prijava");
				btnLogin.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

						if (txtUsername.getText().equals("") || txtPassword.getPassword().toString().equals("")) {
							JOptionPane.showMessageDialog(null, "Unesite sve podatke!");
						} else {
							String x = new String(txtPassword.getPassword());
							PostojeciKorisnik pu = new PostojeciKorisnik(txtUsername.getText(), x);
							if (pu.isPostojiUser()) {

								JOptionPane.showMessageDialog(null, "Logovanje uspesno!");
								setVisible(false);
								MainMenu main = new MainMenu();
								main.setVisible(true);
								
							}
						}
					}
				});
				btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
				btnLogin.setBounds(50, 5, 80, 30);
				btnLogin.setActionCommand("OK");
				buttonPane.add(btnLogin);
				getRootPane().setDefaultButton(btnLogin);

			}
			{
				JButton btnPonisti = new JButton("Ponisti");
				btnPonisti.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						txtUsername.setText("");
						txtPassword.setText("");
					}
				});
				btnPonisti.setFont(new Font("Tahoma", Font.PLAIN, 15));
				btnPonisti.setBounds(155, 5, 80, 30);
				btnPonisti.setActionCommand("Cancel");
				buttonPane.add(btnPonisti);
			}
			
			JButton btnNazad = new JButton("Nazad");
			btnNazad.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					StartupMenu sm = new StartupMenu();
					setVisible(false);
					sm.setVisible(true);
					
				}
			});
			btnNazad.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnNazad.setActionCommand("Cancel");
			btnNazad.setBounds(260, 5, 80, 30);
			buttonPane.add(btnNazad);
		}

		txtPassword = new JPasswordField();
		txtPassword.setBounds(125, 55, 250, 30);
		contentPanel.add(txtPassword);
	}
}
