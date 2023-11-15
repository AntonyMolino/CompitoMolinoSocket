package com.example;

import java.io.*;
import java.net.*;

public class Server {

    //var
    ServerSocket server = null;
    Socket socketClient = null;
    int porta = 6789; // porta server
    String letto;

    DataOutputStream out; 
    DataInputStream in;

    

    public void Comunica()
    {
        for(;;){
            attendi();
        try {
                double base;
                double esponente;
                do{         
                    System.out.println();
                    System.out.println("[SERVER] - aspetto la base");
                    letto = in.readLine();
                    System.out.println("[-] - messaggio ricevuto ");
                    base = Double.parseDouble(letto);
                    System.out.println("[SERVER] - aspetto la base");
                    letto = in.readLine();
                    esponente = Double.parseDouble(letto);
                    Double risposta = Math.pow(base , esponente);
                    System.out.println("[-] - messaggio ricevuto ");
                    System.out.println("[-] - rispondo con " + risposta);
                    if(base == -1 || esponente == -1){
                        out.writeBytes("arrivederci client" + "\n");
                    }else{
                    out.writeBytes(risposta + "\n");
                    }
                }while(base != -1 && esponente != -1);
        
                    socketClient.close(); //chiusura connessione
                    System.out.println( "[!] - CONNECTION CLOSED");    
            } catch (IOException e) {

            e.printStackTrace();
            }
        }   
    }


    public Socket attendi() {
        try {
            System.out.println( "[PROCEDURE] - inizializzazione");
            server = new ServerSocket(porta);
            System.out.println("[-] Server in ascolto sulla porta: " + porta);
            socketClient = server.accept();
            System.out.println("[-] connessione effettuata");
            
            server.close();
            
            in = new DataInputStream(socketClient.getInputStream());
            out = new DataOutputStream(socketClient.getOutputStream());
            
        } catch (IOException e) {
            e.printStackTrace();
        } 

        return socketClient;


    }

    
}
