import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class MainPanel extends JPanel implements Common,MouseListener,Runnable{
	MainFrame mf;
	private Thread thread;
	private Game game;
	private int masu;
	private int mine;

	/*
	 * MainPanelのコンストラクタ
	 *
	 * ・指定されたマス数、爆弾数によるゲーム盤面の作成
	 * ・パネルの大きさの設定
	 * ・ゲーム（M_Sweeper）のゲームループの作成、開始
	 */
	public MainPanel (int masu,int mine,MainFrame mf){
		this.mf = mf;
		this.masu = masu;
		this.mine = mine;
		// 指定された盤面を作成
		game = new Game(this.masu,this.mine);
		// パネルサイズの設定
		setPreferredSize(new Dimension(MY_WIDTH, MY_HEIGHT));
		// マウスを検知の設定
		addMouseListener(this);
		// ゲームループの作成・開始
		thread = new Thread(this);
		thread.start();
	}

	/*
	 * ゲーム画面の描画を行う
	 * repaint()で再描画される
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		// ゲーム画面の描画
		game.draw(g);
	}

	/*
	 * マウスによる処理を行うメソッド
	 *
	 * 右クリック：マスを開ける
	 * 左クリック：目印（Flag）の配置
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		int btn = e.getButton();
		// 右クリック:マスを開ける
		if(btn == MouseEvent.BUTTON1){
			int x = calcPointX(e.getX());// マウス座標からマスの位置を計算
			int y = calcPointY(e.getY());// マウス座標からマスの位置を計算
			// ゲーム内の左クリック処理
			game.clickLeft(y,x);
			// ゲーム内の状態に応じた画面遷移の処理
			if(game.getGameState() == GAMEOVER){
				mf.state = MENU;
			}else if(game.getGameState() == CLEAR){
				mf.state = MENU;
			}
		}
		// 左クリック：目印（Flag）の配置
		if(btn == MouseEvent.BUTTON3){
			int x = calcPointX(e.getX());// マウス座標からマスの位置を計算
			int y = calcPointY(e.getY());// マウス座標からマスの位置を計算
			// ゲーム内の右ククリック処理
			game.clickRight(y,x);
		}
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

	// 指定座標からマス目のY座標を計算するメソッド
	private int calcPointY(int y){
		return y /= MY_HEIGHT / masu;
	}

	// 指定座標からマス目のX座標を計算するメソッド
	private int calcPointX(int x){
		return x /= MY_WIDTH / masu;
	}

	/*
	 * ゲーム（M_Sweeper）をプレイ中のゲームループ
	 */
	@Override
	public void run(){
		//  描画更新を行う
		//  10[ms]待機する
		// 上記の処理を繰り返す
		while(true){
			repaint();//再描画

			try{
				Thread.sleep(10);// 10msの待機
			}catch(InterruptedException e){
			}
		}
	}
}
