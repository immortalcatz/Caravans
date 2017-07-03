package primetoxinz.caravans.capability;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import primetoxinz.caravans.api.ICaravan;

/**
 * Created by primetoxinz on 7/3/17.
 */
public class Caravaner implements ICaravaner {
    ICaravan caravan;

    public Caravaner() {
    }

    @Override
    public ICaravaner setCaravan(ICaravan caravan) {
        this.caravan = caravan;
        return this;
    }

    @Override
    public ICaravan getCaravan() {
        return caravan;
    }

    @Override
    public BlockPos getPosition() {
        return BlockPos.ORIGIN;
    }

    @Override
    public ICaravaner spawn(World world, BlockPos pos, ICaravan caravan) {

        return this;
    }

    @Override
    public ICaravaner setTarget(EntityPlayer player) {
        return this;
    }

    @Override
    public int getID() {
        return 0;
    }

}
