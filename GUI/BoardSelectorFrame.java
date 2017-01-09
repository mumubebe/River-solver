package GUI;

import javax.swing.JFrame;


public class BoardSelectorFrame extends JFrame {
	static BoardSelector boardSelector = new BoardSelector();
	public BoardSelectorFrame() {
		
		
		setSize(556,194);
		boardSelector.setBounds(6, 6, 300, 377);
		getContentPane().add(boardSelector);
	}
	
	public static String getBoard(){
		return boardSelector.getSelectedCards();
	}
}
