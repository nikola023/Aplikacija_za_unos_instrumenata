package paketKlasa;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class StartupMenu extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public void sakrij() {
		this.setVisible(false);
	}

	/**
	 * Launch the application.
	 * 
	 * @throws SQLException
	 */
	public static void main(String[] args) throws SQLException {
		SQLNaredbe s = new SQLNaredbe();
		s.KreirajBazu();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					StartupMenu frame = new StartupMenu();
					frame.setVisible(true);
					// frame.setModal(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public StartupMenu() {

		JLabel background;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 620, 465);
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnRegister = new JButton("Registracija");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Register r = new Register();
				r.setModal(true);
				setVisible(false);
				r.setVisible(true);

			}
		});
		btnRegister.setFont(new Font("Algerian", Font.BOLD, 16));
		btnRegister.setBounds(390, 290, 160, 40);
		contentPane.add(btnRegister);

		// WELCOME lbl
		JLabel lblWelcome = new JLabel("Dobrodo\u0161li u Music Shop");
		lblWelcome.setVerticalAlignment(SwingConstants.BOTTOM);
		lblWelcome.setBackground(new Color(255, 255, 255));
		lblWelcome.setForeground(new Color(0, 0, 255));
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setFont(new Font("Algerian", Font.BOLD, 28));
		lblWelcome.setBounds(10, 0, 594, 50);
		contentPane.add(lblWelcome);

		// BACKGROUND
		ImageIcon img = new ImageIcon("imgFolder/mainIMG.jpg");

		JLabel lblNewLabel = new JLabel("Kupovina i rezervacija instrumenata i opreme na jednom mestu");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Algerian", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 61, 594, 30);
		contentPane.add(lblNewLabel);

		JButton btnLogin = new JButton("Prijava");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Login l = new Login();
				l.setModal(true);
				setVisible(false);
				l.setVisible(true);

			}
		});
		btnLogin.setFont(new Font("Algerian", Font.BOLD, 16));
		btnLogin.setBounds(60, 290, 160, 40);
		contentPane.add(btnLogin);
		background = new JLabel("", img, JLabel.CENTER);
		background.setBackground(Color.WHITE);
		background.setBounds(0, 0, this.getWidth(), this.getHeight());
		getContentPane().add(background);

	}
}
