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
import com.astralife.employee.repository.DivisionsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Optional;

public class DivisionsTableSeeder {
    private Logger logger = LoggerFactory.getLogger(DivisionsTableSeeder.class);

    private DivisionsRepository repository;

    public DivisionsTableSeeder(DivisionsRepository repository) {
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
        this.entity(1, "Ticketing");
        this.entity(2, "Loading");
        this.entity(3, "HRD");
        this.entity(4, "IT");

    }

    private void entity(Integer id, String name) {

        Optional<Divisions> optional = Optional.ofNullable(this.repository.findOneById(id));

        if (!optional.isPresent()) {
            Divisions data = new Divisions();

            data.setId(id);
            data.setName(name);
            data.setCreatedDate(new Date());
            data.setCreatedBy("migration");

            Divisions Divisions = this.repository.save(data);

            logger.info("Divisions id {} migrated", Divisions.getId());
        } else {
            //logger.info("Divisions id {} data seeding not required", uuid);
        }
    }
}
