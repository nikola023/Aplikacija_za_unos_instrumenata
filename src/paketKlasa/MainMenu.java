package paketKlasa;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public MainMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);

		// BACKGROUND
		ImageIcon img = new ImageIcon("imgFolder/mainIMG.jpg");
		JLabel background;

		JButton btnUnos = new JButton("Unos instrumenta");
		btnUnos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KreiranjeInstrumenta k = new KreiranjeInstrumenta();
				k.setModal(true);
				setVisible(false);
				k.setVisible(true);

			}
		});

		btnUnos.setBounds(30, 34, 150, 35);
		contentPane.add(btnUnos);

		JButton btnProdaja = new JButton("Prodaja");
		btnProdaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Prodaja p = new Prodaja();
				setVisible(false);
				p.setResizable(false);
				p.setLocationRelativeTo(null);
				p.setVisible(true);

			}
		});
		btnProdaja.setBounds(30, 100, 150, 35);
		contentPane.add(btnProdaja);
		
		JButton btnTransakcije = new JButton("Pregled transakcija");
		btnTransakcije.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Transakcije t = new Transakcije();
				t.setVisible(true);
			}
		});
		btnTransakcije.setBounds(30, 165, 150, 35);
		contentPane.add(btnTransakcije);
		
		JButton btnIzmenaBrisanje = new JButton("Izmena i brisanje");
		btnIzmenaBrisanje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IzmenaBrisanje ib = new IzmenaBrisanje();
				ib.setModal(true);
				ib.setVisible(true);
			}
		});
		btnIzmenaBrisanje.setBounds(264, 34, 150, 35);
		contentPane.add(btnIzmenaBrisanje);
		background = new JLabel("", img, JLabel.CENTER);
		background.setBackground(Color.WHITE);
		background.setBounds(0, 0, 444, 271);
		getContentPane().add(background);
	}
}
