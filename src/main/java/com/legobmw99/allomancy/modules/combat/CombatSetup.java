package com.legobmw99.allomancy.modules.combat;

import com.legobmw99.allomancy.Allomancy;
import com.legobmw99.allomancy.modules.combat.client.CombatClientSetup;
import com.legobmw99.allomancy.modules.combat.entity.ProjectileNuggetEntity;
import com.legobmw99.allomancy.modules.combat.item.CoinBagItem;
import com.legobmw99.allomancy.modules.combat.item.KolossBladeItem;
import com.legobmw99.allomancy.modules.combat.item.MistcloakItem;
import com.legobmw99.allomancy.modules.combat.item.ObsidianDaggerItem;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class CombatSetup {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Allomancy.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Allomancy.MODID);
    public static final RegistryObject<CoinBagItem> COIN_BAG = ITEMS.register("coin_bag", CoinBagItem::new);
    public static final RegistryObject<ObsidianDaggerItem> OBSIDIAN_DAGGER = ITEMS.register("obsidian_dagger", ObsidianDaggerItem::new);
    public static final RegistryObject<KolossBladeItem> KOLOSS_BLADE = ITEMS.register("koloss_blade", KolossBladeItem::new);
    public static final RegistryObject<EntityType<ProjectileNuggetEntity>> NUGGET_PROJECTILE = ENTITIES.register("nugget_projectile",
                                                                                                                 () -> EntityType.Builder.<ProjectileNuggetEntity>create(
                                                                                                                         ProjectileNuggetEntity::new, EntityClassification.MISC)
                                                                                                                         .setShouldReceiveVelocityUpdates(true)
                                                                                                                         .setUpdateInterval(20)
                                                                                                                         .setCustomClientFactory(
                                                                                                                                 (spawnEntity, world) -> new ProjectileNuggetEntity(
                                                                                                                                         world, spawnEntity.getEntity()))
                                                                                                                         .size(0.25F, 0.25F)
                                                                                                                         .build("nugget_projectile"));
    public static final IArmorMaterial WoolArmor = new IArmorMaterial() {
        @Override
        public int getDurability(EquipmentSlotType slotIn) {
            return 50;
        }

        @Override
        public int getDamageReductionAmount(EquipmentSlotType slotIn) {
            return slotIn == EquipmentSlotType.CHEST ? 4 : 0;
        }

        @Override
        public int getEnchantability() {
            return 15;
        }

        @Override
        public SoundEvent getSoundEvent() {
            return SoundEvents.ITEM_ARMOR_EQUIP_LEATHER;
        }

        @Override
        public Ingredient getRepairMaterial() {
            return Ingredient.fromItems(Items.GRAY_WOOL);
        }

        @Override
        public String getName() {
            return "allomancy:wool";
        }

        @Override
        public float getToughness() {
            return 0;
        }

        @Override
        public float getKnockbackResistance() {
            return 0;
        }

    };
    public static final RegistryObject<MistcloakItem> MISTCLOAK = ITEMS.register("mistcloak", MistcloakItem::new);

    public static void register() {
        ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static void clientInit(final FMLClientSetupEvent e) {
        CombatClientSetup.registerEntityRenders();
    }
}
