package com.fr.matrax.game.entities;

import com.fr.matrax.game.event.entities.PlayerEvent;
import com.fr.matrax.mtxengine.animation.MtxAnimationPart;
import com.fr.matrax.mtxengine.animation.MtxSpriteAnimation;
import com.fr.matrax.mtxengine.engine.MtxEngine;
import com.fr.matrax.mtxengine.maths.Dimension2f;
import com.fr.matrax.mtxengine.maths.Vector2f;
import com.fr.matrax.mtxengine.objects.MtxRectangle;
import com.fr.matrax.mtxengine.objects.MtxSprite;
import com.fr.matrax.mtxengine.textures.MtxTexture;
import com.fr.matrax.mtxengine.utility.MtxColor;

public class Player extends MtxSprite
{
	
	private HealthBar healthBar;
	
	private MtxSpriteAnimation moveAnimation;
	private Vector2f spawnPoint;
	
	private int shootCooldown;
	private int health;
	private int maxHealth;
	
	private float speed;
	private float jumpSpeed;
	private float shootPerSecond;
	
	private boolean dead;
	
	private int leftKey;
	private int rightKey;
	private int jumpKey;
	private int shootKey;

	public Player(String name, Vector2f position, Dimension2f dimension, MtxTexture texture) 
	{
		super(name, position, dimension, texture);
		
		this.spawnPoint = new Vector2f(position);
		this.maxHealth = 100;
		this.health = this.maxHealth;
		this.speed = 0.2f;
		this.jumpSpeed = 10f;
		this.shootPerSecond = 5;
		this.rightKey = 0;
		this.leftKey = 0;
		this.jumpKey = 0;
		this.shootKey = 0;
		
		this.healthBar = new HealthBar(this);
		this.moveAnimation = new MtxSpriteAnimation(this, 12, 3, 2, 2, 1);
		this.moveAnimation.getAnimationPartList().add(new MtxAnimationPart(2, 0));
		this.moveAnimation.getAnimationPartList().add(new MtxAnimationPart(2, 1));
		this.moveAnimation.getAnimationPartList().add(new MtxAnimationPart(1, 0));
		this.moveAnimation.getAnimationPartList().add(new MtxAnimationPart(1, 1));
		this.moveAnimation.getAnimationPartList().add(new MtxAnimationPart(0, 0));
		this.moveAnimation.setDefaultAfterAnimation(true);
		this.getAnimationList().add(this.moveAnimation);
		
		this.setColor(MtxColor.RED);
		this.getPhysicBody().setGravity(true);
		this.getPhysicBody().setMaxVelocityY(10f);
		this.getPhysicBody().setFriction(0.90f);
		this.getPhysicBody().getCollider().setCollidable(true);
		
		this.addEvent(new PlayerEvent(this));
		this.getChildrens().add(this.healthBar);
		this.getTagList().add("Player");
	}
	
	public SlimeBall shoot(Vector2f direction)
	{
		SlimeBall slimeBall = new SlimeBall(this, new Vector2f(this.getPosition().getX(), this.getPosition().getY()), 
											new Dimension2f(0.5f, 0.5f), 
											MtxEngine.getEngine().getLevel().getTextureByName("SlimeBall"), 
											direction, 5f);
		
		if(this.isXInverted() == true)  
		{
			slimeBall.setXInverted(true);
			slimeBall.getDirection().setX(-1f);
			slimeBall.getPosition().setX(this.getPosition().getX() - 1f);
		} else {
			slimeBall.setXInverted(false);
			slimeBall.getDirection().setX(1f);
			slimeBall.getPosition().setX(this.getPosition().getX() + 1f);
		}
		
		slimeBall.setColor(this.getColor());
		
		return slimeBall;
	}
	
	public void freeze()
	{
		this.getPhysicBody().getCollider().setCollidable(false);
		this.getPhysicBody().setGravity(false);
	}
	
	public void unfreeze()
	{
		this.getPhysicBody().getCollider().setCollidable(true);
		this.getPhysicBody().setGravity(true);
	}
	
	public void die()
	{
		this.dead = true;
		this.freeze();
		this.setVisible(false);
	}
	
	public void heal()
	{
		this.health = this.maxHealth;
	}
	
	public void resurect()
	{
		this.dead = false;
		this.heal();
		this.unfreeze();
		this.setVisible(true);
	}
	
	public void spawn()
	{

		this.getPosition().setX(this.spawnPoint.getX());
		this.getPosition().setY(this.spawnPoint.getY());
	}
	
	public void setHealth(int health)
	{
		this.health = health;
	}
	
	public void setMaxHealth(int maxHealth) 
	{
		this.maxHealth = maxHealth;
	}
	
	public void setShootPerSecond(float shootPerSecond) 
	{
		this.shootPerSecond = shootPerSecond;
	}
	
	public void setJumpKey(int jumpKey) 
	{
		this.jumpKey = jumpKey;
	}
	
	public void setLeftKey(int leftKey) 
	{
		this.leftKey = leftKey;
	}
	
	public void setRightKey(int rightKey) 
	{
		this.rightKey = rightKey;
	}
	
	public void setShootKey(int shootKey) 
	{
		this.shootKey = shootKey;
	}
	
	public void setShootCooldown(int shootCooldown) 
	{
		this.shootCooldown = shootCooldown;
	}
	
	public void setJumpSpeed(float jumpSpeed) 
	{
		this.jumpSpeed = jumpSpeed;
	}
	
	public void setSpeed(float speed) 
	{
		this.speed = speed;
	}
	
	public MtxRectangle getHealthBar() 
	{
		return healthBar;
	}
	
	public Vector2f getSpawnPoint() 
	{
		return spawnPoint;
	}
	
	public boolean isDead() 
	{
		return dead;
	}
	
	public int getHealth()
	{
		return health;
	}
	
	public int getMaxHealth() 
	{
		return maxHealth;
	}
	
	public float getShootPerSecond() 
	{
		return shootPerSecond;
	}
	
	public int getJumpKey() 
	{
		return jumpKey;
	}
	
	public int getLeftKey() 
	{
		return leftKey;
	}
	
	public int getRightKey() 
	{
		return rightKey;
	}
	
	public int getShootKey() 
	{
		return shootKey;
	}
	
	public int getShootCooldown() 
	{
		return shootCooldown;
	}
	
	public float getSpeed() 
	{
		return speed;
	}
	
	public float getJumpSpeed() 
	{
		return jumpSpeed;
	}

}
