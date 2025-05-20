package examples;

import java.net.*;

class UdpServer {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(54321);
        System.out.println("UDP Server pornit...");

        byte[] buffer = new byte[1024];

        while (true) {
            DatagramPacket request = new DatagramPacket(buffer, buffer.length);
            socket.receive(request); // așteaptă pachet de la client

            String mesaj = new String(request.getData(), 0, request.getLength());
            System.out.println("Primit: " + mesaj);

            DatagramPacket response = new DatagramPacket(
                    request.getData(), request.getLength(), request.getAddress(), request.getPort()
            );
            socket.send(response);
        }
    }
}

class UdpClient {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress serverAddress = InetAddress.getByName("localhost");

        String mesaj = "Salut UDP!";
        byte[] buf = mesaj.getBytes();

        DatagramPacket request = new DatagramPacket(buf, buf.length, serverAddress, 54321);
        socket.send(request);

        byte[] responseBuf = new byte[1024];
        DatagramPacket response = new DatagramPacket(responseBuf, responseBuf.length);
        socket.receive(response);

        String reply = new String(response.getData(), 0, response.getLength());
        System.out.println("Răspuns de la server: " + reply);

        socket.close();
    }
}
