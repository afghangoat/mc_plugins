package genner;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

public class GroundPopulator extends BlockPopulator{
	@Override
	public void populate(World world, Random random, Chunk chunk) {
		if (random.nextBoolean()) {
		    int amount = random.nextInt(4)+12;
		    for (int i = 1; i < amount; i++) {
		        int X = random.nextInt(15);
		        int Z = random.nextInt(15);
		        int Y;
		        for (Y = world.getMaxHeight()-1; chunk.getBlock(X, Y, Z).getType() == Material.AIR; Y--);
		        if(Y>10&&Y<70) {
		        	if(chunk.getBlock(X, Y, Z).getType() == Material.GRASS_BLOCK) {
		        		int rint=random.nextInt(5);
				        if(rint==0) {
				        	chunk.getBlock(X, Y+1, Z).setType(Material.GRASS);
				        } else if(rint==1){
				        	chunk.getBlock(X, Y+1, Z).setType(Material.BLUE_ORCHID);
				        } else {
				        	chunk.getBlock(X, Y+1, Z).setType(Material.TALL_GRASS);
				       	}
		        	}
		
		    	}
		    }
		}
	}
}
