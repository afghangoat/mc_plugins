package me.MarioPlayer.HelloWorld;


import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

public class DecoPopulator extends BlockPopulator{
	@Override
	public void populate(World world, Random random, Chunk chunk) {
		if (random.nextBoolean()) {
		    int amount = random.nextInt(6)+8;  // Amount of trees
		    for (int i = 1; i < amount; i++) {
		        int X = random.nextInt(15);
		        int Z = random.nextInt(15);
		        int Y;
		        for (Y = world.getMaxHeight()-1; chunk.getBlock(X, Y, Z).getType() == Material.AIR; Y--); // Find the highest block of the (X,Z) coordinate chosen.
		        int rint = random.nextInt(6);
		        if(rint == 0 || rint == 1) {
		        	chunk.getBlock(X, Y, Z).setType(Material.COARSE_DIRT);
		        }else if(rint == 2 || rint == 3) {
		        	chunk.getBlock(X, Y, Z).setType(Material.GRAVEL);
		        }else if(rint == 4 || rint == 5) {
		        	chunk.getBlock(X, Y, Z).setType(Material.DIORITE);
		        }
		        
		    }
		}
	}
}
