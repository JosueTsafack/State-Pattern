package kaffeeautomat;

public class AutomatenSteuerung implements KaffeeAutomat {

	private Produkt produkt ;
	private Zustand zustand;
	private int geld = 0;
	private int mengeKaffee = 2;
	private int mengeKakao = 2;
	private int mengeTee = 3;
	
	private Protokoll protokoll;

	public AutomatenSteuerung() {
	    
		zustand = Zustand.KEINE_MÜNZE;
		produkt=null;
		protokoll=new Protokoll();
		
	}

	@Override
	public void bezahleBetrag(int betrag) {
		
		
		
	    if (zustand == Zustand.IS_GEWAEHLT || zustand==Zustand.HAT_MÜNZE) {
	    	this.geld += betrag;
				if (geld < produkt.getPreis()) {
				    
					System.out.println("Es fehlt noch :" + (produkt.getPreis() - geld));
					zustand=Zustand.HAT_MÜNZE;
	
				} else if (geld >=produkt.getPreis()) {
					
					zustand=Zustand.ZAPFEN;
					this.zapfeProdukt();
					zustand=zustand.HAT_MÜNZE;
				    System.out.println("WechselGeld: "+ this.fordereWechselgeld());
				    
					
	
				}
	
		}else if (zustand == Zustand.ZAPFEN) {
			System.out.println("Beim Zapfen können Sie kein Geld einwerfen");
		}else if(zustand== Zustand.AUSVERKAUFT){
			System.out.println("Sie können keine Geld einwerfen, das Produkt ist ausverkauft");
		}else if(zustand==Zustand.KEINE_MÜNZE){
			System.out.println("Sie müssen zuerst ein  Produkt wählen");
		}

	}

	@Override
	public void waehleProdukt(String produkt) {
		
		if(zustand==Zustand.KEINE_MÜNZE){
			
				this.produkt =this.getProdukt(produkt);
			
		}else if(zustand==Zustand.HAT_MÜNZE){
		    
			System.out.println("Sie müssen zuerst wählen, dann einahlen ");
		}else if(zustand==Zustand.ZAPFEN){
		    
			System.out.println("Zapfvorgang");
		}else if(zustand==Zustand.AUSVERKAUFT){
		    
		    System.out.println("das Produkt ist ausverkauft...");
		}
		
	}
	public Produkt getProdukt (String produkt ){
		
		if (produkt.equalsIgnoreCase("Kaffee") ){
			if (this.getMengeKaffee() > 0) {
				
				zustand=Zustand.IS_GEWAEHLT;
				return new Kaffee(); 
				
			} else {
				System.out.println("Ausverkauft");
				zustand = Zustand.AUSVERKAUFT;
			}
			return null;
			
		}else if(produkt.equalsIgnoreCase("Kakao")){
			if (this.getMengeKaffee() > 0) {
				
				zustand=Zustand.IS_GEWAEHLT;
				return new Kakao(); 
				
			}else {
				System.out.println("Ausverkauft");
				zustand = Zustand.AUSVERKAUFT;
			}
			
		}else if(produkt.equalsIgnoreCase("Tee")){
			if (this.getMengeKaffee() > 0) {
				
				zustand=Zustand.IS_GEWAEHLT;
				return new Tee(); 
				
			}else {
				System.out.println("Ausverkauft");
				zustand = Zustand.AUSVERKAUFT;
			}
			
		}else{
			System.out.println("das "+produkt+" wird nicht angeboten");
		}
		
		return null;
       
	}
	

	@Override
	public void waehleOption(String option) {

		if(zustand==Zustand.IS_GEWAEHLT){
		    
			produkt.wähleOptionen(option);
			
		}else {
			System.out.println("Sie müssen zuerst ein Produkt wählen...");
		}

	}

	@Override
	public int fordereWechselgeld() {

		int wechselGeld = 0;
		
			if(zustand==Zustand.HAT_MÜNZE || zustand==Zustand.ZAPFEN){
				
				zustand=Zustand.KEINE_MÜNZE;
				
				if(geld>=produkt.getPreis()){
				    wechselGeld=wechselGeld+(this.geld-produkt.getPreis());
				
				    this.geld=0;
				    
				}else if(geld<produkt.getPreis()){
					wechselGeld+=geld;
					geld=0;
				}
				
			}else if(zustand==Zustand.KEINE_MÜNZE){
			    System.out.println("Sie haben keine MÜNZE eingeworfen");
			}else if(zustand==Zustand.AUSVERKAUFT || zustand==Zustand.IS_GEWAEHLT){
				System.out.println("Sie müssen zuerst Geld einzahlen");
			}
			
		return wechselGeld;
	}

	@Override
	public int zapfeProdukt() {

		if(zustand==Zustand.ZAPFEN){
		    this.reduziereMenge();
		}else if(zustand==Zustand.HAT_MÜNZE){
		    System.out.println("Es wird kein Produkt ausgegeben");
		   
		}else if(zustand==Zustand.IS_GEWAEHLT){
		    System.out.println("Sie muessen zuerst Geld einzahlen");
		    
		}else if (zustand==Zustand.KEINE_MÜNZE || zustand==Zustand.AUSVERKAUFT){
		    
		    System.out.println("Anfrage nicht möglich");   
		    return 0;
		    
		}
		
		return this.fordereWechselgeld();
	}
	
	public void reduziereMenge(){
		if(produkt instanceof Kaffee){
	        
	        mengeKaffee--;
            
	    }else if(produkt instanceof Tee){
	        
	        mengeTee--;
	        
	    }else if(produkt instanceof Kakao){
	        
	        mengeKakao--;
	    }
		 protokoll.addProdukt(this.produkt);
	}

	@Override
	public int abbruch() {
	    
		if(zustand== Zustand.ZAPFEN || zustand==Zustand.AUSVERKAUFT || zustand==Zustand.KEINE_MÜNZE){
			System.out.println("Anfrage nicht möglich");
			return 0;
		}
		
		return this.fordereWechselgeld();
	}

	

	public String[] listeVerkäufe() {
		return  (String[]) protokoll.getList().toArray(new String[protokoll.getList().size()]);
	}

	public int getMengeKaffee() {
		return mengeKaffee;
	}

	public void setMengeKaffee(int menge) {
		this.mengeKaffee = this.getMengeKaffee() + menge;
	}

	public int getMengeTee() {
		return mengeTee;
	}

	public void setMengeTee(int menge) {
		this.mengeTee = this.getMengeTee() + menge;
	}

	public int getMengeKakao() {
		return mengeKakao;
	}

	public void setMengeKakao(int menge) {
		this.mengeKakao = this.getMengeKakao() + menge;
	}
	
	public Zustand getZustand(){
		return zustand;
	}
	public int getGeld(){
		return this.geld;
	}
	public int getUmsatz(){
	    return protokoll.getUmsatz();
	}
	

	enum Zustand {
		HAT_MÜNZE, KEINE_MÜNZE, ZAPFEN, AUSVERKAUFT, IS_GEWAEHLT;
	}

}




/*
 * Nachteile dieses Entwurfsmusters:
 * 
 * 1-Dieses Design ist nicht gerade besonders objektorientiert
 * 2-Wir haben hier nicht gekapselt, was variiert
 * 3-Wenn Code hinzugefuegt wird, verursacht das mit groesser Wahrscheinlichkeit Programmfehler
 * 4-Keine leichte Ã„nderbarkeit und Erweiterbarkeit ermoeglich
 * 5-Open-Closed Prinzip ist verletzt
 * 6-das Verhalten eines Zustand ist nicht in seiner eigener Klasse lokalisiert
 * 7-keine Verstaendlichkeit
 * 8-Die Zustandsuebergaenge sind nicht explizit
 * 
 **/
