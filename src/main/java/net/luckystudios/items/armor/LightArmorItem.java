package net.luckystudios.items.armor;

import com.google.common.base.Suppliers;
import net.luckystudios.LuckysArmory;
import net.luckystudios.components.ModDataComponents;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemAttributeModifiers;

import java.util.List;
import java.util.function.Supplier;

public class LightArmorItem extends ArmorItem {
    private static final ResourceLocation BOOTS_SPEED_ID = ResourceLocation.withDefaultNamespace("armor.slowness.boots");
    private static final ResourceLocation LEGGINGS_SPEED_ID = ResourceLocation.withDefaultNamespace("armor.slowness.leggings");
    private static final ResourceLocation CHESTPLATE_SPEED_ID = ResourceLocation.withDefaultNamespace("armor.slowness.chestplate");
    private static final ResourceLocation HELMET_SPEED_ID = ResourceLocation.withDefaultNamespace("armor.slowness.helmet");

    protected static String TEXTURE_PATH = "textures/entity/equipment/humanoid/";
    protected static final ResourceLocation HEAVY_ARMOR_DYE_OVERLAY = ResourceLocation.fromNamespaceAndPath(LuckysArmory.MOD_ID, TEXTURE_PATH + "heavy_armor_dye_overlay.png");

    private final Supplier<ItemAttributeModifiers> defaultModifiers;

    public static float getFullnessDisplay(ItemStack stack) {
        return Boolean.TRUE.equals(stack.get(ModDataComponents.OPENED)) ? 1.0F : 0.0F;
    }

    public LightArmorItem(Holder<ArmorMaterial> material, Type type, Properties properties) {
        super(material, type, properties);
        this.defaultModifiers = Suppliers.memoize(() -> {
            int defense = material.value().getDefense(type);
            float toughness = material.value().toughness();
            float knockbackResistance = material.value().knockbackResistance();

            EquipmentSlotGroup slotGroup = EquipmentSlotGroup.bySlot(type.getSlot());
            ResourceLocation armorModifierID = ResourceLocation.withDefaultNamespace("armor." + type.getName());

            ItemAttributeModifiers.Builder builder = ItemAttributeModifiers.builder();

            // **Default Armor Attributes**
            builder.add(Attributes.ARMOR, new AttributeModifier(armorModifierID, defense, AttributeModifier.Operation.ADD_VALUE), slotGroup);
            builder.add(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(armorModifierID, toughness, AttributeModifier.Operation.ADD_VALUE), slotGroup);

            if (knockbackResistance > 0.0F) {
                builder.add(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(armorModifierID, knockbackResistance, AttributeModifier.Operation.ADD_VALUE), slotGroup);
            }

            // **Slowness Effect - Different ID per armor piece**
            ResourceLocation attackSpeedID;
            double speedBoost = 0.05; // Each piece reduces speed by 5%

            switch (type.getSlot()) {
                case FEET -> attackSpeedID = BOOTS_SPEED_ID;
                case LEGS -> attackSpeedID = LEGGINGS_SPEED_ID;
                case CHEST -> attackSpeedID = CHESTPLATE_SPEED_ID;
                case HEAD -> attackSpeedID = HELMET_SPEED_ID;
                default -> throw new IllegalStateException("Unexpected slot: " + type.getSlot());
            }

            builder.add(Attributes.ATTACK_SPEED, new AttributeModifier(attackSpeedID, speedBoost, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), slotGroup);

            return builder.build();
        });
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if (type.getSlot() == EquipmentSlot.HEAD) {
            if (Boolean.TRUE.equals(stack.get(ModDataComponents.OPENED))) {
                tooltipComponents.add(Component.translatable("tooltip.luckysarmory.opened"));
            } else {
                tooltipComponents.add(Component.translatable("tooltip.luckysarmory.closed"));
            }
        }
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
