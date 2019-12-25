package com.clyzer.magicspells.placeholders;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import com.nisovin.magicspells.MagicSpells;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;

public class MagicExpansion extends PlaceholderExpansion {
    
    private final String VERSION = getClass().getPackage().getImplementationVersion();
    private MagicSpells magicspells;

    /**
     * Defines the name of the expansion that is also used in the
     * placeholder itself.
     * 
     * @return {@code example} as String
     */
    @Override
    public String getIdentifier() {
        return "magicspells";
    }

    /**
     * The author of the expansion.
     * 
     * @return {@code everyone} as String
     */
    @Override
    public String getAuthor() {
        return "Clyzer";
    }

    /**
     * Returns the version of the expansion as String.
     *
     * @return The VERSION String
     */
    @Override
    public String getVersion() {
        return VERSION;
    }

    /**
     * Returns the name of the required plugin.
     *
     * @return {@code DeluxeTags} as String
     */
    @Override
    public String getRequiredPlugin() {
        return "MagicSpells";
    }

    /**
     * Used to check if the expansion is able to register.
     * 
     * @return true or false depending on if the required plugin is installed
     */
    @Override
    public boolean canRegister() {
        if (!Bukkit.getPluginManager().isPluginEnabled(getRequiredPlugin())) { return false; }
        magicspells = (MagicSpells) Bukkit.getPluginManager().getPlugin(getRequiredPlugin());
        return super.register() && magicspells != null;
    }

    /**
     * This method is called when a placeholder is used and maches the set
     * {@link #getIdentifier() identifier}
     *
     * @param  offlinePlayer
     *         The player to parse placeholders for
     * @param  params
     *         The part after the identifier ({@code %identifier_params%})
     *
     * @return Possible-null String
     */
    @Override
    public String onRequest(OfflinePlayer offlinePlayer, String params) {
        if (offlinePlayer == null || !offlinePlayer.isOnline()) { return "El jugador no esta en linea"; }

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
