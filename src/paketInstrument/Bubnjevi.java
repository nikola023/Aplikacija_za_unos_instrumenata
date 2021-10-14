package paketInstrument;

public class Bubnjevi extends AbstractInstrument {

	public Bubnjevi(String model, String proizvodjac, String materijal, String id, int kolicina,int cena) {
		super("bubnjevi", model, proizvodjac, materijal, id, kolicina,cena);
	}
	
	public void kreiraj() {
		kreirajInstrument();
	}

}
