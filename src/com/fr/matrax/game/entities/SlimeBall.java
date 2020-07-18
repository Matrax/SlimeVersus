package com.fr.matrax.game.entities;

import com.fr.matrax.game.event.SlimeBallEvent;
import com.fr.matrax.mtxengine.animation.MtxAnimation;
import com.fr.matrax.mtxengine.animation.MtxAnimationPart;
import com.fr.matrax.mtxengine.maths.Dimension2f;
import com.fr.matrax.mtxengine.maths.Vector2f;
import com.fr.matrax.mtxengine.objects.MtxSprite;
import com.fr.matrax.mtxengine.physics.MtxForceType;
import com.fr.matrax.mtxengine.textures.MtxTexture;

public class SlimeBall extends MtxSprite
{
	
	private MtxAnimation moveAnimation;
	private Vector2f direction;
	private float speed;
	
	public SlimeBall(String name, Vector2f position, Dimension2f dimension, MtxTexture texture, Vector2f direction, float speed) 
	{
		super(name, position, dimension, texture);
		this.direction = direction;
		this.speed = speed;
		
		this.moveAnimation = new MtxAnimation(this, 6, 2, 2, 0, 0);
		this.moveAnimation.getAnimationPartList().add(new MtxAnimationPart(1, 1));
		this.moveAnimation.getAnimationPartList().add(new MtxAnimationPart(1, 0));
		this.moveAnimation.getAnimationPartList().add(new MtxAnimationPart(0, 1));
		this.moveAnimation.getAnimationPartList().add(new MtxAnimationPart(0, 0));
		this.moveAnimation.setRunning(true);
		this.moveAnimation.setLoop(true);
		this.getAnimationList().add(this.moveAnimation);
		
		this.getPhysicBody().setGravity(false);
		this.getPhysicBody().getCollider().setCollidable(true);
		this.getPhysicBody().setForceType(MtxForceType.UNIFORM);
		
		this.addEvent(new SlimeBallEvent(this));
	}
	
	public Vector2f getDirection() 
	{
		return direction;
	}
	
	public float getSpeed()
	{
		return speed;
	}

}
