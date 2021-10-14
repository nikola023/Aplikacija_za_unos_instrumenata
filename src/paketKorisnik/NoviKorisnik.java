package paketKorisnik;

public class NoviKorisnik extends AbstractKorisnik{
	public NoviKorisnik(String ime, String prezime, long jmbg, String drzava, String grad, String username, String password) {
		super(ime,prezime,jmbg,drzava,grad,username,password);
		registerFunction();
	}
}
