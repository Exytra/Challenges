package me.aaron.timer.utils;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;

import java.util.Random;

public class CustomChunkGenerator extends ChunkGenerator {
    public int currentHeight;
    @Override
    public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
        //SimplexOctaveGenerator generator = new SimplexOctaveGenerator(new Random(world.getSeed()), 8);
        //generator.setScale(0.005D);
        ChunkData chunk = createChunkData(world);

        //BlockPopulator populator = null;

        //populator.populate(world, random, world.getChunkAt(chunkX, chunkZ));

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                //for (int y = 0; y < 256; y++) {
                    //Material block = chunk.getType(x, y, z);
                    //Bukkit.broadcastMessage(block + " Hallo");
                    //if (block.isSolid() && block.isBlock() && !block.isAir() && !block.isTransparent()) {
                        //chunk.setBlock(x, y, z, RandomChunkGeneration.blocks.get(block));
                    //}
                chunk.setBlock(x, 0, z, Material.BEDROCK);
                for (int i = 1; i < 70; i ++) {
                    chunk.setBlock(x, i, z, Material.TNT);
                }

                //}
            }
        }
        return chunk;
    }
}
