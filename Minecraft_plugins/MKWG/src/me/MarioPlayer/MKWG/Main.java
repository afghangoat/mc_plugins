package me.MarioPlayer.MKWG;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.generator.ChunkGenerator;

public class Main extends JavaPlugin{
	public class BasicChunkGenerator extends ChunkGenerator {

	}
	@Override
	public void onEnable() {
		getLogger().info("MeinKraft WorldGen enabled!");
	}

	@Override
	public void onDisable() {
		getLogger().info("MeinKraft WorldGen disabled!");
	}
	@Override
	public ChunkGenerator getDefaultWorldGenerator(String worldName, String GenId) {
		return new BasicChunkGenerator();
	}
		
}
