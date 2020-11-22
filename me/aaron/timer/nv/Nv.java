package me.aaron.timer.nv;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

public class Nv {
    public void night_vision(Player p) {
        p.addPotionEffect((new PotionEffect(PotionEffectType.NIGHT_VISION, 999999, 255)));
    }
}
