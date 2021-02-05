package com.unoriginal.iceologer.entity.render;

import com.unoriginal.iceologer.entity.Entity.EntityIceologer;
import com.unoriginal.iceologer.entity.model.ModelIceologer;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.entity.EntityLivingBase;

import net.minecraft.entity.monster.EntitySpellcasterIllager;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

@SideOnly(Side.CLIENT)
public class RenderIceologer extends RenderLiving<EntityIceologer>
{
    private static final ResourceLocation texture = new ResourceLocation("iceologer:textures/entity/iceologer.png");
    public static final Factory FACTORY = new Factory();

    public RenderIceologer(RenderManager managerIn)
    {
        super(managerIn, new ModelIceologer(), 0.5F);
        this.addLayer(new LayerHeldItem(this)
        {
            public void doRenderLayer(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
            {
                if (((EntitySpellcasterIllager)entitylivingbaseIn).isSpellcasting())
                {
                    super.doRenderLayer(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale);
                }
            }
            protected void translateToHand(EnumHandSide p_191361_1_)
            {
                ((ModelIceologer)this.livingEntityRenderer.getMainModel()).getArm(p_191361_1_).postRender(0.0625F);
            }
        });
    }
    @Override
    @Nonnull
    protected ResourceLocation getEntityTexture(@Nonnull EntityIceologer entity)
    {
        return texture;
    }

    protected void preRenderCallback(EntityIceologer entitylivingbaseIn, float partialTickTime)
    {
        float f = 0.9375F;
        GlStateManager.scale(0.9375F, 0.9375F, 0.9375F);
    }
    public static class Factory implements IRenderFactory<EntityIceologer> {

        @Override
        public Render<? super EntityIceologer> createRenderFor(RenderManager manager) {
            return new RenderIceologer(manager);
        }

    }
}
