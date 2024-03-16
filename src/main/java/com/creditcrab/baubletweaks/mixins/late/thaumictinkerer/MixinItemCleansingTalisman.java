package com.creditcrab.baubletweaks.mixins.late.thaumictinkerer;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import baubles.api.expanded.BaubleExpandedSlots;
import baubles.api.expanded.BaubleItemHelper;
import baubles.api.expanded.IBaubleExpanded;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import thaumic.tinkerer.common.item.ItemCleansingTalisman;
import thaumic.tinkerer.common.registry.ItemBase;

import java.util.List;

@Mixin(ItemCleansingTalisman.class)
public abstract class MixinItemCleansingTalisman extends ItemBase implements IBauble, IBaubleExpanded {
    @Override
    public String[] getBaubleTypes(ItemStack itemStack) {
        return new String[]{BaubleExpandedSlots.amuletType,BaubleExpandedSlots.charmType};
    }

    @Inject(method = "addInformation",at=@At("RETURN"))
    protected void onAddInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4, CallbackInfo ci){
        BaubleItemHelper.addSlotInformation(par3List,new String[]{BaubleExpandedSlots.amuletType,BaubleExpandedSlots.charmType});
    }
}
