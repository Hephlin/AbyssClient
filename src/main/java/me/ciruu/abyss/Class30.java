package me.ciruu.abyss;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockDeadBush;
import net.minecraft.block.BlockFire;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.passive.EntityAmbientCreature;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityShulkerBullet;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.potion.Potion;
import net.minecraft.util.EnumHand;
import net.minecraft.util.MovementInput;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class Class30 {

    public static void Method2714(Entity entity, boolean bl) {
        if (bl) {
            Globals.mc.player.connection.sendPacket((Packet)new CPacketUseEntity(entity));
        } else {
            Globals.mc.playerController.attackEntity((EntityPlayer)Globals.mc.player, entity);
        }
    }

    public static void Method1524(Entity entity, boolean bl, boolean bl2) {
        Class30.Method2714(entity, bl);
        if (bl2) {
            Globals.mc.player.swingArm(EnumHand.MAIN_HAND);
        }
    }

    public static Vec3d Method778(Entity entity, float f) {
        return new Vec3d(entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)f, entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)f, entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)f);
    }

    public static Vec3d Method968(Entity entity, float f) {
        return new Vec3d(entity.lastTickPosX, entity.lastTickPosY, entity.lastTickPosZ).add(Class30.Method2715(entity, f));
    }

    public static Vec3d Method2079(Entity entity, float f) {
        return Class30.Method968(entity, f).subtract(Globals.mc.getRenderManager().renderPosX, Globals.mc.getRenderManager().renderPosY, Globals.mc.getRenderManager().renderPosZ);
    }

    public static Vec3d Method2717(Entity entity, double d, double d2, double d3) {
        return new Vec3d((entity.posX - entity.lastTickPosX) * d, (entity.posY - entity.lastTickPosY) * d2, (entity.posZ - entity.lastTickPosZ) * d3);
    }

    public static Vec3d Method2715(Entity entity, float f) {
        return Class30.Method2717(entity, f, f, f);
    }

    public static boolean Method231(Entity entity) {
        if (entity instanceof EntityWolf && ((EntityWolf)entity).isAngry()) {
            return false;
        }
        if (!(entity instanceof EntityAgeable || entity instanceof EntityAmbientCreature || entity instanceof EntitySquid)) {
            return entity instanceof EntityIronGolem && ((EntityIronGolem)entity).getRevengeTarget() == null;
        }
        return true;
    }

    public static boolean Method2719(Entity entity, int n, boolean bl) {
        return Class30.Method2720(entity, n, bl).size() == 0;
    }

    public static boolean Method1093(boolean bl) {
        if (bl && Globals.mc.player != null) {
            Globals.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)Globals.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
        }
        return false;
    }

    public static boolean Method1513(Entity entity) {
        return Class30.Method2719(entity, 0, false);
    }

    public static BlockPos Method209(EntityPlayer entityPlayer) {
        return new BlockPos(Math.floor(entityPlayer.posX), Math.floor(entityPlayer.posY), Math.floor(entityPlayer.posZ));
    }

    public static List Method2720(Entity entity, int n, boolean bl) {
        return Class30.Method2721(entity.getPositionVector(), n, bl);
    }

    public static boolean Method2722(Entity entity) {
        if (entity instanceof EntityPigZombie) {
            if (((EntityPigZombie)entity).isArmsRaised() || ((EntityPigZombie)entity).isAngry()) {
                return true;
            }
        } else {
            if (entity instanceof EntityWolf) {
                return ((EntityWolf)entity).isAngry() && !Globals.mc.player.equals((Object)((EntityWolf)entity).getOwner());
            }
            if (entity instanceof EntityEnderman) {
                return ((EntityEnderman)entity).isScreaming();
            }
        }
        return Class30.Method2723(entity);
    }

    public static boolean Method2724(Entity entity) {
        return entity instanceof EntityPigZombie || entity instanceof EntityWolf || entity instanceof EntityEnderman;
    }

    public static boolean Method2725(Entity entity) {
        return entity instanceof EntityShulkerBullet || entity instanceof EntityFireball;
    }

    public static boolean Method2726(Entity entity) {
        return entity instanceof EntityBoat || entity instanceof EntityMinecart;
    }

    public static boolean Method2723(Entity entity) {
        return entity.isCreatureType(EnumCreatureType.MONSTER, false) && !Class30.Method2724(entity);
    }

    public static List Method2721(Vec3d vec3d, int n, boolean bl) {
        ArrayList<Vec3d> arrayList = new ArrayList<Vec3d>();
        for (Vec3d vec3d2 : Class30.Method2728(n, bl)) {
            if (vec3d2 instanceof Vec3d) {
                BlockPos blockPos = new BlockPos(vec3d).add(vec3d2.x, vec3d2.y, vec3d2.z);
                Block block = Globals.mc.world.getBlockState(blockPos).getBlock();
                if (!(block instanceof BlockAir) && !(block instanceof BlockLiquid) && !(block instanceof BlockTallGrass) && !(block instanceof BlockFire) && !(block instanceof BlockDeadBush) && !(block instanceof BlockSnow))
                    continue;
                arrayList.add(vec3d2);
            }
        }
        return arrayList;
    }

    public static boolean Method543(Entity entity) {
        return Class30.Method2729(new BlockPos(entity.posX, entity.posY, entity.posZ));
    }

    public static boolean Method2729(BlockPos blockPos) {
        return Class30.Method2730(blockPos) || Class30.Method2731(blockPos) || Class30.Method2732(blockPos);
    }

    public static boolean Method2731(BlockPos blockPos) {
        BlockPos[] blockPosArray;
        BlockPos[] blockPosArray2 = blockPosArray = new BlockPos[]{blockPos.north(), blockPos.south(), blockPos.east(), blockPos.west(), blockPos.down()};
        int n = blockPosArray.length;
        for (int i = 0; i < n; ++i) {
            BlockPos blockPos2 = blockPosArray2[i];
            IBlockState iBlockState = Globals.mc.world.getBlockState(blockPos2);
            if (iBlockState.getBlock() != Blocks.AIR && iBlockState.getBlock() == Blocks.OBSIDIAN) continue;
            return false;
        }
        return true;
    }

    public static boolean Method2730(BlockPos blockPos) {
        BlockPos[] blockPosArray;
        BlockPos[] blockPosArray2 = blockPosArray = new BlockPos[]{blockPos.north(), blockPos.south(), blockPos.east(), blockPos.west(), blockPos.down()};
        int n = blockPosArray.length;
        for (int i = 0; i < n; ++i) {
            BlockPos blockPos2 = blockPosArray2[i];
            IBlockState iBlockState = Globals.mc.world.getBlockState(blockPos2);
            if (iBlockState.getBlock() != Blocks.AIR && iBlockState.getBlock() == Blocks.BEDROCK) continue;
            return false;
        }
        return true;
    }

    public static boolean Method2732(BlockPos blockPos) {
        BlockPos[] blockPosArray;
        BlockPos[] blockPosArray2 = blockPosArray = new BlockPos[]{blockPos.north(), blockPos.south(), blockPos.east(), blockPos.west(), blockPos.down()};
        int n = blockPosArray.length;
        for (int i = 0; i < n; ++i) {
            BlockPos blockPos2 = blockPosArray2[i];
            IBlockState iBlockState = Globals.mc.world.getBlockState(blockPos2);
            if (iBlockState.getBlock() != Blocks.AIR && (iBlockState.getBlock() == Blocks.BEDROCK || iBlockState.getBlock() == Blocks.OBSIDIAN)) continue;
            return false;
        }
        return true;
    }

    public static List<Vec3d> Method2748(int n, boolean bl) {
        List<Vec3d> offsets = new ArrayList<>();
        offsets.add(new Vec3d(-1.0, n, 0.0));
        offsets.add(new Vec3d(1.0, n, 0.0));
        offsets.add(new Vec3d(0.0, n, -1.0));
        offsets.add(new Vec3d(0.0, n, 1.0));
        if (bl) {
            offsets.add(new Vec3d(0.0, n - 1, 0.0));
        }
        return offsets;
    }

    public static Vec3d[] Method2728(int n, boolean bl) {
        List list = Class30.Method2748(n, bl);
        Vec3d[] vec3dArray = new Vec3d[list.size()];
        return (Vec3d[]) list.toArray(vec3dArray);
    }

    public static boolean Method2753(Entity entity) {
        return entity instanceof EntityLivingBase;
    }

    public static boolean Method2754(Entity entity) {
        return Class30.Method2753(entity) && !entity.isDead && ((EntityLivingBase)entity).getHealth() > 0.0f;
    }

    public static boolean Method2755(Entity entity) {
        return !Class30.Method2754(entity);
    }

    public static float Method1454(Entity entity) {
        if (Class30.Method2753(entity)) {
            EntityLivingBase entityLivingBase = (EntityLivingBase)entity;
            return entityLivingBase.getHealth() + entityLivingBase.getAbsorptionAmount();
        }
        return 0.0f;
    }

    public static boolean Method2757(Entity entity) {
        return Globals.mc.world.rayTraceBlocks(new Vec3d(Globals.mc.player.posX, Globals.mc.player.posX + (double)Globals.mc.player.getEyeHeight(), Globals.mc.player.posZ), new Vec3d(entity.posX, entity.posY, entity.posZ), false, true, false) == null;
    }

    public static boolean Method749(Entity entity, double d) {
        return entity == null || Class30.Method2755(entity) || entity.equals((Object)Globals.mc.player) || entity instanceof EntityPlayer && Manager.Field223.Method233(entity.getName()) || Globals.mc.player.getDistanceSq(entity) > Class29.getDouble6(d);
    }

    public static boolean Method1471(Entity entity, double d) {
        return !Class30.Method749(entity, d);
    }

    public static boolean Method2758(EntityPlayer entityPlayer) {
        if (entityPlayer == null) {
            return false;
        }
        return entityPlayer.getHeldItemMainhand().getItem() instanceof ItemSword || entityPlayer.getHeldItemMainhand().getItem() instanceof ItemAxe;
    }

    public static boolean Method1832() {
        return (double)Globals.mc.player.moveForward != 0.0 || (double)Globals.mc.player.moveStrafing != 0.0;
    }

    public static EntityPlayer Method1810(double d) {
        EntityPlayer entityPlayer = null;
        for (EntityPlayer entityPlayer2 : Globals.mc.world.playerEntities) {
            if (entityPlayer2 instanceof EntityPlayer) {
                if (Class30.Method749((Entity) entityPlayer2, d)) continue;
                if (entityPlayer == null) {
                    entityPlayer = entityPlayer2;
                    continue;
                }
            }
            if (!(Globals.mc.player.getDistanceSq((Entity)entityPlayer2) < Globals.mc.player.getDistanceSq((Entity)entityPlayer))) continue;
            entityPlayer = entityPlayer2;
        }
        return entityPlayer;
    }

    public static boolean Method1834() {
        if (Globals.mc.player.fallDistance >= 3.0f) {
            return false;
        }
        boolean bl = false;
        AxisAlignedBB axisAlignedBB = Globals.mc.player.getRidingEntity() != null ? Globals.mc.player.getRidingEntity().getEntityBoundingBox() : Globals.mc.player.getEntityBoundingBox();
        int n = (int)axisAlignedBB.minY;
        for (int i = MathHelper.floor((double)axisAlignedBB.minX); i < MathHelper.floor((double)axisAlignedBB.maxX) + 1; ++i) {
            for (int j = MathHelper.floor((double)axisAlignedBB.minZ); j < MathHelper.floor((double)axisAlignedBB.maxZ) + 1; ++j) {
                Block block = Globals.mc.world.getBlockState(new BlockPos(i, n, j)).getBlock();
                if (block instanceof BlockAir) continue;
                if (!(block instanceof BlockLiquid)) {
                    return false;
                }
                bl = true;
            }
        }
        return bl;
    }

    public static boolean Method1835() {
        double d = Globals.mc.player.posY - 0.03;
        for (int i = MathHelper.floor((double)Globals.mc.player.posX); i < MathHelper.ceil((double)Globals.mc.player.posX); ++i) {
            for (int j = MathHelper.floor((double)Globals.mc.player.posZ); j < MathHelper.ceil((double)Globals.mc.player.posZ); ++j) {
                BlockPos blockPos = new BlockPos(i, MathHelper.floor((double)d), j);
                if (!(Globals.mc.world.getBlockState(blockPos).getBlock() instanceof BlockLiquid)) continue;
                return true;
            }
        }
        return false;
    }

    public static boolean Method51() {
        for (Entity entity : Globals.mc.world.loadedEntityList) {
            if (entity instanceof Entity) {
                if (!(entity instanceof EntityItem) && !(entity instanceof EntityXPOrb) && !(entity instanceof EntityArrow) || !(Globals.mc.player.getDistance(entity) <= 6.0f))
                    continue;
                return true;
            }
        }
        return false;
    }

    public static boolean Method2776(EntityPlayer entityPlayer) {
        return entityPlayer.getHeldItemMainhand().getItem() == Items.END_CRYSTAL || entityPlayer.getHeldItemOffhand().getItem() == Items.END_CRYSTAL;
    }

    public static EntityPlayer Method518() {
        if (Globals.mc.world.playerEntities.isEmpty()) {
            return null;
        }
        EntityPlayer entityPlayer = null;
        for (EntityPlayer entityPlayer2 : Globals.mc.world.playerEntities) {
            if (entityPlayer2 instanceof EntityPlayer) {
                if (entityPlayer2 == Globals.mc.player || Manager.Field223.Method711((Entity) entityPlayer2) || !Class354.Method1908((Entity) entityPlayer2) || entityPlayer2.getHealth() <= 0.0f || entityPlayer != null && Minecraft.getMinecraft().player.getDistance((Entity) entityPlayer2) > Minecraft.getMinecraft().player.getDistance((Entity) entityPlayer))
                    continue;
                entityPlayer = entityPlayer2;
            }
        }
        return entityPlayer;
    }
}
