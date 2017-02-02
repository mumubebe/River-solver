package org.papeta.riversolver.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.UIManager;

import org.papeta.riversolver.algorithm.Range;

public class RangeSelector extends JPanel implements MouseListener, java.io.Serializable{
	private boolean mousePressed=false;
	private boolean selecting=false;
	private HandButton[][] handButton;
	private Range range;
	private String cardNames[]={"A","K","Q","J","T","9","8","7","6","5","4","3","2"};
	public Range parentRange;
	public boolean relativeWeight = false;
	String combos;
	RangeSelectFrame rsf;
	
	public RangeSelector(){


		setLayout(new GridLayout(13, 13));

		range = new Range();
		
		createButtons(); 
		this.setVisible(true);
		

	}
	
	public RangeSelector(RangeSelectFrame rsf){
		this.rsf = rsf;
		setLayout(new GridLayout(13, 13));

		range = new Range();
		createButtons();
		this.setVisible(true);

	}
	/**
	 * Set buttons to two specific suits
	 * @param s1 
	 * @param s2
	 */
	public void setButtonOffSuit(String s1, String s2){
		System.out.println("draw");
		for(int i=0; i<13; i++){
			for(int j=0;j<13;j++){

				if(i>j){
					handButton[i][j].setHand(cardNames[j]+s1+cardNames[i]+s2);
					handButton[i][j].setWeight(0);
					handButton[i][j].setVisible(true);
					handButton[i][j].setHandType(3);
					if(isHandInRange(handButton[i][j].getCombos())){
						handButton[i][j].enable(getHandWeight(handButton[i][j].getCombos()));

						handButton[i][j].setWeight(getHandWeight(handButton[i][j].getCombos()));
					}

				}
				else if(i<j){ 

					handButton[i][j].setHand(cardNames[i]+s2+cardNames[j]+s1);
					handButton[i][j].setWeight(0);
					handButton[i][j].setVisible(true);
					handButton[i][j].setHandType(3);
					if(isHandInRange(handButton[i][j].getCombos())){
						handButton[i][j].enable(getHandWeight(handButton[i][j].getCombos()));

						handButton[i][j].setWeight(getHandWeight(handButton[i][j].getCombos()));
					}

				}
				else{
					handButton[i][j].setHand(cardNames[j]+s2+cardNames[i]+s1);
					handButton[i][j].setWeight(0);
					handButton[i][j].setVisible(true);
					handButton[i][j].setHandType(3);
					if(isHandInRange(handButton[i][j].getCombos())){
						handButton[i][j].enable(getHandWeight(handButton[i][j].getCombos()));

						handButton[i][j].setWeight(getHandWeight(handButton[i][j].getCombos()));
					}
				}


			}
		}
	}

	/**
	 * Set buttons to a specific suit
	 * @param suit
	 * heart=h, diamond=d, spade=s, clubs=c
	 */
	public void setButtonSuit(String suit){
		System.out.println(range.toString());
		for(int i=0; i<13; i++){
			for(int j=0;j<13;j++){

				if(i<j){ 		
					handButton[i][j].setVisible(true);
					handButton[i][j].setHand(cardNames[i]+suit+cardNames[j]+suit);

					handButton[i][j].setWeight(0);					
					handButton[i][j].setHandType(3);
					if(isHandInRange(handButton[i][j].getCombos())){
						handButton[i][j].enable(getHandWeight(handButton[i][j].getCombos()));
						handButton[i][j].setWeight(getHandWeight(handButton[i][j].getCombos()));
						
					}

				}
			}
		}
	}
	/**
	 * Set buttons to default mode
	 * 
	 */

	public void setButtonAll(){
		for(int i=0; i<13; i++){
			for(int j=0;j<13;j++){

				if(i>j){
					handButton[i][j].disable();
					handButton[i][j].setHand(cardNames[j]+cardNames[i]+"o");
					handButton[i][j].setWeight(0);
					handButton[i][j].setVisible(true);
					handButton[i][j].setHandType(2);
					if(isHandInRange(handButton[i][j].getCombos())){
						
							
						
						handButton[i][j].enable(getHandWeight(handButton[i][j].getCombos()));
						handButton[i][j].setWeight(getHandWeight(handButton[i][j].getCombos()));
						
					}

				}
				else if(i<j){ 
					handButton[i][j].disable();
					handButton[i][j].setHand(cardNames[i]+cardNames[j]+"s");
					handButton[i][j].setWeight(0);
					handButton[i][j].setVisible(true);
					handButton[i][j].setHandType(0);
					if(isHandInRange(handButton[i][j].getCombos())){
						if(isHandInRange(handButton[i][j].getCombos())){
							
								
							
							handButton[i][j].enable(getHandWeight(handButton[i][j].getCombos()));
							handButton[i][j].setWeight(getHandWeight(handButton[i][j].getCombos()));
							
						}
					}

				}
				else{
					handButton[i][j].disable();
					handButton[i][j].setHand(cardNames[j]+cardNames[i]);
					handButton[i][j].setWeight(0);
					handButton[i][j].setVisible(true);
					handButton[i][j].setHandType(1);
					if(isHandInRange(handButton[i][j].getCombos())){
						if(isHandInRange(handButton[i][j].getCombos())){
							
								
							
							handButton[i][j].enable(getHandWeight(handButton[i][j].getCombos()));
							handButton[i][j].setWeight(getHandWeight(handButton[i][j].getCombos()));
							
						}
					}
				}


			}
		}
	}




	/*
	 * Create button objects for offhand, suited and pocket.
	 */
	private  void createButtons(){

		try {
			UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
		} catch (Exception e) {
			e.printStackTrace();
		}

		handButton=new HandButton[13][13];
		for(int i=0; i<13; i++){
			for(int j=0;j<13;j++){


				if(i>j){
					handButton[i][j]=new HandButton(cardNames[j]+cardNames[i]+"o", new Color(255,255,255)); //232,238,255
					handButton[i][j].addMouseListener(this);
					handButton[i][j].setWeight(0);
					handButton[i][j].setHandType(2);
				}
				else if(i<j){ 


					handButton[i][j]=new HandButton(cardNames[i]+cardNames[j]+"s", new Color(255,255,255));  //255,232,237
					handButton[i][j].addMouseListener(this);
					handButton[i][j].setWeight(0);
					handButton[i][j].setHandType(0);
				}
				else{
					handButton[i][j]=new HandButton(cardNames[i]+cardNames[j], new Color(255,255,255));  //233,255,232
					handButton[i][j].addMouseListener(this);
					handButton[i][j].setWeight(0);
					handButton[i][j].setHandType(1);
				}

				this.add(handButton[i][j]);

			}
		}
	}
	/**
	 * Clear buttons and range. New fresh table.
	 */
	public void clearRange(){
		removeButtons();
		range.setAllValuesZero();
		setButtonAll();
	}

	/**
	 * Set all hands visible=false
	 */
	public void removeButtons(){
		for(int i=0; i<13; i++){
			for(int j=0;j<13;j++){
				handButton[i][j].setVisible(false);
				handButton[i][j].disable();
			}
		}
	}

	/**
	 * check total hand weight for a hand
	 * @param hands
	 * @return total hand weight
	 */
	public double getHandWeight(ArrayList<String> hands){
		double n = hands.size();
		double w_sum = 0;
		
		for(int i=0; i<n; i++){

			w_sum += range.getValue(Range.handToID(hands.get(i)));
		}


		return Math.round(w_sum/n * 100.0) / 100.0;
	}
	


	/**
	 * get an arraylist with all activated hands on a specific button
	 * @param hb HandButton
	 * @return arraylist with activated all combos
	 */
	public ArrayList<String> getActivatedHandsOnButton(HandButton hb){
		ArrayList<String> h = hb.getCombos();
		ArrayList<String> a = new ArrayList<String>();

		for(int i=0;i<h.size();i++){
			if(range.getValue(Range.handToID(h.get(i)))>0){
				a.add(h.get(i));
			}

		}
		return a;

	}

	/**
	 * Check if hand is in Range
	 * @param hand
	 * @return boolean if it's true
	 */
	public boolean isHandInRange(ArrayList<String> hands){
		for(int i=0;i<hands.size();i++){
			if(range.getValue(Range.handToID(hands.get(i)))>0){
				return true;
			}
		}

		return false;
	}

	/**
	 * remove hands(arrayList) in Range, 
	 * @param hands 
	 */	
	public void removeFromRange(ArrayList<String> hands){

		for(int i=0; i<hands.size();i++){		
			range.setValue(Range.handToID(hands.get(i)),0.0);


		}		
	}

	/**
	 * Add hands(arrayList) in Range, 
	 * @param hands 
	 */	
	public void addToRange(ArrayList<String> hands){

		for(int i=0; i<hands.size();i++){	

			range.setValue(Range.handToID(hands.get(i)),1);
		}		
	} 

	public void setRange(Range range){
		this.range = range;
		setButtonAll();
		updateComboLabel();

	}
	/**
	 * set tooltip for hand object
	 * @param hb
	 */
	private void setHandButtonToolTip(HandButton hb){
		ArrayList<String> h = getActivatedHandsOnButton(hb);
		String toolTip = "<HTML>";
		double weight = 0.0;

		for(int i=0; i<h.size(); i++){
			if(range.getValue(Range.handToID(h.get(i)))>0.01){

				weight = Math.round(range.getValue(Range.handToID(h.get(i)))*100)/100.0;
				toolTip += hb.handTextTypeParse(h.get(i))+"("+weight+")"+"<br>";
			}

		}

		if(toolTip.contains("(")){
			hb.setToolTipText(toolTip);

		}else{
			hb.setToolTipText("");

		}
	}
	
	


	/**
	 * Get range
	 * @return
	 */
	public Range getRange(){

		return range;
	}


	/*
	 * LISTENERS ----------------------------------------------------------
	 */

	void setMousePressed(boolean b){
		mousePressed=b;
	}

	boolean getMousePressed(){
		return mousePressed;

	}

	void setSelecting(boolean b){
		selecting=b;


	}

	boolean getSelecting(){
		return selecting;
	}

	public void mousePressed(MouseEvent me){

		this.setMousePressed(true);
		HandButton hb = (HandButton)me.getSource();

		if(hb.getActive()){

			this.setSelecting(true); 
			hb.disable();
			hb.setWeight(0);
			removeFromRange(hb.getCombos());



		}else{

			this.setSelecting(false);
			hb.enable(1);
			hb.setWeight(1);
			addToRange(hb.getCombos());


		}



	}
	
	/**
	 * Set number of combos on combo count-label
	 * @param c
	 */
	public void setNumberOfCombos(Double c){
		//Combinations converting String
		c = (double) Math.round(c*100)/100;
		String n_c = Double.toString(c);

		//Percentage and converting to String
		double p = Math.round(c/1326*100.00);
		String ps = (Double.toString(p));
		
		
		combos =  "Combos "+n_c+" ("+ps+"%)";		

	}
	
	private void updateComboLabel(){
		if(!(rsf == null)){
			setNumberOfCombos(range.getCombos());
			rsf.comboLabel.setText(combos);
		}
	}
	
	
	
	public void mouseReleased(MouseEvent me){
		this.setMousePressed(false);
		//When mouse released, count number of combination and show it on label in mainFrame-object
		updateComboLabel();
			
			

	}
	public void mouseClicked(MouseEvent me){}
	public void mouseEntered(MouseEvent me){

		HandButton hb = (HandButton)me.getSource();
		//tooltip
		setHandButtonToolTip(hb);

		if(this.getMousePressed()){
			if(!this.getSelecting()){
				hb.enable(1);
				hb.setWeight(1);
				addToRange(hb.getCombos());


			}else{
				hb.disable();
				hb.setWeight(0);
				removeFromRange(hb.getCombos());

			}
		}

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}


}
