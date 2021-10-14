package paketKlasa;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import paketInstrument.Oprema;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.LocalDate;
import java.awt.event.ActionEvent;

public class Racun extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable RacunTabela;
	DefaultTableModel tableModel;
	private JLabel lblUkupno;
	private JLabel lblCena;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */

	private void upisUBazu() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/seminarskioop", "root", "");
			Statement st = (Statement) con.createStatement();
			String naziv;
			String proizvodjac;
			String model;
			String oprema;

			for (int i = 0; i < tableModel.getRowCount(); i++) {
				naziv = (String) tableModel.getValueAt(i, 0);
				proizvodjac = (String) tableModel.getValueAt(i, 1);
				model = (String) tableModel.getValueAt(i, 2);
				
				int ukupnaCena = 0;
				int kolicinaInstrumenata = (int) tableModel.getValueAt(i, 3);
				int kolicinaOpreme = (int) tableModel.getValueAt(i, 6);
				ukupnaCena += izracunajUkupno() + (kolicinaOpreme*Integer.valueOf(tableModel.getValueAt(i, 7).toString()));
				
				oprema = (String) tableModel.getValueAt(i, 5);
				String updateMain = "update instrument set lager = lager - '" + kolicinaInstrumenata
						+ "' where naziv = '" + naziv + "' and proizvodjac = '" + proizvodjac + "' and model = '"
						+ model + "'";
				st.executeUpdate(updateMain);
				LocalDate datumProdaje = LocalDate.now();
				String sql = "insert into prodaja(naziv, proizvodjac, model, kolicina, oprema, kolicinaOpreme, ukupnaCena, datumProdaje)"
						+ " values('" + naziv + "','" + proizvodjac + "','" + model + "','" + kolicinaInstrumenata
						+ "','" + oprema + "','" + kolicinaOpreme + "','" + ukupnaCena + "','" + datumProdaje + "')";
				st.executeUpdate(sql);
			}

			JOptionPane.showMessageDialog(null, "Transakcija je uspesno izvrsena!");

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	private int izracunajUkupno() {
		int kol = 0;
		int cen = 0;
		int ukupno = 0;
		for (int i = 0; i < tableModel.getRowCount(); i++) {
			kol = (int) tableModel.getValueAt(i, 3);
			cen = (int) tableModel.getValueAt(i, 4);
			ukupno += kol * cen;
		}
		return ukupno;
	}

	public void DodajNaRacun(String naziv, String model, String proizvodjac, int kolicina, int cena) {
		tableModel = (DefaultTableModel) RacunTabela.getModel();
		String[] columnNames = { "naziv", "proizvodjac", "model", "kolicina", "cena", "oprema", "kolicina",
				"cena" };
		tableModel.setColumnIdentifiers(columnNames);
		for (int i = 0; i < tableModel.getRowCount(); i++) {
			if (tableModel.getValueAt(i, 0) == naziv && tableModel.getValueAt(i, 1) == model
					&& tableModel.getValueAt(i, 2) == proizvodjac) {
				JOptionPane.showMessageDialog(null, "Vec je unet instrument!");
				return;
			}
		}

		try {
			int tryKolicina = Integer.parseInt(JOptionPane.showInputDialog("Kolicina"));
			if (tryKolicina > kolicina) {
				JOptionPane.showMessageDialog(null, "Na lageru nema toliko instrumenata!");
				return;
			}
			kolicina = tryKolicina;
			tableModel.addRow(new Object[] { naziv, model, proizvodjac, kolicina, cena });

			int ukupno = izracunajUkupno();

			lblCena.setText(ukupno + " RSD");

			int oprema = JOptionPane.showConfirmDialog(null, "Da li zelite dodatnu opremu?", null,
					JOptionPane.YES_NO_OPTION);
			if (oprema == JOptionPane.YES_OPTION) {
				Oprema o = new Oprema(naziv.toString());
				int opremaKolicina = Integer.parseInt(JOptionPane.showInputDialog("Kolicina"));
				int cenaOpreme = opremaKolicina * o.getCena();

				tableModel.setValueAt(o.getNaziv(), (tableModel.getRowCount() - 1), 5);
				tableModel.setValueAt(opremaKolicina, (tableModel.getRowCount() - 1), 6);
				tableModel.setValueAt(o.getCena(), (tableModel.getRowCount() - 1), 7);
				lblCena.setText((ukupno + cenaOpreme) + " RSD");

			}
			else {
				tableModel.setValueAt("nema", (tableModel.getRowCount() - 1), 5);
				tableModel.setValueAt(0, (tableModel.getRowCount() - 1), 6);
				tableModel.setValueAt(0, (tableModel.getRowCount() - 1), 7);
			}

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Unesite broj!");
		}

	}

	public Racun() {

		setBounds(100, 100, 600, 380);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		this.setTitle("Racun");
		{
			JButton btnPlati = new JButton("Plati");
			btnPlati.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (tableModel.getRowCount() > 0) {
						upisUBazu();
						tableModel.setRowCount(0);
						int ukupno = izracunajUkupno();
						lblCena.setText(ukupno + " RSD");
					} else {
						JOptionPane.showMessageDialog(null, "Na racunu mora biti barem 1 instrument!");
					}

				}
			});
			btnPlati.setBounds(10, 300, 100, 30);
			contentPanel.add(btnPlati);
		}
		{
			JButton btnOdustani = new JButton("Odustani");
			btnOdustani.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					tableModel.setRowCount(0);
					int ukupno = izracunajUkupno();
					lblCena.setText(ukupno + " RSD");
				}
			});
			btnOdustani.setBounds(474, 300, 100, 30);
			contentPanel.add(btnOdustani);
		}

		JLabel lblRacun = new JLabel("Racun");
		lblRacun.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblRacun.setHorizontalAlignment(SwingConstants.CENTER);
		lblRacun.setBounds(10, 11, 564, 20);
		contentPanel.add(lblRacun);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 287, 564, 2);
		contentPanel.add(separator);

		lblUkupno = new JLabel("Ukupno:");
		lblUkupno.setBounds(371, 256, 55, 20);
		contentPanel.add(lblUkupno);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 40, 564, 205);
		contentPanel.add(scrollPane);
		RacunTabela = new JTable();

		RacunTabela.setRowHeight(20);
		scrollPane.setViewportView(RacunTabela);
		RacunTabela.getTableHeader().setResizingAllowed(false);

		lblCena = new JLabel("");
		lblCena.setBounds(436, 256, 138, 20);
		contentPanel.add(lblCena);

		JButton btnUkloni = new JButton("Ukloni");
		btnUkloni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableModel.setRowCount(0);
				int ukupno = izracunajUkupno();
				lblCena.setText(ukupno + " RSD");
			}
		});
		btnUkloni.setBounds(235, 300, 100, 30);
		contentPanel.add(btnUkloni);

	}
}
