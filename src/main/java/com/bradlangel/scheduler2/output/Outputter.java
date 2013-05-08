package com.bradlangel.scheduler2.output;



import com.bradlangel.scheduler2.ScheduledTask;

import java.util.List;

/**
 * User: blangel
 * Date: 4/13/13
 * Time: 4:22 PM
 */
public interface Outputter {

    void output(List<ScheduledTask> tasks);

}
