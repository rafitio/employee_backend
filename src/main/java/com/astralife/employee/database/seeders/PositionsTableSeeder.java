/*
 * Copyright (c) 2020.
 * All Right Reserved
 * Rafi Tio Farabi.
 * Last Modified : 7/29/20, 10:51 PM
 *
 */

package com.astralife.employee.database.seeders;

import com.astralife.employee.entity.Divisions;
import com.astralife.employee.entity.Positions;
import com.astralife.employee.repository.PositionsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Optional;

public class PositionsTableSeeder {
    private Logger logger = LoggerFactory.getLogger(PositionsTableSeeder.class);

    private PositionsRepository repository;

    public PositionsTableSeeder(PositionsRepository repository) {
        this.repository = repository;
    }

    public void run() {
        try {
            this.data();
        } catch (Exception e) {
            logger.error("An exception occurred", e);
        }
    }

    private void data() {
        this.entity(1, "1", "Staff");
        this.entity(2, "2", "Supervisor");
        this.entity(3, "3",  "Assistant Manager");
        this.entity(4, "4", "Manager");

    }

    private void entity(Integer id, String level, String name) {
        Optional<Positions> optional = Optional.ofNullable(this.repository.findOneById(id));

        if (!optional.isPresent()) {
            Positions data = new Positions();

            data.setId(id);
            data.setLevel(level);
            data.setName(name);
            data.setCreatedDate(new Date());
            data.setCreatedBy("migration");

            Positions Positions = this.repository.save(data);

            logger.info("Positions id {} migrated", Positions.getId());
        } else {
            //logger.info("Positions id {} data seeding not required", uuid);
        }
    }
}
