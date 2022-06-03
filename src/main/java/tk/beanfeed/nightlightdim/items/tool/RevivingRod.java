package tk.beanfeed.nightlightdim.items.tool;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;

public class RevivingRod extends ToolItem {
    public RevivingRod(ToolMaterial material, Settings settings) {
        super(material, settings);
    }
    /*
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        Vec3d end = user.getRotationVector().multiply(5);
         world.raycast(new RaycastContext(
                user.getEyePos(),
                end,
                RaycastContext.ShapeType.OUTLINE,
                RaycastContext.FluidHandling.NONE,
                user
        ));

    }
    */

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity player, LivingEntity mob, Hand hand) {
        float damage = 2.0f;
        if(player.getHealth() != 20.0f) {
            if (player.getHealth() <= 18.0) {
                if (mob.getHealth() >= damage) {
                    //mob.setHealth(mob.getHealth() - damage);
                    mob.damage(DamageSource.player(player), damage);
                    player.setHealth(player.getHealth() + damage);
                } else {
                    damage = mob.getHealth();
                    mob.damage(DamageSource.player(player), damage);
                    player.setHealth(player.getHealth() + damage);
                }
            } else {
                if (mob.getHealth() < (20.0 - player.getHealth())) {
                    player.setHealth(player.getHealth() + mob.getHealth());
                    mob.damage(DamageSource.player(player), mob.getHealth());
                } else {
                    damage = 20.0f - player.getHealth();
                    player.setHealth(player.getHealth() + damage);
                    mob.damage(DamageSource.player(player), damage);
                }
            }
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;

    }

}
