package com.fr.matrax.game.entities;

import com.fr.matrax.game.event.entities.HealthBarEvent;
import com.fr.matrax.mtxengine.maths.Dimension2f;
import com.fr.matrax.mtxengine.maths.Vector2f;
import com.fr.matrax.mtxengine.objects.MtxRectangle;
import com.fr.matrax.mtxengine.utility.MtxColor;

public class HealthBar extends MtxRectangle
{
	
	private Player player;

	public HealthBar(Player player) 
	{
		super(player.getName() + ":HealthBar",
				new Vector2f(player.getPosition().getX(), player.getPosition().getY() + 1f),
				new Dimension2f(2f, 0.2f));
		
		this.player = player;
		this.setColor(MtxColor.GREEN);
		this.getPhysicBody().setGravity(false);
		this.getPhysicBody().getCollider().setCollidable(false);
		
		this.addEvent(new HealthBarEvent(this));
	}
	
	public Player getPlayer()
	{
		return this.player;
	}

}
