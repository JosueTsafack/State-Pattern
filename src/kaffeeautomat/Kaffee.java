package kaffeeautomat;

import java.util.Arrays;

public class Kaffee extends Produkt {
	
	public Kaffee(){
		super("Kaffe",120);
		super.setOptionen(new String[]{"Milch","Zucker","Schokostreusel"});
	}

	@Override
	public void w�hleOptionen(String option) {
		if(Arrays.asList(super.getOptionen()).contains(option)){
			super.setPreis(10);
			this.setOption(option+",");
		}else{
			System.out.println("Diese Option wird nicht angeboten");
		}
		
	}

	@Override
	public String toString() {
	    
		return super.getBezeichnung()+": "+super.getPreis()+" , "+ super.getGew�hlteOptionen();
	}

	@Override
	public void setOption(String option) {
		
			gew�hlteOptionen+=option;
		
	}

	
}
