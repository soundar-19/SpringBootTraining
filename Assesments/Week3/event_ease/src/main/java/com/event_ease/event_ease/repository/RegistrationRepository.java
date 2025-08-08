package com.event_ease.event_ease.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.event_ease.event_ease.domain.Registration;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    @Query("SELECT r FROM Registration r WHERE r.user.id = :userId")
    List<Registration> findAllByUserId(@Param("userId") Long userId);

    @Query("SELECT r FROM Registration r WHERE r.event.id = :eventId")
    List<Registration> findAllByEventId(@Param("eventId") Long eventId);
    
}
