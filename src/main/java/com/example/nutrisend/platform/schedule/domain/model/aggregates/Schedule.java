package com.example.nutrisend.platform.schedule.domain.model.aggregates;
import com.example.nutrisend.platform.user.domain.model.aggregates.Users;
import jakarta.persistence.*;


import java.util.Map;


public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userID", nullable = false)
    private Users user;

    @ElementCollection
    @CollectionTable(name = "schedule_days", joinColumns = @JoinColumn(name = "schedule_id"))
    @MapKeyColumn(name = "day")
    @Column(name = "meal_ids")
    private Map<String, Map<String, Long>> days; // Para almacenar comidas por día


    public Schedule() {}
    public Schedule(Users user, Map<String, Map<String, Long>> days) {
        this.user = user;
        this.days = days;
    }

    public Long getId(){return id;}

    public void setId(Long id){this.id = id;}

    public Users getUser(){return user;}

    public void SetUser(Users user){this.user = user;}

    public Map<String, Map<String, Long>> getDays(){
        return days;
    }

    public void setDays(Map<String, Map<String, Long>> days){ this.days = days; }



}