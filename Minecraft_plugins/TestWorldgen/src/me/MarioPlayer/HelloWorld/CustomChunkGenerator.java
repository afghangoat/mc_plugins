package me.MarioPlayer.HelloWorld;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.noise.PerlinOctaveGenerator;
import org.bukkit.util.noise.SimplexOctaveGenerator;

public class CustomChunkGenerator extends ChunkGenerator {
    // Remember this
	int sealevel = 63;
    int currentHeight = 50;
	double threshold = 0.0; // the cutoff point for terrain
    int middle = 70;
    int middletop = 100;
    
    @Override
    public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
    	SimplexOctaveGenerator generator = new SimplexOctaveGenerator(new Random(world.getSeed()), 8);
    	PerlinOctaveGenerator gen2 = new PerlinOctaveGenerator(world.getSeed(), 8);
    	SimplexOctaveGenerator gen3 = new SimplexOctaveGenerator(world.getSeed(), 8);
        ChunkData chunk = createChunkData(world);
        generator.setScale(0.005D);
        gen2.setScale(1/64);
        double threshold = this.threshold;

        for (int x=0;x<16;x++) {
            for (int z=0;z<16;z++) {
                int real_x = x+chunkX * 16;
                int real_z = z+chunkZ*16;
 
                double height = middle+gen2.noise(real_x, real_z, 0.5, 0.5)*middle/3; // generate some smoother terrain
                double height2 = height + 30; 
 
                for (int y=1; y<height && y<256; y++) {
                    if(y > middle-middle/3) {
                        double noise = generator.noise(real_x, y, real_z, 0.5, 0.5);
                        if(noise > threshold) //explained above
                        	if(y >= height) {
                        		chunk.setBlock(x, y, z, Material.GRASS_BLOCK);
                        	} else {
                        		chunk.setBlock(x, y, z, Material.STONE);
                        	}
                        	
//                        	chunk.setBlock(x, y-1, z, Material.DIRT);//set the block solid
//                        	chunk.setBlock(x, y-2, z, Material.STONE);
                    } else {
                    	chunk.setBlock(x, y, z, Material.GRASS_BLOCK);
                    	chunk.setBlock(x, y-1, z, Material.DIRT);//set the block solid
                    }
                }
                for (int y=(int)height+1; y<height2 && y<256; y++) {
                    if(y > height) {
                        double noise = gen3.noise(real_x, y-30, real_z, 0.5, 0.5);
                        if(noise > threshold+5) //explained above
                       	if(y >= height2) {
                       		chunk.setBlock(x, y, z, Material.GRASS_BLOCK);
                       	} else {
                       		chunk.setBlock(x, y, z, Material.STONE);
                       	}
                      	
                    }
               }
                chunk.setBlock(x, 0, z, Material.BEDROCK);
            }
        }
//        for (int X = 0; X < 16; X++)
//            for (int Z = 0; Z < 16; Z++) {
//              currentHeight = (int) (generator.noise(chunkX*16+X, chunkZ*16+Z, 0.5D, 0.5D)*15D+50D);
//                chunk.setBlock(X, currentHeight, Z, Material.GRASS_BLOCK);
//                chunk.setBlock(X, currentHeight-1, Z, Material.DIRT);
//                for (int i = currentHeight-2; i > 0; i--)
//                    chunk.setBlock(X, i, Z, Material.STONE);
//                chunk.setBlock(X, 0, Z, Material.BEDROCK);
//            }
//(BlockPopulator)new OrePopulator() after lakepop
        return chunk;
    }
    @Override
    public List<BlockPopulator> getDefaultPopulators(World world) {
        return Arrays.asList((BlockPopulator)new CavePopulator(),(BlockPopulator)new RuinsPopulator(),(BlockPopulator)new DungeonPopulator(),(BlockPopulator)new DecoPopulator(),(BlockPopulator)new LakePopulator(),(BlockPopulator)new GrassPopulator(),(BlockPopulator)new TreePopulator(),(BlockPopulator)new DesertPopulator());
    }
}