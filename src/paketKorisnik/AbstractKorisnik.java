package paketKorisnik;

import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import java.sql.*;
import com.mysql.jdbc.Statement;

public abstract class AbstractKorisnik implements InterfaceKorisnik {
	private String username, password, ime, prezime, drzava, grad;
	private long jmbg;
	private boolean postojiUser;
	private boolean registrovan;

	public AbstractKorisnik(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public AbstractKorisnik(String ime, String prezime, long jmbg, String drzava, String grad, String username,
			String password) {
		this.ime = ime;
		this.prezime = prezime;
		this.jmbg = jmbg;
		this.drzava = drzava;
		this.grad = grad;
		this.username = username;
		this.password = password;
	}

	public void loginFunction() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/seminarskioop",
					"root", "");
			Statement st = (Statement) con.createStatement();
			String sql = "select * from users where Username = '" + username + "' and Password = '" + password + "'";
			ResultSet rs = (ResultSet) st.executeQuery(sql);
			if (rs.next()) {
				setPostojiUser(true);
			} else {
				setPostojiUser(false);
				JOptionPane.showMessageDialog(null, "Unesite ispravne podatke!");
			}

			con.close();
			st.close();

		} catch (Exception ex) {
			System.out.println(ex);
			JOptionPane.showMessageDialog(null,
					"Doslo je do greske prilikom povezivanja sa bazom podataka, pokusajte kasnije.");
		}

	}

	public void registerFunction() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/seminarskioop",
					"root", "");
			Statement st = (Statement) con.createStatement();

			long x = jmbg;
			long duzina = String.valueOf(x).length();
			if (duzina != 13) {
				JOptionPane.showMessageDialog(null, "JMBG mora imati 13 cifara!");
				setRegistrovan(false);
			} else {
				String sql = "select * from users where JMBG = '" + x + "'";
				ResultSet rs = (ResultSet) st.executeQuery(sql);
				if (rs.next()) {
					JOptionPane.showMessageDialog(null, "Ovaj JMBG je vec unet!");
					setRegistrovan(false);
				} else {
					sql = "select * from users where Username = '" + username + "'";
					rs = (ResultSet) st.executeQuery(sql);
					if (rs.next()) {
						JOptionPane.showMessageDialog(null, "Korisnicko ime vec postoji!");
						setRegistrovan(false);
					} else {
						String insert = "INSERT INTO users(Ime, Prezime, JMBG, Drzava, Grad, Username, Password) VALUES ('"
								+ ime + "','" + prezime + "','" + jmbg + "','" + drzava + "','" + grad + "','"
								+ username + "','" + password + "')";
						st.executeUpdate(insert);
						JOptionPane.showMessageDialog(null, "Registracija uspesna!");
						setRegistrovan(true);
					}

				}
			}
			con.close();
			st.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println(e);
			JOptionPane.showMessageDialog(null,
					"Doslo je do greske prilikom povezivanja sa bazom podataka, pokusajte kasnije.");
		}

	}

	public boolean isPostojiUser() {
		return postojiUser;
	}

	public void setPostojiUser(boolean postojiUser) {
		this.postojiUser = postojiUser;
	}

	public boolean isRegistrovan() {
		return registrovan;
	}

	public void setRegistrovan(boolean registrovan) {
		this.registrovan = registrovan;
	}
}
