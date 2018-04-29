import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Square implements Common{
	private Mine mine;
	private int around_mine_num;

	public boolean mine_exist;
	private boolean open;
	private boolean flack;

	private  int cell_size;

	/*
	 * 文字などを描画する際の表示位置を決めるメソッド
	 */
	private int pointX(int x){ return x * cell_size;}
	private int pointY(int y){ return y * cell_size;}
	private int offsetX(){return (cell_size/2)/2;}
	private int offsetY(){return (cell_size) - cell_size/2;}


	/*
	 * Squareクラスのコンストラクタ
	 */
	public Square(int cs,boolean mine_exist){
		this.cell_size = cs;
		this.mine_exist = mine_exist;

		open = false;
		setFlack(false);

		if(this.mine_exist){
			mine = new Mine(cell_size);
			around_mine_num = 9;
		}else{
			mine = null;
			around_mine_num = 0;
		}
	}

	/*
	 * 描画メソッド
	 */
	public void draw(Graphics g,int y,int x){
        if(open){
        	// マス目が開いている時の描画
        	myFillRect(g,Color.white,y,x);
        	if(mine_exist){
            	mine.drawMine(g,y,x);
            }else if(around_mine_num != 0){
            	myDrawString(g,Color.red,String.valueOf(around_mine_num),y,x);
            }
        }else{
        	// マスが開いていないときの描画
        	myFillRect(g,Color.gray,y,x);
            if(flack){
            	// マスにFlackを立てた時の描画
                myFillRect(g,Color.yellow,y,x);
            	myDrawString(g,Color.blue,"F",y,x);
            }
        }
        // 縁の描画
        g.setColor(Color.lightGray);
    	g.drawRect(pointX(x), pointY(y), cell_size, cell_size);
	}

	/*
	 * 文字列を指定位置、指定色で描画するメソッド
	 */
	private void myDrawString(Graphics g,Color c,String str,int y,int x){
		g.setColor(c);
    	g.setFont(new Font("Arial",Font.BOLD, cell_size/2));
    	g.drawString(str, pointX(x) + offsetX(),pointY(y) + offsetY());
	}

	/*
	 * 四角を塗りつぶして、指定座標に描画するメソッド
	 */
	private void myFillRect(Graphics g,Color c,int y,int x){
		g.setColor(c);
        g.fillRect(pointX(x), pointY(y), cell_size, cell_size);
	}

	/*--------------------------------------------------------
	 * 各変数のsetter,getterを以下に記述
	 *-------------------------------------------------------*/
	public void setAroundMineNum(int num){
		this.around_mine_num = num;
	}

	public int getAroundMineNum(){
		return this.around_mine_num;
	}

	public boolean getMineExist(){
		return this.mine_exist;
	}

	public boolean getOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public boolean getFlack() {
		return flack;
	}

	public void setFlack(boolean flack) {
		this.flack = flack;
	}

}
