package com.bradlangel.scheduler2;

/**
 * User: blangel
 * Date: 4/13/13
 * Time: 4:32 PM
 */
public class ScheduledTask {

    private final String time;

    private final Task task;

    public ScheduledTask(String time, Task task) {
        this.time = time;
        this.task = task;
    }

    public String getTime() {
        return time;
    }

    public Task getTask() {
        return task;
    }
}
