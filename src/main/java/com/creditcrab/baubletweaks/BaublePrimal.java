package com.creditcrab.baubletweaks;

import baubles.api.BaubleType;
import baubles.api.expanded.BaubleExpandedSlots;
import baubles.api.expanded.IBaubleExpanded;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import org.lwjgl.Sys;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.entities.EntityAspectOrb;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.playerdata.PacketResearchComplete;
import thaumcraft.common.lib.research.ResearchManager;

public class BaublePrimal extends Item implements IBaubleExpanded {

    public BaublePrimal(){
        this.setUnlocalizedName("baubletweaks:baublePrimal");
        this.setMaxStackSize(1);
    }
    public IIcon icon;

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister ir){
        icon = ir.registerIcon("thaumcraft:charm");
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIconFromDamage(int par1){
        return icon;
    }

    @Override
    public String[] getBaubleTypes(ItemStack itemStack) {
        return new String[]{BaubleExpandedSlots.charmType};
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemStack) {
        return null;
    }

    @Override
    public void onWornTick(ItemStack itemStack, EntityLivingBase entity) {
        World world = entity.worldObj;

        if (!entity.worldObj.isRemote ) {
            int r = world.rand.nextInt(20000);

            if (r < 20) {
                Aspect aspect = null;
                switch (world.rand.nextInt(6)) {
                    case 0:
                        aspect = Aspect.AIR;
                        break;
                    case 1:
                        aspect = Aspect.EARTH;
                        break;
                    case 2:
                        aspect = Aspect.FIRE;
                        break;
                    case 3:
                        aspect = Aspect.WATER;
                        break;
                    case 4:
                        aspect = Aspect.ORDER;
                        break;
                    case 5:
                        aspect = Aspect.ENTROPY;
                }

                if (aspect != null) {
                    EntityAspectOrb orb = new EntityAspectOrb(world, entity.posX, entity.posY, entity.posZ, aspect, 1);
                    world.spawnEntityInWorld(orb);
                }
            } else if (r == 42 && entity instanceof EntityPlayer && !ResearchManager.isResearchComplete(((EntityPlayer)entity).getCommandSenderName(), "FOCUSPRIMAL") && !ResearchManager.isResearchComplete(((EntityPlayer)entity).getCommandSenderName(), "@FOCUSPRIMAL")) {
                ((EntityPlayer)entity).addChatMessage(new ChatComponentTranslation("§5§o" + StatCollector.translateToLocal("tc.primalcharm.trigger"), new Object[0]));
                PacketHandler.INSTANCE.sendTo(new PacketResearchComplete("@FOCUSPRIMAL"), (EntityPlayerMP)entity);
                Thaumcraft.proxy.getResearchManager().completeResearch((EntityPlayer)entity, "@FOCUSPRIMAL");
            }
        }
    }

    @Override
    public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5){
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
