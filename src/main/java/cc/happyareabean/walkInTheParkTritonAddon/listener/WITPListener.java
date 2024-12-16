package cc.happyareabean.walkInTheParkTritonAddon.listener;

import cc.happyareabean.walkInTheParkTritonAddon.LocaleUtils;
import cc.happyareabean.walkInTheParkTritonAddon.PlayerUtils;
import cc.happyareabean.walkInTheParkTritonAddon.WalkInTheParkTritonAddon;
import com.rexcantor64.triton.api.TritonAPI;
import com.rexcantor64.triton.api.players.LanguagePlayer;
import dev.efnilite.ip.api.ParkourAPI;
import dev.efnilite.ip.api.event.ParkourJoinEvent;
import dev.efnilite.ip.api.event.ParkourLeaveEvent;
import dev.efnilite.ip.player.ParkourUser;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class WITPListener implements Listener {

    private Map<UUID, BukkitTask> tasks = new HashMap<>();

    @EventHandler
    public void onParkourJoin(ParkourJoinEvent event) {
        ParkourUser user = event.player;
        UUID uuid = user.getUUID();

        LanguagePlayer playerManager = TritonAPI.getInstance().getPlayerManager().get(uuid);
        String name = playerManager.getLang().getName();

        new BukkitRunnable() {
            @Override
            public void run() {
                if (!LocaleUtils.getWITPLocales().contains(name)) return; // Do nothing if the locales are not found in WITP
                if (name.equals(user.locale)) return; // Do nothing if the locales are the same

                PlayerUtils.setWITPPlayerLocale(user, name);
                ParkourAPI.getPlayer(user.player).setup(user.getLocation());

            }
        }.runTaskLaterAsynchronously(WalkInTheParkTritonAddon.INSTANCE, 20L);
    }

    @EventHandler
    public void onParkourLeave(ParkourLeaveEvent event) {
        ParkourUser user = event.player;
        UUID uuid = user.getUUID();

        LanguagePlayer playerManager = TritonAPI.getInstance().getPlayerManager().get(uuid);
        String tritonLang = playerManager.getLang().getName();

        String locale = user.locale;

        if (!LocaleUtils.getTritonLocales().contains(locale)) return; // Do nothing if the locales are not found in Triton
        if (locale.equals(tritonLang)) return; // Do nothing if the locales are the same

        PlayerUtils.setTritonPlayerLocale(user.player, locale);
    }

}
