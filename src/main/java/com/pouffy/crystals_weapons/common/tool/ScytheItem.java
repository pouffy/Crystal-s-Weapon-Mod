package com.pouffy.crystals_weapons.common.tool;

import com.pouffy.crystals_weapons.common.item.CrystalsWeaponsItemSettings;
import com.pouffy.crystals_weapons.common.tag.ModItemTags;
import com.pouffy.crystals_weapons.registry.EnchantmentsRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CarvedPumpkinBlock;
import net.minecraft.block.Material;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.Set;

public class ScytheItem extends MiningToolItem {
    public static final Set<Enchantment> ALLOWED_ENCHANTMENTS = Set.of(Enchantments.KNOCKBACK, Enchantments.FIRE_ASPECT, Enchantments.LOOTING,
            Enchantments.MENDING, EnchantmentsRegistry.REAPING.get());
    private static final Set<Material> EFFECTIVE_ON_MATERIAL = Set.of(Material.CACTUS, Material.COBWEB, Material.UNDERWATER_PLANT);

    public ScytheItem(ToolMaterial material) {
        super(.5f, -1.8f, material, ModItemTags.REAPING_WORTHY, new CrystalsWeaponsItemSettings());
    }
    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        Material material = state.getMaterial();
        if (state.isIn(ModItemTags.REAPING_WORTHY) || EFFECTIVE_ON_MATERIAL.contains(material)) {
            return this.miningSpeed;
        } else {
            return super.getMiningSpeedMultiplier(stack, state);
        }
    }

    @Override
    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
        return !miner.isCreative();
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.damage(1, attacker, user -> user.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));

        return true;
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }

    @Override
    public int getEnchantability() {
        return 22; // check ToolMaterials for reference
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        ItemStack tool = context.getStack();
        BlockPos pos = context.getBlockPos();
        BlockState state = world.getBlockState(pos);
        Direction facing = context.getSide();

        if (state.getBlock() == Blocks.PUMPKIN && tool.isIn(ModItemTags.SCYTHES)) {
            PlayerEntity player = context.getPlayer();
            if (player != null && !world.isClient()) {
                Direction direction = facing.getAxis() == Direction.Axis.Y ? player.getHorizontalFacing().getOpposite() : facing;
                world.playSound(null, pos, SoundEvents.BLOCK_PUMPKIN_CARVE, SoundCategory.BLOCKS, 1.f, 1.f);
                world.setBlockState(pos, Blocks.CARVED_PUMPKIN.getDefaultState().with(CarvedPumpkinBlock.FACING, direction), 11);
                ItemEntity itemEntity = new ItemEntity(world,
                        pos.getX() + .5d + direction.getOffsetX() * .65d,
                        pos.getY() + .1d, pos.getZ() + .5d + direction.getOffsetZ() * .65d,
                        new ItemStack(Items.PUMPKIN_SEEDS, 4));
                itemEntity.setVelocity(
                        .05d * direction.getOffsetX() + world.getRandom().nextDouble() * .02d,
                        .05d,
                        .05d * direction.getOffsetZ() + world.getRandom().nextDouble() * 0.02D);
                world.spawnEntity(itemEntity);
                tool.damage(1, player, playerIn -> playerIn.sendToolBreakStatus(context.getHand()));
            }
            return ActionResult.success(world.isClient());
        } else {
            return ActionResult.PASS;
        }
    }
}
