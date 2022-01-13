import java.awt.Dimension;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

public class Main {
	
	
	
	public static void main(String[] args) throws UnsupportedAudioFileException, IOException {
		// TODO Auto-generated method stub
		
		JFrame frame = new MainFrame();
		frame.setPreferredSize(new Dimension(780, 880));
		frame.setLocation(200, 200);
		frame.setTitle("Felix's Minesweeper");
		frame.pack ();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible (true);
		
	}

}
