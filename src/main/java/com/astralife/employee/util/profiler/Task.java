package com.astralife.employee.util.profiler;

/*
 * Copyright(c) 2020. All rights reserved.
 * Last modified 02/06/20 09.49
 */

import org.slf4j.profiler.Profiler;

public interface Task {
    Profiler run(String profilerName, String taskName);
}
