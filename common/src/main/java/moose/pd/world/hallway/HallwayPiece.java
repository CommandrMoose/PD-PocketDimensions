package moose.pd.world.hallway;

import moose.pd.Pd;
import net.minecraft.resources.ResourceLocation;

public abstract class HallwayPiece {

    private String id;

    public HallwayPiece(String id) {
        this.id = id;
    }

    public ResourceLocation getPieceToPlace() {
        return new ResourceLocation(Pd.MOD_ID, id);
    }

}
