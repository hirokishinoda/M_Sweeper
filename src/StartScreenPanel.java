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

public class StartScreenPanel extends JPanel implements MouseListener,Common{
	MainFrame mf;
	SelectButton masu9_btn = new SelectButton("9×9マス,Mine 10",200,50);
	SelectButton masu16_btn = new SelectButton("16×16マス,Mine 40",300,50);
	SelectButton masu32_btn = new SelectButton("32×32マス,Mine 120",400,50);

	public StartScreenPanel(MainFrame mf){
		this.mf = mf;

		setLayout(null);
		setBackground(Color.cyan);

        addActionToButton();

		setPreferredSize(new Dimension(MY_WIDTH, MY_HEIGHT));
	}

	public void paintComponent(Graphics g){
		 super.paintComponent(g);

		 g.setColor(Color.black);
		 g.setFont(new Font("Arial",Font.BOLD, 50));
		 g.drawString("MineSweeper!!!", 75, 100);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	private void addActionToButton(){
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

        add(masu9_btn);
        add(masu16_btn);
        add(masu32_btn);
	}

}

class SelectButton extends JButton implements Common{

	public SelectButton(String name,int y,int x){
		setText(name);
		setFocusPainted(false);
		setBounds(x, y, 200, 40);
		setPreferredSize(new Dimension(300,50));
	}

}
