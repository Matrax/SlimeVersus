package com.fr.matrax.game.gui;

import com.fr.matrax.mtxengine.animation.MtxAnimation;
import com.fr.matrax.mtxengine.animation.MtxAnimationPart;
import com.fr.matrax.mtxengine.maths.Dimension2f;
import com.fr.matrax.mtxengine.maths.Vector2f;
import com.fr.matrax.mtxengine.objects.MtxSprite;
import com.fr.matrax.mtxengine.textures.MtxTexture;

public class BattleButton extends MtxSprite
{
	
	private MtxAnimation hoverAnimation;

	public BattleButton(String name, Vector2f position, Dimension2f dimension, MtxTexture texture) 
	{
		super(name, position, dimension, texture);
		
		this.hoverAnimation = new MtxAnimation(this, 12, 3, 2, 2, 0);
		this.hoverAnimation.getAnimationPartList().add(new MtxAnimationPart(2, 0));
		this.hoverAnimation.getAnimationPartList().add(new MtxAnimationPart(2, 1));
		this.hoverAnimation.getAnimationPartList().add(new MtxAnimationPart(1, 0));
		this.hoverAnimation.getAnimationPartList().add(new MtxAnimationPart(1, 1));
		this.hoverAnimation.getAnimationPartList().add(new MtxAnimationPart(0, 0));
		this.getAnimationList().add(this.hoverAnimation);
		
		this.hoverAnimation.setDefaultAfterAnimation(true);
	}
	
	public MtxAnimation getHoverAnimation() {
		return hoverAnimation;
	}

}
