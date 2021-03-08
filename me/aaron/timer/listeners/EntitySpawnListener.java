package me.aaron.timer.listeners;

import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class EntitySpawnListener implements Listener {
    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent e) {
        World world = e.getEntity().getWorld();
        if (world.getName().equalsIgnoreCase("MLG-World")) {
            e.setCancelled(true);
        }
    }
}
