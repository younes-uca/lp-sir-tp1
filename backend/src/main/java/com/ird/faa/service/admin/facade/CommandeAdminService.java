package com.ird.faa.service.admin.facade;

import java.util.List;
import com.ird.faa.bean.Commande;
import com.ird.faa.ws.rest.provided.vo.CommandeVo;
import com.ird.faa.service.core.facade.AbstractService;

public interface CommandeAdminService extends AbstractService<Commande,Long,CommandeVo>{



    /**
    * find Commande from database by ref (reference)
    * @param ref - reference of Commande
    * @return the founded Commande , If no Commande were
    *         found in database return  null.
    */
    Commande findByRef(String ref);

    /**
    * find Commande from database by id (PK) or ref (reference)
    * @param id - id of Commande
    * @param ref - reference of Commande
    * @return the founded Commande , If no Commande were
    *         found in database return  null.
    */
    Commande findByIdOrRef(Commande commande);


/**
    * delete Commande from database
    * @param id - id of Commande to be deleted
    *
    */
    int deleteById(Long id);




    /**
    * delete Commande from database by ref (reference)
    *
    * @param ref - reference of Commande to be deleted
    * @return 1 if Commande deleted successfully
    */
    int deleteByRef(String ref);





}
