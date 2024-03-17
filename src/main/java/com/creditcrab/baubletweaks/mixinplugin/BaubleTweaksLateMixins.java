package com.creditcrab.baubletweaks.mixinplugin;

import com.gtnewhorizon.gtnhmixins.ILateMixinLoader;
import com.gtnewhorizon.gtnhmixins.LateMixin;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.relauncher.FMLLaunchHandler;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import net.minecraftforge.event.world.ChunkEvent;

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
        List<String> mixins = new ArrayList<>();
        if (Loader.isModLoaded("Thaumcraft")){
            if (FMLLaunchHandler.side().isClient()){
                mixins.add("thaumcraft.MixinTileNodeRenderer");
                mixins.add("thaumcraft.MixinRenderEventHandler");
            }
            mixins.add("thaumcraft.MixinItemGoggles");
            mixins.add("thaumcraft.MixinItemResource");
        }
        if (Loader.isModLoaded("ThaumicExploration")){
            mixins.add("thaumicexploration.MixinItemFoodTalisman");
        }
        if (Loader.isModLoaded("ThaumicTinkerer")){
            mixins.add("thaumictinkerer.MixinItemCleansingTalisman");
            mixins.add("thaumictinkerer.MixinItemXPTalisman");
        }
        if (Loader.isModLoaded("Botania")){
            mixins.add("botania.MixinItemDivaCharm");
            mixins.add("botania.MixinItemFlightTiara");
            mixins.add("botania.MixinHUDHandler");
            mixins.add("botania.MixinItemHolyCloak");
        }
        if(Loader.isModLoaded("ThermalExpansion")){
            mixins.add("thermalexpansion.MixinItemCapacitor");
        }
        return mixins;
    }
}
