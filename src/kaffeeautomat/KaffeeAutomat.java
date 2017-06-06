package kaffeeautomat;

public interface KaffeeAutomat {
	
	public void bezahleBetrag(int betrag);
	public void waehleProdukt(String produkt);
	public void waehleOption(String option);
	public int fordereWechselgeld();
	public int zapfeProdukt(); // Wechselgeld zurückgeben
	public int abbruch(); // Wechselgeld zurückgeben
	
}
