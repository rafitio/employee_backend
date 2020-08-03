package com.astralife.employee.util.profiler.task;

/*
 * Copyright(c) 2020. All rights reserved.
 * Last modified 02/06/20 09.49
 */

import com.astralife.employee.util.profiler.Task;
import org.slf4j.profiler.Profiler;

public class Start implements Task {
    @Override
    public Profiler run(String profilerName, String taskName) {
        Profiler profiler = new Profiler(profilerName);
        profiler.start(taskName + " Start");

        return profiler;
    }
}
