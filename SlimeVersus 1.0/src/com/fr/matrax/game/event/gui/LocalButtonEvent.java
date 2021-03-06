package com.fr.matrax.game.event.gui;

import com.fr.matrax.game.Game;
import com.fr.matrax.mtxengine.engine.MtxEngine;
import com.fr.matrax.mtxengine.events.MtxComponentEvent;
import com.fr.matrax.mtxengine.gui.MtxComponent;
import com.fr.matrax.mtxengine.gui.MtxSpriteComponent;
import com.fr.matrax.mtxengine.utility.MtxColor;

public class LocalButtonEvent implements MtxComponentEvent
{
	
	private MtxColor defaultColor;
	private MtxColor hoverColor;
	
	@Override
	public void OnLoad(MtxComponent object)
	{
		this.defaultColor = MtxColor.WHITE;
		this.hoverColor = MtxColor.ORANGE;
	}

	@Override
	public void OnUnload(MtxComponent object) 
	{
		
	}

	@Override
	public void OnClick(MtxComponent object) 
	{
		MtxEngine.getEngine().loadLevel(Game.getSelectionLevel());
	}
	
	@Override
	public void OnPress(MtxComponent object) 
	{

	}

	@Override
	public void OnDraw(MtxComponent object) 
	{
		
	}
	
	@Override
	public void OnUpdate(MtxComponent object) 
	{
		MtxSpriteComponent s = (MtxSpriteComponent) object;
		s.setColor(this.defaultColor);
	}

	@Override
	public void OnHover(MtxComponent object) 
	{
		MtxSpriteComponent s = (MtxSpriteComponent) object;
		s.setColor(this.hoverColor);
	}

}
