package cc.happyareabean.walkInTheParkTritonAddon;

import cc.happyareabean.walkInTheParkTritonAddon.listener.WITPListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class WalkInTheParkTritonAddon extends JavaPlugin {

    public static WalkInTheParkTritonAddon INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;

        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new WITPListener(), this);
    }

}
