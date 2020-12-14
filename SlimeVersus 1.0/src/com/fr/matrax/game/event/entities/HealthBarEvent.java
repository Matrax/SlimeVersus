package com.fr.matrax.game.event.entities;

import com.fr.matrax.game.entities.HealthBar;
import com.fr.matrax.mtxengine.events.MtxObjectEvent;
import com.fr.matrax.mtxengine.objects.MtxObject;

public class HealthBarEvent implements MtxObjectEvent
{
	
	private HealthBar healthBar;
	
	public HealthBarEvent(HealthBar healthBar) 
	{
		this.healthBar = healthBar;
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

	@Override
	public void OnUpdate(MtxObject object) 
	{
		float healthPourcent = this.healthBar.getPlayer().getHealth() * 100f / this.healthBar.getPlayer().getMaxHealth();
		this.healthBar.getPosition().setX(this.healthBar.getPlayer().getPosition().getX());
		this.healthBar.getPosition().setY(this.healthBar.getPlayer().getPosition().getY() + 1f);
		this.healthBar.getDimension().setWidth(2f * healthPourcent / 100f);
	}

}
