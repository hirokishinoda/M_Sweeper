import java.awt.Color;
import java.awt.Graphics;

public class Mine implements Common{
	private int cell_size;

	/*
	 * Mineクラスのコンストラクタ
	 */
	public Mine(int cs){
		this.cell_size = cs;
	}

	/*
	 * 爆弾の描画
	 *
	 * 黒色の丸をマス内に描画
	 */
	public void drawMine(Graphics g,int y,int x){
		g.setColor(Color.black);
		g.fillOval(x * cell_size + (cell_size/2)/2,y * cell_size + (cell_size/2)/2,cell_size/2,cell_size/2);
	}
}
