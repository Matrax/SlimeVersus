package com.fr.matrax.game.level;

import com.fr.matrax.game.event.BattleButtonEvent;
import com.fr.matrax.game.gui.BattleButton;
import com.fr.matrax.mtxengine.events.MtxLevelEvent;
import com.fr.matrax.mtxengine.gui.MtxSpriteComponent;
import com.fr.matrax.mtxengine.level.MtxLevel;
import com.fr.matrax.mtxengine.maths.Dimension2f;
import com.fr.matrax.mtxengine.maths.Vector2f;
import com.fr.matrax.mtxengine.objects.MtxSprite;
import com.fr.matrax.mtxengine.objects.MtxText;
import com.fr.matrax.mtxengine.textures.MtxBitmap;
import com.fr.matrax.mtxengine.textures.MtxTexture;

public class SelectionLevel extends MtxLevel implements MtxLevelEvent 
{

	private MtxBitmap defaultBitmap;
	
	private MtxTexture battleButtonTexture;
	private MtxTexture fontTexture;
	private MtxTexture panel;
	
	private MtxSprite battleLevelButtonPanel;

	private MtxText battleTitle;
	
	private BattleButton battleButton;
	
	private MtxSpriteComponent font;
	
	public SelectionLevel() 
	{
		//Textures
		this.defaultBitmap = new MtxBitmap("DefaultBitmap", "default_bitmap.png", 16, 16);
		this.fontTexture = new MtxTexture("GreenSlimeBackground", "GreenSlimeBackground.png");
		this.battleButtonTexture = new MtxTexture("BattleButtonTexture", "BattleButton.png");
		this.panel = new MtxTexture("Panel", "Panel.png");
		
		//Panels
		this.battleLevelButtonPanel = new MtxSprite("BattleLevelButtonPanel", new Vector2f(0, 6), new Dimension2f(10, 5), this.panel);
		
		//Sprites
		this.battleButton = new BattleButton("BattleButton", new Vector2f(0, 6), new Dimension2f(6f, 4f), this.battleButtonTexture);
		
		//Titles
		this.battleTitle = new MtxText("BattleTitle", "Battle", new Vector2f(0, 4), new Dimension2f(5, 1), this.defaultBitmap);
		
		//Font
		this.font = new MtxSpriteComponent("Font", new Vector2f(0, 0), new Dimension2f(50, 30), this.fontTexture);
		
		//Events
		this.battleButton.addEvent(new BattleButtonEvent(this.battleButton));
		this.addEvent(this);
	}
	
	@Override
	public void OnLoad(MtxLevel level) 
	{
		this.font.setLayer(9);
		
		this.addBitmap(this.defaultBitmap);
		this.addTexture(this.battleButtonTexture);
		this.addTexture(this.panel);
		this.addTexture(this.fontTexture);
		
		this.addObject(this.battleButton);
		this.addObject(this.battleTitle);
		this.addObject(this.battleLevelButtonPanel);
		
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
