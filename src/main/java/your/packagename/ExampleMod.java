package your.packagename;

import net.minheur.potoflux.PotoFlux;
import net.minheur.potoflux.loader.PotoFluxLoadingContext;
import net.minheur.potoflux.loader.mod.Mod;
import net.minheur.potoflux.loader.mod.ModEventBus;
import net.minheur.potoflux.loader.mod.events.RegisterLangEvent;
import net.minheur.potoflux.loader.mod.events.RegisterTabsEvent;
import your.packagename.tabs.Tabs;
import your.packagename.translations.ExampleModTranslations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Mod(modId = ExampleMod.MOD_ID, version = "1.0", compatibleVersions = ExampleMod.COMPATIBLE_VERSIONS)
public class ExampleMod {
    public static final String MOD_ID = "yourmodid";
    public static final String[] COMPATIBLE_VERSIONS = new String[] {
            "6.0"
    };

    public ExampleMod() {
        ModEventBus modEventBus = PotoFluxLoadingContext.get().getModEventBus();

        modEventBus.addListener(RegisterTabsEvent.class, this::onRegisterTabs);
        modEventBus.addListener(RegisterLangEvent.class, this::onRegisterLang);
    }

    private void onRegisterTabs(RegisterTabsEvent event) {
        Tabs.register(event.reg);
    }
    private void onRegisterLang(RegisterLangEvent event) {
        event.registerLang(new ExampleModTranslations());
    }

    public static Path getModDir() {
        Path dir = PotoFlux.getModDataDir().resolve(MOD_ID);
        try {
            Files.createDirectories(dir);
        } catch (IOException ignored) {}
        return dir;
    }
}
