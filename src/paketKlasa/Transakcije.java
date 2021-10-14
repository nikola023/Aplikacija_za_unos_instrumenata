package paketKlasa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class Transakcije extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable Transakcije;

	private void PrikaziTabelu() {

		DefaultTableModel tableModel = (DefaultTableModel) Transakcije.getModel();
		tableModel = new DefaultTableModel();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/seminarskioop", "root", "");
			Statement st = con.createStatement();
			String[] columnNames = { "naziv", "proizvodjac", "model", "kolicina", "oprema", "kolicina",
					"ukupno", "datum" };
			tableModel.setColumnIdentifiers(columnNames);
			
			String sql = "select * from prodaja";
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
			Transakcije.setModel(tableModel);
			Transakcije.setDefaultEditor(Object.class, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public Transakcije() {
		setBounds(100, 100, 600, 325);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setModal(true);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 564, 264);
		contentPane.add(scrollPane);
		
		Transakcije = new JTable();
		Transakcije.setRowHeight(20);
		scrollPane.setViewportView(Transakcije);
		PrikaziTabelu();
		
	}
}
