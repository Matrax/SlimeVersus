package com.fr.matrax.game.level;

import org.lwjgl.glfw.GLFW;

import com.fr.matrax.game.entities.Player;
import com.fr.matrax.game.entities.SlimeBall;
import com.fr.matrax.game.terrain.GrassTerrain;
import com.fr.matrax.mtxengine.engine.MtxEngine;
import com.fr.matrax.mtxengine.events.MtxLevelEvent;
import com.fr.matrax.mtxengine.gui.MtxSpriteComponent;
import com.fr.matrax.mtxengine.level.MtxLevel;
import com.fr.matrax.mtxengine.maths.Dimension2f;
import com.fr.matrax.mtxengine.maths.Vector2f;
import com.fr.matrax.mtxengine.textures.MtxTexture;

public class BattleLevel extends MtxLevel implements MtxLevelEvent
{
	
	private Player player;
	
	private GrassTerrain grassTerrain;
	
	private MtxSpriteComponent font;
	
	private MtxTexture playerTexture;
	private MtxTexture grassTexture;
	private MtxTexture fontTexture;
	private MtxTexture slimeBallTexture;
	
	private float shootTimer;
	
	public BattleLevel() 
	{
		//Textures
		this.playerTexture = new MtxTexture("PlayerTexture", "Player.png");
		this.grassTexture = new MtxTexture("GrassTexture", "GrassTerrain.png");
		this.fontTexture = new MtxTexture("GreenSlimeBackground", "GreenSlimeBackground.png");
		this.slimeBallTexture = new MtxTexture("SlimeBall", "SlimeBall.png");
		
		//Player
		this.player = new Player("Player", new Vector2f(-8, 0), new Dimension2f(2, 2), this.playerTexture);
		
		//Terrain
		this.grassTerrain = new GrassTerrain("GrassTerrain", new Vector2f(0, -5), new Dimension2f(20, 3), this.grassTexture);
		
		//Font
		this.font = new MtxSpriteComponent("Font", new Vector2f(0, 0), new Dimension2f(50, 30), this.fontTexture);
		
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
		this.addObject(this.player);
	}

	@Override
	public void OnUnload(MtxLevel level) 
	{
		
	}

	@Override
	public void OnUpdate(MtxLevel level) 
	{
		this.shootTimer += 16;
		boolean moved = false;
		float speed = 0.2f;
		float jumpSpeed = 5f;
		
		if(MtxEngine.getEngine().getKeyboard().keyPressed(GLFW.GLFW_KEY_W) && this.player.getPhysicBody().getCollider().isAbove())
		{
			this.player.getPhysicBody().addForce(0, jumpSpeed);
			this.player.getAnimationList().get(0).setRunning(false);
			moved = true;
		}
		
		if(MtxEngine.getEngine().getKeyboard().keyPressed(GLFW.GLFW_KEY_A))
		{
			this.player.setXInverted(true);
			this.player.getPhysicBody().addForce(-speed, 0);
			this.player.getAnimationList().get(0).setRunning(true);
			moved = true;
		}
		
		if(MtxEngine.getEngine().getKeyboard().keyPressed(GLFW.GLFW_KEY_D))
		{
			this.player.setXInverted(false);
			this.player.getPhysicBody().addForce(speed, 0);
			this.player.getAnimationList().get(0).setRunning(true);
			moved = true;
		}
		
		if(MtxEngine.getEngine().getKeyboard().keyPressed(GLFW.GLFW_KEY_SPACE) && this.shootTimer >= 1000)
		{
			Vector2f direction = new Vector2f(1, 0);
			SlimeBall slimeBall = this.player.shoot(direction);
			this.addObject(slimeBall);
			this.shootTimer = 0;
		}
		
		if(moved == false) this.player.getAnimationList().get(0).breakAnimation();
	}

}
