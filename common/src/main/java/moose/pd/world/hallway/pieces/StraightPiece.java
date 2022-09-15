package moose.pd.world.hallway.pieces;

import moose.pd.Pd;
import moose.pd.world.hallway.HallwayPiece;
import net.minecraft.resources.ResourceLocation;

public class StraightPiece extends HallwayPiece {

    private boolean northSouth;

    public StraightPiece(boolean isNorthSouth) {
        this("NOT APPLICABLE");
        this.northSouth = isNorthSouth;
    }

    private StraightPiece(String id) {
        super(id);
    }

    @Override
    public ResourceLocation getPieceToPlace() {

        return (this.northSouth) ? new ResourceLocation(Pd.MOD_ID, "hallways/h_ns") : new ResourceLocation(Pd.MOD_ID,"hallways/h_ew");
    }
}
