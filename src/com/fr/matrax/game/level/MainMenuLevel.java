package com.fr.matrax.game.level;

import com.fr.matrax.game.event.SelectionLevelEvent;
import com.fr.matrax.mtxengine.events.MtxLevelEvent;
import com.fr.matrax.mtxengine.gui.MtxSpriteComponent;
import com.fr.matrax.mtxengine.level.MtxLevel;
import com.fr.matrax.mtxengine.maths.Dimension2f;
import com.fr.matrax.mtxengine.maths.Vector2f;
import com.fr.matrax.mtxengine.objects.MtxSprite;
import com.fr.matrax.mtxengine.textures.MtxBitmap;
import com.fr.matrax.mtxengine.textures.MtxTexture;

public class MainMenuLevel extends MtxLevel implements MtxLevelEvent
{
	
	private MtxBitmap defaultBitmap;
	
	private MtxTexture panel;
	private MtxTexture fontTexture;
	private MtxTexture selectionLevelTexture;
	
	private MtxSprite selectionLevelButtonPanel;
	private MtxSprite selectionLevelButton;
	
	private MtxSpriteComponent font;

	
	public MainMenuLevel() 
	{
		//Textures
		this.defaultBitmap = new MtxBitmap("DefaultBitmap", "default_bitmap.png", 16, 16);
		this.fontTexture = new MtxTexture("GreenSlimeBackground", "GreenSlimeBackground.png");
		this.selectionLevelTexture = new MtxTexture("SelectionLevelTexture", "SelectionLevelTexture.png");
		this.panel = new MtxTexture("Panel", "Panel.png");
		
		//Panels
		this.selectionLevelButtonPanel = new MtxSprite("SelectionLevelButtonPanel", new Vector2f(0, 6), new Dimension2f(10, 4), this.panel);
		
		//Sprites
		this.selectionLevelButton = new MtxSprite("SelectionLevelButton", new Vector2f(0, 6f), new Dimension2f(8, 3), this.selectionLevelTexture);
		
		//Titles
		this.font = new MtxSpriteComponent("Font", new Vector2f(0, 0), new Dimension2f(50, 30), this.fontTexture);
		
		//Events
		this.selectionLevelButton.addEvent(new SelectionLevelEvent());
		this.addEvent(this);
	}
	
	@Override
	public void OnLoad(MtxLevel level) 
	{
		this.selectionLevelButtonPanel.setLayer(8);
		this.font.setLayer(9);
		
		this.addBitmap(this.defaultBitmap);
		this.addTexture(this.fontTexture);
		this.addTexture(this.selectionLevelTexture);
		this.addTexture(this.panel);
		
		this.addObject(this.selectionLevelButton);
		this.addObject(this.selectionLevelButtonPanel);

		this.addComponent(this.font);
	}

	@Override
	public void OnUnload(MtxLevel level) 
	{
		
	}

	@Override
	public void OnUpdate(MtxLevel level) 
	{
		
	}

}
