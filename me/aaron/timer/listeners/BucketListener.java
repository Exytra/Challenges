package me.aaron.timer.listeners;

import me.aaron.timer.challenges.RandomMLG;
import me.aaron.timer.challenges.WaterMLG;
import me.aaron.timer.utils.Permissions;
import me.aaron.timer.utils.SettingsItems;
import me.aaron.timer.utils.SettingsModes;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;

public class BucketListener implements Listener {
    @EventHandler
    public void onBucketFill(PlayerBucketFillEvent e){
        Player p = e.getPlayer();

        if (Permissions.getRank(p) == Permissions.Rank.GUEST) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onBucketEmpty(PlayerBucketEmptyEvent e) {
        Player p = e.getPlayer();

        if (Permissions.getRank(p) == Permissions.Rank.GUEST) {
            e.setCancelled(true);
        }
        if (e.getBlock().getWorld().getName().equals("MLG-World")) {
            if (SettingsModes.challenge.get(SettingsItems.ItemType.RANDOM_MLG) == SettingsItems.ItemState.ENABLED) {
                RandomMLG.blockLocations.add(e.getBlock().getLocation());
            }
            if (SettingsModes.challenge.get(SettingsItems.ItemType.WATER_MLG) == SettingsItems.ItemState.ENABLED) {
                WaterMLG.waterLocations.add(e.getBlock().getLocation());
            }
        }
    }
}
