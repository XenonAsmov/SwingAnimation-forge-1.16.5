package me.xenon.swinganim.module;

import com.mojang.blaze3d.matrix.MatrixStack;
import me.xenon.swinganim.Wrapper;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class SwingAnimation implements Wrapper {

    private static final int power = 70; // Можно изменить для увеличения или уменьшения силы удара
    @SubscribeEvent
    public void onRenderPlayerHand(RenderHandEvent event) {

        assert mc.player != null;

        if (mc.player.getItemInHand(event.getHand()) == mc.player.getMainHandItem() && !mc.player.getItemInHand(event.getHand()).isEmpty()) {
            MatrixStack matrixStack = event.getMatrixStack();
            matrixStack.pushPose();

            matrixStack.translate(0.45D, -0.45D, -0.55D);
            matrixStack.mulPose(Vector3f.XP.rotationDegrees(((-MathHelper.sin(mc.player.getAttackAnim(event.getPartialTicks()) * (float) Math.PI) * power))));

            IRenderTypeBuffer renderTypeBuffer = mc.renderBuffers().bufferSource();
            mc.getItemInHandRenderer().renderItem(
                    mc.player,
                    mc.player.getItemInHand(event.getHand()),
                    event.getHand() == Hand.MAIN_HAND ? ItemCameraTransforms.TransformType.FIRST_PERSON_RIGHT_HAND : ItemCameraTransforms.TransformType.FIRST_PERSON_LEFT_HAND,
                    event.getHand() != Hand.MAIN_HAND,
                    matrixStack,
                    renderTypeBuffer,
                    event.getLight()
            );
            matrixStack.popPose();
            event.setCanceled(true);
        }
    }
}


