package com.ird.faa.service.admin.impl;

import com.ird.faa.bean.Commande;
import com.ird.faa.bean.Paiement;
import com.ird.faa.dao.CommandeDao;
import com.ird.faa.service.admin.facade.CommandeAdminService;
import com.ird.faa.service.admin.facade.PaiementAdminService;
import com.ird.faa.service.core.impl.AbstractServiceImpl;
import com.ird.faa.service.util.ListUtil;
import com.ird.faa.service.util.SearchUtil;
import com.ird.faa.service.util.StringUtil;
import com.ird.faa.ws.rest.provided.vo.CommandeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommandeAdminServiceImpl extends AbstractServiceImpl<Commande> implements CommandeAdminService {

    @Autowired
    private CommandeDao commandeDao;

    @Autowired
    private PaiementAdminService paiementService;


    @Autowired
    private EntityManager entityManager;


    @Override
    public List<Commande> findAll() {
        return commandeDao.findAll();
    }

    @Override
    public Commande findByRef(String ref) {
        if (ref == null) return null;
        return commandeDao.findByRef(ref);
    }

    @Override
    @Transactional
    public int deleteByRef(String ref) {
        return commandeDao.deleteByRef(ref);
    }

    @Override
    public Commande findByIdOrRef(Commande commande) {
        Commande resultat = null;
        if (commande != null) {
            if (StringUtil.isNotEmpty(commande.getId())) {
                resultat = commandeDao.getOne(commande.getId());
            } else if (StringUtil.isNotEmpty(commande.getRef())) {
                resultat = commandeDao.findByRef(commande.getRef());
            }
        }
        return resultat;
    }

    @Override
    public Commande findById(Long id) {
        if (id == null) return null;
        return commandeDao.getOne(id);
    }

    @Override
    public Commande findByIdWithAssociatedList(Long id) {
        Commande commande = findById(id);
        findAssociatedLists(commande);
        return commande;
    }

    private void findAssociatedLists(Commande commande) {
        if (commande != null && commande.getId() != null) {
            List<Paiement> paiements = paiementService.findByCommandeId(commande.getId());
            commande.setPaiements(paiements);
        }
    }

    private void deleteAssociatedLists(Long id) {
        if (id != null) {
            paiementService.deleteByCommandeId(id);
        }
    }

    private void updateAssociatedLists(Commande commande) {
        if (commande != null && commande.getId() != null) {
            List
                    <List<Paiement>> resultPaiements = paiementService.getToBeSavedAndToBeDeleted(paiementService.findByCommandeId(commande.getId()), commande.getPaiements());
            paiementService.delete(resultPaiements.get(1));
            associatePaiement(commande, resultPaiements.get(0));
            paiementService.update(resultPaiements.get(0));

        }
    }

    @Transactional
    public int deleteById(Long id) {
        int res = 0;
        if (commandeDao.findById(id).isPresent()) {
            deleteAssociatedLists(id);
            commandeDao.deleteById(id);
            res = 1;
        }
        return res;
    }


    @Override
    public Commande update(Commande commande) {
        Commande foundedCommande = findById(commande.getId());
        if (foundedCommande == null) return null;
        else {
            updateAssociatedLists(commande);
            return commandeDao.save(commande);
        }
    }

    @Override
    public Commande save(Commande commande) {

        Commande result = null;
        Commande foundedCommande = findByRef(commande.getRef());
        if (foundedCommande == null) {


            Commande savedCommande = commandeDao.save(commande);

            savePaiements(savedCommande, commande.getPaiements());
            result = savedCommande;
        }

        return result;
    }

    @Override
    public List<Commande> save(List<Commande> commandes) {
        List<Commande> list = new ArrayList<>();
        for (Commande commande : commandes) {
            list.add(save(commande));
        }
        return list;
    }

    private List<Paiement> preparePaiements(Commande commande, List<Paiement> paiements) {
        for (Paiement paiement : paiements) {
            paiement.setCommande(commande);
        }
        return paiements;
    }


    @Override
    @Transactional
    public int delete(Commande commande) {
        if (commande.getRef() == null) return -1;

        Commande foundedCommande = findByRef(commande.getRef());
        if (foundedCommande == null) return -1;
        commandeDao.delete(foundedCommande);
        return 1;
    }


    public List<Commande> findByCriteria(CommandeVo commandeVo) {

        String query = "SELECT o FROM Commande o where 1=1 ";

        query += SearchUtil.addConstraint("o", "id", "=", commandeVo.getId());
        query += SearchUtil.addConstraint("o", "ref", "LIKE", commandeVo.getRef());
        query += SearchUtil.addConstraint("o", "total", "=", commandeVo.getTotal());
        query += SearchUtil.addConstraint("o", "totalPayer", "=", commandeVo.getTotalPayer());
        query += SearchUtil.addConstraintMinMax("o", "total", commandeVo.getTotalMin(), commandeVo.getTotalMax());
        query += SearchUtil.addConstraintMinMax("o", "totalPayer", commandeVo.getTotalPayerMin(), commandeVo.getTotalPayerMax());
        return entityManager.createQuery(query).getResultList();
    }

    private void savePaiements(Commande commande, List<Paiement> paiements) {

        if (ListUtil.isNotEmpty(commande.getPaiements())) {
            List<Paiement> savedPaiements = new ArrayList<>();
            paiements.forEach(element -> {
                element.setCommande(commande);
                paiementService.save(element);
            });
            commande.setPaiements(savedPaiements);
        }
    }


    @Override
    @Transactional
    public void delete(List<Commande> commandes) {
        if (ListUtil.isNotEmpty(commandes)) {
            commandes.forEach(e -> commandeDao.delete(e));
        }
    }

    @Override
    public void update(List<Commande> commandes) {
        if (ListUtil.isNotEmpty(commandes)) {
            commandes.forEach(e -> commandeDao.save(e));
        }
    }

    private void associatePaiement(Commande commande, List<Paiement> paiement) {
        if (ListUtil.isNotEmpty(paiement)) {
            paiement.forEach(e -> e.setCommande(commande));
        }
    }


}
