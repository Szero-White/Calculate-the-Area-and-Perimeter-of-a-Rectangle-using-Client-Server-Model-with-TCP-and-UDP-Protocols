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
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientTCP extends JFrame {

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
                    ClientTCP frame = new ClientTCP();
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
    public ClientTCP() {
        setTitle("Client");
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

                    try (Socket socket = new Socket("localhost", 12345);
                         PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                         BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                        
                        out.println(width);
                        out.println(height);
                        String area = in.readLine();
                        String perimeter = in.readLine();
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
