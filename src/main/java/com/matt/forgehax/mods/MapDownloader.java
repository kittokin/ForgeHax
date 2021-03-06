package com.matt.forgehax.mods;

<<<<<<< HEAD
<<<<<<< HEAD
=======
import static com.matt.forgehax.Helper.getWorld;
>>>>>>> dfbf3717fcf8fca944a70062db7192a2f09f70d0
=======
import static com.matt.forgehax.Helper.getWorld;
>>>>>>> fr1kin-master
import static com.matt.forgehax.util.ImageUtils.createResizedCopy;

import com.matt.forgehax.Helper;
import com.matt.forgehax.asm.events.PacketEvent;
import com.matt.forgehax.asm.reflection.FastReflection;
import com.matt.forgehax.util.FileManager;
<<<<<<< HEAD
<<<<<<< HEAD
=======
import com.matt.forgehax.util.command.Setting;
>>>>>>> dfbf3717fcf8fca944a70062db7192a2f09f70d0
=======
import com.matt.forgehax.util.command.Setting;
>>>>>>> fr1kin-master
import com.matt.forgehax.util.mod.Category;
import com.matt.forgehax.util.mod.ToggleMod;
import com.matt.forgehax.util.mod.loader.RegisterMod;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;

import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.item.ItemMap;
import net.minecraft.network.play.server.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.MapData;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

/**
 * Created by Babbaj on 11/6/2017.
 */
@RegisterMod
public class MapDownloader extends ToggleMod {
  
  private File outputDir;
<<<<<<< HEAD
<<<<<<< HEAD
  
  public MapDownloader() {
    super(Category.MISC, "MapDownloader", false, "Saves map items as images");
  }
  
=======
  private int ticks;
  private List<String> mapList = new ArrayList<>();

  private final Setting<Integer> resolution =
      getCommandStub()
          .builders()
          .<Integer>newSettingBuilder()
          .name("resolution")
          .description("resolution of the downloaded image (default 128)")
          .defaultTo(128)
          .build();

  private final Setting<Boolean> onReceived =
      getCommandStub()
          .builders()
          .<Boolean>newSettingBuilder()
          .name("on-received")
          .description("download all maps as they are received")
          .defaultTo(false)
          .build();

  public MapDownloader() {
    super(Category.MISC, "MapDownloader", false, "Saves map items as images");
  }
=======
  private int ticks;
  private List<String> mapList = new ArrayList<>();

  private final Setting<Integer> resolution =
      getCommandStub()
          .builders()
          .<Integer>newSettingBuilder()
          .name("resolution")
          .description("resolution of the downloaded image (default 128)")
          .defaultTo(128)
          .build();

  private final Setting<Boolean> onReceived =
      getCommandStub()
          .builders()
          .<Boolean>newSettingBuilder()
          .name("on-received")
          .description("download all maps as they are received")
          .defaultTo(false)
          .build();

  public MapDownloader() {
    super(Category.MISC, "MapDownloader", false, "Saves map items as images");
  }
>>>>>>> fr1kin-master

  @Override
  protected void onDisabled() {
    if (onReceived.get()) {
      Helper.printInform(mapList.size() + " unique ID's downloaded.");
    }
    mapList.clear();
  }

  @SubscribeEvent
  public void onClientTick(TickEvent.ClientTickEvent event) {
    if (event.phase != TickEvent.Phase.START) return;
    ++ticks;
  }

  @SubscribeEvent
  public void onPacketReceived(PacketEvent.Incoming.Pre event) {
    if (event.getPacket() instanceof SPacketMaps && onReceived.get()) {

      // At a mapart wall many maps load in at the same time - we don't need to download all maps in render for every
      // Maps packet. Of course the best thing would be to only download the map that is getting received at that
      // moment but I don't know how to do that or if it is easily possible.

      if (ticks > 20) {
        downloadAllInRender(resolution.get());
        ticks = 0;
      }
    }
  }

<<<<<<< HEAD
>>>>>>> dfbf3717fcf8fca944a70062db7192a2f09f70d0
=======
>>>>>>> fr1kin-master
  private void saveImage(String fileName, BufferedImage image) {
    
    if (outputDir == null) {
      outputDir = FileManager.getInstance().getBaseResolve("maps").toFile();
    }
    if (!outputDir.exists()) {
      outputDir.mkdir();
    }
    try {
      File file = new File(outputDir, fileName + ".png");
      ImageIO.write(image, "png", file);
    } catch (Exception e) {
      Helper.printStackTrace(e);
    }
  }
<<<<<<< HEAD
<<<<<<< HEAD
  
  private void downloadMap(String fileName, Integer scaledRes) {
    if (MC.player == null || !(MC.player.getHeldItemMainhand().getItem() instanceof ItemMap)) {
      return;
    }
    
    ItemMap map = (ItemMap) MC.player.getHeldItemMainhand().getItem();
    MapData heldMapData = map.getMapData(MC.player.getHeldItemMainhand(), MC.world);
    
=======

  private void downloadAllInRender(Integer scaledRes) {
    if (getWorld() == null) return;

    getWorld().getLoadedEntityList().stream()
        .filter(EntityItemFrame.class::isInstance)
        .map(EntityItemFrame.class::cast)
        .filter(itemframe -> itemframe.getDisplayedItem().getItem() instanceof ItemMap)
        .forEach(itemframe -> {
          ItemMap map = (ItemMap) itemframe.getDisplayedItem().getItem();
          MapData data = map.getMapData(itemframe.getDisplayedItem(), getWorld());

          if (!mapList.contains(data.mapName)) {
            downloadMap(data, null, scaledRes);
            mapList.add(data.mapName);
          }
        });
  }

  private void downloadMap(MapData data, String fileName, Integer scaledRes) {
>>>>>>> fr1kin-master
    if (fileName == null) {
      fileName = data.mapName;
    }
<<<<<<< HEAD
    
    ResourceLocation location = findResourceLocation(heldMapData.mapName);
=======

  private void downloadAllInRender(Integer scaledRes) {
    if (getWorld() == null) return;

    getWorld().getLoadedEntityList().stream()
        .filter(EntityItemFrame.class::isInstance)
        .map(EntityItemFrame.class::cast)
        .filter(itemframe -> itemframe.getDisplayedItem().getItem() instanceof ItemMap)
        .forEach(itemframe -> {
          ItemMap map = (ItemMap) itemframe.getDisplayedItem().getItem();
          MapData data = map.getMapData(itemframe.getDisplayedItem(), getWorld());

          if (!mapList.contains(data.mapName)) {
            downloadMap(data, null, scaledRes);
            mapList.add(data.mapName);
          }
        });
  }

  private void downloadMap(MapData data, String fileName, Integer scaledRes) {
    if (fileName == null) {
      fileName = data.mapName;
    }

    ResourceLocation location = findResourceLocation(data.mapName);
>>>>>>> dfbf3717fcf8fca944a70062db7192a2f09f70d0
=======

    ResourceLocation location = findResourceLocation(data.mapName);
>>>>>>> fr1kin-master
    if (location == null) {
      Helper.printMessage("Failed to find ResourceLocation");
      return;
    }
    
    DynamicTexture texture = (DynamicTexture) MC.getTextureManager().getTexture(location);
    BufferedImage image = dynamicToImage(texture);
    if (scaledRes != null) {
      image = createResizedCopy(image, scaledRes, scaledRes, true);
    }
<<<<<<< HEAD
<<<<<<< HEAD
    
=======

>>>>>>> dfbf3717fcf8fca944a70062db7192a2f09f70d0
=======

>>>>>>> fr1kin-master
    saveImage(fileName, image);
  }
  
  private ResourceLocation findResourceLocation(String name) {
    Map<ResourceLocation, ITextureObject> mapTextureObjects =
        FastReflection.Fields.TextureManager_mapTextureObjects.get(MC.getTextureManager());
    
    return mapTextureObjects
        .keySet()
        .stream()
        .filter(k -> k.getResourcePath().contains(name))
        .findFirst()
        .orElse(null);
  }
  
  // TODO: generalize this
  private BufferedImage dynamicToImage(DynamicTexture texture) {
    int[] data = texture.getTextureData();
<<<<<<< HEAD
<<<<<<< HEAD
=======

>>>>>>> fr1kin-master
    if (data.length != 128 * 128) {
      return null;
    }
    
=======

>>>>>>> dfbf3717fcf8fca944a70062db7192a2f09f70d0
    BufferedImage image = new BufferedImage(128, 128, 2);
    
    image.setRGB(0, 0, image.getWidth(), image.getHeight(), data, 0, 128);
    return image;
  }
  
  @Override
  public void onLoad() {
    getCommandStub()
        .builders()
        .newCommandBuilder()
        .name("Download")
        .description("Download the held map as an image")
        .processor(
            data -> {
              if (MC.player == null || !(MC.player.getHeldItemMainhand().getItem() instanceof ItemMap)) {
                Helper.printError("You're not holding a map.");
                return;
              }

              ItemMap map = (ItemMap) MC.player.getHeldItemMainhand().getItem();
              MapData mapData = map.getMapData(MC.player.getHeldItemMainhand(), MC.world);

              String fileName = data.getArgument(0);
              Integer scaledRes = null;
              try {
                if (data.getArgument(1) != null) {
                  scaledRes = Integer.valueOf(data.getArgument(1));
                }
              } catch (NumberFormatException e) {
                Helper.printMessage("Failed to parse resolution");
              }
<<<<<<< HEAD
<<<<<<< HEAD
              
              downloadMap(fileName, scaledRes);
=======

              downloadMap(mapData, fileName, scaledRes);
>>>>>>> dfbf3717fcf8fca944a70062db7192a2f09f70d0
=======

              downloadMap(mapData, fileName, scaledRes);
>>>>>>> fr1kin-master
            })
        .build();
  }
}
