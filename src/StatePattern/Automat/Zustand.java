package StatePattern.Automat;

public interface Zustand extends KaffeeAutomat{
	
	public void bezahleBetrag();
	public void waehleProdukt();
	public void waehleOption();
	public int fordereWechselgeld();
	public int zapfeProdukt(); // Wechselgeld zurückgeben
	public int abbruch(); // Wechselgeld zurückgeben
	
}
