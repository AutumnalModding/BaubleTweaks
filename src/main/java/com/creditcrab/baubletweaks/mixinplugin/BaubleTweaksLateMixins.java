package com.creditcrab.baubletweaks.mixinplugin;

import com.gtnewhorizon.gtnhmixins.ILateMixinLoader;
import com.gtnewhorizon.gtnhmixins.LateMixin;
import cpw.mods.fml.relauncher.FMLLaunchHandler;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

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
        if (FMLLaunchHandler.side().isClient()){
            mixins.add("thaumcraft.MixinTileNodeRenderer");
            mixins.add("thaumcraft.MixinRenderEventHandler");
        }
        return mixins;
    }
}
