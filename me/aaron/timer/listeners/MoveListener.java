package me.aaron.timer.listeners;

import me.aaron.timer.Main;
import me.aaron.timer.challenges.Trafficlight;
import me.aaron.timer.timer.Timer;
import me.aaron.timer.utils.SettingsItems;
import me.aaron.timer.utils.SettingsModes;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.concurrent.TimeUnit;

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
            if (e.getFrom() != e.getTo()) {
                if (Timer.state == Timer.TimerState.RUNNING || SettingsModes.settings.get(SettingsItems.ItemType.TIMER) == SettingsItems.ItemState.DISABLED) {
                    if (Trafficlight.currentstate == Trafficlight.TrafficLightState.RED || Trafficlight.currentstate == Trafficlight.TrafficLightState.YELLOW_RED) {
                        for (Player pl : Bukkit.getOnlinePlayers()) {
                            pl.sendMessage(Main.getPrefix("Ampel", "Der Spieler §9§l" + p.getName() + " §7hat sich bei §cRot §7bewegt."));
                        }
                        p.setHealth(0);
                    }
                }
            }
        }
        if (SettingsModes.challenge.get(SettingsItems.ItemType.ONEBLOCKONEHEART) == SettingsItems.ItemState.ENABLED) {
            if (Timer.state == Timer.TimerState.RUNNING || SettingsModes.settings.get(SettingsItems.ItemType.TIMER) == SettingsItems.ItemState.DISABLED) {
                if (e.getFrom().getBlockX() != e.getTo().getBlockX() || e.getFrom().getBlockZ() != e.getTo().getBlockZ()) {
                    p.damage(2);
                }
            }
        }
        if (SettingsModes.challenge.get(SettingsItems.ItemType.BEDROCKWALL) == SettingsItems.ItemState.ENABLED) {
            if (Timer.state == Timer.TimerState.RUNNING || SettingsModes.settings.get(SettingsItems.ItemType.TIMER) == SettingsItems.ItemState.DISABLED) {
                if (e.getFrom().getBlock() != e.getTo().getBlock()) {
                    final Player bp = e.getPlayer();
                    final Block bb = e.getTo().getBlock();
                    if (bp.getGameMode() != GameMode.SPECTATOR && bp.getGameMode() != GameMode.CREATIVE) {
                        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(Main.class), () -> {
                            if (Timer.state == Timer.TimerState.RUNNING || SettingsModes.settings.get(SettingsItems.ItemType.TIMER) == SettingsItems.ItemState.DISABLED) {
                                if (SettingsModes.challenge.get(SettingsItems.ItemType.BEDROCKWALL)  == SettingsItems.ItemState.ENABLED) {
                                    for (int i = 0; i < 256; i++) {
                                        Location loc = new Location(bp.getWorld(), bb.getLocation().getBlockX(), i, bb.getLocation().getBlockZ());
                                        loc.getBlock().setType(Material.BEDROCK);
                                    }
                                }
                            }
                        }, 200);
                    }
                }
            }
        }
        if (SettingsModes.challenge.get(SettingsItems.ItemType.THEFLOORISLAVA) == SettingsItems.ItemState.ENABLED) {
            if (Timer.state == Timer.TimerState.RUNNING || SettingsModes.settings.get(SettingsItems.ItemType.TIMER) == SettingsItems.ItemState.DISABLED) {
                if (e.getFrom().getBlock() != e.getTo().getBlock()) {
                    final Player bp = e.getPlayer();
                    final Block bb = bp.getLocation().getBlock().getRelative(BlockFace.DOWN);
                    final Material bt = bb.getType();
                    if (bp.getGameMode() != GameMode.SPECTATOR && bp.getGameMode() != GameMode.CREATIVE) {
                        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(Main.class), () -> {
                            if (bb.getType() != Material.MAGMA_BLOCK && bb.getType() != Material.LAVA && bb.getType() != Material.AIR && bb.getType() != Material.WATER && bb.getType() != Material.GRASS) {
                                bb.setType(Material.MAGMA_BLOCK);
                                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(Main.class), () -> {
                                    bb.setType(Material.LAVA);
                                    Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(Main.class), () -> {
                                        bb.setType(bt);
                                    }, 400);
                                }, 100);
                            }
                        }, 10);
                    }
                }
            }
        }
    }
}
