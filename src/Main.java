import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {
	public static void main(String args[]){
		MainFrame frame = new MainFrame("MineSweeper!");
		frame.setVisible(true);
	}
}

class MainFrame extends JFrame implements Runnable,Common{
	Thread game_loop;
	int game_mode = 0;
	int state = 0;
	int old_state = 0;

	public MainFrame(String title) {
		// タイトル、大きさ、ウィンドウの×ボタンの設定
		setTitle(title);
	    setResizable(false);

	    // パネルの設定
        Container contentPane = getContentPane();
        contentPane.add(new StartScreenPanel(this));

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        game_loop = new Thread(this);
        game_loop.start();
	}

	//画面切り替え用メソッド
	public void change(JPanel panel) {
		//ContentPaneにはめ込まれたパネルを削除
		getContentPane().removeAll();

		super.add(panel);//パネルの追加
		validate();//更新
		repaint();//再描画
	}

	@Override
	public void run() {
		while(true){
			if(state != old_state){

				if(state == MENU){
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
