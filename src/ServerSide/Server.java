package ServerSide;

import ClientSide.Message;
import ClientSide.Personne;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    static Personne loggedInPerson ;
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(9999);
            System.out.println("Server waiting for client on port 9999...");
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(() -> handleClient(socket)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket socket ) {
        MessageImplementation cl = new MessageImplementation();
        ClientImplementation client = new ClientImplementation();
        ClientSide.Personne p = null;
        List<Personne> lp = new ArrayList<>();
        PrintWriter pn;
        ClientSide.Message message = null;
        List<Message> ms ;
        BufferedReader bff;
        PrintWriter ptn;
        BufferedReader bf;
        ObjectOutputStream outputDataO;
        ObjectInputStream inputDataO;


        try {
                bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String requestType =  bf.readLine();

        if("3".equals(requestType)) {
            ptn = new PrintWriter(socket.getOutputStream(), true);
            ptn.println("done...");
            inputDataO = new ObjectInputStream((socket.getInputStream()));
                Personne userName=  (Personne) inputDataO.readObject();
                ClientSide.Personne pp = client.login(userName.getNom(), userName.getPassword());
            if(pp != null){
                outputDataO = new ObjectOutputStream(socket.getOutputStream());
                outputDataO.writeObject(pp);
                loggedInPerson = pp;
            }else{
                pn = new PrintWriter(socket.getOutputStream(), true);
                pn.println("false");
                }
                inputDataO.close();
                bf.close();
        }if  ("2".equals(requestType)){
                ptn = new PrintWriter(socket.getOutputStream(), true);
                ptn.println("done ... ! ");
                inputDataO = new ObjectInputStream(socket.getInputStream());
                p= (ClientSide.Personne) inputDataO.readObject();
                client.addT(p);
                pn = new PrintWriter(socket.getOutputStream(), true);
                pn.println("true");
                inputDataO.close();
                bf.close();
        }if  ("1".equals(requestType)){
                System.out.println("hello from here ");
                ms = new ArrayList<>();
                ms = cl.getAllT(loggedInPerson.getId());
                ms = cl.getAllT(loggedInPerson.getId());
                outputDataO = new ObjectOutputStream(socket.getOutputStream());
                outputDataO.writeObject(ms);
                outputDataO.flush();
                outputDataO.close();
        }if("4".equals(requestType)){
                ptn = new PrintWriter(socket.getOutputStream(), true);
                ptn.println("done ... ! ");
                inputDataO = new ObjectInputStream(socket.getInputStream());
                Message messageR = (Message) inputDataO.readObject();
                cl.addT(messageR);
                pn = new PrintWriter(socket.getOutputStream(), true);
                pn.println("true");
                inputDataO.close();
                bf.close();
            }socket.close();
            } catch (IOException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }
    }
