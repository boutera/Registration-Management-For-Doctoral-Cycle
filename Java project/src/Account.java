
public class Account {

    //full name
    private String name;
    //email
    private String email;

    //Encrypted Password
    private String encryptedpass;
    //la carte d'identitité nationale
    private String cin;
    //le laboratoire choisi
    private String domain;
    //la thématique choisie
    private String sujet;
    //la note du master
    private String notemaster;
    //la note du license
    private String notelicense;

    public Account() {

    }

    public Account(String name, String email, String pass,String cin, String dom,String sjt,String notelicense,String notemaster) {
        this.email = email;
        this.name = name;
        this.cin=cin;
        this.domain=dom;
        this.sujet=sjt;
        this.encryptedpass = pass;
        this.notemaster=notemaster;
        this.notelicense=notelicense;
    }

    /**
     * @return the full name
     */
    public String getName() {
        return name;
    }

    /**
     * @param  sett name to  name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email name to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the domain
     */
    public String getDomain() {
        return this.domain ;
    }

    /**
     * @param user the domain to set
     */
    public void setDomain(String domain) {
        this.domain = domain;
    }

    /**
     * @return the password
     */
    public String getencryptedPass() {
        return encryptedpass;
    }

    /**
     * @param pass the password to set
     */
    public void setencryptedPass(String pass) {
        this.encryptedpass = pass;
    }
    /**
     * @param set sujet to
     */
    public void setSujet(String sjt) {
    	this.sujet=sjt;
    }
   
    /**
     * @return the sujet 
     */
    public String getSujet() {
    	return this.sujet;
    }
    
    /**
     * @return the sujet 
     */
    public void setCin(String cin)
    {
    	this.cin=cin;
    }
    /**
     * @return the cin 
     */
    public String getCin() {
    	return this.cin;
    }
    /**
     * @set the master note 
     */
    public void setnotemaster(String master) {
         this.notemaster=master;
    }
    /**
     * @set the license note 
     */
    public void setnotelicense(String license) {
        this.notelicense=license;
   }
    /**
     * @return the master note 
     */
    public String getnotemaster() {
    	return this.notemaster;
    }
    
    /**
     * @return the license note 
     */
    public String getnotelicense() {
    	return this.notelicense;
    }

}
