package moose.pd.client.models;

import dev.architectury.injectables.annotations.ExpectPlatform;
import moose.pd.Pd;
import moose.pd.client.models.temple.BlueBoxExternalModel;
import moose.pd.client.models.temple.GreekExternalModel;
import moose.pd.client.models.temple.TempleExternalModel;
import moose.pd.util.PlatformWarning;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

public class ModelRegistry {

    public static ModelLayerLocation TEMPLE_EXTERNAL;
    public static ModelLayerLocation GREEK_EXTERNAL;
    public static ModelLayerLocation BLUE_BOX_EXTERNAL;

    public static void init() {
        TEMPLE_EXTERNAL = register(new ModelLayerLocation(new ResourceLocation(Pd.MOD_ID, "temple"), "temple"), TempleExternalModel::meshLayer);
        GREEK_EXTERNAL = register(new ModelLayerLocation(new ResourceLocation(Pd.MOD_ID, "greek"), "greek"), GreekExternalModel::meshLayer);
        BLUE_BOX_EXTERNAL = register(new ModelLayerLocation(new ResourceLocation(Pd.MOD_ID, "blue_box"), "blue_box"), BlueBoxExternalModel::meshLayer);
    }


    @ExpectPlatform
    public static ModelLayerLocation register(ModelLayerLocation location, Supplier<LayerDefinition> definition) {
        throw new RuntimeException(PlatformWarning.addWarning(ModelRegistry.class));
    }

}
