package mwo.lab.repositories;

import mwo.lab.models.Table;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TableRepository extends CrudRepository<Table, Integer> {
    @Query("select t from Table t " +
            "where t.size >= :guestNumber " +
            "order by t.size")
    List<Table> getFreeTable(@Param("guestNumber") Integer guestNumber);

}
