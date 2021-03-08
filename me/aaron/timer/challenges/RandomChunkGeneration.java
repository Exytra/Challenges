package me.aaron.timer.challenges;

import me.aaron.timer.Main;
import me.aaron.timer.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class RandomChunkGeneration extends ChunkGenerator{
    public static HashMap<Material, Material> blocks = new HashMap<>();
    private static ArrayList<Chunk> queued = new ArrayList<>();
    private static boolean running = false;
    private static int runningChunkChanges = 0;

    public static void start() {
        if (blocks.isEmpty()) {
            ArrayList<Material> normalBlock = new ArrayList<>();
            ArrayList<Material> changedBlock;
            normalBlock.addAll(Arrays.asList(Material.values()));

            normalBlock.removeIf(mat -> !mat.isSolid() || !mat.isBlock() || mat.isAir() || mat.isTransparent());
            changedBlock = normalBlock;

            for (int i = 0; i < normalBlock.size(); i ++) {
                int randomNumber = Utils.getRandomInt(0, changedBlock.size() - 1);
                blocks.put(normalBlock.get(i), changedBlock.get(randomNumber));
            }
        }
        loadChunks();
    }

    public static void addChunk(Chunk chunk) {
        queued.add(chunk);
    }

    public static void loadChunks() {
            if (!queued.isEmpty()) {
                Chunk c = queued.get(0);
                int X = c.getX() * 16;
                int Z = c.getZ() * 16;
                for (int x = 0; x < 16; x++) {
                    for (int z = 0; z < 16; z++) {
                        for (int y = 0; y < 256; y++) {
                            Block block = c.getWorld().getBlockAt(X + x, y, Z + z);
                            try {
                                if (block.getType().isSolid() && block.getType().isBlock() && !block.getType().isAir() && !block.getType().isTransparent()) {
                                    block.setType(blocks.get(block.getType()));
                                }
                            } catch (Exception e) {
                                Bukkit.broadcastMessage("§cCaused Error: " + block.getType());
                            }
                        }
                    }
                }
                Bukkit.broadcastMessage("Finished new Chunk: " + X + ", 70, " + Z);
                queued.remove(0);
                Bukkit.broadcastMessage("Remaining: " + queued.size());
                Bukkit.getScheduler().scheduleSyncDelayedTask(JavaPlugin.getPlugin(Main.class), RandomChunkGeneration::loadChunks, 1);
            } else {
                Bukkit.getScheduler().scheduleSyncDelayedTask(JavaPlugin.getPlugin(Main.class), RandomChunkGeneration::loadChunks, 10);
            }
    }
}
