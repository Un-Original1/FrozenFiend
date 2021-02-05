package com.unoriginal.iceologer.entity.render;

import com.unoriginal.iceologer.entity.Entity.EntityIceCube;
import com.unoriginal.iceologer.entity.model.ModelIceCube;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

@SideOnly(Side.CLIENT)
public class RenderIceCube extends Render<EntityIceCube> {

    private static final ResourceLocation TEXTURES = new ResourceLocation("iceologer:textures/entity/ice_chunk.png");
    public static final Factory FACTORY = new Factory();

    protected final ModelIceCube iceCube = new ModelIceCube();

    public RenderIceCube(RenderManager manager)
    {
        super(manager);
        this.shadowSize = 0.5F;
    }
    @Override
    public void doRender(@Nonnull EntityIceCube entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        GlStateManager.pushMatrix();
        this.bindEntityTexture(entity);
        GlStateManager.translate((float)x, (float)y, (float)z);
        GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.scale(-1, -1, 1);
        GlStateManager.translate(0.0D, -1.5D, 0.0D);
        this.iceCube.render(entity, partialTicks, 0.0F, 0.0F, entity.rotationYaw, entity.rotationPitch, 0.0625F);
        GlStateManager.popMatrix();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }
    @Override
    protected ResourceLocation getEntityTexture(EntityIceCube entity){
        return TEXTURES;
    }

    public static class Factory implements IRenderFactory<EntityIceCube> {

        @Override
        public Render<? super EntityIceCube> createRenderFor(RenderManager manager) {
            return new RenderIceCube(manager);
        }

    }

}
