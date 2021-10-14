package paketInstrument;

import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import java.sql.*;
import com.mysql.jdbc.Statement;

public abstract class AbstractInstrument implements InterfaceInstrument {
	private String nazivInstrumenta, model, proizvodjac, materijal, id;
	private int kolicina, cena;

	public AbstractInstrument(String naziv, String proizvodi, String model, String materijal, String id, int kolicina,
			int cena) {
		this.nazivInstrumenta = naziv;
		this.model = model;
		this.proizvodjac = proizvodi;
		this.materijal = materijal;
		this.id = id;
		this.kolicina = kolicina;
		this.cena = cena;
	}

	public String getNazivInstrumenta() {
		return nazivInstrumenta;
	}

	public void kreirajInstrument() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/seminarskioop",
					"root", "");
			Statement st = (Statement) con.createStatement();
			String sql = "select * from instrument where id = '" + id + "'";
			ResultSet rs = (ResultSet) st.executeQuery(sql);
			if (rs.next()) {
				JOptionPane.showMessageDialog(null, "Instrument sa ovim id brojem vec postoji!");

			} else {
				sql = "select * from instrument where naziv= '" + nazivInstrumenta + "' and model = '" + model
						+ "' and proizvodjac = '" + proizvodjac + "'";
				rs = (ResultSet) st.executeQuery(sql);
				if (rs.next()) {
					JOptionPane.showMessageDialog(null, "Ovaj instrument je vec unet!");
				} else {
					String insert = "INSERT INTO instrument(naziv,proizvodjac,model,materijal,id,lager,cena) VALUES ('"
							+ nazivInstrumenta + "','" + proizvodjac + "','" + model + "','" + materijal + "','" + id
							+ "','" + kolicina + "' , '" + cena + "')";
					try {
						st.executeUpdate(insert);
						JOptionPane.showMessageDialog(null, "Instrument uspesno unet!");

					} catch (Exception ex) {
						System.out.println(ex);
					}

				}
			}
			con.close();
			st.close();

		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null,
					"Doslo je do greske prilikom povezivanja sa bazom podataka, pokusajte kasnije.");
			ex.printStackTrace();
		}
	}

	public void prodajInstrument(String idInstrumenta) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/seminarskioop",
					"root", "");
			Statement st = (Statement) con.createStatement();
			String sql = "select * from instrument where id = '" + idInstrumenta + "'";
			ResultSet rs = (ResultSet) st.executeQuery(sql);

			if (rs.next()) {

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
