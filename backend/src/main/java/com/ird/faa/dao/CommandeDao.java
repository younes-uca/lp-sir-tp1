package com.ird.faa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.ird.faa.bean.Commande;


@Repository
public interface CommandeDao extends JpaRepository<Commande,Long> {



@Query("SELECT item FROM Commande item ")
List<Commande> findAll();


    Commande findByRef(String ref);

    int deleteByRef(String ref);



}
