import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;

public class AreaCalculator {
    private static JLabel resultLabel;
    private static JTextField inputField;
    private static JComboBox<String> shapeComboBox;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Area Measurement Calculator");
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // Title Label
        JLabel titleLabel = new JLabel("Area Measurement Calculator");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel shapePanel = createShapePanel();
        mainPanel.add(shapePanel, BorderLayout.WEST);
  JPanel resultPanel = createResultPanel();
        mainPanel.add(resultPanel, BorderLayout.CENTER);

        JPanel buttonPanel = createButtonPanel();
        mainPanel.add(buttonPanel, BorderLayout.EAST);

      
        JPanel helpPanel = createHelpPanel();
        mainPanel.add(helpPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        buttonPanel.setBackground(Color.LIGHT_GRAY);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        addButton(buttonPanel, "Triangle", Color.ORANGE, e -> showTriangleArea());
        addButton(buttonPanel, "Square", Color.GREEN, e -> showSquareArea());
        addButton(buttonPanel, "Trapezium", Color.YELLOW, e -> showTrapeziumArea());
        addButton(buttonPanel, "Circle", Color.BLUE, e -> showCircleArea());

        return buttonPanel;
    }

    private static void addButton(JPanel panel, String label, Color color, ActionListener listener) {
        JButton button = new JButton(label);
        button.setBackground(color);
        button.addActionListener(listener);
        panel.add(button);
    }

    private static JPanel createResultPanel() {
        JPanel resultPanel = new JPanel(new BorderLayout());
        resultPanel.setBackground(Color.WHITE);
        resultPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        resultLabel = new JLabel("Area will be shown here");
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        resultLabel.setForeground(Color.BLUE); 
        resultPanel.add(resultLabel, BorderLayout.CENTER);

        inputField = new JTextField();
        inputField.setFont(new Font("Arial", Font.PLAIN, 14));
        inputField.setHorizontalAlignment(SwingConstants.CENTER);
        inputField.setPreferredSize(new Dimension(200, 30)); 
        resultPanel.add(inputField, BorderLayout.SOUTH);

        return resultPanel;
    }

    private static JPanel createShapePanel() {
        JPanel shapePanel = new JPanel(new BorderLayout());
        shapePanel.setBackground(Color.WHITE);
        shapePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        shapeComboBox = new JComboBox<>(new String[]{"Triangle", "Square", "Trapezium", "Circle"});
        shapeComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        shapeComboBox.addActionListener(e -> updateInputField());
        shapePanel.add(shapeComboBox, BorderLayout.CENTER);

        JLabel shapeLabel = new JLabel("Select Shape:");
        shapeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        shapePanel.add(shapeLabel, BorderLayout.NORTH);

        return shapePanel;
    }

    private static JPanel createHelpPanel() {
        JPanel helpPanel = new JPanel(new BorderLayout());
        helpPanel.setBackground(Color.WHITE);
        helpPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel helpLabel = new JLabel("Press 'T' for Triangle, 'S' for Square, 'Z' for Trapezium, 'C' for Circle");
        helpLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        helpPanel.add(helpLabel, BorderLayout.CENTER);

        inputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyChar()) {
                    case 'T':
                    case 't':
                        shapeComboBox.setSelectedItem("Triangle");
                        break;
                    case 'S':
                    case 's':
                        shapeComboBox.setSelectedItem("Square");
                        break;
                    case 'Z':
                    case 'z':
                        shapeComboBox.setSelectedItem("Trapezium");
                        break;
                    case 'C':
                    case 'c':
                        shapeComboBox.setSelectedItem("Circle");
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        return helpPanel;
    }

    private static void updateInputField() {
        String selectedShape = (String) shapeComboBox.getSelectedItem();
        switch (selectedShape) {
            case "Triangle":
                inputField.setText(getNumericInput("Enter the base and height of the triangle (comma separated):"));
                break;
            case "Square":
                inputField.setText(getNumericInput("Enter the side length of the square:"));
                break;
            case "Trapezium":
                inputField.setText(getNumericInput("Enter the lengths of the bases and height of the trapezium (comma separated):"));
                break;
            case "Circle":
                inputField.setText(getNumericInput("Enter the radius of the circle:"));
                break;
        }
    }

    private static void showTriangleArea() {
        String input = inputField.getText();
        String[] values = input.split(",");
        if (values.length == 2) {
            try {
                double base = Double.parseDouble(values[0]);
                double height = Double.parseDouble(values[1]);

                double area = 0.5 * base * height;
                displayResult("Area of the triangle: " + formatResult(area));
            } catch (NumberFormatException ex) {
                showErrorMessage("Invalid input. Please enter valid numeric values.");
            }
        } else {
            showErrorMessage("Invalid input. Please enter base and height separated by a comma.");
        }
    }

    private static void showSquareArea() {
        String input = inputField.getText();
        try {
            double side = Double.parseDouble(input);

            double area = side * side;
            displayResult("Area of the square: " + formatResult(area));
        } catch (NumberFormatException ex) {
            showErrorMessage("Invalid input. Please enter a valid numeric value for side length.");
        }
    }

    private static void showTrapeziumArea() {
        String input = inputField.getText();
        String[] values = input.split(",");
        if (values.length == 3) {
            try {
                double base1 = Double.parseDouble(values[0]);
                double base2 = Double.parseDouble(values[1]);
                double height = Double.parseDouble(values[2]);

                double area = 0.5 * (base1 + base2) * height;
                displayResult("Area of the trapezium: " + formatResult(area));
            } catch (NumberFormatException ex) {
                showErrorMessage("Invalid input. Please enter valid numeric values.");
            }
        } else {
            showErrorMessage("Invalid input. Please enter lengths of the bases and height separated by commas.");
        }
    }

    private static void showCircleArea() {
        String input = inputField.getText();
        try {
            double radius = Double.parseDouble(input);

            double area = Math.PI * radius * radius;
            displayResult("Area of the circle: " + formatResult(area));
        } catch (NumberFormatException ex) {
            showErrorMessage("Invalid input. Please enter only value for radius.");
        }
    }

    private static String getNumericInput(String message) {
        return JOptionPane.showInputDialog(message);
    }

    private static String formatResult(double result) {
        DecimalFormat df = new DecimalFormat("#.###");
        return df.format(result);
    }

    private static void displayResult(String message) {
        resultLabel.setText(message);
    }

    private static void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
