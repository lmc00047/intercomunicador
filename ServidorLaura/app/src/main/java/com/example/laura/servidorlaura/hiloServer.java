package com.example.laura.servidorlaura;


import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class hiloServer extends Thread{

    Socket so;
    static String pass;
    final int BUFFER_SIZE = 2048 * 10; // Size

    static DataInputStream dis;
    BufferedReader entradaDesdeCliente = null;
    static AudioTrack track =null;

    //SERVIDOR//

    public hiloServer(Socket so) {
        try {

            //entradaDesdeCliente = new BufferedReader(new InputStreamReader(so.getInputStream()));
            //DataInputStream dis = new DataInputStream(socketConexion.getInputStream());
           dis = new DataInputStream(so.getInputStream());
            track = new AudioTrack(AudioManager.STREAM_MUSIC, 8000, AudioFormat.CHANNEL_OUT_MONO, AudioFormat.ENCODING_PCM_16BIT, 8000, AudioTrack.MODE_STREAM);
            final int BUFFER_SIZE = 20; // Size
            track.play();

            //dis = new DataInputStream(so.getInputStream());

        } catch (Exception ex) {
            System.out.println("Error de socket");
        }
    }



    public void run(){

        System.out.println("HILO CREADO");
            //Recepcion de mensaje
            while(true) {
                byte[] bytes = new byte[BUFFER_SIZE];
                try {
                    dis.read(bytes);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                track.write(bytes, 0, BUFFER_SIZE);
                bytes=null;
            }


    }

}

