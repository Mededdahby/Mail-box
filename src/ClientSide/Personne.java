package ClientSide;

import java.io.Serializable;

public class Personne implements Serializable {
    static  private final long serialVersionUID= 6L;
    private int id;
    private String nom ;
    private String prenom;
    private String password;
    public Personne(String nom, String prenom, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
    }
    public Personne( int id ,String nom, String prenom, String password) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString(){
        return"" +id+
                "  " +nom+
                "  "+prenom+
                "  "+password+"";
    }
}
