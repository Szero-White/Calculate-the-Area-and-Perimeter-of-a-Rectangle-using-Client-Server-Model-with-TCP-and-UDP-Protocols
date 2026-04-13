package antoanmang;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTCP extends JFrame {

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
                    ServerTCP frame = new ServerTCP();
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
    public ServerTCP() {
        setTitle("Server");
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
                try (ServerSocket serverSocket = new ServerSocket(12345)) {
                    textArea.append("Server started...\n");
                    while (true) {
                        Socket socket = serverSocket.accept();
                        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

                        String widthStr = input.readLine();
                        String heightStr = input.readLine();
                        double width = Double.parseDouble(widthStr);
                        double height = Double.parseDouble(heightStr);
                        double area = width * height;
                        double perimeter = 2 * (width + height);

                        output.println(area);
                        output.println(perimeter);
                        textArea.append("Calculated area: " + area + ", perimeter: " + perimeter + "\n");

                        input.close();
                        output.close();
                        socket.close();
                    }
                } catch (Exception e) {
                    textArea.append("Error: " + e.getMessage() + "\n");
                }
            }
        }).start();
    }
}
