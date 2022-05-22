package com.ird.faa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.ird.faa.bean.Paiement;


@Repository
public interface PaiementDao extends JpaRepository<Paiement,Long> {



@Query("SELECT item FROM Paiement item ")
List<Paiement> findAll();


    Paiement findByRef(String ref);

    int deleteByRef(String ref);

    List<Paiement> findByCommandeRef(String ref);
    int deleteByCommandeRef(String ref);

    List<Paiement> findByCommandeId(Long id);

    int deleteByCommandeId(Long id);


}
