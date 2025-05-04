package net.luckystudios.items.armor.custom;

import net.luckystudios.LuckysArmory;
import net.luckystudios.items.armor.HeavyArmorItem;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DyedItemColor;
import org.jetbrains.annotations.Nullable;

public class HeavyGoldenArmorItem extends HeavyArmorItem {

    public HeavyGoldenArmorItem(Holder<ArmorMaterial> material, Type type, Properties properties) {
        super(material, type, properties);
    }

    @Override
    public DyedItemColor getDefaultDyeColor() {
        return new DyedItemColor(DyeColor.RED.getTextureDiffuseColor(), false);
    }

    @Override
    public @Nullable ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean innerModel) {
        if (layer.dyeable()) {
            if (slot == EquipmentSlot.HEAD) return ResourceLocation.fromNamespaceAndPath(LuckysArmory.MOD_ID, "textures/entity/equipment/humanoid/heavy_golden_helmet_dye_overlay.png");
            if (slot == EquipmentSlot.CHEST) return ResourceLocation.fromNamespaceAndPath(LuckysArmory.MOD_ID, "textures/entity/equipment/humanoid/heavy_chestplate_dye_overlay.png");
            if (slot == EquipmentSlot.LEGS) return ResourceLocation.fromNamespaceAndPath(LuckysArmory.MOD_ID, "textures/entity/equipment/humanoid/heavy_leggings_dye_overlay.png");
            if (slot == EquipmentSlot.FEET) return ResourceLocation.fromNamespaceAndPath(LuckysArmory.MOD_ID, "textures/entity/equipment/humanoid/heavy_boots_dye_overlay.png");
        }
        if (slot == EquipmentSlot.HEAD) return ResourceLocation.fromNamespaceAndPath(LuckysArmory.MOD_ID, "textures/entity/equipment/humanoid/heavy_golden_helmet.png");
        if (slot == EquipmentSlot.CHEST) return ResourceLocation.fromNamespaceAndPath(LuckysArmory.MOD_ID, "textures/entity/equipment/humanoid/heavy_golden_chestplate.png");
        if (slot == EquipmentSlot.LEGS) return ResourceLocation.fromNamespaceAndPath(LuckysArmory.MOD_ID, "textures/entity/equipment/humanoid/heavy_golden_leggings.png");
        return ResourceLocation.fromNamespaceAndPath(LuckysArmory.MOD_ID, "textures/entity/equipment/humanoid/heavy_golden_boots.png");
    }
}
