package me.aaron.timer.listeners;

import org.bukkit.Chunk;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;

public class ChunkLoadListener implements Listener {
    private static int changed = 0;

    @EventHandler
    public void onChunkLoad(ChunkLoadEvent e) {
        Chunk chunk = e.getChunk();
        //Bukkit.broadcastMessage("neuer Chunk");
        int X = chunk.getX() * 16;
        int Z = chunk.getZ() * 16;
        if (e.isNewChunk()) {
            //Bukkit.broadcastMessage(X + ", 70, " + Z);
        }
        /*if (changed < 1) {
            changed ++;
            //Bukkit.broadcastMessage(X + ", 70, " + Z);
            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {
                    for (int y = 0; y < 255; y++) {
                        if (chunk.getWorld().getBlockAt(X + x, y, Z + z).getType().isSolid()) {
                            if (RandomChunkGeneration.blocks.get(chunk.getWorld().getBlockAt(X + x, y, Z + z).getType()) == null) {
                                RandomChunkGeneration.blocks.put(chunk.getWorld().getBlockAt(X + x, y, Z + z).getType(), Material.values()[10]);
                            }
                            chunk.getWorld().getBlockAt(X + x, y, Z + z).setType(RandomChunkGeneration.blocks.get(chunk.getWorld().getBlockAt(X + x, y, Z + z).getType()));
                        }
                    }
                }
            }
        }*/
    }
}
