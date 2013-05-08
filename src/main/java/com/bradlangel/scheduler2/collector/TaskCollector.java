package com.bradlangel.scheduler2.collector;



import com.bradlangel.scheduler2.Task;

import java.util.List;

/**
 * User: blangel
 * Date: 4/13/13
 * Time: 4:41 PM
 */
public interface TaskCollector {

    List<Task> collect();

}
