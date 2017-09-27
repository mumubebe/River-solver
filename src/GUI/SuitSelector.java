package GUI;
import javax.swing.JPanel;
import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;


public class SuitSelector extends JPanel implements java.io.Serializable {
	RangeSelector rangeSelector;
	public ArrayList<String> selectedSuits = new ArrayList<String>();

	public  SuitSelector(RangeSelector rangeSelector){
		this.rangeSelector = rangeSelector;

		final JCheckBox heart = new JCheckBox("<HTML> <FONT SIZE = 4><FONT COLOR = red>♥</FONT></FONT>");
		heart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(heart.isSelected()){	

					selectedSuits.add("h");
					drawButtons();
					System.out.println(selectedSuits);
				}else{

					removeSuit("h");
					drawButtons();
				}
			}
		});

		add(heart);

		final JCheckBox club = new JCheckBox("<HTML> <FONT SIZE = 4><FONT COLOR = green>♣</FONT></FONT>");
		
		club.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(club.isSelected()){

					selectedSuits.add("c");
					drawButtons();
				}else{
					removeSuit("c");
					drawButtons();
				}
			}
		});
		add(club);

		final JCheckBox spade = new JCheckBox("<HTML> <FONT SIZE = 4><FONT COLOR = black>♠</FONT></FONT>");
		spade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(spade.isSelected()){

					selectedSuits.add("s");
					drawButtons();
				}else{
					removeSuit("s");
					drawButtons();
				}
			}
		});
		add(spade);

		final JCheckBox diamond = new JCheckBox("<HTML> <FONT SIZE = 4><FONT COLOR = blue>♦</FONT></FONT>");
		diamond.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(diamond.isSelected()){

					selectedSuits.add("d");
					drawButtons();
				}else{

					removeSuit("d");
					drawButtons();

				}
			}
		});
		add(diamond);



	}
	/**
	 * remove suit from activated suit-list
	 * @param s
	 */

	private void removeSuit(String s){
		for(int i=0; i<selectedSuits.size();i++){
			if (s.equals(selectedSuits.get(i))){
				selectedSuits.remove(i);
				break;
			}
		}

	}

	/**
	 * get current suit activated
	 * @return
	 */
	public  ArrayList<String> getSuit(){
		return selectedSuits;
	}

	/**
	 * calling setButtonAll-method in rangeSelector object
	 */
	public void setButtonAll(){
		rangeSelector.setButtonAll();
	}

	/**
	 * calling methods for draw buttons in object rangeSelector
	 * @param suit
	 */
	public void drawButtons(){
		//0 suits selected
		if(this.getSuit().size()==0){
			setButtonAll();	
		}
		//1 suits selected
		else if(this.getSuit().size()==1){
			rangeSelector.removeButtons();
			rangeSelector.setButtonSuit(getSuit().get(0));
			//2 suits selected
		}else if(this.getSuit().size()==2){
			rangeSelector.removeButtons();
			rangeSelector.setButtonOffSuit(getSuit().get(0), getSuit().get(1));
		}

	}



}
