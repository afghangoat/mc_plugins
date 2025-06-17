package me.MarioPlayer.MKWG;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.noise.PerlinOctaveGenerator;
import org.bukkit.util.noise.SimplexOctaveGenerator;

public class BasicChunkGenerator extends ChunkGenerator {
	double threshold = 0.0; // the cutoff point for terrain
    int middle = 70;
    /**
     * 
     * @param x
     * X co-ordinate of the block to be set in the array
     * @param y
     * Y co-ordinate of the block to be set in the array
     * @param z
     * Z co-ordinate of the block to be set in the array
     * @param chunk
     * An array containing the Block id's of all the blocks in the chunk. The first offset
     * is the block section number. There are 16 block sections, stacked vertically, each of which
     * 16 by 16 by 16 blocks.
     * @param material
     * The material to set the block to.
     */
    void setBlock(int x, int y, int z, byte[][] chunk, Material material) {
        if (chunk[y >> 4] == null)
            chunk[y >> 4] = new byte[16 * 16 * 16];
        if (!(y <= 256 && y >= 0 && x <= 16 && x >= 0 && z <= 16 && z >= 0))
            return;
        try {
            chunk[y >> 4][((y & 0xF) << 8) | (z << 4) | x] = (byte) material
                    .getId();
        } catch (Exception e) {
            // do nothing
        }
    }
 
    
    /**
     * @param world
     * The world the chunk belongs to
     * @param rand
     * Don't use this, make a new random object using the world seed (world.getSeed())
     * @param biome
     * Use this to set/get the current biome
     * @param ChunkX and ChunkZ
     * The x and z co-ordinates of the current chunk.
     */
    public byte[][] generateBlockSections(World world, Random rand,
            int ChunkX, int ChunkZ, BiomeGrid biomes) {
    	
        Random random = new Random(world.getSeed());
        SimplexOctaveGenerator gen = new SimplexOctaveGenerator(random, 8);
        PerlinOctaveGenerator gen2 = new PerlinOctaveGenerator(world.getSeed(), 8);
 
        byte[][] chunk = new byte[world.getMaxHeight() / 16][];
 
        gen2.setScale(1/64);
        gen.setScale(1/96); //The distance between peaks of the terrain. Scroll down more to see what happens when you play with this
        double threshold = this.threshold; //scroll down to see what happens when you play with this.
 
        for (int x=0;x<16;x++) {
            for (int z=0;z<16;z++) {
                int real_x = x+ChunkX * 16;
                int real_z = z+ChunkZ*16;
 
                double height = middle+gen2.noise(real_x, real_z, 0.5, 0.5)*middle/3; // generate some smoother terrain
 
                for (int y=1; y<height && y<256; y++) {
                    if(y > middle-middle/3) {
                        double noise = gen.noise(real_x, y, real_z, 0.5, 0.5);
                        if(noise > threshold) //explained above
                            setBlock(x,y,z,chunk,Material.STONE); //set the block solid
                    } else {
                        setBlock(x,y,z,chunk,Material.STONE);
                    }
                }
            }
        }
        return chunk;
    }
 
    /**
     * Returns a list of all of the block populators (that do "little" features)
     * to be called after the chunk generator
     */
        @Override
        public List<BlockPopulator> getDefaultPopulators(World world) {
            ArrayList<BlockPopulator> pops = new ArrayList<BlockPopulator>();
            pops.add(new GrassPopulator());
            return pops;
        }
        
}
