package genner;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.noise.SimplexOctaveGenerator;


public class CustomChunkGenerator extends ChunkGenerator {
    int sealevel=50;
    int currentHeight = 50;

    @Override
    public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
    	SimplexOctaveGenerator generator = new SimplexOctaveGenerator(new Random(world.getSeed()), 8);
        ChunkData chunk = createChunkData(world);
        generator.setScale(0.006D);
        for (int X = 0; X < 16; X++)
            for (int Z = 0; Z < 16; Z++) {
                currentHeight = (int) (generator.noise(chunkX*16+X, chunkZ*16+Z, 0.5D, 0.5D)*25D+64D);
                if(currentHeight>70) {
                	int currentHeight2=currentHeight-70;
                	currentHeight+=(int)(currentHeight2*1.5D);
                	if(currentHeight>85) {
                		 if(random.nextInt(5) ==0) {
                			 chunk.setBlock(X, currentHeight, Z, Material.SNOW_BLOCK);
                		 } else {
                			 chunk.setBlock(X, currentHeight, Z, Material.SNOW);
                		 }
		                chunk.setBlock(X, currentHeight-1, Z, Material.SNOW_BLOCK);
                	} else {
	                	int rint = random.nextInt(3);
				        if(rint ==0) {
				        	chunk.setBlock(X, currentHeight, Z,Material.ANDESITE);
				        }else if(rint == 2) {
				        	chunk.setBlock(X, currentHeight, Z,Material.GRAVEL);
				        }
		                chunk.setBlock(X, currentHeight-1, Z, Material.COBBLESTONE);
                	}
	                for (int i = currentHeight-2; i > 0; i--)
	                    chunk.setBlock(X, i, Z, Material.STONE);
	                biome.setBiome(chunkX*16+X, chunkZ*16+Z, Biome.MOUNTAINS);
                } else if(currentHeight<=sealevel) {
                	for (int i = sealevel; i >= currentHeight; i--)
                		chunk.setBlock(X, i, Z, Material.WATER);
	                chunk.setBlock(X, currentHeight-1, Z, Material.MOSSY_COBBLESTONE);
	                for (int i = currentHeight-2; i > 0; i--)
	                    chunk.setBlock(X, i, Z, Material.STONE);
	                biome.setBiome(chunkX*16+X, chunkZ*16+Z, Biome.SWAMP);
                } else {
                	if(biome.getBiome(chunkX*16+X, chunkZ*16+Z)==Biome.RIVER||biome.getBiome(chunkX*16+X, chunkZ*16+Z)==Biome.OCEAN) {
                		if(random.nextInt(6)==0) {
				        	chunk.setBlock(X, currentHeight, Z,Material.GRASS_BLOCK);
				        	if(random.nextInt(2) ==0) {
				        		chunk.setBlock(X, currentHeight+1, Z,Material.SUGAR_CANE);
				        	}
				        }else {
				        	chunk.setBlock(X, currentHeight, Z,Material.WATER);
				        	if(random.nextInt(2) ==0) {
				        		chunk.setBlock(X, currentHeight+1, Z,Material.LILY_PAD);
				        	}
				        }
                	} else {
		                int rint = random.nextInt(9);
				        if(rint <2) {
				        	chunk.setBlock(X, currentHeight, Z,Material.COARSE_DIRT);
				        }else if(rint == 3) {
				        	chunk.setBlock(X, currentHeight, Z,Material.GRAVEL);
				        }else{
				        	 chunk.setBlock(X, currentHeight, Z, Material.GRASS_BLOCK);
				        }
				        biome.setBiome(chunkX*16+X, chunkZ*16+Z, Biome.SWAMP);
                	}
                	chunk.setBlock(X, currentHeight-1, Z, Material.DIRT);
                	for (int i = currentHeight-2; i > 0; i--)
	                    chunk.setBlock(X, i, Z, Material.STONE);
                }
                chunk.setBlock(X, 0, Z, Material.BEDROCK);
            }
        return chunk;
    }
    @Override
    public List<BlockPopulator> getDefaultPopulators(World world) {
    	return Arrays.asList((BlockPopulator)new CavePopulator(),(BlockPopulator)new LakePopulator(),(BlockPopulator)new GroundPopulator(),(BlockPopulator)new TreePopulator());
    }
}