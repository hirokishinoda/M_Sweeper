import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class MainPanel extends JPanel implements Common,MouseListener,Runnable{
	MainFrame mf;
	Thread thread;
	private Game game;
	private int masu;
	private int mine;

	public MainPanel (int masu,int mine,MainFrame mf){
		this.mf = mf;
		this.masu = masu;
		this.mine = mine;
		game = new Game(this.masu,this.mine);
		setPreferredSize(new Dimension(MY_WIDTH, MY_HEIGHT));
		addMouseListener(this);

		thread = new Thread(this);
		thread.start();
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		// ゲーム画面
		game.draw(g);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int btn = e.getButton();

		System.out.println("Debug");

		if(btn == MouseEvent.BUTTON1){
			int x = e.getX();
			int y = e.getY();

			x /= MY_WIDTH / masu;
			y /= MY_HEIGHT / masu;

			game.clickBoard(y,x);
			if(game.getGameState() == GAMEOVER){
				mf.state = MENU;
			}else if(game.getGameState() == CLEAR){
				mf.state = MENU;
			}
		}
		if(btn == MouseEvent.BUTTON3){
			int x = e.getX();
			int y = e.getY();

			x /= MY_WIDTH / masu;
			y /= MY_HEIGHT / masu;

			game.clickRight(y,x);
		}
		//repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void run() {
		while(true){

			repaint();

			try{
				Thread.sleep(10);
			}catch(InterruptedException e){
			}
		}
	}
}
