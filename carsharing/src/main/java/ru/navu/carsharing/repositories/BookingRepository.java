package ru.navu.carsharing.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.navu.carsharing.models.Booking;

import java.time.LocalDateTime;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Modifying
    @Query("update Booking b set b.finished = true, b.finishTime = ?1 where b.id = ?2")
    void finishBooking(LocalDateTime finishTime, Long id);
}
