package  com.ird.faa.ws.rest.provided.vo;

    import java.util.List;
    import java.math.BigDecimal;

public class CommandeVo {

    private String id ;
    private String ref ;
    private String total ;
    private String totalPayer ;


            private String totalMax ;
            private String totalMin ;
            private String totalPayerMax ;
            private String totalPayerMin ;


    private List<PaiementVo> paiementsVo ;

    public CommandeVo(){
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
        public String getTotal(){
        return this.total;
        }

        public void setTotal(String total){
        this.total = total;
        }
        public String getTotalPayer(){
        return this.totalPayer;
        }

        public void setTotalPayer(String totalPayer){
        this.totalPayer = totalPayer;
        }


            public String getTotalMax(){
            return this.totalMax;
            }

            public String getTotalMin(){
            return this.totalMin;
            }

            public void setTotalMax(String totalMax){
            this.totalMax = totalMax;
            }

            public void setTotalMin(String totalMin){
            this.totalMin = totalMin;
            }

            public String getTotalPayerMax(){
            return this.totalPayerMax;
            }

            public String getTotalPayerMin(){
            return this.totalPayerMin;
            }

            public void setTotalPayerMax(String totalPayerMax){
            this.totalPayerMax = totalPayerMax;
            }

            public void setTotalPayerMin(String totalPayerMin){
            this.totalPayerMin = totalPayerMin;
            }




        public List<PaiementVo> getPaiementsVo(){
        return this.paiementsVo;
        }

        public void setPaiementsVo(List<PaiementVo> paiementsVo){
            this.paiementsVo = paiementsVo;
            }

            }
