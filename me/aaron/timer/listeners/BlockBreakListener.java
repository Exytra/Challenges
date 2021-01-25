package me.aaron.timer.listeners;

import me.aaron.timer.challenges.RandomDrops;
import me.aaron.timer.utils.Permissions;
import me.aaron.timer.utils.Timer;
import me.aaron.timer.utils.SettingsItems;
import me.aaron.timer.utils.SettingsModes;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;
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

        if (SettingsModes.challenge.get(SettingsItems.ItemType.RANDOM_DROPS) == SettingsItems.ItemState.ENABLED) {
            if (Timer.state == Timer.TimerState.RUNNING || SettingsModes.settings.get(SettingsItems.ItemType.TIMER) == SettingsItems.ItemState.DISABLED) {
                if (p.getGameMode() == GameMode.SURVIVAL) {
                    try {
                        e.setDropItems(false);
                        world.dropItem(location.add(.5, .5, .5), RandomDrops.drops.get(RandomDrops.makeToItemStack(block.getType())));
                    } catch (Exception exception) {
                        Random random = new Random();
                        RandomDrops.drops.put(RandomDrops.makeToItemStack(block.getType()), RandomDrops.makeToItemStack(Material.values()[random.nextInt(Material.values().length)]));
                        world.dropItem(location.add(.5, .5, .5), RandomDrops.drops.get(RandomDrops.makeToItemStack(block.getType())));
                    }
                }
            }
        }
    }
}
