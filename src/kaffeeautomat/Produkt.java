package kaffeeautomat;

public abstract class Produkt {

	private String bezeichnung; //Warum private, wenn wir Unterklassen davon haben?
	private int preis=0; // Warum int? // Warum private, wenn wir Unterklassen davon haben?
	private String [] optionen; //Warum private, wenn wir Unterklassen davon haben?
	protected String gewählteOptionen="";
	
	
	
	
	Produkt(String bezeichnung, int preis){
		this.bezeichnung=bezeichnung;
		this.preis=preis;
	}
	
	
	
	public abstract void setOption(String option);
	public void setOptionen(String [] optionen){
		this.optionen=optionen;
	}
	
	public abstract String toString();
	
	
	
	public  int getPreis(){
		return preis;
	}
	
	public void setPreis(int preis){
		this.preis=this.getPreis()+preis;
	}
	
	public String [] getOptionen(){
		return optionen;
	}
	
	
	
	public String[] getGewählteOptionen(){
		return 	gewählteOptionen.split(",");
	}
	
	
	public abstract void wähleOptionen (String option);
	
	public String getBezeichnung(){
		return bezeichnung;
	}
	
	
	
}
