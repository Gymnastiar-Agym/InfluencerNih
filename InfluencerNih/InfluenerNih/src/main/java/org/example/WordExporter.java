package org.example;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import javax.swing.JOptionPane;  // Add this import statement

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class WordExporter {

    private static final String WORD_FILE = "influencers.docx";

    // Export to Word
    public static void exportToWord(List<Influencer> influencers) {
        try {
            XWPFDocument document = new XWPFDocument();
            XWPFTable table = document.createTable();

            // Create table headers
            XWPFTableRow headerRow = table.getRow(0);
            headerRow.getCell(0).setText("Name");
            headerRow.addNewTableCell().setText("Platform");
            headerRow.addNewTableCell().setText("Username");
            headerRow.addNewTableCell().setText("Followers");
            headerRow.addNewTableCell().setText("Status");
            headerRow.addNewTableCell().setText("Image Path");

            // Add influencer data
            for (Influencer influencer : influencers) {
                XWPFTableRow row = table.createRow();
                row.getCell(0).setText(influencer.getName());
                row.getCell(1).setText(influencer.getPlatform());
                row.getCell(2).setText(influencer.getUsername());
                row.getCell(3).setText(String.valueOf(influencer.getFollowers()));
                row.getCell(4).setText(influencer.getStatus());
                row.getCell(5).setText(influencer.getImagePath());
            }

            // Save the document
            FileOutputStream out = new FileOutputStream(WORD_FILE);
            document.write(out);
            out.close();

            JOptionPane.showMessageDialog(null, "Influencers saved to Word successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error saving to Word.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Update influencer in Word document
    public static void updateInfluencer(int index, Influencer updatedInfluencer) {
        try {
            FileInputStream fileInputStream = new FileInputStream(WORD_FILE);
            XWPFDocument document = new XWPFDocument(fileInputStream);
            XWPFTable table = document.getTables().get(0);

            // Check if index is valid
            if (index >= 0 && index < table.getRows().size()) {
                XWPFTableRow row = table.getRow(index + 1); // +1 because first row is header
                row.getCell(0).setText(updatedInfluencer.getName());
                row.getCell(1).setText(updatedInfluencer.getPlatform());
                row.getCell(2).setText(updatedInfluencer.getUsername());
                row.getCell(3).setText(String.valueOf(updatedInfluencer.getFollowers()));
                row.getCell(4).setText(updatedInfluencer.getStatus());
                row.getCell(5).setText(updatedInfluencer.getImagePath());

                FileOutputStream out = new FileOutputStream(WORD_FILE);
                document.write(out);
                out.close();

                JOptionPane.showMessageDialog(null, "Influencer updated successfully in Word!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid influencer index for update.", "Error", JOptionPane.ERROR_MESSAGE);
            }

            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error updating Word document.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Delete influencer from Word document
    public static void deleteInfluencer(int index) {
        try {
            FileInputStream fileInputStream = new FileInputStream(WORD_FILE);
            XWPFDocument document = new XWPFDocument(fileInputStream);
            XWPFTable table = document.getTables().get(0);

            // Check if index is valid
            if (index >= 0 && index < table.getRows().size()) {
                table.removeRow(index + 1); // +1 because first row is header

                FileOutputStream out = new FileOutputStream(WORD_FILE);
                document.write(out);
                out.close();

                JOptionPane.showMessageDialog(null, "Influencer deleted successfully from Word!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid influencer index for delete.", "Error", JOptionPane.ERROR_MESSAGE);
            }

            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error deleting from Word document.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
