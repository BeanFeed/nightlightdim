package tk.beanfeed.nightlightdim.entity.goals;

import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import tk.beanfeed.nightlightdim.entity.custom.ReaperEntity;

public class ReaperAttackGoal extends MeleeAttackGoal {
    private final ReaperEntity reaper;
    private int ticks;

    public ReaperAttackGoal(ReaperEntity reaper, double speed, boolean pauseWhenMobIdle) {
        super(reaper, speed, pauseWhenMobIdle);
        this.reaper = reaper;
    }

    public void start() {
        super.start();
        this.ticks = 0;
    }

    public void stop() {
        super.stop();
        this.reaper.setAttacking(false);
    }

    public void tick() {
        super.tick();
        ++this.ticks;
        if (this.ticks >= 5 && this.getCooldown() < this.getMaxCooldown() / 2) {
            this.reaper.setAttacking(true);
        } else {
            this.reaper.setAttacking(false);
        }

    }
}
