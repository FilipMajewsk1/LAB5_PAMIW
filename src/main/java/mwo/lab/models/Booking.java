package mwo.lab.models;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "Bookings")
@NotNull(message = "The booking must not be null.")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="booking_id")
    private int id;

    @NotNull(message = "Name must not be null.")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters.")
    private String name;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate bookDate;

    @ManyToOne
    private Client client;

    @NotNull
    private String clientName;

    @ManyToOne
    private mwo.lab.models.Table table;

    @NotNull(message = "The number must not be null.")
    @Min(value = 1, message = "The number cannot be lower than 1.")
    private int guestNumber;

    @NotNull(message = "Remarks must not be null.")
    @Size(min = 0, max = 150, message = "Remarks must be between 0 and 150 characters.")
    private String additionalRemarks;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return id == booking.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
