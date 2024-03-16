package com.creditcrab.baubletweaks.mixins.late.thaumcraft;

import baubles.api.BaubleType;
import baubles.api.expanded.BaubleExpandedSlots;
import baubles.api.expanded.BaubleItemHelper;
import baubles.api.expanded.IBaubleExpanded;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import thaumcraft.api.aspects.IEssentiaContainerItem;
import thaumcraft.common.items.ItemResource;

import java.util.List;

@Mixin(ItemResource.class)
public abstract class MixinItemResource extends Item implements IBaubleExpanded, IEssentiaContainerItem {
    @Override
    public String[] getBaubleTypes(ItemStack itemStack) {
        return new String[]{BaubleExpandedSlots.charmType};
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemStack) {
        return null;
    }

    @Shadow
    public abstract void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5);

    @Override
    public void onWornTick(ItemStack itemStack, EntityLivingBase entityLivingBase) {
        onUpdate(itemStack, entityLivingBase.worldObj, entityLivingBase,0,true);
    }

    @Override
    public void onEquipped(ItemStack itemStack, EntityLivingBase entityLivingBase) {

    }

    @Override
    public void onUnequipped(ItemStack itemStack, EntityLivingBase entityLivingBase) {

    }

    @Override
    public boolean canEquip(ItemStack itemStack, EntityLivingBase entityLivingBase) {
        if (itemStack.getItemDamage() == 15) return true;
        return false;
    }

    @Override
    public boolean canUnequip(ItemStack itemStack, EntityLivingBase entityLivingBase) {
        return true;
    }

    @Inject(method = "addInformation",at=@At("RETURN"))
    protected void onAddInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4, CallbackInfo ci){
        if (par1ItemStack.getItemDamage() == 15){
            BaubleItemHelper.addSlotInformation(par3List,new String[]{BaubleExpandedSlots.charmType});
        }
    }
}
