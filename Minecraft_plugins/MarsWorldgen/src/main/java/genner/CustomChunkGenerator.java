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
    int currentHeight = 47;

    @Override
    public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
    	SimplexOctaveGenerator generator = new SimplexOctaveGenerator(new Random(world.getSeed()), 8);
        ChunkData chunk = createChunkData(world);
        generator.setScale(0.004D);
        for (int X = 0; X < 16; X++) {
            for (int Z = 0; Z < 16; Z++) {
                currentHeight = (int) (generator.noise(chunkX*16+X, chunkZ*16+Z, 0.5D, 0.5D)*20D+60D);
                if(currentHeight<=sealevel) {
                	for (int i = sealevel; i >= currentHeight; i--)
                		chunk.setBlock(X, i, Z, Material.PACKED_ICE);
	                chunk.setBlock(X, currentHeight-1, Z, Material.REDSTONE_ORE);
	                biome.setBiome(chunkX*16+X, chunkZ*16+Z, Biome.DESERT_LAKES);
                } else if(currentHeight>65){
                	currentHeight = (int) (generator.noise(chunkX*16+X, chunkZ*16+Z, 0.5D, 0.5D)*30D+60D);
                	for (int i = 0; i < 18-(83-currentHeight); i++){
                		int rint = random.nextInt(5);
    					if(rint <2) {
    					     chunk.setBlock(X, 83-i, Z,Material.GRANITE);
    					}else if(rint == 3) {
    					     chunk.setBlock(X, 83-i, Z,Material.RED_SANDSTONE);
    					}else{
    					     chunk.setBlock(X, 83-i, Z, Material.RED_SAND);
    					}
                	}
                	chunk.setBlock(X, currentHeight-1, Z, Material.RED_SAND);
                	biome.setBiome(chunkX*16+X, chunkZ*16+Z, Biome.DESERT_HILLS);
                } else {
                	if(biome.getBiome(chunkX*16+X, chunkZ*16+Z)==Biome.RIVER) {
                		currentHeight = (int) (generator.noise(chunkX*16+X, chunkZ*16+Z, 0.5D, 0.5D)*15D+58D);
                	} else {
                		biome.setBiome(chunkX*16+X, chunkZ*16+Z, Biome.DESERT);
                	}
			        int rint = random.nextInt(8);
					if(rint <2) {
					     chunk.setBlock(X, currentHeight, Z,Material.TERRACOTTA);
					}else if(rint == 3) {
					     chunk.setBlock(X, currentHeight, Z,Material.RED_SANDSTONE);
					}else{
					     chunk.setBlock(X, currentHeight, Z, Material.RED_SAND);
					}
	                chunk.setBlock(X, currentHeight-1, Z, Material.TERRACOTTA);
                }
                for (int i = currentHeight-2; i > 0; i--) {
                	if(random.nextInt(2)==0) {
                		chunk.setBlock(X, i, Z, Material.TERRACOTTA);
                	} else{
                		chunk.setBlock(X, i, Z, Material.RED_SANDSTONE);
                	}
                }
                chunk.setBlock(X, 0, Z, Material.BEDROCK);
    		}
        }
        return chunk;
    }
    @Override
    public List<BlockPopulator> getDefaultPopulators(World world) {
    	//return Arrays.asList((BlockPopulator)new CavePopulator(),(BlockPopulator)new LakePopulator(),(BlockPopulator)new GroundPopulator(),(BlockPopulator)new TreePopulator());
    	return Arrays.asList((BlockPopulator)new CavePopulator(),(BlockPopulator)new GroundPopulator());
    }
}