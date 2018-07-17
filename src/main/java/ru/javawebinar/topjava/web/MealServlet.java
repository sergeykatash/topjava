package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.repository.MealRepositiryImpl;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private MealRepositiryImpl mealRepositiry;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        mealRepositiry = new MealRepositiryImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("forward to meals");

        String action = request.getParameter("action");
        switch (action == null ? "info" : action){
            case "update":
                Meal meal = mealRepositiry.getById(Integer.parseInt(request.getParameter("id")));
                request.setAttribute("meal", meal);
                request.getRequestDispatcher("/addMeal.jsp").forward(request, response);
                break;
            case "delete":
                mealRepositiry.remove(Integer.parseInt(request.getParameter("id")));
                response.sendRedirect("meals");
                break;
            case "info":
            default:
                request.setAttribute("mealList", MealsUtil.getWithExceeded(mealRepositiry.getAll(), MealsUtil.CALORIESPERDAY));
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("doPost of mealServlet");

        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if ("save".equals(action)) {
            Meal meal = mealRepositiry.getById(Integer.parseInt(request.getParameter("id")));
            meal.setDescription(request.getParameter("newDescr"));
            meal.setCalories(Integer.parseInt(request.getParameter("newCalory")));
            mealRepositiry.update(meal);
        }
        response.sendRedirect("meals");
    }
}
