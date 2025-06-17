package me.MarioPlayer.HelloWorld;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.generator.BlockPopulator;

public class TreePopulator extends BlockPopulator{
	@Override
	public void populate(World world, Random random, Chunk chunk) {
		if (random.nextBoolean()) {
		    int amount = random.nextInt(4)+1;  // Amount of trees
		    for (int i = 1; i < amount; i++) {
		        int X = random.nextInt(15);
		        int Z = random.nextInt(15);
		        int Y;
		        for (Y = world.getMaxHeight()-1; chunk.getBlock(X, Y, Z).getType() == Material.AIR; Y--); // Find the highest block of the (X,Z) coordinate chosen.
		        Block bi = chunk.getBlock(X, Y, Z);
		        int rint = random.nextInt(3);
		        if((bi.getBiome() == Biome.JUNGLE) || (bi.getBiome() == Biome.JUNGLE_EDGE) || (bi.getBiome() == Biome.JUNGLE_HILLS) || (bi.getBiome() == Biome.MODIFIED_JUNGLE) || (bi.getBiome() == Biome.MODIFIED_JUNGLE_EDGE)) {
		        	if (rint == 0) {
		        		world.generateTree(chunk.getBlock(X, Y, Z).getLocation(), TreeType.JUNGLE);
		        	}else if(rint == 1) {
		        		world.generateTree(chunk.getBlock(X, Y, Z).getLocation(), TreeType.JUNGLE_BUSH);
		        	}else if(rint == 2) {
		        		world.generateTree(chunk.getBlock(X, Y, Z).getLocation(), TreeType.SMALL_JUNGLE);
		        	}
		        }else if((bi.getBiome() == Biome.FOREST) || (bi.getBiome() == Biome.FLOWER_FOREST)) {
		        	if (rint == 0 || rint == 1) {
		        		world.generateTree(chunk.getBlock(X, Y, Z).getLocation(), TreeType.TREE);
		        	}else if(rint == 2) {
		        		world.generateTree(chunk.getBlock(X, Y, Z).getLocation(), TreeType.BIG_TREE);
		        	}
		        }else if((bi.getBiome() == Biome.BIRCH_FOREST) || (bi.getBiome() == Biome.BIRCH_FOREST_HILLS) || (bi.getBiome() == Biome.TALL_BIRCH_FOREST) || (bi.getBiome() == Biome.TALL_BIRCH_HILLS)) {
		        	if (rint == 0 || rint == 1) {
		        		world.generateTree(chunk.getBlock(X, Y, Z).getLocation(), TreeType.BIRCH);
		        	}else if(rint == 2) {
		        		world.generateTree(chunk.getBlock(X, Y, Z).getLocation(), TreeType.TALL_BIRCH);
		        	}
		        }else if((bi.getBiome() == Biome.DARK_FOREST) || (bi.getBiome() == Biome.DARK_FOREST_HILLS)) {
		        	if (rint == 0) {
		        		world.generateTree(chunk.getBlock(X, Y, Z).getLocation(), TreeType.DARK_OAK);
		        	}else if(rint == 1) {
		        		world.generateTree(chunk.getBlock(X, Y, Z).getLocation(), TreeType.BROWN_MUSHROOM);
		        	}else if(rint == 2) {
		        		world.generateTree(chunk.getBlock(X, Y, Z).getLocation(), TreeType.RED_MUSHROOM);
		        	}
		        }else if((bi.getBiome() == Biome.GIANT_SPRUCE_TAIGA) || (bi.getBiome() == Biome.GIANT_SPRUCE_TAIGA)) {
		        	if (rint == 0 || rint == 1) {
		        		world.generateTree(chunk.getBlock(X, Y, Z).getLocation(), TreeType.REDWOOD);
		        	}else if(rint == 2) {
		        		world.generateTree(chunk.getBlock(X, Y, Z).getLocation(), TreeType.TALL_REDWOOD);
		        	}
		        }else if((bi.getBiome() == Biome.SWAMP_HILLS) || (bi.getBiome() == Biome.SWAMP)) {
		        	world.generateTree(chunk.getBlock(X, Y, Z).getLocation(), TreeType.SWAMP);
		        }else if((bi.getBiome() == Biome.TAIGA) || (bi.getBiome() == Biome.TAIGA_HILLS) || (bi.getBiome() == Biome.TAIGA_MOUNTAINS)) {
		        	world.generateTree(chunk.getBlock(X, Y, Z).getLocation(), TreeType.REDWOOD);
		        }

		        	 
		    }
		}
	}
}
