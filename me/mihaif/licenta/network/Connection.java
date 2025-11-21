package me.mihaif.licenta.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Connection {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public void connect(String host, int port) throws IOException {
        socket = new Socket(host, port);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void send(String message) {
        if (out != null) out.flush(); out.println(message);
    }

    public String readLine() throws IOException {
        if (in != null) return in.readLine();
        return null;
    }

    public void close() {
        try { if (socket != null) socket.close(); } catch (IOException ignored) {}
    }

    public boolean isConnected() {
        return socket != null && socket.isConnected() && !socket.isClosed();
    }
}
