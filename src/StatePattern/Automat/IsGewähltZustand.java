package StatePattern.Automat;

public class IsGewähltZustand implements Zustand {
	
	
private AutomatenSteuerung automat;
	
	
	public IsGewähltZustand(AutomatenSteuerung automat){
		this.automat=automat;
	}

	@Override
	public void bezahleBetrag(int betrag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void waehleProdukt(String produkt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void waehleOption(String option) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void bezahleBetrag() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void waehleProdukt() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void waehleOption() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int fordereWechselgeld() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int zapfeProdukt() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int abbruch() {
		// TODO Auto-generated method stub
		return 0;
	}

}
