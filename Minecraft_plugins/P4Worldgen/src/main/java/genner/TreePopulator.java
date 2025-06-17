package genner;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

public class TreePopulator extends BlockPopulator{
	@Override
	public void populate(World world, Random random, Chunk chunk) {
		if (random.nextBoolean()) {
		    int amount = random.nextInt(8)+2;
		    for (int i = 1; i < amount; i++) {
		        int X = random.nextInt(15);
		        int Z = random.nextInt(15);
		        int Y;
		        for (Y = world.getMaxHeight()-1; chunk.getBlock(X, Y, Z).getType() == Material.AIR; Y--);
		        if(random.nextInt(7)==2) {
		        	world.generateTree(chunk.getBlock(X, Y-1, Z).getLocation(), TreeType.MEGA_REDWOOD);
		        } else {
		        	world.generateTree(chunk.getBlock(X, Y-1, Z).getLocation(), TreeType.DARK_OAK);
		        }
		    }
		}
	}
}
