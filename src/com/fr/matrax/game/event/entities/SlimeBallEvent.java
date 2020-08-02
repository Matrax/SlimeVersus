package com.fr.matrax.game.event.entities;

import com.fr.matrax.game.entities.Player;
import com.fr.matrax.game.entities.SlimeBall;
import com.fr.matrax.mtxengine.array.MtxObjectList;
import com.fr.matrax.mtxengine.engine.MtxEngine;
import com.fr.matrax.mtxengine.events.MtxObjectEvent;
import com.fr.matrax.mtxengine.level.MtxLevel;
import com.fr.matrax.mtxengine.objects.MtxObject;

public class SlimeBallEvent implements MtxObjectEvent
{
	
	private SlimeBall slimeBall;
	private long cooldown;
	
	public SlimeBallEvent(SlimeBall slimeBall) 
	{
		this.slimeBall = slimeBall;
	}
	
	@Override
	public void OnLoad(MtxObject object) 
	{
		
	}
	
	@Override
	public void OnUpdate(MtxObject object) 
	{
		MtxLevel level = MtxEngine.getEngine().getLevel();
		MtxObjectList list = this.slimeBall.getPhysicBody().getCollider().getTouchedObjects();
		
		if(list.getCount() > 0) 
		{
			Player target = (Player) list.getByTag("Player");
			
			if(target != null)
			{
				target.setHealth(target.getHealth() - 10);
			}
			
			this.slimeBall.explode();
		}
		
		if(this.cooldown > 2000)
		{
			level.removeObject(this.slimeBall);
			this.cooldown = 0;
		}
		
		this.slimeBall.getPhysicBody().addForce(this.slimeBall.getDirection().getX() * this.slimeBall.getSpeed(), 
												this.slimeBall.getDirection().getY() * this.slimeBall.getSpeed());
		this.slimeBall.setRotation(this.slimeBall.getRotation() + this.slimeBall.getSpeed());
		this.cooldown += 16;
	}

	@Override
	public void OnClick(MtxObject object) 
	{

	}

	@Override
	public void OnDraw(MtxObject object) 
	{
		
	}

	@Override
	public void OnHover(MtxObject level) 
	{
		
	}

	@Override
	public void OnPress(MtxObject level) 
	{
		
	}

	@Override
	public void OnUnload(MtxObject level) 
	{
		
	}

}
