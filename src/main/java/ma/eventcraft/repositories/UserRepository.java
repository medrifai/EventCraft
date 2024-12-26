package ma.eventcraft.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ma.eventcraft.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    List<User> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
            String firstName, String lastName
    );

    boolean existsByEmail(String email);
    List<User> findByRoles(String role);

    @Query("SELECT u FROM User u WHERE SIZE(u.tickets) > :minTickets")
    List<User> findUsersWithMoreThanXTickets(int minTickets);

    @Query("SELECT COUNT(t) FROM User u JOIN u.tickets t WHERE u.id = :userId")
    Long countUserTickets(@Param("userId") Integer userId);

    @Query("SELECT COUNT(e) FROM User u JOIN u.events e WHERE u.id = :userId")
    Long countUserEvents(@Param("userId") Integer userId);

    List<User> findByCreatedAtAfter(Date date);



}