package paketInstrument;

public class Gitara extends AbstractInstrument {

	public Gitara(String model, String proizvodjac, String materijal, String id, int kolicina,int cena) {
		super("gitara", model, proizvodjac, materijal, id, kolicina,cena);
		
	}
	
	public void kreiraj() {
		kreirajInstrument();
	}

}
