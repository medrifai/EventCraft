package ma.eventcraft.repositories;

import ma.eventcraft.models.SeatCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatCategoryRepository extends JpaRepository<SeatCategory, Integer> {

    // Trouver les catégories de sièges associées à un événement spécifique
    List<SeatCategory> findByEventId(int eventId);

    // Trouver les catégories de sièges avec un prix inférieur à une certaine valeur
    List<SeatCategory> findByPriceLessThan(float price);

    // Trouver les catégories de sièges disponibles pour un événement spécifique
    @Query("SELECT sc FROM SeatCategory sc WHERE sc.availableSeats > 0 AND sc.event.id = :eventId")
    List<SeatCategory> findAvailableSeatCategoriesForEvent(int eventId);

    // Trouver les catégories de sièges presque épuisées (moins de 10% des places disponibles)
    @Query("SELECT sc FROM SeatCategory sc WHERE sc.availableSeats < (sc.maxNumberOfSeats * 0.1)")
    List<SeatCategory> findAlmostSoldOutCategories();
}
