package GUI;

import newdb.ConnectionManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddShareholder extends JFrame{

    public AddShareholder(Dimension dim){
        setSize(750, 700);
        setResizable(false);
        setTitle("Add shareholder");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);


        JLabel message = new JLabel();
        message.setForeground(Color.RED);
        message.setBounds(390,50,200,40);
        add(message);

        JLabel firstName = new JLabel("Introduceti prenumele shareholderului: ");
        firstName.setBounds(40,100,320,30);
        firstName.setFont(new Font(firstName.getName(),Font.BOLD,16));
        JTextField firstNameInput = new JTextField();
        firstNameInput.setBounds(390,100,250,30);

        JLabel lastName = new JLabel("Introduceti numele shareholderului: ");
        lastName.setBounds(40,150,300,30);
        lastName.setFont(new Font(lastName.getName(),Font.BOLD,16));
        JTextField lastNameInput = new JTextField();
        lastNameInput.setBounds(390,150,250,30);

        JLabel ssn = new JLabel("Introduceti CNP-ul shareholderului: ");
        ssn.setBounds(40,200,300,30);
        ssn.setFont(new Font(ssn.getName(),Font.BOLD,16));
        JTextField ssnInput = new JTextField();
        ssnInput.setBounds(390,200,250,30);

        JLabel ownedPercentage = new JLabel("Introduceti procentul detinut de shareholder: ");
        ownedPercentage.setBounds(40,250,360,30);
        ownedPercentage.setFont(new Font(ownedPercentage.getName(),Font.BOLD,16));
        JTextField ownedPercentageInput = new JTextField();
        ownedPercentageInput.setBounds(390,250,250,30);

        JLabel password = new JLabel("Introduceti parola angajatului: ");
        password.setBounds(40,300,300,30);
        password.setFont(new Font(password.getName(),Font.BOLD,16));
        JTextField passwordInput = new JTextField();
        passwordInput.setBounds(390,300,250,30);


        JButton addButton = new JButton("Add shareholder");
        addButton.setBounds(100,500,200,40);
        JButton backButton = new JButton("return");
        backButton.setBounds(650,620,80,30);

        add(firstName);
        add(firstNameInput);
        add(lastName);
        add(lastNameInput);
        add(ssn);
        add(ssnInput);
        add(ownedPercentage);
        add(ownedPercentageInput);
        add(password);
        add(passwordInput);
        add(addButton);
        add(backButton);

        setLayout(new BorderLayout());
        setVisible(true);



        addButton.addActionListener(new ActionListener() {
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();


            @Override
            public void actionPerformed(ActionEvent e) {
                //if(firstNameInput.getText()!=null && lastNameInput.getText()!=null && ssnInput.getText()!=null && salaryInput.getText()!=null && departmentInput.getText()!=null && positionInput.getText()!=null && hireInput.getText()!=null) {
                String sql = "insert into shareholders values('" + firstNameInput.getText() + "','" + lastNameInput.getText() + "','" + ssnInput.getText() + "','" + ownedPercentageInput.getText() + "','shareholder','" + passwordInput.getText() + "');";

                int resultt = 0;
                ResultSet result = null;

                try {
                    Connection connection = ConnectionManager.getInstance().getConnection();
                    PreparedStatement writeStatement = connection.prepareStatement(sql);
                    resultt = writeStatement.executeUpdate();

                } catch (SQLException er) {
                    System.out.println("a");
                    message.setText("Wrong fields!");
                    er.printStackTrace();
                }
                if (resultt != 0) {
                    sql = "select * from shareholders";
                    try {
                        Connection connection = ConnectionManager.getInstance().getConnection();
                        PreparedStatement writeStatement = connection.prepareStatement(sql);
                        result = writeStatement.executeQuery();
                    } catch (SQLException er) {
                        System.out.println("aa");
                    }

                    String[] columnNames = {"First Name", "Last Name", "SSN","Owned percentage"};
                    DefaultTableModel model = new DefaultTableModel(columnNames, 0) {

                        @Override
                        public boolean isCellEditable(int row, int column) {
                            //all cells false
                            return false;
                        }
                    };
                    try {
                        while (result.next()) {
                            model.addRow(new Object[]{result.getString(1), result.getString(2), result.getBigDecimal(3), result.getString(4)});
                        }
                    } catch (SQLException e1) {
                        System.out.println("aaa");
                        // message.setText("Wrong fields!");
                    }

                    JTable table = new JTable(model);
                    DisplayShareHolders displayShareHolders = new DisplayShareHolders(table, dim);
                    setVisible(false);
                } else {
                    System.out.println("aaaa");
                    //if(message.getText()==null)
                    //message.setText("Fields can't be empty!");
                }
                // }
            }
        });

        backButton.addActionListener(new ActionListener() {
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

            @Override
            public void actionPerformed(ActionEvent e) {
                Home home = new Home(dim);
                home.setVisible(true);
                setVisible(false);
            }
        });
    }

}
