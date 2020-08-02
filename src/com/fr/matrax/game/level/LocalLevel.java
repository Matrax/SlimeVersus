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
	private MtxTexture battleButtonTexture;
	private MtxTexture fontTexture;
	private MtxTexture panel;
	
	private MtxTextComponent battleTitle;
	private PanelButton battleLevelButton;
	
	private SlimeFont font;
	
	public LocalLevel() 
	{
		//Textures
		this.defaultBitmap = new MtxBitmap("DefaultBitmap", "DefaultBitmap.png", 16, 16);
		this.fontTexture = new MtxTexture("GreenSlimeBackground", "GreenSlimeBackground.png");
		this.battleButtonTexture = new MtxTexture("BattleButtonTexture", "BattleButton.png");
		this.panel = new MtxTexture("Panel", "Panel.png");
		
		//Panels
		this.battleLevelButton = new PanelButton("", new Vector2f(0, 6), new Dimension2f(10, 4), this.panel);
		
		//Titles
		this.battleTitle = new MtxTextComponent("BattleTitle", "Battle", new Vector2f(0.5f, 6), new Dimension2f(5, 1), this.defaultBitmap);
		
		//Font
		this.font = new SlimeFont(new Vector2f(0, 0), new Dimension2f(50, 30), this.fontTexture);
		
		//Events
		this.battleLevelButton.addEvent(new BattleButtonEvent());
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
		
		this.addComponent(this.battleLevelButton);
		this.addComponent(this.battleTitle);
		
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
