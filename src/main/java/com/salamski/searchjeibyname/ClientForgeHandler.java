package com.salamski.searchjeibyname;

import com.mojang.blaze3d.platform.InputConstants;

import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.inventory.Slot;
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
    if (Keybindings.INSTANCE.searchKey.isActiveAndMatches(InputConstants.getKey(event.getKeyCode(), -1))) {
      ItemStack hoveredItem = getHoveredItem();
      if (!hoveredItem.isEmpty()) {
        sendItemToJeiSearch(hoveredItem.getHoverName().getString());
      }
    }
  }

  private static ItemStack getHoveredItem() {
    Minecraft mc = Minecraft.getInstance();
    Screen screen = mc.screen;
    if (screen instanceof AbstractContainerScreen) {
      AbstractContainerScreen<?> container = (AbstractContainerScreen<?>) screen;
      Slot slotUnderMouse = container.getSlotUnderMouse();

      if (slotUnderMouse != null && slotUnderMouse.hasItem()) {
        return slotUnderMouse.getItem();
      }
    }
    return ItemStack.EMPTY;
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
