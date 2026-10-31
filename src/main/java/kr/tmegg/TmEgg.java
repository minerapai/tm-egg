package kr.tmegg;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * Server side: /tmegg turns the TMCraft TM in the main hand (tmcraft:tm_<move>) into the egg move item of the same
 * move (tmcraft:egg_<move>), one for one. Egg move items are otherwise only sold by the Pokemon Breeder villager, which
 * cannot spawn on this server. Messages are plain Korean text since clients do not have this mod.
 */
public class TmEgg implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("tmegg");
    private static final String NAMESPACE = "tmcraft";
    private static final String TM_PREFIX = "tm_";
    private static final String EGG_PREFIX = "egg_";

    @Override
    public void onInitialize() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) ->
            dispatcher.register(CommandManager.literal("tmegg")
                .executes(ctx -> convert(ctx.getSource().getPlayerOrThrow()) ? 1 : 0)));
    }

    private static boolean convert(ServerPlayerEntity player) {
        ItemStack held = player.getMainHandStack();
        Identifier id = Registries.ITEM.getId(held.getItem());

        if (held.isEmpty() || !id.getNamespace().equals(NAMESPACE) || !id.getPath().startsWith(TM_PREFIX)) {
            player.sendMessage(Text.literal("기술머신(TMCraft)을 주로 쓰는 손에 들고 /tmegg 를 입력하세요.")
                .formatted(Formatting.RED));
            return false;
        }

        String move = id.getPath().substring(TM_PREFIX.length());
        Optional<Item> egg = Registries.ITEM.getOrEmpty(Identifier.of(NAMESPACE, EGG_PREFIX + move));

        if (egg.isEmpty()) {
            player.sendMessage(Text.literal("이 기술은 알 기술 아이템이 없어서 바꿀 수 없습니다.").formatted(Formatting.RED));
            return false;
        }

        int count = held.getCount();
        ItemStack result = new ItemStack(egg.get(), count);
        player.setStackInHand(Hand.MAIN_HAND, result);
        player.sendMessage(Text.literal("기술머신 " + count + "개를 알 기술 ").formatted(Formatting.GREEN)
            .append(result.getName())
            .append(Text.literal(" " + count + "개로 바꿨습니다.").formatted(Formatting.GREEN)));
        LOGGER.info("{} converted {} x{} into {}", player.getGameProfile().getName(), id, count,
            Registries.ITEM.getId(egg.get()));
        return true;
    }
}
