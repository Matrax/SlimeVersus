package com.fr.matrax.game.event.entities;

import com.fr.matrax.game.entities.Player;
import com.fr.matrax.game.entities.SlimeBall;
import com.fr.matrax.mtxengine.engine.MtxEngine;
import com.fr.matrax.mtxengine.events.MtxObjectEvent;
import com.fr.matrax.mtxengine.level.MtxLevel;
import com.fr.matrax.mtxengine.maths.Vector2f;
import com.fr.matrax.mtxengine.objects.MtxObject;

public class PlayerEvent implements MtxObjectEvent
{
	
	private Player player;
	
	public PlayerEvent(Player player) 
	{
		this.player = player;
	}
	
	@Override
	public void OnUpdate(MtxObject object)
	{
		boolean moved = false;
		MtxLevel level = MtxEngine.getEngine().getLevel();
		
		if(this.player.getHealth() <= 0) this.player.die();
		
		if(MtxEngine.getEngine().getKeyboard().keyPressed(this.player.getJumpKey()) 
		&& this.player.getPhysicBody().getCollider().isAbove())
		{
			this.player.getPhysicBody().addForce(0, this.player.getJumpSpeed());
			this.player.getAnimationList().get(0).setRunning(false);
			moved = true;
		}
		
		if(MtxEngine.getEngine().getKeyboard().keyPressed(this.player.getLeftKey()))
		{
			this.player.setXInverted(true);
			this.player.getPhysicBody().addForce(-this.player.getSpeed(), 0);
			this.player.getAnimationList().get(0).setRunning(true);
			moved = true;
		}
		
		if(MtxEngine.getEngine().getKeyboard().keyPressed(this.player.getRightKey()))
		{
			this.player.setXInverted(false);
			this.player.getPhysicBody().addForce(this.player.getSpeed(), 0);
			this.player.getAnimationList().get(0).setRunning(true);
			moved = true;
		}
		
		if(MtxEngine.getEngine().getKeyboard().keyPressed(this.player.getShootKey()) 
		&& this.player.getShootCooldown() >= 1000 / this.player.getShootPerSecond()
		&& this.player.isDead() == false)
		{
			Vector2f direction = new Vector2f(1, 0);
			SlimeBall projectile = this.player.shoot(direction);
			this.player.setShootCooldown(0);
			level.addObject(projectile);
		}
		
		if(moved == false) this.player.getAnimationList().get(0).breakAnimation();
		this.player.setShootCooldown((int) (this.player.getShootCooldown() + MtxEngine.getEngine().getTimePassed()));
	}

	@Override
	public void OnClick(MtxObject object) {}

	@Override
	public void OnDraw(MtxObject object) {}

	@Override
	public void OnHover(MtxObject object) {}

	@Override
	public void OnLoad(MtxObject object) {}

	@Override
	public void OnPress(MtxObject object) {}

	@Override
	public void OnUnload(MtxObject object) {}

}
