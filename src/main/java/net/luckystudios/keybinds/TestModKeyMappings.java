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
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import org.lwjgl.glfw.GLFW;

import java.util.List;
import java.util.function.Supplier;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class TestModKeyMappings {
    public static final Supplier<KeyMapping> TOGGLE_HELMET = Suppliers.memoize( () -> new KeyMapping(
            "key.luckysarmory.helmet_toggle",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_H,
            "key.categories.gameplay"
    ));

    public static final Supplier<List<Item>> getHeavyHelmets = Suppliers.memoize(() -> List.of(
            ModItems.HEAVY_IRON_HELMET.get(),
            ModItems.HEAVY_GOLDEN_HELMET.get(),
            ModItems.HEAVY_DIAMOND_HELMET.get(),
            ModItems.HEAVY_NETHERITE_HELMET.get()
    ));

    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(TOGGLE_HELMET.get());
    }

    public static String getKeyName(Supplier<KeyMapping> toggleHelmet) {
        KeyMapping keyMapping = toggleHelmet.get();
        return keyMapping.getKey().getDisplayName().getString();
    }

    @EventBusSubscriber({Dist.CLIENT})
    public static class KeyEventListener {
        @SubscribeEvent
        public static void onClientTick(ClientTickEvent.Post event) {
            if (Minecraft.getInstance().screen == null) {
                while (TOGGLE_HELMET.get().consumeClick()) {
                    Level level = Minecraft.getInstance().level;
                    Player player = Minecraft.getInstance().player;
                    ItemStack helmet = player.getItemBySlot(EquipmentSlot.HEAD);
                    if (getHeavyHelmets.get().contains(helmet.getItem())) {
                        PacketDistributor.sendToServer(new HelmetTogglePacket(0, 0));
                        HelmetTogglePacket.pressAction(player, helmet, 0, 0);
                    }
                }
            }
        }
    }

}
