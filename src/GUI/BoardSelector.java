package GUI;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;


public class BoardSelector extends JPanel implements MouseListener {
	private String cardNames[]={"A","K","Q","J","T","9","8","7","6","5","4","3","2"};
	private String suits[] = {"h", "s", "d", "c"};
	private HandButton[][] handButton;
	private ArrayList<String> selectedCards = new ArrayList<String>();
	
	public BoardSelector(){
		setSize(500, 500);
		setLayout(new GridLayout(4, 13));
		createButtons();
		this.setVisible(true);
	}
	
	public void createButtons(){
		handButton=new HandButton[4][13];
		for(int i=0; i<suits.length; i++){
			for(int y=0; y<cardNames.length; y++){
				handButton[i][y] = new HandButton(cardNames[y]+suits[i], new Color(255,255,255));
				handButton[i][y].setText("<HTML>"+handButton[i][y].handTextTypeParse(handButton[i][y].getHand()));
				handButton[i][y].addMouseListener(this);
				this.add(handButton[i][y]);
			}
		}
		
	}
	/*
	 * Remove selected card from list
	 */
	private void removeCard(String c){
		selectedCards.remove(c);
	}
	/*
	 * Add selected card from list
	 */
	private void addCard(String c){
		selectedCards.add(c);
	}
	/**
	 * Get selected cards
	 * @return string of selected cards
	 */
	public String getSelectedCards(){
		String c = "";
		for (int i = 0; i<selectedCards.size();i++){
			c+=selectedCards.get(i);
		}
		return c;
	}
	
	
	
	/*
	 * -----------------------------------------------LISTENERS---------------------------------------
	 */

	@Override
	public void mouseClicked(MouseEvent e) {
		HandButton hb = (HandButton)e.getSource();
		
		if(hb.getActive()){
			removeCard(hb.getHand());
			hb.disable();
		

		}else{
			System.out.println(hb.getHand());
			addCard(hb.getHand());
			hb.enable(1);
		
			
			
		
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
