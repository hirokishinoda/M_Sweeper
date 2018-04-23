public class Player implements Common{
	Board board;

	public Player(Board board){
		this.board = board;
	}

	public void clickOnBoard(int y,int x){
		if(board.board[(y * board.getMasu()) + x].getMineExist()){
			board.openAllMasu();
		}else if(board.board[(y * board.getMasu()) + x].getAroundMineNum() == 0){
			board.openEmptyMasu(y, x);
		}else{
			board.openMasu(y, x);
		}
	}

	public void rightClickOnBoard(int y,int x){
		if(!(board.board[(y * board.getMasu()) + x].getOpen())){
			boolean flack = board.board[(y * board.getMasu()) + x].getFlack();
			board.board[(y * board.getMasu()) + x].setFlack(!flack);
		}
	}

}
