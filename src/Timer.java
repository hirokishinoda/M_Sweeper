import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Timer implements Common{
	private long start;
	private long time;

	/*
	 * Timerクラスのコンストラクタ
	 */
	public Timer(){
	}

	/*
	 * 時間計測の開始
	 */
	public void timerStart(){
		start = System.currentTimeMillis();
	}

	/*
	 * 時間計測開始からの経過時間を測定
	 */
	private void calcTime(){
		long end;
		end = System.currentTimeMillis();
		time = (end - start)/1000;
	}

	/*
	 * 計測開始からの時間を表示する
	 */
	public void draw(Graphics g,boolean timer_flag){
		// 時間計測
		if(timer_flag){
			calcTime();
		}
		// 時間を表示
		g.setColor(Color.red);
    	g.setFont(new Font("Arial",Font.BOLD, 20));
    	g.drawString(String.valueOf(time) + "second", 10,MY_HEIGHT - 10);
	}

	/*-------------------------------------------
	 * 各変数のgetter,setterを以下に記述する
	 -----------------------------------------*/
	public long getTime(){
		return time;
	}

	public void setTime(long time){
		this.time = time;
	}
}
