package com.creditcrab.baubletweaks.mixins.late.thaumicexploration;

import baubles.api.BaubleType;
import baubles.api.expanded.BaubleExpandedSlots;
import baubles.api.expanded.BaubleItemHelper;
import baubles.api.expanded.IBaubleExpanded;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import flaxbeard.thaumicexploration.interop.AppleCoreInterop;
import flaxbeard.thaumicexploration.item.ItemFoodTalisman;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.FoodStats;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(value = ItemFoodTalisman.class,remap = false)
public abstract class MixinItemFoodTalisman extends Item implements IBaubleExpanded {

    @Inject(method = "addInformation", at=@At("HEAD"))
    protected void onAddInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4, CallbackInfo ci){
        BaubleItemHelper.addSlotInformation(par3List,new String[]{BaubleExpandedSlots.charmType});
    }
    @Shadow
    public abstract boolean isEdible(ItemStack food, EntityPlayer player);

    @Shadow
    public abstract void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5);

    @Override
    public String[] getBaubleTypes(ItemStack itemStack) {
        return new String[] {BaubleExpandedSlots.charmType};
        };

    @Override
    public BaubleType getBaubleType(ItemStack itemStack) {
        return null;
    }

    @Override
    public void onWornTick(ItemStack par1ItemStack, EntityLivingBase par3Entity) {
        onUpdate(par1ItemStack,par3Entity.worldObj,par3Entity,0,true);
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


