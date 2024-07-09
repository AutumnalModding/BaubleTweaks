package com.creditcrab.baubletweaks;

import baubles.api.expanded.BaubleExpandedSlots;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = BaubleTweaks.MODID, version = Tags.VERSION, name = "BaubleTweaks", acceptedMinecraftVersions = "[1.7.10]",
dependencies = "after:Thaumcraft;after:ThaumicExploration;after:ThaumicTinkerer;after:Botania;after:ThermalExpansion;after:alfheim")
public class BaubleTweaks {

    public static final String MODID = "baubletweaks";

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        BaubleExpandedSlots.tryRegisterType("cosmetic");

        BaubleExpandedSlots.tryAssignSlotsUpToMinimum(BaubleExpandedSlots.headType, 1);
        BaubleExpandedSlots.tryAssignSlotsUpToMinimum(BaubleExpandedSlots.bodyType, 1);
        BaubleExpandedSlots.tryAssignSlotsUpToMinimum(BaubleExpandedSlots.charmType, 1);
        BaubleExpandedSlots.tryAssignSlotsUpToMinimum(BaubleExpandedSlots.wingsType, 1);
        BaubleExpandedSlots.tryAssignSlotsUpToMinimum("cosmetic", 4);
    }
}
