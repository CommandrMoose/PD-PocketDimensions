package moose.pd.world.chunk;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import moose.pd.Pd;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.block.Block;
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

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class TempleChunkGenerator extends ChunkGenerator {

    public final Registry<Biome> BIOME_REG;
    public final Registry<StructureSet> SET_REG;
    private final Random random;

    public final int distanceBetweenRooms = 9;
    public final int arsChunkSize = 3;
    private final int chunkSize = 1;


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

    public final static Codec<TempleChunkGenerator> CODEC = RecordCodecBuilder
            .create(instance -> commonCodec(instance).and(RegistryOps.retrieveRegistry(Registry.BIOME_REGISTRY).forGetter((generator) -> generator.BIOME_REG))
                    .apply(instance, instance.stable(TempleChunkGenerator::new)));

    public TempleChunkGenerator(Registry<StructureSet> setReg, Registry<Biome> biomeReg) {
        super(setReg, Optional.empty(), new FixedBiomeSource(biomeReg.getHolderOrThrow(Biomes.PLAINS)));
        this.BIOME_REG = biomeReg;
        this.SET_REG = setReg;
        this.random = new Random();
    }

    @Override
    public Codec<TempleChunkGenerator> codec() {
        return CODEC;
    }

    @Override
    public void applyCarvers(WorldGenRegion worldGenRegion, long l, RandomState randomState, BiomeManager biomeManager, StructureManager structureManager, ChunkAccess chunkAccess, GenerationStep.Carving carving) {

    }

    @Override
    public void buildSurface(WorldGenRegion worldGenRegion, StructureManager structureManager, RandomState randomState, ChunkAccess chunkAccess) {
        ResourceLocation location = new ResourceLocation(Pd.MOD_ID,  "mr-test");
        ServerLevel level = worldGenRegion.getLevel();
        level.getLevel().getStructureManager().get(location).ifPresent(structure -> {
            BlockPos pos = chunkAccess.getPos().getBlockAt(0, 64,0);
            StructurePlaceSettings settings = new StructurePlaceSettings();
            structure.placeInWorld(level, pos, pos, settings, level.getRandom(), 1);
        });
    }

    @Override
    public void spawnOriginalMobs(WorldGenRegion worldGenRegion) {

    }

    @Override
    public int getGenDepth() {
        return 384;
    }

    @Override
    public CompletableFuture<ChunkAccess> fillFromNoise(Executor executor, Blender blender, RandomState randomState, StructureManager structureManager, ChunkAccess chunkAccess) {
        return CompletableFuture.completedFuture(chunkAccess);
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
    public int getBaseHeight(int i, int j, Heightmap.Types types, LevelHeightAccessor levelHeightAccessor, RandomState randomState) {
        return 0;
    }

    @Override
    public NoiseColumn getBaseColumn(int p_223028_, int p_223029_, LevelHeightAccessor level, RandomState p_223031_) {

        BlockState[] states = new BlockState[level.getHeight()];
        for(int i = 0; i < states.length; ++i){
            states[i] = Blocks.AIR.defaultBlockState();
        }

        return new NoiseColumn(this.getMinY(), states);

        //return new NoiseColumn(0, new BlockState[0]);
    }

    @Override
    public void addDebugScreenInfo(List<String> list, RandomState randomState, BlockPos blockPos) {
    }

    @Override
    public void applyBiomeDecoration(WorldGenLevel worldGenLevel, ChunkAccess chunkAccess, StructureManager structureManager) {
        // Place the desired piece.

    }

    @Override
    public void createStructures(RegistryAccess pRegistryAccess, RandomState pRandom, StructureManager pStructureManager, ChunkAccess pChunk, StructureTemplateManager pStructureTemplateManager, long pSeed) {
        //super.createStructures(pRegistryAccess, pRandom, pStructureManager, pChunk, pStructureTemplateManager, pSeed);

        //chunkAccess.setBlockState(chunkAccess.getPos().getBlockAt(0, 64,0), Blocks.STONE.defaultBlockState(), false);


    }

    @Override
    public void createReferences(WorldGenLevel pLevel, StructureManager pStructureManager, ChunkAccess pChunk) {
        //super.createReferences(pLevel, pStructureManager, pChunk);
    }


}
