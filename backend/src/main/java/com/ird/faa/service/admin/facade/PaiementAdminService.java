package com.ird.faa.service.admin.facade;

import java.util.List;
import com.ird.faa.bean.Paiement;
import com.ird.faa.ws.rest.provided.vo.PaiementVo;
import com.ird.faa.service.core.facade.AbstractService;

public interface PaiementAdminService extends AbstractService<Paiement,Long,PaiementVo>{



    /**
    * find Paiement from database by ref (reference)
    * @param ref - reference of Paiement
    * @return the founded Paiement , If no Paiement were
    *         found in database return  null.
    */
    Paiement findByRef(String ref);

    /**
    * find Paiement from database by id (PK) or ref (reference)
    * @param id - id of Paiement
    * @param ref - reference of Paiement
    * @return the founded Paiement , If no Paiement were
    *         found in database return  null.
    */
    Paiement findByIdOrRef(Paiement paiement);


/**
    * delete Paiement from database
    * @param id - id of Paiement to be deleted
    *
    */
    int deleteById(Long id);


    List<Paiement> findByCommandeRef(String ref);

    int deleteByCommandeRef(String ref);

    List<Paiement> findByCommandeId(Long id);

    int deleteByCommandeId(Long id);


    /**
    * delete Paiement from database by ref (reference)
    *
    * @param ref - reference of Paiement to be deleted
    * @return 1 if Paiement deleted successfully
    */
    int deleteByRef(String ref);





}
