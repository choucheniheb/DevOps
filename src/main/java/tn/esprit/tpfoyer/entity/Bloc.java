package tn.esprit.tpfoyer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Bloc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idBloc;
    @Column(name = "nom_bloc")
    String nomBloc;
    @Column(name = "capacite_bloc")
    long capaciteBloc;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "foyer_id_foyer", nullable = true)
    Foyer foyer;

    @OneToMany(mappedBy = "bloc")
    @JsonIgnore
    @ToString.Exclude
    Set<Chambre> chambres = new HashSet<Chambre>();

}

