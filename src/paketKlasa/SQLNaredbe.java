package paketKlasa;

import java.sql.DriverManager;
import java.sql.SQLException;
//import java.text.SimpleDateFormat;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class SQLNaredbe {

//	public void Prodaja(String proizvodi, String id, int kolicina, int cena, SimpleDateFormat sdf) {
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/seminarskioop",
//					"root", "");
//			Statement s = (Statement) conn.createStatement();
//			String sql = "insert into prodaja values ()";
//			@SuppressWarnings("unused")
//			int x = s.executeUpdate(sql);
//			System.out.println("Uspelo");
//
//		} catch (Exception ex) {
//			System.out.println(ex);
//		}
//	}

	public void KreirajBazu() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
			Statement s = (Statement) conn.createStatement();

			@SuppressWarnings("unused")
			int Result = s.executeUpdate("CREATE DATABASE IF NOT EXISTS seminarskioop");
			@SuppressWarnings("unused")
			int kreirajInstrument = s.executeUpdate("CREATE TABLE IF NOT EXISTS seminarskioop.instrument( "
					+ "`naziv` varchar(100) not null, " + "`proizvodjac` varchar(100) not null, "
					+ "`model` varchar(100) not null, " + "`materijal` varchar(100) not null, " + "`id` varchar(30), "
					+ "`lager` int(30) not null, " + "`cena` int(30) not null," + "PRIMARY KEY (`id`)" + ");");

			@SuppressWarnings("unused")
			int kreirajUsers = s.executeUpdate("CREATE TABLE IF NOT EXISTS seminarskioop.users( "
					+ "`ime` varchar(100) not null, " + "`prezime` varchar(100) not null, "
					+ "`jmbg` bigint(13) not null, " + "`drzava` varchar(100) not null, "
					+ "`grad` varchar(100) not null, " + "`username` varchar(100) not null, "
					+ "`password` varchar(30) not null, " + "PRIMARY KEY (`jmbg`)" + ");");
			@SuppressWarnings("unused")
			int kreirajProdaja = s.executeUpdate("CREATE TABLE IF NOT EXISTS seminarskioop.prodaja( "
					+ "`naziv` varchar(100) not null, " + "`proizvodjac` varchar(100) not null, "
					+ "`model` varchar(100) not null, " + "`kolicina` int(10) not null, " + "`oprema` varchar(100) null, "
					+ "`kolicinaOpreme` int(10) null, " + "`ukupnaCena` int(30) not null,"+"`datumProdaje` date not null" + ");");

		} catch (Exception ex) {
			System.out.println(ex);
		}

	}
}
