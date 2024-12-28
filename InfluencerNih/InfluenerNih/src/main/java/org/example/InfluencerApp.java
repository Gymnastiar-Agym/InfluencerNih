package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

public class InfluencerApp {
    private InfluencerService influencerService;
    private JFrame frame;
    private JTable table;
    private DefaultTableModel model;
    private JTextField nameField, platformField, usernameField, followersField, statusField;
    private String imagePath = "";  // Store the image path

    public InfluencerApp() {
        influencerService = new InfluencerService();
        frame = new JFrame("Influencer Management");
        frame.setLayout(new BorderLayout());

        // Table setup
        model = new DefaultTableModel();
        model.addColumn("Name");
        model.addColumn("Platform");
        model.addColumn("Username");
        model.addColumn("Followers");
        model.addColumn("Status");
        model.addColumn("Image Path");
        model.addColumn("Image"); // New column for displaying images

        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  // Allow only one row to be selected
        table.setFillsViewportHeight(true);  // Ensure the table takes up full space
        table.setRowHeight(100); // Adjust row height to fit image
        table.setSelectionBackground(new Color(173, 216, 230));  // Light blue selection color

        // Set custom renderer for image column
        table.getColumn("Image").setCellRenderer(new ImageCellRenderer());

        JScrollPane scrollPane = new JScrollPane(table);

        // Fields for input
        nameField = new JTextField(15);
        platformField = new JTextField(15);
        usernameField = new JTextField(15);
        followersField = new JTextField(15);
        statusField = new JTextField(15);

        // Styled Buttons
        JButton addButton = createStyledButton("Add Influencer");
        JButton updateButton = createStyledButton("Update Influencer");
        JButton deleteButton = createStyledButton("Delete Influencer");
        JButton chooseImageButton = createStyledButton("Choose Image");
        JButton saveButton = createStyledButton("Save to Word");

        // Panel for Buttons and Input
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);  // Add space between components

        // Add labels and fields to panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        panel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Platform:"), gbc);
        gbc.gridx = 1;
        panel.add(platformField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        panel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Followers:"), gbc);
        gbc.gridx = 1;
        panel.add(followersField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("Status:"), gbc);
        gbc.gridx = 1;
        panel.add(statusField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(chooseImageButton, gbc);

        // Add buttons
        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(addButton, gbc);

        gbc.gridx = 1;
        panel.add(updateButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(deleteButton, gbc);

        gbc.gridx = 1;
        panel.add(saveButton, gbc);

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(panel, BorderLayout.SOUTH);

        // Add Influencer
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String platform = platformField.getText();
                String username = usernameField.getText();
                String followersText = followersField.getText();
                String status = statusField.getText();

                // Validate and parse followers input
                int followers = 0;
                try {
                    followers = Integer.parseInt(followersText);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid number for followers.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Create the influencer object
                Influencer influencer = new Influencer(name, platform, username, followers, status, imagePath);
                influencerService.addInfluencer(influencer);

                // Show success message
                JOptionPane.showMessageDialog(frame, "Influencer added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                updateTable();
            }
        });

        // Update Influencer
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table.getSelectedRow() != -1) {
                    Influencer influencer = influencerService.getInfluencers().get(table.getSelectedRow());
                    influencer.setStatus("Completed");  // Update status or any other field
                    influencerService.updateInfluencer(table.getSelectedRow(), influencer);

                    // Update in Word document
                    WordExporter.updateInfluencer(table.getSelectedRow(), influencer);

                    updateTable();
                }
            }
        });


        // Delete Influencer
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table.getSelectedRow() != -1) {
                    influencerService.deleteInfluencer(table.getSelectedRow());
                    updateTable();
                }
            }
        });

        // Choose Image
        chooseImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "png", "jpeg"));
                int result = fileChooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    imagePath = selectedFile.getAbsolutePath();
                }
            }
        });

        // Save to Word
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WordExporter.exportToWord(influencerService.getInfluencers());
            }
        });

        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(50, 150, 255));  // Light blue
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        button.setPreferredSize(new Dimension(150, 40));
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Add hover effect for button
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(100, 180, 255));  // Change to a lighter blue on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(50, 150, 255));  // Revert back when mouse leaves
            }
        });

        return button;
    }

    public void updateTable() {
        model.setRowCount(0);
        List<Influencer> influencers = influencerService.getInfluencers();
        for (Influencer influencer : influencers) {
            model.addRow(new Object[]{influencer.getName(), influencer.getPlatform(), influencer.getUsername(), influencer.getFollowers(), influencer.getStatus(), influencer.getImagePath(), new ImageIcon(influencer.getImagePath())});
        }
    }

    public static void main(String[] args) {
        new InfluencerApp();
    }

    // Custom renderer for displaying images
    class ImageCellRenderer extends JLabel implements TableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (value instanceof ImageIcon) {
                setIcon((ImageIcon) value);
            }
            setHorizontalAlignment(SwingConstants.CENTER);
            return this;
        }
    }
}
