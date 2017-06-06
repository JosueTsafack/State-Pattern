package StatePattern.Automat;

import kaffeeautomat.Kaffee;
import kaffeeautomat.Kakao;
import kaffeeautomat.Produkt;
import kaffeeautomat.Protokoll;
import kaffeeautomat.Tee;

public class AutomatenSteuerung implements KaffeeAutomat {

	private Zustand zustand;
	private Produkt produkt;
	private Protokoll protokoll;
	private IsGewähltZustand isGewählt;
	private HatMünzeZustand hatMünze;
	private KeineMünzeZustand keineMünze;
	private AusverkauftZustand ausverkauft;
	private ZapfZustand zapfen;
	private int geld;
	private int mengeKaffee = 2;
	private int mengeKakao = 2;
	private int mengeTee = 3;
	
	public AutomatenSteuerung(){
		
		isGewählt=new IsGewähltZustand(this);
		hatMünze=new HatMünzeZustand(this);
		keineMünze=new KeineMünzeZustand(this);
		ausverkauft=new AusverkauftZustand(this);
		zapfen=new ZapfZustand(this);
		setProdukt(null);
		setProtokoll(new Protokoll());
		setZustand(this.getHatMünzeZustand());
		setGeld(0);
	}
	
	
	@Override
	public void bezahleBetrag(int betrag) {
		this.getZustand().bezahleBetrag(betrag);
	}

	@Override
	public void waehleProdukt(String produkt) {
		if(produkt.equalsIgnoreCase("Kaffee")){
			if(this.getMengeKaffee()==0){
				this.setZustand(this.getAusverkauftZustand());
			}
		}else if( produkt.equalsIgnoreCase("Tee")){
			if(this.getMengeTee()==0){
				this.setZustand(this.getAusverkauftZustand());
			}
		}else if(produkt.equalsIgnoreCase("Kakao")){
			if(this.getMengeKakao()==0){
				this.setZustand(this.getAusverkauftZustand());
			}
		}
		this.getZustand().waehleProdukt(produkt);
	}

	@Override
	public void waehleOption(String option) {
		this.getZustand().waehleOption(option);
		
	}

	@Override
	public int fordereWechselgeld() {
		
		return this.getZustand().fordereWechselgeld();
	}

	@Override
	public int zapfeProdukt() {
		
		return this.getZustand().zapfeProdukt();
	}

	@Override
	public int abbruch() {
		
		return this.getZustand().abbruch();
	}


	public HatMünzeZustand getHatMünzeZustand() {
		return hatMünze;
	}


	public KeineMünzeZustand getKeineMünzeZustand() {
		return keineMünze;
	}


	public AusverkauftZustand getAusverkauftZustand() {
		return ausverkauft;
	}


	public IsGewähltZustand getIsGewähltZustand() {
		return isGewählt;
	}

	public ZapfZustand getZapfZustand() {
		return zapfen;
	}

	public Zustand getZustand() {
		return zustand;
	}


	public void setZustand(Zustand zustand) {
		this.zustand = zustand;
	}


	public Produkt getProdukt() {
		return produkt;
	}


	public void setProdukt(Produkt produkt) {
		this.produkt = produkt;
	}


	public int getGeld() {
		return geld;
	}


	public void setGeld(int geld) {
		this.geld = geld;
	}


	public Protokoll getProtokoll() {
		return protokoll;
	}


	public void setProtokoll(Protokoll protokoll) {
		this.protokoll = protokoll;
	}


	public int getMengeKaffee() {
		return mengeKaffee;
	}


	public void setMengeKaffee(int mengeKaffee) {
		this.mengeKaffee = mengeKaffee;
	}


	public int getMengeKakao() {
		return mengeKakao;
	}


	public void setMengeKakao(int mengeKakao) {
		this.mengeKakao = mengeKakao;
	}


	public int getMengeTee() {
		return mengeTee;
	}


	public void setMengeTee(int mengeTee) {
		this.mengeTee = mengeTee;
	}

	public Kaffee getKaffee(){
		return new Kaffee();
	}
	public Tee getTee(){
		return new Tee();
	}
	
	public Kakao getKakao(){
		return new Kakao();
	}

	

}
