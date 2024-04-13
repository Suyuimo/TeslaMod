package de.suyuimo.teslamod.entitys;

import de.suyuimo.teslamod.TeslaMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityManager {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, TeslaMod.MODID);

    public static final RegistryObject<EntityType<ModelYEntity>> MODELY = ENTITIES.register("modely",
            () -> EntityType.Builder.of(ModelYEntity::new, MobCategory.MISC)
                //    .sized(1.375F, 0.5625F) // Größe ändern, falls nötig
                    .build("modely"));


    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }

}
