package antoanmang;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class mains {
    public static void main(String[] args) {
        // Create frame
        JFrame frame = new JFrame("Chat Application");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create panel and use GridBagLayout
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0};
        JPanel panel = new JPanel(gbl_panel);
        
        // Add panel to frame
        frame.getContentPane().add(panel);
        
        // Create buttons for TCP and UDP
        JButton btnTCP = new JButton("TCP");
        
        // Set button actions
        btnTCP.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open TCP client and server options
                JFrame tcpFrame = new JFrame("TCP Options");
                tcpFrame.setSize(300, 200);
                tcpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                JPanel tcpPanel = new JPanel(new GridBagLayout());
                tcpFrame.getContentPane().add(tcpPanel);
                
                JButton clientTCPButton = new JButton("Client TCP");
                clientTCPButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ClientTCP.main(new String[]{});
                    }
                });
                GridBagConstraints gbc1 = new GridBagConstraints();
                gbc1.insets = new Insets(5, 5, 5, 5);
                gbc1.gridx = 0;
                gbc1.gridy = 0;
                tcpPanel.add(clientTCPButton, gbc1);
                
                JButton serverTCPButton = new JButton("Server TCP");
                serverTCPButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ServerTCP.main(new String[]{});
                    }
                });
                GridBagConstraints gbc2 = new GridBagConstraints();
                gbc2.insets = new Insets(5, 5, 5, 5);
                gbc2.gridx = 0;
                gbc2.gridy = 1;
                tcpPanel.add(serverTCPButton, gbc2);
                
                tcpFrame.setVisible(true);
            }
        });
        
        JButton btnNewButton = new JButton("Main Menu");
        GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
        gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
        gbc_btnNewButton.gridx = 1;
        gbc_btnNewButton.gridy = 0;
        panel.add(btnNewButton, gbc_btnNewButton);
        
        // Set main panel layout
        GridBagConstraints gbcTCP = new GridBagConstraints();
        gbcTCP.insets = new Insets(5, 5, 5, 5);
        gbcTCP.gridx = 0;
        gbcTCP.gridy = 1;
        panel.add(btnTCP, gbcTCP);
        JButton btnUDP = new JButton("UDP");
        
        btnUDP.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open UDP client and server options
                JFrame udpFrame = new JFrame("UDP Options");
                udpFrame.setSize(300, 200);
                udpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                JPanel udpPanel = new JPanel(new GridBagLayout());
                udpFrame.getContentPane().add(udpPanel);
                
                JButton clientUDPButton = new JButton("Client UDP");
                clientUDPButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ClientUDP.main(new String[]{});
                    }
                });
                GridBagConstraints gbc1 = new GridBagConstraints();
                gbc1.insets = new Insets(5, 5, 5, 5);
                gbc1.gridx = 0;
                gbc1.gridy = 0;
                udpPanel.add(clientUDPButton, gbc1);
                
                JButton serverUDPButton = new JButton("Server UDP");
                serverUDPButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ServerUDP.main(new String[]{});
                    }
                });
                GridBagConstraints gbc2 = new GridBagConstraints();
                gbc2.insets = new Insets(5, 5, 5, 5);
                gbc2.gridx = 0;
                gbc2.gridy = 1;
                udpPanel.add(serverUDPButton, gbc2);
                
                udpFrame.setVisible(true);
            }
        });
        
        GridBagConstraints gbcUDP = new GridBagConstraints();
        gbcUDP.insets = new Insets(5, 5, 5, 0);
        gbcUDP.gridx = 2;
        gbcUDP.gridy = 1;
        panel.add(btnUDP, gbcUDP);
        
        JLabel lblNewLabel = new JLabel("52200271-DoThanhTu");
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.gridx = 1;
        gbc_lblNewLabel.gridy = 3;
        panel.add(lblNewLabel, gbc_lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("52200271-NguyenCongToan");
        GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
        gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_1.gridx = 1;
        gbc_lblNewLabel_1.gridy = 4;
        panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
        
        // Display frame
        frame.setVisible(true);
    }
}
