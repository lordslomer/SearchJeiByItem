package com.salamski.searchjeibyname;

import com.mojang.blaze3d.platform.InputConstants;

import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ScreenEvent.KeyPressed.Pre;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SearchJeiByName.MODID, value = Dist.CLIENT)
public class ClientForgeHandler {
  private static IJeiRuntime jeiRuntime;

  @SubscribeEvent
  public static void onKeyPress(Pre event) {
    Minecraft mc = Minecraft.getInstance();
    if (mc.screen instanceof InventoryScreen) {
      if (Keybindings.INSTANCE.searchKey.isActiveAndMatches(InputConstants.getKey(event.getKeyCode(), -1))) {
        ItemStack hoveredItem = getHoveredItem();
        if (!hoveredItem.isEmpty()) {
          sendItemToJeiSearch(hoveredItem.getHoverName().getString());
        }
      }
    }
  }

  private static ItemStack getHoveredItem() {
    Minecraft mc = Minecraft.getInstance();
    InventoryScreen screen = (InventoryScreen) mc.screen;
    return screen.getSlotUnderMouse() != null ? screen.getSlotUnderMouse().getItem() : ItemStack.EMPTY;
  }

  public static void setJeiRuntime(IJeiRuntime runtime) {
    jeiRuntime = runtime;
  }

  private static void sendItemToJeiSearch(String itemName) {
    if (jeiRuntime != null) {
      jeiRuntime.getIngredientFilter().setFilterText(itemName);
    }
  }
}
