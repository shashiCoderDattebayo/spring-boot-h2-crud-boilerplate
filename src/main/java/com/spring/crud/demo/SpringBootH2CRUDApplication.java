package com.spring.crud.demo;

import java.util.List;

import com.spring.crud.demo.models.Branch;
import com.spring.crud.demo.models.Price;
import com.spring.crud.demo.models.Vehicle;
import com.spring.crud.demo.service.BranchService;
import com.spring.crud.demo.service.PriceService;
import com.spring.crud.demo.service.VehicleService;
import com.spring.crud.demo.utils.HelperUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@SpringBootApplication
@EnableJpaRepositories
public class SpringBootH2CRUDApplication {

    @Autowired
    private BranchService branchService;
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private PriceService priceService;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootH2CRUDApplication.class, args);
    }

    @Bean
    CommandLineRunner runner() {
        return args -> {

            List<Branch> branches = branchService.findAll();
            if (branches.isEmpty()) {
                log.info("******* Inserting branches to DB *******");
                for (Branch branch : HelperUtil.branchesSupplier.get()) {
                    branchService.save(branch);
                }
            } else {
                log.info("******* Branches stored in DB Size :: {}", branches.size());
                log.info("******* Branches stored in DB :: {}", branches);
            }

            Thread.sleep(2000);
            List<Branch> branchesUpdated = branchService.findAll();
            log.info("******* Branches stored in DB :: {}", branchesUpdated);
            int branchId = branchesUpdated.get(0).getId();

            List<Vehicle> vehicles = vehicleService.findAll();
            if (vehicles.isEmpty()) {
                log.info("******* Inserting vehicles to DB *******");
                for (Vehicle vehicle : HelperUtil.vehicleSupplier.get()) {
                    vehicleService.save(new Vehicle(vehicle.getVehicleNumber(), vehicle.getType(), branchId));
                }
            } else {
                log.info("******* Vehicles stored in DB Size :: {}", vehicles.size());
                log.info("******* Vehicles stored in DB :: {}", vehicles);
            }

            List<Price> prices = priceService.findAll();
            if (prices.isEmpty()) {
                log.info("******* Inserting prices to DB *******");
                for (Price price : HelperUtil.priceSupplier.get()) {
                    priceService.save(new Price(price.getType(), branchId, price.getPrice()));
                }
            } else {
                log.info("******* Prices stored in DB Size :: {}", prices.size());
                log.info("******* Prices stored in DB :: {}", prices);
            }
        };
    }

}
