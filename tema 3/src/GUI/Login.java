package GUI;

import newdb.ConnectionManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Login {

    private static JFrame loginFrame;
    private static JFrame Home;
    private static JTable table;
    private static JLabel usernameLabel, message;
    private static JTextField usernameInput;
    private static JLabel passwordLabel;
    private static JPasswordField passwordInput;
    private static JButton loginButton;
    private static JButton addButton;

    public static void main(String[] args){
        loginFrame = new JFrame("Login window");
        loginFrame.setSize(350, 400);

        usernameLabel = new JLabel("Username: ");
        usernameLabel.setFont(new Font(usernameLabel.getName(),Font.BOLD,14));
        usernameLabel.setBounds(20, 20, 100, 40);
        loginFrame.add(usernameLabel);
        usernameInput = new JTextField();
        usernameInput.setBounds(100, 20, 200, 30);
        loginFrame.add(usernameInput);

        passwordLabel = new JLabel("Password: ");
        passwordLabel.setFont(new Font(passwordLabel.getName(),Font.BOLD,14));
        passwordLabel.setBounds(20, 60, 100, 40);
        loginFrame.add(passwordLabel);
        passwordInput = new JPasswordField();
        passwordInput.setBounds(100, 60, 200, 30);
        loginFrame.add(passwordInput);

        loginButton = new JButton("login");
        loginButton.setBounds(20, 110, 280, 40);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        loginFrame.setLocation(dim.width/2-loginFrame.getSize().width/2, dim.height/2-loginFrame.getSize().height/2);

        loginFrame.setLayout(null);
        loginFrame.setVisible(true);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String sql = "select * from shareholders";
                ResultSet result = null;

                try  {
                    Connection connection = ConnectionManager.getInstance().getConnection();
                    PreparedStatement writeStatement = connection.prepareStatement(sql);
                    result = writeStatement.executeQuery();
                } catch (SQLException er) {
                    er.printStackTrace();
                }

                try {
                    while (result.next()) {
                        if(usernameInput.getText().equals(result.getString(1))& /*Arrays.equals(passwordInput.getPassword(), "pass".toCharArray())*/passwordInput.getText().equals(result.getString(6))){
                            //Object[][] data = {{"Kathy", "Smith"},{"John", "Doe"}};
                            String[] columnNames = {"First Name", "Last Name", "SSN", "Salary", "Department", "Position", "Hire date"};
                            //Object[][] data = new Object[][]{{"a","b",2,"c","d","2020-1-1"}};
                            DefaultTableModel model = new DefaultTableModel(columnNames, 0){

                                @Override
                                public boolean isCellEditable(int row, int column) {
                                    //all cells false
                                    return false;
                                }
                            };
                            try {
                                while (result.next()) {
                                    model.addRow(new Object[]{result.getString(1),result.getString(2),result.getBigDecimal(3),result.getString(4),result.getString(5),result.getString(6),result.getString(7)});
                                }
                            } catch (SQLException e1) {
                                e1.printStackTrace();
                            }

                            table = new JTable(model);
                            Font f = new Font("Arial", Font.BOLD, 14);
                            JTableHeader header = table.getTableHeader();
                            header.setFont(f);
                            header.setReorderingAllowed(false);

                            //table.setFont(new Font("",0,14));
                            //table.setRowHeight(20);

                            String username = usernameInput.getText();
                            String password = passwordInput.getText();
                            Home home = new Home(dim);
                            loginFrame.setVisible(false);

                            loginButton.setBounds(20, 150, 280, 40);
                            message.setForeground(Color.BLACK);

                        } else{
                            loginButton.setBounds(20, 150, 280, 40);
                            message.setText("Wrong username or password!");
                            message.setForeground(Color.RED);
                            message.setBounds(80,110,300,30);
                        }
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                    loginButton.setBounds(20, 150, 280, 40);
                    message.setText("Wrong username or password!");
                    message.setForeground(Color.RED);
                    message.setBounds(80,110,300,30);
                }

                /*if(usernameInput.getText().equals("irina")& Arrays.equals(passwordInput.getPassword(), "pass".toCharArray())){
                    //Object[][] data = {{"Kathy", "Smith"},{"John", "Doe"}};
                    String[] columnNames = {"First Name", "Last Name", "SSN", "Salary", "Department", "Position", "Hire date"};
                    //Object[][] data = new Object[][]{{"a","b",2,"c","d","2020-1-1"}};
                    DefaultTableModel model = new DefaultTableModel(columnNames, 0){

                        @Override
                        public boolean isCellEditable(int row, int column) {
                            //all cells false
                            return false;
                        }
                    };
                    try {
                            while (result.next()) {
                                model.addRow(new Object[]{result.getString(1),result.getString(2),result.getBigDecimal(3),result.getString(4),result.getString(5),result.getString(6),result.getString(7)});
                            }
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }

                    table = new JTable(model);
                    Font f = new Font("Arial", Font.BOLD, 14);
                    JTableHeader header = table.getTableHeader();
                    header.setFont(f);
                    header.setReorderingAllowed(false);

                    //table.setFont(new Font("",0,14));
                    //table.setRowHeight(20);

                    String username = usernameInput.getText();
                    String password = passwordInput.getText();
                    Home home = new Home(dim);
                    loginFrame.setVisible(false);

                    loginButton.setBounds(20, 150, 280, 40);
                    message.setForeground(Color.BLACK);

                } else{
                    loginButton.setBounds(20, 150, 280, 40);
                    message.setText("Wrong username or password!");
                    message.setForeground(Color.RED);
                    message.setBounds(80,110,300,30);
                }*/
            }
        });
        loginFrame.add(loginButton);

        message = new JLabel();
        message.setBounds(130, 110, 150, 40);
        loginFrame.add(message);

        //close operation
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setResizable(false);
        
        //to view the frame

        /*Home = new JFrame("Home");
        Home.setSize(350, 400);

        String[] columnNames = {"First Name", "Last Name"};
        Object[][] data = {{"Kathy", "Smith"},{"John", "Doe"}};
        tabele = new JTable(data, columnNames);
        //JTable table = new JTable(data, columnNames);
        table.setFillsViewportHeight(true);

        Home.add(table);
        Home.setVisible(true);*/


    }



}