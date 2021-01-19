package me.aaron.timer.listeners;

import me.aaron.timer.utils.Permissions;
import me.aaron.timer.utils.Timer;
import me.aaron.timer.utils.SettingsItems;
import me.aaron.timer.utils.SettingsModes;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
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
    }
}
