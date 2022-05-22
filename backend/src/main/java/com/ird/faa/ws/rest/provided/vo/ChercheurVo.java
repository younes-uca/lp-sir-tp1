package  com.ird.faa.ws.rest.provided.vo;

    import java.util.Date;
    import javax.persistence.Temporal;
    import javax.persistence.TemporalType;
    import com.fasterxml.jackson.annotation.JsonFormat;

public class ChercheurVo {

    private String id ;
    private String numeroMatricule ;
    private String emailPrincipale ;
    private String resume ;
    private String role ;
    private Boolean credentialsNonExpired ;
    private Boolean enabled ;
    private Boolean accountNonExpired ;
    private Boolean accountNonLocked ;
    private Boolean passwordChanged ;
    private String createdAt ;
    private String updatedAt ;
    private String username ;
    private String password ;
    private String prenom ;
    private String nom ;


            private String createdAtMax ;
            private String createdAtMin ;
            private String updatedAtMax ;
            private String updatedAtMin ;



    public ChercheurVo(){
    super();
    }

        public String getId(){
        return this.id;
        }

        public void setId(String id){
        this.id = id;
        }
        public String getNumeroMatricule(){
        return this.numeroMatricule;
        }

        public void setNumeroMatricule(String numeroMatricule){
        this.numeroMatricule = numeroMatricule;
        }
        public String getEmailPrincipale(){
        return this.emailPrincipale;
        }

        public void setEmailPrincipale(String emailPrincipale){
        this.emailPrincipale = emailPrincipale;
        }
        public String getResume(){
        return this.resume;
        }

        public void setResume(String resume){
        this.resume = resume;
        }
        public String getRole(){
        return this.role;
        }

        public void setRole(String role){
        this.role = role;
        }
        public Boolean getCredentialsNonExpired(){
        return this.credentialsNonExpired;
        }

        public void setCredentialsNonExpired(Boolean credentialsNonExpired){
        this.credentialsNonExpired = credentialsNonExpired;
        }
        public Boolean getEnabled(){
        return this.enabled;
        }

        public void setEnabled(Boolean enabled){
        this.enabled = enabled;
        }
        public Boolean getAccountNonExpired(){
        return this.accountNonExpired;
        }

        public void setAccountNonExpired(Boolean accountNonExpired){
        this.accountNonExpired = accountNonExpired;
        }
        public Boolean getAccountNonLocked(){
        return this.accountNonLocked;
        }

        public void setAccountNonLocked(Boolean accountNonLocked){
        this.accountNonLocked = accountNonLocked;
        }
        public Boolean getPasswordChanged(){
        return this.passwordChanged;
        }

        public void setPasswordChanged(Boolean passwordChanged){
        this.passwordChanged = passwordChanged;
        }
        public String getCreatedAt(){
        return this.createdAt;
        }

        public void setCreatedAt(String createdAt){
        this.createdAt = createdAt;
        }
        public String getUpdatedAt(){
        return this.updatedAt;
        }

        public void setUpdatedAt(String updatedAt){
        this.updatedAt = updatedAt;
        }
        public String getUsername(){
        return this.username;
        }

        public void setUsername(String username){
        this.username = username;
        }
        public String getPassword(){
        return this.password;
        }

        public void setPassword(String password){
        this.password = password;
        }
        public String getPrenom(){
        return this.prenom;
        }

        public void setPrenom(String prenom){
        this.prenom = prenom;
        }
        public String getNom(){
        return this.nom;
        }

        public void setNom(String nom){
        this.nom = nom;
        }


            public String getCreatedAtMax(){
            return this.createdAtMax;
            }

            public String getCreatedAtMin(){
            return this.createdAtMin;
            }

            public void setCreatedAtMax(String createdAtMax){
            this.createdAtMax = createdAtMax;
            }

            public void setCreatedAtMin(String createdAtMin){
            this.createdAtMin = createdAtMin;
            }

            public String getUpdatedAtMax(){
            return this.updatedAtMax;
            }

            public String getUpdatedAtMin(){
            return this.updatedAtMin;
            }

            public void setUpdatedAtMax(String updatedAtMax){
            this.updatedAtMax = updatedAtMax;
            }

            public void setUpdatedAtMin(String updatedAtMin){
            this.updatedAtMin = updatedAtMin;
            }




            }
