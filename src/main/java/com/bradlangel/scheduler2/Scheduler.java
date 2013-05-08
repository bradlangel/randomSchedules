package com.bradlangel.scheduler2;


import com.bradlangel.scheduler2.collector.TaskCollector;
import com.bradlangel.scheduler2.logic.Logic;
import com.bradlangel.scheduler2.output.Outputter;

import java.util.List;

/**
 * User: blangel
 * Date: 4/13/13
 * Time: 4:23 PM
 */
public final class Scheduler {

    private final TaskCollector taskCollector;

    private final Logic logic;

    private final Outputter outputter;

    public Scheduler(TaskCollector taskCollector, Logic logic, Outputter outputter) {
        this.taskCollector = taskCollector;
        this.logic = logic;
        this.outputter = outputter;
    }

    public void schedule() {
        List<Task> tasks = taskCollector.collect();
        List<ScheduledTask> scheduledTasks = logic.prioritize(tasks);
        outputter.output(scheduledTasks);
    }

}
