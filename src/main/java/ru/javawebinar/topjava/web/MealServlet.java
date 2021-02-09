package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.MealTo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);

    private List<MealTo> MealList = Arrays.asList(
            new MealTo(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "breakfast", 500, false),
            new MealTo(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "dinner", 700, false),
            new MealTo(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "breakfast", 1500, true),
            new MealTo(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "dinner", 1700, true));


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("MealList", MealList);
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
