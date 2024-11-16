package com.example.nutrisend.platform.schedule.domain.model.aggregates;

import com.example.nutrisend.platform.meals.domain.model.aggregates.Meals;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class DailyMeals {

    @ManyToOne
    @JoinColumn(name = "breakfast_id")
    private Meals breakfast;

    @ManyToOne
    @JoinColumn(name = "lunch_id")
    private Meals lunch;

    @ManyToOne
    @JoinColumn(name = "dinner_id")
    private Meals dinner;

    public DailyMeals(){}

    public DailyMeals(Meals breakfast, Meals lunch, Meals dinner){
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
    }
}
































