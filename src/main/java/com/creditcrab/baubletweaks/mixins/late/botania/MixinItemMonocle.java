package com.creditcrab.baubletweaks.mixins.late.botania;

import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import baubles.api.expanded.BaubleExpandedSlots;
import baubles.api.expanded.BaubleItemHelper;
import baubles.api.expanded.IBaubleExpanded;
import baubles.common.lib.PlayerHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import vazkii.botania.api.item.IBurstViewerBauble;
import vazkii.botania.api.item.ICosmeticAttachable;
import vazkii.botania.api.item.ICosmeticBauble;
import vazkii.botania.common.item.equipment.bauble.ItemBauble;
import vazkii.botania.common.item.equipment.bauble.ItemMonocle;

import java.util.List;

@Mixin(value = ItemMonocle.class, remap = false)
public abstract class MixinItemMonocle extends ItemBauble implements IBaubleExpanded, IBurstViewerBauble, ICosmeticBauble {
    public MixinItemMonocle(String name) {
        super(name);
    }

    @Override
    public String[] getBaubleTypes(ItemStack itemStack) {
        return new String[]{BaubleExpandedSlots.headType};
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        BaubleItemHelper.addSlotInformation(par3List,this.getBaubleTypes(par1ItemStack));

    }

    /**
     * @author Nick
     * @reason Stop making me write these :(
     */
    @Overwrite
    public static boolean hasMonocle(EntityPlayer player) {

        ItemStack stack = BaublesApi.getBaubles(player).getStackInSlot(BaubleExpandedSlots.getIndexesOfAssignedSlotsOfType(BaubleExpandedSlots.headType)[0]);
        if(stack == null) return false;
        Item item = stack.getItem();
        return item instanceof IBurstViewerBauble;
    }

}
