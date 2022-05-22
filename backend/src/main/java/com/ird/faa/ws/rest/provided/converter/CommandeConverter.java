package  com.ird.faa.ws.rest.provided.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ird.faa.service.util.*;


import com.ird.faa.bean.Commande;
import com.ird.faa.ws.rest.provided.vo.CommandeVo;

@Component
public class CommandeConverter extends AbstractConverter<Commande,CommandeVo>{

        @Autowired
        private PaiementConverter paiementConverter ;
        private Boolean paiements;

public  CommandeConverter(){
init(true);
}

@Override
public Commande toItem(CommandeVo vo) {
if (vo == null) {
return null;
} else {
Commande item = new Commande();
        if(StringUtil.isNotEmpty(vo.getId()))
        item.setId(NumberUtil.toLong(vo.getId()));
        if(StringUtil.isNotEmpty(vo.getRef()))
        item.setRef(vo.getRef());
        if(StringUtil.isNotEmpty(vo.getTotal()))
        item.setTotal(NumberUtil.toBigDecimal(vo.getTotal()));
        if(StringUtil.isNotEmpty(vo.getTotalPayer()))
        item.setTotalPayer(NumberUtil.toBigDecimal(vo.getTotalPayer()));

        if(ListUtil.isNotEmpty(vo.getPaiementsVo()) && this.paiements)
            item.setPaiements(paiementConverter.toItem(vo.getPaiementsVo()));

return item;
}
}

@Override
public CommandeVo toVo(Commande item) {
if (item == null) {
return null;
} else {
CommandeVo vo = new CommandeVo();
        if(item.getId()!=null)
        vo.setId(NumberUtil.toString(item.getId()));

        if(StringUtil.isNotEmpty(item.getRef()))
        vo.setRef(item.getRef());

        if(item.getTotal()!=null)
        vo.setTotal(NumberUtil.toString(item.getTotal()));

        if(item.getTotalPayer()!=null)
        vo.setTotalPayer(NumberUtil.toString(item.getTotalPayer()));

        if(this.paiements && ListUtil.isNotEmpty(item.getPaiements())){
        paiementConverter.init(true);
        paiementConverter.setCommande(false);
        vo.setPaiementsVo(paiementConverter.toVo(item.getPaiements()));
        paiementConverter.setCommande(true);
        }

return vo;
}
}

    public void initList(Boolean value) {
            this.paiements = value;
    }
public void init(Boolean value) {
    initList(value);
}


        public PaiementConverter getPaiementConverter(){
        return this.paiementConverter;
        }
        public void setPaiementConverter(PaiementConverter paiementConverter ){
        this.paiementConverter = paiementConverter;
        }










        public Boolean  isPaiements(){
        return this.paiements ;
        }
        public void  setPaiements(Boolean paiements ){
        this.paiements  = paiements ;
        }


}
