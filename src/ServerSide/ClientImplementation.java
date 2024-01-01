package ServerSide;

import ClientSide.Personne;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientImplementation  implements Gestion<Personne> {

    @Override
    public Personne getT(String nom) {
        Personne personne = null;
        String Query = "SELECT * from Personne where nom =?";
        try {
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(Query);
            ps.setString(1,nom);
            ResultSet rs = ps.executeQuery();
            rs.next();
                personne = new Personne( rs.getString(2), rs.getString(3), rs.getString(4));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return personne;
    }

    @Override
    public List<Personne> getAllT(int id ) {
        Personne personne = null;
        List<Personne> list = new ArrayList<>();
        String Query = "SELECT * from Personne";
        try {
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(Query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            personne = new Personne(rs.getString(2), rs.getString(3),rs.getString(4));
            list.add(personne);}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }





    @Override
    public void addT(Personne Personne) {
        String Query ="Insert INTO Personne values (?,?,?,?)";
        try {
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(Query);
            ps.setInt(1,Personne.getId());
            ps.setString(2,Personne.getNom());
            ps.setString(3,Personne.getPrenom());
            ps.setString(4,Personne.getPassword());
            int n = ps.executeUpdate();
            if(n>0) System.out.println("the Personne is added" +Personne);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void editT(Personne Personne, int id) {
        String Query = "update Personne set id =? , nom =? ,prenom =? , password=? where id =?";
        try {
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(Query);
            ps.setInt(1,Personne.getId());
            ps.setString(2,Personne.getNom());
            ps.setString(3,Personne.getPrenom());
            ps.setString(4,Personne.getPassword());
            ps.setInt(5, id);
            int n = ps.executeUpdate();
            if(n>0) System.out.println("the Personne is updated " +Personne);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteT(int id) {
        String Query ="Delete from Personne where id =?";
        try {
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(Query);
            ps.setInt(1, id);
            int n = ps.executeUpdate();
            if(n>0) System.out.println("the Personne is deleted ");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Personne login(String name , String password){
        Personne personne = null;
        String Query = "SELECT * from Personne where nom =? and password=?";
        try {
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(Query);
            ps.setString(1,name);
            ps.setString(2,password);

            ResultSet rs = ps.executeQuery();
           if(rs.next()) {
               personne = new Personne(  rs.getInt(1) ,rs.getString(2), rs.getString(3), rs.getString(4));

           }else {
               personne=null;
           }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return personne;
    }
}
