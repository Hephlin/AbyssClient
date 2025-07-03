package me.ciruu.abyss;

import com.google.common.base.Predicate;
import com.google.common.base.Strings;
import com.mojang.realmsclient.gui.ChatFormatting;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.AccessException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;

import me.ciruu.abyss.enums.Class53;
import me.ciruu.abyss.events.network.EventNetworkPrePacketEvent;
import me.ciruu.abyss.events.player.EventPlayerUpdate;
import me.ciruu.abyss.managers.ModuleManager;
import me.ciruu.abyss.modules.client.Client;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listenable;
import me.zero.alpine.listener.Listener;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.server.SPacketPlayerListItem;
import net.minecraft.network.play.server.SPacketTimeUpdate;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class Class489 implements Listenable {
    public static Class489 Field3420 = new Class489();
    @EventHandler
    private final Listener<EventNetworkPrePacketEvent> Field3421 = new Listener<>(Class489::Method4093);

    public Class489() {
        Field3420 = this;
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SuppressWarnings("deprecation")
    private static void Method4093(EventNetworkPrePacketEvent eventNetworkPrePacketEvent) {
    }

    private static void Method4118(SPacketPlayerListItem packet, Object entryObject) {
    }



    @SubscribeEvent
    public void Method4095(TickEvent.ClientTickEvent clientTickEvent) {
        try {
            if (Globals.mc.player == null) return;
            if (clientTickEvent.isCanceled()) return;
        } catch (Exception e) {
            e.getSuppressed();
        }
        System.out.println("abyss: getting ticks!");
        AbyssMod.EVENT_BUS.post(new Class26());
    }

    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (Globals.mc.player == null || event.isCanceled()) return;
        AbyssMod.EVENT_BUS.post(new Class26());
    }

    private void listLoggedPackets() {
        try (Stream<Path> stream = Files.walk(Paths.get("Abyss/PacketLogger/"))) {
            stream.filter(Files::isRegularFile).forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SubscribeEvent
    public void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
        if (Globals.mc.world != null &&
                event.getEntity().getEntityWorld().isRemote &&
                event.getEntityLiving().equals(Globals.mc.player)) {
            Manager.Field298.Method2907();
        }
    }

    @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (!Keyboard.getEventKeyState()) return;
        int key = Keyboard.getEventKey();
        if (key == 0) return;

        ModuleManager.getString1(Keyboard.getKeyName(key));
        AbyssMod.EVENT_BUS.post(new Class350(key));
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public void onRenderOverlay(RenderGameOverlayEvent.Text event) {
        if (event.getType() != RenderGameOverlayEvent.ElementType.TEXT) return;
        ScaledResolution res = new ScaledResolution(Globals.mc);
        Class35 class35 = new Class35(event.getPartialTicks(), res);
        AbyssMod.EVENT_BUS.post(class35);
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
    }

    @SubscribeEvent
    public void onMouseInput(InputEvent.MouseInputEvent event) {
        if (Mouse.getEventButtonState()) {
            AbyssMod.EVENT_BUS.post(event);
        }
        int button = Mouse.getEventButton();
        if (button != -1 && Mouse.isButtonDown(button)) {
            ModuleManager.getString1(Mouse.getButtonName(button));
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onClientChat(ClientChatEvent event) {
        String prefix = ((Client) Manager.moduleManager.getModuleByClass(Client.class)).cmdprefix.getValue().toString().trim();
        if (!event.getMessage().startsWith(prefix)) return;

        event.setCanceled(true);
        Globals.mc.ingameGUI.getChatGUI().addToSentMessages(event.getMessage());

        try {
            String commandBody = event.getMessage().substring(prefix.length());
            String[] args = commandBody.split(" ");
            if (args.length == 0) return;

            List<?> list = Manager.Field1639.addSetting34(args[0]);
            ((Class163) list.get(0)).addSetting8(commandBody);
        } catch (Exception e) {
            e.printStackTrace();
            if ("Index: 0, Size: 0".equalsIgnoreCase(e.getMessage())) {
                Class547.printChatMessage(ChatFormatting.RED + "Error: Incorrect command. Type *Help for a list of commands");
            }
            Class547.printChatMessage(ChatFormatting.DARK_RED + "Error: " + e.getMessage());
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onPlayerListUpdate(EntityJoinWorldEvent event) {
        AbyssMod.EVENT_BUS.post(new Class27(event.getEntity(), event.getWorld()));
    }

    private static void handlePlayerUpdate(EventPlayerUpdate event) {
        Manager.Field1643.Method465();
    }

    private static void handleNetworkPacket(EventNetworkPrePacketEvent event) {
        if (event.getPacket() instanceof SPacketTimeUpdate) {
            Manager.Field1645.addSetting56();
        } else if (event.getPacket() instanceof SPacketPlayerListItem && Globals.mc.player != null && Globals.mc.world != null) {
            SPacketPlayerListItem packet = (SPacketPlayerListItem) event.getPacket();
            if (packet.getAction() != SPacketPlayerListItem.Action.ADD_PLAYER &&
                    packet.getAction() != SPacketPlayerListItem.Action.REMOVE_PLAYER) {
            }
        }
    }

    @SuppressWarnings("deprecation")
    private static boolean isValidPlayerData(Minecraft data) {
        if (data == null || Globals.mc.player.getGameProfile() == null) {
            return false;
        }
        String name = Globals.mc.player.getGameProfile().getName();
        UUID id = Globals.mc.player.getGameProfile().getId();
        return (name != null && !name.isEmpty()) || id != null;
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void Method4107(EntityJoinWorldEvent entityJoinWorldEvent) {
        AbyssMod.EVENT_BUS.post(new Class27(entityJoinWorldEvent.getEntity(), entityJoinWorldEvent.getWorld()));
    }

    @SubscribeEvent
    public void Method4108(LivingAttackEvent livingAttackEvent) {
        AbyssMod.EVENT_BUS.post(livingAttackEvent);
    }

    @SubscribeEvent
    public void Method4109(InputUpdateEvent inputUpdateEvent) {
        AbyssMod.EVENT_BUS.post(inputUpdateEvent);
    }

    @SubscribeEvent
    public void Method4110(AttackEntityEvent attackEntityEvent) {
        AbyssMod.EVENT_BUS.post(attackEntityEvent);
    }

    @SubscribeEvent
    public void Method4111(RenderPlayerEvent.Pre pre) {
        AbyssMod.EVENT_BUS.post(pre);
    }

    @SubscribeEvent
    public void Method4112(ChunkEvent.Load load) {
        AbyssMod.EVENT_BUS.post(load);
    }

    @SubscribeEvent
    public void Method4113(ChunkEvent.Unload unload) {
        AbyssMod.EVENT_BUS.post(unload);
    }

    @SubscribeEvent
    public void Method4114(InputEvent.MouseInputEvent mouseInputEvent) {
        AbyssMod.EVENT_BUS.post(mouseInputEvent);
    }

    @SubscribeEvent
    public void Method4115(ProjectileImpactEvent.Throwable throwable) {
        AbyssMod.EVENT_BUS.post(throwable);
    }

    private static void Method4094(EventPlayerUpdate eventPlayerUpdate) {
        Manager.Field1643.Method465();
    }

    private static void Method4118(SPacketPlayerListItem sPacketPlayerListItem, SPacketPlayerListItem addPlayerData) {
        UUID uUID = null;
        switch (sPacketPlayerListItem.getAction()) {
            case ADD_PLAYER:
                String string = null;
                AbyssMod.EVENT_BUS.post(new Class179(Class53.PRE, uUID, string));
                break;
            case REMOVE_PLAYER:
                EntityPlayer entityPlayer = Globals.mc.world.getPlayerEntityByUUID(uUID);
                if (entityPlayer != null) {
                    string = entityPlayer.getName();
                    AbyssMod.EVENT_BUS.post(new Class179(Class53.PERI, entityPlayer, uUID, string));
                } else {
                    AbyssMod.EVENT_BUS.post(new Class179(Class53.POST, uUID, null));
                }
                break;
        }
    }

    private static boolean Method4097(Path path) {
        return Files.isRegularFile(path, LinkOption.NOFOLLOW_LINKS);
    }

    private static boolean Method4117(Object data) {
        return Method4117(data);
    }

}
