package com.creditcrab.baubletweaks.mixins.late.botania;

import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import baubles.api.expanded.BaubleExpandedSlots;
import baubles.api.expanded.BaubleItemHelper;
import baubles.api.expanded.IBaubleExpanded;
import baubles.common.BaublesExpanded;
import baubles.common.lib.PlayerHandler;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import vazkii.botania.api.item.IBaubleRender;
import vazkii.botania.common.item.equipment.bauble.ItemBauble;
import vazkii.botania.common.item.equipment.bauble.ItemGoldenLaurel;

import java.util.List;

@Mixin(value = ItemGoldenLaurel.class, remap = false)
public abstract class MixinItemGoldenLaurel extends ItemBauble implements IBaubleExpanded, IBaubleRender {
    public MixinItemGoldenLaurel(String name) {
        super(name);
    }

    /**
     * @author Nick
     * @reason Stop making me write these :(
     */
    @SubscribeEvent(
        priority = EventPriority.HIGHEST
    )
    @Overwrite
    public void onPlayerDeath(LivingDeathEvent event) {
        if (event.entity instanceof EntityPlayer player) {
            IInventory baubles = BaublesApi.getBaubles(player);
            int slot = BaubleExpandedSlots.getIndexesOfAssignedSlotsOfType(BaubleExpandedSlots.headType)[0];
            ItemStack amulet = baubles.getStackInSlot(slot);
            if (amulet != null && amulet.getItem() == this) {
                event.setCanceled(true);
                player.setHealth(player.getMaxHealth());
                player.addPotionEffect(new PotionEffect(Potion.resistance.id, 300, 6));
                player.addChatMessage(new ChatComponentTranslation("botaniamisc.savedByLaurel"));
                player.worldObj.playSoundAtEntity(player, "botania:goldenLaurel", 1.0F, 0.3F);
                baubles.setInventorySlotContents(slot,null);
            }
        }

    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        BaubleItemHelper.addSlotInformation(par3List,this.getBaubleTypes(par1ItemStack));
    }

    @Override
    public String[] getBaubleTypes(ItemStack itemStack) {
        return new String[]{BaubleExpandedSlots.headType};
    }

}
