package com.creditcrab.baubletweaks.mixins.late.thermalexpansion;

import baubles.api.BaubleType;
import baubles.api.expanded.BaubleExpandedSlots;
import baubles.api.expanded.BaubleItemHelper;
import baubles.api.expanded.IBaubleExpanded;
import cofh.api.energy.IEnergyContainerItem;
import cofh.core.item.ItemBase;
import cofh.thermalexpansion.item.ItemCapacitor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ItemCapacitor.class)
public abstract class MixinItemCapacitor extends ItemBase implements IBaubleExpanded, IEnergyContainerItem {

    @Inject(method = "addInformation",at=@At("RETURN"))
    public void onAddInformation(ItemStack var1, EntityPlayer var2, List var3, boolean var4, CallbackInfo ci){
        BaubleItemHelper.addSlotInformation(var3,getBaubleTypes(null));
    }
    @Override
    public String[] getBaubleTypes(ItemStack itemStack) {
        return new String[]{BaubleExpandedSlots.charmType};
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemStack) {
        return null;
    }

    @Shadow
    public abstract void onUpdate(ItemStack var1, World var2, Entity var3, int var4, boolean var5);

    @Override
    public void onWornTick(ItemStack itemStack, EntityLivingBase entityLivingBase) {
        onUpdate(itemStack, entityLivingBase.worldObj, entityLivingBase,8,false);
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
}
