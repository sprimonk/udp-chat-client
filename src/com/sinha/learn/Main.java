package com.sinha.learn;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    try{
            InetAddress address = InetAddress.getLoopbackAddress();
            DatagramSocket socket = new DatagramSocket();
            Scanner scanner = new Scanner(System.in);
            String message;
            do{
                System.out.println("Enter your message to send :");
                message = scanner.nextLine();
                byte[] buffer = message.getBytes();
                DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length,address,5000);
                socket.send(datagramPacket);
                // receiving data from server! not general done, client only sends in real world app and does not wait for the
                // response back from the server
                byte[] buffer2 = new byte[50];
                datagramPacket = new DatagramPacket(buffer2, buffer.length);
                //below line is blocking IO.
                socket.receive(datagramPacket);
                System.out.println("Text received is :" + new String(buffer2,0,datagramPacket.getLength()));
            }while (!message.equals("exit"));
        }catch (SocketException e){
            System.out.println("Socket exception :" + e.getMessage());
        }catch (IOException e){
            System.out.println("IOException :" + e.getMessage());
        }
    }
}
