package primetoxinz.caravans.client.gui;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import primetoxinz.caravans.CaravansMod;
import primetoxinz.caravans.api.Merchant;

import java.awt.*;

/**
 * Created by primetoxinz on 7/6/17.
 */
public class TabMerchant {

    private GuiMerchant parent;
    private Merchant merchant;
    private int x, y, w = 32, h = 28;
    public TabMerchant(GuiMerchant parent, Merchant merchant) {
        this.parent = parent;
        this.merchant = merchant;
    }

    public boolean mouseReleased(int mouseX, int mouseY, int state) {
        if (isClicked(mouseX, mouseY) && state == 0) {
            EntityPlayer player = parent.container.player;
            player.openGui(CaravansMod.MODID, parent.container.caravan.getEntity(merchant).getEntityId(), player.world, (int) player.posX, (int) player.posY, (int) player.posZ);
            return true;
        }
        return false;

    }

    private boolean isSelected() {
        return parent.container.merchant.equals(this.merchant);
    }

    public void draw(int x, int y) {
        this.x = x;
        this.y = y;

        int offsetY = isSelected() ? h : 0;
        GlStateManager.disableLighting();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        parent.mc.getTextureManager().bindTexture(parent.CARAVAN_LOC);
        parent.drawTexturedModalRect(x, y, 224, 15 + offsetY, w, h);
        parent.drawItem(merchant.getIcon(),x+8,y+6);
    }


    private boolean isClicked(int mouseX, int mouseY) {
        return new Rectangle(x, y, w, h).contains(mouseX, mouseY);
    }
}
