package examples;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Scanner;

class NioEchoServer {
    public static void main(String[] args) {
        int port = 12345;

        try {
            Selector selector = Selector.open();
            ServerSocketChannel serverChannel = ServerSocketChannel.open();
            serverChannel.bind(new InetSocketAddress(port));
            serverChannel.configureBlocking(false);
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println("Server NIO pornit pe portul " + port);

            while (true) {
                selector.select(); // Blochează doar până apare activitate
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();

                    if (key.isAcceptable()) {
                        SocketChannel clientChannel = ((ServerSocketChannel) key.channel()).accept();
                        clientChannel.configureBlocking(false);
                        clientChannel.register(selector, SelectionKey.OP_READ);
                        System.out.println("Client conectat: " + clientChannel.getRemoteAddress());

                    } else if (key.isReadable()) {
                        SocketChannel clientChannel = (SocketChannel) key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);

                        int bytesRead;
                        try {
                            bytesRead = clientChannel.read(buffer);
                        } catch (IOException e) {
                            System.out.println("Eroare citire. Închidere client.");
                            key.cancel();
                            clientChannel.close();
                            continue;
                        }

                        if (bytesRead == -1) {
                            System.out.println("Client deconectat.");
                            key.cancel();
                            clientChannel.close();
                            continue;
                        }

                        buffer.flip();
                        byte[] data = new byte[buffer.remaining()];
                        buffer.get(data);
                        String message = new String(data);

                        System.out.println("Primit: " + message.trim());

                        String response = "Echo: " + message;
                        ByteBuffer outBuffer = ByteBuffer.wrap(response.getBytes());
                        clientChannel.write(outBuffer);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class NioEchoClient {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 12345;

        try {
            // 1. Deschidem canalul și selectorul
            SocketChannel channel = SocketChannel.open();
            channel.configureBlocking(false);
            channel.connect(new InetSocketAddress(host, port));

            Selector selector = Selector.open();
            channel.register(selector, SelectionKey.OP_CONNECT | SelectionKey.OP_READ);

            Scanner scanner = new Scanner(System.in);
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            System.out.println("Se încearcă conectarea la server...");

            while (true) {
                selector.selectNow(); // non-blocking select

                Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
                while (iter.hasNext()) {
                    SelectionKey key = iter.next();
                    iter.remove();

                    if (key.isConnectable()) {
                        SocketChannel sc = (SocketChannel) key.channel();
                        if (sc.isConnectionPending()) {
                            sc.finishConnect(); // finalizează conexiunea
                        }
                        System.out.println("Conectat la server NIO.");
                    }

                    if (key.isReadable()) {
                        SocketChannel sc = (SocketChannel) key.channel();
                        buffer.clear();
                        int bytesRead = sc.read(buffer);
                        if (bytesRead > 0) {
                            buffer.flip();
                            byte[] data = new byte[buffer.remaining()];
                            buffer.get(data);
                            System.out.println("Server: " + new String(data).trim());
                        }
                    }
                }

                // Citire non-blocantă din consolă
                if (System.in.available() > 0) {
                    String line = scanner.nextLine();
                    if (line.equalsIgnoreCase("exit")) {
                        System.out.println("Închidem conexiunea.");
                        channel.close();
                        break;
                    }
                    channel.write(ByteBuffer.wrap(line.getBytes()));
                }

                Thread.sleep(100); // mică pauză de CPU
            }

        } catch (IOException | InterruptedException e) {
            System.err.println("Eroare client: " + e.getMessage());
        }
    }
}

class ExempluNIO {
    public static void main(String[] args) {
        System.out.println("Această clasă pornește doar clientul. Pornește serverul separat din NioEchoServer.main().");
        NioEchoClient.main(args);
    }
}

