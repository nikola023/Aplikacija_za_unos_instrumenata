package paketInstrument;

public class Violina extends AbstractInstrument{
	
	public Violina(String model, String proizvodjac, String materijal, String id, int kolicina,int cena) {
		super("violina", model, proizvodjac, materijal, id, kolicina,cena);
	}
	
	public void kreiraj() {
		kreirajInstrument();
	}
}
