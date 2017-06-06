package kaffeeautomat;

import java.util.Date;

public class VerkaufsEreignis {

	private String produkBezeichnung;
	private String [] optionen;
	private Date datum;
	private int verkaufsPreis;
	
	public VerkaufsEreignis(){
		datum=new Date();
	}
	
	public void setBezeichnung(String bezeichnung){
		this.produkBezeichnung=bezeichnung;
	}
	
	public void setOptionen(String[] optionen){
		this.optionen=optionen;
	}
	public void setVerkaufsPreis(int preis){
		this.verkaufsPreis=preis;
	}
	
	public Date getDatum(){
	    return datum;
	}
	
	@Override
	public String toString(){
	    return this.produkBezeichnung+","+this.optionen+","+this.datum+","+this.verkaufsPreis+".";
	}
	
	
}
