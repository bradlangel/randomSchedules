package com.bradlangel.scheduler2.logic;

import com.bradlangel.scheduler2.ScheduledTask;
import com.bradlangel.scheduler2.Task;

import java.util.List;

/**
 * User: blangel
 * Date: 4/13/13
 * Time: 4:31 PM
 */
public interface Logic {

    List<ScheduledTask> prioritize(List<Task> tasks);

}
