package com.astralife.employee.util.profiler.task;

/*
 * Copyright(c) 2020. All rights reserved.
 * Last modified 02/06/20 09.49
 */
import org.slf4j.profiler.Profiler;

public class Task {
    private static Task  instance = new Task();
    private        Start start;

    public Task() {
        start = new Start();
    }

    public Profiler start(String profilerName, String taskName) {
        return start.run(profilerName, taskName);
    }

    public static Task getInstance() {
        return instance;
    }
}
