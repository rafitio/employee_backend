package com.astralife.employee.util.profiler;

/*
 * Copyright(c) 2020. All rights reserved.
 * Last modified 01/06/20 07.38
 */

import org.slf4j.Logger;
import org.slf4j.profiler.Profiler;

public interface When {
    void run(String task, Profiler profiler, Logger logger, Throwable throwable);
}
