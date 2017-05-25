package com.project.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.project.main.Game.STATE;

public class Menu extends MouseAdapter{
	
	private Game game;
	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	
	public Menu(Game game, Handler handler, HUD hud){
		this.game = game;
		this.hud = hud;
		this.handler = handler;
	}
	
	public void mousePressed(MouseEvent e){
		int mx = e.getX();
		int my = e.getY();
		
		if(game.gameState == STATE.Menu){
			//play button
			if(mouseOver(mx, my, 210, 100, 200, 64)){
				game.gameState = STATE.Game;
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
			}
			//help button
			if(mouseOver(mx, my, 210, 200, 200, 64)){
				game.gameState = STATE.Help;
			}
			//quit button
			if(mouseOver(mx, my, 210, 300, 200, 64)){
				System.exit(1);
			}
		}

		//help back button
		if(game.gameState == STATE.Help){
			if(mouseOver(mx, my, 210, 300, 200, 64)){
				game.gameState = STATE.Menu;
				return;
			}
		}
		
		//try again button
		if(game.gameState == STATE.End){
			if(mouseOver(mx, my, 210, 300, 200, 64)){
				game.gameState = STATE.Game;
				hud.setLevel(1);
				hud.setScore(0);
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
			}
		}
				
	}
	
	public void mouseReleased(MouseEvent e){
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
		if(mx > x && mx < x + width){
			if(my > y && my < y + height){
				return true;
			}else return false;
		}else return false;
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics g){
		if(game.gameState == STATE.Menu){
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			
			g.setFont(fnt);
			g.setColor(Color.RED);
			g.drawString("Dodge the Bullet", 110, 65);
			
			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawRect(210, 100, 200, 64);
			g.drawString("Play", 278, 145);
			
			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawRect(210, 200, 200, 64);
			g.drawString("Help", 278, 245);
			
			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawRect(210, 300, 200, 64);
			g.drawString("Quit", 278, 345);
			
		}else if(game.gameState == STATE.Help){
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			Font fnt3 = new Font("arial", 1, 20);
			
			g.setFont(fnt);
			g.setColor(Color.RED);
			g.drawString("Help", 245, 65);
			
			g.setFont(fnt3);
			g.setColor(Color.darkGray);
			g.drawString("Use W, A, S, D keys to move player and dodge enemies.", 50, 200);
			
			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawRect(210, 300, 200, 64);
			g.drawString("Back", 278, 345);
		}
		else if(game.gameState == STATE.End){
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			Font fnt3 = new Font("arial", 1, 20);
			
			g.setFont(fnt);
			g.setColor(Color.RED);
			g.drawString("Game Over", 180, 65);
			
			g.setFont(fnt3);
			g.setColor(Color.darkGray);
			g.drawString("You died at a score of: " + hud.getScore(), 180, 200);
			
			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawRect(210, 300, 200, 64);
			g.drawString("Try Again", 245, 345);
		}
	}

}
