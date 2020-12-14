package com.fr.matrax.game.level;

import org.lwjgl.glfw.GLFW;
import com.fr.matrax.game.entities.Player;
import com.fr.matrax.game.gui.SlimeFont;
import com.fr.matrax.game.terrain.SlimeTerrain;
import com.fr.matrax.mtxengine.engine.MtxEngine;
import com.fr.matrax.mtxengine.events.MtxLevelEvent;
import com.fr.matrax.mtxengine.file.MtxFileType;
import com.fr.matrax.mtxengine.file.MtxSpriteFile;
import com.fr.matrax.mtxengine.gui.MtxTextComponent;
import com.fr.matrax.mtxengine.level.MtxLevel;
import com.fr.matrax.mtxengine.maths.Dimension2f;
import com.fr.matrax.mtxengine.maths.Vector2f;
import com.fr.matrax.mtxengine.textures.MtxBitmap;
import com.fr.matrax.mtxengine.textures.MtxTexture;
import com.fr.matrax.mtxengine.utility.MtxColor;

public class BattleLevel extends MtxLevel implements MtxLevelEvent
{
	
	private Player firstPlayer;
	private Player secondPlayer;
	
	private MtxTextComponent firstPlayerScoreText;
	private MtxTextComponent secondPlayerScoreText;
	
	private SlimeTerrain slimeTerrain;
	
	private SlimeFont font;
	
	private MtxBitmap defaultBitmap;
	private MtxTexture playerTexture;
	private MtxTexture slimeTexture;
	private MtxTexture fontTexture;
	private MtxTexture slimeBallTexture;
	
	private int EndRoundTime;
	private int firstPlayerScore;
	private int secondPlayerScore;
	
	private boolean endRound;
	
	public BattleLevel() 
	{
		super(1000, 50, 50, 50, 50, 50);
		this.addEvent(this);
	}

	@Override
	public void OnLoad(MtxLevel level) 
	{
		//Textures
		this.defaultBitmap = new MtxBitmap("DefaultBitmap", "DefaultBitmap.png", 16, 16);
		this.playerTexture = new MtxTexture("PlayerTexture", "Player.png");
		this.slimeTexture = new MtxTexture("SlimeTexture", "SlimeTerrain.png");
		this.fontTexture = new MtxTexture("GreenSlimeBackground", "GreenSlimeBackground.png");
		this.slimeBallTexture = new MtxTexture("SlimeBall", "SlimeBall.png");
		this.addBitmap(this.defaultBitmap);
		this.addTexture(this.fontTexture);
		this.addTexture(this.playerTexture);
		this.addTexture(this.slimeTexture);
		this.addTexture(this.slimeBallTexture);
		
		//Text
		this.firstPlayerScoreText = new MtxTextComponent("FirstPlayerScoreText", "0", new Vector2f(-8f, 8f), new Dimension2f(3, 2), this.defaultBitmap);
		this.secondPlayerScoreText = new MtxTextComponent("SecondPlayerScoreText", "0", new Vector2f(8f, 8f), new Dimension2f(3, 2), this.defaultBitmap);
		this.firstPlayerScoreText.setVisible(false);
		this.secondPlayerScoreText.setVisible(false);
		
		//Players
		//this.firstPlayer = new Player("FirstPlayer", new Vector2f(-8, 0), new Dimension2f(1, 1), this.playerTexture);
		MtxSpriteFile file = new MtxSpriteFile("FirstPlayer");
		file.load();
		
		this.firstPlayer.setJumpKey(GLFW.GLFW_KEY_W);
		this.firstPlayer.setShootKey(GLFW.GLFW_KEY_SPACE);
		this.firstPlayer.setLeftKey(GLFW.GLFW_KEY_A);
		this.firstPlayer.setRightKey(GLFW.GLFW_KEY_D);
		this.firstPlayer.setColor(MtxColor.ORANGE);
		this.firstPlayer.setXInverted(false);
		
		this.secondPlayer = new Player("SecondPlayer", new Vector2f(8, 0), new Dimension2f(1, 1), this.playerTexture);
		this.secondPlayer.setJumpKey(GLFW.GLFW_KEY_UP);
		this.secondPlayer.setShootKey(GLFW.GLFW_KEY_KP_0);
		this.secondPlayer.setLeftKey(GLFW.GLFW_KEY_LEFT);
		this.secondPlayer.setRightKey(GLFW.GLFW_KEY_RIGHT);
		this.secondPlayer.setColor(MtxColor.LIGHT_BLUE);
		this.secondPlayer.setXInverted(true);
		
		//Terrain
		this.slimeTerrain = new SlimeTerrain("SlimeTerrain", new Vector2f(0, -5), new Dimension2f(23, 3), this.slimeTexture);
		this.slimeTerrain.setColor(MtxColor.GREEN);
		
		//Font
		this.font = new SlimeFont(new Vector2f(0, 0), new Dimension2f(50, 30), this.fontTexture);
		
		this.addComponent(this.firstPlayerScoreText);
		this.addComponent(this.secondPlayerScoreText);
		this.addComponent(this.font);
		this.addObject(this.slimeTerrain);
		this.addObject(this.firstPlayer);
		this.addObject(this.secondPlayer);
	}

	@Override
	public void OnUpdate(MtxLevel level) 
	{
		if(this.endRound == true) this.EndRoundTime += MtxEngine.getEngine().getTimePassed();
		if(this.firstPlayer.getPosition().getY() < -15) this.firstPlayer.die();
		if(this.secondPlayer.getPosition().getY() < -15) this.secondPlayer.die();
		
		if( (this.firstPlayer.isDead() || this.secondPlayer.isDead()) && this.endRound == false) 
		{
			if(this.firstPlayer.isDead())
			{
				this.secondPlayerScore++;
				this.secondPlayerScoreText.setText(String.valueOf(this.secondPlayerScore));
			} else if(this.secondPlayer.isDead()) {
				this.firstPlayerScore++;
				this.firstPlayerScoreText.setText(String.valueOf(this.firstPlayerScore));
			}
			
			this.firstPlayerScoreText.setVisible(true);
			this.secondPlayerScoreText.setVisible(true);
			this.endRound = true;
		}
		
		if(this.EndRoundTime >= 2000) 
		{
			this.firstPlayerScoreText.setVisible(false);
			this.secondPlayerScoreText.setVisible(false);
			this.newRound();
		}
	}
	
	@Override
	public void OnUnload(MtxLevel level) {}
	
	public void newRound()
	{
		if(this.firstPlayer.isDead())
		{
			this.secondPlayer.heal();
			this.firstPlayer.resurect();
		} else if(this.secondPlayer.isDead()) {
			this.firstPlayer.heal();
			this.secondPlayer.resurect();
		}
		
		this.EndRoundTime = 0;
		this.endRound = false;
		this.firstPlayer.spawn();
		this.secondPlayer.spawn();
	}
	
	public MtxTextComponent getFirstPlayerScoreText() 
	{
		return firstPlayerScoreText;
	}
	
	public MtxTextComponent getSecondPlayerScoreText() 
	{
		return secondPlayerScoreText;
	}

}
