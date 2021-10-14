package paketInstrument;

public class Oprema {
	private String naziv;
	private int cena;

	public Oprema(String i) {
		if (i.equals("gitara")) {
			naziv = "pojacalo";
			cena = 20000;
		}
		if (i.equals("bubnjevi")) {
			naziv = "palice";
			cena = 2500;
		}
		if(i.equals("klavir")) {
			naziv = "stolica";
			cena = 7500;
		}
		if(i.equals("violina")) {
			naziv = "gudalo";
			cena = 1500;
		}

	}

	public int getCena() {
		return cena;
	}

	public void setCena(int cena) {
		this.cena = cena;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

}
