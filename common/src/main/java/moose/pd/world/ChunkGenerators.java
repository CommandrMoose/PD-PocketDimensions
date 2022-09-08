package moose.pd.world;

import com.mojang.serialization.Codec;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import moose.pd.Pd;
import moose.pd.world.chunk.TempleChunkGenerator;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.chunk.ChunkGenerator;

public class ChunkGenerators {

    public static final DeferredRegister<Codec<? extends ChunkGenerator>> CHUNK_GENERATORS = DeferredRegister.create(Pd.MOD_ID, Registry.CHUNK_GENERATOR_REGISTRY);

    public static final RegistrySupplier<Codec<? extends ChunkGenerator>> TEMPLE_CHUNK_GENERATOR = CHUNK_GENERATORS.register("temple", () -> TempleChunkGenerator.CODEC);

}
