package com.unoriginal.iceologer.entity.model;
// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.12


import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(Side.CLIENT)
public class ModelIceCube extends ModelBase {
	private final ModelRenderer everything;

	public ModelIceCube() {
		textureWidth = 128;
		textureHeight = 128;

		everything = new ModelRenderer(this);
		everything.setRotationPoint(-2.0F, 9.0F, 1.0F);
		everything.cubeList.add(new ModelBox(everything, 64, 0, -14.0F, -1.0F, -17.0F, 16, 16, 16, 0.0F, true));
		everything.cubeList.add(new ModelBox(everything, 0, 40, 2.0F, -1.0F, -17.0F, 16, 8, 16, 0.0F, true));
		everything.cubeList.add(new ModelBox(everything, 0, 0, -14.0F, -9.0F, -1.0F, 16, 24, 16, 0.0F, true));
		everything.cubeList.add(new ModelBox(everything, 64, 32, 2.0F, -9.0F, -1.0F, 16, 16, 16, 0.0F, true));
		everything.cubeList.add(new ModelBox(everything, 0, 64, -14.0F, -9.0F, -9.0F, 16, 8, 8, 0.0F, true));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		everything.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}