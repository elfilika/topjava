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
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.util.MealsUtil.filteredByStreams;

public class MealServlet extends HttpServlet {
    private static final int MAX_CALORIES_BY_DAY = 2000;
    private static final Logger log = getLogger(MealServlet.class);

    private List<Meal> mealList = Arrays.asList(
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "breakfast", 500),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "dinner", 700),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "breakfast", 1500),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "dinner", 1700));


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<MealTo> mealToList = filteredByStreams(mealList,
                LocalTime.MIN,
                LocalTime.MAX,
                MAX_CALORIES_BY_DAY);

        log.debug("Setting attribute mealToList");
        request.setAttribute("mealToList", mealToList);
        log.debug("Forward to meals.jsp");
        request.getRequestDispatcher("meals.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
