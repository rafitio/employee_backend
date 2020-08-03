package com.astralife.employee.util.profiler.when;

/*
 * Copyright(c) 2020. All rights reserved.
 * Last modified 01/06/20 13.07
 */
import org.slf4j.Logger;
import org.slf4j.profiler.Profiler;

public class When {
    private static When instance = new When();
    private        Fine fine;
    private        Fail fail;

    public When() {
        fine = new Fine();
        fail = new Fail();
    }

    public void fine(String task, Profiler profiler, Logger logger, Throwable throwable) {
        fine.run(task, profiler, logger, throwable);
    }

    public void fail(String task, Profiler profiler, Logger logger, Throwable throwable) {
        fail.run(task, profiler, logger, throwable);
    }

    public static When getInstance() {
        return instance;
    }
}
