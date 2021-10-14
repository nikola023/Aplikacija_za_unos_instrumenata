package paketInstrument;

public class Klavir extends AbstractInstrument{
	
	public Klavir(String model, String proizvodjac, String materijal, String id, int kolicina,int cena) {
		super("klavir", model, proizvodjac, materijal, id, kolicina,cena);
	}
	
	public void kreiraj() {
		kreirajInstrument();
	}
}
