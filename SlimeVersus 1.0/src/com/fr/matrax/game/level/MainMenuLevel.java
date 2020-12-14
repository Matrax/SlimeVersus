package com.fr.matrax.game.level;

import com.fr.matrax.game.event.gui.LocalButtonEvent;
import com.fr.matrax.game.gui.PanelButton;
import com.fr.matrax.game.gui.SlimeFont;
import com.fr.matrax.mtxengine.events.MtxLevelEvent;
import com.fr.matrax.mtxengine.gui.MtxTextComponent;
import com.fr.matrax.mtxengine.level.MtxLevel;
import com.fr.matrax.mtxengine.maths.Dimension2f;
import com.fr.matrax.mtxengine.maths.Vector2f;
import com.fr.matrax.mtxengine.textures.MtxBitmap;
import com.fr.matrax.mtxengine.textures.MtxTexture;

public class MainMenuLevel extends MtxLevel implements MtxLevelEvent
{
	
	private MtxBitmap defaultBitmap;
	private MtxTexture panel;
	private MtxTexture fontTexture;
	
	private PanelButton LocalLevelButton;
	private MtxTextComponent localLevelText;
	
	private SlimeFont font;
	
	public MainMenuLevel() 
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
		
		//Buttons
		this.LocalLevelButton = new PanelButton("LocalLevelButton" , new Vector2f(0, 6), new Dimension2f(10, 4), this.panel);
		
		//Titles
		this.localLevelText = new MtxTextComponent("LocalLevelText", "Local", new Vector2f(0, 6), new Dimension2f(3, 1), this.defaultBitmap);
		
		//Font
		this.font = new SlimeFont(new Vector2f(0, 0), new Dimension2f(50, 30), this.fontTexture);
		
		//Events
		this.LocalLevelButton.addEvent(new LocalButtonEvent());
		
		this.addBitmap(this.defaultBitmap);
		this.addTexture(this.fontTexture);
		this.addTexture(this.panel);
		
		this.addComponent(this.localLevelText);
		this.addComponent(this.LocalLevelButton);
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
