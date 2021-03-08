package me.aaron.timer.listeners;

import me.aaron.timer.utils.Permissions;
import me.aaron.timer.utils.SettingsItems;
import me.aaron.timer.utils.SettingsModes;
import me.aaron.timer.utils.Timer;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

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
            e.setCancelled(true);
        }
    }
}
