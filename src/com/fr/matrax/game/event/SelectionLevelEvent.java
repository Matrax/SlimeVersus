package com.fr.matrax.game.event;

import com.fr.matrax.game.Game;
import com.fr.matrax.mtxengine.engine.MtxEngine;
import com.fr.matrax.mtxengine.events.MtxObjectEvent;
import com.fr.matrax.mtxengine.objects.MtxObject;
import com.fr.matrax.mtxengine.objects.MtxSprite;
import com.fr.matrax.mtxengine.utility.MtxColor;

public class SelectionLevelEvent implements MtxObjectEvent
{
	
	public SelectionLevelEvent() 
	{
		
	}

	@Override
	public void OnClick(MtxObject object) 
	{
		MtxEngine.getEngine().loadLevel(Game.getSelectionLevel());
	}
	
	@Override
	public void OnPress(MtxObject object) 
	{

	}

	@Override
	public void OnDraw(MtxObject object) 
	{
		
	}
	
	@Override
	public void OnUpdate(MtxObject object) 
	{
		MtxSprite s = (MtxSprite) object;
		s.setColor(MtxColor.GRAY);
	}

	@Override
	public void OnHover(MtxObject object) 
	{
		MtxSprite s = (MtxSprite) object;
		s.setColor(MtxColor.WHITE);
	}

	@Override
	public void OnLoad(MtxObject object)
	{
		
	}

	@Override
	public void OnUnload(MtxObject object) 
	{
		
	}

}
