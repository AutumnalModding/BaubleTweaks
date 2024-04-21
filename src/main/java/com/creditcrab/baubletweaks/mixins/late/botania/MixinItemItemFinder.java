package com.creditcrab.baubletweaks.mixins.late.botania;

import baubles.api.expanded.BaubleExpandedSlots;
import baubles.api.expanded.BaubleItemHelper;
import baubles.api.expanded.IBaubleExpanded;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import vazkii.botania.api.item.IBaubleRender;
import vazkii.botania.common.item.equipment.bauble.ItemBauble;
import vazkii.botania.common.item.equipment.bauble.ItemItemFinder;

import java.util.List;

@Mixin(ItemItemFinder.class)
public abstract class MixinItemItemFinder extends ItemBauble implements IBaubleExpanded, IBaubleRender {
    public MixinItemItemFinder(String name) {
        super(name);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        BaubleItemHelper.addSlotInformation(par3List,this.getBaubleTypes(par1ItemStack));

    }

    @Override
    public String[] getBaubleTypes(ItemStack itemStack) {
        return new String[]{BaubleExpandedSlots.headType};
    }
}
