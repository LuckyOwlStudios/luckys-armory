package net.luckystudios.items.armor.custom;

import net.luckystudios.LuckysArmory;
import net.luckystudios.components.ModDataComponents;
import net.luckystudios.items.armor.HeavyArmorItem;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class HeavyNetheriteArmorItem extends HeavyArmorItem {

    public HeavyNetheriteArmorItem(Holder<ArmorMaterial> material, Type type, Properties properties) {
        super(material, type, properties);
    }

    @Override
    public DyedItemColor getDefaultDyeColor() {
        return new DyedItemColor(DyeColor.BLACK.getTextureDiffuseColor(), false);
    }

    @Override
    public @Nullable ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean innerModel) {
        boolean isOpened = Boolean.TRUE.equals(stack.get(ModDataComponents.OPENED));
        if (layer.dyeable()) {
            if (slot == EquipmentSlot.HEAD) {
                return isOpened ? ResourceLocation.fromNamespaceAndPath(LuckysArmory.MOD_ID, "textures/entity/equipment/humanoid/heavy_netherite_helmet_opened_dye_overlay.png") : ResourceLocation.fromNamespaceAndPath(LuckysArmory.MOD_ID, "textures/entity/equipment/humanoid/heavy_netherite_helmet_dye_overlay.png");
            }
            if (slot == EquipmentSlot.CHEST) return ResourceLocation.fromNamespaceAndPath(LuckysArmory.MOD_ID, "textures/entity/equipment/humanoid/heavy_chestplate_dye_overlay.png");
            if (slot == EquipmentSlot.LEGS) return ResourceLocation.fromNamespaceAndPath(LuckysArmory.MOD_ID, "textures/entity/equipment/humanoid/heavy_leggings_dye_overlay.png");
            if (slot == EquipmentSlot.FEET) return ResourceLocation.fromNamespaceAndPath(LuckysArmory.MOD_ID, "textures/entity/equipment/humanoid/heavy_boots_dye_overlay.png");
        }
        if (slot == EquipmentSlot.HEAD) {
            return isOpened ? ResourceLocation.fromNamespaceAndPath(LuckysArmory.MOD_ID, "textures/entity/equipment/humanoid/heavy_netherite_helmet_opened.png") : ResourceLocation.fromNamespaceAndPath(LuckysArmory.MOD_ID, "textures/entity/equipment/humanoid/heavy_netherite_helmet.png");
        }
        if (slot == EquipmentSlot.CHEST) return ResourceLocation.fromNamespaceAndPath(LuckysArmory.MOD_ID, "textures/entity/equipment/humanoid/heavy_netherite_chestplate.png");
        if (slot == EquipmentSlot.LEGS) return ResourceLocation.fromNamespaceAndPath(LuckysArmory.MOD_ID, "textures/entity/equipment/humanoid/heavy_netherite_leggings.png");
        return ResourceLocation.fromNamespaceAndPath(LuckysArmory.MOD_ID, "textures/entity/equipment/humanoid/heavy_netherite_boots.png");
    }
}
