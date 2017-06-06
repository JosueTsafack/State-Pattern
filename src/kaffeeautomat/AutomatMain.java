package kaffeeautomat;

public class AutomatMain {

	public static void main(String[] args) {
		AutomatenSteuerung a = new AutomatenSteuerung();
		
		
		a.waehleProdukt("Kaffee");
		System.out.println(a.getZustand());
		a.waehleOption("Milch");
		System.out.println(a.getZustand());
		a.bezahleBetrag(15);
		System.out.println(a.getZustand());
		System.out.println(a.getGeld());
		System.out.println(a.fordereWechselgeld());
		System.out.println(a.getZustand());
		System.out.println("----------");
		a.zapfeProdukt();
		System.out.println(a.getZustand());
		System.out.println(a.getGeld());
		a.waehleProdukt("Kaffee");
		a.bezahleBetrag(115);
//		a.bezahleBetrag(20);
//		System.out.println(a.getZustand());
//		System.out.println(a.getGeld());
//		System.out.println("---------");
//		System.out.println(a.getZustand());
		
		System.out.println(a.abbruch());
		System.out.println(a.getZustand());
		a.bezahleBetrag(70);
		
		System.out.println(a.getGeld());
		System.out.println(a.getZustand());

		
		
		
		
		

	}

}
