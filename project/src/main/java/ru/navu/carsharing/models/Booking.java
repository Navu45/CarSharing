package ru.navu.carsharing.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "bookings")
@Getter
@Setter
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "start_time")
    private LocalDateTime startTime;
    @Column(name = "finish_time")
    private LocalDateTime finishTime;
    @Column(name = "finished")
    private boolean finished;
    @Column(name = "service_cost")
    private double serviceCost;
    @Column
    private String carNumber;
    @ManyToOne
    private User user;
    @Transient
    static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm MM-dd-yy");

    /*@ManyToOne
    private Car car;

     */

    public String getFinishTime() {
        return finishTime.format(FORMATTER);
    }

    public String getStartTime() {
        return startTime.format(FORMATTER);
    }
}
