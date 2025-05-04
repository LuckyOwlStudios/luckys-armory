package net.luckystudios.items.armor;

import com.google.common.base.Suppliers;
import net.luckystudios.LuckysArmory;
import net.luckystudios.components.ModDataComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Supplier;

public abstract class HeavyArmorItem extends ArmorItem implements DyedArmor {
    private static final ResourceLocation BOOTS_SPEED_ID = ResourceLocation.withDefaultNamespace("armor.slowness.boots");
    private static final ResourceLocation LEGGINGS_SPEED_ID = ResourceLocation.withDefaultNamespace("armor.slowness.leggings");
    private static final ResourceLocation CHESTPLATE_SPEED_ID = ResourceLocation.withDefaultNamespace("armor.slowness.chestplate");
    private static final ResourceLocation HELMET_SPEED_ID = ResourceLocation.withDefaultNamespace("armor.slowness.helmet");

    protected static String TEXTURE_PATH = "textures/entity/equipment/humanoid/";
    protected static final ResourceLocation HEAVY_ARMOR_DYE_OVERLAY = ResourceLocation.fromNamespaceAndPath(LuckysArmory.MOD_ID, TEXTURE_PATH + "heavy_armor_dye_overlay.png");
    private final Supplier<ItemAttributeModifiers> heavyModifiers;
    public static float getFullnessDisplay(ItemStack stack) {
        return Boolean.TRUE.equals(stack.get(ModDataComponents.OPENED)) ? 1.0F : 0.0F;
    }

    public HeavyArmorItem(Holder<ArmorMaterial> material, Type type, Item.Properties properties) {
        super(material, type, properties);

        this.heavyModifiers = Suppliers.memoize(() -> {
            int defense = material.value().getDefense(type);
            float toughness = material.value().toughness();
            float knockbackResistance = material.value().knockbackResistance();

            EquipmentSlotGroup slotGroup = EquipmentSlotGroup.bySlot(type.getSlot());
            ResourceLocation armorModifierID = ResourceLocation.withDefaultNamespace("armor." + type.getName());

            ItemAttributeModifiers.Builder builder = ItemAttributeModifiers.builder();

            builder.add(Attributes.ARMOR, new AttributeModifier(armorModifierID, defense, AttributeModifier.Operation.ADD_VALUE), slotGroup);
            builder.add(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(armorModifierID, toughness, AttributeModifier.Operation.ADD_VALUE), slotGroup);

            if (knockbackResistance > 0.0F) {
                builder.add(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(armorModifierID, knockbackResistance, AttributeModifier.Operation.ADD_VALUE), slotGroup);
            }

            // Slowness: 5% speed reduction
            ResourceLocation movementSpeedID = switch (type.getSlot()) {
                case FEET -> BOOTS_SPEED_ID;
                case LEGS -> LEGGINGS_SPEED_ID;
                case CHEST -> CHESTPLATE_SPEED_ID;
                case HEAD -> HELMET_SPEED_ID;
                default -> throw new IllegalStateException("Unexpected slot: " + type.getSlot());
            };

            builder.add(Attributes.MOVEMENT_SPEED, new AttributeModifier(movementSpeedID, -0.05, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), slotGroup);

            return builder.build();
        });
    }

    @Override
    public DyedItemColor getDefaultDyeColor() {
        return null;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context, @NotNull List<Component> tooltipComponents, @NotNull TooltipFlag tooltipFlag) {
        if (isSameColorAsDefault(stack)) tooltipComponents.add(Component.translatable("tooltip.luckysarmory.dyeable").withStyle(ChatFormatting.GRAY));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }

    @Override
    public ItemAttributeModifiers getDefaultAttributeModifiers() {
        return heavyModifiers.get();
    }

    protected boolean isSameColorAsDefault(ItemStack stack) {
        return stack.has(DataComponents.DYED_COLOR) && stack.get(DataComponents.DYED_COLOR) != getDefaultDyeColor();
    }
}
