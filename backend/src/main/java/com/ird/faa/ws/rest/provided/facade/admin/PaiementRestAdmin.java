package com.ird.faa.ws.rest.provided.facade.admin;

import com.ird.faa.bean.Paiement;
import com.ird.faa.service.admin.facade.PaiementAdminService;
import com.ird.faa.ws.rest.provided.converter.PaiementConverter;
import com.ird.faa.ws.rest.provided.vo.PaiementVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("Manages paiement services")
@RestController
@RequestMapping("api/admin/paiement")
public class PaiementRestAdmin {

    @Autowired
    private PaiementAdminService paiementService;

    @Autowired
    private PaiementConverter paiementConverter;


    @ApiOperation("Updates the specified  paiement")
    @PutMapping("/")
    public PaiementVo update(@RequestBody PaiementVo paiementVo) {
        Paiement paiement = paiementConverter.toItem(paiementVo);
        paiement = paiementService.update(paiement);
        return paiementConverter.toVo(paiement);
    }

    @ApiOperation("Finds a list of all paiements")
    @GetMapping("/")
    public List<PaiementVo> findAll() {
        return paiementConverter.toVo(paiementService.findAll());
    }

    @ApiOperation("Finds a paiement with associated lists by id")
    @GetMapping("/detail/id/{id}")
    public PaiementVo findByIdWithAssociatedList(@PathVariable Long id) {
        return paiementConverter.toVo(paiementService.findByIdWithAssociatedList(id));
    }

    @ApiOperation("Search paiement by a specific criteria")
    @PostMapping("/search")
    public List<PaiementVo> findByCriteria(@RequestBody PaiementVo paiementVo) {
        paiementConverter.setCommande(false);
        List<PaiementVo> paiementVos = paiementConverter.toVo(paiementService.findByCriteria(paiementVo));
        paiementConverter.setCommande(true);
        return paiementVos;

    }

    @ApiOperation("Finds a paiement by id")
    @GetMapping("/id/{id}")
    public PaiementVo findById(@PathVariable Long id) {
        return paiementConverter.toVo(paiementService.findById(id));
    }

    @ApiOperation("Saves the specified  paiement")
    @PostMapping("/")
    public PaiementVo save(@RequestBody PaiementVo paiementVo) {
        Paiement paiement = paiementConverter.toItem(paiementVo);
        paiement = paiementService.save(paiement);
        return paiementConverter.toVo(paiement);
    }

    @ApiOperation("Delete the specified paiement")
    @DeleteMapping("/")
    public int delete(@RequestBody PaiementVo paiementVo) {
        Paiement paiement = paiementConverter.toItem(paiementVo);
        return paiementService.delete(paiement);
    }

    @ApiOperation("Deletes a paiement by id")
    @DeleteMapping("/id/{id}")
    public int deleteById(@PathVariable Long id) {
        return paiementService.deleteById(id);
    }

    @ApiOperation("find by commande ref")
    @GetMapping("/commande/ref/{ref}")
    public List<Paiement> findByCommandeRef(@PathVariable String ref) {
        return paiementService.findByCommandeRef(ref);
    }

    @ApiOperation("delete by commande ref")
    @DeleteMapping("/commande/ref/{ref}")
    public int deleteByCommandeRef(@PathVariable String ref) {
        return paiementService.deleteByCommandeRef(ref);
    }

    @ApiOperation("find by commande id")
    @GetMapping("/commande/id/{id}")
    public List<Paiement> findByCommandeId(@PathVariable Long id) {
        return paiementService.findByCommandeId(id);
    }

    @ApiOperation("delete by commande id")
    @DeleteMapping("/commande/id/{id}")
    public int deleteByCommandeId(@PathVariable Long id) {
        return paiementService.deleteByCommandeId(id);
    }


}
