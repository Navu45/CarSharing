package ru.navu.carsharing.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.navu.carsharing.models.Car;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findCarsByLatitudeBetweenAndLongitudeBetween(double latMore,
                                                           double latLess,
                                                           double lngMore,
                                                           double lngLess);
}
