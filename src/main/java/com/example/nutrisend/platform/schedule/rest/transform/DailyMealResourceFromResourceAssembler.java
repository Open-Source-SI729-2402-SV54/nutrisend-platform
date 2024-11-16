package com.example.nutrisend.platform.schedule.rest.transform;

import com.example.nutrisend.platform.meals.domain.model.aggregates.Meals;
import com.example.nutrisend.platform.schedule.domain.model.aggregates.DailyMeals;
import com.example.nutrisend.platform.schedule.rest.resources.DailyMealResource;

import java.util.Map;

public class DailyMealResourceFromResourceAssembler {

    public static DailyMeals toEntity(DailyMealResource resource, Map<Long, Meals> mealsMap) {
        Meals breakfast = mealsMap.get(resource.breakfast().id());
        Meals lunch = mealsMap.get(resource.lunch().id());
        Meals dinner = mealsMap.get(resource.dinner().id());

        return new DailyMeals(breakfast, lunch, dinner);
    }
}
