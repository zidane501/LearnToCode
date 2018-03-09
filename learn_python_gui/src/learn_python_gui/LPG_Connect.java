/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learn_python_gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import static java.lang.System.in;
import java.net.Socket;

/**
 *
 * @author duck
 */
public class LPG_Connect {
    PrintWriter writer;
    InputStreamReader ISR;
    BufferedReader in;

    

    public String[][] connect(String code, String problem) throws Exception, IOException{
        
        String str = "";
        String message = "";
        String splitter = "4nXQ6cBTFb33";

        String local_machine_add = "127.0.0.1";
        String host = "10.54.54.254";
        Socket sock = new Socket(local_machine_add, 6542);
        writer = new PrintWriter(sock.getOutputStream(), true);

        writer.println(problem + splitter + code);
        ISR = new InputStreamReader(sock.getInputStream());
        in = new BufferedReader(ISR); 

        System.out.println("Client: Networking established");

        StringBuilder s = new StringBuilder();
        while((str = in.readLine()) != null){
                s.append(str+"\n");   
        }
        message = s.toString();
        
        String[] passed = new String[]{message.split(splitter, 0)[0]};
        String result = message.split(splitter)[1];
        String[][] finalMessage = new String[][] {passed, result.split("!!!",0)};
        
        return finalMessage;
    }
}
