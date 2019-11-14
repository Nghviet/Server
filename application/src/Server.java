import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static Socket socket = null;
    private static ServerSocket server;
    private static int port = 3000;
    private static DataInputStream in = null;
    private static DataOutputStream out = null;

    private class Score implements Comparable<Score>{
        private String name;
        private int score;

        Score(String name,int score) {
            this.name = name;
            this.score = score;
        }

        public String toString() {
            return null;
        }

        @Override
        public int compareTo(Score that) {
            if(this.score > that.score) return 1;
            if(this.score < that.score) return -1;
            return 0;
        }
    }

    public static void main(String[] args) throws IOException {
        server = new ServerSocket(port);
        while(true)
        {
            socket = server.accept();
            System.out.println("new connection");
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            String line = "";

            while(!line.equals("over")) {
                try {
                    line = in.readUTF();
                    if(line.equals("update")) {
                        System.out.println("sending response");
                        out.writeUTF("roger");
                        out.writeUTF("over");
                        out.flush();
                    }
                    System.out.println(line);
                } catch (IOException io) {
                    System.out.println(io);
                }
            }

            System.out.println("terminated");
        }
    }

    private String[] highValue() {
        return null;
    }
}
