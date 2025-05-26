package net.luckystudios.keybinds;

import com.google.common.base.Suppliers;
import com.mojang.blaze3d.platform.InputConstants;
import net.luckystudios.items.ModItems;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.glfw.GLFW;

import java.util.List;
import java.util.function.Supplier;

public class ModKeybinds {
    public static List<Item> getHeavyHelmets = List.of(
            ModItems.HEAVY_IRON_HELMET.get(),
            ModItems.HEAVY_GOLDEN_HELMET.get(),
            ModItems.HEAVY_DIAMOND_HELMET.get(),
            ModItems.HEAVY_NETHERITE_HELMET.get()
    );

    public static final Supplier<KeyMapping> TOGGLE_HELMET = Suppliers.memoize( () -> new KeyMapping(
            "key.luckysarmory.helmet_toggle",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_H,
            "key.categories.gameplay"
    ));

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        while (TOGGLE_HELMET.get().consumeClick()) {
            Player player = Minecraft.getInstance().player;
            if (player == null) return;
            var helmet = player.getItemBySlot(EquipmentSlot.HEAD);
            if (getHeavyHelmets.contains(helmet.getItem())) {
                PacketDistributor.sendToServer(new HelmetTogglePacket(0,0));
                HelmetTogglePacket.pressAction(player, helmet, 0, 0);
            }
        }
    }

    public static @NotNull String getKeyName(@NotNull Supplier<KeyMapping> keyMapping) {
        return keyMapping.get().getKey().getDisplayName().getString();
    }
}