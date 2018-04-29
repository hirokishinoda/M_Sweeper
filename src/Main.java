import java.awt.Container;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {
	// mainメソッド（最初に呼ばれるメソッド）
	// フレームを作成する
	public static void main(String args[]){
		MainFrame frame = new MainFrame("MineSweeper!");
		frame.setVisible(true);
	}
}

class MainFrame extends JFrame implements Runnable,Common{
	private Thread game_loop;
	int game_mode = 0;
	int state = 0;
	int old_state = 0;

	/*
	 * MainFrameクラスのコンストラクタ
	 *
	 * Frameを作成するための準備を行う
	 * ・タイトルの設定
	 * ・パネルの準備
	 * ・ゲームループの準備、開始
	 */
	public MainFrame(String title) {
		// タイトル、大きさ変更許可しない、ウィンドウの×ボタンの設定
		setTitle(title);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    // パネルの設定（メニュー画面）
        Container contentPane = getContentPane();
        contentPane.add(new StartScreenPanel(this));
        pack();

        // ゲームループの準備・開始
        game_loop = new Thread(this);
        game_loop.start();
	}

	/*
	 * 画面切り替え用メソッド
	 */
	public void change(JPanel panel) {
		//ContentPaneにはめ込まれたパネルを削除
		getContentPane().removeAll();

		super.add(panel);//パネルの追加
		validate();//更新
		repaint();//再描画
	}

	/*
	 * ゲームループ(メインとなるゲームループ)
	 *
	 * 画面遷移の処理も行う
	 */
	@Override
	public void run() {
		while(true){
			// 画面遷移が行われたら（画面の状態が以前と変わったら）処理を行う
			//
			// 状態がMENUだったらメニュー画面に切り替え
			// 状態がGAMEだったらゲーム画面に切り替え
			// (メニュー画面で押されたボタンによってマスと爆弾の数が決まり
			//  その項目に応じたマスと爆弾の数のゲームを開始する)
			if(state != old_state){
				if(state == MENU){
					try {
						TimeUnit.SECONDS.sleep(3);
					} catch (InterruptedException e1) {
					}
					change(new StartScreenPanel(this));
				}else if(state == GAME){
					switch(game_mode){
					case MODE_9:
						change(new MainPanel(9,10,this));
						break;
					case MODE_16:
						change(new MainPanel(16,40,this));
						break;
					case MODE_32:
						change(new MainPanel(32,120,this));
						break;
					}
				}
				old_state = state;
			}

			try{
				Thread.sleep(20);
			}catch(InterruptedException e){
			}
		}
	}
}
