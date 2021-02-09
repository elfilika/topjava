package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);

    private List<Meal> mealList = Arrays.asList(
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "breakfast", 500),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "dinner", 700),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "breakfast", 1500),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "dinner", 1700));


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<MealTo> mealToList = mealList.stream()
                .map(meal -> new MealTo(meal.getDateTime(), meal.getDescription(), meal.getCalories(), meal.getCalories() > 1000))
                .collect(Collectors.toList());

        log.debug("Setting attribute mealToList");
        request.setAttribute("mealToList", mealToList);
        log.debug("Forward to meals.jsp");
        request.getRequestDispatcher("meals.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
