package paketKlasa;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class IzmenaBrisanje extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable Tabela;

	private void PrikaziTabelu() {
		DefaultTableModel tableModel = (DefaultTableModel) Tabela.getModel();
		tableModel = new DefaultTableModel();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/seminarskioop", "root", "");
			Statement st = con.createStatement();
			String[] columnNames = { "naziv", "proizvodjac", "model", "materijal", "id", "lager", "cena" };
			tableModel.setColumnIdentifiers(columnNames);

			String sql = "select * from instrument";
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
			Tabela.setModel(tableModel);
			Tabela.setDefaultEditor(Object.class, null);

			Tabela.addMouseListener(new java.awt.event.MouseAdapter() {
				@Override
				public void mouseClicked(java.awt.event.MouseEvent evt) {
					try {
						Class.forName("com.mysql.jdbc.Driver");
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/seminarskioop",
								"root", "");
						Statement st = (Statement) con.createStatement();
						ResultSet rs = null;
						String sql = null;
						int vrsta = Tabela.getSelectedRow();
						String[] options = new String[2];
						options[0] = new String("Izmeni");
						options[1] = new String("Izbrisi");

						String naziv = Tabela.getValueAt(vrsta, 0).toString();
						String proiz = Tabela.getValueAt(vrsta, 1).toString();
						String model = Tabela.getValueAt(vrsta, 2).toString();

						int x = JOptionPane.showOptionDialog(getContentPane(),
								"Da li zelite da izmenite ili izbrisete instrument?", "Opcija", 0,
								JOptionPane.INFORMATION_MESSAGE, null, options, null);

						if (x == 0) {
							DialogMenjaj dm = new DialogMenjaj();
							
							String sql1 = "select * from instrument where naziv = '" + naziv + "' and proizvodjac = '"
									+ proiz + "' and model = '" + model + "'";
							
							rs = st.executeQuery(sql1);
							while(rs.next()){
								String naz = rs.getString("naziv");
								String pro = rs.getString("proizvodjac");
								String mod = rs.getString("model");
								String mat = rs.getString("materijal");
								Integer lag = (int) rs.getObject("lager");
								Integer cena = (int) rs.getObject("cena");
								String id = rs.getString("id");
								dm.PopuniTxt(naz, pro, mod, mat, lag, cena,id);
							}
							
							dm.setModal(true);
							dm.setVisible(true);
							
							st.close();
							rs.close();
							
						}

						if (x == 1) {

							sql = "delete from instrument where naziv = '" + naziv + "' and proizvodjac = '" + proiz
									+ "' and model ='" + model + "'";
							JOptionPane.showMessageDialog(null, "Instrument uspesno izbrisan");
							st.executeUpdate(sql);
							st.close();
							rs.close();
						}

						
						PrikaziTabelu();
						
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public IzmenaBrisanje() {
		setBounds(100, 100, 565, 350);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 529, 289);
		contentPanel.add(scrollPane);

		Tabela = new JTable();

		Tabela.setRowHeight(20);
		scrollPane.setViewportView(Tabela);
		Tabela.getTableHeader().setResizingAllowed(false);
		PrikaziTabelu();
	}
}
