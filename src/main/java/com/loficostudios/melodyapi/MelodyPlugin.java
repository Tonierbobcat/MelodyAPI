package com.loficostudios.melodyapi;

import com.loficostudios.melodyapi.gui.GuiManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
<<<<<<< Updated upstream
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

=======
import org.jetbrains.annotations.ApiStatus;

import java.util.logging.Level;

@Deprecated
@ApiStatus.ScheduledForRemoval(inVersion = "1.0")
>>>>>>> Stashed changes
public abstract class MelodyPlugin<T extends MelodyPlugin<T>> extends JavaPlugin {

    protected void onStart() {
    }

    private Scheduler scheduler;

    @Override
    public void onEnable() {
        scheduler = new Scheduler(this);
        Bukkit.getPluginManager().registerEvents(new GuiManager(this), this);
        onStart();
    }

    public Scheduler getScheduler() {
        return scheduler;
    }

    public void runTaskLater(Runnable runnable, long i) {
        scheduler.runTaskLater(runnable, i);
    }

    public static class Scheduler {
        private final JavaPlugin plugin;

        public Scheduler(JavaPlugin plugin) {
            this.plugin = plugin;
        }

        public BukkitTask runTask(Runnable runnable) {
            if (runnable instanceof BukkitRunnable) {
                return ((BukkitRunnable) runnable).runTask(plugin);
            } else {
                return new BukkitRunnable() {
                    @Override
                    public void run() {
                        runnable.run();
                    }
                }.runTask(plugin);
            }
        }

        public BukkitTask runTaskAsynchronously(Runnable runnable) {
            if (runnable instanceof BukkitRunnable) {
                return ((BukkitRunnable) runnable).runTaskAsynchronously(plugin);
            } else {
                return new BukkitRunnable() {
                    @Override
                    public void run() {
                        runnable.run();
                    }
                }.runTaskAsynchronously(plugin);
            }
        }

        public BukkitTask runTaskLater(Runnable runnable, long delay) {
            if (runnable instanceof BukkitRunnable) {
                return ((BukkitRunnable) runnable).runTaskLater(plugin, delay);
            } else {
                return new BukkitRunnable() {
                    @Override
                    public void run() {
                        runnable.run();
                    }
                }.runTaskLater(plugin, delay);
            }
        }

        public BukkitTask runTaskLaterAsynchronously(Runnable runnable, long delay) {
            if (runnable instanceof BukkitRunnable) {
                return ((BukkitRunnable) runnable).runTaskLaterAsynchronously(plugin, delay);
            } else {
                return new BukkitRunnable() {
                    @Override
                    public void run() {
                        runnable.run();
                    }
                }.runTaskLaterAsynchronously(plugin, delay);
            }
        }

        public BukkitTask runTaskTimer(Runnable runnable, long delay, long period) {
            if (runnable instanceof BukkitRunnable) {
                return ((BukkitRunnable) runnable).runTaskTimer(plugin, delay, period);
            } else {
                return new BukkitRunnable() {
                    @Override
                    public void run() {
                        runnable.run();
                    }
                }.runTaskTimer(plugin, delay, period);
            }
        }

        public BukkitTask runTaskTimerAsynchronously(Runnable runnable, long delay, long period) {
            if (runnable instanceof BukkitRunnable) {
                return ((BukkitRunnable) runnable).runTaskTimerAsynchronously(plugin, delay, period);
            } else {
                return new BukkitRunnable() {
                    @Override
                    public void run() {
                        runnable.run();
                    }
                }.runTaskTimerAsynchronously(plugin, delay, period);
            }
        }
    }
}
