package com.fr.matrax.game;

import com.fr.matrax.game.level.MainMenuLevel;
import com.fr.matrax.game.level.LocalLevel;
import com.fr.matrax.game.level.BattleLevel;
import com.fr.matrax.mtxengine.engine.MtxEngine;

public class Game extends MtxEngine
{
	
	private static Game game;
	private static MainMenuLevel mainMenuLevel;
	private static LocalLevel localLevel;
	private static BattleLevel battleLevel;
	
	public static void main(String[] args) 
	{
		game = new Game();
		
		mainMenuLevel = new MainMenuLevel();
		battleLevel = new BattleLevel();
		localLevel = new LocalLevel();
		
		game.getRenderer().setVerticalSynchronisation(true);
		game.start(mainMenuLevel);
	}
	
	@Override
	public void OnUpdate() 
	{
		this.getWindow().setTitle("SlimeVersus FPS: " + this.getFramesPerSecond() 
								+ " VRAM: " + this.getMemoryManager().getGpuUsedMemory() 
								+ " / " + this.getMemoryManager().getGpuTotalMemory()
								+ " RAM: " + this.getMemoryManager().getUsedMemory()
								+ " / " + this.getMemoryManager().getTotalMemory());
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
	
	public static MainMenuLevel getMainMenu() 
	{
		return mainMenuLevel;
	}
	
	public static LocalLevel getSelectionLevel() 
	{
		return localLevel;
	}
	
	public static BattleLevel getBattleLevel() 
	{
		return battleLevel;
	}

}
