package paketKlasa;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class Prodaja extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtParametar;
	JLayeredPane layeredPane;
	private JTable Pretraga;
	private Racun r = new Racun();

	/**
	 * Launch the application.
	 */

	private void PopuniBezParametara() {
		String sql = "";
		DefaultTableModel tableModel = (DefaultTableModel) Pretraga.getModel();
		tableModel = new DefaultTableModel();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/seminarskioop", "root", "");
			Statement st = con.createStatement();
			String[] columnNames = { "naziv", "proizvodjac", "model", "materijal", "id", "kolicina", "cena" };

			tableModel.setColumnIdentifiers(columnNames);
			sql = "select * from instrument";

			ResultSet rs = st.executeQuery(sql);

			java.sql.ResultSetMetaData rsmd = rs.getMetaData();
			int colNo = rsmd.getColumnCount();
			while (rs.next()) {
				Object[] objects = new Object[colNo];
				for (int i = 0; i < colNo; i++) {
					objects[i] = rs.getObject(i + 1);

				}
				tableModel.addRow(objects);
			}
			Pretraga.setModel(tableModel);
			Pretraga.setDefaultEditor(Object.class, null);
			javax.swing.table.TableColumn col1 = Pretraga.getColumnModel().getColumn(1);
			col1.setPreferredWidth(120);
			javax.swing.table.TableColumn col2 = Pretraga.getColumnModel().getColumn(2);
			col2.setPreferredWidth(120);
			Pretraga.getTableHeader().setResizingAllowed(false);

			Pretraga.addMouseListener(new java.awt.event.MouseAdapter() {
				@Override
				public void mouseClicked(java.awt.event.MouseEvent evt) {
					int vrsta = Pretraga.getSelectedRow();

					String naziv = Pretraga.getModel().getValueAt(vrsta, 0).toString();
					String model = Pretraga.getModel().getValueAt(vrsta, 1).toString();
					String proizvodjac = Pretraga.getModel().getValueAt(vrsta, 2).toString();
					int kolicina = Integer.valueOf(Pretraga.getModel().getValueAt(vrsta, 5).toString());
					;
					int cena = Integer.valueOf(Pretraga.getModel().getValueAt(vrsta, 6).toString());
					r.DodajNaRacun(naziv, model, proizvodjac, kolicina, cena);
				}
			});

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Unesite validne podatke");
		}
	}

	private void PopuniSaParametrom() {

		String sql = "";
		DefaultTableModel tableModel = (DefaultTableModel) Pretraga.getModel();
		tableModel = new DefaultTableModel();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/seminarskioop", "root", "");
			Statement st = con.createStatement();
			String[] columnNames = { "naziv", "proizvodjac", "model", "materijal", "id", "kolicina", "cena" };

			tableModel.setColumnIdentifiers(columnNames);
			sql = "select * from instrument";
			if (txtParametar.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Unesite parametar za pretragu!");
			} else {
				if (Pattern.matches("[a-zA-Z]+", txtParametar.getText())) {
					sql = "select * from instrument where naziv = '" + txtParametar.getText() + "' or proizvodjac = '"
							+ txtParametar.getText() + "' or model = '" + txtParametar.getText() + "' or materijal = '"
							+ txtParametar.getText() + "' or id = '" + txtParametar.getText() + "'";
				} else {
					int kolicina = Integer.parseInt(txtParametar.getText());
					sql = "select * from instrument where lager = '" + kolicina + "'or id = '" + txtParametar.getText()
							+ "' or cena='" + txtParametar.getText() + "'";
				}
				ResultSet rs = st.executeQuery(sql);

				java.sql.ResultSetMetaData rsmd = rs.getMetaData();
				int colNo = rsmd.getColumnCount();
				while (rs.next()) {
					Object[] objects = new Object[colNo];
					for (int i = 0; i < colNo; i++) {
						objects[i] = rs.getObject(i + 1);

					}
					tableModel.addRow(objects);
				}
				Pretraga.setModel(tableModel);
				Pretraga.setDefaultEditor(Object.class, null);
				javax.swing.table.TableColumn col1 = Pretraga.getColumnModel().getColumn(1);
				col1.setPreferredWidth(120);
				javax.swing.table.TableColumn col2 = Pretraga.getColumnModel().getColumn(2);
				col2.setPreferredWidth(120);
				Pretraga.getTableHeader().setResizingAllowed(false);

				Pretraga.addMouseListener(new java.awt.event.MouseAdapter() {
					@Override
					public void mouseClicked(java.awt.event.MouseEvent evt) {
						int vrsta = Pretraga.getSelectedRow();

						String naziv = Pretraga.getModel().getValueAt(vrsta, 0).toString();
						String model = Pretraga.getModel().getValueAt(vrsta, 1).toString();
						String proizvodjac = Pretraga.getModel().getValueAt(vrsta, 2).toString();
						int kolicina = Integer.valueOf(Pretraga.getModel().getValueAt(vrsta, 5).toString());
						;
						int cena = Integer.valueOf(Pretraga.getModel().getValueAt(vrsta, 6).toString());
						r.DodajNaRacun(naziv, model, proizvodjac, kolicina, cena);
					}
				});

			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Unesite validne podatke");
		}

	}

	/**
	 * Create the dialog.
	 */
	public Prodaja() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Runtime.getRuntime().exit(1);
			}
		});
		setBounds(100, 100, 510, 350);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		this.setTitle("Prodavnica");
		r.setVisible(true);
		{
			JButton btnNazad = new JButton("Nazad");
			btnNazad.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					MainMenu m = new MainMenu();
					m.setVisible(true);
					r.dispose();
					dispose();
				}
			});
			btnNazad.setBounds(360, 270, 105, 31);
			contentPanel.add(btnNazad);
			btnNazad.setActionCommand("Cancel");
		}

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 263, 474, 2);
		contentPanel.add(separator);

		txtParametar = new JTextField();
		txtParametar.setBounds(250, 209, 215, 30);
		contentPanel.add(txtParametar);
		txtParametar.setColumns(10);

		JLabel lblNaziv = new JLabel("Parametar za pretragu");
		lblNaziv.setHorizontalAlignment(SwingConstants.CENTER);
		lblNaziv.setBounds(30, 209, 150, 30);
		lblNaziv.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		contentPanel.add(lblNaziv);

		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(null);
		}
		JButton btnPretrazi = new JButton("Pretraga");
		btnPretrazi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PopuniSaParametrom();
			}
		});
		btnPretrazi.setBounds(30, 270, 105, 30);
		contentPanel.add(btnPretrazi);
		btnPretrazi.setActionCommand("OK");
		getRootPane().setDefaultButton(btnPretrazi);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 474, 178);
		contentPanel.add(scrollPane);

		Pretraga = new JTable();

		Pretraga.setRowHeight(20);
		scrollPane.setViewportView(Pretraga);
		PopuniBezParametara();
	}
}
