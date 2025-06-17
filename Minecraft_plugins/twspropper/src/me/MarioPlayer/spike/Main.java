package me.MarioPlayer.spike;

import org.bukkit.plugin.java.JavaPlugin;


import net.minecraft.server.v1_16_R3.WorldServer;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.generator.ChunkGenerator;

public class Main extends JavaPlugin{
	@Override
	public void onEnable() {
		if(getServer().getWorld("world") == null)
		{
		}
		getLogger().info("TWSpropper enabled!");
	}

	@Override
	public void onDisable() {
		getLogger().info("TWSpropper disabled!");
	}
//	public List<BlockPopulator> getDefaultPopulators(World world)
//	{
//		ArrayList<BlockPopulator> pop =  new ArrayList<BlockPopulator>();
//		pop.add(new GrassPopulator());
//		return pop;
//	}
//	@EventHandler
//	public void onWorldInit(WorldInitEvent e)
//	{
//	    e.getWorld().getPopulators().add(new GrassPopulator());
//	}
	@Override
	public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
	    World w = Bukkit.getWorld(worldName);
	    if (w == null) {
	        Util.log(Level.SEVERE, "World is null.");
	        return null;
	    }
	    CraftWorld cw = (CraftWorld) w;
	    WorldServer ws = cw.getHandle();
	    if (ws == null) {
	        Util.log(Level.SEVERE, "WorldServer is null.");
	        return null;
	    }
	    return new DeathGamesGenerator(ws, ws.getSeed());
	}
		
}
