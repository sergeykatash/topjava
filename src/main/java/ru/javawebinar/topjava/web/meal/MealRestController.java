package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.ValidationUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.util.List;

@Controller
public class MealRestController {

    private MealService service;

    @Autowired
    public MealRestController(MealService service) {
        this.service = service;
    }

    public Meal get(int id){
        int userId = SecurityUtil.authUserId();
        return service.get(id, userId);
    }

    public void delete(int id){
        int userId = SecurityUtil.authUserId();
        service.delete(id, userId);
    }

    public List<MealWithExceed> getAll(){
        int userId = SecurityUtil.authUserId();
        return MealsUtil.getWithExceeded(service.getAll(userId), SecurityUtil.authUserCaloriesPerDay());
    }

    public Meal create(Meal meal){
        int userId = SecurityUtil.authUserId();
        return service.create(meal, userId);
    }

    public void update(Meal meal, int id){
        int userId = SecurityUtil.authUserId();
        ValidationUtil.assureIdConsistent(meal, id);
        service.update(meal, userId);
    }

}