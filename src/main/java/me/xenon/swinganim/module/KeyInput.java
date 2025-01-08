package me.xenon.swinganim.module;

import me.xenon.swinganim.Wrapper;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.lwjgl.glfw.GLFW;

public class KeyInput implements Wrapper {
    private SwingAnimation swingAnimation;

    public KeyInput() {
        swingAnimation = new SwingAnimation();
        EVENT_BUS.register(swingAnimation);
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (event.getKey() == GLFW.GLFW_KEY_F7 && event.getAction() == GLFW.GLFW_PRESS) {
            if (swingAnimation == null) {
                swingAnimation = new SwingAnimation();
                EVENT_BUS.register(swingAnimation);
            }
        }

        if (event.getKey() == GLFW.GLFW_KEY_F8 && event.getAction() == GLFW.GLFW_PRESS) {
            if (swingAnimation != null) {
                EVENT_BUS.unregister(swingAnimation);
                swingAnimation = null;

                assert mc.player != null;
                mc.player.input = new net.minecraft.util.MovementInputFromOptions(mc.options);
            }
        }
    }
}
