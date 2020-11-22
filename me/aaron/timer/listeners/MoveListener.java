package me.aaron.timer.listeners;

import me.aaron.timer.Main;
import me.aaron.timer.challenges.Trafficlight;
import me.aaron.timer.timer.Timer;
import me.aaron.timer.utils.SettingsItems;
import me.aaron.timer.utils.SettingsModes;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MoveListener implements Listener {
    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (SettingsModes.challenge.get(SettingsItems.ItemType.DIRT) == SettingsItems.ItemState.ENABLED) {
            if (Timer.state == Timer.TimerState.RUNNING || SettingsModes.settings.get(SettingsItems.ItemType.TIMER) == SettingsItems.ItemState.DISABLED) {
                if (p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() != Material.DIRT && p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() != (Material.AIR) && p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() != Material.GRASS) {
                    p.setHealth(0);
                    for (Player pl : Bukkit.getOnlinePlayers()) {
                        pl.sendMessage(" ");
                        pl.sendMessage("§8[§6Dirt§8] §9§l" + p.getName() + " §7stand auf §6" + p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType());
                        pl.sendMessage(" ");
                    }
                }
            }
        }
        if (SettingsModes.challenge.get(SettingsItems.ItemType.TRAFFICLIGHT) == SettingsItems.ItemState.ENABLED) {
            if (e.getFrom().getBlockY() - e.getTo().getBlockY() >= 3 || e.getFrom().getBlockZ() - e.getTo().getBlockZ() != 0 || e.getFrom().getBlockX() - e.getTo().getBlockX() != 0)
            if (Timer.state == Timer.TimerState.RUNNING || SettingsModes.settings.get(SettingsItems.ItemType.TIMER) == SettingsItems.ItemState.DISABLED) {
                if (Trafficlight.currentstate == Trafficlight.TrafficLightState.RED || Trafficlight.currentstate == Trafficlight.TrafficLightState.YELLOW_RED) {
                    for (Player pl : Bukkit.getOnlinePlayers()) {
                        pl.sendMessage(Main.getPrefix("Ampel", "Der Spieler §9§l" + p.getName() + " §7hat sich bei §cRot §7bewegt."));
                    }
                    p.setHealth(0);
                }
            }
        }
        if (SettingsModes.challenge.get(SettingsItems.ItemType.ONEBLOCKONEHEART) == SettingsItems.ItemState.ENABLED) {
            if (Timer.state == Timer.TimerState.RUNNING || SettingsModes.settings.get(SettingsItems.ItemType.TIMER) == SettingsItems.ItemState.DISABLED) {
                Location from = e.getFrom();
                Location to = e.getTo();
                assert to != null;
                if (from.getBlockX() - to.getBlockX() >= 1 || from.getBlockZ() - to.getBlockZ() >= 1 || to.getBlockX() - from.getBlockX() >= 1 || to.getBlockZ() - from.getBlockZ() >= 1) {
                    p.damage(2);
                }
            }
        }
    }
}
