import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Timer implements Common{
	long start;
	long end;

	public Timer(){
	}

	public void timerStart(){
		start = System.currentTimeMillis();
	}

	public void timerStop(){
		end = System.currentTimeMillis();
	}

	public void draw(Graphics g){
		timerStop();

		g.setColor(Color.red);
    	g.setFont(new Font("Arial",Font.BOLD, 20));
    	g.drawString(String.valueOf((end - start)/1000) + "second", 10,MY_HEIGHT - 10);
	}
}
