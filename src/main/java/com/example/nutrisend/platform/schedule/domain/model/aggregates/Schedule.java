package com.example.nutrisend.platform.schedule.domain.model.aggregates;
import com.example.nutrisend.platform.user.domain.model.aggregates.Users;
import jakarta.persistence.*;


import java.util.HashMap;
import java.util.Map;


@Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @ElementCollection
    @CollectionTable(name = "schedule_days", joinColumns = @JoinColumn(name = "schedule_id"))
    @MapKeyColumn(name = "day")
    @Column(name = "meal_id") // Aquí se asigna un ID de comida
    private Map<String, Long> days = new HashMap<>(); // Cambia a un solo Long

    public Schedule() {}

    public Schedule(Long userId, Map<String, Long> days) {
        this.userId = userId;
        this.days = days;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Map<String, Long> getDays() {
        return days;
    }

    public void setDays(Map<String, Long> days) {
        this.days = days;
    }
}
