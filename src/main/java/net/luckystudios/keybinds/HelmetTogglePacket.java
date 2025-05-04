package net.luckystudios.keybinds;

import com.google.common.collect.ImmutableList;
import net.luckystudios.LuckysArmory;
import net.luckystudios.components.ModDataComponents;
import net.luckystudios.items.armor.HeavyArmorItem;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.List;

public record HelmetTogglePacket(int eventType, int pressedms) implements CustomPacketPayload {

    public static final Type<HelmetTogglePacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(LuckysArmory.MOD_ID, "key_helmet_toggle"));

    public static final StreamCodec<RegistryFriendlyByteBuf, HelmetTogglePacket> STREAM_CODEC = StreamCodec.of((RegistryFriendlyByteBuf buffer, HelmetTogglePacket message) -> {
        buffer.writeInt(message.eventType);
        buffer.writeInt(message.pressedms);
    }, (RegistryFriendlyByteBuf buffer) -> new HelmetTogglePacket(buffer.readInt(), buffer.readInt()));


    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handleData(final HelmetTogglePacket message, final IPayloadContext context) {
        if (context.flow() == PacketFlow.SERVERBOUND) {
            context.enqueueWork(() -> {
                pressAction(context.player(), context.player().getItemBySlot(EquipmentSlot.HEAD), message.eventType, message.pressedms);
            }).exceptionally(e -> {
                context.connection().disconnect(Component.literal(e.getMessage()));
                return null;
            });
        }
    }


    public static void pressAction(Player player, ItemStack helmet, int type, int pressedms) {
        Level world = player.level();

        // Security measure to prevent arbitrary chunk generation
        if (!world.hasChunkAt(player.blockPosition())) return;
        if (type != 0) return;

        // Check if the helmet is "opened"
        boolean opened = Boolean.TRUE.equals(helmet.get(ModDataComponents.OPENED));

        // Copy stack and toggle the "opened" state
        ItemStack newHelmet = helmet.copy();
        newHelmet.set(ModDataComponents.OPENED, !opened);

        // Get the ArmorItem and its material
        if (newHelmet.getItem() instanceof HeavyArmorItem heavyArmorItem) {
            // Retrieve the defense value for this helmet
            Holder<ArmorMaterial> armorMaterial = heavyArmorItem.getMaterial();
            ArmorMaterial material = armorMaterial.value();
            int defaultDefense = material.getDefense(ArmorItem.Type.HELMET);  // Default defense value

            // Adjust the defense based on whether the helmet is opened or closed
            int modifiedDefense = opened ? defaultDefense : defaultDefense / 2;

            List<ItemAttributeModifiers.Entry> oldEntries = newHelmet.getAttributeModifiers().modifiers();
            ImmutableList.Builder<ItemAttributeModifiers.Entry> builder = ImmutableList.builder();

// Your new armor modifier
            ItemAttributeModifiers.Entry newArmorEntry = new ItemAttributeModifiers.Entry(
                    Attributes.ARMOR,
                    new AttributeModifier(ResourceLocation.withDefaultNamespace("armor.helmet"), modifiedDefense, AttributeModifier.Operation.ADD_VALUE),
                    EquipmentSlotGroup.HEAD
            );

// Add the new armor modifier FIRST
            builder.add(newArmorEntry);

// Add the rest of the modifiers, excluding any existing armor modifier with the same ID
            for (ItemAttributeModifiers.Entry entry : oldEntries) {
                if (!entry.matches(Attributes.ARMOR, newArmorEntry.modifier().id())) {
                    builder.add(entry);
                }
            }

// Apply new modifiers with correct order
            newHelmet.set(DataComponents.ATTRIBUTE_MODIFIERS, new ItemAttributeModifiers(builder.build(), true));


            // Update the helmet's NBT with the new modifiers
            // Equip the modified helmet
            player.setItemSlot(EquipmentSlot.HEAD, newHelmet);
            // Feedback message to the player
            player.swing(InteractionHand.OFF_HAND);
        }
    }
}
