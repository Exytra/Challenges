package me.aaron.timer.listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();

        if(p.getGameMode().equals(GameMode.SPECTATOR)) {
            e.setFormat("§8§o" + p.getName() + "§7§o » " + e.getMessage());
        } else {
            e.setFormat("§9" + p.getName() + "§f§7 » §f" + e.getMessage());
        }
    }
}
