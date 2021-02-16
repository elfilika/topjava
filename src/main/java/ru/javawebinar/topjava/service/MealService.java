package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.DateTimeUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

import static java.util.Collections.emptyList;
import static ru.javawebinar.topjava.util.MealsUtil.filterByPredicate;
import static ru.javawebinar.topjava.util.MealsUtil.getTos;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

public class MealService {

    private MealRepository repository;

    public Collection<MealTo> getAll(int userId, int maxCalories) {
        List<MealTo> mealToList = getTos(repository.getAll(userId), maxCalories);

        if (mealToList == null) {
            mealToList = emptyList();
        }
        return mealToList;
    }

    public Collection<MealTo> getAllFiltered(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime,
                                             int userId, int maxCalories) {

        List<MealTo> mealToList = filterByPredicate(repository.getAll(userId), maxCalories,
                meal -> DateTimeUtil.isDateTimeBetweenHalfOpen(meal.getDateTime(), LocalDateTime.of(startDate, startTime),
                        LocalDateTime.of(endDate, endTime)));

        if (mealToList == null) {
            mealToList = emptyList();
        }
        return mealToList;
    }

    //MealTo or Meal??
    public MealTo getById(int mealId, int userId) {
        final Meal meal = checkNotFoundWithId(repository.get(mealId, userId), mealId);
        return new MealTo(mealId, meal.getDateTime(), meal.getDescription(), meal.getCalories(), false); //what should i pass to excess??
    }

    public boolean deleteById(int mealId, int userId) {
        final Meal meal = checkNotFoundWithId(repository.get(mealId, userId), mealId);
        return repository.delete(mealId);
    }

    //MealTo or Meal??
    public Meal save(Meal meal, int userId) {
        Meal checkedMeal = checkNotFoundWithId(repository.get(meal.getId(), userId), meal.getId());
        return repository.save(checkedMeal);
    }

}