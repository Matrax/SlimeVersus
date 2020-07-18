package com.fr.matrax.game.entities;

import com.fr.matrax.mtxengine.animation.MtxAnimation;
import com.fr.matrax.mtxengine.animation.MtxAnimationPart;
import com.fr.matrax.mtxengine.engine.MtxEngine;
import com.fr.matrax.mtxengine.maths.Dimension2f;
import com.fr.matrax.mtxengine.maths.Vector2f;
import com.fr.matrax.mtxengine.objects.MtxSprite;
import com.fr.matrax.mtxengine.textures.MtxTexture;
import com.fr.matrax.mtxengine.utility.MtxColor;

public class Player extends MtxSprite
{
	
	private MtxAnimation moveAnimation;

	public Player(String name, Vector2f position, Dimension2f dimension, MtxTexture texture) 
	{
		super(name, position, dimension, texture);
		
		this.moveAnimation = new MtxAnimation(this, 12, 3, 3, 2, 0);
		this.moveAnimation.getAnimationPartList().add(new MtxAnimationPart(2, 1));
		this.moveAnimation.getAnimationPartList().add(new MtxAnimationPart(2, 2));
		this.moveAnimation.getAnimationPartList().add(new MtxAnimationPart(1, 0));
		this.moveAnimation.getAnimationPartList().add(new MtxAnimationPart(1, 1));
		this.moveAnimation.getAnimationPartList().add(new MtxAnimationPart(1, 2));
		this.moveAnimation.getAnimationPartList().add(new MtxAnimationPart(0, 0));
		this.moveAnimation.getAnimationPartList().add(new MtxAnimationPart(0, 1));
		this.moveAnimation.getAnimationPartList().add(new MtxAnimationPart(0, 2));
		this.moveAnimation.setDefaultAfterAnimation(false);
		this.getAnimationList().add(this.moveAnimation);
		
		this.setColor(MtxColor.RED);
		this.getPhysicBody().setGravity(true);
		this.getPhysicBody().setFriction(0.90f);
		this.getPhysicBody().getCollider().setCollidable(true);
		
	}
	
	
	public SlimeBall shoot(Vector2f direction)
	{
		SlimeBall slimeBall = new SlimeBall("SlimeBall", new Vector2f(this.getPosition().getX() + 2f, this.getPosition().getY()), new Dimension2f(1, 1), 
											MtxEngine.getEngine().getLevel().getTextureByName("SlimeBall"), 
											direction, 5f);
		slimeBall.setColor(this.getColor());
		
		if(this.isXInverted() == true)  
		{
			slimeBall.setXInverted(true);
			slimeBall.getDirection().setX(-1f);
			slimeBall.getPosition().setX(this.getPosition().getX() - 2f);
		} else {
			slimeBall.setXInverted(false);
			slimeBall.getDirection().setX(1f);
			slimeBall.getPosition().setX(this.getPosition().getX() + 2f);
		}
		return slimeBall;
	}

}
