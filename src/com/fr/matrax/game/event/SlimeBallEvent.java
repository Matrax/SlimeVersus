package com.fr.matrax.game.event;

import com.fr.matrax.game.entities.SlimeBall;
import com.fr.matrax.mtxengine.engine.MtxEngine;
import com.fr.matrax.mtxengine.events.MtxObjectEvent;
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
		this.slimeBall.getPhysicBody().addForce(this.slimeBall.getDirection().getX() * this.slimeBall.getSpeed(), 
												this.slimeBall.getDirection().getY() * this.slimeBall.getSpeed());
		
		if(this.cooldown > 2000)
		{
			MtxEngine.getEngine().getLevel().removeObject(this.slimeBall);
			this.cooldown = 0;
		}
		
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
