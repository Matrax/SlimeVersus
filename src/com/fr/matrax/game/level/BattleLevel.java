package com.fr.matrax.game.level;

import org.lwjgl.glfw.GLFW;

import com.fr.matrax.game.entities.Player;
import com.fr.matrax.game.gui.SlimeFont;
import com.fr.matrax.game.terrain.GrassTerrain;
import com.fr.matrax.mtxengine.events.MtxLevelEvent;
import com.fr.matrax.mtxengine.level.MtxLevel;
import com.fr.matrax.mtxengine.maths.Dimension2f;
import com.fr.matrax.mtxengine.maths.Vector2f;
import com.fr.matrax.mtxengine.objects.MtxText;
import com.fr.matrax.mtxengine.textures.MtxBitmap;
import com.fr.matrax.mtxengine.textures.MtxTexture;

public class BattleLevel extends MtxLevel implements MtxLevelEvent
{
	
	private Player firstPlayer;
	private Player secondPlayer;
	
	private MtxText firstPlayerScoreText;
	private MtxText secondPlayerScoreText;
	
	private GrassTerrain grassTerrain;
	
	private SlimeFont font;
	
	private MtxBitmap defaultBitmap;
	private MtxTexture playerTexture;
	private MtxTexture grassTexture;
	private MtxTexture fontTexture;
	private MtxTexture slimeBallTexture;
	
	private int firstPlayerScore;
	private int secondPlayerScore;
	
	public BattleLevel() 
	{
		super(1000, 50, 50, 50, 50, 50);
		
		//Textures
		this.defaultBitmap = new MtxBitmap("DefaultBitmap", "DefaultBitmap.png", 16, 16);
		this.playerTexture = new MtxTexture("PlayerTexture", "Player.png");
		this.grassTexture = new MtxTexture("GrassTexture", "GrassTerrain.png");
		this.fontTexture = new MtxTexture("GreenSlimeBackground", "GreenSlimeBackground.png");
		this.slimeBallTexture = new MtxTexture("SlimeBall", "SlimeBall.png");
		
		//Text
		this.firstPlayerScoreText = new MtxText("FirstPlayerScoreText", "0", new Vector2f(-5f, 0), new Dimension2f(2, 1), this.defaultBitmap);
		this.secondPlayerScoreText = new MtxText("SecondPlayerScoreText", "0", new Vector2f(5f, 0), new Dimension2f(2, 1), this.defaultBitmap);
		
		//Player
		this.firstPlayer = new Player("FirstPlayer", new Vector2f(-8, 0), new Dimension2f(1, 1), this.playerTexture);
		this.firstPlayer.setJumpKey(GLFW.GLFW_KEY_W);
		this.firstPlayer.setShootKey(GLFW.GLFW_KEY_SPACE);
		this.firstPlayer.setLeftKey(GLFW.GLFW_KEY_A);
		this.firstPlayer.setRightKey(GLFW.GLFW_KEY_D);
		
		this.secondPlayer = new Player("SecondPlayer", new Vector2f(8, 0), new Dimension2f(1, 1), this.playerTexture);
		this.secondPlayer.setJumpKey(GLFW.GLFW_KEY_UP);
		this.secondPlayer.setShootKey(GLFW.GLFW_KEY_KP_0);
		this.secondPlayer.setLeftKey(GLFW.GLFW_KEY_LEFT);
		this.secondPlayer.setRightKey(GLFW.GLFW_KEY_RIGHT);
		this.secondPlayer.setXInverted(true);
		
		//Terrain
		this.grassTerrain = new GrassTerrain("GrassTerrain", new Vector2f(0, -5), new Dimension2f(23, 3), this.grassTexture);
		
		//Font
		this.font = new SlimeFont(new Vector2f(0, 0), new Dimension2f(50, 30), this.fontTexture);
		
		//Event
		this.addEvent(this);
	}

	@Override
	public void OnLoad(MtxLevel level) 
	{
		this.font.setLayer(9);
		
		this.addTexture(this.fontTexture);
		this.addTexture(this.playerTexture);
		this.addTexture(this.grassTexture);
		this.addTexture(this.slimeBallTexture);
		
		this.addComponent(this.font);
		this.addObject(this.grassTerrain);
		this.addObject(this.firstPlayer);
		this.addObject(this.secondPlayer);
	}

	@Override
	public void OnUpdate(MtxLevel level) 
	{
		if(this.firstPlayer.getPosition().getY() < -15) this.firstPlayer.die();
		if(this.secondPlayer.getPosition().getY() < -15) this.secondPlayer.die();
		
		if(this.firstPlayer.isDead())
		{
			this.secondPlayerScore++;
			this.secondPlayerScoreText.setText(this.secondPlayerScore + "");
			this.secondPlayer.heal();
			this.firstPlayer.resurect();
			this.newBattle();
		} else if(this.secondPlayer.isDead()) {
			this.firstPlayerScore++;
			this.firstPlayerScoreText.setText(this.firstPlayerScore + "");
			this.firstPlayer.heal();
			this.secondPlayer.resurect();
			this.newBattle();
		}
	}
	
	@Override
	public void OnUnload(MtxLevel level) {}
	
	public void newBattle()
	{
		this.firstPlayer.spawn();
		this.secondPlayer.spawn();
	}
	
	public MtxText getFirstPlayerScoreText() 
	{
		return firstPlayerScoreText;
	}
	
	public MtxText getSecondPlayerScoreText() 
	{
		return secondPlayerScoreText;
	}

}
