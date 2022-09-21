package moose.pd.world.chunk;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import moose.pd.Pd;
import moose.pd.world.ChunkGenerators;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.world.level.*;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.biome.FixedBiomeSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.blending.Blender;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class TempleChunkGenerator extends ChunkGenerator {

    public static final Codec<TempleChunkGenerator> CODEC = RecordCodecBuilder
            .create(instance -> commonCodec(instance).and(RegistryOps.retrieveRegistry(Registry.BIOME_REGISTRY).forGetter((thing) -> thing.BIOME_REG))
                    .apply(instance, instance.stable(TempleChunkGenerator::new)));

    public final Registry<Biome> BIOME_REG;
    public final Registry<StructureSet> SET_REG;
    public final Random RANDOM;

    // Some parameter values.
    public final int distanceBetweenRooms = 9;
    //public final int arsChunkSize = TardisARSPiece.LOCKED_PIECE_CHUNK_SIZE;
    private final int chunkSize = 16;

    private static String[] LOCATIONS = new String[] {
            "bf4fd009-3a6b-43e7-b7eb-0af7f040fdec",
            "4363b7ad-5c57-4a9c-a6a8-07621c2695e9",
            "c063d4c4-abb5-4367-b2d7-02f56ffa6bc8",
            "4b6b8077-6e04-45cf-8ee6-9fb925b18291",
            "c0d35236-84ce-4d87-804b-4f58f6c0e2a9",
            "4e015434-5168-4e9a-9ff8-72ae6e93af13",
            "d929ba38-3970-4a5c-a5a5-e77c03fb33ac",
            "5c536e0b-e846-4e8a-9c5a-5d6e8d08f445",
            "e40ddacd-7071-434c-af0a-84a40496a7f1",
            "6818a3d8-cd9f-40ff-89f5-1fe0c22baff8",
            "e8ebb10f-41be-40cd-bd7d-4f7f58d00e39"
    };


    public TempleChunkGenerator(Registry<StructureSet> setReg, Registry<Biome> biomeReg) {
        super(setReg, Optional.empty(), new FixedBiomeSource(biomeReg.getHolderOrThrow(ChunkGenerators.TEMPLE_BIOME)));
        this.BIOME_REG = biomeReg;
        this.SET_REG = setReg;
        this.RANDOM = new Random();
    }

    @Override
    public void applyBiomeDecoration(WorldGenLevel pLevel, ChunkAccess pChunk, StructureManager pStructureManager) {

        if (pChunk.getPos().x == 0 && pChunk.getPos().z == 0) {
            //placePieceInWorld(pLevel, getRandomConsoleRoomPiece().getResourceLocation(), pChunk);
        } else {
            // For each ARS chunk size interval.
            if(pChunk.getPos().x % 3 == 0 && pChunk.getPos().z % 3 == 0){

                // Determine if the piece is a room or a corridor
                ResourceLocation pieceToPlace = (isChunkAtRoomInterval(pChunk.getPos())) ? new ResourceLocation(Pd.MOD_ID, LOCATIONS[this.RANDOM.nextInt(LOCATIONS.length)]) : new ResourceLocation(Pd.MOD_ID, LOCATIONS[this.RANDOM.nextInt(LOCATIONS.length)]);
                placePieceInWorld(pLevel, pieceToPlace, pChunk);
            }
        }
    }

    private void placePieceInWorld(WorldGenLevel level, ResourceLocation pieceToPlace, ChunkAccess pChunk) {
        // Place the desired piece.
        level.getLevel().getStructureManager().get(pieceToPlace).ifPresent(structure -> {
            BlockPos pos = pChunk.getPos().getBlockAt(0, 64,0).north(chunkSize).west(chunkSize); // Must be offset to utilize all 3x3 chunks.
            StructurePlaceSettings settings = new StructurePlaceSettings();
            structure.placeInWorld(level, pos, pos, settings, level.getRandom(), 1);
        });
    }


    /**
     * Determines if the chunk is a room chunk
     * @param pos the position of the chunk
     * @return is the chunk a room chunk
     */
    private boolean isChunkAtRoomInterval(ChunkPos pos) {
        return pos.x % distanceBetweenRooms == 0 && pos.z % distanceBetweenRooms == 0;
    }

//    /**
//     * Fetch a random corridor piece to populate a chunk
//     * @return random corridor ARS piece from the registry.
//     */
//    private TardisARSPiece getRandomCorridorPiece() {
//        return TardisARSPieceRegistry.CORRIDORS.get(this.RANDOM.nextInt(TardisARSPieceRegistry.CORRIDORS.size()));
//    }
//
//    /**
//     * Fetch a random console room piece to populate a chunk
//     * @return random console room ARS piece from the registry.
//     */
//    private TardisARSPiece getRandomConsoleRoomPiece() {
//        return TardisARSPieceRegistry.CONSOLE_ROOMS.get(this.RANDOM.nextInt(TardisARSPieceRegistry.CONSOLE_ROOMS.size()));
//    }
//
//    /**
//     * Fetch a random room piece to populate a chunk
//     * @return random room ARS piece from the registry.
//     */
//    private TardisARSPiece getRandomRoomPiece() {
//        return TardisARSPieceRegistry.ROOMS.get(this.RANDOM.nextInt(TardisARSPieceRegistry.ROOMS.size()));
//    }


    @Override
    public void createStructures(RegistryAccess pRegistryAccess, RandomState pRandom, StructureManager pStructureManager, ChunkAccess pChunk, StructureTemplateManager pStructureTemplateManager, long pSeed) {
        //super.createStructures(pRegistryAccess, pRandom, pStructureManager, pChunk, pStructureTemplateManager, pSeed);
    }

    @Override
    public void createReferences(WorldGenLevel pLevel, StructureManager pStructureManager, ChunkAccess pChunk) {
        //super.createReferences(pLevel, pStructureManager, pChunk);
    }

    @Override
    public Codec<TempleChunkGenerator> codec() {
        return CODEC;
    }

    @Override
    public void applyCarvers(WorldGenRegion p_223043_, long p_223044_, RandomState p_223045_, BiomeManager p_223046_, StructureManager p_223047_, ChunkAccess p_223048_, GenerationStep.Carving p_223049_) {

    }

    @Override
    public void buildSurface(WorldGenRegion p_223050_, StructureManager p_223051_, RandomState p_223052_, ChunkAccess p_223053_) {

    }

    @Override
    public void spawnOriginalMobs(WorldGenRegion p_62167_) {}

    @Override
    public int getGenDepth() {
        return 384;
    }

    @Override
    public CompletableFuture<ChunkAccess> fillFromNoise(Executor executor, Blender p_223210_, RandomState p_223211_, StructureManager p_223212_, ChunkAccess access) {
        return CompletableFuture.completedFuture(access);
    }

    @Override
    public int getSeaLevel() {
        return -63;
    }

    @Override
    public int getMinY() {
        return 0;
    }

    @Override
    public int getBaseHeight(int p_223032_, int p_223033_, Heightmap.Types p_223034_, LevelHeightAccessor p_223035_, RandomState p_223036_) {
        return 0;
    }

    @Override
    public NoiseColumn getBaseColumn(int p_223028_, int p_223029_, LevelHeightAccessor level, RandomState p_223031_) {

        BlockState[] states = new BlockState[level.getHeight()];
        for(int i = 0; i < states.length; ++i){
            states[i] = Blocks.STONE.defaultBlockState();
        }

        //return new NoiseColumn(this.getMinY(), states);

        return new NoiseColumn(0, states);
    }

    @Override
    public void addDebugScreenInfo(List<String> p_223175_, RandomState p_223176_, BlockPos p_223177_) {}
}
