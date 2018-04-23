import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Mine implements Common{
	ImageIcon mine_img;
	int cell_size;

	public Mine(int cs){
		mine_img = new ImageIcon("./img/suika.jpg");
		this.cell_size = cs;
	}

	public void drawMine(Graphics g,int y,int x){
		g.setColor(Color.black);
		g.fillOval(x * cell_size+ (cell_size/2)/2,y * cell_size+ (cell_size/2)/2,cell_size/2,cell_size/2);
	}
}
