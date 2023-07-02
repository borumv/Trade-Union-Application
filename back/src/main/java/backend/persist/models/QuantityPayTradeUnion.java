package backend.persist.models;

import backend.persist.entity.PersonEntity;
import lombok.Data;
import org.springframework.context.annotation.Bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Data
@Entity
public class QuantityPayTradeUnion {
    private int count;
    @Id
    private String name;


}
