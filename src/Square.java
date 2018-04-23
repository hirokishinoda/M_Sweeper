import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Square implements Common{
	private Mine mine;
	private int around_mine_num;

	public boolean mine_exist;
	private boolean open;
	private boolean flack;

	private int cell_size;
	/*private int coordinate_y;
	private int coordinate_x;*/

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

	public void draw(Graphics g,int y,int x){
        if(open){
        	g.setColor(Color.white);
            g.fillRect(x * cell_size, y * cell_size, cell_size, cell_size);
        	if(mine_exist){
            	mine.drawMine(g,y,x);
            }else if(around_mine_num != 0){
            	g.setColor(Color.red);
            	g.setFont(new Font("Arial",Font.BOLD, 20));
            	g.drawString(String.valueOf(around_mine_num), x * cell_size + (cell_size/2)/2,
            			y * cell_size + (cell_size) -cell_size/2);
            }
        }else{
        	g.setColor(Color.gray);
            g.fillRect(x * cell_size, y * cell_size, cell_size, cell_size);

            if(flack){
            	g.setColor(Color.yellow);
                g.fillRect(x * cell_size, y * cell_size, cell_size, cell_size);
            	g.setColor(Color.blue);
            	g.setFont(new Font("Arial",Font.BOLD, 20));
            	g.drawString("!!!", x * cell_size + (cell_size/2)/2,
            			y * cell_size + (cell_size) -cell_size/2);
            }
        }
        g.setColor(Color.lightGray);
    	g.drawRect(x * cell_size, y * cell_size, cell_size, cell_size);
	}

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
