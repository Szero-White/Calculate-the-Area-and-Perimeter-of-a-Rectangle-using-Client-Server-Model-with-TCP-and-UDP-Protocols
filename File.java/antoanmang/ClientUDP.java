package antoanmang;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClientUDP extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField widthField;
    private JTextField heightField;
    private JLabel resultAreaLabel;
    private JLabel resultPerimeterLabel;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ClientUDP frame = new ClientUDP();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public ClientUDP() {
        setTitle("UDP Client");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblWidth = new JLabel("Width:");
        lblWidth.setBounds(50, 50, 80, 25);
        contentPane.add(lblWidth);

        widthField = new JTextField();
        widthField.setBounds(130, 50, 200, 25);
        contentPane.add(widthField);
        widthField.setColumns(10);

        JLabel lblHeight = new JLabel("Height:");
        lblHeight.setBounds(50, 100, 80, 25);
        contentPane.add(lblHeight);

        heightField = new JTextField();
        heightField.setBounds(130, 100, 200, 25);
        contentPane.add(heightField);
        heightField.setColumns(10);

        JButton calculateButton = new JButton("Calculate Area & Perimeter");
        calculateButton.setBounds(130, 150, 200, 25);
        contentPane.add(calculateButton);

        resultAreaLabel = new JLabel("Area: ");
        resultAreaLabel.setBounds(130, 200, 200, 25);
        contentPane.add(resultAreaLabel);

        resultPerimeterLabel = new JLabel("Perimeter: ");
        resultPerimeterLabel.setBounds(130, 230, 200, 25);
        contentPane.add(resultPerimeterLabel);

        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String width = widthField.getText();
                    String height = heightField.getText();

                    try (DatagramSocket socket = new DatagramSocket()) {
                        InetAddress address = InetAddress.getByName("localhost");
                        String message = width + "," + height;
                        byte[] buffer = message.getBytes();
                        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 12345);
                        socket.send(packet);

                        byte[] responseBuffer = new byte[1024];
                        DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length);
                        socket.receive(responsePacket);

                        String response = new String(responsePacket.getData(), 0, responsePacket.getLength());
                        String[] results = response.split(",");
                        String area = results[0];
                        String perimeter = results[1];
                        resultAreaLabel.setText("Area: " + area);
                        resultPerimeterLabel.setText("Perimeter: " + perimeter);
                    }
                } catch (Exception ex) {
                    resultAreaLabel.setText("Error: " + ex.getMessage());
                }
            }
        });
    }
}
