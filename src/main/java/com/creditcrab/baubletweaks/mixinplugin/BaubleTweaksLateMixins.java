package com.creditcrab.baubletweaks.mixinplugin;

import baubles.api.expanded.BaubleExpandedSlots;
import com.creditcrab.baubletweaks.BaubleTweaks;
import com.gtnewhorizon.gtnhmixins.ILateMixinLoader;
import com.gtnewhorizon.gtnhmixins.LateMixin;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.relauncher.FMLLaunchHandler;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.world.ChunkEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@LateMixin
public class BaubleTweaksLateMixins implements ILateMixinLoader {


    @Override
    public String getMixinConfig() {
        return "mixins.baubletweaks.late.json";
    }

    @Override
    public List<String> getMixins(Set<String> loadedCoreMods) {

        boolean thaumGoggles;
        boolean foodTalisman;
        boolean cleansingTalisman;
        boolean xpTalisman;
        boolean divaCharm;
        boolean flightTiara;
        boolean holyCloak;
        boolean goldenLaurel;
        boolean monocle;
        boolean itemFinder;
        boolean capacitor;
        boolean alfheimShield;
        boolean botaniaCosmetics;

        var config = new Configuration(new File(Loader.instance().getConfigDir(), "BaubleTweaks.cfg"));
        config.load();

        if (Loader.isModLoaded("Thaumcraft")) {
            thaumGoggles = config.get("Thaumcraft", "Goggles of Revealing", true).getBoolean(true);
        } else {
            thaumGoggles = false;
        }

        if (Loader.isModLoaded("ThaumicExploration")) {
            foodTalisman = config.get("Thaumic Exploration", "Food Talisman", true).getBoolean(true);
        } else {
            foodTalisman = false;
        }

        if (Loader.isModLoaded("ThaumicTinkerer")) {
            cleansingTalisman = config.get("Thaumic Tinkerer", "Cleansing Talisman", true).getBoolean(true);
            xpTalisman = config.get("Thaumic Tinkerer", "XP Talisman", true).getBoolean(true);
        } else {
            cleansingTalisman = false;
            xpTalisman = false;
        }

        if (Loader.isModLoaded("Botania")) {
            divaCharm = config.get("Botania", "Diva Charm", true).getBoolean(true);
            flightTiara = config.get("Botania", "Flight Tiara", true).getBoolean(true);
            holyCloak = config.get("Botania", "Holy Cloak", true).getBoolean(true);
            goldenLaurel = config.get("Botania", "Golden Laurel", true).getBoolean(true);
            monocle = config.get("Botania", "Manaseer Monocle", true).getBoolean(true);
            itemFinder = config.get("Botania", "The Spectator", true).getBoolean(true);
            botaniaCosmetics = config.get("Botania", "Cosmetic Baubles", true).getBoolean(true);
        } else {
            divaCharm = false;
            flightTiara = false;
            holyCloak = false;
            goldenLaurel = false;
            monocle = false;
            itemFinder = false;
            botaniaCosmetics = false;
        }

        if (Loader.isModLoaded("ThermalExpansion")) {
            capacitor = config.get("Thermal Expansion", "Flux Capacitor", true).getBoolean(true);
        } else {
            capacitor = false;
        }

        if (Loader.isModLoaded("alfheim")) {
            alfheimShield = config.get("Alfheim", "Coat of Arms", true).getBoolean(true);
        } else {
            alfheimShield = false;
        }

        config.save();


        List<String> mixins = new ArrayList<>();
        if (thaumGoggles) {
            if (FMLLaunchHandler.side().isClient()) {
                mixins.add("thaumcraft.MixinTileNodeRenderer");
                mixins.add("thaumcraft.MixinRenderEventHandler");
            }
            mixins.add("thaumcraft.MixinItemGoggles");
            mixins.add("thaumcraft.MixinItemResource");
        }

        if (foodTalisman) {
            mixins.add("thaumicexploration.MixinItemFoodTalisman");
        }

        if (cleansingTalisman) {
            mixins.add("thaumictinkerer.MixinItemCleansingTalisman");
        }

        if (xpTalisman) {
            mixins.add("thaumictinkerer.MixinItemXPTalisman");
        }

        if (divaCharm) {
            mixins.add("botania.MixinItemDivaCharm");
        }

        if (flightTiara) {
            mixins.add("botania.MixinItemFlightTiara");
            mixins.add("botania.MixinHUDHandler");
        }

        if (holyCloak) {
            mixins.add("botania.MixinItemHolyCloak");
        }

        if (goldenLaurel) {
            mixins.add("botania.MixinItemGoldenLaurel");
        }

        if (monocle) {
            mixins.add("botania.MixinItemMonocle");
        }

        if (itemFinder) {
            mixins.add("botania.MixinItemItemFinder");
        }

        if (botaniaCosmetics) {
            mixins.add("botania.MixinItemBaubleCosmetic");
        }

        if (capacitor) {
            mixins.add("thermalexpansion.MixinItemCapacitor");
        }

        if (alfheimShield) {
            mixins.add("alfheim.MixinItemCoatOfArms");
        }

        return mixins;
    }
}
