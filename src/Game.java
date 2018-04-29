import java.awt.Graphics;

public class Game implements Common{
	private Board board;
	private Timer timer;
	private MyFile my_file;

	/*
	 * Gameクラスのコンストラクタ
	 *
	 * 各変数のインスタンス化
	 * タイマーの作成・スタート
	 */
	public Game(int masu,int mine){
		board = new Board(masu,mine);
		my_file = new MyFile();
		my_file.readFile();
		// タイマーの作成・スタート
		timer = new Timer();
		timer.timerStart();
	}

	/*
	 * プレイヤーが盤面（マス）を左クリックしたメソッド
	 */
	public void clickLeft(int y,int x){
		// 指定された座標のマスに応じた処理

		// マスが爆弾ならすべてのマスを開き、ゲームオーバー
		// マスが空白なら周りの空白マスを開く
		// 上記以外（そのマス目の周りに爆弾がある）ならそのマスだけを開く
		if(board.board[(y * board.getMasu()) + x].getMineExist()){
			board.openAllMasu();
		}else if(board.board[(y * board.getMasu()) + x].getAroundMineNum() == 0){
			board.openEmptyMasu(y, x);
		}else{
			board.openMasu(y, x);
		}

		// CLEARなら時間を記録
		if(getGameState() == CLEAR){
			if(comparateTime()){
				my_file.writeFile();
			}
			System.out.println(timer.getTime() + " CLEAR" );
		}
	}

	private boolean comparateTime(){
		int index;

		switch(board.getMasu()){
		case 9:
			index = 0;
			break;
		case 16:
			index = 1;
			break;
		case 32:
			index = 2;
			break;
		default:
			return false;
		}

		if(my_file.array[index] == "No Data."){
			my_file.array[index] = String.valueOf(timer.getTime());
			return true;
		}else{
			if(timer.getTime() < Integer.parseInt(my_file.array[index])){
				my_file.array[index] = String.valueOf(timer.getTime());
				return true;
			}
		}

		return false;
	}

	/*
	 * プレイヤーが盤面（マス）を右クリックした時の処理を行うメソッド
	 */
	public void clickRight(int y,int x){
		if(!(board.board[(y * board.getMasu()) + x].getOpen())){
			// 指定マスのFlack（目印）を切り替える
			// Flack(True) -> Flack(False)
			// Flack(False)-> Flack(True)
			boolean flack = board.board[(y * board.getMasu()) + x].getFlack();
			board.board[(y * board.getMasu()) + x].setFlack(!flack);
		}
	}

	/*
	 * ゲーム画面の描画
	 */
	public void draw(Graphics g){
		board.drawBoard(g);
		if(getGameState() != GAME){
			timer.draw(g,false);
		}else{
			timer.draw(g,true);
		}
	}

	/*
	 * 盤面（ゲーム内）の状態を取得するメソッド
	 */
	public int getGameState(){
		return board.getGameState();
	}
}
