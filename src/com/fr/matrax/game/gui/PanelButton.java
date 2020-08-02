package com.fr.matrax.game.gui;

import com.fr.matrax.mtxengine.animation.MtxAnimationPart;
import com.fr.matrax.mtxengine.animation.MtxSpriteComponentAnimation;
import com.fr.matrax.mtxengine.gui.MtxSpriteComponent;
import com.fr.matrax.mtxengine.maths.Dimension2f;
import com.fr.matrax.mtxengine.maths.Vector2f;
import com.fr.matrax.mtxengine.textures.MtxTexture;
import com.fr.matrax.mtxengine.utility.MtxColor;

public class PanelButton extends MtxSpriteComponent
{
	
	private MtxSpriteComponentAnimation panelAnimation;

	public PanelButton(String name, Vector2f position, Dimension2f dimension, MtxTexture texture) 
	{
		super(name, position, dimension, texture);
		
		this.panelAnimation = new MtxSpriteComponentAnimation(this, 8, 4, 1, 0, 0);
		this.panelAnimation.getAnimationPartList().add(new MtxAnimationPart(1, 0));
		this.panelAnimation.getAnimationPartList().add(new MtxAnimationPart(2, 0));
		this.panelAnimation.getAnimationPartList().add(new MtxAnimationPart(3, 0));
		this.panelAnimation.setRunning(true);
		this.panelAnimation.setLoop(true);
		this.panelAnimation.setDefaultAfterAnimation(true);
		this.getAnimationList().add(this.panelAnimation);
		
		this.setColor(MtxColor.RED);
	}

}
