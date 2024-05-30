package com.salamski.searchjeibyname;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SearchJeiByName.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class KeyRegister {

  @SubscribeEvent
  public static void registerKey(RegisterKeyMappingsEvent event) {
    event.register(Keybindings.INSTANCE.searchKey);
  }
}