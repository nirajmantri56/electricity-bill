import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ElectricityBillCalculatorGUI implements ActionListener {
    
    private JFrame frame;
    private JPanel panel;
    private JLabel unitsLabel, resultLabel;
    private JTextField unitsTextField;
    private JButton calculateButton;
    
    public ElectricityBillCalculatorGUI() {
        // Create the GUI components
        unitsLabel = new JLabel("Enter the number of units consumed:");
        unitsTextField = new JTextField(10);
        resultLabel = new JLabel();
        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(this);
        
        // Set font and color of components
        unitsLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        unitsLabel.setForeground(Color.BLACK);
        unitsTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        resultLabel.setForeground(Color.BLUE);
        calculateButton.setFont(new Font("Arial", Font.BOLD, 16));
        calculateButton.setForeground(Color.WHITE);
        calculateButton.setBackground(Color.BLUE);
        
        // Create the panel and add the components to it
        panel = new JPanel();
        panel.add(unitsLabel);
        panel.add(unitsTextField);
        panel.add(calculateButton);
        panel.add(resultLabel);
        
        // Create the frame and add the panel to it
        frame = new JFrame("Electricity Bill Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        new ElectricityBillCalculatorGUI();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == calculateButton) {
            String unitsText = unitsTextField.getText();
            if(unitsText.equals("")) {
                resultLabel.setText("Please enter the number of units.");
            }
            else {
                try {
                    int unitsConsumed = Integer.parseInt(unitsText);
                    double billAmount = calculateBillAmount(unitsConsumed);
                    NumberFormat indianCurrencyFormat = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));
                    String formattedBillAmount = indianCurrencyFormat.format(billAmount);
                    frame.setVisible(false);
                    JOptionPane.showMessageDialog(null, "Your bill amount is: " + formattedBillAmount);
                    

                } catch(NumberFormatException ex) {
                    resultLabel.setText("Invalid input. Please enter a valid integer.");
                }
            }
        }
    }
    
    private static double calculateBillAmount(int unitsConsumed) {
        
        double billAmount = 0;
        
        if(unitsConsumed <= 100) {
            billAmount = unitsConsumed * 0.10;
        }
        else if(unitsConsumed <= 200) {
            billAmount = 10 + (unitsConsumed - 100) * 0.15;
        }
        else if(unitsConsumed <= 300) {
            billAmount = 25 + (unitsConsumed - 200) * 0.20;
        }
        else {
            billAmount = 45 + (unitsConsumed - 300) * 0.25;
        }
        
        return billAmount;
    }
}

