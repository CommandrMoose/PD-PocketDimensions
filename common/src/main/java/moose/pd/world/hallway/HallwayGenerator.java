package moose.pd.world.hallway;

import moose.pd.Pd;
import moose.pd.world.hallway.pieces.StraightPiece;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;

public class HallwayGenerator {

    private static int TEMPLATE_SIZE = 8;
    private static int PLACEMENT_OFFSET = 6;

    // Generate from a position in world space and create the structure to be saved.
    public static void generateFromPosition(Level level, BlockPos corner, BlockPos resultLocation) {

        // Create a 2D array.
        boolean[][] hasBlockVector = new boolean[8][8];

        for (int x = 0; x < TEMPLATE_SIZE; x++) {
            for (int z = 0; z < TEMPLATE_SIZE; z++) {
                hasBlockVector[x][z] = level.getBlockState(new BlockPos(corner.getX() + 1 + x, corner.getY(), corner.getZ() + 1 + z)).getBlock() != Blocks.AIR;
            }
        }

        // Place structures if the position is correct.
        for (int x = 0; x < TEMPLATE_SIZE; x++) {
            for (int z = 0; z < TEMPLATE_SIZE; z++) {

                boolean isASpace = hasBlockVector[x][z];
                if (!isASpace) {
                    continue;
                } else {


                    // Get direction of the placement to place, ya dig?
                    // NORTH, SOUTH, EAST, WEST
                    boolean[] directions = new boolean[] {false, false, false, false};

                    directions[0] = doesPieceExist(hasBlockVector, x, z-1);
                    directions[1] = doesPieceExist(hasBlockVector, x, z+1);
                    directions[2] = doesPieceExist(hasBlockVector, x+1, z);
                    directions[3] = doesPieceExist(hasBlockVector, x-1, z);


                    // Place the final piece.
                    int finalX = x;
                    int finalZ = z;
                    level.getServer().getStructureManager().get(getRLForPiece(directions)).ifPresent(structure -> {

                        BlockPos position = new BlockPos(corner.getX() + (finalX * PLACEMENT_OFFSET), corner.getY() + 10, corner.getZ() + (finalZ * PLACEMENT_OFFSET));
                        StructurePlaceSettings settings = new StructurePlaceSettings();
                        ServerLevel slevel = (ServerLevel) level;
                        structure.placeInWorld(slevel, position, position, settings, level.getRandom(), 3);
                    });



                }

            }
        }

        //level.setBlockAndUpdate(corner.above(9), BlockRegistry.CORRIDOR_STRUCTURE_SAVER.get().defaultBlockState());
    }

    private static boolean doesPieceExist(boolean[][] states, int x, int z) {
        if (x < 0) return true;
        if (x >= TEMPLATE_SIZE) return true;
        if (z < 0) return true;
        if (z >= TEMPLATE_SIZE) return true;

        return states[x][z];
    }

    private static ResourceLocation getRLForPiece(boolean[] results) {

        String path = "hallways/";

        // CHECK IF NORTH/SOUTH/EAST/WEST
        if (results[0] && results[1] && !results[2] && !results[3]) {
            return new ResourceLocation(Pd.MOD_ID,path+ "h_ns");
        }

        if (!results[0] && !results[1] && results[2] && results[3]) {
            return new ResourceLocation(Pd.MOD_ID,path+ "h_ew");
        }

        if (results[0] && results[1] && results[2] && results[3]) {
            return new ResourceLocation(Pd.MOD_ID,path+ "h_4way");
        }

        // CHECK IF NORTH/SOUTH/EAST/WEST
        if (results[0] && !results[1] && results[2] && !results[3]) {
            return new ResourceLocation(Pd.MOD_ID,path+ "h_ne");
        }

        if (results[0] && !results[1] && !results[2] && results[3]) {
            return new ResourceLocation(Pd.MOD_ID,path+ "h_nw");
        }

        if (!results[0] && results[1] && results[2] && !results[3]) {
            return new ResourceLocation(Pd.MOD_ID,path+ "h_se");
        }

        if (!results[0] && results[1] && !results[2] && results[3]) {
            return new ResourceLocation(Pd.MOD_ID,path+ "h_sw");
        }

        if (results[0] && !results[1] && results[2] && results[3]) {
            return new ResourceLocation(Pd.MOD_ID,path+ "h_new");
        }

        if (results[0] && results[1] && results[2] && !results[3]) {
            return new ResourceLocation(Pd.MOD_ID,path+ "h_nse");
        }

        if (!results[0] && results[1] && results[2] && results[3]) {
            return new ResourceLocation(Pd.MOD_ID,path+ "h_sew");
        }

        if (results[0] && results[1] && !results[2] && results[3]) {
            return new ResourceLocation(Pd.MOD_ID,path+ "h_nsw");
        }

        return new ResourceLocation("");
    }

}
