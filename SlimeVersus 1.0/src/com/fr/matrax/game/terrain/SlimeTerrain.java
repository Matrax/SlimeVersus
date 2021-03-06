package com.fr.matrax.game.terrain;

import com.fr.matrax.mtxengine.maths.Dimension2f;
import com.fr.matrax.mtxengine.maths.Vector2f;
import com.fr.matrax.mtxengine.objects.MtxSprite;
import com.fr.matrax.mtxengine.textures.MtxTexture;

public class SlimeTerrain extends MtxSprite 
{

	public SlimeTerrain(String name, Vector2f position, Dimension2f dimension, MtxTexture texture) 
	{
		super(name, position, dimension, texture);
		this.getPhysicBody().getCollider().setCollidable(true);
		this.getPhysicBody().getCollider().setStaticBody(true);
	}

}
