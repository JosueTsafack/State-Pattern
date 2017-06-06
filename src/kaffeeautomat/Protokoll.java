package kaffeeautomat;

import java.util.ArrayList;
import java.util.List;

public class Protokoll {

	private int umsatz;
	private List<String> array;
	private VerkaufsEreignis ereignis;
	
	public Protokoll(){
		array=new ArrayList<>();
		ereignis=new VerkaufsEreignis();
	}
	
	public void addProdukt(Produkt produkt){
	    
	        umsatz+=produkt.getPreis();
	        ereignis.setBezeichnung(produkt.getBezeichnung());
	        ereignis.setOptionen(produkt.getGewählteOptionen());
	        ereignis.setVerkaufsPreis(produkt.getPreis());
	        array.add(ereignis.toString());
	}
	
	public int getUmsatz() {
		return this.umsatz;
	}
	
	public void setUmsatz(int geld){
		umsatz+=geld;
	}
	
	public List<String> getList(){
		return array;
	}
	
}
