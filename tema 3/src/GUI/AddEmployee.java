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

public class AddEmployee extends JFrame{

    public AddEmployee(Dimension dim){
        setSize(750, 700);
        setResizable(false);
        setTitle("Add employee");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);


        JLabel message = new JLabel();
        message.setForeground(Color.RED);
        message.setBounds(350,50,200,40);
        add(message);

        JLabel firstName = new JLabel("Introduceti prenumele angajatului: ");
        firstName.setBounds(40,100,300,30);
        firstName.setFont(new Font(firstName.getName(),Font.BOLD,16));
        JTextField firstNameInput = new JTextField();
        firstNameInput.setBounds(350,100,250,30);

        JLabel lastName = new JLabel("Introduceti numele angajatului: ");
        lastName.setBounds(40,150,300,30);
        lastName.setFont(new Font(lastName.getName(),Font.BOLD,16));
        JTextField lastNameInput = new JTextField();
        lastNameInput.setBounds(350,150,250,30);

        JLabel ssn = new JLabel("Introduceti CNP-ul angajatului: ");
        ssn.setBounds(40,200,300,30);
        ssn.setFont(new Font(ssn.getName(),Font.BOLD,16));
        JTextField ssnInput = new JTextField();
        ssnInput.setBounds(350,200,250,30);

        JLabel salary = new JLabel("Introduceti salaraiul angajatului: ");
        salary.setBounds(40,250,300,30);
        salary.setFont(new Font(salary.getName(),Font.BOLD,16));
        JTextField salaryInput = new JTextField();
        salaryInput.setBounds(350,250,250,30);

        JLabel departmentName = new JLabel("Introduceti numele departamentului: ");
        departmentName.setBounds(40,300,300,30);
        departmentName.setFont(new Font(departmentName.getName(),Font.BOLD,16));
        JTextField departmentInput = new JTextField();
        departmentInput.setBounds(350,300,250,30);

        JLabel positionName = new JLabel("Introduceti pozitia angajatului: ");
        positionName.setBounds(40,350,300,30);
        positionName.setFont(new Font(positionName.getName(),Font.BOLD,16));
        JTextField positionInput = new JTextField();
        positionInput.setBounds(350,350,250,30);

        JLabel hireDate = new JLabel("Introduceti data angajarii: ");
        hireDate.setBounds(40,400,300,30);
        hireDate.setFont(new Font(hireDate.getName(),Font.BOLD,16));
        JTextField hireInput = new JTextField();
        hireInput.setBounds(350,400,250,30);

        JLabel password = new JLabel("Introduceti parola angajatului: ");
        password.setBounds(40,450,300,30);
        password.setFont(new Font(password.getName(),Font.BOLD,16));
        JTextField passwordInput = new JTextField();
        passwordInput.setBounds(350,450,250,30);


        JButton addButton = new JButton("Add employee");
        addButton.setBounds(100,500,200,40);
        JButton backButton = new JButton("return");
        backButton.setBounds(650,620,80,30);

        add(firstName);
        add(firstNameInput);
        add(lastName);
        add(lastNameInput);
        add(ssn);
        add(ssnInput);
        add(salary);
        add(salaryInput);
        add(departmentName);
        add(departmentInput);
        add(positionName);
        add(positionInput);
        add(hireDate);
        add(hireInput);
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
                    String sql = "insert into employees values('" + firstNameInput.getText() + "','" + lastNameInput.getText() + "','" + ssnInput.getText() + "','" + salaryInput.getText() + "','" + departmentInput.getText() + "','" + positionInput.getText() + "','" + hireInput.getText() +"','" + passwordInput.getText() + "');";

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
                        sql = "select * from employees";
                        try {
                            Connection connection = ConnectionManager.getInstance().getConnection();
                            PreparedStatement writeStatement = connection.prepareStatement(sql);
                            result = writeStatement.executeQuery();
                        } catch (SQLException er) {
                            System.out.println("aa");
                        }

                        String[] columnNames = {"First Name", "Last Name", "SSN", "Salary", "Department", "Position", "Hire date"};
                        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {

                            @Override
                            public boolean isCellEditable(int row, int column) {
                                //all cells false
                                return false;
                            }
                        };
                        try {
                            while (result.next()) {
                                model.addRow(new Object[]{result.getString(1), result.getString(2), result.getBigDecimal(3), result.getString(4), result.getString(5), result.getString(6), result.getString(7)});
                            }
                        } catch (SQLException e1) {
                            System.out.println("aaa");
                           // message.setText("Wrong fields!");
                        }

                        JTable table = new JTable(model);
                        DisplayEmployees displayEmployees = new DisplayEmployees(table, dim);
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
