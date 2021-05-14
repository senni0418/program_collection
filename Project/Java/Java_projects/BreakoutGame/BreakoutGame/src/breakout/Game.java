package breakout;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JPanel implements KeyListener, ActionListener{
	private boolean play = false;
	private int score = 0;
	private int totalBricks = 20;
	
	private Timer timer;
	private int delay = 8;
	
	private int player = 310;
	private int ballposX = 120;
	private int ballposY = 350;
	private int balldirX = -1;
	private int balldirY = -2;
	
	private Map map;
	
	public Game() {
		map = new Map(4,5);
		
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}
	
	public void paint(Graphics graph) {
		//background
		graph.setColor(Color.white);
		graph.fillRect(1, 1, 692, 592);
		
		//bricks
		map.draw((Graphics2D)graph);
		
		//borders
		graph.setColor(Color.white);
		graph.fillRect(0, 0, 3, 592);
		graph.fillRect(0, 0, 692, 3);
		graph.fillRect(691, 0, 3, 592);
		
		//scores
		graph.setColor(Color.black);
		graph.setFont(new Font("serif", Font.BOLD, 25));
		graph.drawString("" + score, 590, 30);
		
		//paddle
		graph.setColor(Color.black);
		graph.fillRect(player, 550, 100, 8);
		
		//ball
		graph.setColor(Color.yellow);
		graph.fillOval(ballposX, ballposY, 20, 20);
		
		if (score == 100) {
			play = false;
			balldirX = 0;
			balldirY = 0;
			graph.setColor(Color.black);
			graph.setFont(new Font("serif", Font.BOLD, 30));
			graph.drawString("Congradulations!", 210, 300);
			
			graph.setFont(new Font("serif", Font.BOLD, 20));
			graph.drawString("Press Enter to Restart or Esc to exit", 230, 350);
		}
		
		if (ballposY > 570) {
			play = false;
			balldirX = 0;
			balldirY = 0;
			graph.setColor(Color.black);
			graph.setFont(new Font("serif", Font.BOLD, 30));
			graph.drawString("Game Over! Hit all bricks to win!", 140, 300);
			
			graph.setFont(new Font("serif", Font.BOLD, 20));
			graph.drawString("Press Enter to Restart or Esc to exit", 230, 350);
			
		}
		
		graph.dispose();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if (play) {
			if (new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(player, 550, 100, 8))) {
				balldirY = -balldirY;
			}
			
			label: for (int i = 0; i < map.map.length; i++) {
				for (int j = 0; j < map.map[0].length; j++) {
					if (map.map[i][j] > 0) {
						int brickX = j*map.brickW + 80;
						int brickY = i*map.brickH + 50;
						int brickW = map.brickW;
						int brickH = map.brickH;
						
						Rectangle rec = new Rectangle(brickX, brickY, brickW, brickH);
						Rectangle ballRec = new Rectangle(ballposX, ballposY, 20, 20);
						Rectangle brickRec = rec;
						
						if (ballRec.intersects(brickRec)) {
							map.setBrickValue(0, i, j);
							totalBricks--;
							score += 5;
							
							if (ballposX + 19 <= brickRec.x || ballposX + 1 >= brickRec.x + brickRec.width) {
								balldirX = -balldirX;
							} else {
								balldirY = -balldirY;
							}
							break label;
						}
					}
				}
			}
			
			ballposX += balldirX;
			ballposY += balldirY;
			if (ballposX < 0) {
				balldirX = -balldirX;
			}
			if (ballposY < 0) {
				balldirY = -balldirY;
			}
			if (ballposX > 670) {
				balldirX = -balldirX;
			}
		}
		
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (player >= 600) {
				player = 600;
			}else {
				moveRight();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (player < 10) {
				player = 10;
			}else {
				moveLeft();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (!play) {
				play = true;
				ballposX = 120;
				ballposY = 350;
				balldirX = -1;
				balldirY = -2;
				player = 310;
				score = 0;
				totalBricks = 20;
				score = 0;
				map = new Map(4, 5);
				repaint();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			Main.frame.dispose();
		}
	}
	
	public void moveRight() {
		play = true;
		player += 20;
	}
	
	public void moveLeft() {
		play = true;
		player -= 20;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
