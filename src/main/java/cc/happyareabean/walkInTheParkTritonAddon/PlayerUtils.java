package cc.happyareabean.walkInTheParkTritonAddon;

import com.rexcantor64.triton.api.TritonAPI;
import dev.efnilite.ip.api.ParkourAPI;
import dev.efnilite.ip.player.ParkourPlayer;
import dev.efnilite.ip.player.ParkourUser;
import org.bukkit.entity.Player;

public class PlayerUtils {

    public static void setWITPPlayerLocale(ParkourUser player, String name) {
        if (!LocaleUtils.getWITPLocales().contains(name)) return;
        ParkourPlayer parkourPlayer = ParkourAPI.getPlayer(player.player);

        parkourPlayer.locale = name;
        parkourPlayer._locale = name;
    }

    public static void setTritonPlayerLocale(Player player, String name) {
        var languageManager = TritonAPI.getInstance().getLanguageManager();
        var language = languageManager.getLanguageByName(name);

        if (language.isEmpty()) return;

        TritonAPI.getInstance().getPlayerManager().get(player.getUniqueId()).setLang(language.get());
    }

}
