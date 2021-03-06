package com.unoriginal.iceologer.entity.model;
// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.12

import com.unoriginal.iceologer.entity.Entity.EntityIceologer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.AbstractIllager;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelIceologer extends ModelBase {
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer cape;
	private final ModelRenderer left_leg;
	private final ModelRenderer right_leg;
	private final ModelRenderer right_arm;
	private final ModelRenderer left_arm;

	public ModelIceologer() {
		textureWidth = 128;
		textureHeight = 64;

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.cubeList.add(new ModelBox(head, 0, 0, -4.0F, -10.0F, -4.0F, 8, 10, 8, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 70, 33, -4.5F, -10.5F, -4.5F, 9, 11, 9, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 24, 0, -1.0F, -3.0F, -6.0F, 2, 4, 2, 0.0F, false));

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 16, 20, -4.0F, 0.0F, -3.0F, 8, 12, 6, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 38, -4.0F, 0.0F, -3.0F, 8, 18, 6, 0.25F, false));

		cape = new ModelRenderer(this);
		cape.setRotationPoint(0.0F, -1.0F, 3.0F);
		body.addChild(cape);
		cape.cubeList.add(new ModelBox(cape, 99, 16, -4.5F, 1.0F, -1.0F, 9, 20, 1, 0.0F, false));

		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(2.0F, 12.0F, 0.0F);
		left_leg.cubeList.add(new ModelBox(left_leg, 0, 22, -2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F, true));

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(-2.0F, 12.0F, 0.0F);
		right_leg.cubeList.add(new ModelBox(right_leg, 0, 22, -2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F, false));

		right_arm = new ModelRenderer(this);
		right_arm.setRotationPoint(-5.0F, 2.0F, 0.0F);
		right_arm.cubeList.add(new ModelBox(right_arm, 40, 46, -3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F, false));

		left_arm = new ModelRenderer(this);
		left_arm.setRotationPoint(5.0F, 2.0F, 0.0F);
		left_arm.cubeList.add(new ModelBox(left_arm, 40, 46, -1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F, true));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		head.render(f5);
		body.render(f5);
		left_leg.render(f5);
		right_leg.render(f5);
		right_arm.render(f5);
		left_arm.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
	{
		this.head.rotateAngleY = netHeadYaw * 0.017453292F;
		this.head.rotateAngleX = headPitch * 0.017453292F;
		this.left_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount * 0.5F;
		this.right_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount * 0.5F;
		this.left_leg.rotateAngleY = 0.0F;
		this.right_leg.rotateAngleY = 0.0F;
		//this.cape.rotateAngleX = MathHelper.cos(0.6662F) * 1.6F * limbSwingAmount * 0.5F;
		AbstractIllager.IllagerArmPose abstractillager$illagerarmpose = ((AbstractIllager)entityIn).getArmPose();
		if(abstractillager$illagerarmpose == AbstractIllager.IllagerArmPose.CROSSED) {

			this.left_arm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount * 0.5F;
			this.right_arm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount * 0.5F;
			this.right_arm.rotateAngleZ = 0.0F;
			this.left_arm.rotateAngleZ = 0.0F;

		}
		if (abstractillager$illagerarmpose == AbstractIllager.IllagerArmPose.SPELLCASTING)
		{
			this.right_arm.rotationPointZ = 0.0F;
			this.right_arm.rotationPointX = -5.0F;
			this.left_arm.rotationPointZ = 0.0F;
			this.left_arm.rotationPointX = 5.0F;
			this.right_arm.rotateAngleX = MathHelper.cos(ageInTicks * 0.6662F) * 0.25F;
			this.left_arm.rotateAngleX = MathHelper.cos(ageInTicks * 0.6662F) * 0.25F;
			this.right_arm.rotateAngleZ = 2.3561945F;
			this.left_arm.rotateAngleZ = -2.3561945F;
			this.right_arm.rotateAngleY = 0.0F;
			this.left_arm.rotateAngleY = 0.0F;
		}
	}
	@Override
	public void setLivingAnimations(EntityLivingBase entityLivingBase, float limbSwing, float limbSwingAmount, float partialTickTime)
	{
		EntityIceologer iceologer = (EntityIceologer)entityLivingBase;
		double d0 = iceologer.prevChasingPosX + (iceologer.chasingPosX - iceologer.prevChasingPosX) * partialTickTime - (iceologer.prevPosX + (iceologer.posX - iceologer.prevPosX) * partialTickTime);
		double d1 = iceologer.prevPosY + (iceologer.chasingPosY - iceologer.prevChasingPosY) * partialTickTime - (iceologer.prevPosY + (iceologer.posY - iceologer.prevPosY) * partialTickTime);
		double d2 = iceologer.prevChasingPosZ + (iceologer.chasingPosZ - iceologer.prevChasingPosZ) * partialTickTime - (iceologer.prevPosZ + (iceologer.posZ - iceologer.prevPosZ) * partialTickTime);
		float f = iceologer.prevRenderYawOffset + (iceologer.renderYawOffset - iceologer.prevRenderYawOffset) * partialTickTime;
		double d3 = MathHelper.sin(f * 0.017453292F);
		double d4 = (-MathHelper.cos(f * 0.017453292F));
		float f1 = (float) d1 * 10.0F;
		f1 = MathHelper.clamp(f1, -6.0F, 32.0F);
		float f2 = (float) (d0 * d3 + d2 * d4) * 100.0F;
		f2 = MathHelper.clamp(f2, 0.0F, 150.0F);
		float f3 = (float) (d0 * d4 - d2 * d3) * 100.0F;
		f3 = MathHelper.clamp(f3, -20.0F, 20.0F);

		float f4 = iceologer.prevRotationYaw + (iceologer.rotationYaw - iceologer.prevRotationYaw) * partialTickTime;
		f1 = f1 + MathHelper.sin((iceologer.prevDistanceWalkedModified + (iceologer.distanceWalkedModified - iceologer.prevDistanceWalkedModified) * partialTickTime) * 6.0F) * 32.0F * f4;
		if (iceologer.isSneaking())
			f1 += 25.0F;

		this.cape.rotateAngleX = (float) Math.toRadians(180F - (6.0F + f2 / 2.0F + f1));
		this.cape.rotateAngleY = (float) Math.toRadians(f3 / 2.0F);
		this.cape.rotateAngleZ = (float) Math.toRadians(180.0F - f3 / 2.0F);
		super.setLivingAnimations(iceologer, limbSwing, limbSwingAmount, partialTickTime);
	}
	public ModelRenderer getArm(EnumHandSide p_191216_1_)
	{
		return p_191216_1_ == EnumHandSide.LEFT ? this.left_arm : this.right_arm;
	}
}
