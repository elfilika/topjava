package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static ru.javawebinar.topjava.web.SecurityUtil.authUserCaloriesPerDay;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;

public class MealRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    private MealService service;

    public List<MealTo> getAll() {
        log.info("getAll");
        return new ArrayList<>(service.getAll(authUserId(), authUserCaloriesPerDay()));
    }

    public List<MealTo> getAllFiltered(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        log.info("getAllFiltered");
        return new ArrayList<>(service.getAllFiltered(startDate, startTime, endDate, endTime, authUserId(), authUserCaloriesPerDay()));
    }

    public MealTo getById(int mealId) {
        log.info("getById");
        return service.getById(mealId, authUserId());
    }

    public boolean deleteById(int mealId) {
        log.info("deleteById");
        return service.deleteById(mealId, authUserId());
    }

    public Meal save(Meal meal) {
        log.info("deleteById");
        return service.save(meal, authUserId());
    }

}