package com.astralife.employee.util.profiler.when;

/*
 * Copyright(c) 2020. All rights reserved.
 * Last modified 01/06/20 06.04
 */

import com.astralife.employee.util.profiler.When;
import org.slf4j.Logger;
import org.slf4j.profiler.Profiler;

public class Fine implements When {
    @Override public void run(String task, Profiler profiler, Logger logger, Throwable throwable) {
        logger.info(task);
        profiler.stop().print();
    }
}
