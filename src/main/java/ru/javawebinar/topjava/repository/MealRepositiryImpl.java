package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealRepositiryImpl implements MealRepositiry {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger count = new AtomicInteger(0);

    {
        MealsUtil.meals.forEach(this::add);
    }

    @Override
    public void add(Meal meal) {
        if (meal != null){
            int currentId = count.getAndIncrement();
            meal.setId(currentId);
            repository.put(currentId, meal);
       }
    }

    @Override
    public void remove(int id) {
        repository.remove(id);
    }

    @Override
    public Meal getById(int id) {
        return repository.get(id);
    }

    @Override
    public void update(Meal meal) {
        if(repository.containsKey(meal.getId())){
            repository.replace(meal.getId(), meal);
        }
    }

    @Override
    public Collection<Meal> getAll() {
        return repository.values();
    }

    public static void main(String[] args) {
        new MealRepositiryImpl().repository.values().forEach(System.out::println);
    }
}
