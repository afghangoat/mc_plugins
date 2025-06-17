package me.MarioPlayer.HelloWorld;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.generator.BlockPopulator;

public class DesertPopulator extends BlockPopulator {

    @Override
    public void populate(World world, Random random, Chunk chunk) {
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int tx = (chunk.getX() << 4) + x;
                int tz = (chunk.getZ() << 4) + z;
                int y = world.getHighestBlockYAt(tx, tz);
                
                Block block = chunk.getBlock(x, y+1, z);
                if (block.getBiome() != Biome.DESERT) continue;
                
                // Set top few layers of grass/dirt to sand
                for (int i = 0; i < 5; ++i) {
                    Block b2 = chunk.getBlock(x, y-i, z);
                    if (b2.getType() == Material.GRASS_BLOCK || b2.getType() == Material.DIRT) {
                        b2.setType(Material.SAND);
                    }
                }
                
                // Generate cactus
                if (random.nextBoolean()) {
        		    int amount = random.nextInt(5)+1;  // Amount of cactus
        		    for (int i = 1; i < amount; i++) {
        		        int X = random.nextInt(15);
        		        int Z = random.nextInt(15);
        		        int Y;
        		        for (Y = world.getMaxHeight()-1; chunk.getBlock(X, Y, Z).getType() == Material.AIR; Y--); // Find the highest block of the (X,Z) coordinate chosen.
        		        int rint = random.nextInt(3)+1;
        		        for(i=1;i >= rint; i++) {
        		        	chunk.getBlock(X, Y+1, Z).setType(Material.CACTUS);
        		        }
        		        
        		    }
        		}
            }
        }
    }

    
}
