package com.creditcrab.baubletweaks;

import baubles.api.expanded.BaubleExpandedSlots;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import thaumcraft.api.ThaumcraftApi;

@Mod(modid = BaubleTweaks.MODID, version = Tags.VERSION, name = "BaubleTweaks", acceptedMinecraftVersions = "[1.7.10]",
dependencies = "after:Thaumcraft")
public class BaubleTweaks {

    public static final String MODID = "baubletweaks";
    public static final Logger LOG = LogManager.getLogger(MODID);

    @SidedProxy(clientSide = "com.creditcrab.baubletweaks.ClientProxy", serverSide = "com.creditcrab.baubletweaks.CommonProxy")
    public static CommonProxy proxy;

    public static Item baubleOfRevealing;
    public static Item baublePrimal;

    @Mod.EventHandler
    // preInit "Run before anything else. Read your config, create blocks, items, etc, and register them with the
    // GameRegistry." (Remove if not needed)
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
        BaubleExpandedSlots.tryAssignSlotsUpToMinimum(BaubleExpandedSlots.headType,1);
        BaubleExpandedSlots.tryAssignSlotsUpToMinimum(BaubleExpandedSlots.charmType,1);
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
