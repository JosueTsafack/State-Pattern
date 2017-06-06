package kaffeeautomat;

import java.util.Arrays;

public class Kakao extends Produkt {

	
	
	public Kakao() {
		super("Kakao", 90);
		super.setOptionen(new String[]{"kalt","heiﬂ"});
	}

	@Override
	public void w‰hleOptionen(String option) {
		if(Arrays.asList(super.getOptionen()).contains(option)){
				this.setOption(option+",");
		}else{
			System.out.println("Diese Option wird nicht angeboten");
		}
	}

	@Override
	public String toString() {
		return super.getBezeichnung()+": "+super.getPreis()+" , "+ super.getGew‰hlteOptionen();
	}

	@Override
	public void setOption(String option) {
		
		gew‰hlteOptionen+=option;
		
	}

	
	
}
