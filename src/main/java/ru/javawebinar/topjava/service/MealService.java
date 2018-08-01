package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

public interface MealService {

    Meal create(Meal meal, int userId);

    void delete(int id, int userId) throws NotFoundException;

    Meal get(int id, int userId) throws NotFoundException;

    List<Meal> getBetweenDateTime(LocalDateTime sDate, LocalDateTime eDate, int userId);

    default List<Meal> getBetweenDate(LocalDate sDate, LocalDate eDate, int userId){
        return getBetweenDateTime(LocalDateTime.of(sDate, LocalTime.MIN), LocalDateTime.of(eDate, LocalTime.MAX), userId);
    }

    Meal update(Meal meal, int userId);

    List<Meal> getAll(int userId);
}