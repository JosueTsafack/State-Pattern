package kaffeeautomat;

import java.util.Arrays;

public class Tee extends Produkt {

	
	public Tee(){
		super("Tee",100);
		super.setOptionen(new String[]{"milch","Zucker"});
	}

	@Override
	public void wähleOptionen(String option) {
		if(Arrays.asList(super.getOptionen()).contains(option)){
			this.setOption(option+",");
		}else{
			System.out.println("Diese Option wird nicht angeboten");
		}
	}

	@Override
	public String toString() {
		return super.getBezeichnung()+": "+super.getPreis()+" , "+ super.getGewählteOptionen();
	}

	@Override
	public void setOption(String option) {
		
			gewählteOptionen+=option;
	}

	
	
}
