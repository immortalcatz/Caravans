package primetoxinz.caravans.common;

import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import primetoxinz.caravans.api.IEntityTrade;

/**
 * Created by primetoxinz on 7/9/17.
 */
public class ItemEntityTrade implements IEntityTrade {
    private ItemStack input;
    private Class<? extends EntityLiving> output;

    public ItemEntityTrade(ItemStack input, Class<? extends EntityLiving> output) {
        this.input = input;
        this.output = output;
    }

    @Override
    public ItemStack getInput() {
        return input;
    }

    @Override
    public Class<? extends EntityLiving> getOutput() {
        return output;
    }

    @Override
    public String toString() {
        return String.format("%s -> %s", getInput(), getOutput().getSimpleName());
    }
}