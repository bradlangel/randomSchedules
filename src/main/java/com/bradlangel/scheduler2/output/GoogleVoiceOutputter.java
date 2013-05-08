package com.bradlangel.scheduler2.output;



import com.bradlangel.scheduler2.ScheduledTask;

import java.util.List;

/**
 * User: blangel
 * Date: 4/13/13
 * Time: 4:22 PM
 */
public final class GoogleVoiceOutputter implements Outputter {

    @Override public void output(List<ScheduledTask> tasks) {
        System.out.println("google!");
    }
}
