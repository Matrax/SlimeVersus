package com.fr.matrax.game.level;

import com.fr.matrax.game.event.gui.BattleButtonEvent;
import com.fr.matrax.game.gui.PanelButton;
import com.fr.matrax.game.gui.SlimeFont;
import com.fr.matrax.mtxengine.events.MtxLevelEvent;
import com.fr.matrax.mtxengine.gui.MtxTextComponent;
import com.fr.matrax.mtxengine.level.MtxLevel;
import com.fr.matrax.mtxengine.maths.Dimension2f;
import com.fr.matrax.mtxengine.maths.Vector2f;
import com.fr.matrax.mtxengine.textures.MtxBitmap;
import com.fr.matrax.mtxengine.textures.MtxTexture;

public class LocalLevel extends MtxLevel implements MtxLevelEvent 
{

	private MtxBitmap defaultBitmap;
	private MtxTexture fontTexture;
	private MtxTexture panel;
	
	private MtxTextComponent battleLevelText;
	private PanelButton battleLevelButton;
	
	private SlimeFont font;
	
	public LocalLevel() 
	{
		this.addEvent(this);
	}
	
	@Override
	public void OnLoad(MtxLevel level) 
	{
		//Textures
		this.defaultBitmap = new MtxBitmap("DefaultBitmap", "DefaultBitmap.png", 16, 16);
		this.fontTexture = new MtxTexture("GreenSlimeBackground", "GreenSlimeBackground.png");
		this.panel = new MtxTexture("Panel", "Panel.png");
		
		//Panels
		this.battleLevelButton = new PanelButton("BattleLevelButton", new Vector2f(0, 6), new Dimension2f(10, 4), this.panel);
		
		//Text
		this.battleLevelText = new MtxTextComponent("BattleLevelText", "Battle 1v1", new Vector2f(0f, 6), new Dimension2f(3, 1), this.defaultBitmap);
		
		//Font
		this.font = new SlimeFont(new Vector2f(0, 0), new Dimension2f(50, 30), this.fontTexture);
		
		//Events
		this.battleLevelButton.addEvent(new BattleButtonEvent());
		
		this.addBitmap(this.defaultBitmap);
		
		this.addTexture(this.fontTexture);
		this.addTexture(this.panel);
		
		this.addComponent(this.battleLevelText);
		this.addComponent(this.battleLevelButton);
		this.addComponent(this.font);
	}

	@Override
	public void OnUnload(MtxLevel level) {}

	@Override
	public void OnUpdate(MtxLevel level) {}

}
