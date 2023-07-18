package backend.persist.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class QuantityPayTradeUnion {
    private int count;
    @Id
    private String name;
}
