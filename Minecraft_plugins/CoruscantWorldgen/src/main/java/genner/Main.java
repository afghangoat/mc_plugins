package genner;

import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
	@Override
	public void onEnable() {
		getLogger().info("Enabled!");
	}

	@Override
	public void onDisable() {
		getLogger().info("Disabled!");
	}
	@Override
	public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
	    return new CustomChunkGenerator();
	}
}