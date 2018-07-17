package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;

public interface MealRepositiry {
    void add(Meal meal);
    void remove(int id);
    Meal getById(int id);
    void update(Meal meal);
    Collection<Meal> getAll();

}
