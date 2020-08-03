/*
 * Copyright (c) 2020.
 * All Right Reserved
 * Rafi Tio Farabi.
 * Last Modified : 7/29/20, 11:09 PM
 *
 */

package com.astralife.employee.database;

import com.astralife.employee.base.BaseTableSeeder;
import com.astralife.employee.database.seeders.DivisionsTableSeeder;
import com.astralife.employee.database.seeders.PositionsTableSeeder;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder extends BaseTableSeeder {

    @EventListener
    public void run(ContextRefreshedEvent event) {
        new DivisionsTableSeeder(divisionsRepository).run();
        new PositionsTableSeeder(positionsRepository).run();
    }
}
