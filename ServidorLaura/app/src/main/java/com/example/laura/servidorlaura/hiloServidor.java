package com.example.laura.servidorlaura;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class hiloServidor extends Thread{

    int PUERTO=2510;
    ServerSocket sc;
    Socket so;
    int count = 0;
    static DataInputStream dis;

    //SERVIDOR//

    public hiloServidor()
    {

    }


    @Override
    public void run() {
        try {
            sc = new ServerSocket(PUERTO);

            while (true) {
                System.out.println("Esperando conexion");
                Socket socket = sc.accept();
                System.out.println("CONECTADOOO");
                hiloServer nuevoHilo = new hiloServer(socket);
                nuevoHilo.start();

            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
