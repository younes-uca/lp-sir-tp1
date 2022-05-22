package com.ird.faa.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "commande")
public class Commande {

    @Id
    @SequenceGenerator(name = "commande_seq", sequenceName = "commande_seq",
            allocationSize = 1, initialValue = 10000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "commande_seq")
    private Long id;

    @Column(length = 500)
    private String ref;
    private BigDecimal total;
    private BigDecimal totalPayer;


    @OneToMany(mappedBy = "commande")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Paiement> paiements;

    public Commande() {
        super();
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRef() {
        return this.ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public BigDecimal getTotal() {
        return this.total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getTotalPayer() {
        return this.totalPayer;
    }

    public void setTotalPayer(BigDecimal totalPayer) {
        this.totalPayer = totalPayer;
    }

    public List<Paiement> getPaiements() {
        return this.paiements;
    }

    public void setPaiements(List<Paiement> paiements) {
        this.paiements = paiements;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commande commande = (Commande) o;
        return id != null && id.equals(commande.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

