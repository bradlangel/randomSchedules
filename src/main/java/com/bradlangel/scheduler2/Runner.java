package com.bradlangel.scheduler2;


import com.bradlangel.scheduler2.collector.TaskCollector;
import com.bradlangel.scheduler2.logic.Logic;
import com.bradlangel.scheduler2.output.FileOutputter;
import com.bradlangel.scheduler2.output.GoogleVoiceOutputter;
import com.bradlangel.scheduler2.output.Outputter;

/**
 * User: blangel
 * Date: 4/13/13
 * Time: 4:30 PM
 */
public final class Runner {

    public static void main(String[] args) {
        TaskCollector taskCollector = null; // TODO
        Logic logic = null; // TODO
        Outputter outputter;
        if (args.length > 0 && "file".equals(args[0])) {
            outputter = new FileOutputter();
        } else {
            outputter = new GoogleVoiceOutputter();
        }
        Scheduler scheduler = new Scheduler(taskCollector, logic, outputter);
        scheduler.schedule();
    }

}
