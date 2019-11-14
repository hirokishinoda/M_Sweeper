import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class StartScreenPanel extends JPanel implements Common{
	MainFrame mf;
	private MyFile my_file;
	private SelectButton masu9_btn = new SelectButton("9×9マス,Mine 10",BUTTON_9_Y,BUTTON_X);
	private SelectButton masu16_btn = new SelectButton("16×16マス,Mine 40",BUTTON_16_Y,BUTTON_X);
	private SelectButton masu32_btn = new SelectButton("32×32マス,Mine 120",BUTTON_32_Y,BUTTON_X);

	/*
	 * StartScreenPanelクラスのコンストラクタ
	 * 主にパネルの設定を行う
	 */
	public StartScreenPanel(MainFrame mf){
		this.mf = mf;
		my_file = new MyFile();
		// スコアファイルの読み込み
		my_file.readFile();
		// レイアウトを無効
		setLayout(null);
		// 背景色を設定
		setBackground(Color.black);
		// ボタンを作成し、パネルに追加
        addActionToButton();
        // パネルの大きさの設定
		setPreferredSize(new Dimension(MY_WIDTH, MY_HEIGHT));
	}

	/*
	 * メニュー画面の描画メソッド
	 */
	public void paintComponent(Graphics g){
		 super.paintComponent(g);
		 // タイトル「M_Sweeper!!!」表示の設定（色、フォント、大きさ、表示位置）と描画
		 myDrawString(g,Color.white,"M_Sweeper!!!", TITLE_Y, TITLE_X , 50);
		 myDrawString(g,Color.lightGray,"[Select Mode]", SELECT_MODE_Y, SELECT_MODE_X, 25);
		 myDrawString(g,Color.lightGray,"[Best Scores]", BEST_SCORES_Y, BEST_SCORES_X, 25);
		 // 各種スコアの表示
		 drawScoreBoard(g);
	}

	private void drawScoreBoard(Graphics g){
		//9×9の最短クリアタイム表示
		myDrawString(g,Color.white,"[9×9]Best TIME", SCORE_9_Y , SCORE_X , 20);
		myDrawString(g,Color.lightGray,my_file.array[0] + " seconds", SCORE_9_Y + 30, SCORE_X, 20);
		//16×16の最短クリアタイム表示
		myDrawString(g,Color.white,"[16×16]Best TIME" , SCORE_16_Y , SCORE_X , 20);
		myDrawString(g,Color.lightGray,my_file.array[1] + " seconds", SCORE_16_Y + 30, SCORE_X, 20);
		//32×32の最短クリアタイム表示
		myDrawString(g,Color.white,"[32×32]Best TIME" , SCORE_32_Y , SCORE_X, 20);
		myDrawString(g,Color.lightGray,my_file.array[2] + " seconds", SCORE_32_Y + 30, SCORE_X, 20);
	}

	/*
	 * 文字列を指定位置、指定色で描画するメソッド
	 */
	private void myDrawString(Graphics g,Color c,String str,int y,int x,int str_size){
		g.setColor(c);
    	g.setFont(new Font("Arial",Font.BOLD, str_size));
    	g.drawString(str, x, y);
	}

	/*
	 * パネルに配置するボタンを作成・配置するメソッド
	 */
	private void addActionToButton(){
		// 各ボタンに応じた処理を設定
		//
		// 押されたときにマス目の数と爆弾の数を決定し、
		// モードをゲーム画面に切り替え
		masu9_btn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	mf.state = GAME;
            	mf.game_mode = MODE_9;
            }
        });

        masu16_btn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	mf.state = GAME;
            	mf.game_mode = MODE_16;
            }
        });

        masu32_btn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	mf.state = GAME;
            	mf.game_mode = MODE_32;
            }
        });
        // 各ボタンをパネルに追加
        add(masu9_btn);
        add(masu16_btn);
        add(masu32_btn);
	}
}

class SelectButton extends JButton implements Common{

	/*
	 * SelectButtonクラスのコンストラクタ
	 * ボタンの設定を行う
	 */
	public SelectButton(String name,int y,int x){
		// 表示する文字の設定
		setText(name);
		// 色設定
		//this.setBackground(Color.);
		// マウスによるフォーカスを許可しない
		setFocusPainted(false);
		// 表示位置、大きさの設定
		setBounds(x, y, 200, 40);
		setPreferredSize(new Dimension(300,50));
	}
}