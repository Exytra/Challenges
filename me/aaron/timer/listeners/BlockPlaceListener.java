package me.aaron.timer.listeners;

import me.aaron.timer.Main;
import me.aaron.timer.challenges.RandomMLG;
import me.aaron.timer.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class BlockPlaceListener implements Listener {
    @EventHandler
    public void onPlayer(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        World world = e.getBlock().getWorld();

        if (Timer.state == Timer.TimerState.PAUSED && SettingsModes.settings.get(SettingsItems.ItemType.TIMER) == SettingsItems.ItemState.ENABLED) {
            if (p.getGameMode() == GameMode.SURVIVAL) {
                e.setCancelled(true);
                p.sendMessage("§cDer Timer ist noch pausiert!");
            }
        }

        if (Permissions.getRank(p) == Permissions.Rank.GUEST) {
            e.setCancelled(true);
            p.sendMessage("§7Du hast den Rang §9Guest §7und kannst daher keine Blöcke plazieren. Der Rang kann mit §9/rank <guest | user | op | admin> §7von einem Admin oder der Serverkonsole geupdatet werden.");
        }

        if (world.getName().equalsIgnoreCase("MLG-World")) {
            if (e.getBlock().getType() != Material.SLIME_BLOCK && e.getBlock().getType() != Material.COBWEB) {
                e.setCancelled(true);
            } else {
                if (SettingsModes.challenge.get(SettingsItems.ItemType.RANDOM_MLG) == SettingsItems.ItemState.ENABLED) {
                    RandomMLG.blockLocations.add(e.getBlock().getLocation());
                }
            }
        }

        if (SettingsModes.challenge.get(SettingsItems.ItemType.EVERYTHING_REVERSE) == SettingsItems.ItemState.ENABLED && SettingsModes.customSettingsBooleans.get(SettingsItems.ItemType.EVERYTHING_REVERSE).get(0)) {
            if (Timer.state == Timer.TimerState.RUNNING || SettingsModes.settings.get(SettingsItems.ItemType.TIMER) == SettingsItems.ItemState.DISABLED) {
                Bukkit.getScheduler().scheduleSyncDelayedTask(JavaPlugin.getPlugin(Main.class), () -> {
                    e.getBlock().setType(Material.AIR);
                }, Utils.TimeToTicks(0, 0, SettingsModes.customSettingsInts.get(SettingsItems.ItemType.EVERYTHING_REVERSE).get(0)));
            }
        }
    }
}
