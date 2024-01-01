package ClientSide;

import java.io.Serializable;

public class Message implements Serializable {
static  private final long serialVersionUID= 6L;
    private int id ;
    private String titre ;
    private String message;
    private int clientID;

    public Message( String titre, String message, int clientID) {
        this.titre = titre;
        this.message = message;
        this.clientID = clientID;
    }



    
    public Message(int id, String messageText) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public String toString(){
        return " TITLE :  " +titre+
                "\tTEXT : " +message+ "  \t\n";
    }
}
