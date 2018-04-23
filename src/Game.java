import java.awt.Graphics;

public class Game {
	private Board board;
	Timer timer;
	private Player player;

	public Game(int masu,int mine){
		board = new Board(masu,mine);
		player = new Player(board);
		timer = new Timer();
		timer.timerStart();
	}

	public void clickBoard(int y,int x){
		player.clickOnBoard(y, x);
	}

	public void clickRight(int y,int x){
		player.rightClickOnBoard(y, x);
	}

	public void draw(Graphics g){
		board.drawBoard(g);
		timer.draw(g);
	}

	public int getGameState(){
		return board.getGameState();
	}
}
