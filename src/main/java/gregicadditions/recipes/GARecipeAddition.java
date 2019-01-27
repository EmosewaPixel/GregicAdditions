package gregicadditions.recipes;

import forestry.core.ModuleCore;
import forestry.core.fluids.Fluids;
import forestry.core.items.EnumElectronTube;
import gregicadditions.GAConfig;
import gregicadditions.GAMaterials;
import gregicadditions.item.GAMetaBlocks;
import gregicadditions.item.GAMetaItems;
import gregicadditions.item.GAMultiblockCasing;
import gregicadditions.item.GATransparentCasing;
import gregicadditions.machines.GATileEntities;
import gregtech.api.GTValues;
import gregtech.api.items.ToolDictNames;
import gregtech.api.recipes.CountableIngredient;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.recipes.ingredients.IntCircuitIngredient;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.MarkerMaterials.Color;
import gregtech.api.unification.material.MarkerMaterials.Tier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.type.*;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.MaterialStack;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.api.util.GTUtility;
import gregtech.common.blocks.BlockMachineCasing;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.BlockMultiblockCasing.MultiblockCasingType;
import gregtech.common.blocks.BlockWireCoil;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.items.MetaItems;
import gregtech.common.metatileentities.MetaTileEntities;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GARecipeAddition {

    private static final MaterialStack[] solderingList = {
            new MaterialStack(Materials.Tin, 2L),
            new MaterialStack(Materials.SolderingAlloy, 1L),
            new MaterialStack(Materials.Lead, 4L)
    };

    private static final MaterialStack[] sawLubricants = {
            new MaterialStack(Materials.Lubricant, 1L),
            new MaterialStack(Materials.DistilledWater, 3L),
            new MaterialStack(Materials.Water, 4L)
    };

    private static final MaterialStack[] cableFluids = {
            new MaterialStack(Materials.Rubber, 144),
            new MaterialStack(GAMaterials.StyreneButadieneRubber, 108),
            new MaterialStack(GAMaterials.SiliconeRubber, 72)
    };

    private static final MaterialStack[] cableDusts = {
            new MaterialStack(GAMaterials.Polydimethylsiloxane, 1),
            new MaterialStack(GAMaterials.PolyvinylChloride, 1)
    };

    private static final MaterialStack[] firstMetal = {
            new MaterialStack(Materials.Iron, 1),
            new MaterialStack(Materials.Nickel, 1),
            new MaterialStack(Materials.Invar, 2),
            new MaterialStack(Materials.Steel, 2),
            new MaterialStack(Materials.StainlessSteel, 3),
            new MaterialStack(Materials.Titanium, 3),
            new MaterialStack(Materials.Tungsten, 4),
            new MaterialStack(Materials.TungstenSteel, 5)
    };

    private static final MaterialStack[] lastMetal = {
            new MaterialStack(Materials.Tin, 0),
            new MaterialStack(Materials.Zinc, 0),
            new MaterialStack(Materials.Aluminium, 1)
    };

    private static final MaterialStack[] ironOres = {
            new MaterialStack(Materials.Pyrite, 1),
            new MaterialStack(Materials.BrownLimonite, 1),
            new MaterialStack(Materials.YellowLimonite, 1),
            new MaterialStack(Materials.Magnetite, 1),
            new MaterialStack(Materials.Iron, 1)
    };

    private static final MaterialStack[] lubeDusts = {
            new MaterialStack(Materials.Talc, 1),
            new MaterialStack(Materials.Soapstone, 1),
            new MaterialStack(Materials.Redstone, 1)
    };

    private static final MaterialStack[] lapisLike = {
            new MaterialStack(Materials.Lapis, 1),
            new MaterialStack(Materials.Lazurite, 1),
            new MaterialStack(Materials.Sodalite, 1)
    };

    public static void init() {
        //GTNH Bricks
        ModHandler.addSmeltingRecipe(GAMetaItems.COMPRESSED_COKE_CLAY.getStackForm(), GAMetaItems.COKE_BRICK.getStackForm());
        ModHandler.removeFurnaceSmelting(new ItemStack(Items.CLAY_BALL, 1, OreDictionary.WILDCARD_VALUE));
        ModHandler.addSmeltingRecipe(GAMetaItems.COMPRESSED_CLAY.getStackForm(), new ItemStack(Items.BRICK));
        RecipeMaps.ALLOY_SMELTER_RECIPES.recipeBuilder().duration(200).EUt(8).inputs(GAMetaItems.COMPRESSED_CLAY.getStackForm(), new ItemStack(Blocks.SAND, 2)).outputs(GAMetaItems.COKE_BRICK.getStackForm()).buildAndRegister();
        RecipeMaps.ALLOY_SMELTER_RECIPES.recipeBuilder().duration(200).EUt(2).inputs(new ItemStack(Items.CLAY_BALL)).notConsumable(MetaItems.SHAPE_MOLD_INGOT).outputs(new ItemStack(Items.BRICK)).buildAndRegister();
        ModHandler.addShapelessRecipe("acacia_form", GAMetaItems.ACACIA_FORM.getStackForm(), GAMetaItems.PLANK_ACACIA.getStackForm(), ToolDictNames.craftingToolKnife);
        ModHandler.addShapelessRecipe("birch_form", GAMetaItems.BIRCH_FORM.getStackForm(), GAMetaItems.PLANK_BIRCH.getStackForm(), ToolDictNames.craftingToolKnife);
        ModHandler.addShapelessRecipe("darkoak_form", GAMetaItems.DARK_OAK_FORM.getStackForm(), GAMetaItems.PLANK_DARKOAK.getStackForm(), ToolDictNames.craftingToolKnife);
        ModHandler.addShapelessRecipe("jungle_form", GAMetaItems.JUNGLE_FORM.getStackForm(), GAMetaItems.PLANK_JUNGLE.getStackForm(), ToolDictNames.craftingToolKnife);
        ModHandler.addShapelessRecipe("oak_form", GAMetaItems.OAK_FORM.getStackForm(), GAMetaItems.PLANK_OAK.getStackForm(), ToolDictNames.craftingToolKnife);
        ModHandler.addShapelessRecipe("spruce_form", GAMetaItems.SPRUCE_FORM.getStackForm(), GAMetaItems.PLANK_SPRUCE.getStackForm(), ToolDictNames.craftingToolKnife);
        ModHandler.addShapelessRecipe("clay_brick", GAMetaItems.COMPRESSED_CLAY.getStackForm(), new ItemStack(Items.CLAY_BALL), "formWood");
        ModHandler.addShapedRecipe("eight_clay_brick", GAMetaItems.COMPRESSED_CLAY.getStackForm(8), "BBB", "BFB", "BBB", 'B', new ItemStack(Items.CLAY_BALL), 'F', "formWood");
        ModHandler.addShapedRecipe("coke_brick", GAMetaItems.COMPRESSED_COKE_CLAY.getStackForm(3), "BBB", "SFS", "SSS", 'B', new ItemStack(Items.CLAY_BALL), 'S', new ItemStack(Blocks.SAND), 'F', "formWood");
        ModHandler.addShapedRecipe("coke_bricks", GAMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(GAMultiblockCasing.CasingType.COKE_OVEN_BRICKS), "BB", "BB", 'B', GAMetaItems.COKE_BRICK.getStackForm());

        //GT5U Old Primitive Brick Processing
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:casing_primitive_bricks"));
        ModHandler.addShapedRecipe("fireclay_bricks", MetaBlocks.METAL_CASING.getItemVariant(BlockMetalCasing.MetalCasingType.PRIMITIVE_BRICKS), "BB", "BB", 'B', GAMetaItems.FIRECLAY_BRICK.getStackForm());
        ModHandler.addSmeltingRecipe(GAMetaItems.COMPRESSED_FIRECLAY.getStackForm(), GAMetaItems.FIRECLAY_BRICK.getStackForm());
        ModHandler.addShapelessRecipe("fireclay", OreDictUnifier.get(OrePrefix.dust, GAMaterials.Fireclay, 2), "dustBrick", OreDictUnifier.get(OrePrefix.dust, Materials.Clay));
        RecipeMaps.MIXER_RECIPES.recipeBuilder().input(OrePrefix.dust, GAMaterials.Brick).input(OrePrefix.dust, Materials.Clay).outputs(OreDictUnifier.get(OrePrefix.dust, GAMaterials.Fireclay, 2)).duration(200).EUt(8).buildAndRegister();
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder().input(OrePrefix.dust, GAMaterials.Fireclay).outputs(GAMetaItems.COMPRESSED_FIRECLAY.getStackForm()).duration(400).EUt(2).buildAndRegister();
        RecipeMaps.FORGE_HAMMER_RECIPES.recipeBuilder().inputs(new ItemStack(Items.BRICK)).outputs(OreDictUnifier.get(OrePrefix.dustSmall, GAMaterials.Brick)).duration(16).EUt(10).buildAndRegister();

        //GT5U Misc Recipes
        ModHandler.addSmeltingRecipe(new ItemStack(Items.SLIME_BALL), MetaItems.RUBBER_DROP.getStackForm());
        ModHandler.removeRecipeByName(new ResourceLocation("minecraft:bone_meal_from_bone"));
        ModHandler.addShapelessRecipe("harder_bone_meal", new ItemStack(Items.DYE, 3, 15), new ItemStack(Items.BONE), ToolDictNames.craftingToolMortar);
        RecipeMaps.FORGE_HAMMER_RECIPES.recipeBuilder().inputs(new ItemStack(Items.BONE)).outputs(new ItemStack(Items.DYE, 3, 15)).duration(16).EUt(10).buildAndRegister();
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder().inputs(new ItemStack(Items.BONE)).outputs(new ItemStack(Items.DYE, 3, 15)).duration(300).EUt(2).buildAndRegister();
        ModHandler.addSmeltingRecipe(GAMetaItems.COMPRESSED_FIRECLAY.getStackForm(), GAMetaItems.FIRECLAY_BRICK.getStackForm());

        //GT6 Bending
        if (GAConfig.GT6.BendingCurvedPlates && GAConfig.GT6.BendingCylinders) {
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:iron_bucket"));
            ModHandler.addShapedRecipe("bucket", new ItemStack(Items.BUCKET), "ChC", " P ", 'C', "plateCurvedIron", 'P', "plateIron");
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(4).input(OrePrefix.valueOf("plateCurved"), Materials.Iron, 2).input(OrePrefix.plate, Materials.Iron).outputs(new ItemStack(Items.BUCKET)).buildAndRegister();
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(4).input(OrePrefix.valueOf("plateCurved"), Materials.WroughtIron, 2).input(OrePrefix.plate, Materials.WroughtIron).outputs(new ItemStack(Items.BUCKET)).buildAndRegister();
            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:iron_helmet"));
            ModHandler.addShapedRecipe("iron_helmet", new ItemStack(Items.IRON_HELMET), "PPP", "ChC", 'P', "plateIron", 'C', "plateCurvedIron");
            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:iron_chestplate"));
            ModHandler.addShapedRecipe("iron_chestplate", new ItemStack(Items.IRON_CHESTPLATE), "PhP", "CPC", "CPC", 'P', "plateIron", 'C', "plateCurvedIron");
            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:iron_leggings"));
            ModHandler.addShapedRecipe("iron_leggings", new ItemStack(Items.IRON_LEGGINGS), "PCP", "ChC", "C C", 'P', "plateIron", 'C', "plateCurvedIron");
            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:iron_boots"));
            ModHandler.addShapedRecipe("iron_boots", new ItemStack(Items.IRON_BOOTS), "P P", "ChC", 'P', "plateIron", 'C', "plateCurvedIron");
            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:golden_helmet"));
            ModHandler.addShapedRecipe("golden_helmet", new ItemStack(Items.GOLDEN_HELMET), "PPP", "ChC", 'P', "plateGold", 'C', "plateCurvedGold");
            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:golden_chestplate"));
            ModHandler.addShapedRecipe("golden_chestplate", new ItemStack(Items.GOLDEN_CHESTPLATE), "PhP", "CPC", "CPC", 'P', "plateGold", 'C', "plateCurvedGold");
            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:golden_leggings"));
            ModHandler.addShapedRecipe("golden_leggings", new ItemStack(Items.GOLDEN_LEGGINGS), "PCP", "ChC", "C C", 'P', "plateGold", 'C', "plateCurvedGold");
            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:golden_boots"));
            ModHandler.addShapedRecipe("golden_boots", new ItemStack(Items.GOLDEN_BOOTS), "P P", "ChC", 'P', "plateGold", 'C', "plateCurvedGold");
            ModHandler.addShapedRecipe("chain_helmet", new ItemStack(Items.CHAINMAIL_HELMET), "RRR", "RhR", 'R', "ringIron");
            ModHandler.addShapedRecipe("chain_chestplate", new ItemStack(Items.CHAINMAIL_CHESTPLATE), "RhR", "RRR", "RRR", 'R', "ringIron");
            ModHandler.addShapedRecipe("chain_leggings", new ItemStack(Items.CHAINMAIL_LEGGINGS), "RRR", "RhR", "R R", 'R', "ringIron");
            ModHandler.addShapedRecipe("chain_boots", new ItemStack(Items.CHAINMAIL_BOOTS), "R R", "RhR", 'R', "ringIron");
        }
        for (Material m : IngotMaterial.MATERIAL_REGISTRY) {
            if (!OreDictUnifier.get(OrePrefix.ring, m).isEmpty() && !OreDictUnifier.get(OrePrefix.stick, m).isEmpty() && m != Materials.Rubber && m != GAMaterials.StyreneButadieneRubber && m != GAMaterials.SiliconeRubber && GAConfig.GT6.BendingRings && GAConfig.GT6.BendingCylinders) {
                ModHandler.removeRecipes(OreDictUnifier.get(OrePrefix.ring, m));
                ModHandler.addShapedRecipe("tod_to_ring_" + m.toString(), OreDictUnifier.get(OrePrefix.ring, m), "hS", " C", 'S', OreDictUnifier.get(OrePrefix.stick, m), 'C', "craftingToolBendingCylinderSmall");
            }
            if (!OreDictUnifier.get(OrePrefix.valueOf("plateCurved"), m).isEmpty() && GAConfig.GT6.BendingCurvedPlates && GAConfig.GT6.BendingCylinders) {
                ModHandler.addShapedRecipe("curved_plate_" + m.toString(), OreDictUnifier.get(OrePrefix.valueOf("plateCurved"), m), "h", "P", "C", 'P', new UnificationEntry(OrePrefix.plate, m), 'C', "craftingToolBendingCylinder");
                ModHandler.addShapedRecipe("flatten_plate_" + m.toString(), OreDictUnifier.get(OrePrefix.plate, m), "h", "C", 'C', new UnificationEntry(OrePrefix.valueOf("plateCurved"), m));
                RecipeMaps.BENDER_RECIPES.recipeBuilder().EUt(24).duration((int) m.getMass()).input(OrePrefix.plate, m).circuitMeta(0).outputs(OreDictUnifier.get(OrePrefix.valueOf("plateCurved"), m)).buildAndRegister();
            }
            if (!OreDictUnifier.get(OrePrefix.rotor, m).isEmpty() && GAConfig.GT6.BendingRotors && GAConfig.GT6.BendingCylinders) {
                ModHandler.removeRecipes(OreDictUnifier.get(OrePrefix.rotor, m));
                ModHandler.addShapedRecipe("ga_rotor_" + m.toString(), OreDictUnifier.get(OrePrefix.rotor, m), "ChC", "SRf", "CdC", 'C', OreDictUnifier.get(OrePrefix.valueOf("plateCurved"), m), 'S', OreDictUnifier.get(OrePrefix.screw, m), 'R', OreDictUnifier.get(OrePrefix.ring, m));
                RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(240).EUt(24).inputs(OreDictUnifier.get(OrePrefix.valueOf("plateCurved"), m, 4), OreDictUnifier.get(OrePrefix.ring, m)).fluidInputs(Materials.SolderingAlloy.getFluid(32)).outputs(OreDictUnifier.get(OrePrefix.rotor, m)).buildAndRegister();
            }
            if (!OreDictUnifier.get(OrePrefix.foil, m).isEmpty()) {
                if (GAConfig.GT6.BendingFoils && GAConfig.GT6.BendingCylinders) {
                    ModHandler.addShapedRecipe("foil_" + m.toString(), OreDictUnifier.get(OrePrefix.foil, m, 2), "hPC", 'P', new UnificationEntry(OrePrefix.plate, m), 'C', "craftingToolBendingCylinder");
                }
                if (GAConfig.GT6.BendingFoilsAutomatic && GAConfig.GT6.BendingCylinders) {
                    GARecipeMaps.CLUSTER_MILL_RECIPES.recipeBuilder().EUt(24).duration((int) m.getMass()).input(OrePrefix.plate, m).outputs(OreDictUnifier.get(OrePrefix.foil, m, 4)).buildAndRegister();
                } else if (GAConfig.GT6.BendingFoilsAutomatic == false || GAConfig.GT6.BendingCylinders == false) {
                    RecipeMaps.BENDER_RECIPES.recipeBuilder().EUt(24).duration((int) m.getMass()).circuitMeta(4).input(OrePrefix.plate, m).outputs(OreDictUnifier.get(OrePrefix.foil, m, 4)).buildAndRegister();
                }
            }
            if ((!OreDictUnifier.get(OrePrefix.wireGtSingle, m).isEmpty() && !OreDictUnifier.get(OrePrefix.wireFine, m).isEmpty()) && GAConfig.GT5U.OldFineWireRecipes && GAConfig.GT6.BendingCylinders) {
                RecipeMaps.WIREMILL_RECIPES.recipeBuilder().EUt(9).duration(200).inputs(OreDictUnifier.get(OrePrefix.wireGtSingle, m)).outputs(OreDictUnifier.get(OrePrefix.wireFine, m, 4)).buildAndRegister();
            }

            if (!OreDictUnifier.get(OrePrefix.valueOf("round"), m).isEmpty()) {
                ModHandler.addShapedRecipe("round" + m.toString(), OreDictUnifier.get(OrePrefix.valueOf("round"), m), "fN", "N ", 'N', OreDictUnifier.get(OrePrefix.nugget, m));
                RecipeMaps.LATHE_RECIPES.recipeBuilder().EUt(8).duration((int) m.getMass()).inputs(OreDictUnifier.get(OrePrefix.nugget, m)).outputs(OreDictUnifier.get(OrePrefix.valueOf("round"), m)).buildAndRegister();
            }

            ModHandler.addShapedRecipe("plasma_pipe", OreDictUnifier.get(OrePrefix.pipeMedium, GAMaterials.Plasma), "ESE", "NTN", "ESE", 'E', "platePlastic", 'S', OreDictUnifier.get(OrePrefix.wireGtDouble, Tier.Superconductor), 'N', "plateNeodymiumMagnetic", 'T', OreDictUnifier.get(OrePrefix.pipeSmall, Materials.Titanium));

            if (GAConfig.GT6.BendingPipes && GAConfig.GT6.BendingCylinders) {
                ModHandler.removeRecipes(OreDictUnifier.get(OrePrefix.pipeSmall, Materials.Wood));
                ModHandler.removeRecipes(OreDictUnifier.get(OrePrefix.pipeMedium, Materials.Wood));
                ModHandler.addShapedRecipe("pipe_ga_wood", OreDictUnifier.get(OrePrefix.pipeMedium, Materials.Wood, 2), "PPP", "sCh", "PPP", 'P', "plankWood", 'C', "craftingToolBendingCylinder");
                ModHandler.addShapedRecipe("pipe_ga_large_wood", OreDictUnifier.get(OrePrefix.pipeLarge, Materials.Wood), "PhP", "PCP", "PsP", 'P', "plankWood", 'C', "craftingToolBendingCylinder");
                ModHandler.addShapedRecipe("pipe_ga_small_wood", OreDictUnifier.get(OrePrefix.pipeSmall, Materials.Wood, 6), "PsP", "PCP", "PhP", 'P', "plankWood", 'C', "craftingToolBendingCylinder");
            }

            //Cables
            if (m instanceof IngotMaterial && !OreDictUnifier.get(OrePrefix.cableGtSingle, m).isEmpty() && m != Materials.RedAlloy && m != Materials.Cobalt && m != Materials.Zinc && m != Materials.SolderingAlloy && m != Materials.Tin && m != Materials.Lead && GAConfig.GT5U.CablesGT5U) {
                for (MaterialStack stackFluid : cableFluids) {
                    IngotMaterial fluid = (IngotMaterial) stackFluid.material;
                    int multiplier = (int) stackFluid.amount;
                    if (m == Materials.Tungsten || m == Materials.Osmium || m == Materials.Platinum || m == Materials.TungstenSteel || m == Materials.Graphene || m == Materials.VanadiumGallium || m == Materials.HSSG || m == Materials.YttriumBariumCuprate || m == Materials.NiobiumTitanium || m == Materials.Naquadah || m == Materials.NiobiumTitanium || m == Materials.NaquadahEnriched || m == Materials.Duranium || m == Materials.NaquadahAlloy) {
                        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(OrePrefix.wireGtSingle, m), OreDictUnifier.get(OrePrefix.foil, m)).fluidInputs(fluid.getFluid(multiplier)).circuitMeta(24).outputs(OreDictUnifier.get(OrePrefix.cableGtSingle, m)).buildAndRegister();
                        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(OrePrefix.wireGtDouble, m), OreDictUnifier.get(OrePrefix.foil, m, 2)).fluidInputs(fluid.getFluid(multiplier * 2)).circuitMeta(24).outputs(OreDictUnifier.get(OrePrefix.cableGtDouble, m)).buildAndRegister();
                        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(OrePrefix.wireGtQuadruple, m), OreDictUnifier.get(OrePrefix.foil, m, 4)).fluidInputs(fluid.getFluid(multiplier * 4)).circuitMeta(24).outputs(OreDictUnifier.get(OrePrefix.cableGtQuadruple, m)).buildAndRegister();
                        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(OrePrefix.wireGtOctal, m), OreDictUnifier.get(OrePrefix.foil, m, 8)).fluidInputs(fluid.getFluid(multiplier * 8)).circuitMeta(24).outputs(OreDictUnifier.get(OrePrefix.cableGtOctal, m)).buildAndRegister();
                        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(OrePrefix.wireGtHex, m), OreDictUnifier.get(OrePrefix.foil, m, 16)).fluidInputs(fluid.getFluid(multiplier * 16)).circuitMeta(24).outputs(OreDictUnifier.get(OrePrefix.cableGtHex, m)).buildAndRegister();

                        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(OrePrefix.wireGtSingle, m), OreDictUnifier.get(OrePrefix.foil, GAMaterials.PolyphenyleneSulfide)).fluidInputs(fluid.getFluid(multiplier)).circuitMeta(24).outputs(OreDictUnifier.get(OrePrefix.cableGtSingle, m)).buildAndRegister();
                        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(OrePrefix.wireGtDouble, m), OreDictUnifier.get(OrePrefix.foil, GAMaterials.PolyphenyleneSulfide, 2)).fluidInputs(fluid.getFluid(multiplier * 2)).circuitMeta(24).outputs(OreDictUnifier.get(OrePrefix.cableGtDouble, m)).buildAndRegister();
                        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(OrePrefix.wireGtQuadruple, m), OreDictUnifier.get(OrePrefix.foil, GAMaterials.PolyphenyleneSulfide, 4)).fluidInputs(fluid.getFluid(multiplier * 4)).circuitMeta(24).outputs(OreDictUnifier.get(OrePrefix.cableGtQuadruple, m)).buildAndRegister();
                        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(OrePrefix.wireGtOctal, m), OreDictUnifier.get(OrePrefix.foil, GAMaterials.PolyphenyleneSulfide, 8)).fluidInputs(fluid.getFluid(multiplier * 8)).circuitMeta(24).outputs(OreDictUnifier.get(OrePrefix.cableGtOctal, m)).buildAndRegister();
                        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(OrePrefix.wireGtHex, m), OreDictUnifier.get(OrePrefix.foil, GAMaterials.PolyphenyleneSulfide, 16)).fluidInputs(fluid.getFluid(multiplier * 16)).circuitMeta(24).outputs(OreDictUnifier.get(OrePrefix.cableGtHex, m)).buildAndRegister();
                        for (MaterialStack stackDust : cableDusts) {
                            Material dust = stackDust.material;
                            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(OrePrefix.wireGtSingle, m), OreDictUnifier.get(OrePrefix.foil, m), OreDictUnifier.get(OrePrefix.dustSmall, dust)).fluidInputs(fluid.getFluid(multiplier / 2)).outputs(OreDictUnifier.get(OrePrefix.cableGtSingle, m)).buildAndRegister();
                            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(OrePrefix.wireGtDouble, m), OreDictUnifier.get(OrePrefix.foil, m, 2), OreDictUnifier.get(OrePrefix.dustSmall, dust, 2)).fluidInputs(fluid.getFluid(multiplier)).outputs(OreDictUnifier.get(OrePrefix.cableGtDouble, m)).buildAndRegister();
                            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(OrePrefix.wireGtQuadruple, m), OreDictUnifier.get(OrePrefix.foil, m, 4), OreDictUnifier.get(OrePrefix.dustSmall, dust, 4)).fluidInputs(fluid.getFluid(multiplier * 2)).outputs(OreDictUnifier.get(OrePrefix.cableGtQuadruple, m)).buildAndRegister();
                            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(OrePrefix.wireGtOctal, m), OreDictUnifier.get(OrePrefix.foil, m, 8), OreDictUnifier.get(OrePrefix.dustSmall, dust, 8)).fluidInputs(fluid.getFluid(multiplier * 4)).outputs(OreDictUnifier.get(OrePrefix.cableGtOctal, m)).buildAndRegister();
                            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(OrePrefix.wireGtHex, m), OreDictUnifier.get(OrePrefix.foil, m, 16), OreDictUnifier.get(OrePrefix.dustSmall, dust, 16)).fluidInputs(fluid.getFluid(multiplier * 8)).outputs(OreDictUnifier.get(OrePrefix.cableGtHex, m)).buildAndRegister();

                            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(OrePrefix.wireGtSingle, m), OreDictUnifier.get(OrePrefix.foil, GAMaterials.PolyphenyleneSulfide), OreDictUnifier.get(OrePrefix.dustSmall, dust)).fluidInputs(fluid.getFluid(multiplier / 2)).outputs(OreDictUnifier.get(OrePrefix.cableGtSingle, m)).buildAndRegister();
                            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(OrePrefix.wireGtDouble, m), OreDictUnifier.get(OrePrefix.foil, GAMaterials.PolyphenyleneSulfide, 2), OreDictUnifier.get(OrePrefix.dustSmall, dust, 2)).fluidInputs(fluid.getFluid(multiplier)).outputs(OreDictUnifier.get(OrePrefix.cableGtDouble, m)).buildAndRegister();
                            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(OrePrefix.wireGtQuadruple, m), OreDictUnifier.get(OrePrefix.foil, GAMaterials.PolyphenyleneSulfide, 4), OreDictUnifier.get(OrePrefix.dustSmall, dust, 4)).fluidInputs(fluid.getFluid(multiplier * 2)).outputs(OreDictUnifier.get(OrePrefix.cableGtQuadruple, m)).buildAndRegister();
                            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(OrePrefix.wireGtOctal, m), OreDictUnifier.get(OrePrefix.foil, GAMaterials.PolyphenyleneSulfide, 8), OreDictUnifier.get(OrePrefix.dustSmall, dust, 8)).fluidInputs(fluid.getFluid(multiplier * 4)).outputs(OreDictUnifier.get(OrePrefix.cableGtOctal, m)).buildAndRegister();
                            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(OrePrefix.wireGtHex, m), OreDictUnifier.get(OrePrefix.foil, GAMaterials.PolyphenyleneSulfide, 16), OreDictUnifier.get(OrePrefix.dustSmall, dust, 16)).fluidInputs(fluid.getFluid(multiplier * 8)).outputs(OreDictUnifier.get(OrePrefix.cableGtHex, m)).buildAndRegister();
                        }
                    } else {
                        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(OrePrefix.wireGtSingle, m)).fluidInputs(fluid.getFluid(multiplier)).circuitMeta(24).outputs(OreDictUnifier.get(OrePrefix.cableGtSingle, m)).buildAndRegister();
                        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(OrePrefix.wireGtDouble, m)).fluidInputs(fluid.getFluid(multiplier * 2)).circuitMeta(24).outputs(OreDictUnifier.get(OrePrefix.cableGtDouble, m)).buildAndRegister();
                        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(OrePrefix.wireGtQuadruple, m)).fluidInputs(fluid.getFluid(multiplier * 4)).circuitMeta(24).outputs(OreDictUnifier.get(OrePrefix.cableGtQuadruple, m)).buildAndRegister();
                        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(OrePrefix.wireGtOctal, m)).fluidInputs(fluid.getFluid(multiplier * 8)).circuitMeta(24).outputs(OreDictUnifier.get(OrePrefix.cableGtOctal, m)).buildAndRegister();
                        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(OrePrefix.wireGtHex, m)).fluidInputs(fluid.getFluid(multiplier * 16)).circuitMeta(24).outputs(OreDictUnifier.get(OrePrefix.cableGtHex, m)).buildAndRegister();
                        for (MaterialStack stackDust : cableDusts) {
                            Material dust = stackDust.material;
                            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(OrePrefix.wireGtSingle, m), OreDictUnifier.get(OrePrefix.dustSmall, dust)).fluidInputs(fluid.getFluid(multiplier / 2)).outputs(OreDictUnifier.get(OrePrefix.cableGtSingle, m)).buildAndRegister();
                            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(OrePrefix.wireGtSingle, m), OreDictUnifier.get(OrePrefix.dustSmall, dust)).fluidInputs(fluid.getFluid(multiplier / 2)).outputs(OreDictUnifier.get(OrePrefix.cableGtSingle, m)).buildAndRegister();
                            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(OrePrefix.wireGtDouble, m), OreDictUnifier.get(OrePrefix.dustSmall, dust, 2)).fluidInputs(fluid.getFluid(multiplier)).outputs(OreDictUnifier.get(OrePrefix.cableGtDouble, m)).buildAndRegister();
                            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(OrePrefix.wireGtQuadruple, m), OreDictUnifier.get(OrePrefix.dustSmall, dust, 4)).fluidInputs(fluid.getFluid(multiplier * 2)).outputs(OreDictUnifier.get(OrePrefix.cableGtQuadruple, m)).buildAndRegister();
                            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(OrePrefix.wireGtOctal, m), OreDictUnifier.get(OrePrefix.dustSmall, dust, 8)).fluidInputs(fluid.getFluid(multiplier * 4)).outputs(OreDictUnifier.get(OrePrefix.cableGtOctal, m)).buildAndRegister();
                            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(8).inputs(OreDictUnifier.get(OrePrefix.wireGtHex, m), OreDictUnifier.get(OrePrefix.dustSmall, dust, 16)).fluidInputs(fluid.getFluid(multiplier * 8)).outputs(OreDictUnifier.get(OrePrefix.cableGtHex, m)).buildAndRegister();
                        }
                    }
                }
            }

            //GT6 Plate Recipe
            if (m instanceof IngotMaterial && !OreDictUnifier.get(OrePrefix.plate, m).isEmpty() && !OreDictUnifier.get(OrePrefix.valueOf("ingotDouble"), m).isEmpty() && GAConfig.GT6.PlateDoubleIngot) {
                ModHandler.removeRecipes(OreDictUnifier.get(OrePrefix.plate, m));
                ModHandler.addShapedRecipe("ingot_double_" + m.toString(), OreDictUnifier.get(OrePrefix.valueOf("ingotDouble"), m), "h", "I", "I", 'I', new UnificationEntry(OrePrefix.ingot, m));
                ModHandler.addShapedRecipe("double_ingot_to_plate_" + m.toString(), OreDictUnifier.get(OrePrefix.plate, m), "h", "I", 'I', OreDictUnifier.get(OrePrefix.valueOf("ingotDouble"), m));
            }
        }

        //Pipes
        for (Material m : IngotMaterial.MATERIAL_REGISTRY) {
            if (!OreDictUnifier.get(OrePrefix.pipeMedium, m).isEmpty() && GAConfig.GT6.BendingPipes) {
                ModHandler.removeRecipeByName(new ResourceLocation("gregtech:small_" + m.toString() + "_pipe"));
                ModHandler.removeRecipeByName(new ResourceLocation("gregtech:medium_" + m.toString() + "_pipe"));
                ModHandler.removeRecipeByName(new ResourceLocation("gregtech:large_" + m.toString() + "_pipe"));
                if (!OreDictUnifier.get(OrePrefix.valueOf("plateCurved"), m).isEmpty()) {
                    ModHandler.addShapedRecipe("pipe_ga_" + m.toString(), OreDictUnifier.get(OrePrefix.pipeMedium, m, 2), "PPP", "wCh", "PPP", 'P', OreDictUnifier.get(OrePrefix.valueOf("plateCurved"), m), 'C', "craftingToolBendingCylinder");
                    ModHandler.addShapedRecipe("pipe_ga_large_" + m.toString(), OreDictUnifier.get(OrePrefix.pipeLarge, m), "PhP", "PCP", "PwP", 'P', OreDictUnifier.get(OrePrefix.valueOf("plateCurved"), m), 'C', "craftingToolBendingCylinder");
                    ModHandler.addShapedRecipe("pipe_ga_small_" + m.toString(), OreDictUnifier.get(OrePrefix.pipeSmall, m, 4), "PwP", "PCP", "PhP", 'P', OreDictUnifier.get(OrePrefix.valueOf("plateCurved"), m), 'C', "craftingToolBendingCylinder");
                }
            }
        }

        //Rubber Rings
        ModHandler.removeRecipes(OreDictUnifier.get(OrePrefix.ring, GAMaterials.SiliconeRubber));
        ModHandler.removeRecipes(OreDictUnifier.get(OrePrefix.ring, GAMaterials.StyreneButadieneRubber));
        ModHandler.addShapedRecipe("silicone_rubber_ring", OreDictUnifier.get(OrePrefix.ring, GAMaterials.SiliconeRubber), "k", "P", 'P', OreDictUnifier.get(OrePrefix.plate, GAMaterials.SiliconeRubber));
        ModHandler.addShapedRecipe("styrene_rubber_ring", OreDictUnifier.get(OrePrefix.ring, GAMaterials.StyreneButadieneRubber), "k", "P", 'P', OreDictUnifier.get(OrePrefix.plate, GAMaterials.StyreneButadieneRubber));

        //Rubbers
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(GAMaterials.Isoprene.getFluid(144), Materials.Air.getFluid(2000)).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.RawRubber)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(GAMaterials.Isoprene.getFluid(144), Materials.Oxygen.getFluid(2000)).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.RawRubber, 3)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(240).fluidInputs(GAMaterials.Butadiene.getFluid(108), GAMaterials.Styrene.getFluid(36), Materials.Air.getFluid(2000)).outputs(OreDictUnifier.get(OrePrefix.dust, GAMaterials.RawStyreneButadieneRubber)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(240).fluidInputs(GAMaterials.Butadiene.getFluid(108), GAMaterials.Styrene.getFluid(36), Materials.Oxygen.getFluid(2000)).outputs(OreDictUnifier.get(OrePrefix.dust, GAMaterials.RawStyreneButadieneRubber, 3)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(120).EUt(30).notConsumable(new IntCircuitIngredient(2)).fluidInputs(GAMaterials.Propene.getFluid(2000)).fluidOutputs(Materials.Methane.getFluid(1000), GAMaterials.Isoprene.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(3500).EUt(30).input(OrePrefix.dust, Materials.Carbon).notConsumable(new IntCircuitIngredient(0)).fluidInputs(Materials.Hydrogen.getFluid(4000)).fluidOutputs(Materials.Methane.getFluid(5000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(120).EUt(30).fluidInputs(GAMaterials.Ethylene.getFluid(1000), GAMaterials.Propene.getFluid(1000)).fluidOutputs(Materials.Hydrogen.getFluid(2000), GAMaterials.Isoprene.getFluid(1000)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(30).input(OrePrefix.dust, GAMaterials.RawStyreneButadieneRubber, 9).input(OrePrefix.dust, Materials.Sulfur).fluidOutputs(GAMaterials.StyreneButadieneRubber.getFluid(1296)).buildAndRegister();

        //Polyphenylene Process
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(60).EUt(30).input(OrePrefix.dust, Materials.Sodium, 2).input(OrePrefix.dust, Materials.Sulfur).outputs(OreDictUnifier.get(OrePrefix.dust, GAMaterials.GASodiumSulfide, 3)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(360).input(OrePrefix.dust, GAMaterials.GASodiumSulfide).fluidInputs(GAMaterials.Dichlorobenzene.getFluid(1000), Materials.Air.getFluid(16000)).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.Salt, 2)).fluidOutputs(GAMaterials.PolyphenyleneSulfide.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(360).input(OrePrefix.dust, GAMaterials.GASodiumSulfide).fluidInputs(GAMaterials.Dichlorobenzene.getFluid(1000), Materials.Oxygen.getFluid(8000)).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.Salt, 2)).fluidOutputs(GAMaterials.PolyphenyleneSulfide.getFluid(1500)).buildAndRegister();

        //Platinum Sludge
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(50).EUt(30).inputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Chalcopyrite)).fluidInputs(Materials.NitricAcid.getFluid(1000)).outputs(OreDictUnifier.get(OrePrefix.dustTiny, GAMaterials.PlatinumGroupSludge)).fluidOutputs(GAMaterials.BlueVitriolSolution.getFluid(9000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(50).EUt(30).inputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Pentlandite)).fluidInputs(Materials.NitricAcid.getFluid(1000)).outputs(OreDictUnifier.get(OrePrefix.dustTiny, GAMaterials.PlatinumGroupSludge)).fluidOutputs(GAMaterials.NickelSulfateSolution.getFluid(9000)).buildAndRegister();

        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(900).EUt(30).input(OrePrefix.dust, GAMaterials.PlatinumGroupSludge).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.SiliconDioxide), OreDictUnifier.get(OrePrefix.dustTiny, Materials.Gold), OreDictUnifier.get(OrePrefix.dustTiny, Materials.Platinum)).chancedOutput(OreDictUnifier.get(OrePrefix.dustTiny, Materials.Palladium), 8000).chancedOutput(OreDictUnifier.get(OrePrefix.dustTiny, Materials.Iridium), 6000).chancedOutput(OreDictUnifier.get(OrePrefix.dustTiny, Materials.Osmium), 6000).buildAndRegister();

        //Ultimate Pipes
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(300).EUt(96).inputs(OreDictUnifier.get(OrePrefix.pipeSmall, Materials.TungstenSteel), MetaItems.ELECTRIC_PUMP_EV.getStackForm()).outputs(OreDictUnifier.get(OrePrefix.pipeSmall, GAMaterials.Ultimate)).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(148).inputs(OreDictUnifier.get(OrePrefix.pipeMedium, Materials.TungstenSteel), MetaItems.ELECTRIC_PUMP_IV.getStackForm()).outputs(OreDictUnifier.get(OrePrefix.pipeMedium, GAMaterials.Ultimate)).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(256).inputs(OreDictUnifier.get(OrePrefix.pipeLarge, Materials.TungstenSteel), MetaItems.ELECTRIC_PUMP_IV.getStackForm(2)).outputs(OreDictUnifier.get(OrePrefix.pipeLarge, GAMaterials.Ultimate)).buildAndRegister();

        //Reinforced Glass
        int multiplier2;
        for (MaterialStack metal1 : firstMetal) {
            IngotMaterial material1 = (IngotMaterial) metal1.material;
            int multiplier1 = (int) metal1.amount;
            for (MaterialStack metal2 : lastMetal) {
                IngotMaterial material2 = (IngotMaterial) metal2.material;
                if ((int) metal1.amount == 1)
                    multiplier2 = 0;
                else
                    multiplier2 = (int) metal2.amount;
                ModHandler.addShapedRecipe("mixed_metal_1_" + material1.toString() + "_" + material2.toString(), GAMetaItems.MIXED_METAL_PLATE.getStackForm(multiplier1 + multiplier2), "F", "M", "L", 'F', new UnificationEntry(OrePrefix.plate, material1), 'M', "plateBronze", 'L', OreDictUnifier.get(OrePrefix.plate, material2));
                ModHandler.addShapedRecipe("mixed_metal_2_" + material1.toString() + "_" + material2.toString(), GAMetaItems.MIXED_METAL_PLATE.getStackForm(multiplier1 + multiplier2), "F", "M", "L", 'F', new UnificationEntry(OrePrefix.plate, material1), 'M', "plateBrass", 'L', OreDictUnifier.get(OrePrefix.plate, material2));

                RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(40 * multiplier1 + multiplier2 * 40).EUt(8).input(OrePrefix.plate, material1).input(OrePrefix.plank, Materials.Bronze).input(OrePrefix.plate, material2).outputs(GAMetaItems.MIXED_METAL_PLATE.getStackForm(multiplier1 + multiplier2)).buildAndRegister();
                RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(40 * multiplier1 + multiplier2 * 40).EUt(8).input(OrePrefix.plate, material1).input(OrePrefix.plate, Materials.Brass).input(OrePrefix.plate, material2).outputs(GAMetaItems.MIXED_METAL_PLATE.getStackForm(multiplier1 + multiplier2)).buildAndRegister();
            }
        }

        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2).inputs(GAMetaItems.MIXED_METAL_PLATE.getStackForm()).outputs(GAMetaItems.ADVANCED_ALLOY_PLATE.getStackForm()).buildAndRegister();

        RecipeMaps.ALLOY_SMELTER_RECIPES.recipeBuilder().duration(400).EUt(4).inputs(GAMetaItems.ADVANCED_ALLOY_PLATE.getStackForm()).input(OrePrefix.dust, Materials.Glass, 3).outputs(GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(GATransparentCasing.CasingType.REINFORCED_GLASS, 4)).buildAndRegister();
        RecipeMaps.ALLOY_SMELTER_RECIPES.recipeBuilder().duration(400).EUt(4).inputs(GAMetaItems.ADVANCED_ALLOY_PLATE.getStackForm(), new ItemStack(Blocks.GLASS, 3)).outputs(GAMetaBlocks.TRANSPARENT_CASING.getItemVariant(GATransparentCasing.CasingType.REINFORCED_GLASS, 4)).buildAndRegister();

        //Iridium Alloy
        ModHandler.addShapedRecipe("iridium_alloy_plate", GAMetaItems.PLATE_IRIDIUM_ALLOY_UNCOMPRESSED.getStackForm(), "AIA", "IDI", "AIA", 'A', GAMetaItems.ADVANCED_ALLOY_PLATE.getStackForm(), 'I', "plateIridium", 'D', "plateDiamond");
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(40).EUt(8).inputs(GAMetaItems.ADVANCED_ALLOY_PLATE.getStackForm(4)).input(OrePrefix.plate, Materials.Iridium, 4).input(OrePrefix.plate, Materials.Diamond).outputs(GAMetaItems.PLATE_IRIDIUM_ALLOY_UNCOMPRESSED.getStackForm()).buildAndRegister();

        RecipeMaps.IMPLOSION_RECIPES.recipeBuilder().duration(20).EUt(30).inputs(GAMetaItems.PLATE_IRIDIUM_ALLOY_UNCOMPRESSED.getStackForm()).explosivesAmount(8).outputs(GAMetaItems.PLATE_IRIDIUM_ALLOY.getStackForm()).buildAndRegister();

        //Machine Components
        ModHandler.removeRecipes(MetaItems.EMITTER_LV.getStackForm());
        ModHandler.removeRecipes(MetaItems.EMITTER_MV.getStackForm());
        ModHandler.removeRecipes(MetaItems.EMITTER_HV.getStackForm());
        ModHandler.removeRecipes(MetaItems.EMITTER_EV.getStackForm());
        ModHandler.removeRecipes(MetaItems.EMITTER_IV.getStackForm());

        ModHandler.removeRecipes(MetaItems.SENSOR_LV.getStackForm());
        ModHandler.removeRecipes(MetaItems.SENSOR_MV.getStackForm());
        ModHandler.removeRecipes(MetaItems.SENSOR_HV.getStackForm());
        ModHandler.removeRecipes(MetaItems.SENSOR_EV.getStackForm());
        ModHandler.removeRecipes(MetaItems.SENSOR_IV.getStackForm());

        ModHandler.removeRecipes(MetaItems.ROBOT_ARM_LV.getStackForm());
        ModHandler.removeRecipes(MetaItems.ROBOT_ARM_MV.getStackForm());
        ModHandler.removeRecipes(MetaItems.ROBOT_ARM_HV.getStackForm());
        ModHandler.removeRecipes(MetaItems.ROBOT_ARM_EV.getStackForm());
        ModHandler.removeRecipes(MetaItems.ROBOT_ARM_IV.getStackForm());

        ModHandler.removeRecipes(MetaItems.FIELD_GENERATOR_LV.getStackForm());
        ModHandler.removeRecipes(MetaItems.FIELD_GENERATOR_MV.getStackForm());
        ModHandler.removeRecipes(MetaItems.FIELD_GENERATOR_HV.getStackForm());
        ModHandler.removeRecipes(MetaItems.FIELD_GENERATOR_EV.getStackForm());
        ModHandler.removeRecipes(MetaItems.FIELD_GENERATOR_IV.getStackForm());

        ModHandler.removeRecipes(MetaItems.ELECTRIC_PUMP_LV.getStackForm());
        ModHandler.removeRecipes(MetaItems.ELECTRIC_PUMP_MV.getStackForm());
        ModHandler.removeRecipes(MetaItems.ELECTRIC_PUMP_HV.getStackForm());
        ModHandler.removeRecipes(MetaItems.ELECTRIC_PUMP_EV.getStackForm());
        ModHandler.removeRecipes(MetaItems.ELECTRIC_PUMP_IV.getStackForm());

        ModHandler.addShapedRecipe("ga_lv_emitter", MetaItems.EMITTER_LV.getStackForm(), "RRS", "CGR", "SCR", 'R', OreDictUnifier.get(OrePrefix.stick, Materials.Brass), 'S', "circuitGABasic", 'C', OreDictUnifier.get(OrePrefix.cableGtSingle, Materials.Tin), 'G', OreDictUnifier.get(OrePrefix.gem, Materials.Quartzite));
        ModHandler.addShapedRecipe("ga_mv_emitter", MetaItems.EMITTER_MV.getStackForm(), "RRS", "CGR", "SCR", 'R', OreDictUnifier.get(OrePrefix.stick, Materials.Electrum), 'S', "circuitGAGood", 'C', OreDictUnifier.get(OrePrefix.cableGtSingle, Materials.Copper), 'G', OreDictUnifier.get(OrePrefix.gem, Materials.NetherQuartz));
        ModHandler.addShapedRecipe("ga_hv_emitter", MetaItems.EMITTER_HV.getStackForm(), "RRS", "CGR", "SCR", 'R', OreDictUnifier.get(OrePrefix.stick, Materials.Chrome), 'S', "circuitGAAdvanced", 'C', OreDictUnifier.get(OrePrefix.cableGtSingle, Materials.Gold), 'G', OreDictUnifier.get(OrePrefix.gem, Materials.Emerald));
        ModHandler.addShapedRecipe("ga_ev_emitter", MetaItems.EMITTER_EV.getStackForm(), "RRS", "CGR", "SCR", 'R', OreDictUnifier.get(OrePrefix.stick, Materials.Platinum), 'S', "circuitGAExtreme", 'C', OreDictUnifier.get(OrePrefix.cableGtSingle, Materials.Aluminium), 'G', OreDictUnifier.get(OrePrefix.gem, Materials.EnderPearl));
        ModHandler.addShapedRecipe("ga_iv_emitter", MetaItems.EMITTER_IV.getStackForm(), "RRS", "CGR", "SCR", 'R', OreDictUnifier.get(OrePrefix.stick, Materials.Osmium), 'S', "circuitGAElite", 'C', OreDictUnifier.get(OrePrefix.cableGtSingle, Materials.Tungsten), 'G', OreDictUnifier.get(OrePrefix.gem, Materials.EnderEye));

        ModHandler.addShapedRecipe("ga_lv_sensor", MetaItems.SENSOR_LV.getStackForm(), "P G", "PR ", "SPP", 'P', "plateSteel", 'G', "gemQuartzite", 'R', "stickBrass", 'S', "circuitGABasic");
        ModHandler.addShapedRecipe("ga_mv_sensor", MetaItems.SENSOR_MV.getStackForm(), "P G", "PR ", "SPP", 'P', "plateAluminium", 'G', "gemNetherQuartz", 'R', "stickElectrum", 'S', "circuitGAGood");
        ModHandler.addShapedRecipe("ga_hv_sensor", MetaItems.SENSOR_HV.getStackForm(), "P G", "PR ", "SPP", 'P', "plateStainlessSteel", 'G', "gemEmerald", 'R', "stickChrome", 'S', "circuitGAAdvanced");
        ModHandler.addShapedRecipe("ga_ev_sensor", MetaItems.SENSOR_EV.getStackForm(), "P G", "PR ", "SPP", 'P', "plateTitanium", 'G', "gemEnderPearl", 'R', "stickPlatinum", 'S', "circuitGAExtreme");
        ModHandler.addShapedRecipe("ga_iv_sensor", MetaItems.SENSOR_IV.getStackForm(), "P G", "PR ", "SPP", 'P', "plateTungstenSteel", 'G', "gemEnderEye", 'R', "stickOsmium", 'S', "circuitGAElite");

        ModHandler.addShapedRecipe("ga_lv_robot_arm", MetaItems.ROBOT_ARM_LV.getStackForm(), "CCC", "MRM", "PSR", 'C', OreDictUnifier.get(OrePrefix.cableGtSingle, Materials.Tin), 'M', MetaItems.ELECTRIC_MOTOR_LV.getStackForm(), 'R', OreDictUnifier.get(OrePrefix.stick, Materials.Steel), 'P', MetaItems.ELECTRIC_PISTON_LV.getStackForm(), 'S', "circuitGABasic");
        ModHandler.addShapedRecipe("ga_mv_robot_arm", MetaItems.ROBOT_ARM_MV.getStackForm(), "CCC", "MRM", "PSR", 'C', OreDictUnifier.get(OrePrefix.cableGtSingle, Materials.Copper), 'M', MetaItems.ELECTRIC_MOTOR_MV.getStackForm(), 'R', OreDictUnifier.get(OrePrefix.stick, Materials.Aluminium), 'P', MetaItems.ELECTRIC_PISTON_MV.getStackForm(), 'S', "circuitGAGood");
        ModHandler.addShapedRecipe("ga_hv_robot_arm", MetaItems.ROBOT_ARM_HV.getStackForm(), "CCC", "MRM", "PSR", 'C', OreDictUnifier.get(OrePrefix.cableGtSingle, Materials.Gold), 'M', MetaItems.ELECTRIC_MOTOR_HV.getStackForm(), 'R', OreDictUnifier.get(OrePrefix.stick, Materials.StainlessSteel), 'P', MetaItems.ELECTRIC_PISTON_HV.getStackForm(), 'S', "circuitGAAdvanced");
        ModHandler.addShapedRecipe("ga_ev_robot_arm", MetaItems.ROBOT_ARM_EV.getStackForm(), "CCC", "MRM", "PSR", 'C', OreDictUnifier.get(OrePrefix.cableGtSingle, Materials.Aluminium), 'M', MetaItems.ELECTRIC_MOTOR_EV.getStackForm(), 'R', OreDictUnifier.get(OrePrefix.stick, Materials.Titanium), 'P', MetaItems.ELECTRIC_PISTON_EV.getStackForm(), 'S', "circuitGAExtreme");
        ModHandler.addShapedRecipe("ga_iv_robot_arm", MetaItems.ROBOT_ARM_IV.getStackForm(), "CCC", "MRM", "PSR", 'C', OreDictUnifier.get(OrePrefix.cableGtSingle, Materials.Tungsten), 'M', MetaItems.ELECTRIC_MOTOR_IV.getStackForm(), 'R', OreDictUnifier.get(OrePrefix.stick, Materials.TungstenSteel), 'P', MetaItems.ELECTRIC_PISTON_IV.getStackForm(), 'S', "circuitGAElite");

        ModHandler.addShapedRecipe("ga_lv_field_generator", MetaItems.FIELD_GENERATOR_LV.getStackForm(), "WSW", "SGS", "WSW", 'W', OreDictUnifier.get(OrePrefix.wireGtSingle, Materials.Osmium), 'S', "circuitGABasic", 'G', OreDictUnifier.get(OrePrefix.gem, Materials.EnderPearl));
        ModHandler.addShapedRecipe("ga_mv_field_generator", MetaItems.FIELD_GENERATOR_MV.getStackForm(), "WSW", "SGS", "WSW", 'W', OreDictUnifier.get(OrePrefix.wireGtDouble, Materials.Osmium), 'S', "circuitGAGood", 'G', OreDictUnifier.get(OrePrefix.gem, Materials.EnderEye));
        ModHandler.addShapedRecipe("ga_hv_field_generator", MetaItems.FIELD_GENERATOR_HV.getStackForm(), "WSW", "SGS", "WSW", 'W', OreDictUnifier.get(OrePrefix.wireGtQuadruple, Materials.Osmium), 'S', "circuitGAAdvanced", 'G', MetaItems.QUANTUM_EYE.getStackForm());
        ModHandler.addShapedRecipe("ga_ev_field_generator", MetaItems.FIELD_GENERATOR_EV.getStackForm(), "WSW", "SGS", "WSW", 'W', OreDictUnifier.get(OrePrefix.wireGtOctal, Materials.Osmium), 'S', "circuitGAExtreme", 'G', OreDictUnifier.get(OrePrefix.gem, Materials.NetherStar));
        ModHandler.addShapedRecipe("iga_v_field_generator", MetaItems.FIELD_GENERATOR_IV.getStackForm(), "WSW", "SGS", "WSW", 'W', OreDictUnifier.get(OrePrefix.wireGtHex, Materials.Osmium), 'S', "circuitGAElite", 'G', MetaItems.QUANTUM_STAR.getStackForm());

        ModHandler.addShapedRecipe("lv_electric_pump_paper", MetaItems.ELECTRIC_PUMP_LV.getStackForm(), "SRH", "dPw", "HMC", 'S', OreDictUnifier.get(OrePrefix.screw, Materials.Tin), 'R', OreDictUnifier.get(OrePrefix.rotor, Materials.Tin), 'H', OreDictUnifier.get(OrePrefix.ring, Materials.Paper), 'P', OreDictUnifier.get(OrePrefix.pipeMedium, Materials.Bronze), 'M', MetaItems.ELECTRIC_MOTOR_LV.getStackForm(), 'C', OreDictUnifier.get(OrePrefix.cableGtSingle, Materials.Tin));
        for (MaterialStack stackFluid : cableFluids) {
            IngotMaterial m = (IngotMaterial) stackFluid.material;
            ModHandler.addShapedRecipe("lv_electric_pump_" + m.toString(), MetaItems.ELECTRIC_PUMP_LV.getStackForm(), "SRH", "dPw", "HMC", 'S', OreDictUnifier.get(OrePrefix.screw, Materials.Tin), 'R', OreDictUnifier.get(OrePrefix.rotor, Materials.Tin), 'H', OreDictUnifier.get(OrePrefix.ring, m), 'P', OreDictUnifier.get(OrePrefix.pipeMedium, Materials.Bronze), 'M', MetaItems.ELECTRIC_MOTOR_LV.getStackForm(), 'C', OreDictUnifier.get(OrePrefix.cableGtSingle, Materials.Tin));
            ModHandler.addShapedRecipe("mv_electric_pump_" + m.toString(), MetaItems.ELECTRIC_PUMP_MV.getStackForm(), "SRH", "dPw", "HMC", 'S', OreDictUnifier.get(OrePrefix.screw, Materials.Bronze), 'R', OreDictUnifier.get(OrePrefix.rotor, Materials.Bronze), 'H', OreDictUnifier.get(OrePrefix.ring, m), 'P', OreDictUnifier.get(OrePrefix.pipeMedium, Materials.Steel), 'M', MetaItems.ELECTRIC_MOTOR_MV.getStackForm(), 'C', OreDictUnifier.get(OrePrefix.cableGtSingle, Materials.Copper));
            ModHandler.addShapedRecipe("hv_electric_pump_" + m.toString(), MetaItems.ELECTRIC_PUMP_HV.getStackForm(), "SRH", "dPw", "HMC", 'S', OreDictUnifier.get(OrePrefix.screw, Materials.Steel), 'R', OreDictUnifier.get(OrePrefix.rotor, Materials.Steel), 'H', OreDictUnifier.get(OrePrefix.ring, m), 'P', OreDictUnifier.get(OrePrefix.pipeMedium, Materials.StainlessSteel), 'M', MetaItems.ELECTRIC_MOTOR_HV.getStackForm(), 'C', OreDictUnifier.get(OrePrefix.cableGtSingle, Materials.Gold));
            ModHandler.addShapedRecipe("ev_electric_pump_" + m.toString(), MetaItems.ELECTRIC_PUMP_EV.getStackForm(), "SRH", "dPw", "HMC", 'S', OreDictUnifier.get(OrePrefix.screw, Materials.StainlessSteel), 'R', OreDictUnifier.get(OrePrefix.rotor, Materials.StainlessSteel), 'H', OreDictUnifier.get(OrePrefix.ring, m), 'P', OreDictUnifier.get(OrePrefix.pipeMedium, Materials.Titanium), 'M', MetaItems.ELECTRIC_MOTOR_EV.getStackForm(), 'C', OreDictUnifier.get(OrePrefix.cableGtSingle, Materials.Aluminium));
            ModHandler.addShapedRecipe("iv_electric_pump_" + m.toString(), MetaItems.ELECTRIC_PUMP_IV.getStackForm(), "SRH", "dPw", "HMC", 'S', OreDictUnifier.get(OrePrefix.screw, Materials.TungstenSteel), 'R', OreDictUnifier.get(OrePrefix.rotor, Materials.TungstenSteel), 'H', OreDictUnifier.get(OrePrefix.ring, m), 'P', OreDictUnifier.get(OrePrefix.pipeMedium, Materials.TungstenSteel), 'M', MetaItems.ELECTRIC_MOTOR_IV.getStackForm(), 'C', OreDictUnifier.get(OrePrefix.cableGtSingle, Materials.Tungsten));
        }

        //Automatic Machine Component Recipes
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(800).EUt(4).inputs(CountableIngredient.from(OrePrefix.valueOf("circuitGA"), Tier.Basic, 2), CountableIngredient.from(MetaItems.ELECTRIC_PUMP_LV.getStackForm())).outputs(MetaItems.FLUID_REGULATOR_LV.getStackForm()).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(800).EUt(8).inputs(CountableIngredient.from(OrePrefix.valueOf("circuitGA"), Tier.Good, 2), CountableIngredient.from(MetaItems.ELECTRIC_PUMP_MV.getStackForm())).outputs(MetaItems.FLUID_REGULATOR_MV.getStackForm()).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(800).EUt(16).inputs(CountableIngredient.from(OrePrefix.valueOf("circuitGA"), Tier.Advanced, 2), CountableIngredient.from(MetaItems.ELECTRIC_PUMP_HV.getStackForm())).outputs(MetaItems.FLUID_REGULATOR_HV.getStackForm()).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(800).EUt(32).inputs(CountableIngredient.from(OrePrefix.valueOf("circuitGA"), GAMaterials.Extreme, 2), CountableIngredient.from(MetaItems.ELECTRIC_PUMP_EV.getStackForm())).outputs(MetaItems.FLUID_REGULATOR_EV.getStackForm()).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(800).EUt(64).inputs(CountableIngredient.from(OrePrefix.valueOf("circuitGA"), Tier.Elite, 2), CountableIngredient.from(MetaItems.ELECTRIC_PUMP_IV.getStackForm())).outputs(MetaItems.FLUID_REGULATOR_IV.getStackForm()).buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(30).inputs(CountableIngredient.from(OrePrefix.valueOf("circuitGA"), Tier.Basic, 4), CountableIngredient.from(OrePrefix.dust, Materials.EnderPearl)).fluidInputs(Materials.Osmium.getFluid(288)).outputs(MetaItems.FIELD_GENERATOR_LV.getStackForm()).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(120).inputs(CountableIngredient.from(OrePrefix.valueOf("circuitGA"), Tier.Good, 4), CountableIngredient.from(OrePrefix.dust, Materials.EnderEye)).fluidInputs(Materials.Osmium.getFluid(576)).outputs(MetaItems.FIELD_GENERATOR_MV.getStackForm()).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(480).inputs(CountableIngredient.from(OrePrefix.valueOf("circuitGA"), Tier.Advanced, 4), CountableIngredient.from(MetaItems.QUANTUM_EYE.getStackForm())).fluidInputs(Materials.Osmium.getFluid(1152)).outputs(MetaItems.FIELD_GENERATOR_HV.getStackForm()).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(1920).inputs(CountableIngredient.from(OrePrefix.valueOf("circuitGA"), GAMaterials.Extreme, 4), CountableIngredient.from(OrePrefix.dust, Materials.NetherStar)).fluidInputs(Materials.Osmium.getFluid(2304)).outputs(MetaItems.FIELD_GENERATOR_EV.getStackForm()).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(7680).inputs(CountableIngredient.from(OrePrefix.valueOf("circuitGA"), Tier.Elite, 4), CountableIngredient.from(MetaItems.QUANTUM_STAR.getStackForm())).fluidInputs(Materials.Osmium.getFluid(4608)).outputs(MetaItems.FIELD_GENERATOR_IV.getStackForm()).buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(10).inputs(CountableIngredient.from(OrePrefix.cableGtSingle, Materials.Tin, 2), CountableIngredient.from(OrePrefix.stick, Materials.Iron, 2), CountableIngredient.from(OrePrefix.stick, Materials.IronMagnetic)).fluidInputs(Materials.Copper.getFluid(288)).outputs(MetaItems.ELECTRIC_MOTOR_LV.getStackForm()).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(10).inputs(CountableIngredient.from(OrePrefix.cableGtSingle, Materials.Tin, 2), CountableIngredient.from(OrePrefix.stick, Materials.Steel, 2), CountableIngredient.from(OrePrefix.stick, Materials.SteelMagnetic)).fluidInputs(Materials.Copper.getFluid(288)).outputs(MetaItems.ELECTRIC_MOTOR_LV.getStackForm()).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(40).inputs(CountableIngredient.from(OrePrefix.cableGtSingle, Materials.Copper, 2), CountableIngredient.from(OrePrefix.stick, Materials.Aluminium, 2), CountableIngredient.from(OrePrefix.stick, Materials.SteelMagnetic)).fluidInputs(Materials.Copper.getFluid(576)).outputs(MetaItems.ELECTRIC_MOTOR_MV.getStackForm()).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(160).inputs(CountableIngredient.from(OrePrefix.cableGtSingle, Materials.Gold, 2), CountableIngredient.from(OrePrefix.stick, Materials.StainlessSteel, 2), CountableIngredient.from(OrePrefix.stick, Materials.SteelMagnetic)).fluidInputs(Materials.Copper.getFluid(1152)).outputs(MetaItems.ELECTRIC_MOTOR_HV.getStackForm()).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(640).inputs(CountableIngredient.from(OrePrefix.cableGtSingle, Materials.Aluminium, 2), CountableIngredient.from(OrePrefix.stick, Materials.Titanium, 2), CountableIngredient.from(OrePrefix.stick, Materials.NeodymiumMagnetic)).fluidInputs(Materials.AnnealedCopper.getFluid(2304)).outputs(MetaItems.ELECTRIC_MOTOR_EV.getStackForm()).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(2560).inputs(CountableIngredient.from(OrePrefix.cableGtSingle, Materials.Tungsten, 2), CountableIngredient.from(OrePrefix.stick, Materials.TungstenSteel, 2), CountableIngredient.from(OrePrefix.stick, Materials.NeodymiumMagnetic)).fluidInputs(Materials.AnnealedCopper.getFluid(4608)).outputs(MetaItems.ELECTRIC_MOTOR_IV.getStackForm()).buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(15).inputs(OreDictUnifier.get(OrePrefix.cableGtSingle, Materials.Tin), MetaItems.ELECTRIC_MOTOR_LV.getStackForm(2)).fluidInputs(Materials.Rubber.getFluid(864)).outputs(MetaItems.CONVEYOR_MODULE_LV.getStackForm()).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(60).inputs(OreDictUnifier.get(OrePrefix.cableGtSingle, Materials.Copper), MetaItems.ELECTRIC_MOTOR_MV.getStackForm(2)).fluidInputs(Materials.Rubber.getFluid(864)).outputs(MetaItems.CONVEYOR_MODULE_MV.getStackForm()).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(240).inputs(OreDictUnifier.get(OrePrefix.cableGtSingle, Materials.Gold), MetaItems.ELECTRIC_MOTOR_HV.getStackForm(2)).fluidInputs(Materials.Rubber.getFluid(864)).outputs(MetaItems.CONVEYOR_MODULE_HV.getStackForm()).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(960).inputs(OreDictUnifier.get(OrePrefix.cableGtSingle, Materials.Aluminium), MetaItems.ELECTRIC_MOTOR_EV.getStackForm(2)).fluidInputs(Materials.Rubber.getFluid(864)).outputs(MetaItems.CONVEYOR_MODULE_EV.getStackForm()).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(3840).inputs(OreDictUnifier.get(OrePrefix.cableGtSingle, Materials.Tungsten), MetaItems.ELECTRIC_MOTOR_IV.getStackForm(2)).fluidInputs(Materials.Rubber.getFluid(864)).outputs(MetaItems.CONVEYOR_MODULE_IV.getStackForm()).buildAndRegister();

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(15).input(OrePrefix.valueOf("circuitGA"), Tier.Basic, 2).inputs(OreDictUnifier.get(OrePrefix.cableGtSingle, Materials.Tin, 2), OreDictUnifier.get(OrePrefix.gem, Materials.Quartzite)).fluidInputs(Materials.Brass.getFluid(576)).outputs(MetaItems.EMITTER_LV.getStackForm()).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(60).input(OrePrefix.valueOf("circuitGA"), Tier.Good, 2).inputs(OreDictUnifier.get(OrePrefix.cableGtSingle, Materials.Copper, 2), OreDictUnifier.get(OrePrefix.gem, Materials.NetherQuartz)).fluidInputs(Materials.Electrum.getFluid(576)).outputs(MetaItems.EMITTER_MV.getStackForm()).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(240).input(OrePrefix.valueOf("circuitGA"), Tier.Advanced, 2).inputs(OreDictUnifier.get(OrePrefix.cableGtSingle, Materials.Gold, 2), OreDictUnifier.get(OrePrefix.gem, Materials.Emerald)).fluidInputs(Materials.Chrome.getFluid(576)).outputs(MetaItems.EMITTER_HV.getStackForm()).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(960).input(OrePrefix.valueOf("circuitGA"), GAMaterials.Extreme, 2).inputs(OreDictUnifier.get(OrePrefix.cableGtSingle, Materials.Aluminium, 2), OreDictUnifier.get(OrePrefix.gem, Materials.EnderPearl)).fluidInputs(Materials.Platinum.getFluid(576)).outputs(MetaItems.EMITTER_EV.getStackForm()).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(600).EUt(3840).input(OrePrefix.valueOf("circuitGA"), Tier.Elite, 2).inputs(OreDictUnifier.get(OrePrefix.cableGtSingle, Materials.Tungsten, 2), OreDictUnifier.get(OrePrefix.gem, Materials.EnderEye)).fluidInputs(Materials.Osmium.getFluid(576)).outputs(MetaItems.EMITTER_IV.getStackForm()).buildAndRegister();

        //Coke Oven Recipes
        GARecipeMaps.COKE_OVEN_RECIPES.recipeBuilder().duration(1800).input(OrePrefix.gem, Materials.Coal).outputs(OreDictUnifier.get(OrePrefix.gem, GAMaterials.Coke)).fluidOutputs(Materials.Creosote.getFluid(500)).buildAndRegister();
        GARecipeMaps.COKE_OVEN_RECIPES.recipeBuilder().duration(1800).input(OrePrefix.gem, Materials.Lignite).outputs(OreDictUnifier.get(OrePrefix.gem, GAMaterials.LigniteCoke)).fluidOutputs(Materials.Creosote.getFluid(500)).buildAndRegister();
        GARecipeMaps.COKE_OVEN_RECIPES.recipeBuilder().duration(1800).input(OrePrefix.log, Materials.Wood).outputs(new ItemStack(Items.COAL, 1, 1)).fluidOutputs(Materials.Creosote.getFluid(500)).buildAndRegister();

        //Pyrolise Oven Recipes
        RecipeMaps.PYROLYSE_RECIPES.recipeBuilder()
                .input(OrePrefix.log, Materials.Wood, 16)
                .circuitMeta(0)
                .outputs(new ItemStack(Items.COAL, 20, 1))
                .fluidOutputs(Materials.Creosote.getFluid(4000))
                .duration(440)
                .EUt(64)
                .buildAndRegister();

        RecipeMaps.PYROLYSE_RECIPES.recipeBuilder()
                .input(OrePrefix.log, Materials.Wood, 16)
                .circuitMeta(1)
                .fluidInputs(Materials.Nitrogen.getFluid(400))
                .outputs(new ItemStack(Items.COAL, 20, 1))
                .fluidOutputs(Materials.Creosote.getFluid(4000))
                .duration(200)
                .EUt(96)
                .buildAndRegister();

        RecipeMaps.PYROLYSE_RECIPES.recipeBuilder()
                .input(OrePrefix.log, Materials.Wood, 16)
                .circuitMeta(2)
                .outputs(OreDictUnifier.get(OrePrefix.dust, Materials.Ash, 4))
                .fluidOutputs(Materials.OilHeavy.getFluid(200))
                .duration(280)
                .EUt(192)
                .buildAndRegister();

        RecipeMaps.PYROLYSE_RECIPES.recipeBuilder()
                .input(OrePrefix.log, Materials.Wood, 16)
                .circuitMeta(3)
                .outputs(new ItemStack(Items.COAL, 20, 1))
                .fluidOutputs(GAMaterials.WoodVinegar.getFluid(3000))
                .duration(640)
                .EUt(64)
                .buildAndRegister();

        RecipeMaps.PYROLYSE_RECIPES.recipeBuilder()
                .input(OrePrefix.log, Materials.Wood, 16)
                .circuitMeta(4)
                .fluidInputs(Materials.Nitrogen.getFluid(400))
                .outputs(new ItemStack(Items.COAL, 20, 1))
                .fluidOutputs(GAMaterials.WoodVinegar.getFluid(3000))
                .duration(320)
                .EUt(96)
                .buildAndRegister();

        RecipeMaps.PYROLYSE_RECIPES.recipeBuilder()
                .input(OrePrefix.log, Materials.Wood, 16)
                .circuitMeta(5)
                .outputs(new ItemStack(Items.COAL, 20, 1))
                .fluidOutputs(GAMaterials.WoodGas.getFluid(1500))
                .duration(640)
                .EUt(64)
                .buildAndRegister();

        RecipeMaps.PYROLYSE_RECIPES.recipeBuilder()
                .input(OrePrefix.log, Materials.Wood, 16)
                .circuitMeta(6)
                .fluidInputs(Materials.Nitrogen.getFluid(400))
                .outputs(new ItemStack(Items.COAL, 20, 1))
                .fluidOutputs(GAMaterials.WoodGas.getFluid(1500))
                .duration(320)
                .EUt(96)
                .buildAndRegister();

        RecipeMaps.PYROLYSE_RECIPES.recipeBuilder()
                .input(OrePrefix.log, Materials.Wood, 16)
                .circuitMeta(7)
                .outputs(new ItemStack(Items.COAL, 20, 1))
                .fluidOutputs(GAMaterials.WoodTar.getFluid(1500))
                .duration(640)
                .EUt(64)
                .buildAndRegister();

        RecipeMaps.PYROLYSE_RECIPES.recipeBuilder()
                .input(OrePrefix.log, Materials.Wood, 16)
                .circuitMeta(8)
                .fluidInputs(Materials.Nitrogen.getFluid(400))
                .outputs(new ItemStack(Items.COAL, 20, 1))
                .fluidOutputs(GAMaterials.WoodTar.getFluid(1500))
                .duration(320)
                .EUt(96)
                .buildAndRegister();

        RecipeMaps.PYROLYSE_RECIPES.recipeBuilder()
                .input(OrePrefix.log, Materials.Wood, 16)
                .circuitMeta(9)
                .fluidInputs(Materials.Nitrogen.getFluid(400))
                .outputs(new ItemStack(Items.COAL, 20, 1))
                .fluidOutputs(GAMaterials.CharcoalByproducts.getFluid(4000))
                .duration(320)
                .EUt(96)
                .buildAndRegister();

        RecipeMaps.PYROLYSE_RECIPES.recipeBuilder()
                .inputs(new ItemStack(Items.SUGAR, 23))
                .circuitMeta(1)
                .outputs(OreDictUnifier.get(OrePrefix.dust, Materials.Charcoal, 12))
                .fluidOutputs(Materials.Water.getFluid(1500))
                .duration(640)
                .EUt(64)
                .buildAndRegister();

        RecipeMaps.PYROLYSE_RECIPES.recipeBuilder()
                .inputs(new ItemStack(Items.SUGAR, 23))
                .circuitMeta(2)
                .fluidInputs(Materials.Nitrogen.getFluid(400))
                .outputs(OreDictUnifier.get(OrePrefix.dust, Materials.Charcoal, 12))
                .fluidOutputs(Materials.Water.getFluid(1500))
                .duration(320)
                .EUt(96)
                .buildAndRegister();

        //Cracker Recipes
        GARecipeMaps.CRACKER_UNIT_RECIPES.recipeBuilder().duration(40).EUt(120).fluidInputs(Materials.Hydrogen.getFluid(2000), GAMaterials.Ethane.getFluid(1000)).fluidOutputs(GAMaterials.HydroCrackedEthane.getFluid(1000)).buildAndRegister();
        GARecipeMaps.CRACKER_UNIT_RECIPES.recipeBuilder().duration(40).EUt(120).fluidInputs(Materials.Hydrogen.getFluid(2000), GAMaterials.Ethylene.getFluid(1000)).fluidOutputs(GAMaterials.HydroCrackedEthylene.getFluid(1000)).buildAndRegister();
        GARecipeMaps.CRACKER_UNIT_RECIPES.recipeBuilder().duration(40).EUt(120).fluidInputs(Materials.Hydrogen.getFluid(2000), GAMaterials.Propene.getFluid(1000)).fluidOutputs(GAMaterials.HydroCrackedPropene.getFluid(1000)).buildAndRegister();
        GARecipeMaps.CRACKER_UNIT_RECIPES.recipeBuilder().duration(40).EUt(120).fluidInputs(Materials.Hydrogen.getFluid(2000), GAMaterials.Propane.getFluid(1000)).fluidOutputs(GAMaterials.HydroCrackedPropane.getFluid(1000)).buildAndRegister();
        GARecipeMaps.CRACKER_UNIT_RECIPES.recipeBuilder().duration(40).EUt(120).fluidInputs(Materials.Hydrogen.getFluid(2000), Materials.LightFuel.getFluid(1000)).fluidOutputs(GAMaterials.HydroCrackedLightFuel.getFluid(1000)).buildAndRegister();
        GARecipeMaps.CRACKER_UNIT_RECIPES.recipeBuilder().duration(40).EUt(120).fluidInputs(Materials.Hydrogen.getFluid(2000), GAMaterials.Butane.getFluid(1000)).fluidOutputs(GAMaterials.HydroCrackedButane.getFluid(1000)).buildAndRegister();
        GARecipeMaps.CRACKER_UNIT_RECIPES.recipeBuilder().duration(40).EUt(120).fluidInputs(Materials.Hydrogen.getFluid(2000), Materials.Naphtha.getFluid(1000)).fluidOutputs(GAMaterials.HydroCrackedNaphtha.getFluid(1000)).buildAndRegister();
        GARecipeMaps.CRACKER_UNIT_RECIPES.recipeBuilder().duration(40).EUt(120).fluidInputs(Materials.Hydrogen.getFluid(2000), Materials.HeavyFuel.getFluid(1000)).fluidOutputs(GAMaterials.HydroCrackedHeavyFuel.getFluid(1000)).buildAndRegister();
        GARecipeMaps.CRACKER_UNIT_RECIPES.recipeBuilder().duration(40).EUt(120).fluidInputs(Materials.Hydrogen.getFluid(2000), Materials.Gas.getFluid(1000)).fluidOutputs(GAMaterials.HydroCrackedGas.getFluid(1000)).buildAndRegister();
        GARecipeMaps.CRACKER_UNIT_RECIPES.recipeBuilder().duration(40).EUt(120).fluidInputs(Materials.Hydrogen.getFluid(2000), GAMaterials.Butene.getFluid(1000)).fluidOutputs(GAMaterials.HydroCrackedButene.getFluid(1000)).buildAndRegister();
        GARecipeMaps.CRACKER_UNIT_RECIPES.recipeBuilder().duration(40).EUt(120).fluidInputs(Materials.Hydrogen.getFluid(2000), GAMaterials.Butadiene.getFluid(1000)).fluidOutputs(GAMaterials.HydroCrackedButadiene.getFluid(1000)).buildAndRegister();
        GARecipeMaps.CRACKER_UNIT_RECIPES.recipeBuilder().duration(40).EUt(120).fluidInputs(Materials.Steam.getFluid(2000), GAMaterials.Ethane.getFluid(1000)).fluidOutputs(GAMaterials.SteamCrackedEthane.getFluid(1000)).buildAndRegister();
        GARecipeMaps.CRACKER_UNIT_RECIPES.recipeBuilder().duration(40).EUt(120).fluidInputs(Materials.Steam.getFluid(2000), GAMaterials.Ethylene.getFluid(1000)).fluidOutputs(GAMaterials.SteamCrackedEthylene.getFluid(1000)).buildAndRegister();
        GARecipeMaps.CRACKER_UNIT_RECIPES.recipeBuilder().duration(40).EUt(120).fluidInputs(Materials.Steam.getFluid(2000), GAMaterials.Propene.getFluid(1000)).fluidOutputs(GAMaterials.SteamCrackedPropene.getFluid(1000)).buildAndRegister();
        GARecipeMaps.CRACKER_UNIT_RECIPES.recipeBuilder().duration(40).EUt(120).fluidInputs(Materials.Steam.getFluid(2000), GAMaterials.Propane.getFluid(1000)).fluidOutputs(GAMaterials.SteamCrackedPropane.getFluid(1000)).buildAndRegister();
        GARecipeMaps.CRACKER_UNIT_RECIPES.recipeBuilder().duration(40).EUt(120).fluidInputs(Materials.Steam.getFluid(2000), Materials.LightFuel.getFluid(1000)).fluidOutputs(Materials.CrackedLightFuel.getFluid(1000)).buildAndRegister();
        GARecipeMaps.CRACKER_UNIT_RECIPES.recipeBuilder().duration(40).EUt(120).fluidInputs(Materials.Steam.getFluid(2000), GAMaterials.Butane.getFluid(1000)).fluidOutputs(GAMaterials.SteamCrackedButane.getFluid(1000)).buildAndRegister();
        GARecipeMaps.CRACKER_UNIT_RECIPES.recipeBuilder().duration(40).EUt(120).fluidInputs(Materials.Steam.getFluid(2000), Materials.Naphtha.getFluid(1000)).fluidOutputs(GAMaterials.SteamCrackedNaphtha.getFluid(1000)).buildAndRegister();
        GARecipeMaps.CRACKER_UNIT_RECIPES.recipeBuilder().duration(40).EUt(120).fluidInputs(Materials.Steam.getFluid(2000), Materials.HeavyFuel.getFluid(1000)).fluidOutputs(Materials.CrackedHeavyFuel.getFluid(1000)).buildAndRegister();
        GARecipeMaps.CRACKER_UNIT_RECIPES.recipeBuilder().duration(40).EUt(120).fluidInputs(Materials.Steam.getFluid(2000), Materials.Gas.getFluid(1000)).fluidOutputs(GAMaterials.SteamCrackedGas.getFluid(1000)).buildAndRegister();
        GARecipeMaps.CRACKER_UNIT_RECIPES.recipeBuilder().duration(40).EUt(120).fluidInputs(Materials.Steam.getFluid(2000), GAMaterials.Butene.getFluid(1000)).fluidOutputs(GAMaterials.SteamCrackedButene.getFluid(1000)).buildAndRegister();
        GARecipeMaps.CRACKER_UNIT_RECIPES.recipeBuilder().duration(40).EUt(120).fluidInputs(Materials.Steam.getFluid(2000), GAMaterials.Butadiene.getFluid(1000)).fluidOutputs(GAMaterials.SteamCrackedButadiene.getFluid(1000)).buildAndRegister();

        //Chemical Reactor Cracking
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.Hydrogen.getFluid(2000), GAMaterials.Ethane.getFluid(1000)).fluidOutputs(GAMaterials.HydroCrackedEthane.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.Hydrogen.getFluid(2000), GAMaterials.Ethylene.getFluid(1000)).fluidOutputs(GAMaterials.HydroCrackedEthylene.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.Hydrogen.getFluid(2000), GAMaterials.Propene.getFluid(1000)).fluidOutputs(GAMaterials.HydroCrackedPropene.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.Hydrogen.getFluid(2000), GAMaterials.Propane.getFluid(1000)).fluidOutputs(GAMaterials.HydroCrackedPropane.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.Hydrogen.getFluid(2000), Materials.LightFuel.getFluid(1000)).fluidOutputs(GAMaterials.HydroCrackedLightFuel.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.Hydrogen.getFluid(2000), GAMaterials.Butane.getFluid(1000)).fluidOutputs(GAMaterials.HydroCrackedButane.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.Hydrogen.getFluid(2000), Materials.Naphtha.getFluid(1000)).fluidOutputs(GAMaterials.HydroCrackedNaphtha.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.Hydrogen.getFluid(2000), Materials.HeavyFuel.getFluid(1000)).fluidOutputs(GAMaterials.HydroCrackedHeavyFuel.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.Hydrogen.getFluid(2000), Materials.Gas.getFluid(1000)).fluidOutputs(GAMaterials.HydroCrackedGas.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.Hydrogen.getFluid(2000), GAMaterials.Butene.getFluid(1000)).fluidOutputs(GAMaterials.HydroCrackedButene.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.Hydrogen.getFluid(2000), GAMaterials.Butadiene.getFluid(1000)).fluidOutputs(GAMaterials.HydroCrackedButadiene.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.Steam.getFluid(2000), GAMaterials.Ethane.getFluid(1000)).fluidOutputs(GAMaterials.SteamCrackedEthane.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.Steam.getFluid(2000), GAMaterials.Ethylene.getFluid(1000)).fluidOutputs(GAMaterials.SteamCrackedEthylene.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.Steam.getFluid(2000), GAMaterials.Propene.getFluid(1000)).fluidOutputs(GAMaterials.SteamCrackedPropene.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.Steam.getFluid(2000), GAMaterials.Propane.getFluid(1000)).fluidOutputs(GAMaterials.SteamCrackedPropane.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.Steam.getFluid(2000), Materials.LightFuel.getFluid(1000)).fluidOutputs(Materials.CrackedLightFuel.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.Steam.getFluid(2000), GAMaterials.Butane.getFluid(1000)).fluidOutputs(GAMaterials.SteamCrackedButane.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.Steam.getFluid(2000), Materials.Naphtha.getFluid(1000)).fluidOutputs(GAMaterials.SteamCrackedNaphtha.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.Steam.getFluid(2000), Materials.HeavyFuel.getFluid(1000)).fluidOutputs(Materials.CrackedHeavyFuel.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.Steam.getFluid(2000), Materials.Gas.getFluid(1000)).fluidOutputs(GAMaterials.SteamCrackedGas.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.Steam.getFluid(2000), GAMaterials.Butene.getFluid(1000)).fluidOutputs(GAMaterials.SteamCrackedButene.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.Steam.getFluid(2000), GAMaterials.Butadiene.getFluid(1000)).fluidOutputs(GAMaterials.SteamCrackedButadiene.getFluid(1000)).buildAndRegister();

        //Distillation Recipes
        GARecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(16).EUt(96).fluidInputs(GAMaterials.FishOil.getFluid(24)).fluidOutputs(Materials.Lubricant.getFluid(12)).buildAndRegister();
        GARecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(16).EUt(96).fluidInputs(Materials.Creosote.getFluid(24)).fluidOutputs(Materials.Lubricant.getFluid(12)).buildAndRegister();
        GARecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(120).EUt(120).fluidInputs(GAMaterials.HydroCrackedEthane.getFluid(1000)).fluidOutputs(Materials.Methane.getFluid(2000)).buildAndRegister();
        GARecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(120).EUt(120).fluidInputs(GAMaterials.SteamCrackedEthane.getFluid(1000)).outputs(OreDictUnifier.get(OrePrefix.dustSmall, Materials.Carbon, 2)).fluidOutputs(Materials.Methane.getFluid(1500)).buildAndRegister();
        GARecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(120).EUt(120).fluidInputs(GAMaterials.HydroCrackedEthylene.getFluid(1000)).fluidOutputs(GAMaterials.Ethane.getFluid(1000)).buildAndRegister();
        GARecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(120).EUt(120).fluidInputs(GAMaterials.SteamCrackedEthylene.getFluid(1000)).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.Carbon)).fluidOutputs(Materials.Methane.getFluid(1000)).buildAndRegister();
        GARecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(120).EUt(120).fluidInputs(GAMaterials.HydroCrackedPropene.getFluid(1000)).fluidOutputs(GAMaterials.Propane.getFluid(500), GAMaterials.Ethylene.getFluid(500), Materials.Methane.getFluid(500)).buildAndRegister();
        GARecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(180).EUt(120).fluidInputs(GAMaterials.SteamCrackedPropene.getFluid(1000)).outputs(OreDictUnifier.get(OrePrefix.dustSmall, Materials.Carbon, 6)).fluidOutputs(Materials.Methane.getFluid(1500)).buildAndRegister();
        GARecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(120).EUt(120).fluidInputs(GAMaterials.HydroCrackedPropane.getFluid(1000)).fluidOutputs(GAMaterials.Ethane.getFluid(1000), Materials.Methane.getFluid(1000)).buildAndRegister();
        GARecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(240).EUt(120).fluidInputs(GAMaterials.SteamCrackedPropane.getFluid(2000)).outputs(OreDictUnifier.get(OrePrefix.dustSmall, Materials.Carbon, 3)).fluidOutputs(GAMaterials.Ethylene.getFluid(500), Materials.Methane.getFluid(3500)).buildAndRegister();
        GARecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(120).EUt(120).fluidInputs(GAMaterials.HydroCrackedLightFuel.getFluid(1000)).fluidOutputs(Materials.Naphtha.getFluid(800), GAMaterials.Butane.getFluid(150), GAMaterials.Propane.getFluid(200), GAMaterials.Ethane.getFluid(125), Materials.Methane.getFluid(125)).buildAndRegister();
        GARecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(120).EUt(120).fluidInputs(Materials.CrackedLightFuel.getFluid(1000)).outputs(OreDictUnifier.get(OrePrefix.dustTiny, Materials.Carbon)).fluidOutputs(Materials.HeavyFuel.getFluid(50), Materials.Naphtha.getFluid(100), Materials.Toluene.getFluid(30), GAMaterials.Benzene.getFluid(150), GAMaterials.Butene.getFluid(65), GAMaterials.Butadiene.getFluid(50), GAMaterials.Propane.getFluid(50), GAMaterials.Propene.getFluid(250), GAMaterials.Ethane.getFluid(50), GAMaterials.Ethylene.getFluid(250), Materials.Methane.getFluid(250)).buildAndRegister();
        GARecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(90).EUt(120).fluidInputs(GAMaterials.HydroCrackedButane.getFluid(750)).fluidOutputs(GAMaterials.Propane.getFluid(500), GAMaterials.Ethane.getFluid(500), Materials.Methane.getFluid(500)).buildAndRegister();
        GARecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(240).EUt(120).fluidInputs(GAMaterials.SteamCrackedButane.getFluid(2000)).outputs(OreDictUnifier.get(OrePrefix.dustSmall, Materials.Carbon, 9)).fluidOutputs(GAMaterials.Propane.getFluid(250), GAMaterials.Ethane.getFluid(250), GAMaterials.Ethylene.getFluid(250), Materials.Methane.getFluid(4000)).buildAndRegister();
        GARecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(120).EUt(120).fluidInputs(GAMaterials.HydroCrackedNaphtha.getFluid(1000)).fluidOutputs(GAMaterials.Butane.getFluid(800), GAMaterials.Propane.getFluid(300), GAMaterials.Ethane.getFluid(250), Materials.Methane.getFluid(250)).buildAndRegister();
        GARecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(120).EUt(120).fluidInputs(GAMaterials.SteamCrackedNaphtha.getFluid(1000)).outputs(OreDictUnifier.get(OrePrefix.dustTiny, Materials.Carbon, 3)).fluidOutputs(Materials.HeavyFuel.getFluid(25), Materials.LightFuel.getFluid(50), Materials.Toluene.getFluid(20), GAMaterials.Benzene.getFluid(100), GAMaterials.Butene.getFluid(50), GAMaterials.Butadiene.getFluid(50), GAMaterials.Propane.getFluid(15), GAMaterials.Propene.getFluid(300), GAMaterials.Ethane.getFluid(65), GAMaterials.Ethylene.getFluid(500), Materials.Methane.getFluid(500)).buildAndRegister();
        GARecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(120).EUt(120).fluidInputs(GAMaterials.HydroCrackedHeavyFuel.getFluid(1000)).fluidOutputs(Materials.LightFuel.getFluid(600), Materials.Naphtha.getFluid(100), GAMaterials.Butane.getFluid(100), GAMaterials.Propane.getFluid(100), GAMaterials.Ethane.getFluid(75), Materials.Methane.getFluid(75)).buildAndRegister();
        GARecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(120).EUt(120).fluidInputs(Materials.CrackedHeavyFuel.getFluid(1000)).outputs(OreDictUnifier.get(OrePrefix.dustTiny, Materials.Carbon, 3)).fluidOutputs(Materials.LightFuel.getFluid(100), Materials.Naphtha.getFluid(125), Materials.Toluene.getFluid(80), GAMaterials.Benzene.getFluid(400), GAMaterials.Butene.getFluid(80), GAMaterials.Butadiene.getFluid(50), GAMaterials.Propane.getFluid(10), GAMaterials.Propene.getFluid(100), GAMaterials.Ethane.getFluid(15), GAMaterials.Ethylene.getFluid(150), Materials.Methane.getFluid(150)).buildAndRegister();
        GARecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(120).EUt(120).fluidInputs(GAMaterials.HydroCrackedGas.getFluid(1000)).fluidOutputs(Materials.Methane.getFluid(1400), Materials.Hydrogen.getFluid(1340), Materials.Helium.getFluid(20)).buildAndRegister();
        GARecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(96).EUt(120).fluidInputs(GAMaterials.SteamCrackedGas.getFluid(800)).outputs(OreDictUnifier.get(OrePrefix.dustTiny, Materials.Carbon)).fluidOutputs(GAMaterials.Propene.getFluid(6), GAMaterials.Ethane.getFluid(6), GAMaterials.Ethylene.getFluid(20), Materials.Methane.getFluid(914), Materials.Helium.getFluid(16)).buildAndRegister();
        GARecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(90).EUt(120).fluidInputs(GAMaterials.HydroCrackedButene.getFluid(750)).fluidOutputs(GAMaterials.Butane.getFluid(250), GAMaterials.Propene.getFluid(250), GAMaterials.Ethane.getFluid(250), Materials.Methane.getFluid(250)).buildAndRegister();
        GARecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(240).EUt(120).fluidInputs(GAMaterials.SteamCrackedButene.getFluid(2000)).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.Carbon, 3)).fluidOutputs(GAMaterials.Propene.getFluid(250), GAMaterials.Ethylene.getFluid(625), Materials.Methane.getFluid(3000)).buildAndRegister();
        GARecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(90).EUt(120).fluidInputs(GAMaterials.HydroCrackedButadiene.getFluid(750)).fluidOutputs(GAMaterials.Butene.getFluid(500), GAMaterials.Ethylene.getFluid(500)).buildAndRegister();
        GARecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(240).EUt(120).fluidInputs(GAMaterials.SteamCrackedButadiene.getFluid(2000)).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.Carbon, 2)).fluidOutputs(GAMaterials.Propene.getFluid(250), GAMaterials.Ethylene.getFluid(375), Materials.Methane.getFluid(2250)).buildAndRegister();
        GARecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(20).EUt(96).fluidInputs(Materials.OilLight.getFluid(150)).fluidOutputs(Materials.SulfuricHeavyFuel.getFluid(10), Materials.SulfuricLightFuel.getFluid(20), Materials.SulfuricNaphtha.getFluid(30), Materials.SulfuricGas.getFluid(240)).buildAndRegister();
        GARecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(20).EUt(96).fluidInputs(Materials.OilMedium.getFluid(100)).fluidOutputs(Materials.SulfuricHeavyFuel.getFluid(15), Materials.SulfuricLightFuel.getFluid(50), Materials.SulfuricNaphtha.getFluid(20), Materials.SulfuricGas.getFluid(60)).buildAndRegister();
        GARecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(20).EUt(288).fluidInputs(Materials.OilHeavy.getFluid(150)).fluidOutputs(Materials.SulfuricHeavyFuel.getFluid(250), Materials.SulfuricLightFuel.getFluid(45), Materials.SulfuricNaphtha.getFluid(15), Materials.SulfuricGas.getFluid(600)).buildAndRegister();
        GARecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(20).EUt(96).fluidInputs(Materials.Oil.getFluid(50)).fluidOutputs(Materials.SulfuricHeavyFuel.getFluid(15), Materials.SulfuricLightFuel.getFluid(50), Materials.SulfuricNaphtha.getFluid(20), Materials.SulfuricGas.getFluid(60)).buildAndRegister();
        GARecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(600).EUt(64).fluidInputs(GAMaterials.DilutedHydrochloricAcid.getFluid(2000)).fluidOutputs(Materials.Water.getFluid(1000), GAMaterials.HydrochloricAcid.getFluid(1000)).buildAndRegister();
        GARecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(600).EUt(120).fluidInputs(GAMaterials.DilutedSulfuricAcid.getFluid(3000)).fluidOutputs(Materials.SulfuricAcid.getFluid(2000), Materials.Water.getFluid(1000)).buildAndRegister();
        GARecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(40).EUt(256).fluidInputs(GAMaterials.CharcoalByproducts.getFluid(1000)).outputs(OreDictUnifier.get(OrePrefix.dustSmall, Materials.Charcoal)).fluidOutputs(GAMaterials.WoodTar.getFluid(250), GAMaterials.WoodVinegar.getFluid(500), GAMaterials.WoodGas.getFluid(250)).buildAndRegister();
        GARecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(40).EUt(256).fluidInputs(GAMaterials.WoodTar.getFluid(1000)).fluidOutputs(Materials.Creosote.getFluid(500), GAMaterials.Phenol.getFluid(75), GAMaterials.Benzene.getFluid(350), Materials.Toluene.getFluid(75)).buildAndRegister();
        GARecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(40).EUt(256).fluidInputs(GAMaterials.WoodGas.getFluid(1000)).fluidOutputs(Materials.CarbonDioxide.getFluid(490), GAMaterials.Ethylene.getFluid(20), Materials.Methane.getFluid(130), GAMaterials.CarbonMonoxde.getFluid(340), Materials.Hydrogen.getFluid(20)).buildAndRegister();
        GARecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(160).EUt(120).fluidInputs(Materials.Water.getFluid(576)).fluidOutputs(Materials.DistilledWater.getFluid(520)).buildAndRegister();
        GARecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(80).EUt(480).fluidInputs(GAMaterials.CalciumAcetate.getFluid(1000)).outputs(OreDictUnifier.get(OrePrefix.dustSmall, GAMaterials.Quicklime, 3)).fluidOutputs(GAMaterials.Acetone.getFluid(1000), Materials.CarbonDioxide.getFluid(1000)).buildAndRegister();
        GARecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(80).EUt(640).fluidInputs(GAMaterials.Acetone.getFluid(1000)).fluidOutputs(GAMaterials.Ethenone.getFluid(1000), Materials.Methane.getFluid(1000)).buildAndRegister();

        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(160).EUt(24).circuitMeta(1).fluidInputs(Materials.Toluene.getFluid(30)).fluidOutputs(Materials.LightFuel.getFluid(30)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(16).EUt(24).circuitMeta(1).fluidInputs(Materials.HeavyFuel.getFluid(10)).fluidOutputs(Materials.Toluene.getFluid(4)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(16).EUt(24).circuitMeta(2).fluidInputs(Materials.HeavyFuel.getFluid(10)).fluidOutputs(GAMaterials.Benzene.getFluid(4)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(32).EUt(24).circuitMeta(3).fluidInputs(Materials.HeavyFuel.getFluid(20)).fluidOutputs(GAMaterials.Phenol.getFluid(5)).buildAndRegister();

        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(16).EUt(24).fluidInputs(Materials.OilLight.getFluid(300)).circuitMeta(4).fluidOutputs(Materials.Oil.getFluid(100)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(16).EUt(24).fluidInputs(Materials.OilMedium.getFluid(200)).circuitMeta(4).fluidOutputs(Materials.Oil.getFluid(100)).buildAndRegister();
        RecipeMaps.DISTILLERY_RECIPES.recipeBuilder().duration(16).EUt(24).fluidInputs(Materials.OilHeavy.getFluid(100)).circuitMeta(4).fluidOutputs(Materials.Oil.getFluid(100)).buildAndRegister();

        //Fluid Heater Recipes
        RecipeMaps.FLUID_HEATER_RECIPES.recipeBuilder().duration(16).EUt(30).circuitMeta(1).fluidInputs(GAMaterials.Acetone.getFluid(100)).fluidOutputs(GAMaterials.Ethenone.getFluid(100)).buildAndRegister();
        RecipeMaps.FLUID_HEATER_RECIPES.recipeBuilder().duration(16).EUt(30).circuitMeta(1).fluidInputs(GAMaterials.CalciumAcetate.getFluid(200)).fluidOutputs(GAMaterials.Acetone.getFluid(200)).buildAndRegister();
        RecipeMaps.FLUID_HEATER_RECIPES.recipeBuilder().duration(30).EUt(24).circuitMeta(1).fluidInputs(GAMaterials.RawGrowthMedium.getFluid(500)).fluidOutputs(GAMaterials.SterilizedGrowthMedium.getFluid(500)).buildAndRegister();

        //Fermenter Recipe
        RecipeMaps.FERMENTING_RECIPES.recipeBuilder().duration(150).EUt(2).fluidInputs(Materials.Biomass.getFluid(100)).fluidOutputs(GAMaterials.FermentedBiomass.getFluid(100)).buildAndRegister();

        //Oil Extractor Recipes
        RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(160).EUt(4).inputs(new ItemStack(Items.FISH)).fluidOutputs(GAMaterials.FishOil.getFluid(40)).buildAndRegister();
        RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(160).EUt(4).inputs(new ItemStack(Items.FISH, 1, 1)).fluidOutputs(GAMaterials.FishOil.getFluid(60)).buildAndRegister();
        RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(160).EUt(4).inputs(new ItemStack(Items.FISH, 1, 2)).fluidOutputs(GAMaterials.FishOil.getFluid(70)).buildAndRegister();
        RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(160).EUt(4).inputs(new ItemStack(Items.FISH, 1, 3)).fluidOutputs(GAMaterials.FishOil.getFluid(30)).buildAndRegister();

        //Misc Blast Furnace Recipes
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(120).EUt(120).blastFurnaceTemp(1200).input(OrePrefix.dust, Materials.Galena).fluidInputs(Materials.Oxygen.getFluid(3000)).outputs(OreDictUnifier.get(OrePrefix.dust, GAMaterials.Massicot), OreDictUnifier.get(OrePrefix.nugget, Materials.Lead, 6)).fluidOutputs(GAMaterials.SulfurDioxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(120).EUt(120).blastFurnaceTemp(1200).input(OrePrefix.dust, Materials.Stibnite).fluidInputs(Materials.Oxygen.getFluid(3000)).outputs(OreDictUnifier.get(OrePrefix.dust, GAMaterials.AntimonyTrioxide), OreDictUnifier.get(OrePrefix.dustTiny, Materials.Ash)).fluidOutputs(GAMaterials.SulfurDioxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(120).EUt(120).blastFurnaceTemp(1200).input(OrePrefix.dust, Materials.Sphalerite).fluidInputs(Materials.Oxygen.getFluid(3000)).outputs(OreDictUnifier.get(OrePrefix.dust, GAMaterials.Zincite), OreDictUnifier.get(OrePrefix.dustTiny, Materials.Ash)).fluidOutputs(GAMaterials.SulfurDioxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(120).EUt(120).blastFurnaceTemp(1200).input(OrePrefix.dust, Materials.Cobaltite).fluidInputs(Materials.Oxygen.getFluid(3000)).outputs(OreDictUnifier.get(OrePrefix.dust, GAMaterials.CobaltOxide), OreDictUnifier.get(OrePrefix.dust, GAMaterials.ArsenicTrioxide)).fluidOutputs(GAMaterials.SulfurDioxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(120).EUt(120).blastFurnaceTemp(1200).input(OrePrefix.dust, Materials.Tetrahedrite).fluidInputs(Materials.Oxygen.getFluid(3000)).outputs(OreDictUnifier.get(OrePrefix.dust, GAMaterials.CupricOxide), OreDictUnifier.get(OrePrefix.dustTiny, GAMaterials.AntimonyTrioxide, 3)).fluidOutputs(GAMaterials.SulfurDioxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(120).EUt(120).blastFurnaceTemp(1200).input(OrePrefix.dust, Materials.Chalcopyrite).fluidInputs(Materials.Oxygen.getFluid(3000)).outputs(OreDictUnifier.get(OrePrefix.dust, GAMaterials.CupricOxide), OreDictUnifier.get(OrePrefix.dust, GAMaterials.Ferrosilite)).fluidOutputs(GAMaterials.SulfurDioxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(120).EUt(120).blastFurnaceTemp(1200).input(OrePrefix.dust, Materials.Pentlandite).fluidInputs(Materials.Oxygen.getFluid(3000)).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.Garnierite), OreDictUnifier.get(OrePrefix.dustTiny, Materials.Ash)).fluidOutputs(GAMaterials.SulfurDioxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(120).EUt(120).blastFurnaceTemp(1200).input(OrePrefix.dust, Materials.Pyrite).fluidInputs(Materials.Oxygen.getFluid(3000)).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.BandedIron), OreDictUnifier.get(OrePrefix.dustTiny, Materials.Ash)).fluidOutputs(GAMaterials.SulfurDioxide.getFluid(1000)).buildAndRegister();

        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(240).EUt(120).blastFurnaceTemp(1200).input(OrePrefix.dust, GAMaterials.Massicot, 2).input(OrePrefix.dust, Materials.Carbon).outputs(OreDictUnifier.get(OrePrefix.ingot, Materials.Lead, 3), OreDictUnifier.get(OrePrefix.dustTiny, Materials.Ash, 2)).fluidOutputs(Materials.CarbonDioxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(240).EUt(120).blastFurnaceTemp(1200).input(OrePrefix.dust, GAMaterials.AntimonyTrioxide, 2).input(OrePrefix.dust, Materials.Carbon).outputs(OreDictUnifier.get(OrePrefix.ingot, Materials.Antimony, 3), OreDictUnifier.get(OrePrefix.dustTiny, Materials.Ash, 2)).fluidOutputs(Materials.CarbonDioxide.getFluid(3000)).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(240).EUt(120).blastFurnaceTemp(1200).input(OrePrefix.dust, GAMaterials.CobaltOxide, 2).input(OrePrefix.dust, Materials.Carbon).outputs(OreDictUnifier.get(OrePrefix.ingot, Materials.Cobalt, 3), OreDictUnifier.get(OrePrefix.dustTiny, Materials.Ash, 2)).fluidOutputs(Materials.CarbonDioxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(240).EUt(120).blastFurnaceTemp(1200).input(OrePrefix.dust, GAMaterials.ArsenicTrioxide, 2).input(OrePrefix.dust, Materials.Carbon).outputs(OreDictUnifier.get(OrePrefix.ingot, Materials.Arsenic, 3), OreDictUnifier.get(OrePrefix.dustTiny, Materials.Ash, 2)).fluidOutputs(Materials.CarbonDioxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(240).EUt(120).blastFurnaceTemp(1200).input(OrePrefix.dust, GAMaterials.CupricOxide, 2).input(OrePrefix.dust, Materials.Carbon).outputs(OreDictUnifier.get(OrePrefix.ingot, Materials.Copper, 3), OreDictUnifier.get(OrePrefix.dustTiny, Materials.Ash, 2)).fluidOutputs(Materials.CarbonDioxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(240).EUt(120).blastFurnaceTemp(1200).input(OrePrefix.dust, Materials.Garnierite, 2).input(OrePrefix.dust, Materials.Carbon).outputs(OreDictUnifier.get(OrePrefix.ingot, Materials.Nickel, 3), OreDictUnifier.get(OrePrefix.dustTiny, Materials.Ash, 2)).fluidOutputs(Materials.CarbonDioxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(240).EUt(120).blastFurnaceTemp(1200).input(OrePrefix.dust, Materials.BandedIron, 2).input(OrePrefix.dust, Materials.Carbon).outputs(OreDictUnifier.get(OrePrefix.ingot, Materials.Iron, 3), OreDictUnifier.get(OrePrefix.dustTiny, Materials.Ash, 2)).fluidOutputs(Materials.CarbonDioxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(240).EUt(120).blastFurnaceTemp(1200).input(OrePrefix.dust, GAMaterials.Massicot, 2).input(OrePrefix.dust, Materials.Carbon).outputs(OreDictUnifier.get(OrePrefix.ingot, Materials.Lead, 3), OreDictUnifier.get(OrePrefix.dustTiny, Materials.Ash, 2)).fluidOutputs(Materials.CarbonDioxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(240).EUt(120).blastFurnaceTemp(1200).input(OrePrefix.dust, GAMaterials.Massicot, 2).input(OrePrefix.dust, Materials.Carbon).outputs(OreDictUnifier.get(OrePrefix.ingot, Materials.Lead, 3), OreDictUnifier.get(OrePrefix.dustTiny, Materials.Ash, 2)).fluidOutputs(Materials.CarbonDioxide.getFluid(1000)).buildAndRegister();

        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(240).EUt(120).blastFurnaceTemp(1200).input(OrePrefix.dust, Materials.SiliconDioxide).input(OrePrefix.dust, Materials.Carbon, 2).outputs(OreDictUnifier.get(OrePrefix.ingot, Materials.Silicon), OreDictUnifier.get(OrePrefix.dustTiny, Materials.Ash)).fluidOutputs(GAMaterials.CarbonMonoxde.getFluid(2000)).buildAndRegister();

        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(240).EUt(120).blastFurnaceTemp(1200).input(OrePrefix.dust, Materials.Malachite, 2).input(OrePrefix.dust, Materials.Carbon).outputs(OreDictUnifier.get(OrePrefix.ingot, Materials.Copper, 3), OreDictUnifier.get(OrePrefix.dustTiny, Materials.Ash, 2)).fluidOutputs(Materials.CarbonDioxide.getFluid(3000)).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(240).EUt(120).blastFurnaceTemp(1200).input(OrePrefix.dust, Materials.Magnetite, 2).input(OrePrefix.dust, Materials.Carbon).outputs(OreDictUnifier.get(OrePrefix.ingot, Materials.Iron, 3), OreDictUnifier.get(OrePrefix.dustTiny, Materials.Ash, 2)).fluidOutputs(Materials.CarbonDioxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(240).EUt(120).blastFurnaceTemp(1200).input(OrePrefix.dust, Materials.GraniticMineralSand, 2).input(OrePrefix.dust, Materials.Carbon).outputs(OreDictUnifier.get(OrePrefix.ingot, Materials.Iron, 3), OreDictUnifier.get(OrePrefix.dustTiny, Materials.Ash, 2)).fluidOutputs(Materials.CarbonDioxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(240).EUt(120).blastFurnaceTemp(1200).input(OrePrefix.dust, Materials.BrownLimonite, 2).input(OrePrefix.dust, Materials.Carbon).outputs(OreDictUnifier.get(OrePrefix.ingot, Materials.Iron, 3), OreDictUnifier.get(OrePrefix.dustTiny, Materials.Ash, 2)).fluidOutputs(Materials.CarbonDioxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(240).EUt(120).blastFurnaceTemp(1200).input(OrePrefix.dust, Materials.YellowLimonite, 2).input(OrePrefix.dust, Materials.Carbon).outputs(OreDictUnifier.get(OrePrefix.ingot, Materials.Iron, 3), OreDictUnifier.get(OrePrefix.dustTiny, Materials.Ash, 2)).fluidOutputs(Materials.CarbonDioxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(240).EUt(120).blastFurnaceTemp(1200).input(OrePrefix.dust, Materials.BasalticMineralSand, 2).input(OrePrefix.dust, Materials.Carbon).outputs(OreDictUnifier.get(OrePrefix.ingot, Materials.Iron, 3), OreDictUnifier.get(OrePrefix.dustTiny, Materials.Ash, 2)).fluidOutputs(Materials.CarbonDioxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(240).EUt(120).blastFurnaceTemp(1200).input(OrePrefix.dust, Materials.Cassiterite, 2).input(OrePrefix.dust, Materials.Carbon).outputs(OreDictUnifier.get(OrePrefix.ingot, Materials.Tin, 3), OreDictUnifier.get(OrePrefix.dustTiny, Materials.Ash, 2)).fluidOutputs(Materials.CarbonDioxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(240).EUt(120).blastFurnaceTemp(1200).input(OrePrefix.dust, Materials.CassiteriteSand, 2).input(OrePrefix.dust, Materials.Carbon).outputs(OreDictUnifier.get(OrePrefix.ingot, Materials.Tin, 3), OreDictUnifier.get(OrePrefix.dustTiny, Materials.Ash, 2)).fluidOutputs(Materials.CarbonDioxide.getFluid(1000)).buildAndRegister();

        for (MaterialStack ore : ironOres) {
            Material materials = ore.material;
            RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(500).EUt(120).blastFurnaceTemp(1500).input(OrePrefix.ore, materials).input(OrePrefix.dust, Materials.Calcite).outputs(OreDictUnifier.get(OrePrefix.ingot, Materials.Iron, 3), OreDictUnifier.get(OrePrefix.dustSmall, Materials.DarkAsh)).buildAndRegister();
            RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(500).EUt(120).blastFurnaceTemp(1500).input(OrePrefix.ore, materials).input(OrePrefix.dustTiny, GAMaterials.Quicklime, 3).outputs(OreDictUnifier.get(OrePrefix.ingot, Materials.Iron, 2), OreDictUnifier.get(OrePrefix.dustSmall, Materials.DarkAsh)).buildAndRegister();
        }

        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(944).EUt(120).input(OrePrefix.dust, Materials.Silicon).notConsumable(new IntCircuitIngredient(1)).blastFurnaceTemp(1687).outputs(OreDictUnifier.get(OrePrefix.ingot, Materials.Silicon)).buildAndRegister();

        //Misc Centrifuging
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(300).EUt(192).fluidInputs(GAMaterials.LeadZincSolution.getFluid(8000)).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.Lead), OreDictUnifier.get(OrePrefix.dust, Materials.Silver), OreDictUnifier.get(OrePrefix.dust, Materials.Zinc), OreDictUnifier.get(OrePrefix.dust, Materials.Sulfur, 3)).fluidOutputs(Materials.Water.getFluid(2000)).buildAndRegister();

        //Mince Meat Recipes
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder().duration(60).EUt(16).inputs(new ItemStack(Items.PORKCHOP)).outputs(OreDictUnifier.get(OrePrefix.dustSmall, GAMaterials.Meat, 6)).buildAndRegister();
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder().duration(60).EUt(16).inputs(new ItemStack(Items.BEEF)).outputs(OreDictUnifier.get(OrePrefix.dustSmall, GAMaterials.Meat, 6)).buildAndRegister();
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder().duration(60).EUt(16).inputs(new ItemStack(Items.RABBIT)).outputs(OreDictUnifier.get(OrePrefix.dustSmall, GAMaterials.Meat, 6)).buildAndRegister();
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder().duration(40).EUt(16).inputs(new ItemStack(Items.CHICKEN)).outputs(OreDictUnifier.get(OrePrefix.dust, GAMaterials.Meat)).buildAndRegister();
        RecipeMaps.MACERATOR_RECIPES.recipeBuilder().duration(40).EUt(16).inputs(new ItemStack(Items.MUTTON)).outputs(OreDictUnifier.get(OrePrefix.dust, GAMaterials.Meat)).buildAndRegister();

        //Ash-Related Recipes
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(250).EUt(6).input(OrePrefix.dust, Materials.DarkAsh).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.Ash), OreDictUnifier.get(OrePrefix.dust, Materials.Carbon)).buildAndRegister();
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(240).EUt(30).input(OrePrefix.dust, Materials.Ash).chancedOutput(OreDictUnifier.get(OrePrefix.dustSmall, GAMaterials.Quicklime, 2), 9900).chancedOutput(OreDictUnifier.get(OrePrefix.dustSmall, GAMaterials.Potash), 6400).chancedOutput(OreDictUnifier.get(OrePrefix.dustSmall, GAMaterials.Magnesia), 6000).chancedOutput(OreDictUnifier.get(OrePrefix.dustSmall, GAMaterials.PhosphorousPentoxide), 500).chancedOutput(OreDictUnifier.get(OrePrefix.dustSmall, GAMaterials.SodaAsh), 5000).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(80).EUt(30).input(OrePrefix.dust, GAMaterials.Quicklime).fluidInputs(Materials.CarbonDioxide.getFluid(1000)).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.Calcite)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(80).EUt(30).input(OrePrefix.dust, GAMaterials.Magnesia).fluidInputs(Materials.CarbonDioxide.getFluid(1000)).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.Magnesite)).buildAndRegister();

        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(30).input(OrePrefix.dust, Materials.Calcite).notConsumable(new IntCircuitIngredient(1)).outputs(OreDictUnifier.get(OrePrefix.dust, GAMaterials.Quicklime)).fluidOutputs(Materials.CarbonDioxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(30).input(OrePrefix.dust, Materials.Magnesite).outputs(OreDictUnifier.get(OrePrefix.dust, GAMaterials.Magnesia)).fluidOutputs(Materials.CarbonDioxide.getFluid(1000)).buildAndRegister();

        //Circuit Rabbit Hole - Layer 1
        for (MaterialStack stack : solderingList) {
            IngotMaterial material = (IngotMaterial) stack.material;
            int multiplier = (int) stack.amount;
            ModHandler.addShapedRecipe("vacuum_tube_1", GAMetaItems.VACUUM_TUBE.getStackForm(), "PTP", "WWW", 'P', new ItemStack(Items.PAPER), 'T', GAMetaItems.GLASS_TUBE.getStackForm(), 'W', OreDictUnifier.get(OrePrefix.wireGtSingle, Materials.Copper));
            ModHandler.addShapedRecipe("vacuum_tube_2", GAMetaItems.VACUUM_TUBE.getStackForm(), "PTP", "WWW", 'P', new ItemStack(Items.PAPER), 'T', GAMetaItems.GLASS_TUBE.getStackForm(), 'W', OreDictUnifier.get(OrePrefix.wireFine, Materials.Copper));
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(120).EUt(8).inputs(GAMetaItems.GLASS_TUBE.getStackForm(), OreDictUnifier.get(OrePrefix.wireGtSingle, Materials.Copper, 2), new ItemStack(Items.PAPER, 2)).outputs(GAMetaItems.VACUUM_TUBE.getStackForm()).buildAndRegister();
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(120).EUt(8).inputs(GAMetaItems.GLASS_TUBE.getStackForm(), OreDictUnifier.get(OrePrefix.wireFine, Materials.Copper, 2), new ItemStack(Items.PAPER, 2)).outputs(GAMetaItems.VACUUM_TUBE.getStackForm()).buildAndRegister();
            ModHandler.addShapedRecipe("ga_basic_circuit", GAMetaItems.BASIC_CIRCUIT.getStackForm(), "RPR", "VBV", "CCC", 'R', GAMetaItems.RESISTOR.getStackForm(), 'P', "plateSteel", 'V', GAMetaItems.VACUUM_TUBE.getStackForm(), 'B', GAMetaItems.COATED_BOARD.getStackForm(), 'C', OreDictUnifier.get(OrePrefix.cableGtSingle, Materials.RedAlloy));
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(8).inputs(GAMetaItems.PHENOLIC_BOARD.getStackForm(), GAMetaItems.LOGIC_CIRCUIT.getStackForm(), GAMetaItems.RESISTOR.getStackForm(2), OreDictUnifier.get(OrePrefix.wireFine, Materials.Copper)).fluidInputs(material.getFluid(72 * multiplier)).outputs(MetaItems.CIRCUIT_BASIC.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(8).inputs(GAMetaItems.PHENOLIC_BOARD.getStackForm(), GAMetaItems.LOGIC_CIRCUIT.getStackForm(), GAMetaItems.SMD_RESISTOR.getStackForm(2), OreDictUnifier.get(OrePrefix.wireFine, Materials.Copper)).fluidInputs(material.getFluid(72 * multiplier)).outputs(MetaItems.CIRCUIT_BASIC.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(60).inputs(GAMetaItems.PLASTIC_BOARD.getStackForm(), GAMetaItems.CPU.getStackForm(4), GAMetaItems.RESISTOR.getStackForm(4), GAMetaItems.CAPACITOR.getStackForm(4), GAMetaItems.TRANSISTOR.getStackForm(4), OreDictUnifier.get(OrePrefix.wireFine, Materials.Copper, 2)).fluidInputs(material.getFluid(72 * multiplier)).outputs(MetaItems.CIRCUIT_PARTS_ADVANCED.getStackForm(4)).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(60).inputs(GAMetaItems.PLASTIC_BOARD.getStackForm(), GAMetaItems.CPU.getStackForm(4), GAMetaItems.SMD_RESISTOR.getStackForm(4), GAMetaItems.SMD_CAPACITOR.getStackForm(4), GAMetaItems.SMD_TRANSISTOR.getStackForm(4), OreDictUnifier.get(OrePrefix.wireFine, Materials.Copper, 2)).fluidInputs(material.getFluid(72 * multiplier)).outputs(MetaItems.CIRCUIT_PARTS_ADVANCED.getStackForm(4)).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(600).inputs(GAMetaItems.PLASTIC_BOARD.getStackForm(), GAMetaItems.SOC.getStackForm(4), OreDictUnifier.get(OrePrefix.wireFine, Materials.Copper, 2)).fluidInputs(material.getFluid(72 * multiplier)).outputs(MetaItems.CIRCUIT_PARTS_ADVANCED.getStackForm(4)).buildAndRegister();
            ModHandler.addShapedRecipe("good_circuit", MetaItems.CIRCUIT_GOOD.getStackForm(), "PCR", "CDC", "RCP", 'P', "plateSteel", 'C', GAMetaItems.BASIC_CIRCUIT.getStackForm(), 'R', OreDictUnifier.get(OrePrefix.cableGtSingle, Materials.RedAlloy), 'D', GAMetaItems.DIODE.getStackForm());
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(16).inputs(GAMetaItems.PHENOLIC_BOARD.getStackForm(), MetaItems.CIRCUIT_BASIC.getStackForm(3), GAMetaItems.RESISTOR.getStackForm(4), OreDictUnifier.get(OrePrefix.wireFine, Materials.Electrum, 8)).fluidInputs(material.getFluid(72 * multiplier)).outputs(GAMetaItems.GOOD_CIRCUIT.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(16).inputs(GAMetaItems.PHENOLIC_BOARD.getStackForm(), MetaItems.CIRCUIT_BASIC.getStackForm(3), GAMetaItems.SMD_RESISTOR.getStackForm(4), OreDictUnifier.get(OrePrefix.wireFine, Materials.Electrum, 8)).fluidInputs(material.getFluid(72 * multiplier)).outputs(GAMetaItems.GOOD_CIRCUIT.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(60).inputs(GAMetaItems.PLASTIC_BOARD.getStackForm(), GAMetaItems.CPU.getStackForm(), GAMetaItems.RESISTOR.getStackForm(2), GAMetaItems.CAPACITOR.getStackForm(2), GAMetaItems.TRANSISTOR.getStackForm(2), OreDictUnifier.get(OrePrefix.wireFine, Materials.RedAlloy, 2)).fluidInputs(material.getFluid(72 * multiplier)).outputs(MetaItems.CIRCUIT_ADVANCED.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(60).inputs(GAMetaItems.PLASTIC_BOARD.getStackForm(), GAMetaItems.CPU.getStackForm(), GAMetaItems.SMD_RESISTOR.getStackForm(2), GAMetaItems.SMD_CAPACITOR.getStackForm(2), GAMetaItems.SMD_TRANSISTOR.getStackForm(2), OreDictUnifier.get(OrePrefix.wireFine, Materials.RedAlloy, 2)).fluidInputs(material.getFluid(72 * multiplier)).outputs(MetaItems.CIRCUIT_ADVANCED.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(2400).inputs(GAMetaItems.PLASTIC_BOARD.getStackForm(), GAMetaItems.SOC.getStackForm(), OreDictUnifier.get(OrePrefix.wireFine, Materials.RedAlloy, 2)).fluidInputs(material.getFluid(72 * multiplier)).outputs(MetaItems.CIRCUIT_ADVANCED.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(800).EUt(28).inputs(GAMetaItems.GOOD_CIRCUIT.getStackForm(2), GAMetaItems.LOGIC_CIRCUIT.getStackForm(3), GAMetaItems.RAM.getStackForm(), GAMetaItems.TRANSISTOR.getStackForm(4), OreDictUnifier.get(OrePrefix.wireFine, Materials.Electrum, 16)).fluidInputs(material.getFluid(72 * multiplier)).outputs(GAMetaItems.ADVANCED_CIRCUIT.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(800).EUt(28).inputs(GAMetaItems.GOOD_CIRCUIT.getStackForm(2), GAMetaItems.LOGIC_CIRCUIT.getStackForm(3), GAMetaItems.RAM.getStackForm(), GAMetaItems.SMD_TRANSISTOR.getStackForm(4), OreDictUnifier.get(OrePrefix.wireFine, Materials.Electrum, 16)).fluidInputs(material.getFluid(72 * multiplier)).outputs(GAMetaItems.ADVANCED_CIRCUIT.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(90).inputs(GAMetaItems.PLASTIC_BOARD.getStackForm(), MetaItems.CIRCUIT_ADVANCED.getStackForm(2), GAMetaItems.SMALL_COIL.getStackForm(4), GAMetaItems.CAPACITOR.getStackForm(4), GAMetaItems.RAM.getStackForm(4), OreDictUnifier.get(OrePrefix.wireFine, Materials.RedAlloy, 12)).fluidInputs(material.getFluid(144 * multiplier)).outputs(GAMetaItems.INTEGRATED_ASSEMBLY.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(80).inputs(GAMetaItems.PLASTIC_BOARD.getStackForm(), MetaItems.CIRCUIT_ADVANCED.getStackForm(2), GAMetaItems.SMALL_COIL.getStackForm(4), GAMetaItems.SMD_CAPACITOR.getStackForm(4), GAMetaItems.RAM.getStackForm(4), OreDictUnifier.get(OrePrefix.wireFine, Materials.RedAlloy, 12)).fluidInputs(material.getFluid(144 * multiplier)).outputs(GAMetaItems.INTEGRATED_ASSEMBLY.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(600).inputs(GAMetaItems.EPOXY_BOARD.getStackForm(), GAMetaItems.NANOCPU.getStackForm(), GAMetaItems.SMD_RESISTOR.getStackForm(2), GAMetaItems.SMD_CAPACITOR.getStackForm(2), GAMetaItems.SMD_TRANSISTOR.getStackForm(2), OreDictUnifier.get(OrePrefix.wireFine, Materials.Electrum, 2)).fluidInputs(material.getFluid(72 * multiplier)).outputs(GAMetaItems.NANOPROCESSOR.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(9600).inputs(GAMetaItems.EPOXY_BOARD.getStackForm(), GAMetaItems.SOC.getStackForm(), OreDictUnifier.get(OrePrefix.wireFine, Materials.Electrum, 2)).fluidInputs(material.getFluid(72 * multiplier)).outputs(GAMetaItems.NANOPROCESSOR.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(90).inputs(GAMetaItems.PLASTIC_BOARD.getStackForm(2), GAMetaItems.INTEGRATED_ASSEMBLY.getStackForm(3), GAMetaItems.DIODE.getStackForm(4), GAMetaItems.RAM.getStackForm(4), OreDictUnifier.get(OrePrefix.wireFine, Materials.Electrum, 6)).fluidInputs(material.getFluid(144 * multiplier)).outputs(MetaItems.CIRCUIT_DATA.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(90).inputs(GAMetaItems.PLASTIC_BOARD.getStackForm(2), GAMetaItems.INTEGRATED_ASSEMBLY.getStackForm(3), GAMetaItems.SMD_DIODE.getStackForm(4), GAMetaItems.RAM.getStackForm(4), OreDictUnifier.get(OrePrefix.wireFine, Materials.Electrum, 6)).fluidInputs(material.getFluid(144 * multiplier)).outputs(MetaItems.CIRCUIT_DATA.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(600).inputs(GAMetaItems.EPOXY_BOARD.getStackForm(), GAMetaItems.NANOPROCESSOR.getStackForm(2), GAMetaItems.SMALL_COIL.getStackForm(4), GAMetaItems.SMD_CAPACITOR.getStackForm(4), GAMetaItems.RAM.getStackForm(4), OreDictUnifier.get(OrePrefix.wireFine, Materials.Electrum, 6)).fluidInputs(material.getFluid(144 * multiplier)).outputs(GAMetaItems.NANO_ASSEMBLY.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(2400).inputs(GAMetaItems.FIBER_BOARD.getStackForm(), GAMetaItems.QBIT_CPU.getStackForm(), GAMetaItems.NANOCPU.getStackForm(), GAMetaItems.SMD_CAPACITOR.getStackForm(2), GAMetaItems.SMD_TRANSISTOR.getStackForm(2), OreDictUnifier.get(OrePrefix.wireFine, Materials.Platinum, 2)).fluidInputs(material.getFluid(72 * multiplier)).outputs(GAMetaItems.QUANTUMPROCESSOR.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(38400).inputs(GAMetaItems.FIBER_BOARD.getStackForm(), GAMetaItems.ASOC.getStackForm(), OreDictUnifier.get(OrePrefix.wireFine, Materials.Platinum, 2)).fluidInputs(material.getFluid(72 * multiplier)).outputs(GAMetaItems.QUANTUMPROCESSOR.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(1600).EUt(480).inputs(OreDictUnifier.get(OrePrefix.frameGt, Materials.Aluminium), MetaItems.CIRCUIT_DATA.getStackForm(4), GAMetaItems.SMALL_COIL.getStackForm(4), GAMetaItems.CAPACITOR.getStackForm(24), GAMetaItems.RAM.getStackForm(16), OreDictUnifier.get(OrePrefix.wireGtSingle, Materials.AnnealedCopper, 12)).fluidInputs(material.getFluid(288 * multiplier)).outputs(GAMetaItems.INTEGRATED_MAINFRAME.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(1600).EUt(480).inputs(OreDictUnifier.get(OrePrefix.frameGt, Materials.Aluminium), MetaItems.CIRCUIT_DATA.getStackForm(4), GAMetaItems.SMALL_COIL.getStackForm(4), GAMetaItems.SMD_CAPACITOR.getStackForm(24), GAMetaItems.RAM.getStackForm(16), OreDictUnifier.get(OrePrefix.wireGtSingle, Materials.AnnealedCopper, 12)).fluidInputs(material.getFluid(288 * multiplier)).outputs(GAMetaItems.INTEGRATED_MAINFRAME.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(600).inputs(GAMetaItems.EPOXY_BOARD.getStackForm(2), GAMetaItems.NANO_ASSEMBLY.getStackForm(3), GAMetaItems.SMD_DIODE.getStackForm(4), GAMetaItems.NOR.getStackForm(4), GAMetaItems.RAM.getStackForm(4), OreDictUnifier.get(OrePrefix.wireFine, Materials.Electrum, 6)).fluidInputs(material.getFluid(144 * multiplier)).outputs(GAMetaItems.NANO_COMPUTER.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(2400).inputs(GAMetaItems.FIBER_BOARD.getStackForm(), GAMetaItems.QUANTUMPROCESSOR.getStackForm(2), GAMetaItems.SMALL_COIL.getStackForm(4), GAMetaItems.SMD_CAPACITOR.getStackForm(4), GAMetaItems.RAM.getStackForm(4), OreDictUnifier.get(OrePrefix.wireFine, Materials.Platinum, 6)).fluidInputs(material.getFluid(144 * multiplier)).outputs(MetaItems.CIRCUIT_ELITE.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(9600).inputs(GAMetaItems.MULTILAYER_FIBER_BOARD.getStackForm(), GAMetaItems.CRYSTAL_CPU.getStackForm(), GAMetaItems.NANOCPU.getStackForm(), GAMetaItems.SMD_CAPACITOR.getStackForm(2), GAMetaItems.SMD_TRANSISTOR.getStackForm(2), OreDictUnifier.get(OrePrefix.wireFine, Materials.NiobiumTitanium, 2)).fluidInputs(material.getFluid(72 * multiplier)).outputs(GAMetaItems.CRYSTALPROCESSOR.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(153600).inputs(GAMetaItems.MULTILAYER_FIBER_BOARD.getStackForm(), GAMetaItems.CRYSTAL_SOC.getStackForm(), OreDictUnifier.get(OrePrefix.wireFine, Materials.NiobiumTitanium, 2)).fluidInputs(material.getFluid(72 * multiplier)).outputs(GAMetaItems.CRYSTALPROCESSOR.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(1600).EUt(1920).inputs(OreDictUnifier.get(OrePrefix.frameGt, Materials.Aluminium), GAMetaItems.NANO_COMPUTER.getStackForm(4), GAMetaItems.SMALL_COIL.getStackForm(4), GAMetaItems.SMD_CAPACITOR.getStackForm(24), GAMetaItems.RAM.getStackForm(16), OreDictUnifier.get(OrePrefix.wireGtSingle, Materials.AnnealedCopper, 12)).fluidInputs(material.getFluid(288 * multiplier)).outputs(GAMetaItems.NANO_MAINFRAME.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(2400).inputs(GAMetaItems.FIBER_BOARD.getStackForm(2), MetaItems.CIRCUIT_ELITE.getStackForm(3), GAMetaItems.SMD_DIODE.getStackForm(4), GAMetaItems.NOR.getStackForm(4), GAMetaItems.RAM.getStackForm(4), OreDictUnifier.get(OrePrefix.wireFine, Materials.Platinum, 6)).fluidInputs(material.getFluid(144 * multiplier)).outputs(GAMetaItems.QUANTUM_COMPUTER.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(9600).inputs(GAMetaItems.MULTILAYER_FIBER_BOARD.getStackForm(), GAMetaItems.CRYSTALPROCESSOR.getStackForm(2), GAMetaItems.SMALL_COIL.getStackForm(4), GAMetaItems.SMD_CAPACITOR.getStackForm(4), GAMetaItems.RAM.getStackForm(4), OreDictUnifier.get(OrePrefix.wireFine, Materials.NiobiumTitanium, 6)).fluidInputs(material.getFluid(144 * multiplier)).outputs(MetaItems.CIRCUIT_MASTER.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(200).EUt(38400).inputs(MetaItems.CIRCUIT_BOARD_ELITE.getStackForm(), GAMetaItems.CRYSTAL_CPU.getStackForm(), GAMetaItems.NANOCPU.getStackForm(), GAMetaItems.SMD_CAPACITOR.getStackForm(2), GAMetaItems.SMD_TRANSISTOR.getStackForm(2), OreDictUnifier.get(OrePrefix.wireFine, Materials.YttriumBariumCuprate, 2)).fluidInputs(material.getFluid(72 * multiplier)).outputs(GAMetaItems.WETWAREPROCESSOR.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(1600).EUt(7680).inputs(OreDictUnifier.get(OrePrefix.frameGt, Materials.Aluminium), GAMetaItems.QUANTUM_COMPUTER.getStackForm(4), GAMetaItems.SMALL_COIL.getStackForm(4), GAMetaItems.SMD_CAPACITOR.getStackForm(24), GAMetaItems.RAM.getStackForm(16), OreDictUnifier.get(OrePrefix.wireGtSingle, Materials.AnnealedCopper, 12)).fluidInputs(material.getFluid(288 * multiplier)).outputs(GAMetaItems.QUANTUM_MAINFRAME.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(9600).inputs(GAMetaItems.MULTILAYER_FIBER_BOARD.getStackForm(2), MetaItems.CIRCUIT_MASTER.getStackForm(3), GAMetaItems.SMD_DIODE.getStackForm(4), GAMetaItems.NOR.getStackForm(4), GAMetaItems.RAM.getStackForm(4), OreDictUnifier.get(OrePrefix.wireFine, Materials.NiobiumTitanium, 6)).fluidInputs(material.getFluid(144 * multiplier)).outputs(GAMetaItems.CRYSTAL_COMPUTER.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(38400).inputs(GAMetaItems.WETWARE_BOARD.getStackForm(), GAMetaItems.WETWAREPROCESSOR.getStackForm(2), GAMetaItems.SMALL_COIL.getStackForm(4), GAMetaItems.SMD_CAPACITOR.getStackForm(4), GAMetaItems.RAM.getStackForm(4), OreDictUnifier.get(OrePrefix.wireFine, Materials.YttriumBariumCuprate, 6)).fluidInputs(material.getFluid(144 * multiplier)).outputs(GAMetaItems.WETWARE_ASSEMBLY.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(1600).EUt(30720).inputs(OreDictUnifier.get(OrePrefix.frameGt, Materials.Aluminium), GAMetaItems.CRYSTAL_COMPUTER.getStackForm(4), GAMetaItems.SMALL_COIL.getStackForm(4), GAMetaItems.SMD_CAPACITOR.getStackForm(24), GAMetaItems.RAM.getStackForm(16), OreDictUnifier.get(OrePrefix.wireGtSingle, Tier.Superconductor, 12)).fluidInputs(material.getFluid(288 * multiplier)).outputs(GAMetaItems.CRYSTAL_MAINFRAME.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(38400).inputs(GAMetaItems.WETWARE_BOARD.getStackForm(2), GAMetaItems.WETWARE_ASSEMBLY.getStackForm(3), GAMetaItems.SMD_DIODE.getStackForm(4), GAMetaItems.NOR.getStackForm(4), GAMetaItems.RAM.getStackForm(4), OreDictUnifier.get(OrePrefix.wireFine, Materials.YttriumBariumCuprate, 6)).fluidInputs(material.getFluid(144 * multiplier)).outputs(GAMetaItems.WETWARE_COMPUTER.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(512).EUt(1024).inputs(GAMetaItems.FIBER_BOARD.getStackForm(), GAMetaItems.PIC.getStackForm(4), MetaItems.CIRCUIT_PARTS_CRYSTAL_CHIP_MASTER.getStackForm(18), GAMetaItems.NANOCPU.getStackForm(), OreDictUnifier.get(OrePrefix.wireFine, Materials.Platinum, 16)).fluidInputs(material.getFluid(144 * multiplier)).outputs(MetaItems.ENERGY_LAPOTRONIC_ORB.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(1024).EUt(4096).inputs(GAMetaItems.FIBER_BOARD.getStackForm(), GAMetaItems.HPIC.getStackForm(4), MetaItems.ENERGY_LAPOTRONIC_ORB.getStackForm(8), GAMetaItems.QBIT_CPU.getStackForm(), OreDictUnifier.get(OrePrefix.wireFine, Materials.Platinum, 16)).input(OrePrefix.plate, Materials.Europium, 4).fluidInputs(material.getFluid(144 * multiplier)).outputs(MetaItems.ENERGY_LAPOTRONIC_ORB2.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(90).inputs(GAMetaItems.PLASTIC_BOARD.getStackForm(), MetaItems.CIRCUIT_ADVANCED.getStackForm(), GAMetaItems.NAND.getStackForm(32), GAMetaItems.RAM.getStackForm(4), OreDictUnifier.get(OrePrefix.wireFine, Materials.RedAlloy, 8)).input(OrePrefix.plate, Materials.Plastic, 4).fluidInputs(material.getFluid(144 * multiplier)).outputs(MetaItems.TOOL_DATA_STICK.getStackForm()).buildAndRegister();
            GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(1200).inputs(GAMetaItems.EPOXY_BOARD.getStackForm(), GAMetaItems.NANOPROCESSOR.getStackForm(), GAMetaItems.RAM.getStackForm(4), GAMetaItems.NOR.getStackForm(32), GAMetaItems.NAND.getStackForm(64), OreDictUnifier.get(OrePrefix.wireFine, Materials.Platinum, 32)).fluidInputs(material.getFluid(144 * multiplier)).outputs(MetaItems.TOOL_DATA_ORB.getStackForm()).buildAndRegister();
        }
        GARecipeMaps.CIRCUIT_ASSEMBLER_RECIPES.recipeBuilder().duration(30).EUt(4).input(OrePrefix.dust, Materials.Tantalum).input(OrePrefix.foil, Materials.Manganese).fluidInputs(Materials.Plastic.getFluid(144)).outputs(MetaItems.BATTERY_RE_ULV_TANTALUM.getStackForm(8)).buildAndRegister();

        //Circuit Rabbit Hole - Layer 2
        RecipeMaps.ALLOY_SMELTER_RECIPES.recipeBuilder().duration(160).EUt(8).input(OrePrefix.dust, Materials.Glass).notConsumable(MetaItems.SHAPE_MOLD_BALL).outputs(GAMetaItems.GLASS_TUBE.getStackForm()).buildAndRegister();
        ModHandler.addShapedRecipe("resistor_1", GAMetaItems.RESISTOR.getStackForm(3), " P ", "WCW", " P ", 'P', new ItemStack(Items.PAPER), 'W', OreDictUnifier.get(OrePrefix.wireGtSingle, Materials.Copper), 'C', "dustCoal");
        ModHandler.addShapedRecipe("resistor_2", GAMetaItems.RESISTOR.getStackForm(3), " P ", "WCW", " P ", 'P', new ItemStack(Items.PAPER), 'W', OreDictUnifier.get(OrePrefix.wireFine, Materials.Copper), 'C', "dustCoal");
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(160).EUt(6).input(OrePrefix.dust, Materials.Coal).input(OrePrefix.wireFine, Materials.Copper, 4).outputs(GAMetaItems.RESISTOR.getStackForm(12)).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(96).input(OrePrefix.plate, Materials.Plastic).input(OrePrefix.foil, Materials.Aluminium, 2).outputs(GAMetaItems.CAPACITOR.getStackForm(2)).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(24).input(OrePrefix.plate, Materials.Silicon).input(OrePrefix.foil, Materials.Tin, 6).fluidInputs(Materials.Plastic.getFluid(144)).outputs(GAMetaItems.TRANSISTOR.getStackForm(8)).buildAndRegister();
        ModHandler.addShapedRecipe("diode_1", GAMetaItems.DIODE.getStackForm(), "DG ", "TWT", "DG ", 'D', "dyeBlack", 'G', new ItemStack(Blocks.GLASS_PANE), 'T', OreDictUnifier.get(OrePrefix.wireGtSingle, Materials.Tin), 'W', GAMetaItems.SILICON_WAFER.getStackForm());
        ModHandler.addShapedRecipe("diode_2", GAMetaItems.DIODE.getStackForm(), "DG ", "TWT", "DG ", 'D', "dyeBlack", 'G', new ItemStack(Blocks.GLASS_PANE), 'T', OreDictUnifier.get(OrePrefix.wireFine, Materials.Tin), 'W', GAMetaItems.SILICON_WAFER.getStackForm());
        ModHandler.addShapedRecipe("diode_3", GAMetaItems.DIODE.getStackForm(4), "DG ", "TWT", "DG ", 'D', "dyeBlack", 'G', new ItemStack(Blocks.GLASS_PANE), 'T', OreDictUnifier.get(OrePrefix.wireGtSingle, Materials.Tin), 'W', "dustTinyGallium");
        ModHandler.addShapedRecipe("diode_4", GAMetaItems.DIODE.getStackForm(4), "DG ", "TWT", "DG ", 'D', "dyeBlack", 'G', new ItemStack(Blocks.GLASS_PANE), 'T', OreDictUnifier.get(OrePrefix.wireFine, Materials.Tin), 'W', "dustTinyGallium");
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(48).inputs(OreDictUnifier.get(OrePrefix.wireFine, Materials.AnnealedCopper, 4), OreDictUnifier.get(OrePrefix.dustSmall, Materials.Gallium)).fluidInputs(Materials.Plastic.getFluid(288)).outputs(GAMetaItems.DIODE.getStackForm(16)).buildAndRegister();
        ModHandler.addShapedRecipe("small_coil_1", GAMetaItems.SMALL_COIL.getStackForm(2), "WWW", "WBW", "WWW", 'W', OreDictUnifier.get(OrePrefix.wireFine, Materials.Copper), 'B', OreDictUnifier.get(OrePrefix.bolt, Materials.Steel));
        ModHandler.addShapedRecipe("small_coil_2", GAMetaItems.SMALL_COIL.getStackForm(2), "WWW", "WBW", "WWW", 'W', OreDictUnifier.get(OrePrefix.wireFine, Materials.AnnealedCopper), 'B', OreDictUnifier.get(OrePrefix.bolt, Materials.Steel));
        ModHandler.addShapedRecipe("small_coil_3", GAMetaItems.SMALL_COIL.getStackForm(4), "WWW", "WBW", "WWW", 'W', OreDictUnifier.get(OrePrefix.wireFine, Materials.Copper), 'B', OreDictUnifier.get(OrePrefix.bolt, GAMaterials.NickelZincFerrite));
        ModHandler.addShapedRecipe("small_coil_4", GAMetaItems.SMALL_COIL.getStackForm(4), "WWW", "WBW", "WWW", 'W', OreDictUnifier.get(OrePrefix.wireFine, Materials.AnnealedCopper), 'B', OreDictUnifier.get(OrePrefix.bolt, GAMaterials.NickelZincFerrite));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(8).inputs(OreDictUnifier.get(OrePrefix.wireFine, Materials.Copper), OreDictUnifier.get(OrePrefix.bolt, Materials.Steel)).outputs(GAMetaItems.SMALL_COIL.getStackForm(2)).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(8).inputs(OreDictUnifier.get(OrePrefix.wireFine, Materials.AnnealedCopper), OreDictUnifier.get(OrePrefix.bolt, Materials.Steel)).outputs(GAMetaItems.SMALL_COIL.getStackForm(2)).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(8).inputs(OreDictUnifier.get(OrePrefix.wireFine, Materials.Copper), OreDictUnifier.get(OrePrefix.bolt, GAMaterials.NickelZincFerrite)).outputs(GAMetaItems.SMALL_COIL.getStackForm(4)).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(8).inputs(OreDictUnifier.get(OrePrefix.wireFine, Materials.AnnealedCopper), OreDictUnifier.get(OrePrefix.bolt, GAMaterials.NickelZincFerrite)).outputs(GAMetaItems.SMALL_COIL.getStackForm(4)).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(96).input(OrePrefix.dust, Materials.Carbon).input(OrePrefix.wireFine, Materials.Electrum, 4).fluidInputs(Materials.Plastic.getFluid(144)).outputs(GAMetaItems.SMD_RESISTOR.getStackForm(24)).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(120).input(OrePrefix.dustSmall, Materials.Gallium).input(OrePrefix.wireFine, Materials.Platinum, 4).fluidInputs(Materials.Plastic.getFluid(288)).outputs(GAMetaItems.SMD_DIODE.getStackForm(32)).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(80).EUt(96).input(OrePrefix.plate, Materials.Gallium).input(OrePrefix.wireFine, Materials.AnnealedCopper, 6).fluidInputs(Materials.Plastic.getFluid(288)).outputs(GAMetaItems.SMD_TRANSISTOR.getStackForm(32)).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(120).inputs(OreDictUnifier.get(OrePrefix.foil, GAMaterials.PolyvinylChloride, 4), OreDictUnifier.get(OrePrefix.foil, Materials.Aluminium)).fluidInputs(Materials.Plastic.getFluid(36)).outputs(GAMetaItems.SMD_CAPACITOR.getStackForm(16)).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(60).EUt(120).inputs(OreDictUnifier.get(OrePrefix.foil, GAMaterials.SiliconeRubber, 4), OreDictUnifier.get(OrePrefix.foil, Materials.Aluminium)).fluidInputs(Materials.Plastic.getFluid(36)).outputs(GAMetaItems.SMD_CAPACITOR.getStackForm(16)).buildAndRegister();
        ModHandler.addShapedRecipe("coated_board", GAMetaItems.COATED_BOARD.getStackForm(3), " R ", "PPP", " R ", 'R', MetaItems.RUBBER_DROP.getStackForm(), 'P', "plateWood");
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(160).EUt(8).input(OrePrefix.plate, Materials.Wood, 8).inputs(MetaItems.RUBBER_DROP.getStackForm()).fluidInputs(Materials.Glue.getFluid(100)).outputs(GAMetaItems.COATED_BOARD.getStackForm(8)).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(30).EUt(8).input(OrePrefix.dust, Materials.Wood).notConsumable(MetaItems.SHAPE_MOLD_PLATE.getStackForm()).fluidInputs(Materials.Glue.getFluid(100)).outputs(GAMetaItems.PHENOLIC_BOARD.getStackForm()).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(30).EUt(8).input(OrePrefix.dust, Materials.Wood).notConsumable(MetaItems.SHAPE_MOLD_PLATE.getStackForm()).fluidInputs(GAMaterials.BisphenolA.getFluid(100)).outputs(GAMetaItems.PHENOLIC_BOARD.getStackForm(4)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(10).input(OrePrefix.plate, Materials.Plastic).input(OrePrefix.foil, Materials.Copper).fluidInputs(Materials.SulfuricAcid.getFluid(125)).outputs(GAMetaItems.PLASTIC_BOARD.getStackForm()).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(10).input(OrePrefix.plate, GAMaterials.PolyvinylChloride).input(OrePrefix.foil, Materials.Copper).fluidInputs(Materials.SulfuricAcid.getFluid(125)).outputs(GAMetaItems.PLASTIC_BOARD.getStackForm(2)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(10).input(OrePrefix.plate, Materials.Polytetrafluoroethylene).input(OrePrefix.foil, Materials.Copper).fluidInputs(Materials.SulfuricAcid.getFluid(125)).outputs(GAMetaItems.PLASTIC_BOARD.getStackForm(4)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(500).EUt(10).input(OrePrefix.plate, Materials.Epoxid).input(OrePrefix.foil, Materials.Copper).fluidInputs(Materials.SulfuricAcid.getFluid(125)).outputs(GAMetaItems.EPOXY_BOARD.getStackForm()).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(500).EUt(10).input(OrePrefix.plate, GAMaterials.ReinforcedEpoxyResin).input(OrePrefix.foil, Materials.Copper).fluidInputs(Materials.SulfuricAcid.getFluid(125)).outputs(GAMetaItems.FIBER_BOARD.getStackForm()).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(100).EUt(480).inputs(GAMetaItems.FIBER_BOARD.getStackForm(), OreDictUnifier.get(OrePrefix.foil, Materials.Electrum, 16)).fluidInputs(Materials.SulfuricAcid.getFluid(250)).outputs(GAMetaItems.MULTILAYER_FIBER_BOARD.getStackForm()).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(400).EUt(480).inputs(GAMetaItems.MULTILAYER_FIBER_BOARD.getStackForm(), GAMetaItems.PETRI_DISH.getStackForm()).input(OrePrefix.valueOf("circuitGA"), Tier.Good).fluidInputs(GAMaterials.SterilizedGrowthMedium.getFluid(250)).outputs(GAMetaItems.WETWARE_BOARD.getStackForm()).buildAndRegister();
        RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder().duration(100).EUt(40000).inputs(GAMetaItems.CRYSTAL_CPU.getStackForm()).notConsumable(OrePrefix.craftingLens, Color.Blue).outputs(GAMetaItems.CRYSTAL_SOC.getStackForm()).buildAndRegister();
        RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder().duration(100).EUt(10000).inputs(MetaItems.CIRCUIT_PARTS_CRYSTAL_CHIP_ELITE.getStackForm()).notConsumable(OrePrefix.craftingLens, Color.Lime).outputs(GAMetaItems.CRYSTAL_CPU.getStackForm()).buildAndRegister();
        RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder().duration(256).EUt(480).inputs(GAMetaItems.LAPOTRON_CRYSTAL.getStackForm()).notConsumable(OrePrefix.craftingLens, Color.Blue).outputs(MetaItems.CIRCUIT_PARTS_CRYSTAL_CHIP_MASTER.getStackForm(3)).buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(30).EUt(480).fluidInputs(GAMaterials.PositiveMatter.getFluid(10), GAMaterials.NeutralMatter.getFluid(10)).fluidOutputs(Materials.UUMatter.getFluid(20)).buildAndRegister();

        //Assline Casing
        ModHandler.addShapedRecipe("assline_casing", GAMetaBlocks.MUTLIBLOCK_CASING.getItemVariant(GAMultiblockCasing.CasingType.TUNGSTENSTEEL_GEARBOX_CASING, 2), "PhP", "AFA", "PwP", 'P', "plateSteel", 'A', MetaItems.ROBOT_ARM_IV.getStackForm(), 'F', OreDictUnifier.get(OrePrefix.frameGt, Materials.TungstenSteel));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:casing_assembler_casing"));
        ModHandler.addShapedRecipe("ga_assmbler_casing", MetaBlocks.MUTLIBLOCK_CASING.getItemVariant(MultiblockCasingType.ASSEMBLER_CASING, 3), "CCC", "CFC", "CMC", 'C', "circuitGAElite", 'F', "frameGtTungstenSteel", 'M', MetaItems.ELECTRIC_MOTOR_IV.getStackForm());

        //Cutting Machine Recipes
        for (MaterialStack stack : sawLubricants) {
            FluidMaterial material = (FluidMaterial) stack.material;
            int multiplier = (int) stack.amount;
            int time = multiplier == 1L ? 4 : 1;
            RecipeMaps.CUTTER_RECIPES.recipeBuilder().duration(600 / time).EUt(48).inputs(GAMetaItems.ASOC_WAFER.getStackForm()).fluidInputs(material.getFluid(22 * multiplier)).outputs(GAMetaItems.ASOC.getStackForm(6)).buildAndRegister();
            RecipeMaps.CUTTER_RECIPES.recipeBuilder().duration(600 / time).EUt(48).inputs(GAMetaItems.SOC_WAFER.getStackForm()).fluidInputs(material.getFluid(22 * multiplier)).outputs(GAMetaItems.SOC.getStackForm(6)).buildAndRegister();
            RecipeMaps.CUTTER_RECIPES.recipeBuilder().duration(600 / time).EUt(48).inputs(GAMetaItems.RAM_WAFER.getStackForm()).fluidInputs(material.getFluid(22 * multiplier)).outputs(GAMetaItems.RAM.getStackForm(32)).buildAndRegister();
            RecipeMaps.CUTTER_RECIPES.recipeBuilder().duration(600 / time).EUt(48).inputs(GAMetaItems.QBIT_CPU_WAFER.getStackForm()).fluidInputs(material.getFluid(22 * multiplier)).outputs(GAMetaItems.QBIT_CPU.getStackForm(5)).buildAndRegister();
            RecipeMaps.CUTTER_RECIPES.recipeBuilder().duration(600 / time).EUt(48).inputs(GAMetaItems.PIC_WAFER.getStackForm()).fluidInputs(material.getFluid(22 * multiplier)).outputs(GAMetaItems.PIC.getStackForm(4)).buildAndRegister();
            RecipeMaps.CUTTER_RECIPES.recipeBuilder().duration(600 / time).EUt(48).inputs(GAMetaItems.HPIC_WAFER.getStackForm()).fluidInputs(material.getFluid(22 * multiplier)).outputs(GAMetaItems.HPIC.getStackForm(2)).buildAndRegister();
            RecipeMaps.CUTTER_RECIPES.recipeBuilder().duration(600 / time).EUt(48).inputs(GAMetaItems.NOR_WAFER.getStackForm()).fluidInputs(material.getFluid(22 * multiplier)).outputs(GAMetaItems.NOR.getStackForm(16)).buildAndRegister();
            RecipeMaps.CUTTER_RECIPES.recipeBuilder().duration(600 / time).EUt(48).inputs(GAMetaItems.NAND_WAFER.getStackForm()).fluidInputs(material.getFluid(22 * multiplier)).outputs(GAMetaItems.NAND.getStackForm(32)).buildAndRegister();
            RecipeMaps.CUTTER_RECIPES.recipeBuilder().duration(600 / time).EUt(48).inputs(GAMetaItems.CPU_WAFER.getStackForm()).fluidInputs(material.getFluid(22 * multiplier)).outputs(GAMetaItems.CPU.getStackForm(8)).buildAndRegister();
            RecipeMaps.CUTTER_RECIPES.recipeBuilder().duration(600 / time).EUt(48).inputs(GAMetaItems.LOGIC_CIRCUIT_WAFER.getStackForm()).fluidInputs(material.getFluid(22)).outputs(GAMetaItems.LOGIC_CIRCUIT.getStackForm(8)).buildAndRegister();
            RecipeMaps.CUTTER_RECIPES.recipeBuilder().duration(600 / time).EUt(48).inputs(GAMetaItems.NANOCPU_WAFER.getStackForm()).fluidInputs(material.getFluid(22)).outputs(GAMetaItems.NANOCPU.getStackForm(7)).buildAndRegister();
            RecipeMaps.CUTTER_RECIPES.recipeBuilder().duration(200 / time).EUt(8).inputs(GAMetaItems.SILICON_BOULE.getStackForm()).fluidInputs(material.getFluid(multiplier)).outputs(GAMetaItems.SILICON_WAFER.getStackForm(16)).buildAndRegister();
            RecipeMaps.CUTTER_RECIPES.recipeBuilder().duration(400 / time).EUt(64).inputs(GAMetaItems.GLOWSTONE_BOULE.getStackForm()).fluidInputs(material.getFluid(multiplier)).outputs(GAMetaItems.GLOWSTONE_WAFER.getStackForm(32)).buildAndRegister();
            RecipeMaps.CUTTER_RECIPES.recipeBuilder().duration(800 / time).EUt(384).inputs(GAMetaItems.NAQUADAH_BOULE.getStackForm()).fluidInputs(material.getFluid(multiplier)).outputs(GAMetaItems.NAQUADAH_WAFER.getStackForm(64)).buildAndRegister();
            RecipeMaps.CUTTER_RECIPES.recipeBuilder().duration(960 / time).EUt(420).inputs(GAMetaItems.CRYSTAL_CPU.getStackForm()).fluidInputs(material.getFluid(2 * multiplier)).outputs(GAMetaItems.RAW_CRYSTAL_CHIP.getStackForm(2)).buildAndRegister();
        }

        //Circuit Rabbit Hole - Layer 3
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).notConsumable(new IntCircuitIngredient(0)).fluidInputs(Materials.Air.getFluid(1000), GAMaterials.Ethylene.getFluid(144)).fluidOutputs(Materials.Plastic.getFluid(144)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).notConsumable(new IntCircuitIngredient(0)).fluidInputs(Materials.Oxygen.getFluid(1000), GAMaterials.Ethylene.getFluid(144)).fluidOutputs(Materials.Plastic.getFluid(216)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(800).EUt(30).notConsumable(new IntCircuitIngredient(1)).fluidInputs(Materials.Air.getFluid(7500), GAMaterials.Ethylene.getFluid(2160), Materials.TitaniumTetrachloride.getFluid(100)).fluidOutputs(Materials.Plastic.getFluid(3240)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(800).EUt(30).notConsumable(new IntCircuitIngredient(1)).fluidInputs(Materials.Oxygen.getFluid(7500), GAMaterials.Ethylene.getFluid(2160), Materials.TitaniumTetrachloride.getFluid(100)).fluidOutputs(Materials.Plastic.getFluid(4320)).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(600).EUt(120).input(OrePrefix.dust, GAMaterials.FerriteMixture).fluidInputs(Materials.Oxygen.getFluid(2000)).outputs(OreDictUnifier.get(OrePrefix.ingot, GAMaterials.NickelZincFerrite)).blastFurnaceTemp(1500).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).notConsumable(new IntCircuitIngredient(0)).fluidInputs(Materials.Air.getFluid(1000), GAMaterials.VinylChloride.getFluid(144)).fluidOutputs(GAMaterials.PolyvinylChloride.getFluid(144)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).notConsumable(new IntCircuitIngredient(0)).fluidInputs(Materials.Oxygen.getFluid(1000), GAMaterials.VinylChloride.getFluid(144)).fluidOutputs(GAMaterials.PolyvinylChloride.getFluid(216)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(800).EUt(30).notConsumable(new IntCircuitIngredient(1)).fluidInputs(Materials.Air.getFluid(7500), GAMaterials.VinylChloride.getFluid(2160), Materials.TitaniumTetrachloride.getFluid(100)).fluidOutputs(GAMaterials.PolyvinylChloride.getFluid(3240)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(800).EUt(30).notConsumable(new IntCircuitIngredient(1)).fluidInputs(Materials.Oxygen.getFluid(7500), GAMaterials.VinylChloride.getFluid(2160), Materials.TitaniumTetrachloride.getFluid(100)).fluidOutputs(GAMaterials.PolyvinylChloride.getFluid(4320)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(30).input(OrePrefix.dust, GAMaterials.Polydimethylsiloxane, 9).input(OrePrefix.dust, Materials.Sulfur).fluidOutputs(GAMaterials.SiliconeRubber.getFluid(1296)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(96).input(OrePrefix.dust, Materials.Silicon).fluidInputs(Materials.Epichlorhydrin.getFluid(144)).fluidOutputs(GAMaterials.SiliconeRubber.getFluid(144)).buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(50).EUt(8).fluidInputs(GAMaterials.PolyvinylAcetate.getFluid(1000), GAMaterials.Acetone.getFluid(1500)).fluidOutputs(Materials.Glue.getFluid(2500)).buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(50).EUt(8).fluidInputs(GAMaterials.PolyvinylAcetate.getFluid(1000), GAMaterials.MethylAcetate.getFluid(1500)).fluidOutputs(Materials.Glue.getFluid(2500)).buildAndRegister();
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(300).EUt(5).inputs(MetaItems.RUBBER_DROP.getStackForm()).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.RawRubber, 3)).chancedOutput(MetaItems.PLANT_BALL.getStackForm(), 1000).fluidOutputs(Materials.Glue.getFluid(100)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(GAMaterials.HydrochloricAcid.getFluid(1000), GAMaterials.Acetone.getFluid(1000), GAMaterials.Phenol.getFluid(2000)).fluidOutputs(GAMaterials.BisphenolA.getFluid(1000), Materials.Water.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(8).fluidInputs(GAMaterials.SulfurTrioxide.getFluid(1000), Materials.Water.getFluid(1000)).fluidOutputs(Materials.SulfuricAcid.getFluid(1000)).buildAndRegister();
        RecipeMaps.ELECTROLYZER_RECIPES.recipeBuilder().duration(900).EUt(30).fluidInputs(GAMaterials.NickelSulfateSolution.getFluid(9000)).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.Nickel)).fluidOutputs(Materials.Oxygen.getFluid(1000), Materials.SulfuricAcid.getFluid(8000)).buildAndRegister();
        RecipeMaps.ELECTROLYZER_RECIPES.recipeBuilder().duration(900).EUt(30).fluidInputs(GAMaterials.BlueVitriolSolution.getFluid(9000)).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.Copper)).fluidOutputs(Materials.Oxygen.getFluid(1000), Materials.SulfuricAcid.getFluid(8000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).notConsumable(new IntCircuitIngredient(0)).fluidInputs(Materials.Air.getFluid(1000), GAMaterials.Tetrafluoroethylene.getFluid(144)).fluidOutputs(Materials.Polytetrafluoroethylene.getFluid(144)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).notConsumable(new IntCircuitIngredient(0)).fluidInputs(Materials.Oxygen.getFluid(1000), GAMaterials.Tetrafluoroethylene.getFluid(144)).fluidOutputs(Materials.Polytetrafluoroethylene.getFluid(216)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(800).EUt(30).notConsumable(new IntCircuitIngredient(1)).fluidInputs(Materials.Air.getFluid(7500), GAMaterials.Tetrafluoroethylene.getFluid(2160), Materials.TitaniumTetrachloride.getFluid(100)).fluidOutputs(Materials.Polytetrafluoroethylene.getFluid(3240)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(800).EUt(30).notConsumable(new IntCircuitIngredient(1)).fluidInputs(Materials.Oxygen.getFluid(7500), GAMaterials.Tetrafluoroethylene.getFluid(2160), Materials.TitaniumTetrachloride.getFluid(100)).fluidOutputs(Materials.Polytetrafluoroethylene.getFluid(4320)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(30).inputs(OreDictUnifier.get(OrePrefix.dust, GAMaterials.SodiumHydroxide)).fluidInputs(Materials.Epichlorhydrin.getFluid(1000), GAMaterials.BisphenolA.getFluid(1000)).fluidOutputs(Materials.Epoxid.getFluid(1000), GAMaterials.SaltWater.getFluid(1000)).buildAndRegister();
        ModHandler.addShapelessRecipe("reinforce_expoy_resin_1", OreDictUnifier.get(OrePrefix.dust, GAMaterials.ReinforcedEpoxyResin), OreDictUnifier.get(OrePrefix.dust, Materials.Epoxid), GAMetaItems.GLASS_FIBER.getStackForm());
        ModHandler.addShapelessRecipe("reinforce_expoy_resin_2", OreDictUnifier.get(OrePrefix.dust, GAMaterials.ReinforcedEpoxyResin), OreDictUnifier.get(OrePrefix.dust, Materials.Epoxid), GAMetaItems.RAW_CARBON_FIBERS.getStackForm());
        RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder().duration(240).EUt(16).inputs(GAMetaItems.GLASS_FIBER.getStackForm()).fluidInputs(Materials.Epoxid.getFluid(144)).outputs(OreDictUnifier.get(OrePrefix.plate, GAMaterials.ReinforcedEpoxyResin)).buildAndRegister();
        RecipeMaps.CHEMICAL_BATH_RECIPES.recipeBuilder().duration(240).EUt(16).inputs(GAMetaItems.RAW_CARBON_FIBERS.getStackForm()).fluidInputs(Materials.Epoxid.getFluid(144)).outputs(OreDictUnifier.get(OrePrefix.plate, GAMaterials.ReinforcedEpoxyResin)).buildAndRegister();
        RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder().duration(160).EUt(16).fluidInputs(GAMaterials.Polystyrene.getFluid(36)).notConsumable(MetaItems.SHAPE_MOLD_CYLINDER.getStackForm()).outputs(GAMetaItems.PETRI_DISH.getStackForm()).buildAndRegister();
        RecipeMaps.FLUID_SOLIDFICATION_RECIPES.recipeBuilder().duration(160).EUt(16).fluidInputs(Materials.Polytetrafluoroethylene.getFluid(36)).notConsumable(MetaItems.SHAPE_MOLD_CYLINDER.getStackForm()).outputs(GAMetaItems.PETRI_DISH.getStackForm()).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(900).EUt(480).blastFurnaceTemp(5000).inputs(GAMetaItems.RAW_CRYSTAL_CHIP.getStackForm()).input(OrePrefix.plate, Materials.Emerald).fluidInputs(Materials.Helium.getFluid(1000)).outputs(MetaItems.CIRCUIT_PARTS_CRYSTAL_CHIP_ELITE.getStackForm()).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(900).EUt(480).blastFurnaceTemp(5000).inputs(GAMetaItems.RAW_CRYSTAL_CHIP.getStackForm()).input(OrePrefix.plate, Materials.Olivine).fluidInputs(Materials.Helium.getFluid(1000)).outputs(MetaItems.CIRCUIT_PARTS_CRYSTAL_CHIP_ELITE.getStackForm()).buildAndRegister();
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(200).EUt(20).inputs(OreDictUnifier.get(OrePrefix.log, Materials.Wood)).chancedOutput(MetaItems.RUBBER_DROP.getStackForm(), 5000).chancedOutput(MetaItems.PLANT_BALL.getStackForm(), 3750).chancedOutput(OreDictUnifier.get(OrePrefix.dust, Materials.Carbon), 2500).chancedOutput(OreDictUnifier.get(OrePrefix.dust, Materials.Wood), 2500).fluidOutputs(Materials.Methane.getFluid(60)).buildAndRegister();
        RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder().duration(900).EUt(120).inputs(GAMetaItems.SILICON_WAFER.getStackForm()).notConsumable(OrePrefix.craftingLens, Color.Red).outputs(GAMetaItems.LOGIC_CIRCUIT_WAFER.getStackForm()).buildAndRegister();
        RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder().duration(500).EUt(480).inputs(GAMetaItems.GLOWSTONE_WAFER.getStackForm()).notConsumable(OrePrefix.craftingLens, Color.Red).outputs(GAMetaItems.LOGIC_CIRCUIT_WAFER.getStackForm(4)).buildAndRegister();
        RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder().duration(200).EUt(1920).inputs(GAMetaItems.NAQUADAH_WAFER.getStackForm()).notConsumable(OrePrefix.craftingLens, Color.Red).outputs(GAMetaItems.LOGIC_CIRCUIT_WAFER.getStackForm(8)).buildAndRegister();
        RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder().duration(900).EUt(120).inputs(GAMetaItems.SILICON_WAFER.getStackForm()).notConsumable(OrePrefix.lens, Materials.GreenSapphire).outputs(GAMetaItems.RAM_WAFER.getStackForm()).buildAndRegister();
        RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder().duration(500).EUt(480).inputs(GAMetaItems.GLOWSTONE_WAFER.getStackForm()).notConsumable(OrePrefix.lens, Materials.GreenSapphire).outputs(GAMetaItems.RAM_WAFER.getStackForm(4)).buildAndRegister();
        RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder().duration(200).EUt(1920).inputs(GAMetaItems.NAQUADAH_WAFER.getStackForm()).notConsumable(OrePrefix.lens, Materials.GreenSapphire).outputs(GAMetaItems.RAM_WAFER.getStackForm(8)).buildAndRegister();
        RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder().duration(500).EUt(480).inputs(GAMetaItems.GLOWSTONE_WAFER.getStackForm()).notConsumable(OrePrefix.lens, Materials.EnderPearl).outputs(GAMetaItems.NAND_WAFER.getStackForm()).buildAndRegister();
        RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder().duration(200).EUt(1920).inputs(GAMetaItems.NAQUADAH_WAFER.getStackForm()).notConsumable(OrePrefix.lens, Materials.EnderPearl).outputs(GAMetaItems.NAND_WAFER.getStackForm(4)).buildAndRegister();
        RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder().duration(500).EUt(480).inputs(GAMetaItems.GLOWSTONE_WAFER.getStackForm()).notConsumable(OrePrefix.lens, Materials.EnderEye).outputs(GAMetaItems.NOR_WAFER.getStackForm()).buildAndRegister();
        RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder().duration(200).EUt(1920).inputs(GAMetaItems.NAQUADAH_WAFER.getStackForm()).notConsumable(OrePrefix.lens, Materials.EnderEye).outputs(GAMetaItems.NOR_WAFER.getStackForm(4)).buildAndRegister();
        RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder().duration(900).EUt(120).inputs(GAMetaItems.SILICON_WAFER.getStackForm()).notConsumable(OrePrefix.craftingLens, Color.White).outputs(GAMetaItems.CPU_WAFER.getStackForm()).buildAndRegister();
        RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder().duration(500).EUt(480).inputs(GAMetaItems.GLOWSTONE_WAFER.getStackForm()).notConsumable(OrePrefix.craftingLens, Color.White).outputs(GAMetaItems.CPU_WAFER.getStackForm(4)).buildAndRegister();
        RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder().duration(200).EUt(1920).inputs(GAMetaItems.NAQUADAH_WAFER.getStackForm()).notConsumable(OrePrefix.craftingLens, Color.White).outputs(GAMetaItems.CPU_WAFER.getStackForm(8)).buildAndRegister();
        RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder().duration(200).EUt(1920).inputs(GAMetaItems.NAQUADAH_WAFER.getStackForm()).notConsumable(OrePrefix.lens, Materials.GarnetYellow).outputs(GAMetaItems.SOC_WAFER.getStackForm()).buildAndRegister();
        RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder().duration(200).EUt(1920).inputs(GAMetaItems.NAQUADAH_WAFER.getStackForm()).notConsumable(OrePrefix.craftingLens, Color.Orange).outputs(GAMetaItems.ASOC_WAFER.getStackForm()).buildAndRegister();
        RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder().duration(500).EUt(480).inputs(GAMetaItems.GLOWSTONE_WAFER.getStackForm()).notConsumable(OrePrefix.craftingLens, Color.Blue).outputs(GAMetaItems.PIC_WAFER.getStackForm()).buildAndRegister();
        RecipeMaps.LASER_ENGRAVER_RECIPES.recipeBuilder().duration(200).EUt(1920).inputs(GAMetaItems.NAQUADAH_WAFER.getStackForm()).notConsumable(OrePrefix.craftingLens, Color.Blue).outputs(GAMetaItems.PIC_WAFER.getStackForm(4)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(1200).EUt(1920).inputs(GAMetaItems.PIC_WAFER.getStackForm(), OreDictUnifier.get(OrePrefix.dust, GAMaterials.IndiumGalliumPhosphide, 2)).fluidInputs(Materials.RedAlloy.getFluid(288)).outputs(GAMetaItems.HPIC_WAFER.getStackForm()).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(400).EUt(1920).inputs(GAMetaItems.CPU_WAFER.getStackForm(), GAMetaItems.RAW_CARBON_FIBERS.getStackForm(16)).fluidInputs(Materials.Glowstone.getFluid(576)).outputs(GAMetaItems.NANOCPU_WAFER.getStackForm()).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(400).EUt(1920).inputs(GAMetaItems.NANOCPU_WAFER.getStackForm(), MetaItems.QUANTUM_EYE.getStackForm(2)).fluidInputs(GAMaterials.GalliumArsenide.getFluid(288)).outputs(GAMetaItems.QBIT_CPU_WAFER.getStackForm()).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(1920).inputs(GAMetaItems.NANOCPU_WAFER.getStackForm(), OreDictUnifier.get(OrePrefix.dust, GAMaterials.IndiumGalliumPhosphide)).fluidInputs(Materials.Radon.getFluid(50)).outputs(GAMetaItems.QBIT_CPU_WAFER.getStackForm()).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(10).EUt(30).notConsumable(new IntCircuitIngredient(0)).fluidInputs(Materials.Oxygen.getFluid(1000), Materials.Hydrogen.getFluid(2000)).fluidOutputs(Materials.DistilledWater.getFluid(3000)).buildAndRegister();
        RecipeMaps.EXTRACTOR_RECIPES.recipeBuilder().duration(300).EUt(1024).inputs(new ItemStack(Items.EGG)).chancedOutput(GAMetaItems.STEMCELLS.getStackForm(), 500).buildAndRegister();

        //Circuit Rabbit Hole - Layer 4
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(500).EUt(480).input(OrePrefix.dust, Materials.Carbon, 2).input(OrePrefix.dust, Materials.Rutile).fluidInputs(Materials.Chlorine.getFluid(4000)).fluidOutputs(GAMaterials.CarbonMonoxde.getFluid(2000), Materials.TitaniumTetrachloride.getFluid(1000)).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(9000).EUt(120).input(OrePrefix.dust, Materials.Silicon, 32).input(OrePrefix.dustTiny, Materials.Gallium).outputs(GAMetaItems.SILICON_BOULE.getStackForm()).blastFurnaceTemp(1784).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(12000).EUt(480).input(OrePrefix.dust, Materials.Silicon, 64).input(OrePrefix.dust, Materials.Glowstone, 8).fluidInputs(Materials.Nitrogen.getFluid(8000)).outputs(GAMetaItems.GLOWSTONE_BOULE.getStackForm()).blastFurnaceTemp(2484).buildAndRegister();
        RecipeMaps.BLAST_RECIPES.recipeBuilder().duration(1500).EUt(1920).inputs(OreDictUnifier.get(OrePrefix.block, Materials.Silicon, 15), OreDictUnifier.get(OrePrefix.ingot, Materials.Naquadah)).fluidInputs(Materials.Argon.getFluid(8000)).outputs(GAMetaItems.NAQUADAH_BOULE.getStackForm()).blastFurnaceTemp(5400).buildAndRegister();
        ModHandler.addShapelessRecipe("ferrite_mixture", OreDictUnifier.get(OrePrefix.dust, GAMaterials.FerriteMixture, 6), "dustNickel", "dustZinc", "dustIron", "dustIron", "dustIron", "dustIron");
        for (OrePrefix Prefix : Arrays.asList(OrePrefix.dust, OrePrefix.dustSmall, OrePrefix.dustTiny))
            RecipeMaps.MIXER_RECIPES.recipeBuilder().duration((int) (600L * Prefix.materialAmount / 3628800L)).EUt(8).input(Prefix, Materials.Nickel, 1).input(Prefix, Materials.Zinc, 1).input(Prefix, Materials.Iron, 4).outputs(OreDictUnifier.getDust(GAMaterials.FerriteMixture, 6L * Prefix.materialAmount)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(96).fluidInputs(GAMaterials.Dimethyldichlorosilane.getFluid(1000), Materials.Water.getFluid(1000)).outputs(OreDictUnifier.get(OrePrefix.dust, GAMaterials.Polydimethylsiloxane, 3)).fluidOutputs(GAMaterials.DilutedHydrochloricAcid.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(480).EUt(96).input(OrePrefix.dust, Materials.Silicon).fluidInputs(GAMaterials.HydrochloricAcid.getFluid(2000), GAMaterials.Methanol.getFluid(2000)).outputs(OreDictUnifier.get(OrePrefix.dust, GAMaterials.Polydimethylsiloxane, 3)).fluidOutputs(GAMaterials.DilutedHydrochloricAcid.getFluid(2000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(480).EUt(96).input(OrePrefix.dust, Materials.Silicon).notConsumable(new IntCircuitIngredient(2)).fluidInputs(Materials.Water.getFluid(1000), Materials.Chlorine.getFluid(4000), Materials.Methane.getFluid(2000)).outputs(OreDictUnifier.get(OrePrefix.dust, GAMaterials.Polydimethylsiloxane, 3)).fluidOutputs(GAMaterials.HydrochloricAcid.getFluid(2000), GAMaterials.DilutedHydrochloricAcid.getFluid(2000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(60).EUt(8).fluidInputs(Materials.Chlorine.getFluid(1000), Materials.Hydrogen.getFluid(1000)).fluidOutputs(GAMaterials.HydrochloricAcid.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(60).EUt(30).input(OrePrefix.dust, Materials.Salt, 2).fluidInputs(Materials.SulfuricAcid.getFluid(1000)).outputs(OreDictUnifier.get(OrePrefix.dust, GAMaterials.SodiumBisulfate)).fluidOutputs(GAMaterials.HydrochloricAcid.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(80).EUt(30).notConsumable(new IntCircuitIngredient(1)).fluidInputs(Materials.Chlorine.getFluid(6000), Materials.Methane.getFluid(1000)).fluidOutputs(GAMaterials.HydrochloricAcid.getFluid(3000), GAMaterials.Chloroform.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(80).EUt(30).notConsumable(new IntCircuitIngredient(3)).fluidInputs(Materials.Chlorine.getFluid(2000), Materials.Methane.getFluid(1000)).fluidOutputs(GAMaterials.HydrochloricAcid.getFluid(1000), GAMaterials.Chloromethane.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(120).EUt(30).fluidInputs(Materials.Chlorine.getFluid(2000), GAMaterials.Benzene.getFluid(500)).fluidOutputs(GAMaterials.HydrochloricAcid.getFluid(1000), GAMaterials.Dichlorobenzene.getFluid(500)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(GAMaterials.Propene.getFluid(1000), Materials.Chlorine.getFluid(2000)).fluidOutputs(GAMaterials.HydrochloricAcid.getFluid(1000), GAMaterials.AllylChloride.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.Chlorine.getFluid(2000), GAMaterials.Ethylene.getFluid(1000)).fluidOutputs(GAMaterials.VinylChloride.getFluid(1000), GAMaterials.HydrochloricAcid.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(30).input(OrePrefix.dust, Materials.Apatite).fluidInputs(Materials.Water.getFluid(10000), Materials.SulfuricAcid.getFluid(5000)).fluidOutputs(GAMaterials.HydrochloricAcid.getFluid(1000), GAMaterials.PhosphoricAcid.getFluid(3000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(200).EUt(30).fluidInputs(GAMaterials.SulfurDioxide.getFluid(1000), Materials.Oxygen.getFluid(1000)).fluidOutputs(GAMaterials.SulfurTrioxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(280).EUt(30).input(OrePrefix.dust, Materials.Sulfur).notConsumable(new IntCircuitIngredient(3)).fluidInputs(Materials.Oxygen.getFluid(3000)).fluidOutputs(GAMaterials.SulfurTrioxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(256).fluidInputs(GAMaterials.Chloroform.getFluid(1000), GAMaterials.HydrofluoricAcid.getFluid(2000)).fluidOutputs(GAMaterials.DilutedHydrochloricAcid.getFluid(3000), GAMaterials.Tetrafluoroethylene.getFluid(500)).buildAndRegister();
        RecipeMaps.ELECTROLYZER_RECIPES.recipeBuilder().duration(720).EUt(30).fluidInputs(GAMaterials.SaltWater.getFluid(2000)).outputs(OreDictUnifier.get(OrePrefix.dust, GAMaterials.SodiumHydroxide)).fluidOutputs(Materials.Chlorine.getFluid(1000), Materials.Hydrogen.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(40).EUt(8).input(OrePrefix.dust, Materials.Sodium).fluidInputs(Materials.Water.getFluid(1000)).outputs(OreDictUnifier.get(OrePrefix.dust, GAMaterials.SodiumHydroxide)).fluidOutputs(Materials.Hydrogen.getFluid(1000)).buildAndRegister();
        RecipeMaps.EXTRUDER_RECIPES.recipeBuilder().duration(160).EUt(96).input(OrePrefix.ingot, GAMaterials.BorosilicateGlass).notConsumable(MetaItems.SHAPE_EXTRUDER_WIRE.getStackForm()).outputs(GAMetaItems.GLASS_FIBER.getStackForm(8)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).notConsumable(new IntCircuitIngredient(0)).fluidInputs(Materials.Air.getFluid(1000), GAMaterials.Styrene.getFluid(144)).fluidOutputs(GAMaterials.Polystyrene.getFluid(144)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).notConsumable(new IntCircuitIngredient(0)).fluidInputs(Materials.Oxygen.getFluid(1000), GAMaterials.Styrene.getFluid(144)).fluidOutputs(GAMaterials.Polystyrene.getFluid(216)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(800).EUt(30).notConsumable(new IntCircuitIngredient(1)).fluidInputs(Materials.Air.getFluid(7500), GAMaterials.Styrene.getFluid(2160), Materials.TitaniumTetrachloride.getFluid(100)).fluidOutputs(GAMaterials.Polystyrene.getFluid(3240)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(800).EUt(30).notConsumable(new IntCircuitIngredient(1)).fluidInputs(Materials.Oxygen.getFluid(7500), GAMaterials.Styrene.getFluid(2160), Materials.TitaniumTetrachloride.getFluid(100)).fluidOutputs(GAMaterials.Polystyrene.getFluid(4320)).buildAndRegister();
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder().duration(12000).EUt(320).inputs(OreDictUnifier.get(OrePrefix.gemExquisite, Materials.Olivine)).fluidInputs(Materials.Europium.getFluid(16)).chancedOutput(GAMetaItems.RAW_CRYSTAL_CHIP.getStackForm(), 1000).buildAndRegister();
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder().duration(12000).EUt(320).inputs(OreDictUnifier.get(OrePrefix.gemExquisite, Materials.Emerald)).fluidInputs(Materials.Europium.getFluid(16)).chancedOutput(GAMetaItems.RAW_CRYSTAL_CHIP.getStackForm(), 1000).buildAndRegister();
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder().duration(150).EUt(6).input(OrePrefix.dust, Materials.Carbon).fluidInputs(Materials.Palladium.getFluid(1)).chancedOutput(GAMetaItems.RAW_CARBON_FIBERS.getStackForm(2), 9000).buildAndRegister();
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder().duration(150).EUt(6).input(OrePrefix.dust, Materials.Carbon).fluidInputs(Materials.Lutetium.getFluid(1)).chancedOutput(GAMetaItems.RAW_CARBON_FIBERS.getStackForm(2), 3333).buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(160).EUt(16).inputs(new ItemStack(Items.SUGAR, 4), OreDictUnifier.get(OrePrefix.dust, GAMaterials.Meat), OreDictUnifier.get(OrePrefix.dustTiny, Materials.Salt)).fluidInputs(Materials.DistilledWater.getFluid(4000)).fluidOutputs(GAMaterials.RawGrowthMedium.getFluid(4000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).notConsumable(new IntCircuitIngredient(3)).fluidInputs(Materials.Oxygen.getFluid(1000), GAMaterials.HydrochloricAcid.getFluid(1000), GAMaterials.Ethylene.getFluid(1000)).fluidOutputs(Materials.Water.getFluid(1000), GAMaterials.VinylChloride.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.Oxygen.getFluid(1000), GAMaterials.Cumene.getFluid(1000)).fluidOutputs(GAMaterials.Phenol.getFluid(1000), GAMaterials.Acetone.getFluid(1000)).buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(1200).EUt(2).input(OrePrefix.dust, Materials.Wood, 4).fluidInputs(Materials.SulfuricAcid.getFluid(1000)).outputs(new ItemStack(Items.COAL, 1, 1)).fluidOutputs(GAMaterials.DilutedSulfuricAcid.getFluid(1000)).buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(1200).EUt(2).inputs(new ItemStack(Items.SUGAR, 4)).fluidInputs(Materials.SulfuricAcid.getFluid(1000)).outputs(new ItemStack(Items.COAL, 1, 1)).fluidOutputs(GAMaterials.DilutedSulfuricAcid.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(180).EUt(30).fluidInputs(Materials.NitrationMixture.getFluid(3000), GAMaterials.Glycerol.getFluid(1000)).fluidOutputs(Materials.Glyceryl.getFluid(1000), GAMaterials.DilutedSulfuricAcid.getFluid(3000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(120).fluidInputs(Materials.SulfuricAcid.getFluid(1000), GAMaterials.AceticAcid.getFluid(1000)).fluidOutputs(GAMaterials.Ethenone.getFluid(1000), GAMaterials.DilutedSulfuricAcid.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(400).EUt(380).input(OrePrefix.dust, Materials.Calcite).fluidInputs(GAMaterials.AceticAcid.getFluid(4000)).fluidOutputs(GAMaterials.Acetone.getFluid(4000), Materials.CarbonDioxide.getFluid(4000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(400).EUt(380).input(OrePrefix.dust, GAMaterials.Quicklime).fluidInputs(GAMaterials.AceticAcid.getFluid(4000)).fluidOutputs(GAMaterials.Acetone.getFluid(4000), Materials.CarbonDioxide.getFluid(4000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(400).EUt(380).input(OrePrefix.dust, Materials.Calcium).fluidInputs(GAMaterials.AceticAcid.getFluid(4000)).fluidOutputs(GAMaterials.Acetone.getFluid(4000), Materials.CarbonDioxide.getFluid(4000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(30).fluidInputs(GAMaterials.Methanol.getFluid(1000), GAMaterials.AceticAcid.getFluid(1000)).fluidOutputs(GAMaterials.MethylAcetate.getFluid(1000), Materials.Water.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(480).EUt(30).fluidInputs(GAMaterials.Glycerol.getFluid(1000), GAMaterials.HydrochloricAcid.getFluid(1000)).fluidOutputs(Materials.Water.getFluid(2000), Materials.Epichlorhydrin.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(480).EUt(30).input(OrePrefix.dust, GAMaterials.SodiumHydroxide).fluidInputs(GAMaterials.AllylChloride.getFluid(1000), GAMaterials.HypochlorousAcid.getFluid(1000)).fluidOutputs(GAMaterials.SaltWater.getFluid(1000), Materials.Epichlorhydrin.getFluid(1000)).buildAndRegister();
        //RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(640).EUt(30).inputs(OreDictUnifier.get(OrePrefix.dust, GAMaterials.SodiumHydroxide)).fluidInputs(Materials.Water.getFluid(1000), Materials.Chlorine.getFluid(4000), GAMaterials.Propene.getFluid(1000)).fluidOutputs(GAMaterials.SaltWater.getFluid(1000), Materials.Epichlorhydrin.getFluid(1000), GAMaterials.HydrochloricAcid.getFluid(2000)).buildAndRegister();
        //RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(640).EUt(30).inputs(OreDictUnifier.get(OrePrefix.dust, GAMaterials.SodiumHydroxide)).fluidInputs(GAMaterials.HypochlorousAcid.getFluid(1000), Materials.Chlorine.getFluid(2000), GAMaterials.Propene.getFluid(1000)).fluidOutputs(GAMaterials.SaltWater.getFluid(1000), Materials.Epichlorhydrin.getFluid(1000), GAMaterials.HydrochloricAcid.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(60).EUt(8).input(OrePrefix.dust, Materials.Sulfur).fluidInputs(Materials.Hydrogen.getFluid(2000)).fluidOutputs(Materials.HydrogenSulfide.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.SulfuricLightFuel.getFluid(12000), Materials.Hydrogen.getFluid(2000)).fluidOutputs(Materials.HydrogenSulfide.getFluid(1000), Materials.LightFuel.getFluid(12000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.SulfuricHeavyFuel.getFluid(8000), Materials.Hydrogen.getFluid(2000)).fluidOutputs(Materials.HydrogenSulfide.getFluid(1000), Materials.HeavyFuel.getFluid(8000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.SulfuricNaphtha.getFluid(12000), Materials.Hydrogen.getFluid(2000)).fluidOutputs(Materials.HydrogenSulfide.getFluid(1000), Materials.Naphtha.getFluid(12000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.SulfuricGas.getFluid(16000), Materials.Hydrogen.getFluid(2000)).fluidOutputs(Materials.HydrogenSulfide.getFluid(1000), Materials.Gas.getFluid(16000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.NaturalGas.getFluid(16000), Materials.Hydrogen.getFluid(2000)).fluidOutputs(Materials.HydrogenSulfide.getFluid(1000), Materials.Gas.getFluid(16000)).buildAndRegister();
        ModHandler.addShapelessRecipe("indium_gallium_phosphide", OreDictUnifier.get(OrePrefix.dust, GAMaterials.IndiumGalliumPhosphide, 3), "dustIndium", "dustGallium", "dustPhosphorus");
        for (OrePrefix Prefix : Arrays.asList(OrePrefix.dust, OrePrefix.dustSmall, OrePrefix.dustTiny))
            RecipeMaps.MIXER_RECIPES.recipeBuilder().duration((int) (300L * Prefix.materialAmount / 3628800L)).EUt(8).input(Prefix, Materials.Indium).input(Prefix, Materials.Gallium).input(Prefix, Materials.Phosphorus).outputs(OreDictUnifier.getDust(GAMaterials.IndiumGalliumPhosphide, 3L * Prefix.materialAmount)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(Materials.Oxygen.getFluid(1000), GAMaterials.NitricOxide.getFluid(1000)).fluidOutputs(Materials.NitrogenDioxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(1250).EUt(30).notConsumable(new IntCircuitIngredient(1)).fluidInputs(Materials.Nitrogen.getFluid(1000), Materials.Oxygen.getFluid(2000)).fluidOutputs(Materials.NitrogenDioxide.getFluid(3000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).notConsumable(new IntCircuitIngredient(0)).fluidInputs(Materials.Air.getFluid(1000), GAMaterials.VinylAcetate.getFluid(144)).fluidOutputs(GAMaterials.PolyvinylAcetate.getFluid(144)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).notConsumable(new IntCircuitIngredient(0)).fluidInputs(Materials.Oxygen.getFluid(1000), GAMaterials.VinylAcetate.getFluid(144)).fluidOutputs(GAMaterials.PolyvinylAcetate.getFluid(216)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(800).EUt(30).notConsumable(new IntCircuitIngredient(1)).fluidInputs(Materials.Air.getFluid(7500), GAMaterials.VinylAcetate.getFluid(2160), Materials.TitaniumTetrachloride.getFluid(100)).fluidOutputs(GAMaterials.PolyvinylAcetate.getFluid(3240)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(800).EUt(30).notConsumable(new IntCircuitIngredient(1)).fluidInputs(Materials.Oxygen.getFluid(7500), GAMaterials.VinylAcetate.getFluid(2160), Materials.TitaniumTetrachloride.getFluid(100)).fluidOutputs(GAMaterials.PolyvinylAcetate.getFluid(4320)).buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(300).EUt(30).input(OrePrefix.dust, Materials.Gallium).input(OrePrefix.dust, Materials.Arsenic).outputs(OreDictUnifier.get(OrePrefix.dust, GAMaterials.GalliumArsenide, 2)).buildAndRegister();

        //Circuit Rabbit Hole - Layer 5
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(1484).EUt(5).fluidInputs(Materials.LiquidAir.getFluid(53000)).fluidOutputs(Materials.Nitrogen.getFluid(32000), Materials.Nitrogen.getFluid(8000), Materials.Oxygen.getFluid(11000), Materials.Argon.getFluid(1000), Materials.NobleGases.getFluid(1000)).buildAndRegister();
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(680).EUt(5).fluidInputs(Materials.NobleGases.getFluid(34000)).fluidOutputs(Materials.CarbonDioxide.getFluid(21000), Materials.Helium.getFluid(9000), Materials.Methane.getFluid(3000), Materials.Deuterium.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(120).EUt(96).fluidInputs(Materials.Hydrogen.getFluid(6000), Materials.CarbonDioxide.getFluid(1000)).fluidOutputs(Materials.Water.getFluid(1000), GAMaterials.Methanol.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(120).EUt(96).notConsumable(new IntCircuitIngredient(0)).fluidInputs(Materials.Hydrogen.getFluid(4000), GAMaterials.CarbonMonoxde.getFluid(1000)).fluidOutputs(GAMaterials.Methanol.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(96).input(OrePrefix.dust, Materials.Carbon).notConsumable(new IntCircuitIngredient(3)).fluidInputs(Materials.Hydrogen.getFluid(4000), Materials.Oxygen.getFluid(1000)).fluidOutputs(GAMaterials.Methanol.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(8).fluidInputs(Materials.Mercury.getFluid(1000), Materials.Water.getFluid(10000), Materials.Chlorine.getFluid(10000)).fluidOutputs(GAMaterials.HypochlorousAcid.getFluid(10000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(120).EUt(30).notConsumable(new IntCircuitIngredient(1)).fluidInputs(Materials.Water.getFluid(1000), Materials.Chlorine.getFluid(2000)).fluidOutputs(GAMaterials.DilutedHydrochloricAcid.getFluid(1000), GAMaterials.HypochlorousAcid.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(960).EUt(480).fluidInputs(GAMaterials.Dimethylamine.getFluid(1000), GAMaterials.Chloramine.getFluid(1000)).fluidOutputs(GAMaterials.Dimethylhydrazine.getFluid(1000), GAMaterials.DilutedHydrochloricAcid.getFluid(1000)).buildAndRegister();
        //RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(1040).EUt(480).fluidInputs(GAMaterials.Methanol.getFluid(2000), GAMaterials.Ammonia.getFluid(1000), GAMaterials.HypochlorousAcid.getFluid(1000)).fluidOutputs(GAMaterials.Dimethylhydrazine.getFluid(1000), GAMaterials.DilutedHydrochloricAcid.getFluid(1000), Materials.Water.getFluid(3000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(60).EUt(8).input(OrePrefix.dust, Materials.Sulfur).notConsumable(new IntCircuitIngredient(2)).fluidInputs(Materials.Oxygen.getFluid(2000)).fluidOutputs(GAMaterials.SulfurDioxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(120).EUt(30).fluidInputs(Materials.Oxygen.getFluid(3000), Materials.HydrogenSulfide.getFluid(1000)).fluidOutputs(Materials.Water.getFluid(1000), GAMaterials.SulfurDioxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(60).EUt(8).fluidInputs(Materials.Hydrogen.getFluid(1000), Materials.Fluorine.getFluid(1000)).fluidOutputs(GAMaterials.HydrofluoricAcid.getFluid(1000)).buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(40).EUt(8).input(OrePrefix.dust, Materials.Salt).fluidInputs(Materials.Water.getFluid(1000)).fluidOutputs(GAMaterials.SaltWater.getFluid(2000)).buildAndRegister();
        ModHandler.addShapelessRecipe("borosilicate_glass", OreDictUnifier.get(OrePrefix.dust, GAMaterials.BorosilicateGlass, 8), "dustBoron", "dustGlass", "dustGlass", "dustGlass", "dustGlass", "dustGlass", "dustGlass", "dustGlass");
        for (OrePrefix Prefix : Arrays.asList(OrePrefix.dust, OrePrefix.dustSmall, OrePrefix.dustTiny))
            RecipeMaps.MIXER_RECIPES.recipeBuilder().duration((int) (800L * Prefix.materialAmount / 3628800L)).EUt(8).input(Prefix, Materials.Boron, 1).input(Prefix, Materials.Glass, 7).outputs(OreDictUnifier.getDust(GAMaterials.BorosilicateGlass, 8L * Prefix.materialAmount)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(120).EUt(30).fluidInputs(GAMaterials.Ethylene.getFluid(1000), GAMaterials.Benzene.getFluid(1000)).fluidOutputs(Materials.Hydrogen.getFluid(2000), GAMaterials.Styrene.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(1920).EUt(30).fluidInputs(GAMaterials.PhosphoricAcid.getFluid(1000), GAMaterials.Benzene.getFluid(8000), GAMaterials.Propene.getFluid(8000)).fluidOutputs(GAMaterials.Cumene.getFluid(8000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(96).input(OrePrefix.dust, Materials.Silicon).fluidInputs(GAMaterials.Chloromethane.getFluid(2000)).fluidOutputs(GAMaterials.Dimethyldichlorosilane.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(100).EUt(30).notConsumable(new IntCircuitIngredient(2)).fluidInputs(Materials.Oxygen.getFluid(2000), GAMaterials.Ethylene.getFluid(1000)).fluidOutputs(GAMaterials.AceticAcid.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(300).EUt(30).fluidInputs(GAMaterials.CarbonMonoxde.getFluid(1000), GAMaterials.Methanol.getFluid(1000)).fluidOutputs(GAMaterials.AceticAcid.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(30).notConsumable(new IntCircuitIngredient(1)).fluidInputs(Materials.Hydrogen.getFluid(4000), GAMaterials.CarbonMonoxde.getFluid(2000)).fluidOutputs(GAMaterials.AceticAcid.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(480).EUt(30).input(OrePrefix.dust, Materials.Carbon, 2).notConsumable(new IntCircuitIngredient(4)).fluidInputs(Materials.Oxygen.getFluid(2000), Materials.Hydrogen.getFluid(4000)).fluidOutputs(GAMaterials.AceticAcid.getFluid(1000)).buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(80).EUt(16).input(OrePrefix.dust, GAMaterials.Quicklime).fluidInputs(GAMaterials.AceticAcid.getFluid(2000)).fluidOutputs(GAMaterials.CalciumAcetate.getFluid(2000)).buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(80).EUt(16).input(OrePrefix.dust, Materials.Calcium).fluidInputs(GAMaterials.AceticAcid.getFluid(2000)).fluidOutputs(GAMaterials.CalciumAcetate.getFluid(2000)).buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(240).EUt(16).input(OrePrefix.dust, Materials.Calcite).fluidInputs(GAMaterials.AceticAcid.getFluid(2000)).fluidOutputs(GAMaterials.CalciumAcetate.getFluid(2000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(50).EUt(600).input(OrePrefix.dust, Materials.Aluminium, 4).fluidInputs(GAMaterials.IndiumConcentrate.getFluid(8000)).outputs(OreDictUnifier.get(OrePrefix.dustTiny, Materials.Indium)).fluidOutputs(GAMaterials.LeadZincSolution.getFluid(8000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(30).EUt(240).notConsumable(new IntCircuitIngredient(3)).fluidInputs(Materials.NitrogenDioxide.getFluid(3000), Materials.Water.getFluid(1000)).fluidOutputs(GAMaterials.NitricOxide.getFluid(1000), Materials.NitricAcid.getFluid(2000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(30).notConsumable(new IntCircuitIngredient(1)).fluidInputs(Materials.Oxygen.getFluid(10000), GAMaterials.Ammonia.getFluid(4000)).fluidOutputs(GAMaterials.NitricOxide.getFluid(4000), Materials.Water.getFluid(6000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(180).EUt(30).notConsumable(new IntCircuitIngredient(3)).fluidInputs(Materials.Oxygen.getFluid(1000), GAMaterials.AceticAcid.getFluid(1000), GAMaterials.Ethylene.getFluid(1000)).fluidOutputs(Materials.Water.getFluid(1000), GAMaterials.VinylAcetate.getFluid(1000)).buildAndRegister();
        RecipeMaps.ELECTROLYZER_RECIPES.recipeBuilder().duration(200).EUt(30).input(OrePrefix.dust, Materials.Sphalerite, 2).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.Zinc), OreDictUnifier.get(OrePrefix.dust, Materials.Sulfur)).chancedOutput(OreDictUnifier.get(OrePrefix.dustTiny, Materials.Gallium), 2500).buildAndRegister();

        //Circuit Rabbit Hole - layer 6
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(40).EUt(8).input(OrePrefix.dust, Materials.Carbon).notConsumable(new IntCircuitIngredient(1)).fluidInputs(Materials.Oxygen.getFluid(1000)).fluidOutputs(GAMaterials.CarbonMonoxde.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(80).EUt(8).input(OrePrefix.gem, Materials.Charcoal).notConsumable(new IntCircuitIngredient(1)).fluidInputs(Materials.Oxygen.getFluid(1000)).outputs(OreDictUnifier.get(OrePrefix.dustTiny, Materials.Ash)).fluidOutputs(GAMaterials.CarbonMonoxde.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(80).EUt(8).input(OrePrefix.gem, Materials.Coal).notConsumable(new IntCircuitIngredient(1)).fluidInputs(Materials.Oxygen.getFluid(1000)).outputs(OreDictUnifier.get(OrePrefix.dustTiny, Materials.Ash)).fluidOutputs(GAMaterials.CarbonMonoxde.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(80).EUt(8).input(OrePrefix.dust, Materials.Charcoal).notConsumable(new IntCircuitIngredient(1)).fluidInputs(Materials.Oxygen.getFluid(1000)).outputs(OreDictUnifier.get(OrePrefix.dustTiny, Materials.Ash)).fluidOutputs(GAMaterials.CarbonMonoxde.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(80).EUt(8).input(OrePrefix.dust, Materials.Coal).notConsumable(new IntCircuitIngredient(1)).fluidInputs(Materials.Oxygen.getFluid(1000)).outputs(OreDictUnifier.get(OrePrefix.dustTiny, Materials.Ash)).fluidOutputs(GAMaterials.CarbonMonoxde.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(800).EUt(8).input(OrePrefix.dust, Materials.Carbon).fluidInputs(Materials.CarbonDioxide.getFluid(1000)).fluidOutputs(GAMaterials.CarbonMonoxde.getFluid(2000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(384).notConsumable(new IntCircuitIngredient(1)).fluidInputs(Materials.Hydrogen.getFluid(3000), Materials.Nitrogen.getFluid(1000)).fluidOutputs(GAMaterials.Ammonia.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).fluidInputs(GAMaterials.HypochlorousAcid.getFluid(1000), GAMaterials.Ammonia.getFluid(1000)).fluidOutputs(Materials.Water.getFluid(1000), GAMaterials.Chloramine.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(120).fluidInputs(GAMaterials.Ammonia.getFluid(1000), GAMaterials.Methanol.getFluid(2000)).fluidOutputs(Materials.Water.getFluid(2000), GAMaterials.Dimethylamine.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(40).EUt(30).input(OrePrefix.dust, GAMaterials.PhosphorousPentoxide).fluidInputs(Materials.Water.getFluid(6000)).fluidOutputs(GAMaterials.PhosphoricAcid.getFluid(4000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(30).input(OrePrefix.dust, Materials.Phosphorus).fluidInputs(Materials.Water.getFluid(1500), Materials.Oxygen.getFluid(2500)).fluidOutputs(GAMaterials.PhosphoricAcid.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(30).notConsumable(new IntCircuitIngredient(1)).fluidInputs(GAMaterials.HydrochloricAcid.getFluid(1000), GAMaterials.Methanol.getFluid(1000)).fluidOutputs(Materials.Water.getFluid(1000), GAMaterials.Chloromethane.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(60).EUt(150).inputs(OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Sphalerite), OreDictUnifier.get(OrePrefix.crushedPurified, Materials.Galena)).fluidInputs(Materials.SulfuricAcid.getFluid(4000)).fluidOutputs(GAMaterials.IndiumConcentrate.getFluid(8000)).buildAndRegister();

        //Circuit Rabbit Hole - Layer 7
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(40).EUt(30).input(OrePrefix.dust, Materials.Phosphorus, 4).fluidInputs(Materials.Oxygen.getFluid(10000)).outputs(OreDictUnifier.get(OrePrefix.dust, GAMaterials.PhosphorousPentoxide, 14)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(40).EUt(8).input(OrePrefix.dust, Materials.Carbon).notConsumable(new IntCircuitIngredient(2)).fluidInputs(Materials.Oxygen.getFluid(2000)).fluidOutputs(Materials.CarbonDioxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(80).EUt(8).input(OrePrefix.gem, Materials.Charcoal).notConsumable(new IntCircuitIngredient(2)).fluidInputs(Materials.Oxygen.getFluid(2000)).outputs(OreDictUnifier.get(OrePrefix.dustTiny, Materials.Ash)).fluidOutputs(Materials.CarbonDioxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(80).EUt(8).input(OrePrefix.gem, Materials.Coal).notConsumable(new IntCircuitIngredient(2)).fluidInputs(Materials.Oxygen.getFluid(2000)).outputs(OreDictUnifier.get(OrePrefix.dustTiny, Materials.Ash)).fluidOutputs(Materials.CarbonDioxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(80).EUt(8).input(OrePrefix.dust, Materials.Charcoal).notConsumable(new IntCircuitIngredient(2)).fluidInputs(Materials.Oxygen.getFluid(2000)).outputs(OreDictUnifier.get(OrePrefix.dustTiny, Materials.Ash)).fluidOutputs(Materials.CarbonDioxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(80).EUt(8).input(OrePrefix.dust, Materials.Coal).notConsumable(new IntCircuitIngredient(2)).fluidInputs(Materials.Oxygen.getFluid(2000)).outputs(OreDictUnifier.get(OrePrefix.dustTiny, Materials.Ash)).fluidOutputs(Materials.CarbonDioxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(150).EUt(480).notConsumable(new IntCircuitIngredient(1)).fluidInputs(Materials.Water.getFluid(2000), Materials.Methane.getFluid(1000)).fluidOutputs(Materials.Hydrogen.getFluid(8000), Materials.CarbonDioxide.getFluid(1000)).buildAndRegister();
    }

    public static void init2() {
        //Fuel Rabbit Hole - Layer 1
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(20).EUt(480).fluidInputs(GAMaterials.BioDiesel.getFluid(1000), GAMaterials.Tetranitromethane.getFluid(40)).fluidOutputs(Materials.NitroFuel.getFluid(750)).buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(20).EUt(480).fluidInputs(Materials.Fuel.getFluid(1000), GAMaterials.Tetranitromethane.getFluid(20)).fluidOutputs(Materials.NitroFuel.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(1000).EUt(388).notConsumable(new IntCircuitIngredient(1)).fluidInputs(Materials.NitrogenDioxide.getFluid(1000), Materials.Hydrogen.getFluid(3000), Materials.Oxygen.getFluid(500)).fluidOutputs(Materials.Water.getFluid(4000), GAMaterials.RocketFuel.getFluid(3000)).buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(60).EUt(16).fluidInputs(Materials.Oxygen.getFluid(1000), GAMaterials.Dimethylhydrazine.getFluid(1000)).fluidOutputs(GAMaterials.RocketFuel.getFluid(3000)).buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(60).EUt(16).fluidInputs(GAMaterials.DinitrogenTetroxide.getFluid(1000), GAMaterials.Dimethylhydrazine.getFluid(1000)).fluidOutputs(GAMaterials.RocketFuel.getFluid(6000)).buildAndRegister();
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(20).EUt(5).fluidInputs(GAMaterials.Butane.getFluid(320)).fluidOutputs(Materials.LPG.getFluid(370)).buildAndRegister();
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(20).EUt(5).fluidInputs(GAMaterials.Propane.getFluid(320)).fluidOutputs(Materials.LPG.getFluid(290)).buildAndRegister();
        RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(16).EUt(120).fluidInputs(Materials.LightFuel.getFluid(5000), Materials.HeavyFuel.getFluid(1000)).fluidOutputs(Materials.Fuel.getFluid(6000)).buildAndRegister();

        //Fuel Rabbit Hole - Layer 2
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(480).EUt(120).fluidInputs(Materials.NitricAcid.getFluid(8000), GAMaterials.Ethenone.getFluid(1000)).fluidOutputs(GAMaterials.Tetranitromethane.getFluid(2000), Materials.Water.getFluid(9000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(480).EUt(30).notConsumable(new IntCircuitIngredient(3)).fluidInputs(Materials.Oxygen.getFluid(7000), GAMaterials.Ammonia.getFluid(2000)).fluidOutputs(GAMaterials.DinitrogenTetroxide.getFluid(1000), Materials.Water.getFluid(3000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(640).EUt(30).notConsumable(new IntCircuitIngredient(2)).fluidInputs(Materials.NitrogenDioxide.getFluid(2000)).fluidOutputs(GAMaterials.DinitrogenTetroxide.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(1100).EUt(480).notConsumable(new IntCircuitIngredient(23)).fluidInputs(Materials.Oxygen.getFluid(7000), Materials.Nitrogen.getFluid(2000), Materials.Hydrogen.getFluid(6000)).fluidOutputs(GAMaterials.DinitrogenTetroxide.getFluid(1000), Materials.Water.getFluid(3000)).buildAndRegister();

        //Fuel Rabbit Hole - Layer 3
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(30).notConsumable(new IntCircuitIngredient(2)).fluidInputs(Materials.Oxygen.getFluid(4000), GAMaterials.Ammonia.getFluid(1000)).fluidOutputs(Materials.NitricAcid.getFluid(1000), Materials.Water.getFluid(1000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(240).EUt(30).notConsumable(new IntCircuitIngredient(4)).fluidInputs(Materials.Water.getFluid(1000), Materials.Oxygen.getFluid(1000), Materials.NitrogenDioxide.getFluid(2000)).fluidOutputs(Materials.NitricAcid.getFluid(2000)).buildAndRegister();
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(320).EUt(480).notConsumable(new IntCircuitIngredient(24)).fluidInputs(Materials.Oxygen.getFluid(4000), Materials.Nitrogen.getFluid(1000), Materials.Hydrogen.getFluid(3000)).fluidOutputs(Materials.NitricAcid.getFluid(1000), Materials.Water.getFluid(1000)).buildAndRegister();

        //Assline Recipes
        GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(
                OreDictUnifier.get(OrePrefix.stickLong, Materials.NeodymiumMagnetic, 1),
                OreDictUnifier.get(OrePrefix.stickLong, Materials.HSSG, 2),
                OreDictUnifier.get(OrePrefix.wireFine, Materials.AnnealedCopper, 64),
                OreDictUnifier.get(OrePrefix.wireFine, Materials.AnnealedCopper, 64),
                OreDictUnifier.get(OrePrefix.wireFine, Materials.AnnealedCopper, 64),
                OreDictUnifier.get(OrePrefix.wireFine, Materials.AnnealedCopper, 64),
                OreDictUnifier.get(OrePrefix.cableGtSingle, Materials.YttriumBariumCuprate, 2)).fluidInputs(
                Materials.SolderingAlloy.getFluid(144),
                Materials.Lubricant.getFluid(250))
                .outputs(MetaItems.ELECTRIC_MOTOR_LUV.getStackForm()).duration(600).EUt(10240)
                .buildAndRegister();

        GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(
                OreDictUnifier.get(OrePrefix.stickLong, Materials.NeodymiumMagnetic, 2),
                OreDictUnifier.get(OrePrefix.stickLong, Materials.HSSE, 4),
                OreDictUnifier.get(OrePrefix.ring, Materials.HSSE, 4),
                OreDictUnifier.get(OrePrefix.valueOf("round"), Materials.HSSE, 16),
                OreDictUnifier.get(OrePrefix.wireFine, Materials.Platinum, 64),
                OreDictUnifier.get(OrePrefix.wireFine, Materials.Platinum, 64),
                OreDictUnifier.get(OrePrefix.wireFine, Materials.Platinum, 64),
                OreDictUnifier.get(OrePrefix.wireFine, Materials.Platinum, 64),
                OreDictUnifier.get(OrePrefix.cableGtQuadruple, Materials.VanadiumGallium, 2)).fluidInputs(
                Materials.SolderingAlloy.getFluid(288),
                Materials.Lubricant.getFluid(750))
                .outputs(MetaItems.ELECTRIC_MOTOR_ZPM.getStackForm()).duration(600).EUt(40960)
                .buildAndRegister();

        GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(
                OreDictUnifier.get(OrePrefix.block, Materials.NeodymiumMagnetic, 1),
                OreDictUnifier.get(OrePrefix.stickLong, GAMaterials.Neutronium, 4),
                OreDictUnifier.get(OrePrefix.ring, GAMaterials.Neutronium, 4),
                OreDictUnifier.get(OrePrefix.valueOf("round"), GAMaterials.Neutronium, 16),
                OreDictUnifier.get(OrePrefix.wireGtSingle, Tier.Superconductor, 64),
                OreDictUnifier.get(OrePrefix.wireGtSingle, Tier.Superconductor, 64),
                OreDictUnifier.get(OrePrefix.wireGtSingle, Tier.Superconductor, 64),
                OreDictUnifier.get(OrePrefix.wireGtSingle, Tier.Superconductor, 64),
                OreDictUnifier.get(OrePrefix.cableGtQuadruple, Materials.NiobiumTitanium, 2)).fluidInputs(
                Materials.SolderingAlloy.getFluid(1296),
                Materials.Lubricant.getFluid(2000))
                .outputs(MetaItems.ELECTRIC_MOTOR_UV.getStackForm()).duration(600).EUt(163840)
                .buildAndRegister();

        GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(
                MetaItems.ELECTRIC_MOTOR_LUV.getStackForm(),
                OreDictUnifier.get(OrePrefix.pipeSmall, GAMaterials.Ultimate, 2),
                OreDictUnifier.get(OrePrefix.plate, Materials.HSSG, 2),
                OreDictUnifier.get(OrePrefix.screw, Materials.HSSG, 8),
                OreDictUnifier.get(OrePrefix.ring, GAMaterials.SiliconeRubber, 4),
                OreDictUnifier.get(OrePrefix.rotor, Materials.HSSG, 2),
                OreDictUnifier.get(OrePrefix.cableGtSingle, Materials.YttriumBariumCuprate, 2)).fluidInputs(
                Materials.SolderingAlloy.getFluid(144),
                Materials.Lubricant.getFluid(250))
                .outputs(MetaItems.ELECTRIC_PUMP_LUV.getStackForm()).duration(600).EUt(15360)
                .buildAndRegister();

        GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(
                MetaItems.ELECTRIC_MOTOR_ZPM.getStackForm(),
                OreDictUnifier.get(OrePrefix.pipeMedium, GAMaterials.Ultimate, 2),
                OreDictUnifier.get(OrePrefix.plate, Materials.HSSE, 2),
                OreDictUnifier.get(OrePrefix.screw, Materials.HSSE, 8),
                OreDictUnifier.get(OrePrefix.ring, GAMaterials.SiliconeRubber, 16),
                OreDictUnifier.get(OrePrefix.rotor, Materials.HSSE, 2),
                OreDictUnifier.get(OrePrefix.cableGtQuadruple, Materials.VanadiumGallium, 2)).fluidInputs(
                Materials.SolderingAlloy.getFluid(288),
                Materials.Lubricant.getFluid(750))
                .outputs(MetaItems.ELECTRIC_PUMP_ZPM.getStackForm()).duration(600).EUt(61440)
                .buildAndRegister();

        GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(
                MetaItems.ELECTRIC_MOTOR_UV.getStackForm(),
                OreDictUnifier.get(OrePrefix.pipeLarge, GAMaterials.Ultimate, 2),
                OreDictUnifier.get(OrePrefix.plate, GAMaterials.Neutronium, 2),
                OreDictUnifier.get(OrePrefix.screw, GAMaterials.Neutronium, 8),
                OreDictUnifier.get(OrePrefix.ring, GAMaterials.SiliconeRubber, 16),
                OreDictUnifier.get(OrePrefix.rotor, GAMaterials.Neutronium, 2),
                OreDictUnifier.get(OrePrefix.cableGtQuadruple, Materials.NiobiumTitanium, 2)).fluidInputs(
                Materials.SolderingAlloy.getFluid(1296),
                Materials.Lubricant.getFluid(2000))
                .outputs(MetaItems.ELECTRIC_PUMP_UV.getStackForm()).duration(600).EUt(245760)
                .buildAndRegister();

        GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(
                MetaItems.ELECTRIC_MOTOR_LUV.getStackForm(2),
                OreDictUnifier.get(OrePrefix.plate, Materials.HSSG, 2),
                OreDictUnifier.get(OrePrefix.ring, Materials.HSSG, 4),
                OreDictUnifier.get(OrePrefix.valueOf("round"), Materials.HSSG, 32),
                OreDictUnifier.get(OrePrefix.cableGtSingle, Materials.YttriumBariumCuprate, 2))
                .notConsumable(new IntCircuitIngredient(1)).fluidInputs(
                GAMaterials.StyreneButadieneRubber.getFluid(1440),
                Materials.Lubricant.getFluid(250))
                .outputs(MetaItems.CONVEYOR_MODULE_LUV.getStackForm()).duration(600).EUt(15360)
                .buildAndRegister();

        GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(
                MetaItems.ELECTRIC_MOTOR_ZPM.getStackForm(2),
                OreDictUnifier.get(OrePrefix.plate, Materials.HSSE, 2),
                OreDictUnifier.get(OrePrefix.ring, Materials.HSSE, 4),
                OreDictUnifier.get(OrePrefix.valueOf("round"), Materials.HSSE, 32),
                OreDictUnifier.get(OrePrefix.cableGtQuadruple, Materials.VanadiumGallium, 2))
                .notConsumable(new IntCircuitIngredient(1)).fluidInputs(
                GAMaterials.StyreneButadieneRubber.getFluid(2880),
                Materials.Lubricant.getFluid(750))
                .outputs(MetaItems.CONVEYOR_MODULE_ZPM.getStackForm()).duration(600).EUt(61440)
                .buildAndRegister();

        GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(
                MetaItems.ELECTRIC_MOTOR_UV.getStackForm(2),
                OreDictUnifier.get(OrePrefix.plate, GAMaterials.Neutronium, 2),
                OreDictUnifier.get(OrePrefix.ring, GAMaterials.Neutronium, 4),
                OreDictUnifier.get(OrePrefix.valueOf("round"), GAMaterials.Neutronium, 32),
                OreDictUnifier.get(OrePrefix.cableGtQuadruple, Materials.NiobiumTitanium, 2))
                .notConsumable(new IntCircuitIngredient(1)).fluidInputs(
                GAMaterials.StyreneButadieneRubber.getFluid(2880),
                Materials.Lubricant.getFluid(2000))
                .outputs(MetaItems.CONVEYOR_MODULE_UV.getStackForm()).duration(600).EUt(245760)
                .buildAndRegister();

        GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(
                MetaItems.ELECTRIC_MOTOR_LUV.getStackForm(),
                OreDictUnifier.get(OrePrefix.plate, Materials.HSSG, 6),
                OreDictUnifier.get(OrePrefix.ring, Materials.HSSG, 4),
                OreDictUnifier.get(OrePrefix.valueOf("round"), Materials.HSSG, 32),
                OreDictUnifier.get(OrePrefix.stick, Materials.HSSG, 4),
                OreDictUnifier.get(OrePrefix.gear, Materials.HSSG, 1),
                OreDictUnifier.get(OrePrefix.gearSmall, Materials.HSSG, 2),
                OreDictUnifier.get(OrePrefix.cableGtSingle, Materials.YttriumBariumCuprate, 4))
                .notConsumable(new IntCircuitIngredient(2)).fluidInputs(
                Materials.SolderingAlloy.getFluid(144),
                Materials.Lubricant.getFluid(250))
                .outputs(MetaItems.ELECTRIC_PISTON_LUV.getStackForm()).duration(600).EUt(15360)
                .buildAndRegister();


        GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(
                MetaItems.ELECTRIC_MOTOR_ZPM.getStackForm(),
                OreDictUnifier.get(OrePrefix.plate, Materials.HSSE, 6),
                OreDictUnifier.get(OrePrefix.ring, Materials.HSSE, 4),
                OreDictUnifier.get(OrePrefix.valueOf("round"), Materials.HSSE, 32),
                OreDictUnifier.get(OrePrefix.stick, Materials.HSSE, 4),
                OreDictUnifier.get(OrePrefix.gear, Materials.HSSE, 1),
                OreDictUnifier.get(OrePrefix.gearSmall, Materials.HSSE, 2),
                OreDictUnifier.get(OrePrefix.cableGtQuadruple, Materials.VanadiumGallium, 4))
                .notConsumable(new IntCircuitIngredient(2)).fluidInputs(
                Materials.SolderingAlloy.getFluid(288),
                Materials.Lubricant.getFluid(750))
                .outputs(MetaItems.ELECTRIC_PISTON_ZPM.getStackForm()).duration(600).EUt(61440)
                .buildAndRegister();

        GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(
                MetaItems.ELECTRIC_MOTOR_UV.getStackForm(),
                OreDictUnifier.get(OrePrefix.plate, GAMaterials.Neutronium, 6),
                OreDictUnifier.get(OrePrefix.ring, GAMaterials.Neutronium, 4),
                OreDictUnifier.get(OrePrefix.valueOf("round"), GAMaterials.Neutronium, 32),
                OreDictUnifier.get(OrePrefix.stick, GAMaterials.Neutronium, 4),
                OreDictUnifier.get(OrePrefix.gear, GAMaterials.Neutronium, 1),
                OreDictUnifier.get(OrePrefix.gearSmall, GAMaterials.Neutronium, 2),
                OreDictUnifier.get(OrePrefix.cableGtQuadruple, Materials.NiobiumTitanium, 4))
                .notConsumable(new IntCircuitIngredient(2)).fluidInputs(
                Materials.SolderingAlloy.getFluid(1296),
                Materials.Lubricant.getFluid(2000))
                .outputs(MetaItems.ELECTRIC_PISTON_UV.getStackForm()).duration(600).EUt(245760)
                .buildAndRegister();

        GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(
                OreDictUnifier.get(OrePrefix.stickLong, Materials.HSSG, 4),
                OreDictUnifier.get(OrePrefix.gear, Materials.HSSG, 1),
                OreDictUnifier.get(OrePrefix.gearSmall, Materials.HSSG, 3),
                MetaItems.ELECTRIC_MOTOR_LUV.getStackForm(2),
                MetaItems.ELECTRIC_PISTON_LUV.getStackForm(),
                OreDictUnifier.get(OrePrefix.cableGtSingle, Materials.YttriumBariumCuprate, 6))
                .input(OrePrefix.valueOf("circuitGA"), Tier.Master, 2)
                .input(OrePrefix.valueOf("circuitGA"), Tier.Elite, 2)
                .input(OrePrefix.valueOf("circuitGA"), GAMaterials.Extreme, 6).fluidInputs(
                Materials.SolderingAlloy.getFluid(576),
                Materials.Lubricant.getFluid(250))
                .outputs(MetaItems.ROBOT_ARM_LUV.getStackForm()).duration(600).EUt(20480)
                .buildAndRegister();

        GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(
                OreDictUnifier.get(OrePrefix.stickLong, Materials.HSSE, 4),
                OreDictUnifier.get(OrePrefix.gear, Materials.HSSE, 1),
                OreDictUnifier.get(OrePrefix.gearSmall, Materials.HSSE, 3),
                MetaItems.ELECTRIC_MOTOR_ZPM.getStackForm(2),
                MetaItems.ELECTRIC_PISTON_ZPM.getStackForm(),
                OreDictUnifier.get(OrePrefix.cableGtQuadruple, Materials.VanadiumGallium, 6))
                .input(OrePrefix.valueOf("circuitGA"), Tier.Master, 4)
                .input(OrePrefix.valueOf("circuitGA"), Tier.Elite, 4)
                .input(OrePrefix.valueOf("circuitGA"), GAMaterials.Extreme, 12).fluidInputs(
                Materials.SolderingAlloy.getFluid(1152),
                Materials.Lubricant.getFluid(750))
                .outputs(MetaItems.ROBOT_ARM_ZPM.getStackForm()).duration(600).EUt(81920)
                .buildAndRegister();

        GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(
                OreDictUnifier.get(OrePrefix.stickLong, GAMaterials.Neutronium, 4),
                OreDictUnifier.get(OrePrefix.gear, GAMaterials.Neutronium, 1),
                OreDictUnifier.get(OrePrefix.gearSmall, GAMaterials.Neutronium, 3),
                MetaItems.ELECTRIC_MOTOR_UV.getStackForm(2),
                MetaItems.ELECTRIC_PISTON_UV.getStackForm(),
                OreDictUnifier.get(OrePrefix.cableGtQuadruple, Materials.NiobiumTitanium, 6))
                .input(OrePrefix.valueOf("circuitGA"), Tier.Master, 8)
                .input(OrePrefix.valueOf("circuitGA"), Tier.Elite, 8)
                .input(OrePrefix.valueOf("circuitGA"), GAMaterials.Extreme, 24).fluidInputs(
                Materials.SolderingAlloy.getFluid(2304),
                Materials.Lubricant.getFluid(2000))
                .outputs(MetaItems.ROBOT_ARM_UV.getStackForm()).duration(600).EUt(327680)
                .buildAndRegister();

        GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(
                OreDictUnifier.get(OrePrefix.frameGt, Materials.HSSG, 1),
                MetaItems.EMITTER_IV.getStackForm(),
                MetaItems.EMITTER_EV.getStackForm(2),
                MetaItems.EMITTER_HV.getStackForm(4),
                OreDictUnifier.get(OrePrefix.foil, Materials.Electrum, 64),
                OreDictUnifier.get(OrePrefix.foil, Materials.Electrum, 64),
                OreDictUnifier.get(OrePrefix.foil, Materials.Electrum, 64),
                OreDictUnifier.get(OrePrefix.cableGtSingle, Materials.YttriumBariumCuprate, 7))
                .input(OrePrefix.valueOf("circuitGA"), GAMaterials.Extreme, 7).fluidInputs(
                Materials.SolderingAlloy.getFluid(576))
                .outputs(MetaItems.EMITTER_LUV.getStackForm()).duration(600).EUt(15360)
                .buildAndRegister();

        GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(
                OreDictUnifier.get(OrePrefix.frameGt, Materials.HSSE, 1),
                MetaItems.EMITTER_LUV.getStackForm(),
                MetaItems.EMITTER_IV.getStackForm(2),
                MetaItems.EMITTER_EV.getStackForm(4),
                OreDictUnifier.get(OrePrefix.foil, Materials.Platinum, 64),
                OreDictUnifier.get(OrePrefix.foil, Materials.Platinum, 64),
                OreDictUnifier.get(OrePrefix.foil, Materials.Platinum, 64),
                OreDictUnifier.get(OrePrefix.cableGtQuadruple, Materials.VanadiumGallium, 7))
                .input(OrePrefix.valueOf("circuitGA"), Tier.Elite, 7).fluidInputs(
                Materials.SolderingAlloy.getFluid(576))
                .outputs(MetaItems.EMITTER_ZPM.getStackForm()).duration(600).EUt(61440)
                .buildAndRegister();

        GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(
                OreDictUnifier.get(OrePrefix.frameGt, GAMaterials.Neutronium, 1),
                MetaItems.EMITTER_ZPM.getStackForm(),
                MetaItems.EMITTER_LUV.getStackForm(2),
                MetaItems.EMITTER_IV.getStackForm(4),
                OreDictUnifier.get(OrePrefix.foil, Materials.Osmiridium, 64),
                OreDictUnifier.get(OrePrefix.foil, Materials.Osmiridium, 64),
                OreDictUnifier.get(OrePrefix.foil, Materials.Osmiridium, 64),
                OreDictUnifier.get(OrePrefix.cableGtQuadruple, Materials.NiobiumTitanium, 7))
                .input(OrePrefix.valueOf("circuitGA"), Tier.Master, 7).fluidInputs(
                Materials.SolderingAlloy.getFluid(576))
                .outputs(MetaItems.EMITTER_UV.getStackForm()).duration(600).EUt(245760)
                .buildAndRegister();

        GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(
                OreDictUnifier.get(OrePrefix.frameGt, Materials.HSSG, 1),
                MetaItems.SENSOR_IV.getStackForm(),
                MetaItems.SENSOR_EV.getStackForm(2),
                MetaItems.SENSOR_HV.getStackForm(4),
                OreDictUnifier.get(OrePrefix.foil, Materials.Electrum, 64),
                OreDictUnifier.get(OrePrefix.foil, Materials.Electrum, 64),
                OreDictUnifier.get(OrePrefix.foil, Materials.Electrum, 64),
                OreDictUnifier.get(OrePrefix.cableGtSingle, Materials.YttriumBariumCuprate, 7))
                .input(OrePrefix.valueOf("circuitGA"), GAMaterials.Extreme, 7).fluidInputs(
                Materials.SolderingAlloy.getFluid(576))
                .outputs(MetaItems.SENSOR_LUV.getStackForm()).duration(600).EUt(15360)
                .buildAndRegister();

        GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(
                OreDictUnifier.get(OrePrefix.frameGt, Materials.HSSE, 1),
                MetaItems.SENSOR_LUV.getStackForm(),
                MetaItems.SENSOR_IV.getStackForm(2),
                MetaItems.SENSOR_EV.getStackForm(4),
                OreDictUnifier.get(OrePrefix.foil, Materials.Platinum, 64),
                OreDictUnifier.get(OrePrefix.foil, Materials.Platinum, 64),
                OreDictUnifier.get(OrePrefix.foil, Materials.Platinum, 64),
                OreDictUnifier.get(OrePrefix.cableGtQuadruple, Materials.VanadiumGallium, 7))
                .input(OrePrefix.valueOf("circuitGA"), Tier.Elite, 7).fluidInputs(
                Materials.SolderingAlloy.getFluid(576))
                .outputs(MetaItems.SENSOR_ZPM.getStackForm()).duration(600).EUt(61440)
                .buildAndRegister();

        GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(
                OreDictUnifier.get(OrePrefix.frameGt, GAMaterials.Neutronium, 1),
                MetaItems.SENSOR_ZPM.getStackForm(),
                MetaItems.SENSOR_LUV.getStackForm(2),
                MetaItems.SENSOR_IV.getStackForm(4),
                OreDictUnifier.get(OrePrefix.foil, Materials.Osmiridium, 64),
                OreDictUnifier.get(OrePrefix.foil, Materials.Osmiridium, 64),
                OreDictUnifier.get(OrePrefix.foil, Materials.Osmiridium, 64),
                OreDictUnifier.get(OrePrefix.cableGtQuadruple, Materials.NiobiumTitanium, 7))
                .input(OrePrefix.valueOf("circuitGA"), Tier.Master, 7).fluidInputs(
                Materials.SolderingAlloy.getFluid(576))
                .outputs(MetaItems.SENSOR_UV.getStackForm()).duration(600).EUt(245760)
                .buildAndRegister();

        GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(
                OreDictUnifier.get(OrePrefix.frameGt, Materials.HSSG, 1),
                OreDictUnifier.get(OrePrefix.plate, Materials.HSSG, 6),
                MetaItems.QUANTUM_STAR.getStackForm(),
                MetaItems.EMITTER_LUV.getStackForm(4),
                GAMetaItems.QUANTUM_COMPUTER.getStackForm(8), GAMetaItems.CRYSTALPROCESSOR.getStackForm(8)
                , MetaItems.CIRCUIT_BOARD_ELITE.getStackForm(8),
                OreDictUnifier.get(OrePrefix.wireFine, Materials.Osmium, 64),
                OreDictUnifier.get(OrePrefix.wireFine, Materials.Osmium, 64),
                OreDictUnifier.get(OrePrefix.wireFine, Materials.Osmium, 64),
                OreDictUnifier.get(OrePrefix.wireFine, Materials.Osmium, 64),
                OreDictUnifier.get(OrePrefix.cableGtSingle, Materials.YttriumBariumCuprate, 8)).fluidInputs(
                Materials.SolderingAlloy.getFluid(576))
                .outputs(MetaItems.FIELD_GENERATOR_LUV.getStackForm()).duration(600).EUt(30720)
                .buildAndRegister();

        GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(
                OreDictUnifier.get(OrePrefix.frameGt, Materials.HSSE, 1),
                OreDictUnifier.get(OrePrefix.plate, Materials.HSSE, 6),
                MetaItems.QUANTUM_STAR.getStackForm(4),
                MetaItems.EMITTER_ZPM.getStackForm(4),
                GAMetaItems.CRYSTALPROCESSOR.getStackForm(16), MetaItems.CIRCUIT_BOARD_ELITE.getStackForm(16),
                OreDictUnifier.get(OrePrefix.wireFine, Materials.Osmium, 64),
                OreDictUnifier.get(OrePrefix.wireFine, Materials.Osmium, 64),
                OreDictUnifier.get(OrePrefix.wireFine, Materials.Osmium, 64),
                OreDictUnifier.get(OrePrefix.wireFine, Materials.Osmium, 64),
                OreDictUnifier.get(OrePrefix.wireFine, Materials.Osmium, 64),
                OreDictUnifier.get(OrePrefix.wireFine, Materials.Osmium, 64),
                OreDictUnifier.get(OrePrefix.wireFine, Materials.Osmium, 64),
                OreDictUnifier.get(OrePrefix.wireFine, Materials.Osmium, 64),
                OreDictUnifier.get(OrePrefix.cableGtQuadruple, Materials.VanadiumGallium, 8)).fluidInputs(
                Materials.SolderingAlloy.getFluid(1152))
                .outputs(MetaItems.FIELD_GENERATOR_ZPM.getStackForm()).duration(600).EUt(122880)
                .buildAndRegister();

        GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(
                OreDictUnifier.get(OrePrefix.frameGt, GAMaterials.Neutronium, 1),
                OreDictUnifier.get(OrePrefix.plate, GAMaterials.Neutronium, 6),
                MetaItems.GRAVI_STAR.getStackForm(),
                MetaItems.EMITTER_UV.getStackForm(4),
                MetaItems.CIRCUIT_BOARD_ELITE.getStackForm(64),
                OreDictUnifier.get(OrePrefix.wireFine, Materials.Osmium, 64),
                OreDictUnifier.get(OrePrefix.wireFine, Materials.Osmium, 64),
                OreDictUnifier.get(OrePrefix.wireFine, Materials.Osmium, 64),
                OreDictUnifier.get(OrePrefix.wireFine, Materials.Osmium, 64),
                OreDictUnifier.get(OrePrefix.wireFine, Materials.Osmium, 64),
                OreDictUnifier.get(OrePrefix.wireFine, Materials.Osmium, 64),
                OreDictUnifier.get(OrePrefix.wireFine, Materials.Osmium, 64),
                OreDictUnifier.get(OrePrefix.wireFine, Materials.Osmium, 64),
                OreDictUnifier.get(OrePrefix.cableGtQuadruple, Materials.NiobiumTitanium, 8)).fluidInputs(
                Materials.SolderingAlloy.getFluid(2304))
                .outputs(MetaItems.FIELD_GENERATOR_UV.getStackForm()).duration(600).EUt(491520)
                .buildAndRegister();

        GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(
                GAMetaItems.WETWARE_BOARD.getStackForm(),
                GAMetaItems.STEMCELLS.getStackForm(8),
                GAMetaItems.GLASS_TUBE.getStackForm(8),
                OreDictUnifier.get(OrePrefix.foil, GAMaterials.SiliconeRubber, 64))
                .input(OrePrefix.plate, Materials.Gold, 8)
                .input(OrePrefix.plate, Materials.StainlessSteel, 4).fluidInputs(
                GAMaterials.SterilizedGrowthMedium.getFluid(250),
                Materials.UUMatter.getFluid(100), Materials.Water.getFluid(250), Materials.Lava.getFluid(1000))
                .outputs(MetaItems.CIRCUIT_BOARD_ELITE.getStackForm()).duration(200).EUt(80000)
                .buildAndRegister();

        GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(
                OreDictUnifier.get(OrePrefix.frameGt, Materials.Tritanium, 4),
                GAMetaItems.WETWARE_COMPUTER.getStackForm(8),
                GAMetaItems.SMALL_COIL.getStackForm(4),
                GAMetaItems.SMD_CAPACITOR.getStackForm(24),
                GAMetaItems.SMD_RESISTOR.getStackForm(64),
                GAMetaItems.SMD_TRANSISTOR.getStackForm(32),
                GAMetaItems.SMD_DIODE.getStackForm(16),
                GAMetaItems.RAM.getStackForm(16),
                OreDictUnifier.get(OrePrefix.wireGtSingle, Tier.Superconductor, 32),
                OreDictUnifier.get(OrePrefix.foil, GAMaterials.SiliconeRubber, 64)).fluidInputs(
                Materials.SolderingAlloy.getFluid(2880), Materials.Water.getFluid(10000))
                .outputs(GAMetaItems.WETWARE_MAINFRAME.getStackForm()).duration(2000).EUt(300000)
                .buildAndRegister();

        ItemStack last_bat = (GAConfig.GT5U.replaceUVwithMAXBat ? GAMetaItems.MAX_BATTERY : MetaItems.ZPM2).getStackForm();

        if (GAConfig.GT5U.enableZPMandUVBats) {
            GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(
                    OreDictUnifier.get(OrePrefix.plate, Materials.Europium, 16),
                    GAMetaItems.WETWARE_COMPUTER.getStackForm(),
                    GAMetaItems.WETWARE_COMPUTER.getStackForm(),
                    GAMetaItems.WETWARE_COMPUTER.getStackForm(),
                    GAMetaItems.WETWARE_COMPUTER.getStackForm(),
                    MetaItems.ENERGY_LAPOTRONIC_ORB2.getStackForm(8),
                    MetaItems.FIELD_GENERATOR_LUV.getStackForm(2),
                    GAMetaItems.NANOCPU.getStackForm(64),
                    GAMetaItems.NANOCPU.getStackForm(64),
                    GAMetaItems.SMD_DIODE.getStackForm(8),
                    OreDictUnifier.get(OrePrefix.cableGtSingle, Materials.Naquadah, 32)).fluidInputs(
                    Materials.SolderingAlloy.getFluid(2880),
                    Materials.Water.getFluid(8000))
                    .outputs(GAMetaItems.ENERGY_MODULE.getStackForm()).duration(2000).EUt(100000)
                    .buildAndRegister();

            GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(
                    OreDictUnifier.get(OrePrefix.plate, Materials.Americium, 16),
                    GAMetaItems.WETWARE_COMPUTER.getStackForm(),
                    GAMetaItems.WETWARE_COMPUTER.getStackForm(),
                    GAMetaItems.WETWARE_COMPUTER.getStackForm(),
                    GAMetaItems.WETWARE_COMPUTER.getStackForm(),
                    GAMetaItems.ENERGY_MODULE.getStackForm(8),
                    MetaItems.FIELD_GENERATOR_ZPM.getStackForm(2),
                    GAMetaItems.HPIC.getStackForm(64),
                    GAMetaItems.HPIC.getStackForm(64),
                    GAMetaItems.SMD_DIODE.getStackForm(16),
                    OreDictUnifier.get(OrePrefix.cableGtSingle, Materials.NaquadahAlloy, 32)).fluidInputs(
                    Materials.SolderingAlloy.getFluid(2880),
                    Materials.Water.getFluid(16000))
                    .outputs(GAMetaItems.ENERGY_CLUSTER.getStackForm()).duration(2000).EUt(200000)
                    .buildAndRegister();

            GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(
                    OreDictUnifier.get(OrePrefix.plate, GAMaterials.Neutronium, 16),
                    GAMetaItems.WETWARE_MAINFRAME.getStackForm(),
                    GAMetaItems.WETWARE_MAINFRAME.getStackForm(),
                    GAMetaItems.WETWARE_MAINFRAME.getStackForm(),
                    GAMetaItems.WETWARE_MAINFRAME.getStackForm(),
                    GAMetaItems.ENERGY_CLUSTER.getStackForm(8),
                    MetaItems.FIELD_GENERATOR_UV.getStackForm(2),
                    GAMetaItems.HPIC.getStackForm(64),
                    GAMetaItems.HPIC.getStackForm(64),
                    GAMetaItems.SMD_DIODE.getStackForm(16),
                    OreDictUnifier.get(OrePrefix.wireGtSingle, Tier.Superconductor, 32)).fluidInputs(
                    Materials.SolderingAlloy.getFluid(2880),
                    Materials.Water.getFluid(16000),
                    Materials.Naquadria.getFluid(1152))
                    .outputs(last_bat).duration(2000).EUt(300000)
                    .buildAndRegister();
        } else
            GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(
                    OreDictUnifier.get(OrePrefix.plate, GAMaterials.Neutronium, 16),
                    GAMetaItems.WETWARE_MAINFRAME.getStackForm(),
                    GAMetaItems.WETWARE_MAINFRAME.getStackForm(),
                    GAMetaItems.WETWARE_MAINFRAME.getStackForm(),
                    GAMetaItems.WETWARE_MAINFRAME.getStackForm(),
                    MetaItems.ENERGY_LAPOTRONIC_ORB2.getStackForm(8),
                    MetaItems.FIELD_GENERATOR_UV.getStackForm(2),
                    GAMetaItems.HPIC.getStackForm(64),
                    GAMetaItems.HPIC.getStackForm(64),
                    GAMetaItems.SMD_DIODE.getStackForm(16),
                    OreDictUnifier.get(OrePrefix.wireGtSingle, Tier.Superconductor, 32)).fluidInputs(
                    Materials.SolderingAlloy.getFluid(2880), Materials.Water.getFluid(16000))
                    .outputs(last_bat).duration(2000).EUt(300000)
                    .buildAndRegister();

        GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(
                MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.FUSION_COIL),
                OreDictUnifier.get(OrePrefix.plate, Materials.Plutonium241),
                OreDictUnifier.get(OrePrefix.plate, Materials.NetherStar),
                MetaItems.FIELD_GENERATOR_IV.getStackForm(2),
                GAMetaItems.HPIC.getStackForm(32),
                OreDictUnifier.get(OrePrefix.wireGtSingle, Tier.Superconductor, 32))
                .input(OrePrefix.valueOf("circuitGA"), Tier.Ultimate)
                .input(OrePrefix.valueOf("circuitGA"), Tier.Ultimate)
                .input(OrePrefix.valueOf("circuitGA"), Tier.Ultimate)
                .input(OrePrefix.valueOf("circuitGA"), Tier.Ultimate).fluidInputs(
                Materials.SolderingAlloy.getFluid(2880))
                .outputs(GATileEntities.FUSION_REACTOR[0].getStackForm()).duration(1000).EUt(30000)
                .buildAndRegister();

        GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(
                MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.FUSION_COIL),
                OreDictUnifier.get(OrePrefix.plate, Materials.Europium, 4),
                MetaItems.FIELD_GENERATOR_LUV.getStackForm(2),
                GAMetaItems.HPIC.getStackForm(48),
                OreDictUnifier.get(OrePrefix.wireGtDouble, Tier.Superconductor, 32))
                .input(OrePrefix.valueOf("circuitGA"), Tier.Superconductor)
                .input(OrePrefix.valueOf("circuitGA"), Tier.Superconductor)
                .input(OrePrefix.valueOf("circuitGA"), Tier.Superconductor)
                .input(OrePrefix.valueOf("circuitGA"), Tier.Superconductor).fluidInputs(
                Materials.SolderingAlloy.getFluid(2880))
                .outputs(GATileEntities.FUSION_REACTOR[1].getStackForm()).duration(1000).EUt(60000)
                .buildAndRegister();

        GARecipeMaps.ASSEMBLY_LINE_RECIPES.recipeBuilder().inputs(
                MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.FUSION_COIL),
                GAMetaItems.WETWARE_MAINFRAME.getStackForm(),
                GAMetaItems.WETWARE_MAINFRAME.getStackForm(),
                GAMetaItems.WETWARE_MAINFRAME.getStackForm(),
                GAMetaItems.WETWARE_MAINFRAME.getStackForm(),
                OreDictUnifier.get(OrePrefix.plate, Materials.Americium, 4),
                MetaItems.FIELD_GENERATOR_ZPM.getStackForm(2),
                GAMetaItems.HPIC.getStackForm(64),
                OreDictUnifier.get(OrePrefix.wireGtQuadruple, Tier.Superconductor, 32)).fluidInputs(
                Materials.SolderingAlloy.getFluid(2880))
                .outputs(GATileEntities.FUSION_REACTOR[2].getStackForm()).duration(1000).EUt(90000)
                .buildAndRegister();

        //Star Recipes
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(60000).EUt(8).input(OrePrefix.ingot, Materials.Plutonium, 3).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.Plutonium, 3)).fluidOutputs(Materials.Radon.getFluid(50)).buildAndRegister();
        RecipeMaps.AUTOCLAVE_RECIPES.recipeBuilder().duration(480).EUt(7680).inputs(new ItemStack(Items.NETHER_STAR)).fluidInputs(GAMaterials.Neutronium.getFluid(288)).outputs(MetaItems.GRAVI_STAR.getStackForm()).buildAndRegister();

        //Fusion Recipes
        RecipeMaps.FUSION_RECIPES.recipeBuilder().fluidInputs(Materials.Deuterium.getFluid(125), Materials.Tritium.getFluid(125)).fluidOutputs(Materials.Helium.getPlasma(125)).duration(16).EUt(4096).EUToStart(400000).buildAndRegister();
        RecipeMaps.FUSION_RECIPES.recipeBuilder().fluidInputs(Materials.Deuterium.getFluid(125), Materials.Helium3.getFluid(125)).fluidOutputs(Materials.Helium.getPlasma(125)).duration(16).EUt(2048).EUToStart(600000).buildAndRegister();
        RecipeMaps.FUSION_RECIPES.recipeBuilder().fluidInputs(Materials.Carbon.getFluid(125), Materials.Helium3.getFluid(125)).fluidOutputs(Materials.Oxygen.getPlasma(125)).duration(32).EUt(4096).EUToStart(800000).buildAndRegister();
        RecipeMaps.FUSION_RECIPES.recipeBuilder().fluidInputs(Materials.Beryllium.getFluid(16), Materials.Deuterium.getFluid(375)).fluidOutputs(Materials.Nitrogen.getPlasma(175)).duration(16).EUt(16384).EUToStart(1800000).buildAndRegister();
        RecipeMaps.FUSION_RECIPES.recipeBuilder().fluidInputs(Materials.Silicon.getFluid(16), Materials.Magnesium.getFluid(16)).fluidOutputs(Materials.Iron.getPlasma(125)).duration(32).EUt(8192).EUToStart(3600000).buildAndRegister();
        RecipeMaps.FUSION_RECIPES.recipeBuilder().fluidInputs(Materials.Potassium.getFluid(16), Materials.Fluorine.getFluid(125)).fluidOutputs(Materials.Nickel.getPlasma(125)).duration(16).EUt(32768).EUToStart(4800000).buildAndRegister();
        RecipeMaps.FUSION_RECIPES.recipeBuilder().fluidInputs(Materials.Beryllium.getFluid(16), Materials.Tungsten.getFluid(16)).fluidOutputs(Materials.Platinum.getFluid(16)).duration(32).EUt(32768).EUToStart(1500000).buildAndRegister();
        RecipeMaps.FUSION_RECIPES.recipeBuilder().fluidInputs(Materials.Neodymium.getFluid(16), Materials.Hydrogen.getFluid(48)).fluidOutputs(Materials.Europium.getFluid(16)).duration(64).EUt(24576).EUToStart(1500000).buildAndRegister();
        RecipeMaps.FUSION_RECIPES.recipeBuilder().fluidInputs(Materials.Lutetium.getFluid(16), Materials.Chrome.getFluid(16)).fluidOutputs(Materials.Americium.getFluid(16)).duration(96).EUt(49152).EUToStart(2000000).buildAndRegister();
        RecipeMaps.FUSION_RECIPES.recipeBuilder().fluidInputs(Materials.Plutonium.getFluid(16), Materials.Thorium.getFluid(16)).fluidOutputs(Materials.Naquadah.getFluid(16)).duration(64).EUt(32768).EUToStart(3000000).buildAndRegister();
        RecipeMaps.FUSION_RECIPES.recipeBuilder().fluidInputs(Materials.Americium.getFluid(16), Materials.Naquadria.getFluid(16)).fluidOutputs(GAMaterials.Neutronium.getFluid(2)).duration(200).EUt(98304).EUToStart(6000000).buildAndRegister();
        RecipeMaps.FUSION_RECIPES.recipeBuilder().fluidInputs(Materials.Tungsten.getFluid(16), Materials.Helium.getFluid(16)).fluidOutputs(Materials.Osmium.getFluid(16)).duration(64).EUt(24578).EUToStart(1500000).buildAndRegister();
        RecipeMaps.FUSION_RECIPES.recipeBuilder().fluidInputs(Materials.Manganese.getFluid(16), Materials.Hydrogen.getFluid(16)).fluidOutputs(Materials.Iron.getFluid(16)).duration(64).EUt(8192).EUToStart(1200000).buildAndRegister();
        RecipeMaps.FUSION_RECIPES.recipeBuilder().fluidInputs(Materials.Mercury.getFluid(16), Materials.Magnesium.getFluid(16)).fluidOutputs(Materials.Uranium.getFluid(16)).duration(64).EUt(49152).EUToStart(2400000).buildAndRegister();
        RecipeMaps.FUSION_RECIPES.recipeBuilder().fluidInputs(Materials.Gold.getFluid(16), Materials.Aluminium.getFluid(16)).fluidOutputs(Materials.Uranium.getFluid(16)).duration(64).EUt(49152).EUToStart(2400000).buildAndRegister();
        RecipeMaps.FUSION_RECIPES.recipeBuilder().fluidInputs(Materials.Uranium.getFluid(16), Materials.Helium.getFluid(16)).fluidOutputs(Materials.Plutonium.getFluid(16)).duration(128).EUt(49152).EUToStart(4800000).buildAndRegister();
        RecipeMaps.FUSION_RECIPES.recipeBuilder().fluidInputs(Materials.Vanadium.getFluid(16), Materials.Hydrogen.getFluid(125)).fluidOutputs(Materials.Chrome.getFluid(16)).duration(64).EUt(24576).EUToStart(1400000).buildAndRegister();
        RecipeMaps.FUSION_RECIPES.recipeBuilder().fluidInputs(Materials.Gallium.getFluid(16), Materials.Radon.getFluid(125)).fluidOutputs(Materials.Duranium.getFluid(16)).duration(64).EUt(16384).EUToStart(1400000).buildAndRegister();
        RecipeMaps.FUSION_RECIPES.recipeBuilder().fluidInputs(Materials.Titanium.getFluid(48), Materials.Duranium.getFluid(32)).fluidOutputs(Materials.Tritanium.getFluid(16)).duration(64).EUt(32768).EUToStart(2000000).buildAndRegister();
        RecipeMaps.FUSION_RECIPES.recipeBuilder().fluidInputs(Materials.Gold.getFluid(16), Materials.Mercury.getFluid(16)).fluidOutputs(Materials.Radon.getFluid(125)).duration(64).EUt(32768).EUToStart(2000000).buildAndRegister();
        RecipeMaps.FUSION_RECIPES.recipeBuilder().fluidInputs(Materials.Tantalum.getFluid(16), Materials.Tritium.getFluid(16)).fluidOutputs(Materials.Tungsten.getFluid(16)).duration(16).EUt(24576).EUToStart(2000000).buildAndRegister();
        RecipeMaps.FUSION_RECIPES.recipeBuilder().fluidInputs(Materials.Silver.getFluid(16), Materials.Lithium.getFluid(16)).fluidOutputs(Materials.Indium.getFluid(16)).duration(32).EUt(24576).EUToStart(3800000).buildAndRegister();
        RecipeMaps.FUSION_RECIPES.recipeBuilder().fluidInputs(Materials.NaquadahEnriched.getFluid(15), Materials.Radon.getFluid(125)).fluidOutputs(Materials.Naquadria.getFluid(3)).duration(64).EUt(49152).EUToStart(4000000).buildAndRegister();
        RecipeMaps.FUSION_RECIPES.recipeBuilder().fluidInputs(Materials.Lithium.getFluid(16), Materials.Tungsten.getFluid(16)).fluidOutputs(Materials.Iridium.getFluid(16)).duration(32).EUt(32768).EUToStart(3000000).buildAndRegister();

        //FUsion Casing Recipes
        ModHandler.addShapedRecipe("fusion_casing_1", MetaBlocks.MUTLIBLOCK_CASING.getItemVariant(MultiblockCasingType.ASSEMBLER_CASING.FUSION_CASING), "PhP", "PHP", "PwP", 'P', "plateTungstenSteel", 'H', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.LuV));
        ModHandler.addShapedRecipe("fusion_casing_2", MetaBlocks.MUTLIBLOCK_CASING.getItemVariant(MultiblockCasingType.ASSEMBLER_CASING.FUSION_CASING_MK2), "PhP", "PHP", "PwP", 'P', "plateAmericium", 'H', MetaBlocks.MUTLIBLOCK_CASING.getItemVariant(MultiblockCasingType.ASSEMBLER_CASING.FUSION_CASING));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().EUt(16).inputs(MetaBlocks.MUTLIBLOCK_CASING.getItemVariant(MultiblockCasingType.ASSEMBLER_CASING.FUSION_CASING)).input(OrePrefix.plate, Materials.Americium, 6).outputs(MetaBlocks.MUTLIBLOCK_CASING.getItemVariant(MultiblockCasingType.ASSEMBLER_CASING.FUSION_CASING_MK2)).duration(50).buildAndRegister();

        ModHandler.addShapedRecipe("fusion_coil", MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.FUSION_COIL), "CRC", "FSF", "CRC", 'C', "circuitGAMaster", 'R', GAMetaItems.NEUTRON_REFLECTOR.getStackForm(), 'F', MetaItems.FIELD_GENERATOR_MV.getStackForm(), 'S', MetaBlocks.WIRE_COIL.getItemVariant(BlockWireCoil.CoilType.SUPERCONDUCTOR));

        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(4000).EUt(120).inputs(GAMetaItems.PLATE_IRIDIUM_ALLOY.getStackForm()).input(OrePrefix.plate, Materials.Beryllium, 30).input(OrePrefix.plate, Materials.TungstenCarbide, 3).fluidInputs(Materials.TinAlloy.getFluid(13824)).outputs(GAMetaItems.NEUTRON_REFLECTOR.getStackForm()).buildAndRegister();

        //Explosive Recipes
        ModHandler.removeRecipes(new ItemStack(Blocks.TNT));
        ModHandler.removeRecipes(MetaItems.DYNAMITE.getStackForm());
        RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(160).EUt(4).inputs(new ItemStack(Items.PAPER), new ItemStack(Items.STRING)).fluidInputs(Materials.Glyceryl.getFluid(500)).outputs(MetaItems.DYNAMITE.getStackForm()).buildAndRegister();

        //Electromagnetic Separator Recipes
        RecipeMaps.ELECTROMAGNETIC_SEPARATOR_RECIPES.recipeBuilder().duration(400).EUt(24).input(OrePrefix.dustPure, Materials.BrownLimonite).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.BrownLimonite)).chancedOutput(OreDictUnifier.get(OrePrefix.dustSmall, Materials.Iron), 4000).chancedOutput(OreDictUnifier.get(OrePrefix.nugget, Materials.Iron), 2000).buildAndRegister();
        RecipeMaps.ELECTROMAGNETIC_SEPARATOR_RECIPES.recipeBuilder().duration(400).EUt(24).input(OrePrefix.dustPure, Materials.YellowLimonite).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.YellowLimonite)).chancedOutput(OreDictUnifier.get(OrePrefix.dustSmall, Materials.Iron), 4000).chancedOutput(OreDictUnifier.get(OrePrefix.nugget, Materials.Iron), 2000).buildAndRegister();
        RecipeMaps.ELECTROMAGNETIC_SEPARATOR_RECIPES.recipeBuilder().duration(400).EUt(24).input(OrePrefix.dustPure, Materials.Nickel).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.Nickel)).chancedOutput(OreDictUnifier.get(OrePrefix.dustSmall, Materials.Iron), 4000).chancedOutput(OreDictUnifier.get(OrePrefix.nugget, Materials.Iron), 2000).buildAndRegister();
        RecipeMaps.ELECTROMAGNETIC_SEPARATOR_RECIPES.recipeBuilder().duration(400).EUt(24).input(OrePrefix.dustPure, Materials.Pentlandite).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.Pentlandite)).chancedOutput(OreDictUnifier.get(OrePrefix.dustSmall, Materials.Iron), 4000).chancedOutput(OreDictUnifier.get(OrePrefix.nugget, Materials.Iron), 2000).buildAndRegister();
        RecipeMaps.ELECTROMAGNETIC_SEPARATOR_RECIPES.recipeBuilder().duration(400).EUt(24).input(OrePrefix.dustPure, Materials.BandedIron).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.BandedIron)).chancedOutput(OreDictUnifier.get(OrePrefix.dustSmall, Materials.Iron), 4000).chancedOutput(OreDictUnifier.get(OrePrefix.nugget, Materials.Iron), 2000).buildAndRegister();
        RecipeMaps.ELECTROMAGNETIC_SEPARATOR_RECIPES.recipeBuilder().duration(400).EUt(24).input(OrePrefix.dustPure, Materials.Ilmenite).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.Ilmenite)).chancedOutput(OreDictUnifier.get(OrePrefix.dustSmall, Materials.Iron), 4000).chancedOutput(OreDictUnifier.get(OrePrefix.nugget, Materials.Iron), 2000).buildAndRegister();
        RecipeMaps.ELECTROMAGNETIC_SEPARATOR_RECIPES.recipeBuilder().duration(400).EUt(24).input(OrePrefix.dustPure, Materials.Pyrite).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.Pyrite)).chancedOutput(OreDictUnifier.get(OrePrefix.dustSmall, Materials.Iron), 4000).chancedOutput(OreDictUnifier.get(OrePrefix.nugget, Materials.Iron), 2000).buildAndRegister();
        RecipeMaps.ELECTROMAGNETIC_SEPARATOR_RECIPES.recipeBuilder().duration(400).EUt(24).input(OrePrefix.dustPure, Materials.Tin).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.Tin)).chancedOutput(OreDictUnifier.get(OrePrefix.dustSmall, Materials.Iron), 4000).chancedOutput(OreDictUnifier.get(OrePrefix.nugget, Materials.Iron), 2000).buildAndRegister();
        RecipeMaps.ELECTROMAGNETIC_SEPARATOR_RECIPES.recipeBuilder().duration(400).EUt(24).input(OrePrefix.dustPure, Materials.Chromite).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.Chromite)).chancedOutput(OreDictUnifier.get(OrePrefix.dustSmall, Materials.Iron), 4000).chancedOutput(OreDictUnifier.get(OrePrefix.nugget, Materials.Iron), 2000).buildAndRegister();
        RecipeMaps.ELECTROMAGNETIC_SEPARATOR_RECIPES.recipeBuilder().duration(400).EUt(24).input(OrePrefix.dustPure, Materials.Monazite).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.Monazite)).chancedOutput(OreDictUnifier.get(OrePrefix.dustSmall, Materials.Neodymium), 4000).chancedOutput(OreDictUnifier.get(OrePrefix.nugget, Materials.Neodymium), 2000).buildAndRegister();
        RecipeMaps.ELECTROMAGNETIC_SEPARATOR_RECIPES.recipeBuilder().duration(400).EUt(24).input(OrePrefix.dustPure, Materials.Bastnasite).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.Bastnasite)).chancedOutput(OreDictUnifier.get(OrePrefix.dustSmall, Materials.Neodymium), 4000).chancedOutput(OreDictUnifier.get(OrePrefix.nugget, Materials.Neodymium), 2000).buildAndRegister();
        RecipeMaps.ELECTROMAGNETIC_SEPARATOR_RECIPES.recipeBuilder().duration(400).EUt(24).input(OrePrefix.dustPure, Materials.VanadiumMagnetite).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.VanadiumMagnetite)).chancedOutput(OreDictUnifier.get(OrePrefix.dustSmall, Materials.Gold), 4000).chancedOutput(OreDictUnifier.get(OrePrefix.nugget, Materials.Gold), 2000).buildAndRegister();
        RecipeMaps.ELECTROMAGNETIC_SEPARATOR_RECIPES.recipeBuilder().duration(400).EUt(24).input(OrePrefix.dustPure, Materials.Magnetite).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.Magnetite)).chancedOutput(OreDictUnifier.get(OrePrefix.dustSmall, Materials.Gold), 4000).chancedOutput(OreDictUnifier.get(OrePrefix.nugget, Materials.Gold), 2000).buildAndRegister();

        //Lapotron Crystal Recipes
        for (MaterialStack m : lapisLike) {
            GemMaterial gem = (GemMaterial) m.material;
            ModHandler.addShapedRecipe("lapotron_crystal_shaped" + gem.toString(), GAMetaItems.LAPOTRON_CRYSTAL.getStackForm(), "PCP", "RFR", "PCP", 'P', new UnificationEntry(OrePrefix.plate, gem), 'C', "circuitGAAdvanced", 'R', OreDictUnifier.get(OrePrefix.stick, gem), 'F', OreDictUnifier.get(OrePrefix.gemFlawless, Materials.Sapphire));
            ModHandler.addShapelessRecipe("lapotron_crystal_shapeless" + gem.toString(), GAMetaItems.LAPOTRON_CRYSTAL.getStackForm(), OreDictUnifier.get(OrePrefix.gemExquisite, Materials.Sapphire), OreDictUnifier.get(OrePrefix.stick, gem), GAMetaItems.CAPACITOR.getStackForm());
        }

        //Add Missing Superconducter Wire Tiering Recipes
        ModHandler.addShapelessRecipe("superonducter_wire_gtsingle_doubling", OreDictUnifier.get(OrePrefix.wireGtDouble, Tier.Superconductor), OreDictUnifier.get(OrePrefix.wireGtSingle, Tier.Superconductor), OreDictUnifier.get(OrePrefix.wireGtSingle, Tier.Superconductor));
        ModHandler.addShapelessRecipe("superonducter_wire_gtdouble_doubling", OreDictUnifier.get(OrePrefix.wireGtQuadruple, Tier.Superconductor), OreDictUnifier.get(OrePrefix.wireGtDouble, Tier.Superconductor), OreDictUnifier.get(OrePrefix.wireGtDouble, Tier.Superconductor));
        ModHandler.addShapelessRecipe("superonducter_wire_gtquadruple_doubling", OreDictUnifier.get(OrePrefix.wireGtOctal, Tier.Superconductor), OreDictUnifier.get(OrePrefix.wireGtQuadruple, Tier.Superconductor), OreDictUnifier.get(OrePrefix.wireGtQuadruple, Tier.Superconductor));
        ModHandler.addShapelessRecipe("superonducter_wire_gtoctal_doubling", OreDictUnifier.get(OrePrefix.wireGtHex, Tier.Superconductor), OreDictUnifier.get(OrePrefix.wireGtOctal, Tier.Superconductor), OreDictUnifier.get(OrePrefix.wireGtOctal, Tier.Superconductor));
        ModHandler.addShapelessRecipe("superonducter_wire_gtdouble_splitting", OreDictUnifier.get(OrePrefix.wireGtSingle, Tier.Superconductor, 2), OreDictUnifier.get(OrePrefix.wireGtDouble, Tier.Superconductor));
        ModHandler.addShapelessRecipe("superonducter_wire_gtquadruple_splitting", OreDictUnifier.get(OrePrefix.wireGtDouble, Tier.Superconductor, 2), OreDictUnifier.get(OrePrefix.wireGtQuadruple, Tier.Superconductor));
        ModHandler.addShapelessRecipe("superonducter_wire_gtoctal_splitting", OreDictUnifier.get(OrePrefix.wireGtQuadruple, Tier.Superconductor, 2), OreDictUnifier.get(OrePrefix.wireGtOctal, Tier.Superconductor));
        ModHandler.addShapelessRecipe("superonducter_wire_gthex_splitting", OreDictUnifier.get(OrePrefix.wireGtOctal, Tier.Superconductor, 2), OreDictUnifier.get(OrePrefix.wireGtHex, Tier.Superconductor));

        //Dust Packing
        for (Material m : DustMaterial.MATERIAL_REGISTRY) {
            if (!OreDictUnifier.get(OrePrefix.dust, m).isEmpty() && GAConfig.Misc.PackagerDustRecipes) {
                RecipeMaps.PACKER_RECIPES.recipeBuilder().duration(100).EUt(4).input(OrePrefix.dustSmall, m, 4).notConsumable(GAMetaItems.SCHEMATIC_DUST.getStackForm()).outputs(OreDictUnifier.get(OrePrefix.dust, m)).buildAndRegister();
                RecipeMaps.PACKER_RECIPES.recipeBuilder().duration(100).EUt(4).input(OrePrefix.dustTiny, m, 9).notConsumable(GAMetaItems.SCHEMATIC_DUST.getStackForm()).outputs(OreDictUnifier.get(OrePrefix.dust, m)).buildAndRegister();
            }
        }

        //Schematic Recipes
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(3200).EUt(4).input(OrePrefix.valueOf("circuitGA"), Tier.Good, 4).input(OrePrefix.plate, Materials.StainlessSteel, 2).outputs(GAMetaItems.SCHEMATIC.getStackForm()).buildAndRegister();
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:schematic/schematic_1"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:schematic/schematic_c"));

        //Configuration Circuit
        ModHandler.removeRecipes(MetaItems.CIRCUIT_BASIC.getStackForm());
        ModHandler.removeRecipes(MetaItems.INTEGRATED_CIRCUIT.getStackForm());
        ModHandler.addShapelessRecipe("basic_to_configurable_circuit", MetaItems.INTEGRATED_CIRCUIT.getStackForm(), "circuitGABasic");

        //MAX Machine Hull
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:casing_max"));
        ModHandler.removeRecipeByName(new ResourceLocation("gregtech:hull_max"));
        ModHandler.addShapedRecipe("ga_casing_max", MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.MAX), "PPP", "PwP", "PPP", 'P', new UnificationEntry(OrePrefix.plate, GAMaterials.Neutronium));
        ModHandler.addShapedRecipe("ga_hull_max", MetaTileEntities.HULL[GTValues.MAX].getStackForm(), "PHP", "CMC", 'M', MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.MAX), 'C', new UnificationEntry(OrePrefix.wireGtSingle, Tier.Superconductor), 'H', new UnificationEntry(OrePrefix.plate, GAMaterials.Neutronium), 'P', new UnificationEntry(OrePrefix.plate, Materials.Polytetrafluoroethylene));
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(16).input(OrePrefix.plate, GAMaterials.Neutronium, 8).outputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.MAX)).circuitMeta(8).duration(50).buildAndRegister();
        RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(50).EUt(16).inputs(MetaBlocks.MACHINE_CASING.getItemVariant(BlockMachineCasing.MachineCasingType.MAX)).input(OrePrefix.wireGtSingle, Tier.Superconductor, 2).fluidInputs(Materials.Polytetrafluoroethylene.getFluid(288)).outputs(MetaTileEntities.HULL[9].getStackForm()).buildAndRegister();


        List<ItemStack> allWoodLeaves = OreDictionary.getOres("treeLeaves").stream()
                .flatMap(stack -> ModHandler.getAllSubItems(stack).stream())
                .collect(Collectors.toList());

        List<ItemStack> allSaplings = OreDictionary.getOres("treeSapling").stream()
                .flatMap(stack -> ModHandler.getAllSubItems(stack).stream())
                .collect(Collectors.toList());

        //Biomass Process
        RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(1440).EUt(3).inputs(MetaItems.PLANT_BALL.getStackForm()).fluidInputs(Materials.Water.getFluid(180)).fluidOutputs(Materials.Biomass.getFluid(180)).buildAndRegister();
        for (ItemStack stack : allSaplings)
            RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(800).EUt(3).inputs(GTUtility.copyAmount(1, stack)).fluidInputs(Materials.Water.getFluid(100)).fluidOutputs(Materials.Biomass.getFluid(100)).buildAndRegister();
        RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(160).EUt(3).inputs(new ItemStack(Items.POTATO)).fluidInputs(Materials.Water.getFluid(20)).fluidOutputs(Materials.Biomass.getFluid(20)).buildAndRegister();
        RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(160).EUt(3).inputs(new ItemStack(Items.CARROT)).fluidInputs(Materials.Water.getFluid(20)).fluidOutputs(Materials.Biomass.getFluid(20)).buildAndRegister();
        RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(160).EUt(3).inputs(new ItemStack(Blocks.CACTUS)).fluidInputs(Materials.Water.getFluid(20)).fluidOutputs(Materials.Biomass.getFluid(20)).buildAndRegister();
        RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(160).EUt(3).inputs(new ItemStack(Items.REEDS)).fluidInputs(Materials.Water.getFluid(20)).fluidOutputs(Materials.Biomass.getFluid(20)).buildAndRegister();
        RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(160).EUt(3).inputs(new ItemStack(Blocks.BROWN_MUSHROOM)).fluidInputs(Materials.Water.getFluid(20)).fluidOutputs(Materials.Biomass.getFluid(20)).buildAndRegister();
        RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(160).EUt(3).inputs(new ItemStack(Blocks.RED_MUSHROOM)).fluidInputs(Materials.Water.getFluid(20)).fluidOutputs(Materials.Biomass.getFluid(20)).buildAndRegister();
        RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(160).EUt(3).inputs(new ItemStack(Items.BEETROOT)).fluidInputs(Materials.Water.getFluid(20)).fluidOutputs(Materials.Biomass.getFluid(20)).buildAndRegister();

        for (ItemStack stack : allSaplings)
            RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2).inputs(GTUtility.copyAmount(8, stack)).outputs(MetaItems.PLANT_BALL.getStackForm()).buildAndRegister();
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2).inputs(new ItemStack(Items.WHEAT, 8)).outputs(MetaItems.PLANT_BALL.getStackForm()).buildAndRegister();
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2).inputs(new ItemStack(Items.POTATO, 8)).outputs(MetaItems.PLANT_BALL.getStackForm()).buildAndRegister();
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2).inputs(new ItemStack(Items.CARROT, 8)).outputs(MetaItems.PLANT_BALL.getStackForm()).buildAndRegister();
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2).inputs(new ItemStack(Blocks.CACTUS, 8)).outputs(MetaItems.PLANT_BALL.getStackForm()).buildAndRegister();
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2).inputs(new ItemStack(Items.REEDS, 8)).outputs(MetaItems.PLANT_BALL.getStackForm()).buildAndRegister();
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2).inputs(new ItemStack(Blocks.BROWN_MUSHROOM, 8)).outputs(MetaItems.PLANT_BALL.getStackForm()).buildAndRegister();
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2).inputs(new ItemStack(Blocks.RED_MUSHROOM, 8)).outputs(MetaItems.PLANT_BALL.getStackForm()).buildAndRegister();
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder().duration(300).EUt(2).inputs(new ItemStack(Items.BEETROOT, 8)).outputs(MetaItems.PLANT_BALL.getStackForm()).buildAndRegister();

        //Bentonite
        RecipeMaps.ELECTROLYZER_RECIPES.recipeBuilder().duration(480).EUt(120).input(OrePrefix.dust, Materials.Bentonite, 30).outputs(OreDictUnifier.get(OrePrefix.dust, Materials.Sodium), OreDictUnifier.get(OrePrefix.dust, Materials.Magnesium, 6), OreDictUnifier.get(OrePrefix.dust, Materials.Silicon, 12)).fluidOutputs(Materials.Hydrogen.getFluid(6000), Materials.Water.getFluid(5000)).buildAndRegister();

        //Food To Methane
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(72).EUt(5).inputs(new ItemStack(Items.BREAD)).fluidOutputs(Materials.Methane.getFluid(9)).buildAndRegister();
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(72).EUt(5).inputs(new ItemStack(Items.COOKIE)).fluidOutputs(Materials.Methane.getFluid(9)).buildAndRegister();
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(72).EUt(5).inputs(new ItemStack(Items.MELON)).fluidOutputs(Materials.Methane.getFluid(9)).buildAndRegister();
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(144).EUt(5).inputs(new ItemStack(Items.APPLE)).fluidOutputs(Materials.Methane.getFluid(18)).buildAndRegister();
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(144).EUt(5).inputs(new ItemStack(Items.NETHER_WART)).fluidOutputs(Materials.Methane.getFluid(18)).buildAndRegister();
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(144).EUt(5).inputs(new ItemStack(Blocks.BROWN_MUSHROOM)).fluidOutputs(Materials.Methane.getFluid(18)).buildAndRegister();
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(144).EUt(5).inputs(new ItemStack(Blocks.RED_MUSHROOM)).fluidOutputs(Materials.Methane.getFluid(18)).buildAndRegister();
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(144).EUt(5).inputs(new ItemStack(Items.SPIDER_EYE)).fluidOutputs(Materials.Methane.getFluid(18)).buildAndRegister();
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(192).EUt(5).inputs(new ItemStack(Items.BAKED_POTATO)).fluidOutputs(Materials.Methane.getFluid(24)).buildAndRegister();
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(288).EUt(5).inputs(new ItemStack(Blocks.PUMPKIN)).fluidOutputs(Materials.Methane.getFluid(36)).buildAndRegister();
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(288).EUt(5).inputs(new ItemStack(Items.CARROT)).fluidOutputs(Materials.Methane.getFluid(36)).buildAndRegister();
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(288).EUt(5).inputs(new ItemStack(Items.COOKED_BEEF)).fluidOutputs(Materials.Methane.getFluid(36)).buildAndRegister();
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(288).EUt(5).inputs(new ItemStack(Items.MUSHROOM_STEW)).outputs(new ItemStack(Items.BOWL)).fluidOutputs(Materials.Methane.getFluid(36)).buildAndRegister();
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(288).EUt(5).inputs(new ItemStack(Items.COOKED_FISH)).fluidOutputs(Materials.Methane.getFluid(36)).buildAndRegister();
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(288).EUt(5).inputs(new ItemStack(Items.COOKED_FISH, 1, 1)).fluidOutputs(Materials.Methane.getFluid(36)).buildAndRegister();
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(288).EUt(5).inputs(new ItemStack(Items.COOKED_CHICKEN)).fluidOutputs(Materials.Methane.getFluid(36)).buildAndRegister();
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(288).EUt(5).inputs(new ItemStack(Items.POTATO)).fluidOutputs(Materials.Methane.getFluid(36)).buildAndRegister();
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(288).EUt(5).inputs(new ItemStack(Items.ROTTEN_FLESH)).fluidOutputs(Materials.Methane.getFluid(36)).buildAndRegister();
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(288).EUt(5).inputs(new ItemStack(Items.COOKED_PORKCHOP)).fluidOutputs(Materials.Methane.getFluid(36)).buildAndRegister();
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(288).EUt(5).inputs(new ItemStack(Items.COOKED_RABBIT)).fluidOutputs(Materials.Methane.getFluid(36)).buildAndRegister();
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(288).EUt(5).inputs(new ItemStack(Items.COOKED_MUTTON)).fluidOutputs(Materials.Methane.getFluid(36)).buildAndRegister();
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(384).EUt(5).inputs(new ItemStack(Items.PORKCHOP)).fluidOutputs(Materials.Methane.getFluid(48)).buildAndRegister();
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(384).EUt(5).inputs(new ItemStack(Items.FISH)).fluidOutputs(Materials.Methane.getFluid(48)).buildAndRegister();
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(384).EUt(5).inputs(new ItemStack(Items.FISH, 1, 1)).fluidOutputs(Materials.Methane.getFluid(48)).buildAndRegister();
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(384).EUt(5).inputs(new ItemStack(Items.FISH, 1, 2)).fluidOutputs(Materials.Methane.getFluid(48)).buildAndRegister();
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(384).EUt(5).inputs(new ItemStack(Items.FISH, 1, 3)).fluidOutputs(Materials.Methane.getFluid(48)).buildAndRegister();
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(384).EUt(5).inputs(new ItemStack(Items.POISONOUS_POTATO)).fluidOutputs(Materials.Methane.getFluid(48)).buildAndRegister();
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(384).EUt(5).inputs(new ItemStack(Items.CHICKEN)).fluidOutputs(Materials.Methane.getFluid(48)).buildAndRegister();
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(384).EUt(5).inputs(new ItemStack(Items.RABBIT)).fluidOutputs(Materials.Methane.getFluid(48)).buildAndRegister();
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(384).EUt(5).inputs(new ItemStack(Items.MUTTON)).fluidOutputs(Materials.Methane.getFluid(48)).buildAndRegister();
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(384).EUt(5).inputs(new ItemStack(Items.BEEF)).fluidOutputs(Materials.Methane.getFluid(48)).buildAndRegister();
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(576).EUt(5).inputs(new ItemStack(Items.CAKE)).fluidOutputs(Materials.Methane.getFluid(72)).buildAndRegister();
        RecipeMaps.CENTRIFUGE_RECIPES.recipeBuilder().duration(1008).EUt(5).inputs(new ItemStack(Items.RABBIT_STEW)).outputs(new ItemStack(Items.BOWL)).fluidOutputs(Materials.Methane.getFluid(126)).buildAndRegister();

        //Redstone and glowstone melting
        RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(80).EUt(32).input(OrePrefix.dust, Materials.Redstone).fluidOutputs(Materials.Redstone.getFluid(144)).buildAndRegister();
        RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(80).EUt(32).input(OrePrefix.dust, Materials.Glowstone).fluidOutputs(Materials.Glowstone.getFluid(144)).buildAndRegister();

        //Gem Tool Part Fixes
        for (Material material : GemMaterial.MATERIAL_REGISTRY) {
            if (!OreDictUnifier.get(OrePrefix.gem, material).isEmpty() && !OreDictUnifier.get(OrePrefix.toolHeadHammer, material).isEmpty() && material != Materials.Flint) {
                ModHandler.removeRecipes(OreDictUnifier.get(OrePrefix.toolHeadAxe, material));
                ModHandler.addShapedRecipe("axe_head_" + material.toString(), OreDictUnifier.get(OrePrefix.toolHeadAxe, material), "GG", "Gf", 'G', new UnificationEntry(OrePrefix.gem, material));
                ModHandler.removeRecipes(OreDictUnifier.get(OrePrefix.toolHeadFile, material));
                ModHandler.addShapedRecipe("file_head_" + material.toString(), OreDictUnifier.get(OrePrefix.toolHeadFile, material), "G", "G", "f", 'G', new UnificationEntry(OrePrefix.gem, material));
                ModHandler.removeRecipes(OreDictUnifier.get(OrePrefix.toolHeadHammer, material));
                ModHandler.addShapedRecipe("hammer_head_" + material.toString(), OreDictUnifier.get(OrePrefix.toolHeadHammer, material), "GG ", "GGf", "GG ", 'G', new UnificationEntry(OrePrefix.gem, material));
                ModHandler.removeRecipes(OreDictUnifier.get(OrePrefix.toolHeadHoe, material));
                ModHandler.addShapedRecipe("hoe_head_" + material.toString(), OreDictUnifier.get(OrePrefix.toolHeadHoe, material), "GGf", 'G', new UnificationEntry(OrePrefix.gem, material));
                ModHandler.removeRecipes(OreDictUnifier.get(OrePrefix.toolHeadPickaxe, material));
                ModHandler.addShapedRecipe("pickaxe_head_" + material.toString(), OreDictUnifier.get(OrePrefix.toolHeadPickaxe, material), "GGG", "f  ", 'G', new UnificationEntry(OrePrefix.gem, material));
                ModHandler.removeRecipes(OreDictUnifier.get(OrePrefix.toolHeadPlow, material));
                ModHandler.addShapedRecipe("flow_head_" + material.toString(), OreDictUnifier.get(OrePrefix.toolHeadPlow, material), "GG", "GG", " f", 'G', new UnificationEntry(OrePrefix.gem, material));
                ModHandler.removeRecipes(OreDictUnifier.get(OrePrefix.toolHeadSaw, material));
                ModHandler.addShapedRecipe("saw_head_" + material.toString(), OreDictUnifier.get(OrePrefix.toolHeadSaw, material), "GG", "f ", 'G', new UnificationEntry(OrePrefix.gem, material));
                ModHandler.removeRecipes(OreDictUnifier.get(OrePrefix.toolHeadSense, material));
                ModHandler.addShapedRecipe("sense_head_" + material.toString(), OreDictUnifier.get(OrePrefix.toolHeadSense, material), "GGG", " f ", 'G', new UnificationEntry(OrePrefix.gem, material));
                ModHandler.removeRecipes(OreDictUnifier.get(OrePrefix.toolHeadShovel, material));
                ModHandler.addShapedRecipe("shovel_head_" + material.toString(), OreDictUnifier.get(OrePrefix.toolHeadShovel, material), "fG", 'G', new UnificationEntry(OrePrefix.gem, material));
                ModHandler.removeRecipes(OreDictUnifier.get(OrePrefix.toolHeadSword, material));
                ModHandler.addShapedRecipe("sword_head_" + material.toString(), OreDictUnifier.get(OrePrefix.toolHeadSword, material), " G", "fG", 'G', new UnificationEntry(OrePrefix.gem, material));
                ModHandler.removeRecipes(OreDictUnifier.get(OrePrefix.toolHeadUniversalSpade, material));
                ModHandler.addShapedRecipe("universal_spade_head_" + material.toString(), OreDictUnifier.get(OrePrefix.toolHeadUniversalSpade, material), "GGG", "GfG", " G ", 'G', new UnificationEntry(OrePrefix.gem, material));
            }
        }

        //Misc Recipe Patches
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder().duration(400).EUt(2).input(OrePrefix.dust, Materials.NetherQuartz).outputs(OreDictUnifier.get(OrePrefix.plate, Materials.NetherQuartz)).buildAndRegister();
        RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder().duration(400).EUt(2).input(OrePrefix.dust, Materials.CertusQuartz).outputs(OreDictUnifier.get(OrePrefix.plate, Materials.CertusQuartz)).buildAndRegister();

        //Dust Uncrafting Fixes
        for (Material m : DustMaterial.MATERIAL_REGISTRY) {
            if (!OreDictUnifier.get(OrePrefix.dustSmall, m).isEmpty()) {
                ModHandler.removeRecipes(OreDictUnifier.get(OrePrefix.dustSmall, m));
                ModHandler.addShapedRecipe("dust_small_" + m.toString(), OreDictUnifier.get(OrePrefix.dustSmall, m, 4), " D", "  ", 'D', new UnificationEntry(OrePrefix.dust, m));
            }
        }

        //Things GTCE Removed
        ModHandler.addShapedRecipe("oak_plank", GAMetaItems.PLANK_OAK.getStackForm(), "s ", " S", 'S', new ItemStack(Blocks.WOODEN_SLAB));
        ModHandler.addShapedRecipe("spruce_plank", GAMetaItems.PLANK_SPRUCE.getStackForm(), "s ", " S", 'S', new ItemStack(Blocks.WOODEN_SLAB, 1, 1));
        ModHandler.addShapedRecipe("birch_plank", GAMetaItems.PLANK_BIRCH.getStackForm(), "s ", " S", 'S', new ItemStack(Blocks.WOODEN_SLAB, 1, 2));
        ModHandler.addShapedRecipe("jungle_plank", GAMetaItems.PLANK_JUNGLE.getStackForm(), "s ", " S", 'S', new ItemStack(Blocks.WOODEN_SLAB, 1, 3));
        ModHandler.addShapedRecipe("acacia_plank", GAMetaItems.PLANK_ACACIA.getStackForm(), "s ", " S", 'S', new ItemStack(Blocks.WOODEN_SLAB, 1, 4));
        ModHandler.addShapedRecipe("dark_oak_plank", GAMetaItems.PLANK_DARKOAK.getStackForm(), "s ", " S", 'S', new ItemStack(Blocks.WOODEN_SLAB, 1, 5));

        ModHandler.addShapedRecipe("3x3_schematic", GAMetaItems.SCHEMATIC_3X3.getStackForm(), "  d", " S ", "   ", 'S', GAMetaItems.SCHEMATIC.getStackForm());
        ModHandler.addShapedRecipe("2x2_schematic", GAMetaItems.SCHEMATIC_2X2.getStackForm(), " d ", " S ", "   ", 'S', GAMetaItems.SCHEMATIC.getStackForm());
        ModHandler.addShapedRecipe("dust_schematic", GAMetaItems.SCHEMATIC_DUST.getStackForm(), "   ", " S ", "  d", 'S', GAMetaItems.SCHEMATIC.getStackForm());

    }

    public static void forestrySupport() {
        //Distillation Support
        if (Loader.isModLoaded("forestry") && GAConfig.Misc.ForestryIntegration) {
            GARecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(16).EUt(96).fluidInputs(Fluids.SEED_OIL.getFluid(24)).fluidOutputs(Materials.Lubricant.getFluid(12)).buildAndRegister();
            GARecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(40).EUt(256).fluidInputs(GAMaterials.WoodVinegar.getFluid(1000)).fluidOutputs(GAMaterials.AceticAcid.getFluid(100), Materials.Water.getFluid(500), Fluids.BIO_ETHANOL.getFluid(10), GAMaterials.Methanol.getFluid(300), GAMaterials.Acetone.getFluid(50), GAMaterials.MethylAcetate.getFluid(10)).buildAndRegister();
            GARecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(75).EUt(180).fluidInputs(GAMaterials.FermentedBiomass.getFluid(1000)).fluidOutputs(GAMaterials.AceticAcid.getFluid(25), Materials.Water.getFluid(375), Fluids.BIO_ETHANOL.getFluid(150), GAMaterials.Methanol.getFluid(150), GAMaterials.Ammonia.getFluid(100), Materials.CarbonDioxide.getFluid(400), Materials.Methane.getFluid(600)).buildAndRegister();
            GARecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(32).EUt(400).fluidInputs(Materials.Biomass.getFluid(1000)).outputs(OreDictUnifier.get(OrePrefix.dustSmall, Materials.Wood, 2)).fluidOutputs(Fluids.BIO_ETHANOL.getFluid(600), Materials.Water.getFluid(300)).buildAndRegister();
        } else {
            GARecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(16).EUt(96).fluidInputs(Materials.SeedOil.getFluid(24)).fluidOutputs(Materials.Lubricant.getFluid(12)).buildAndRegister();
            GARecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(40).EUt(256).fluidInputs(GAMaterials.WoodVinegar.getFluid(1000)).fluidOutputs(GAMaterials.AceticAcid.getFluid(100), Materials.Water.getFluid(500), Materials.Ethanol.getFluid(10), GAMaterials.Methanol.getFluid(300), GAMaterials.Acetone.getFluid(50), GAMaterials.MethylAcetate.getFluid(10)).buildAndRegister();
            GARecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(75).EUt(180).fluidInputs(GAMaterials.FermentedBiomass.getFluid(1000)).fluidOutputs(GAMaterials.AceticAcid.getFluid(25), Materials.Water.getFluid(375), Materials.Ethanol.getFluid(150), GAMaterials.Methanol.getFluid(150), GAMaterials.Ammonia.getFluid(100), Materials.CarbonDioxide.getFluid(400), Materials.Methane.getFluid(600)).buildAndRegister();
            GARecipeMaps.DISTILLATION_RECIPES.recipeBuilder().duration(32).EUt(400).fluidInputs(Materials.Biomass.getFluid(1000)).outputs(OreDictUnifier.get(OrePrefix.dustSmall, Materials.Wood, 2)).fluidOutputs(Materials.Ethanol.getFluid(600), Materials.Water.getFluid(300)).buildAndRegister();
        }

        //Extracting Seed Oil
        if (Loader.isModLoaded("forestry") && GAConfig.Misc.ForestryIntegration) {
            RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(128).EUt(5).inputs(new ItemStack(Items.WHEAT_SEEDS)).fluidOutputs(Fluids.SEED_OIL.getFluid(10)).buildAndRegister();
            RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(128).EUt(5).inputs(new ItemStack(Items.MELON_SEEDS)).fluidOutputs(Fluids.SEED_OIL.getFluid(10)).buildAndRegister();
            RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(128).EUt(5).inputs(new ItemStack(Items.PUMPKIN_SEEDS)).fluidOutputs(Fluids.SEED_OIL.getFluid(10)).buildAndRegister();
        } else {
            RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(32).EUt(2).inputs(new ItemStack(Items.WHEAT_SEEDS)).fluidOutputs(Materials.SeedOil.getFluid(10)).buildAndRegister();
            RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(32).EUt(2).inputs(new ItemStack(Items.MELON_SEEDS)).fluidOutputs(Materials.SeedOil.getFluid(10)).buildAndRegister();
            RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(32).EUt(2).inputs(new ItemStack(Items.PUMPKIN_SEEDS)).fluidOutputs(Materials.SeedOil.getFluid(10)).buildAndRegister();
            RecipeMaps.FLUID_EXTRACTION_RECIPES.recipeBuilder().duration(32).EUt(2).inputs(new ItemStack(Items.BEETROOT_SEEDS)).fluidOutputs(Materials.SeedOil.getFluid(10)).buildAndRegister();
        }

        //Making BioDiesel
        if (Loader.isModLoaded("forestry") && GAConfig.Misc.ForestryIntegration) {
            RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(30).input(OrePrefix.dustTiny, GAMaterials.SodiumHydroxide).fluidInputs(Fluids.SEED_OIL.getFluid(6000), GAMaterials.Methanol.getFluid(1000)).fluidOutputs(GAMaterials.Glycerol.getFluid(1000), GAMaterials.BioDiesel.getFluid(6000)).buildAndRegister();
            RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(30).input(OrePrefix.dustTiny, GAMaterials.SodiumHydroxide).fluidInputs(Fluids.SEED_OIL.getFluid(6000), Fluids.BIO_ETHANOL.getFluid(1000)).fluidOutputs(GAMaterials.Glycerol.getFluid(1000), GAMaterials.BioDiesel.getFluid(6000)).buildAndRegister();
            RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(30).input(OrePrefix.dustTiny, GAMaterials.SodiumHydroxide).fluidInputs(GAMaterials.FishOil.getFluid(6000), GAMaterials.Methanol.getFluid(1000)).fluidOutputs(GAMaterials.Glycerol.getFluid(1000), GAMaterials.BioDiesel.getFluid(6000)).buildAndRegister();
            RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(30).input(OrePrefix.dustTiny, GAMaterials.SodiumHydroxide).fluidInputs(GAMaterials.FishOil.getFluid(6000), Fluids.BIO_ETHANOL.getFluid(1000)).fluidOutputs(GAMaterials.Glycerol.getFluid(1000), GAMaterials.BioDiesel.getFluid(6000)).buildAndRegister();
        } else {
            RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(30).input(OrePrefix.dustTiny, GAMaterials.SodiumHydroxide).fluidInputs(Materials.SeedOil.getFluid(6000), GAMaterials.Methanol.getFluid(1000)).fluidOutputs(GAMaterials.Glycerol.getFluid(1000), GAMaterials.BioDiesel.getFluid(6000)).buildAndRegister();
            RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(30).input(OrePrefix.dustTiny, GAMaterials.SodiumHydroxide).fluidInputs(Materials.SeedOil.getFluid(6000), Materials.Ethanol.getFluid(1000)).fluidOutputs(GAMaterials.Glycerol.getFluid(1000), GAMaterials.BioDiesel.getFluid(6000)).buildAndRegister();
            RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(30).input(OrePrefix.dustTiny, GAMaterials.SodiumHydroxide).fluidInputs(GAMaterials.FishOil.getFluid(6000), GAMaterials.Methanol.getFluid(1000)).fluidOutputs(GAMaterials.Glycerol.getFluid(1000), GAMaterials.BioDiesel.getFluid(6000)).buildAndRegister();
            RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(600).EUt(30).input(OrePrefix.dustTiny, GAMaterials.SodiumHydroxide).fluidInputs(GAMaterials.FishOil.getFluid(6000), Materials.Ethanol.getFluid(1000)).fluidOutputs(GAMaterials.Glycerol.getFluid(1000), GAMaterials.BioDiesel.getFluid(6000)).buildAndRegister();
        }

        //Lube Mixer Recipes
        for (MaterialStack lubeDust : lubeDusts) {
            DustMaterial dust = (DustMaterial) lubeDust.material;
            RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(128).EUt(4).input(OrePrefix.dust, dust).fluidInputs(Materials.Oil.getFluid(750)).fluidOutputs(Materials.Lubricant.getFluid(750)).buildAndRegister();
            RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(128).EUt(4).input(OrePrefix.dust, dust).fluidInputs(Materials.Creosote.getFluid(750)).fluidOutputs(Materials.Lubricant.getFluid(750)).buildAndRegister();
            if (Loader.isModLoaded("forestry") && GAConfig.Misc.ForestryIntegration)
                RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(128).EUt(4).input(OrePrefix.dust, dust).fluidInputs(Fluids.SEED_OIL.getFluid(750)).fluidOutputs(Materials.Lubricant.getFluid(750)).buildAndRegister();
            else
                RecipeMaps.MIXER_RECIPES.recipeBuilder().duration(128).EUt(4).input(OrePrefix.dust, dust).fluidInputs(Materials.SeedOil.getFluid(750)).fluidOutputs(Materials.Lubricant.getFluid(750)).buildAndRegister();
        }

        List<ItemStack> allSaplings = OreDictionary.getOres("treeSapling").stream()
                .flatMap(stack -> ModHandler.getAllSubItems(stack).stream())
                .collect(Collectors.toList());

        //Biomass
        if (Loader.isModLoaded("forestry") && GAConfig.Misc.ForestryIntegration) {
            RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(2880).EUt(3).inputs(MetaItems.PLANT_BALL.getStackForm()).fluidInputs(Fluids.FOR_HONEY.getFluid(180)).fluidOutputs(Materials.Biomass.getFluid(270)).buildAndRegister();
            for (ItemStack stack : allSaplings)
                RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(1200).EUt(3).inputs(GTUtility.copyAmount(1, stack)).fluidInputs(Fluids.FOR_HONEY.getFluid(100)).fluidOutputs(Materials.Biomass.getFluid(150)).buildAndRegister();
            RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(320).EUt(3).inputs(new ItemStack(Items.POTATO)).fluidInputs(Fluids.FOR_HONEY.getFluid(20)).fluidOutputs(Materials.Biomass.getFluid(30)).buildAndRegister();
            RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(320).EUt(3).inputs(new ItemStack(Items.CARROT)).fluidInputs(Fluids.FOR_HONEY.getFluid(20)).fluidOutputs(Materials.Biomass.getFluid(30)).buildAndRegister();
            RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(320).EUt(3).inputs(new ItemStack(Blocks.CACTUS)).fluidInputs(Fluids.FOR_HONEY.getFluid(20)).fluidOutputs(Materials.Biomass.getFluid(30)).buildAndRegister();
            RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(320).EUt(3).inputs(new ItemStack(Items.REEDS)).fluidInputs(Fluids.FOR_HONEY.getFluid(20)).fluidOutputs(Materials.Biomass.getFluid(30)).buildAndRegister();
            RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(320).EUt(3).inputs(new ItemStack(Blocks.BROWN_MUSHROOM)).fluidInputs(Fluids.FOR_HONEY.getFluid(20)).fluidOutputs(Materials.Biomass.getFluid(30)).buildAndRegister();
            RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(320).EUt(3).inputs(new ItemStack(Blocks.RED_MUSHROOM)).fluidInputs(Fluids.FOR_HONEY.getFluid(20)).fluidOutputs(Materials.Biomass.getFluid(30)).buildAndRegister();
            RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(320).EUt(3).inputs(new ItemStack(Items.BEETROOT)).fluidInputs(Fluids.FOR_HONEY.getFluid(20)).fluidOutputs(Materials.Biomass.getFluid(30)).buildAndRegister();
        }

        if (Loader.isModLoaded("forestry") && GAConfig.Misc.ForestryIntegration) {
            RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(2880).EUt(3).inputs(MetaItems.PLANT_BALL.getStackForm()).fluidInputs(Fluids.JUICE.getFluid(180)).fluidOutputs(Materials.Biomass.getFluid(270)).buildAndRegister();
            for (ItemStack stack : allSaplings)
                RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(1200).EUt(3).inputs(GTUtility.copyAmount(1, stack)).fluidInputs(Fluids.JUICE.getFluid(100)).fluidOutputs(Materials.Biomass.getFluid(150)).buildAndRegister();
            RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(320).EUt(3).inputs(new ItemStack(Items.POTATO)).fluidInputs(Fluids.JUICE.getFluid(20)).fluidOutputs(Materials.Biomass.getFluid(30)).buildAndRegister();
            RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(320).EUt(3).inputs(new ItemStack(Items.CARROT)).fluidInputs(Fluids.JUICE.getFluid(20)).fluidOutputs(Materials.Biomass.getFluid(30)).buildAndRegister();
            RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(320).EUt(3).inputs(new ItemStack(Blocks.CACTUS)).fluidInputs(Fluids.JUICE.getFluid(20)).fluidOutputs(Materials.Biomass.getFluid(30)).buildAndRegister();
            RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(320).EUt(3).inputs(new ItemStack(Items.REEDS)).fluidInputs(Fluids.JUICE.getFluid(20)).fluidOutputs(Materials.Biomass.getFluid(30)).buildAndRegister();
            RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(320).EUt(3).inputs(new ItemStack(Blocks.BROWN_MUSHROOM)).fluidInputs(Fluids.JUICE.getFluid(20)).fluidOutputs(Materials.Biomass.getFluid(30)).buildAndRegister();
            RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(320).EUt(3).inputs(new ItemStack(Blocks.RED_MUSHROOM)).fluidInputs(Fluids.JUICE.getFluid(20)).fluidOutputs(Materials.Biomass.getFluid(30)).buildAndRegister();
            RecipeMaps.BREWING_RECIPES.recipeBuilder().duration(320).EUt(3).inputs(new ItemStack(Items.BEETROOT)).fluidInputs(Fluids.JUICE.getFluid(20)).fluidOutputs(Materials.Biomass.getFluid(30)).buildAndRegister();
        }

        //Making Ethylene
        if (Loader.isModLoaded("forestry") && GAConfig.Misc.ForestryIntegration)
            RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(1200).EUt(120).fluidInputs(Materials.SulfuricAcid.getFluid(1000), Fluids.BIO_ETHANOL.getFluid(1000)).fluidOutputs(GAMaterials.Ethylene.getFluid(1000), GAMaterials.DilutedSulfuricAcid.getFluid(1000)).buildAndRegister();
        else
            RecipeMaps.CHEMICAL_RECIPES.recipeBuilder().duration(1200).EUt(120).fluidInputs(Materials.SulfuricAcid.getFluid(1000), Materials.Ethanol.getFluid(1000)).fluidOutputs(GAMaterials.Ethylene.getFluid(1000), GAMaterials.DilutedSulfuricAcid.getFluid(1000)).buildAndRegister();


        if (Loader.isModLoaded("forestry") && GAConfig.GT6.electrodes) {
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16).inputs(GAMetaItems.ELECTRODE_APATITE.getStackForm(), OreDictUnifier.get(OrePrefix.plate, Materials.Glass)).outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.APATITE, 1)).buildAndRegister();
            RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(100).EUt(24).input(OrePrefix.stick, Materials.Apatite, 2).input(OrePrefix.bolt, Materials.Apatite).input(OrePrefix.dustSmall, Materials.Redstone, 2).outputs(GAMetaItems.ELECTRODE_APATITE.getStackForm()).buildAndRegister();
            RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(200).EUt(24).input(OrePrefix.stick, Materials.Apatite, 4).input(OrePrefix.bolt, Materials.Apatite, 2).input(OrePrefix.dust, Materials.Redstone).outputs(GAMetaItems.ELECTRODE_APATITE.getStackForm(2)).buildAndRegister();
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16).inputs(GAMetaItems.ELECTRODE_BLAZE.getStackForm(), OreDictUnifier.get(OrePrefix.plate, Materials.Glass)).outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.BLAZE, 1)).buildAndRegister();
            RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(200).EUt(24).input(OrePrefix.dust, Materials.Blaze, 2).input(OrePrefix.dustSmall, Materials.Blaze, 2).input(OrePrefix.dust, Materials.Redstone).outputs(GAMetaItems.ELECTRODE_BLAZE.getStackForm(2)).buildAndRegister();
            RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(400).EUt(24).input(OrePrefix.dust, Materials.Blaze, 5).input(OrePrefix.dust, Materials.Redstone, 2).outputs(GAMetaItems.ELECTRODE_BLAZE.getStackForm(4)).buildAndRegister();
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16).inputs(GAMetaItems.ELECTRODE_BRONZE.getStackForm(), OreDictUnifier.get(OrePrefix.plate, Materials.Glass)).outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.BRONZE, 1)).buildAndRegister();
            RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(100).EUt(24).input(OrePrefix.stick, Materials.Bronze, 2).input(OrePrefix.bolt, Materials.Bronze).input(OrePrefix.dustSmall, Materials.Redstone, 2).outputs(GAMetaItems.ELECTRODE_BRONZE.getStackForm()).buildAndRegister();
            RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(200).EUt(24).input(OrePrefix.stick, Materials.Bronze, 4).input(OrePrefix.bolt, Materials.Bronze, 2).input(OrePrefix.dust, Materials.Redstone).outputs(GAMetaItems.ELECTRODE_BRONZE.getStackForm(2)).buildAndRegister();
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16).inputs(GAMetaItems.ELECTRODE_COPPER.getStackForm(), OreDictUnifier.get(OrePrefix.plate, Materials.Glass)).outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.COPPER, 1)).buildAndRegister();
            RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(100).EUt(24).input(OrePrefix.stick, Materials.Copper, 2).input(OrePrefix.bolt, Materials.Copper).input(OrePrefix.dustSmall, Materials.Redstone, 2).outputs(GAMetaItems.ELECTRODE_COPPER.getStackForm()).buildAndRegister();
            RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(200).EUt(24).input(OrePrefix.stick, Materials.Copper, 4).input(OrePrefix.bolt, Materials.Copper, 2).input(OrePrefix.dust, Materials.Redstone).outputs(GAMetaItems.ELECTRODE_COPPER.getStackForm(2)).buildAndRegister();
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16).inputs(GAMetaItems.ELECTRODE_DIAMOND.getStackForm(), OreDictUnifier.get(OrePrefix.plate, Materials.Glass)).outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.DIAMOND, 1)).buildAndRegister();
            RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(100).EUt(24).input(OrePrefix.stick, Materials.Diamond, 2).input(OrePrefix.bolt, Materials.Diamond).input(OrePrefix.dustSmall, Materials.Redstone, 2).outputs(GAMetaItems.ELECTRODE_DIAMOND.getStackForm()).buildAndRegister();
            RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(200).EUt(24).input(OrePrefix.stick, Materials.Diamond, 4).input(OrePrefix.bolt, Materials.Diamond, 2).input(OrePrefix.dust, Materials.Redstone).outputs(GAMetaItems.ELECTRODE_DIAMOND.getStackForm(2)).buildAndRegister();
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16).inputs(GAMetaItems.ELECTRODE_EMERALD.getStackForm(), OreDictUnifier.get(OrePrefix.plate, Materials.Glass)).outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.EMERALD, 1)).buildAndRegister();
            RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(100).EUt(24).input(OrePrefix.stick, Materials.Emerald, 2).input(OrePrefix.bolt, Materials.Emerald).input(OrePrefix.dustSmall, Materials.Redstone, 2).outputs(GAMetaItems.ELECTRODE_EMERALD.getStackForm()).buildAndRegister();
            RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(200).EUt(24).input(OrePrefix.stick, Materials.Emerald, 4).input(OrePrefix.bolt, Materials.Emerald, 2).input(OrePrefix.dust, Materials.Redstone).outputs(GAMetaItems.ELECTRODE_EMERALD.getStackForm(2)).buildAndRegister();
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16).inputs(GAMetaItems.ELECTRODE_ENDER.getStackForm(), OreDictUnifier.get(OrePrefix.plate, Materials.Glass)).outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.ENDER, 1)).buildAndRegister();
            RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(200).EUt(24).input(OrePrefix.dust, Materials.Endstone, 2).input(OrePrefix.dustSmall, Materials.Endstone, 2).input(OrePrefix.dust, Materials.EnderEye).outputs(GAMetaItems.ELECTRODE_ENDER.getStackForm(2)).buildAndRegister();
            RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(400).EUt(24).input(OrePrefix.dust, Materials.Endstone, 5).input(OrePrefix.dust, Materials.EnderEye, 2).outputs(GAMetaItems.ELECTRODE_ENDER.getStackForm(4)).buildAndRegister();
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16).inputs(GAMetaItems.ELECTRODE_GOLD.getStackForm(), OreDictUnifier.get(OrePrefix.plate, Materials.Glass)).outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.GOLD, 1)).buildAndRegister();
            RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(100).EUt(24).input(OrePrefix.stick, Materials.Gold, 2).input(OrePrefix.bolt, Materials.Gold).input(OrePrefix.dustSmall, Materials.Redstone, 2).outputs(GAMetaItems.ELECTRODE_GOLD.getStackForm()).buildAndRegister();
            RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(200).EUt(24).input(OrePrefix.stick, Materials.Gold, 4).input(OrePrefix.bolt, Materials.Gold, 2).input(OrePrefix.dust, Materials.Redstone).outputs(GAMetaItems.ELECTRODE_GOLD.getStackForm(2)).buildAndRegister();
            if (Loader.isModLoaded("ic2") || Loader.isModLoaded("binniecore")) {
                RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16).inputs(GAMetaItems.ELECTRODE_IRON.getStackForm(), OreDictUnifier.get(OrePrefix.plate, Materials.Glass)).outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.IRON, 1)).buildAndRegister();
                RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(100).EUt(24).input(OrePrefix.stick, Materials.Iron, 2).input(OrePrefix.bolt, Materials.Iron).input(OrePrefix.dustSmall, Materials.Redstone, 2).outputs(GAMetaItems.ELECTRODE_IRON.getStackForm()).buildAndRegister();
                RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(200).EUt(24).input(OrePrefix.stick, Materials.Iron, 4).input(OrePrefix.bolt, Materials.Iron, 2).input(OrePrefix.dust, Materials.Redstone).outputs(GAMetaItems.ELECTRODE_IRON.getStackForm(2)).buildAndRegister();
            }
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16).inputs(GAMetaItems.ELECTRODE_LAPIS.getStackForm(), OreDictUnifier.get(OrePrefix.plate, Materials.Glass)).outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.LAPIS, 1)).buildAndRegister();
            RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(100).EUt(24).input(OrePrefix.stick, Materials.Lapis, 2).input(OrePrefix.bolt, Materials.Lapis).input(OrePrefix.dustSmall, Materials.Redstone, 2).outputs(GAMetaItems.ELECTRODE_LAPIS.getStackForm()).buildAndRegister();
            RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(200).EUt(24).input(OrePrefix.stick, Materials.Lapis, 4).input(OrePrefix.bolt, Materials.Lapis, 2).input(OrePrefix.dust, Materials.Redstone).outputs(GAMetaItems.ELECTRODE_LAPIS.getStackForm(2)).buildAndRegister();
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16).inputs(GAMetaItems.ELECTRODE_OBSIDIAN.getStackForm(), OreDictUnifier.get(OrePrefix.plate, Materials.Glass)).outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.OBSIDIAN, 1)).buildAndRegister();
            RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(200).EUt(24).input(OrePrefix.dust, Materials.Obsidian, 2).input(OrePrefix.dustSmall, Materials.Obsidian, 2).input(OrePrefix.dust, Materials.Redstone).outputs(GAMetaItems.ELECTRODE_OBSIDIAN.getStackForm(2)).buildAndRegister();
            RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(400).EUt(24).input(OrePrefix.dust, Materials.Obsidian, 5).input(OrePrefix.dust, Materials.Redstone, 2).outputs(GAMetaItems.ELECTRODE_OBSIDIAN.getStackForm(4)).buildAndRegister();
            if (Loader.isModLoaded("extrautils2")) {
                RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16).inputs(GAMetaItems.ELECTRODE_ORCHID.getStackForm(), OreDictUnifier.get(OrePrefix.plate, Materials.Glass)).outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.ORCHID, 1)).buildAndRegister();
                RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(400).EUt(24).inputs(new ItemStack(Blocks.REDSTONE_ORE, 5), OreDictUnifier.get(OrePrefix.dust, Materials.Redstone)).outputs(GAMetaItems.ELECTRODE_ORCHID.getStackForm(4)).buildAndRegister();
            }
            if (Loader.isModLoaded("ic2") || Loader.isModLoaded("techreborn")) {
                RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16).inputs(GAMetaItems.ELECTRODE_RUBBER.getStackForm(), OreDictUnifier.get(OrePrefix.plate, Materials.Glass)).outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.RUBBER, 1)).buildAndRegister();
                RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(100).EUt(24).input(OrePrefix.stick, Materials.Rubber, 2).input(OrePrefix.bolt, Materials.Rubber).input(OrePrefix.dustSmall, Materials.Redstone, 2).outputs(GAMetaItems.ELECTRODE_RUBBER.getStackForm()).buildAndRegister();
                RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(200).EUt(24).input(OrePrefix.stick, Materials.Rubber, 4).input(OrePrefix.bolt, Materials.Rubber, 2).input(OrePrefix.dust, Materials.Redstone).outputs(GAMetaItems.ELECTRODE_RUBBER.getStackForm(2)).buildAndRegister();
            }
            RecipeMaps.ASSEMBLER_RECIPES.recipeBuilder().duration(150).EUt(16).inputs(GAMetaItems.ELECTRODE_TIN.getStackForm(), OreDictUnifier.get(OrePrefix.plate, Materials.Glass)).outputs(ModuleCore.getItems().tubes.get(EnumElectronTube.TIN, 1)).buildAndRegister();
            RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(100).EUt(24).input(OrePrefix.stick, Materials.Tin, 2).input(OrePrefix.bolt, Materials.Tin).input(OrePrefix.dustSmall, Materials.Redstone, 2).outputs(GAMetaItems.ELECTRODE_TIN.getStackForm()).buildAndRegister();
            RecipeMaps.FORMING_PRESS_RECIPES.recipeBuilder().duration(200).EUt(24).input(OrePrefix.stick, Materials.Tin, 4).input(OrePrefix.bolt, Materials.Tin, 2).input(OrePrefix.dust, Materials.Redstone).outputs(GAMetaItems.ELECTRODE_TIN.getStackForm(2)).buildAndRegister();
        }
    }

    public static void generatedRecipes() {
        List<ResourceLocation> recipesToRemove = new ArrayList<>();

        for (IRecipe recipe : CraftingManager.REGISTRY) {
            if (recipe.getIngredients().size() == 9) {
                if (recipe.getIngredients().get(0).getMatchingStacks().length > 0 && Block.getBlockFromItem(recipe.getRecipeOutput().getItem()) != Blocks.AIR) {
                    boolean match = true;
                    for (int i = 1; i < recipe.getIngredients().size(); i++) {
                        if (recipe.getIngredients().get(i).getMatchingStacks().length == 0 || !recipe.getIngredients().get(0).getMatchingStacks()[0].isItemEqual(recipe.getIngredients().get(i).getMatchingStacks()[0])) {
                            match = false;
                            break;
                        }
                    }
                    if (match) {
                        if (GAConfig.GT5U.Remove3x3BlockRecipes)
                            recipesToRemove.add(recipe.getRegistryName());
                        if (GAConfig.GT5U.GenerateCompressorRecipes)
                            RecipeMaps.COMPRESSOR_RECIPES.recipeBuilder().duration(400).EUt(2).inputs(CountableIngredient.from(recipe.getIngredients().get(0).getMatchingStacks()[0], recipe.getIngredients().size())).outputs(recipe.getRecipeOutput()).buildAndRegister();
                    }
                }
            }
            if (recipe.getIngredients().size() == 9) {
                if (recipe.getIngredients().get(0).getMatchingStacks().length > 0 && Block.getBlockFromItem(recipe.getRecipeOutput().getItem()) == Blocks.AIR) {
                    boolean match = true;
                    for (int i = 1; i < recipe.getIngredients().size(); i++) {
                        if (recipe.getIngredients().get(i).getMatchingStacks().length == 0 || !recipe.getIngredients().get(0).getMatchingStacks()[0].isItemEqual(recipe.getIngredients().get(i).getMatchingStacks()[0])) {
                            match = false;
                            break;
                        }
                    }
                    if (match && !recipesToRemove.contains(recipe.getRegistryName()) && !GAMetaItems.hasPrefix(recipe.getRecipeOutput(), "dust", "dustTiny") && recipe.getRecipeOutput().getCount() == 1 && GAConfig.Misc.Packager3x3Recipes) {
                        RecipeMaps.PACKER_RECIPES.recipeBuilder().duration(100).EUt(4).inputs(CountableIngredient.from(recipe.getIngredients().get(0).getMatchingStacks()[0], recipe.getIngredients().size())).notConsumable(GAMetaItems.SCHEMATIC_3X3.getStackForm()).outputs(recipe.getRecipeOutput()).buildAndRegister();
                    }
                }
            }
            if (recipe.getIngredients().size() == 4) {
                if (recipe.getIngredients().get(0).getMatchingStacks().length > 0 && Block.getBlockFromItem(recipe.getRecipeOutput().getItem()) != Blocks.QUARTZ_BLOCK) {
                    boolean match = true;
                    for (int i = 1; i < recipe.getIngredients().size(); i++) {
                        if (recipe.getIngredients().get(i).getMatchingStacks().length == 0 || !recipe.getIngredients().get(0).getMatchingStacks()[0].isItemEqual(recipe.getIngredients().get(i).getMatchingStacks()[0])) {
                            match = false;
                            break;
                        }
                    }
                    if (match && !recipesToRemove.contains(recipe.getRegistryName()) && !GAMetaItems.hasPrefix(recipe.getRecipeOutput(), "dust", "dustSmall") && recipe.getRecipeOutput().getCount() == 1 && GAConfig.Misc.Packager2x2Recipes) {
                        RecipeMaps.PACKER_RECIPES.recipeBuilder().duration(100).EUt(4).inputs(CountableIngredient.from(recipe.getIngredients().get(0).getMatchingStacks()[0], recipe.getIngredients().size())).notConsumable(GAMetaItems.SCHEMATIC_2X2.getStackForm()).outputs(recipe.getRecipeOutput()).buildAndRegister();
                    }
                }
            }
            if (recipe.getIngredients().size() == 1 && recipe.getIngredients().get(0).getMatchingStacks().length > 0 && recipe.getRecipeOutput().getCount() == 9 && Block.getBlockFromItem(recipe.getIngredients().get(0).getMatchingStacks()[0].getItem()) != Blocks.AIR && Block.getBlockFromItem(recipe.getIngredients().get(0).getMatchingStacks()[0].getItem()) != Blocks.SLIME_BLOCK) {
                boolean isIngot = false;
                for (int i : OreDictionary.getOreIDs(recipe.getRecipeOutput())) {
                    if (OreDictionary.getOreName(i).startsWith("ingot")) {
                        isIngot = true;
                        break;
                    }
                }
                if (GAConfig.GT5U.RemoveBlockUncraftingRecipes)
                    recipesToRemove.add(recipe.getRegistryName());
                if (!isIngot) {
                    RecipeMaps.FORGE_HAMMER_RECIPES.recipeBuilder().duration(100).EUt(24).inputs(recipe.getIngredients().get(0).getMatchingStacks()[0]).outputs(recipe.getRecipeOutput()).buildAndRegister();
                }
            }
            if (recipe.getIngredients().size() == 1 && recipe.getIngredients().get(0).getMatchingStacks().length > 0 && recipe.getRecipeOutput().getCount() == 9) {
                if (!recipesToRemove.contains(recipe.getRegistryName()) && GAConfig.Misc.Unpackager3x3Recipes) {
                    RecipeMaps.UNPACKER_RECIPES.recipeBuilder().duration(100).EUt(8).inputs(recipe.getIngredients().get(0).getMatchingStacks()[0]).outputs(recipe.getRecipeOutput()).buildAndRegister();
                }
            }
        }

        for (ResourceLocation r : recipesToRemove)
            ModHandler.removeRecipeByName(r);
        recipesToRemove.clear();

        if (GAConfig.GT5U.GenerateCompressorRecipes) {
            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:glowstone"));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:block_compress_glowstone"));
            ModHandler.removeRecipeByName(new ResourceLocation("minecraft:quartz_block"));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:block_compress_nether_quartz"));
            ModHandler.removeRecipeByName(new ResourceLocation("gregtech:block_decompress_nether_quartz"));
            RecipeMaps.FORGE_HAMMER_RECIPES.recipeBuilder().duration(100).EUt(24).inputs(OreDictUnifier.get(OrePrefix.block, Materials.NetherQuartz)).outputs(OreDictUnifier.get(OrePrefix.gem, Materials.NetherQuartz, 4)).buildAndRegister();
        }

        //Generate Plank Recipes
        for (IRecipe recipe : CraftingManager.REGISTRY) {
            if (recipe.getRecipeOutput().isEmpty())
                continue;
            for (int i : OreDictionary.getOreIDs(recipe.getRecipeOutput())) {
                if (OreDictionary.getOreName(i).equals("plankWood") && recipe.getIngredients().size() == 1 && recipe.getRecipeOutput().getCount() == 4) {
                    if (GAConfig.GT5U.GeneratedSawingRecipes) {
                        ModHandler.removeRecipeByName(recipe.getRegistryName());
                        ModHandler.addShapelessRecipe("log_to_4_" + recipe.getRecipeOutput().toString(), GTUtility.copyAmount(4, recipe.getRecipeOutput()), recipe.getIngredients().get(0).getMatchingStacks()[0], ToolDictNames.craftingToolSaw);
                        ModHandler.addShapelessRecipe("log_to_2_" + recipe.getRecipeOutput().toString(), GTUtility.copyAmount(2, recipe.getRecipeOutput()), recipe.getIngredients().get(0).getMatchingStacks()[0]);
                    }
                    RecipeMaps.CUTTER_RECIPES.recipeBuilder().duration(200).EUt(8).inputs(recipe.getIngredients().get(0).getMatchingStacks()[0]).fluidInputs(Materials.Lubricant.getFluid(1)).outputs(GTUtility.copyAmount(6, recipe.getRecipeOutput()), OreDictUnifier.get(OrePrefix.dust, Materials.Wood, 2)).buildAndRegister();
                }
                if (OreDictionary.getOreName(i).equals("slabWood") && recipe.getRecipeOutput().getCount() == 6) {
                    RecipeMaps.CUTTER_RECIPES.recipeBuilder().duration(50).EUt(4).inputs(recipe.getIngredients().get(0).getMatchingStacks()[0]).outputs(GTUtility.copyAmount(2, recipe.getRecipeOutput())).buildAndRegister();
                }
            }
        }

        //Disable Wood To Charcoal Recipes
        List<ItemStack> allWoodLogs = OreDictionary.getOres("logWood").stream()
                .flatMap(stack -> ModHandler.getAllSubItems(stack).stream())
                .collect(Collectors.toList());

        for (ItemStack stack : allWoodLogs) {
            ItemStack smeltingOutput = ModHandler.getSmeltingOutput(stack);
            if (!smeltingOutput.isEmpty() && smeltingOutput.getItem() == Items.COAL && smeltingOutput.getMetadata() == 1 && GAConfig.GT5U.DisableLogToCharcoalSmeltg) {
                ItemStack woodStack = stack.copy();
                woodStack.setItemDamage(OreDictionary.WILDCARD_VALUE);
                ModHandler.removeFurnaceSmelting(woodStack);
            }
        }
    }
}
