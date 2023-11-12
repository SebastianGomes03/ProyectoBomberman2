package main;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JFrame;

import utilz.LoadSave;

public class GameWindow extends JFrame{


	public GameWindow(GamePanel gamePanel) {

		setIconImage(LoadSave.GetSpriteAtlas(LoadSave.ICON));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(gamePanel);
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		addWindowFocusListener(new WindowFocusListener() {
			
			@Override
			public void windowLostFocus(WindowEvent e) {
				gamePanel.getGame().windowFocusLost();
			}

			@Override
			public void windowGainedFocus(WindowEvent e) {
				// TODO Auto-generated method stub

			}
		});

	}

}