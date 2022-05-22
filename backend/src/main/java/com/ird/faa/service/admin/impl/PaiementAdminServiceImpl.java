package com.ird.faa.service.admin.impl;

import com.ird.faa.bean.Commande;
import com.ird.faa.bean.Paiement;
import com.ird.faa.dao.PaiementDao;
import com.ird.faa.service.admin.facade.CommandeAdminService;
import com.ird.faa.service.admin.facade.PaiementAdminService;
import com.ird.faa.service.core.impl.AbstractServiceImpl;
import com.ird.faa.service.util.ListUtil;
import com.ird.faa.service.util.SearchUtil;
import com.ird.faa.service.util.StringUtil;
import com.ird.faa.ws.rest.provided.vo.PaiementVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaiementAdminServiceImpl extends AbstractServiceImpl<Paiement> implements PaiementAdminService {

    @Autowired
    private PaiementDao paiementDao;

    @Autowired
    private CommandeAdminService commandeService;


    @Autowired
    private EntityManager entityManager;


    @Override
    public List<Paiement> findAll() {
        return paiementDao.findAll();
    }

    @Override
    public List<Paiement> findByCommandeRef(String ref) {
        return paiementDao.findByCommandeRef(ref);
    }

    @Override
    @Transactional
    public int deleteByCommandeRef(String ref) {
        return paiementDao.deleteByCommandeRef(ref);
    }

    @Override
    public List<Paiement> findByCommandeId(Long id) {
        return paiementDao.findByCommandeId(id);
    }

    @Override
    @Transactional
    public int deleteByCommandeId(Long id) {
        return paiementDao.deleteByCommandeId(id);
    }

    @Override
    public Paiement findByRef(String ref) {
        if (ref == null) return null;
        return paiementDao.findByRef(ref);
    }

    @Override
    @Transactional
    public int deleteByRef(String ref) {
        return paiementDao.deleteByRef(ref);
    }

    @Override
    public Paiement findByIdOrRef(Paiement paiement) {
        Paiement resultat = null;
        if (paiement != null) {
            if (StringUtil.isNotEmpty(paiement.getId())) {
                resultat = paiementDao.getOne(paiement.getId());
            } else if (StringUtil.isNotEmpty(paiement.getRef())) {
                resultat = paiementDao.findByRef(paiement.getRef());
            }
        }
        return resultat;
    }

    @Override
    public Paiement findById(Long id) {
        if (id == null) return null;
        return paiementDao.getOne(id);
    }

    @Override
    public Paiement findByIdWithAssociatedList(Long id) {
        return findById(id);
    }


    @Transactional
    public int deleteById(Long id) {
        int res = 0;
        if (paiementDao.findById(id).isPresent()) {
            paiementDao.deleteById(id);
            res = 1;
        }
        return res;
    }


    @Override
    public Paiement update(Paiement paiement) {
        Paiement foundedPaiement = findById(paiement.getId());
        if (foundedPaiement == null) return null;
        else {
            return paiementDao.save(paiement);
        }
    }

    @Override
    public Paiement save(Paiement paiement) {

        Paiement result = null;
        Paiement foundedPaiement = findByRef(paiement.getRef());
        if (foundedPaiement == null) {


            findCommande(paiement);

            Paiement savedPaiement = paiementDao.save(paiement);

            result = savedPaiement;
        }

        return result;
    }

    @Override
    public List<Paiement> save(List<Paiement> paiements) {
        List<Paiement> list = new ArrayList<>();
        for (Paiement paiement : paiements) {
            list.add(save(paiement));
        }
        return list;
    }


    @Override
    @Transactional
    public int delete(Paiement paiement) {
        if (paiement.getRef() == null) return -1;

        Paiement foundedPaiement = findByRef(paiement.getRef());
        if (foundedPaiement == null) return -1;
        paiementDao.delete(foundedPaiement);
        return 1;
    }


    public List<Paiement> findByCriteria(PaiementVo paiementVo) {

        String query = "SELECT o FROM Paiement o where 1=1 ";

        query += SearchUtil.addConstraint("o", "id", "=", paiementVo.getId());
        query += SearchUtil.addConstraint("o", "ref", "LIKE", paiementVo.getRef());
        query += SearchUtil.addConstraint("o", "montant", "=", paiementVo.getMontant());
        query += SearchUtil.addConstraint("o", "description", "LIKE", paiementVo.getDescription());
        query += SearchUtil.addConstraintMinMax("o", "montant", paiementVo.getMontantMin(), paiementVo.getMontantMax());
        if (paiementVo.getCommandeVo() != null) {
            query += SearchUtil.addConstraint("o", "commande.id", "=", paiementVo.getCommandeVo().getId());
            query += SearchUtil.addConstraint("o", "commande.ref", "LIKE", paiementVo.getCommandeVo().getRef());
        }

        return entityManager.createQuery(query).getResultList();
    }

    private void findCommande(Paiement paiement) {
        Commande loadedCommande = commandeService.findByIdOrRef(paiement.getCommande());

        if (loadedCommande == null) {
            return;
        }
        paiement.setCommande(loadedCommande);
    }

    @Override
    @Transactional
    public void delete(List<Paiement> paiements) {
        if (ListUtil.isNotEmpty(paiements)) {
            paiements.forEach(e -> paiementDao.delete(e));
        }
    }

    @Override
    public void update(List<Paiement> paiements) {
        if (ListUtil.isNotEmpty(paiements)) {
            paiements.forEach(e -> paiementDao.save(e));
        }
    }


}
