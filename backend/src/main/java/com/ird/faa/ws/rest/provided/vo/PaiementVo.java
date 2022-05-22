package  com.ird.faa.ws.rest.provided.vo;

    import java.math.BigDecimal;

public class PaiementVo {

    private String id ;
    private String ref ;
    private String montant ;
    private String description ;


            private String montantMax ;
            private String montantMin ;

        private CommandeVo commandeVo ;


    public PaiementVo(){
    super();
    }

        public String getId(){
        return this.id;
        }

        public void setId(String id){
        this.id = id;
        }
        public String getRef(){
        return this.ref;
        }

        public void setRef(String ref){
        this.ref = ref;
        }
        public String getMontant(){
        return this.montant;
        }

        public void setMontant(String montant){
        this.montant = montant;
        }
        public String getDescription(){
        return this.description;
        }

        public void setDescription(String description){
        this.description = description;
        }


            public String getMontantMax(){
            return this.montantMax;
            }

            public String getMontantMin(){
            return this.montantMin;
            }

            public void setMontantMax(String montantMax){
            this.montantMax = montantMax;
            }

            public void setMontantMin(String montantMin){
            this.montantMin = montantMin;
            }


        public CommandeVo getCommandeVo(){
        return this.commandeVo;
        }

        public void setCommandeVo(CommandeVo commandeVo){
        this.commandeVo = commandeVo;
        }


            }
