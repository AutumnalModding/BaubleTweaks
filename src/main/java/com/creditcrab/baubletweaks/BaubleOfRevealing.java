package com.creditcrab.baubletweaks;

import baubles.api.BaubleType;
import baubles.api.expanded.IBaubleExpanded;
import baubles.common.BaublesExpanded;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import thaumcraft.common.items.armor.ItemGoggles;

public class BaubleOfRevealing extends ItemGoggles implements IBaubleExpanded {
    public BaubleOfRevealing(ArmorMaterial enumarmormaterial, int j, int k) {
        super(enumarmormaterial, j, k);
        this.setUnlocalizedName("baubleOfRevealing");
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemStack) {
        return null;
    }

    @Override
    public void onWornTick(ItemStack itemStack, EntityLivingBase entityLivingBase) {

    }

    @Override
    public void onEquipped(ItemStack itemStack, EntityLivingBase entityLivingBase) {

    }

    @Override
    public void onUnequipped(ItemStack itemStack, EntityLivingBase entityLivingBase) {

    }

    @Override
    public boolean canEquip(ItemStack itemStack, EntityLivingBase entityLivingBase) {
        return true;
    }

    @Override
    public boolean canUnequip(ItemStack itemStack, EntityLivingBase entityLivingBase) {
        return true;
    }

    @Override
    public String[] getBaubleTypes(ItemStack itemStack){
        return new String[]{"head","amulet"};
    }
}
