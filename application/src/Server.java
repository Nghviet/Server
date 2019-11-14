import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Server {
    private static Socket socket = null;
    private static ServerSocket server;
    private static int port = 3000;
    private static DataInputStream in = null;
    private static DataOutputStream out = null;

    private static class Score implements Comparable<Score>{
        private String name;
        private int score;
        Score(String name,int score) {
            this.name = name;
            this.score = score;
        }

        public String toString() {
            return name+" "+score;
        }

        @Override
        public int compareTo(Score that) {
            if(this.score > that.score) return 1;
            if(this.score < that.score) return -1;
            return 0;
        }

    }

    private static Score[] storage = new Score[10];

    public static void main(String[] args) throws IOException {
        server = new ServerSocket(port);

        File storeF = new File("application/src/storage").getAbsoluteFile();
        Scanner scanner = new Scanner(storeF);

        for(int i=0;i<10;i++) {
            String name = scanner.next();
            int score = scanner.nextInt();
            storage[i] = new Score(name,score);
        }

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
                        System.out.println(line);
                        String[] res = value();
                        for(int i=0;i<10;i++) out.writeUTF(res[i]);
                        out.flush();
                        break;
                    }

                    if(line.equals("add")) {

                    }
                    System.out.println(line);
                } catch (IOException io) {
                    System.out.println(io);
                }
            }

            System.out.println("terminated");
        }
    }

    private static String[] value() {
        String[] ret = new String[10];
        for(int i=0;i<10;i++) ret[i] = storage[i].toString();
        return ret;
    }

    private void add(String name,int score) {
        Score t = new Score(name,score);
        if(storage[0].compareTo(t) < 0) {
            storage[0] = t;
            Arrays.sort(storage);
        }
    }

}
