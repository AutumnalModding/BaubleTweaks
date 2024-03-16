package com.creditcrab.baubletweaks.mixins.late.thaumcraft;


import baubles.api.BaubleType;
import baubles.api.expanded.BaubleExpandedSlots;
import baubles.api.expanded.BaubleItemHelper;
import baubles.api.expanded.IBaubleExpanded;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import thaumcraft.common.items.armor.ItemGoggles;

import java.util.List;

@Mixin(ItemGoggles.class)
public abstract class MixinItemGoggles extends ItemArmor implements IBaubleExpanded {
    public MixinItemGoggles(ArmorMaterial p_i45325_1_, int p_i45325_2_, int p_i45325_3_) {
        super(p_i45325_1_, p_i45325_2_, p_i45325_3_);
    }

    @Override
    public String[] getBaubleTypes(ItemStack itemStack) {
        return new String[]{BaubleExpandedSlots.headType};
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
    @Inject(method = "addInformation",at=@At("RETURN"))
    protected void onAddInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4, CallbackInfo ci){

        BaubleItemHelper.addSlotInformation(par3List,new String[]{BaubleExpandedSlots.headType});

    }
}
