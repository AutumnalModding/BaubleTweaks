package com.creditcrab.baubletweaks;

import baubles.api.expanded.BaubleExpandedSlots;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import thaumcraft.api.ThaumcraftApi;

import java.io.File;

@Mod(modid = BaubleTweaks.MODID, version = Tags.VERSION, name = "BaubleTweaks", acceptedMinecraftVersions = "[1.7.10]",
dependencies = "after:Thaumcraft;after:ThaumicExploration;after:ThaumicTinkerer;after:Botania;after:ThermalExpansion")
public class BaubleTweaks {

    public static final String MODID = "baubletweaks";
    public static final Logger LOG = LogManager.getLogger(MODID);

    public static final Configuration CONFIGURATION = new Configuration(new File(Loader.instance().getConfigDir(),"BaubleTweaks.cfg"));

    @SidedProxy(clientSide = "com.creditcrab.baubletweaks.ClientProxy", serverSide = "com.creditcrab.baubletweaks.CommonProxy")
    public static CommonProxy proxy;

    public static boolean gogglesOfRevealing = true;
    public static boolean foodTalisman = true;
    public static boolean cleansingTalisman = true;
    public static  boolean xpTalisman = true;
    public static boolean divaCharm = true;
    public static boolean flightTiara = true;
    public static boolean holyCloak = true;
    public static boolean goldenLaurel = true;
    public static boolean monocle = true;
    public static boolean itemFinder = true;
    public static boolean capacitor = true;

    @Mod.EventHandler
    // preInit "Run before anything else. Read your config, create blocks, items, etc, and register them with the
    // GameRegistry." (Remove if not needed)
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
        BaubleExpandedSlots.tryAssignSlotsUpToMinimum(BaubleExpandedSlots.headType,1);
        BaubleExpandedSlots.tryAssignSlotsUpToMinimum(BaubleExpandedSlots.bodyType,1);
        BaubleExpandedSlots.tryAssignSlotsUpToMinimum(BaubleExpandedSlots.charmType,1);

        gogglesOfRevealing = CONFIGURATION.get("Thaumcraft","Goggles of Revealing",gogglesOfRevealing).getBoolean(gogglesOfRevealing);
        foodTalisman =  CONFIGURATION.get("Thaumic Exploration","Food Talisman",foodTalisman).getBoolean(foodTalisman);
        cleansingTalisman = CONFIGURATION.get("Thaumic Tinkerer","Cleansing Talisman",cleansingTalisman).getBoolean(cleansingTalisman);
        xpTalisman = CONFIGURATION.get("Thaumic Tinkerer","XP Talisman",xpTalisman).getBoolean(xpTalisman);
        divaCharm = CONFIGURATION.get("Botania","Diva Charm",divaCharm).getBoolean(divaCharm);
        flightTiara = CONFIGURATION.get("Botania","Flight Tiara",flightTiara).getBoolean(flightTiara);
        holyCloak = CONFIGURATION.get("Botania","Holy Cloak",holyCloak).getBoolean(holyCloak);
        goldenLaurel = CONFIGURATION.get("Botania","Golden Laurel",goldenLaurel).getBoolean(goldenLaurel);
        monocle = CONFIGURATION.get("Botania","Monocle",monocle).getBoolean(monocle);
        itemFinder = CONFIGURATION.get("Botania","The Spectator",itemFinder).getBoolean(itemFinder);
        capacitor = CONFIGURATION.get("Thermal Expansion","Flux Capacitor",capacitor).getBoolean(capacitor);
    }

    @Mod.EventHandler
    // load "Do your mod setup. Build whatever data structures you care about. Register recipes." (Remove if not needed)
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    // postInit "Handle interaction with other mods, complete your setup based on this." (Remove if not needed)
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    @Mod.EventHandler
    // register server commands in this event handler (Remove if not needed)
    public void serverStarting(FMLServerStartingEvent event) {
        proxy.serverStarting(event);
    }
}
