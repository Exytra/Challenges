package me.aaron.timer.gm;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class Gm {
    public void survival(Player p) {
        p.setGameMode(GameMode.SURVIVAL);
    }
    public void creative(Player p) {
        p.setGameMode(GameMode.CREATIVE);
    }
    public void adventure(Player p) {
        p.setGameMode(GameMode.ADVENTURE);
    }
    public void spectator(Player p) {
        p.setGameMode(GameMode.SPECTATOR);
    }
}
