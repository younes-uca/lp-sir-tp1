package com.ird.faa.ws.rest.provided.facade.admin;

import com.ird.faa.bean.Commande;
import com.ird.faa.service.admin.facade.CommandeAdminService;
import com.ird.faa.ws.rest.provided.converter.CommandeConverter;
import com.ird.faa.ws.rest.provided.vo.CommandeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("Manages commande services")
@RestController
@RequestMapping("api/admin/commande")
public class CommandeRestAdmin {

    @Autowired
    private CommandeAdminService commandeService;

    @Autowired
    private CommandeConverter commandeConverter;


    @ApiOperation("Updates the specified  commande")
    @PutMapping("/")
    public CommandeVo update(@RequestBody CommandeVo commandeVo) {
        Commande commande = commandeConverter.toItem(commandeVo);
        commande = commandeService.update(commande);
        return commandeConverter.toVo(commande);
    }

    @ApiOperation("Finds a list of all commandes")
    @GetMapping("/")
    public List<CommandeVo> findAll() {
        commandeConverter.initList(false);
        List<CommandeVo> result = commandeConverter.toVo(commandeService.findAll());
        commandeConverter.initList(true);
        return result;
    }

    @ApiOperation("Finds a commande with associated lists by id")
    @GetMapping("/detail/id/{id}")
    public CommandeVo findByIdWithAssociatedList(@PathVariable Long id) {
        return commandeConverter.toVo(commandeService.findByIdWithAssociatedList(id));
    }

    @ApiOperation("Search commande by a specific criteria")
    @PostMapping("/search")
    public List<CommandeVo> findByCriteria(@RequestBody CommandeVo commandeVo) {
        commandeConverter.setPaiements(false);
        List<CommandeVo> commandeVos = commandeConverter.toVo(commandeService.findByCriteria(commandeVo));
        commandeConverter.setPaiements(true);
        return commandeVos;
    }

    @ApiOperation("Finds a commande by id")
    @GetMapping("/id/{id}")
    public CommandeVo findById(@PathVariable Long id) {
        return commandeConverter.toVo(commandeService.findById(id));
    }

    @ApiOperation("Saves the specified  commande")
    @PostMapping("/")
    public CommandeVo save(@RequestBody CommandeVo commandeVo) {
        Commande commande = commandeConverter.toItem(commandeVo);
        commande = commandeService.save(commande);
        return commandeConverter.toVo(commande);
    }

    @ApiOperation("Delete the specified commande")
    @DeleteMapping("/")
    public int delete(@RequestBody CommandeVo commandeVo) {
        Commande commande = commandeConverter.toItem(commandeVo);
        return commandeService.delete(commande);
    }

    @ApiOperation("Deletes a commande by id")
    @DeleteMapping("/id/{id}")
    public int deleteById(@PathVariable Long id) {
        return commandeService.deleteById(id);
    }


}
