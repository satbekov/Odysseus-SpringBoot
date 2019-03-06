package kairat.dev.SP.Odysseus.Models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    public long getId() {
        return id;
    }
}
