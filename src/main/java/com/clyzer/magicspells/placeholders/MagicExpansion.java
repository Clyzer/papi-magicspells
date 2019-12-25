package com.clyzer.magicspells.placeholders;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import com.nisovin.magicspells.MagicSpells;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;

public class MagicExpansion extends PlaceholderExpansion {
    
    private final String VERSION = getClass().getPackage().getImplementationVersion();
    private MagicSpells magicspells;

    @Override
    public String getIdentifier() {
        return "magicspells";
    }

    @Override
    public String getAuthor() {
        return "Clyzer";
    }

    @Override
    public String getVersion() {
        return VERSION;
    }

    @Override
    public String getRequiredPlugin() {
        return "MagicSpells";
    }

    @Override
    public boolean canRegister() {
        if (!Bukkit.getPluginManager().isPluginEnabled(getRequiredPlugin())) { return false; }
        magicspells = (MagicSpells) Bukkit.getPluginManager().getPlugin(getRequiredPlugin());
        return super.register() && magicspells != null;
    }

    @Override
    public String onRequest(OfflinePlayer offlinePlayer, String params) {
        if (offlinePlayer == null || !offlinePlayer.isOnline()) { return "The player is not online"; }

        Player player = offlinePlayer.getPlayer();
        switch (params) {
            case "mana":
                return String.valueOf(MagicSpells.getManaHandler().getMana(player));
                
            case "maxmana":
                return String.valueOf(MagicSpells.getManaHandler().getMaxMana(player));
                
            default:
                return null;
        }
    }

}
