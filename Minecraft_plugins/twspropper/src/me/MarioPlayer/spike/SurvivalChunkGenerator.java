package me.MarioPlayer.spike;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bukkit.craftbukkit.v1_16_R3.generator.CustomChunkGenerator;
import org.bukkit.craftbukkit.v1_16_R3.generator.InternalChunkGenerator;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;

import net.minecraft.server.v1_16_R3.IChunkProvider;
import net.minecraft.server.v1_16_R3.StructureSettings;
import net.minecraft.server.v1_16_R3.WorldChunkManager;
import net.minecraft.server.v1_16_R3.WorldServer;

public class SurvivalChunkGenerator extends InternalChunkGenerator {
    public SurvivalChunkGenerator(WorldChunkManager worldchunkmanager, StructureSettings structuresettings) {
		super(worldchunkmanager, structuresettings);
		// TODO Auto-generated constructor stub
	}

	private final IChunkProvider provider;

    public NormalChunkGenerator(World world, long seed) {
        provider = world.worldProvider.getChunkProvider();
    }

    public byte[] generate(org.bukkit.World world, Random random, int x, int z) {
        throw new UnsupportedOperationException("Not supported.");
    }

    public boolean canSpawn(org.bukkit.World world, int x, int z) {
        return ((CraftWorld) world).getHandle().worldProvider.canSpawn(x, z);
    }

    public List<BlockPopulator> getDefaultPopulators(org.bukkit.World world) {
        return new ArrayList<BlockPopulator>();
    }

    public boolean isChunkLoaded(int i, int i1) {
        return provider.isChunkLoaded(i, i1);
    }

    public Chunk getOrCreateChunk(int i, int i1) {
        return provider.getOrCreateChunk(i, i1);
    }

    public Chunk getChunkAt(int i, int i1) {
        return provider.getChunkAt(i, i1);
    }

    public void getChunkAt(IChunkProvider icp, int i, int i1) {
        provider.getChunkAt(icp, i, i1);
    }

    public boolean saveChunks(boolean bln, IProgressUpdate ipu) {
        return provider.saveChunks(bln, ipu);
    }

    public boolean unloadChunks() {
        return provider.unloadChunks();
    }

    public boolean canSave() {
        return provider.canSave();
    }

    public List<?> getMobsFor(EnumCreatureType ect, int i, int i1, int i2) {
        return provider.getMobsFor(ect, i, i1, i2);
    }

    public ChunkPosition findNearestMapFeature(World world, String string, int i, int i1, int i2) {
        return provider.findNearestMapFeature(world, string, i, i1, i2);
    }

    public void recreateStructures(int i, int j) {
        provider.recreateStructures(i, j);
    }

    // n.m.s implementations always return 0. (The true implementation is in ChunkProviderServer)
    public int getLoadedChunks() {
        return 0;
    }

    public String getName() {
        return "NormalWorldGenerator";
    }

    public void c() {}
}