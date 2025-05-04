package net.luckystudios.items.armor.custom;

import net.luckystudios.LuckysArmory;
import net.luckystudios.items.armor.HeavyArmorItem;
import net.luckystudios.items.armor.LightArmorItem;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class LightGoldenArmorItem extends LightArmorItem {

    public LightGoldenArmorItem(Holder<ArmorMaterial> material, Type type, Properties properties) {
        super(material, type, properties);
    }

    @Override
    public @Nullable ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean innerModel) {
        return ResourceLocation.fromNamespaceAndPath(LuckysArmory.MOD_ID, "textures/entity/equipment/humanoid/light_golden_armor.png");
    }
}
