import java.awt.Graphics;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Board implements Common{
	public Square board[];
	private int masu;
	private int mine;
	private int game_state;
	private int open_masu_num;

	/*
	 * Board（盤面）クラスのコンストラクタ
	 *
	 * masu : 盤面の大きさ〈 毎回ゲーム開始時に決定 〉
	 * mine : 地雷の数〈 毎回ゲーム開始時に決定 〉
	 *
	 * initBoard() : 盤面の初期化を行う
	 */
	public Board(int masu,int mine){
		this.setMasu(masu);
		this.mine = mine;
		game_state = GAME;
		open_masu_num = 0;

		initBoard();
	}

	/*
	 * Board（盤面）の初期化、地雷の配置を行う
	 */
	private void initBoard(){
		board = new Square[this.getMasu() * this.getMasu()];

		//盤面の初期化と地雷を必要数分配置
		for(int y = 0;y < getMasu();y++){
			for(int x = 0;x < getMasu();x++){
				// 0~（地雷必要数分）の個数なら配置
				// 配置が完了しているなら地雷は置かない
				if((toOneDimention(y,x)) < mine){
					board[toOneDimention(y,x)] = new Square(MY_WIDTH / getMasu(),true);
				}else{
					board[toOneDimention(y,x)] = new Square(MY_WIDTH / getMasu(),false);
				}
			}
		}
		// 盤面をシャッフルすることによって爆弾をランダム配置
		shuffleBoard();
		// デバッグ用
		disp();
		System.out.println();
		// 爆弾のあるマスを探してその周囲のマスの隣接爆弾数のカウントを＋1する
		for(int y = 0;y < getMasu();y++){
			for(int x = 0;x < getMasu();x++){
				if((board[toOneDimention(y,x)].getMineExist())){
					checkAroundMine(y,x);
				}
			}
		}
	}

	/*
	 * 盤面をシャッフルすることによって爆弾の配置をランダムにする
	 */
	private void shuffleBoard(){
		// 配列からListへ変換する
	    List<Square> list = Arrays.asList(board);
	    // リストの並びをシャッフする
	    Collections.shuffle(list);
	    // listから配列へ戻する
	    board =(Square[])list.toArray(new Square[list.size()]);
	}

	/*
	 * 指定された座標の（爆弾のある）周囲8マスのそれぞれの爆弾が周囲にある数を1増やす
	 */
	private void checkAroundMine(int y,int x){

		// 指定座標から見た周囲8マスの座標のオフセット値
		int offset[][] =  {	{-1,-1},{-1,0},{-1,1},
							{0,-1}        , {0,1},
							{1,-1} , {1,0}, {1,1}};
		int tmp_y,tmp_x;
		int tmp_num;

		for(int i = 0;i < 8;i++){
			// 周囲のマスの座標を計算し一時的に保持
			tmp_y = y + offset[i][1];
			tmp_x = x + offset[i][0];
			// 上で計算した座標が正方形盤面内に存在することを確かめ、
			// その座標の周囲の爆弾の数を1増やす
			if(checkArea(tmp_y,tmp_x)){
				tmp_num = (board[toOneDimention(tmp_y,tmp_x)].getAroundMineNum()) + 1;
				board[toOneDimention(tmp_y,tmp_x)].setAroundMineNum(tmp_num);
			}
		}
	}

	/*
	 * 調べたい座標のマスが正方形の盤面上に存在するか調べる
	 * 存在すればtrueを存在しなければfalseを返す
	 */
	private boolean checkArea(int y,int x){
		if(y >= 0 && x >= 0 && y < getMasu() && x < getMasu() ){
			if(!(board[toOneDimention(y,x)].getMineExist())){
				return true;
			}
		}
		return false;
	}

	/*
	 * 指定された座標を開く
	 */
	public void openMasu(int y,int x){
		if(!board[toOneDimention(y,x)].getOpen()){
			board[toOneDimention(y,x)].setOpen(true);
			open_masu_num++;
		}
		if(open_masu_num >= (masu*masu) - mine){
			game_state = CLEAR;
		}
		System.out.println(open_masu_num);
	}

	/*
	 * 全てのマスを開く
	 */
	public void openAllMasu(){
		for(int y = 0;y < getMasu();y++){
			for(int x = 0;x < getMasu();x++){
				board[toOneDimention(y,x)].setOpen(true);
			}
		}
		game_state = GAMEOVER;
	}

	/*
	 *指定された座標の周辺の空白マスを開く
	 */
	public void openEmptyMasu(int y,int x){
		// 指定座標から見た周囲8マスの座標のオフセット値
		int offset[][] =  {	{-1,-1},{-1,0},{-1,1},
							{0,-1}        , {0,1},
							{1,-1} , {1,0}, {1,1}};
		int tmp_y,tmp_x;

		openMasu(y,x);//指定されたマスを開く
		if(board[toOneDimention(y,x)].getAroundMineNum() == 0){
			for(int i = 0;i < 8;i++){
				// 周囲のマスの座標を計算し一時的に保持
				tmp_y = y + offset[i][1];
				tmp_x = x + offset[i][0];
				// 空のマスを連鎖的に開く処理（再帰）
				if(checkArea(tmp_y,tmp_x) && !(board[toOneDimention(tmp_y,tmp_x)].getOpen())){
					openEmptyMasu(tmp_y,tmp_x);
				}
			}
			return;
		}
		return;
	}

	/*
	 * 二次元配列的考えを一次元配列敵考えにする
	 */
	private int toOneDimention(int y,int x){
		return ((y * getMasu()) + x);
	}

	/*
	 * 盤面を描画する
	 *
	 * それぞれのマスに対してdraw()を呼び出して描画
	 */
	public void drawBoard(Graphics g) {
        for (int y = 0; y < getMasu(); y++) {
            for (int x = 0; x < getMasu(); x++) {
            	board[toOneDimention(y,x)].draw(g,y,x);
            }
        }
        //disp();
    }

	/*
	 * コンソール表示用メソッド（デバッグ用）
	 */
	private void disp(){
		for (int y = 0; y < getMasu(); y++) {
            for (int x = 0; x < getMasu(); x++) {
            	System.out.print(board[toOneDimention(y,x)].getAroundMineNum() + ",");
            }
            System.out.println();
        }
	}

	/*----------------------------------
	 * 各変数のgetter,setterを以下に記述
	 ---------------------------------*/
	public int getMasu() {
		return masu;
	}

	public void setMasu(int masu){
		this.masu = masu;
	}

	public int getGameState(){
		return game_state;
	}

}
