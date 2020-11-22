package me.aaron.timer.weather;

import org.bukkit.WeatherType;
import org.bukkit.entity.Player;

public class Weather {
    public void rain(Player p) {
        p.setPlayerWeather(WeatherType.DOWNFALL);
    }
}
