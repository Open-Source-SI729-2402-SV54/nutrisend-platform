package com.example.nutrisend.platform.availability.domain.model.aggregates;

import com.example.nutrisend.platform.availability.domain.model.commands.CreateAvailabilityCommand;
import jakarta.persistence.*;

@Entity
public class Availability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String provider;

    @Column(nullable = false, unique = true)
    private String img;

    @Column(nullable = false, unique = true)
    private String description;

    public Availability() {}

    public Availability(String name, String provider, String img, String description) {
        this.name = name;
        this.provider = provider;
        this.img = img;
        this.description = description;
    }

    public Availability(CreateAvailabilityCommand command) {
        this.name = command.name();
        this.provider = command.provider();
        this.img = command.img();
        this.description = command.description();
    }

    public Long getId() { return id; }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getProvider() { return provider; }
    public void setProvider(String provider) { this.provider = provider; }

    public String getImg() { return img; }
    public void setImg(String img) { this.img = img; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }



}
