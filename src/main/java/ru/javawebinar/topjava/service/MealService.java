package ru.javawebinar.topjava.service;

import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.DateTimeUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

import static ru.javawebinar.topjava.util.MealsUtil.filterByPredicate;
import static ru.javawebinar.topjava.util.MealsUtil.getTos;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealService {

    private MealRepository repository;

    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public Collection<MealTo> getAll(int userId, int maxCalories) {
        return getTos(repository.getAll(userId), maxCalories);
    }

    public Collection<MealTo> getAllFiltered(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime,
                                             int userId, int maxCalories) {

        return filterByPredicate(repository.getAll(userId), maxCalories,
                meal -> DateTimeUtil.isDateBetweenHalfOpen(meal.getDateTime().toLocalDate(), startDate, endDate)
                        && DateTimeUtil.isBetweenHalfOpen(meal.getDateTime().toLocalTime(), startTime, endTime));
    }

    public Meal getById(int mealId, int userId) {
        return checkNotFoundWithId(repository.get(mealId, userId), mealId);
    }

    public boolean deleteById(int mealId, int userId) {
        return repository.delete(mealId, userId);
    }

    public Meal save(Meal meal, int userId) {
        return repository.save(meal, userId);
    }

}