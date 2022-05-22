package com.ird.faa.bean;

import java.util.Objects;



    import java.math.BigDecimal;
import javax.persistence.*;



@Entity
@Table(name = "paiement")
public class Paiement   {

@Id
    @SequenceGenerator(name="paiement_seq",sequenceName="paiement_seq",
    allocationSize=1, initialValue = 10000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="paiement_seq")
private Long id;

            @Column(length = 500)
            private String ref;
            private BigDecimal montant ;
            @Column(length = 500)
            private String description;

    @ManyToOne
    private Commande commande ;


public Paiement(){
super();
}


            public Long getId(){
            return this.id;
            }
            public void setId(Long id){
            this.id = id;
            }
            public String getRef(){
            return this.ref;
            }
            public void setRef(String ref){
            this.ref = ref;
            }
            public BigDecimal getMontant(){
            return this.montant;
            }
            public void setMontant(BigDecimal montant){
            this.montant = montant;
            }
            public String getDescription(){
            return this.description;
            }
            public void setDescription(String description){
            this.description = description;
            }
            public Commande getCommande(){
            return this.commande;
            }
            public void setCommande(Commande commande){
            this.commande = commande;
            }

        @Override
        public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paiement paiement = (Paiement) o;
        return id != null && id.equals(paiement.id);
        }

        @Override
        public int hashCode() {
        return Objects.hash(id);
        }

}

