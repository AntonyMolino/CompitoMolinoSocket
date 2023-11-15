package com.example;

import java.io.*;
import java.net.*;

public class Client {


    Socket mioSocket = null;
    int porta = 6789; // porta server
    BufferedReader tastiera;
    String messaggio;

    DataOutputStream out; 
    DataInputStream in;


    public void Comunica()
    {
        
        try{
            double base;
            double esponente;
            do{    
                tastiera = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("[CLIENT] - Base da inviare al server: ");
                messaggio = tastiera.readLine();
                base = Double.parseDouble(messaggio);
                System.out.println("[-] Invio: " );
                out.writeBytes( messaggio + "\n");
                System.out.print("[CLIENT] - Esponente da inviare al server: ");
                messaggio = tastiera.readLine();
                esponente = Double.parseDouble(messaggio);
                System.out.println("[-] Invio: " );
                out.writeBytes( messaggio + "\n");
                System.out.println("[-] Attesa risposta");
                String ricevuta = in.readLine();
                System.out.println("[-] Riposta del server: " + ricevuta);
            }while(base != -1 && esponente != -1);   
        } catch (IOException e) {
            e.printStackTrace();
        }
    

    }

    public Socket connetti()
    {
        try {
            System.out.println("[PROCEDURE] - provo connessione server");
            mioSocket = new Socket("localhost", porta);
            System.out.println("[-] - connessione effettuata");
            System.out.println("[Server] - per uscire dalla connessione digitare -1");
            in = new DataInputStream(mioSocket.getInputStream());
            out = new DataOutputStream(mioSocket.getOutputStream());
            
        
        } catch (UnknownHostException e) {
            System.err.println("[ERR] - Host sconosciuto");
        } catch (IOException e) {
            System.err.println("[ERR] - Impossibile stabilire connessione");
        }

        return mioSocket;


    }

}
