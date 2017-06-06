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
	    
		zustand = Zustand.KEINE_M‹NZE;
		produkt=null;
		protokoll=new Protokoll();
		
	}

	@Override
	public void bezahleBetrag(int betrag) {
		
		
		
	    if (zustand == Zustand.IS_GEWAEHLT || zustand==Zustand.HAT_M‹NZE) {
	    	this.geld += betrag;
				if (geld < produkt.getPreis()) {
				    
					System.out.println("Es fehlt noch :" + (produkt.getPreis() - geld));
					zustand=Zustand.HAT_M‹NZE;
	
				} else if (geld >=produkt.getPreis()) {
					
					zustand=Zustand.ZAPFEN;
					this.zapfeProdukt();
					zustand=zustand.HAT_M‹NZE;
				    System.out.println("WechselGeld: "+ this.fordereWechselgeld());
				    
					
	
				}
	
		}else if (zustand == Zustand.ZAPFEN) {
			System.out.println("Beim Zapfen k√∂nnen Sie kein Geld einwerfen");
		}else if(zustand== Zustand.AUSVERKAUFT){
			System.out.println("Sie k√∂nnen keine Geld einwerfen, das Produkt ist ausverkauft");
		}else if(zustand==Zustand.KEINE_M‹NZE){
			System.out.println("Sie m¸ssen zuerst ein  Produkt w‰hlen");
		}

	}

	@Override
	public void waehleProdukt(String produkt) {
		
		if(zustand==Zustand.KEINE_M‹NZE){
			
				this.produkt =this.getProdukt(produkt);
			
		}else if(zustand==Zustand.HAT_M‹NZE){
		    
			System.out.println("Sie m¸ssen zuerst w‰hlen, dann einahlen ");
		}else if(zustand==Zustand.ZAPFEN){
		    
			System.out.println("Zapfvorgang");
		}else if(zustand==Zustand.AUSVERKAUFT){
		    
		    System.out.println("das Produkt ist ausverkauft...");
		}
		
	}
	public Produkt getProdukt (String produkt ){
		
		if (produkt.equals("kaffee") ){
			if (this.getMengeKaffee() > 0) {
				
				zustand=Zustand.IS_GEWAEHLT;
				return new Kaffee(); 
				
			} else {
				System.out.println("Ausverkauft");
				zustand = Zustand.AUSVERKAUFT;
			}
			return null;
			
		}else if(produkt.equals("Kakao")){
			if (this.getMengeKaffee() > 0) {
				
				zustand=Zustand.IS_GEWAEHLT;
				return new Kakao(); 
				
			}else {
				System.out.println("Ausverkauft");
				zustand = Zustand.AUSVERKAUFT;
			}
			
		}else if(produkt.equals("Tee")){
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
		    
			produkt.w‰hleOptionen(option);
			
		}else {
			System.out.println("Sie m√ºssen zuerst ein Produkt w√§hlen...");
		}

	}

	@Override
	public int fordereWechselgeld() {

		int wechselGeld = 0;
		

			if(zustand==Zustand.HAT_M‹NZE || zustand==Zustand.ZAPFEN){
				zustand=Zustand.KEINE_M‹NZE;
				if(geld>=produkt.getPreis()){
				    wechselGeld=this.geld-produkt.getPreis();
				
				    this.geld=0;
				    
				}else{
					wechselGeld=geld;
					geld=0;
					return wechselGeld;
				}
				
				return wechselGeld;
				
			}else if(zustand==Zustand.KEINE_M‹NZE){
			    System.out.println("Sie haben keine M√úNZE eingeworfen");
			}
			
			System.out.println("Anfrage nicht m√∂glich");
		
		
		return 0;
	}

	@Override
	public int zapfeProdukt() {
		
		if(zustand==Zustand.ZAPFEN){
		    
		    this.reduziereMenge();
		}else if(zustand==Zustand.HAT_M‹NZE){
		    System.out.println("Es wird kein Produkt ausgegeben");
		   
		}else if(zustand==Zustand.IS_GEWAEHLT){
		    System.out.println("Sie m√ºssen zuerst Geld einzahlen");
		    
		}else if (zustand==Zustand.KEINE_M‹NZE || zustand==Zustand.AUSVERKAUFT){
		    
		    System.out.println("Anfrage nicht m√∂glich");   
		    return 0;
		    
		}
		
		return this.fordereWechselgeld();
	}
	
	public void reduziereMenge(){
		if(produkt instanceof Kaffee){
	        
	        mengeKaffee--;
            protokoll.addProdukt(this.produkt);
            
	    }else if(produkt instanceof Tee){
	        
	        mengeTee--;
	        protokoll.addProdukt(this.produkt);
	        
	    }else if(produkt instanceof Kakao){
	        
	        mengeKakao--;
	        protokoll.addProdukt(this.produkt);
	    }
	}

	@Override
	public int abbruch() {
	    
		if(zustand== Zustand.ZAPFEN || zustand==Zustand.AUSVERKAUFT){
			System.out.println("Anfrage nicht m√∂glich");
			return 0;
		}
		
		return this.fordereWechselgeld();
	}

	

	public String[] listeVerk‰ufe() {
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
		HAT_M‹NZE, KEINE_M‹NZE, ZAPFEN, AUSVERKAUFT, IS_GEWAEHLT;
	}

}




/*
 * Nachteile dieses Entwurfsmusters:
 * 
 * 1-Dieses Design ist nicht gerade besonders objektorientiert
 * 2-Wir haben hier nicht gekapselt, was variiert
 * 3-Wenn Code hinzugef√ºht wird, verursacht das mit gro√üer Wahrscheinlichkeit Programmfehler
 * 4-Keine leichte √Ñnderbarkeit und Erweiterbarkeit erm√∂glich
 * 5-Open-Closed Prinzip ist verletzt
 * 6-das Verhalten eines Zustand ist nicht in seiner eigener Klasse lokalisiert
 * 7-keine Verst√§ndlichkeit
 * 8-Die Zustands√ºberg√§nge sind nicht explizit
 * 
 **/
