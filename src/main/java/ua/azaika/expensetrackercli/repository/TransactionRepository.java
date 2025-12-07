package ua.azaika.expensetrackercli.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.azaika.expensetrackercli.model.TransactionEntity;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer> {
    @Query("SELECT e FROM TransactionEntity e WHERE e.date >= :start AND e.date <= :end")
    List<TransactionEntity> findTransactionByMonth(@Param("start") LocalDate startOfMonth,
                                                   @Param("end") LocalDate endOfMonth);
}
