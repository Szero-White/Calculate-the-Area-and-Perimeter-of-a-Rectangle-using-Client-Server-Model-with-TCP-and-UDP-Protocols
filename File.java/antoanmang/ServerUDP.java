package antoanmang;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServerUDP extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextArea textArea;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ServerUDP frame = new ServerUDP();
                    frame.setVisible(true);
                    frame.startServer();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public ServerUDP() {
        setTitle("UDP Server");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 11, 414, 239);
        contentPane.add(scrollPane);

        textArea = new JTextArea();
        scrollPane.setViewportView(textArea);
    }

    /**
     * Start the server.
     */
    public void startServer() {
        new Thread(new Runnable() {
            public void run() {
                try (DatagramSocket socket = new DatagramSocket(12345)) {
                    textArea.append("Server started...\n");
                    while (true) {
                        byte[] buffer = new byte[1024];
                        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                        socket.receive(packet);
                        
                        String received = new String(packet.getData(), 0, packet.getLength());
                        String[] parts = received.split(",");
                        double width = Double.parseDouble(parts[0]);
                        double height = Double.parseDouble(parts[1]);
                        double area = width * height;
                        double perimeter = 2 * (width + height);

                        String response = area + "," + perimeter;
                        byte[] responseData = response.getBytes();
                        DatagramPacket responsePacket = new DatagramPacket(responseData, responseData.length, packet.getAddress(), packet.getPort());
                        socket.send(responsePacket);

                        textArea.append("Received width: " + width + ", height: " + height + "\n");
                        textArea.append("Calculated area: " + area + ", perimeter: " + perimeter + "\n");
                    }
                } catch (Exception e) {
                    textArea.append("Error: " + e.getMessage() + "\n");
                }
            }
        }).start();
    }
}
