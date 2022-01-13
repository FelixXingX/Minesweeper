import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.sound.sampled.*;
import java.awt.Font;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.GridLayout;

public class MainFrame extends JFrame {
	
	static final int R = 13;
	static final int C = 13;
	static int mineCount = 30;
	
	static JButton button;
	static JButton end = new JButton("End"), restart = new JButton("Restart");
	static JLabel label, rules, safe, counter;
	static JPanel myPanel;
	static JPanel subP;
	static MineButton[][] buttons;

	static File bg = new File("background.wav");
	static File vc = new File("victory.wav");
	static File de = new File("deth.wav");
	static File eg = new File("endgame.wav");
	static AudioInputStream audioStream;
	static Clip background;
	static Clip endgame;
	static Clip deth;
	static Clip victory;
	{
		try {
			audioStream = AudioSystem.getAudioInputStream(bg);
			background = AudioSystem.getClip();
			background.open(audioStream);
			audioStream = AudioSystem.getAudioInputStream(vc);
			victory = AudioSystem.getClip();
			victory.open(audioStream);
			audioStream = AudioSystem.getAudioInputStream(de);
			deth = AudioSystem.getClip();
			deth.open(audioStream);
			audioStream = AudioSystem.getAudioInputStream(eg);
			endgame = AudioSystem.getClip();
			endgame.open(audioStream);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}




	static int num = 0;
	public static void but(){

		end.addActionListener((ActionEvent e) -> {
			System.exit(0);
		});
		restart.addActionListener((ActionEvent e) -> {
			deth.stop();
			myPanel.removeAll();
			subP = new JPanel();
			subP.setLayout(new GridLayout(R, C));
			generateMines();
			myPanel.add(subP);
			myPanel.add(counter);
			myPanel.revalidate();
            myPanel.repaint();
			background.loop(Clip.LOOP_CONTINUOUSLY);
            num = 0;
		});
		myPanel.add(end);
		myPanel.add(restart);
	}
	public static void isWin(){
		num = num + 1;

		if (num == 139){
			MainFrame.victoryRoyale();
		}
	}
	public static void victoryRoyale(){
		endgame.stop();
		victory.start();
		victory.setMicrosecondPosition(0);
		for (MineButton[] list : buttons) {
			for (MineButton b : list) {
				b.disabled = true;
			}
		}
		MainFrame.but();
		
	}
	public static void endScreen(){
		background.stop();
		endgame.stop();
		deth.start();
		deth.setMicrosecondPosition(0);
		for (MineButton[] list : buttons) {
			for (MineButton b : list) {
				b.disabled = true;
			}
		}
		MainFrame.but();		
	}
	public static void decreaseMineC() {
		mineCount--;
		if (mineCount == 10){
			background.stop();
			endgame.loop(Clip.LOOP_CONTINUOUSLY);
		}
		counter.setText(String.valueOf(mineCount));
		}
	public static void increaseMineC() {
		mineCount++;
		if (mineCount == 11){
			endgame.stop();
			background.loop(Clip.LOOP_CONTINUOUSLY);
		}
		counter.setText(String.valueOf(mineCount));
		
	}
	
	public static void generateMines() {
		mineCount = 30;
		counter.setText(String.valueOf(mineCount));
		buttons = new MineButton[R][C];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				buttons[i][j] = new MineButton(false, i, j);
				subP.add(buttons[i][j]);
			}
		}
		
		// generating mines
		int counterr = 0;
		for(int count = 1; count <= mineCount; count++){
			int rand = (int)(Math.random()*R);
			int rand2 = (int)(Math.random()*C);
			
			if (buttons[rand][rand2].isMine) {
				count = count - 1;
			}
			else {
				buttons[rand][rand2].isMine = true;
				counterr++;
				
			}
			
		}
	}
	public static URL getCompleteURL (String fileName)
	{
		try
		{
			return new URL ("file:" + System.getProperty ("user.dir") + "/" + fileName);
		}
		catch (MalformedURLException e)
		{
			System.err.println (e.getMessage ());
		}
		return null;
	}
	public MainFrame() throws UnsupportedAudioFileException, IOException {
		
		try {
            // Set System L&F
			UIManager.setLookAndFeel(
            UIManager.getSystemLookAndFeelClassName());
		} 
		catch (UnsupportedLookAndFeelException e) {
			// handle exception
		}
		catch (ClassNotFoundException e) {
			// handle exception
		}
		catch (InstantiationException e) {
			// handle exception
		}
		catch (IllegalAccessException e) {
			// handle exception
		}
		counter = new JLabel(String.valueOf(mineCount));
		counter.setFont(new Font("Comic sans ms", Font.PLAIN, 25));
		
		myPanel = new JPanel();
		subP = new JPanel();
		subP.setLayout(new GridLayout(R, C));
		
		label = new JLabel ("Welcome to Minesweeper!");
		rules = new JLabel (new ImageIcon (""));
		rules.setAlignmentX (JLabel.TOP_ALIGNMENT);
		button = new JButton("Play");
		button.addActionListener((ActionEvent e) -> {
			String eventName = e.getActionCommand();
			if (eventName.equals ("Play")) {
				label.setText("");
				rules.setIcon (new ImageIcon ("Rules.gif"));
				button.setText("Next");
				button.addActionListener((ActionEvent e2) -> {
					generateMines();
					myPanel.remove(rules);
					myPanel.remove(button);
					myPanel.add(subP);
					myPanel.add(counter);
					background.loop(Clip.LOOP_CONTINUOUSLY);
					myPanel.revalidate();
		            myPanel.repaint();
		            
				});
			}
		});
		

		myPanel.add(label);
		myPanel.add(rules);
		myPanel.add(button);
		this.add(myPanel);
		
		
	}

	

	
}
