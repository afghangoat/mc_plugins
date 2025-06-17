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
		    int amount = random.nextInt(3)+1;
		    for (int i = 1; i < amount; i++) {
		        int X = random.nextInt(15);
		        int Z = random.nextInt(15);
		        int Y;
		        for (Y = world.getMaxHeight()-1; chunk.getBlock(X, Y, Z).getType() == Material.AIR; Y--);
		        if(Y>10) {
		        	if(chunk.getBlock(X, Y, Z).getType() != Material.PACKED_ICE) {
		        		if(random.nextInt(2)==0) {
		        			chunk.getBlock(X, Y+1, Z).setType(Material.RED_SANDSTONE_SLAB);
		        		} else {
		        			chunk.getBlock(X, Y+1, Z).setType(Material.RED_SANDSTONE);
		        			chunk.getBlock(X, Y+2, Z).setType(Material.RED_SANDSTONE);
		        		}
		        	}
		
		    	}
		    }
		}
	}
}
