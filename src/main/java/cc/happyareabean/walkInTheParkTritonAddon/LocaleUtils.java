package cc.happyareabean.walkInTheParkTritonAddon;

import com.rexcantor64.triton.api.TritonAPI;
import com.rexcantor64.triton.api.language.Language;
import dev.efnilite.ip.config.Locales;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LocaleUtils {

    public static List<String> getWITPLocales() {
        return new ArrayList<>(Locales.locales.keySet());
    }

    public static List<String> getTritonLocales() {
        return TritonAPI.getInstance().getLanguageManager().getAllLanguages().stream().map(Language::getName).collect(Collectors.toList());
    }

}

