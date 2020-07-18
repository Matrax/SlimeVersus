package com.fr.matrax.game;

import com.fr.matrax.game.level.MainMenuLevel;
import com.fr.matrax.game.level.SelectionLevel;
import com.fr.matrax.game.level.BattleLevel;
import com.fr.matrax.mtxengine.engine.MtxEngine;

public class Game extends MtxEngine
{
	
	private static Game game;
	private static MainMenuLevel mainMenuLevel;
	private static SelectionLevel selectionLevel;
	private static BattleLevel battleLevel;
	
	public static void main(String[] args) 
	{
		game = new Game();
		mainMenuLevel = new MainMenuLevel();
		battleLevel = new BattleLevel();
		selectionLevel = new SelectionLevel();
		
		game.start(mainMenuLevel);
	}
	
	public static MainMenuLevel getMainMenu() 
	{
		return mainMenuLevel;
	}
	
	public static SelectionLevel getSelectionLevel() 
	{
		return selectionLevel;
	}
	
	public static BattleLevel getBattleLevel() 
	{
		return battleLevel;
	}
	
	@Override
	public void OnUpdate() 
	{
		this.getWindow().setTitle("SlimeVersus FPS: " + this.getFramesPerSecond());
	}

	@Override
	public void OnLoad() 
	{
		this.getWindow().setTitle("SlimeVersus");
	}

	@Override
	public void OnTerminate() 
	{
		
	}

}
