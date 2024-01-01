package ServerSide;

import ClientSide.Message;

import javax.management.Query;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageImplementation implements Gestion<Message> {

    @Override
    public Message getT(String id) {
        Message message = null;
        String Query = "SELECT * from Message where titre =?";
        try {
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(Query);
            ps.setString(1,id);
            ResultSet rs = ps.executeQuery();
            rs.next();
             message = new Message(rs.getString(2), rs.getString(3),rs.getInt(4));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return message;
    }

    @Override
    public List<Message> getAllT(int id) {
        Message message = null;
        List<Message> list = new ArrayList<>();
        String Query = "SELECT * from Message where client_ID=?";
        try {
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(Query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
            message = new Message(rs.getString(2), rs.getString(3),rs.getInt(4));
            list.add(message);}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public void addT(Message message) {
String Query ="Insert INTO Message values (?,?,?,?)";
        try {
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(Query);
            ps.setInt(1,message.getId());
            ps.setString(2,message.getTitre());
            ps.setString(3,message.getMessage());
            ps.setInt(4,message.getClientID());
          int n = ps.executeUpdate();
          if(n>0) System.out.println("the message is added" +message);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void editT(Message message, int id) {
       String Query = "update Messages set id =? , titre =? , message=? , clientID=? where id =?";
        try {
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(Query);
            ps.setInt(1,message.getId());
            ps.setString(2,message.getTitre());
            ps.setString(3,message.getMessage());
            ps.setInt(4,message.getClientID());
            ps.setInt(5, id);
            int n = ps.executeUpdate();
            if(n>0) System.out.println("the message is updated " +message);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteT(int id) {
        String Query ="Delete from messages where id =?";
        try {
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(Query);
            ps.setInt(1, id);
            int n = ps.executeUpdate();
            if(n>0) System.out.println("the message is deleted ");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
