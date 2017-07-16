package primetoxinz.caravans.compat;

import com.google.common.collect.Maps;
import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import net.minecraft.util.ResourceLocation;
import primetoxinz.caravans.CaravansMod;
import primetoxinz.caravans.api.CaravanAPI;
import primetoxinz.caravans.api.CaravanBuilder;
import primetoxinz.caravans.api.MerchantBuilder;
import primetoxinz.caravans.common.entity.EntityCaravaneer;
import primetoxinz.caravans.common.entity.types.*;
import stanhebben.zenscript.annotations.Optional;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.HashMap;

/**
 * Created by primetoxinz on 7/9/17.
 */
@ZenClass("mod.caravans.Caravan")
public class MTCaravan {

    public final static HashMap<String, Class<? extends EntityCaravaneer>> MODEL_TYPES = Maps.newHashMap();

    static {
        MODEL_TYPES.put("villager", EntityVillagerCaravaneer.class);
        MODEL_TYPES.put("zombie", EntityZombieCaravaneer.class);
        MODEL_TYPES.put("skeleton", EntitySkeletonCaravaneer.class);
        MODEL_TYPES.put("creeper", EntityCreeperCaravaneer.class);
        MODEL_TYPES.put("spider", EntitySpiderCaravaneer.class);
        MODEL_TYPES.put("human", EntityHumanCaravaneer.class);

    }

    public static Class<? extends EntityCaravaneer> getModel(String model) {
        if (model != null && MODEL_TYPES.containsKey(model.toLowerCase()))
            return MODEL_TYPES.get(model.toLowerCase());
        return EntityVillagerCaravaneer.class;
    }

    @ZenMethod
    public static void addFollower(String caravan, String merchant, @Optional String modelType) {
        CaravanBuilder builder = MTCompat.getCaravan(caravan);
        MerchantBuilder follower = MTCompat.getMerchant(merchant);
        if (builder != null && follower != null) {
            CraftTweakerAPI.apply(new AddFollower(builder, follower, modelType));
        }
    }


    public static class AddFollower implements IAction {

        private CaravanBuilder builder;
        private MerchantBuilder merchant;
        private String model;

        public AddFollower(CaravanBuilder builder, MerchantBuilder merchant, String model) {
            this.builder = builder;
            this.merchant = merchant;
            this.model = model;
        }

        @Override
        public void apply() {
            builder.addFollower(getModel(model), merchant);
        }

        @Override
        public String describe() {
            return String.format("Adding Caravan Follower: %s > %s ", builder, merchant);
        }

    }

    @ZenMethod
    public static void registerCaravan(String name, @Optional String leaderModel, @Optional String gamestage) {
        CraftTweakerAPI.apply(new RegisterCaravan(name, gamestage, leaderModel));
    }

    public static class RegisterCaravan implements IAction {
        private CaravanBuilder builder;

        protected RegisterCaravan(String name, String gamestage, @Optional String leaderModel) {
            this.builder = new CaravanBuilder(new ResourceLocation(CaravansMod.MODID, name), getModel(leaderModel));
            if (gamestage != null) {
                builder.setStage(gamestage);
            }
        }


        @Override
        public void apply() {
            if (!CaravanAPI.CARAVANS.containsKey(builder.getRegistryName())) {
                CaravanAPI.CARAVANS.register(builder);
            }
        }


        @Override
        public String describe() {
            return String.format("Registering Caravan: %s ", builder);
        }

    }


}
