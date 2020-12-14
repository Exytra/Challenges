package me.aaron.timer.listeners;

import me.aaron.timer.Main;
import me.aaron.timer.utils.Permissions;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;

public class GamemodeChangeListener implements Listener {
    @EventHandler
    public void onGamemodeChange(PlayerGameModeChangeEvent e) {
        Player p = e.getPlayer();
        if (Permissions.getRank(p).equals(Permissions.Rank.GUEST)) {
            e.setCancelled(true);
        } else {
            p.sendMessage(Main.getPrefix("GameMode", "Dein §9Spielmodus §7wurde zu §9" + e.getNewGameMode().name().substring(0, 1).toUpperCase() + e.getNewGameMode().name().substring(1).toLowerCase() + "§7 geändert."));
        }
    }
}
