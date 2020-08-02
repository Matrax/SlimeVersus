package com.fr.matrax.game.gui;

import com.fr.matrax.mtxengine.gui.MtxSpriteComponent;
import com.fr.matrax.mtxengine.maths.Dimension2f;
import com.fr.matrax.mtxengine.maths.Vector2f;
import com.fr.matrax.mtxengine.textures.MtxTexture;

public class SlimeFont extends MtxSpriteComponent
{

	public SlimeFont(Vector2f position, Dimension2f dimension, MtxTexture texture) 
	{
		super("SlimeFont", position, dimension, texture);
		this.setLayer(9);
	}

}
