import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class MineButton extends JButton {


	
	final MineButton dummy = this;
	boolean isMine;
	int x, y;
	// 0 - unopened
	// 1 - flagged
	// 2 - opened
	int state;
	boolean disabled = false;
	int num;
	public MineButton(boolean _isMine, int _x, int _y) {
		super();
		this.x = _x;
		this.y = _y;
		this.setPreferredSize(new Dimension(60, 60));
		
		this.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if (disabled) return;
				if (e.getButton() == MouseEvent.BUTTON3) {
					if (state == 1) {
						state = 0;
						dummy.setIcon(new ImageIcon(""));
						MainFrame.increaseMineC();
					}
					else if (state == 0){
						state = 1;
						dummy.setIcon(new ImageIcon("flag.gif"));
						MainFrame.decreaseMineC();
					}
					
				}
				if (e.getButton() == MouseEvent.BUTTON1) {
					if (state == 0) {
						
						state = 2;
						if (dummy.isMine) {
							dummy.setIcon(new ImageIcon("mine.gif"));
							MainFrame.endScreen();
						}
						else {
							int c = countMines();
							if (c == 0){
								dummy.setIcon(new ImageIcon("num0.gif"));
							}
							if (c == 1){
								dummy.setIcon(new ImageIcon("num1.gif"));
							}
							if (c == 2){
								dummy.setIcon(new ImageIcon("num2.gif"));
							}
							if (c == 3){
								dummy.setIcon(new ImageIcon("num3.gif"));
							}
							if (c == 4){
								dummy.setIcon(new ImageIcon("num4.gif"));
							}
							if (c == 5){
								dummy.setIcon(new ImageIcon("num5.gif"));
							}
							if (c == 6){
								dummy.setIcon(new ImageIcon("num6.gif"));
							}
							if (c == 7){
								dummy.setIcon(new ImageIcon("num7.gif"));
							}
							if (c == 8){
								dummy.setIcon(new ImageIcon("num8.gif"));
							}
						}
						MainFrame.isWin();
						// check if it is mine
					}
				}
				
			}
			
		});
		isMine = _isMine;
	}
	
	private int countMines() {
		int count = 0;
		if (x != 0 && y != 0 && MainFrame.buttons[x - 1][y - 1].isMine) {
			count++;
		}
		if (x != MainFrame.R - 1 && y != MainFrame.C -1 && MainFrame.buttons[x + 1][y + 1].isMine) {
			count++;
		}
		if (x != MainFrame.R - 1 && y != 0 && MainFrame.buttons[x + 1][y - 1].isMine) {
			count++;
		}
		if (x != 0 && y != MainFrame.C - 1 && MainFrame.buttons[x - 1][y + 1].isMine) {
			count++;
		}
		if (x != 0 && MainFrame.buttons[x - 1][y].isMine) {
			count++;
		}
		if (y != 0 && MainFrame.buttons[x][y - 1].isMine) {
			count++;
		}
		if (x != MainFrame.R - 1 && MainFrame.buttons[x + 1][y].isMine) {
			count++;
		}
		if (y != MainFrame.C - 1 && MainFrame.buttons[x][y + 1].isMine) {
			count++;
		}
		
		return count;
	}

}
