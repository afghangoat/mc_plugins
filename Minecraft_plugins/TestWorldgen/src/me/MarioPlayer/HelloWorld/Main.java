package me.MarioPlayer.HelloWorld;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.generator.ChunkGenerator;

public class Main extends JavaPlugin{
	@Override
	public void onEnable() {
		getLogger().info("Isis WorldGen enabled!");
	}

	@Override
	public void onDisable() {
		getLogger().info("Isis WorldGen disabled!");
	}
	@Override
	public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
	    return new CustomChunkGenerator();
	}
		
}
