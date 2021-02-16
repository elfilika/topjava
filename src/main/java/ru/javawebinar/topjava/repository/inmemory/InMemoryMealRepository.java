package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFound;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private final Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.meals.forEach(meal -> save(meal, authUserId()));
    }

    @Override
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            if (meal.getUserId() == null) { //for hardcode userId in MealsUtil
                meal.setUserId(userId);
            }
            repository.put(meal.getId(), meal);
            return meal;
        }
        // handle case: update, but not present in storage
        if (!repository.get(meal.getId()).getUserId().equals(userId)) {
            checkNotFound(false, meal.getId().toString());
        }
        return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int id, int userId) {
        if (!repository.get(id).getUserId().equals(userId)) {
            checkNotFoundWithId(false, id);
        }
        return repository.remove(id) != null;
    }

    @Override
    public Meal get(int id, int userId) {
        final Meal meal = repository.get(id);
        if (meal != null && meal.getUserId().equals(userId)) {
            return meal;
        }
        checkNotFoundWithId(false, id);
        return null;
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        Comparator<Meal> comparator = Comparator.comparing(Meal::getDateTime);
        return repository.values().stream().filter(meal -> meal.getUserId().
                equals(userId)).sorted(comparator.reversed()).collect(Collectors.toList());
    }
}

