package ma.eventcraft.repositories;

import ma.eventcraft.models.EventCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EventCategoryRepository extends JpaRepository<EventCategory, Long> {
    List<EventCategory> findByNameContainingIgnoreCase(String name);

    @Query("SELECT ec FROM EventCategory ec WHERE SIZE(ec.events) > 0")
    List<EventCategory> findCategoriesWithEvents();

    @Query("SELECT ec FROM EventCategory ec WHERE SIZE(ec.events) = 0")
    List<EventCategory> findCategoriesWithoutEvents();

    @Query("SELECT COUNT(e) FROM EventCategory ec JOIN ec.events e WHERE ec.id = :categoryId")
    Long countEventsByCategory(@Param("categoryId") Integer categoryId);

    // Recherche des catégories avec des événements actifs
    @Query("SELECT DISTINCT ec FROM EventCategory ec JOIN ec.events e WHERE e.isSelling = true")
    List<EventCategory> findCategoriesWithActiveEvents();




    boolean existsByName(String name);
}