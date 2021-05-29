package me.aaron.timer.listeners;

import me.aaron.timer.Main;
import me.aaron.timer.challenges.RandomDrops;
import me.aaron.timer.utils.*;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public class BlockBreakListener implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        World world = p.getWorld();
        Block block = e.getBlock();
        Location location = block.getLocation();
        if (Timer.state == Timer.TimerState.PAUSED && SettingsModes.settings.get(SettingsItems.ItemType.TIMER) == SettingsItems.ItemState.ENABLED) {
            if (p.getGameMode() == GameMode.SURVIVAL) {
                e.setCancelled(true);
                p.sendMessage("§cDer Timer ist noch pausiert!");
            }
        }
        if (Permissions.getRank(p) == Permissions.Rank.GUEST) {
            e.setCancelled(true);
            p.sendMessage("§7Du hast den Rang §9Guest §7und kannst daher keine Blöcke abbauen. Der Rang kann mit §9/rank <guest | user | op | admin> §7von einem Admin oder der Serverkonsole geupdatet werden.");
        }
        if (world.getName().equalsIgnoreCase("MLG-World")) {
            e.setCancelled(true);
        }

        if (SettingsModes.challenge.get(SettingsItems.ItemType.RANDOM_DROPS) == SettingsItems.ItemState.ENABLED) {
            if (Timer.state == Timer.TimerState.RUNNING || SettingsModes.settings.get(SettingsItems.ItemType.TIMER) == SettingsItems.ItemState.DISABLED) {
                if (p.getGameMode() == GameMode.SURVIVAL) {
                    boolean found = false;
                    e.setDropItems(false);
                    if (RandomDrops.allRandom) {
                        while (!found) {
                            try {
                                world.dropItem(location.add(.5, .5, .5), new ItemStack(Material.values()[Utils.getRandomInt(0, Material.values().length - 1)]));
                                found = true;
                            } catch (Exception ex) {
                                found = false;
                            }
                        }
                    } else {
                        while (!found) {
                            try {
                                world.dropItem(location.add(.5, .5, .5), RandomDrops.drops.get(RandomDrops.makeToItemStack(block.getType())));
                                found = true;
                            } catch (Exception exception) {
                                Random random = new Random();
                                RandomDrops.drops.put(RandomDrops.makeToItemStack(block.getType()), RandomDrops.makeToItemStack(Material.values()[random.nextInt(Material.values().length)]));
                                found = false;
                            }
                        }
                    }
                }
            }
        }

        if (SettingsModes.challenge.get(SettingsItems.ItemType.EVERYTHING_REVERSE) == SettingsItems.ItemState.ENABLED) {
            if (Timer.state == Timer.TimerState.RUNNING || SettingsModes.settings.get(SettingsItems.ItemType.TIMER) == SettingsItems.ItemState.DISABLED) {

            }
        }

        if (SettingsModes.challenge.get(SettingsItems.ItemType.EVERYTHING_REVERSE) == SettingsItems.ItemState.ENABLED && SettingsModes.customSettingsBooleans.get(SettingsItems.ItemType.EVERYTHING_REVERSE).get(1)) {
            if (Timer.state == Timer.TimerState.RUNNING || SettingsModes.settings.get(SettingsItems.ItemType.TIMER) == SettingsItems.ItemState.DISABLED) {
                final Material material = block.getType();
                Bukkit.getScheduler().scheduleSyncDelayedTask(JavaPlugin.getPlugin(Main.class), () -> {
                    e.getBlock().setType(material);
                }, Utils.TimeToTicks(0, 0, SettingsModes.customSettingsInts.get(SettingsItems.ItemType.EVERYTHING_REVERSE).get(0)));
            }
        }
    }
}
