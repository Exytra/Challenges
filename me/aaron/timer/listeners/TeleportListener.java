package me.aaron.timer.listeners;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class TeleportListener implements Listener {
    @EventHandler
    public void onTeleport(PlayerTeleportEvent e) {
        Player p = e.getPlayer();

        Location to = e.getTo();

        //p.teleport(to);

        //p.sendMessage("§8[§6Teleport§8] §9§l" + p.getName() + "§7 wurde zu §9" + to.getBlockX() + ", " + to.getBlockY() + ", " + to.getBlockZ() + "§7 teleportiert.");
    }
}
