package GUI;

import javax.swing.JButton;

import java.awt.Color;
import java.awt.Insets;
import java.util.ArrayList;

public class HandButton extends JButton{
	private final static int BUTTON_INSETS=1;
	boolean active=false;
	private Color color;
	private String hand;
	private final String[] suits = {"s", "c", "h", "d"};
	private int HAND_TYPE = 0;
	private ArrayList<String> COMBOS = new ArrayList<String>();



	public HandButton(String text, Color color){
		this.setText(text);
		this.color=color;
		this.hand = this.getText();
		this.setBackground(color);
		this.setMargin(new Insets(BUTTON_INSETS, BUTTON_INSETS, BUTTON_INSETS, BUTTON_INSETS));
	}

	public void press(){
		
		if(!active){
		
			this.setBackground(new Color(255, 255, 0));   //255, 255, 0
		}
		else{
			
			this.setBackground(color);
		}
		active=!active;
	}
	/*
	 * replaces h to ♥ etc etc
	 */
	public String handTextTypeParse(String hand){
		String h = hand;
		if (h.length()<4 &&!(h.length()==2)){
			return h;
		}else {
		
			h = h.replace("c", "<FONT SIZE = 0><FONT COLOR = green>♣</FONT></FONT>");
			h = h.replace("s", "<FONT SIZE = 0><FONT COLOR = black>♠</FONT></FONT>");
			h = h.replace("d", "<FONT SIZE = 0><FONT COLOR = blue>♦</FONT></FONT>");
			h = h.replace("h", "<FONT SIZE = 0><FONT COLOR = red>♥</FONT></FONT>");
			
			return h;
		}
		
	}

	public void setWeight(double weight){
		this.setText("<HTML><b> <FONT SIZE = 2>"+handTextTypeParse(hand)+"  \n"+"</FONT></b><CENTER><FONT SIZE = 1>"+"<FONT COLOR = black>"+weight+"</FONT></CENTER></HTML>");	
	}

	public void enable(double weight){
		
		if (weight==0){
			this.remove(this);
		}else{
			
		
		//weight color
		double c1 = (1-weight)*170+85;     //weight*192+63;
		Long L1 = Math.round(c1);
		int i = Integer.valueOf(L1.intValue());
		
		double c2 = (1-weight)*84+171;
		Long L = Math.round(c2);
		int y = Integer.valueOf(L.intValue());
				
		active=true;
		this.setBackground(new Color(i, y, 255));
		}
	}

	public void disable(){
		active=false;
		this.setBackground(color);
	}

	public boolean getActive(){
		return active;
	}

	/**
	 * Set hand to a hand type
	 * 0 = Suited, 1 = pocket, 2 = offsuited, 3 = one combo hand
	 * @param HAND_TYPE
	 */
	public void setHandType(int HAND_TYPE){
		this.HAND_TYPE = HAND_TYPE;

		switch (HAND_TYPE) {
		case 0: suited(); break;
		case 1:	pocket(); break;
		case 2:	offsuited(); break;
		case 3: oneCombo(); break;

		}
	}
	/**
	 * Return hand type
	 * 0 = Suited, 1 = pocket, 2 = offsuited
	 * @return
	 */
	public int getHandType(){
		return HAND_TYPE;
	}
	/*
	 * Set offsuited conditions in hand
	 */
	private void offsuited(){
		COMBOS.clear();
		String h1 = hand.substring(0, 1);
		String h2 = hand.substring(1, 2);
		for (int i = 0; i<4; i++){
			for(int y=0; y<4; y++){
				if(!(i==y)){
					COMBOS.add(h1+suits[i]+h2+suits[y]);
				}
			}
		}

	}
	/*
	 * Set suited conditions in hand
	 */
	private void suited(){
		COMBOS.clear();
		String h1 = hand.substring(0, 1);
		String h2 = hand.substring(1, 2);
		for (int i = 0; i<4; i++){
			COMBOS.add(h1+suits[i]+h2+suits[i]);
		}
	
	}
	/*
	 * Set suited conditions in hand
	 */
	private void pocket(){
		String used_s = "";
		COMBOS.clear();
		String h1 = hand.substring(0, 1);
		String h2 = hand.substring(1, 2);
		for (int i = 0; i<4; i++){
			for(int y=0; y<4; y++){				
				if(!(i==y) && !used_s.contains(suits[y])){
					
					COMBOS.add(h1+suits[i]+h2+suits[y]);
				}
			}
			used_s = used_s + suits[i]; 
		
		}
	}
	
	private void oneCombo(){
		COMBOS.clear();
		COMBOS.add(hand);
	}
	/**
	 * Get hand combos in button
	 * @return 
	 */
	public ArrayList<String> getCombos(){
		return COMBOS;
	}
	
	public void setHand(String hand){
		this.setText(hand);
		this.hand = hand;
	}
	public String getHand(){
		return hand;
	}
	
	

}
