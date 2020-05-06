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
import java.util.Arrays;

public class Home extends JFrame {


    public Home(Dimension dim) {
        setSize(750, 700);
        setVisible(true);
        setTitle("Home");
        setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel displayEmployees = new JLabel("To show all employees");
        displayEmployees.setBounds(50,100,200,50);
        displayEmployees.setFont(new Font(displayEmployees.getName(),Font.BOLD,16));
        add(displayEmployees);

        JLabel displayManagers = new JLabel("To show all managers");
        displayManagers.setBounds(50,200,200,50);
        displayManagers.setFont(new Font(displayManagers.getName(),Font.BOLD,16));
        add(displayManagers);

        JLabel displayDepartments = new JLabel("To show all departments");
        displayDepartments.setBounds(50,300,200,50);
        displayDepartments.setFont(new Font(displayDepartments.getName(),Font.BOLD,16));
        add(displayDepartments);

        JLabel displayShareholders = new JLabel("To show all shareholders");
        displayShareholders.setBounds(50,400,200,50);
        displayShareholders.setFont(new Font(displayShareholders.getName(),Font.BOLD,16));
        add(displayShareholders);

        JButton displayEmployeesButton = new JButton("CLICK HERE");
        displayEmployeesButton.setBounds(300,105,150,40);
        add(displayEmployeesButton);
        displayEmployeesButton.addActionListener(new ActionListener() {
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

            @Override
            public void actionPerformed(ActionEvent e) {

                String sql = "select * from employees";
                ResultSet result = null;

                try {
                    Connection connection = ConnectionManager.getInstance().getConnection();
                    PreparedStatement writeStatement = connection.prepareStatement(sql);
                    result = writeStatement.executeQuery();
                } catch (SQLException er) {
                    er.printStackTrace();
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
                    e1.printStackTrace();
                }

                JTable table = new JTable(model);
                DisplayEmployees displayEmployees = new DisplayEmployees(table, dim);
                setVisible(false);

            }

        });


        JButton displayManagersButton = new JButton("CLICK HERE");
        displayManagersButton.setBounds(300,205,150,40);
        add(displayManagersButton);
        displayManagersButton.addActionListener(new ActionListener() {
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

            @Override
            public void actionPerformed(ActionEvent e) {

                String sql = "select * from employees where Position='Manager'";
                ResultSet result = null;

                try {
                    Connection connection = ConnectionManager.getInstance().getConnection();
                    PreparedStatement writeStatement = connection.prepareStatement(sql);
                    result = writeStatement.executeQuery();
                } catch (SQLException er) {
                    er.printStackTrace();
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
                    e1.printStackTrace();
                }

                JTable table = new JTable(model);
                DisplayManagers displayManagers1 = new DisplayManagers(table, dim);
                setVisible(false);
            }
        });

        JButton displayDepartmentsButton = new JButton("CLICK HERE");
        displayDepartmentsButton.setBounds(300,305,150,40);
        add(displayDepartmentsButton);
        displayDepartmentsButton.addActionListener(new ActionListener() {
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

            @Override
            public void actionPerformed(ActionEvent e) {

                String sql = "select * from departments";
                ResultSet result = null;

                try {
                    Connection connection = ConnectionManager.getInstance().getConnection();
                    PreparedStatement writeStatement = connection.prepareStatement(sql);
                    result = writeStatement.executeQuery();
                } catch (SQLException er) {
                    er.printStackTrace();
                }

                String[] columnNames = {"Department Name", "Number of employees", "Manager name"};
                DefaultTableModel model = new DefaultTableModel(columnNames, 0) {

                    @Override
                    public boolean isCellEditable(int row, int column) {
                        //all cells false
                        return false;
                    }
                };
                try {
                    while (result.next()) {
                        model.addRow(new Object[]{result.getString(1), result.getInt(2), result.getString(3)});
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

                JTable table = new JTable(model);
                DisplayDepartments displayDepartments1 = new DisplayDepartments(table, dim);
                setVisible(false);
            }
        });

        JButton displayShareholdersButton = new JButton("CLICK HERE");
        displayShareholdersButton.setBounds(300,405,150,40);
        add(displayShareholdersButton);

        displayShareholdersButton.addActionListener(new ActionListener() {
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

            @Override
            public void actionPerformed(ActionEvent e) {

                String sql = "select * from shareholders";
                ResultSet result = null;

                try {
                    Connection connection = ConnectionManager.getInstance().getConnection();
                    PreparedStatement writeStatement = connection.prepareStatement(sql);
                    result = writeStatement.executeQuery();
                } catch (SQLException er) {
                    er.printStackTrace();
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
                        model.addRow(new Object[]{result.getString(1), result.getString(2), result.getBigDecimal(3), result.getBigDecimal(4)});
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

                JTable table = new JTable(model);
                DisplayShareHolders displayShareHolders1 = new DisplayShareHolders(table, dim);
                setVisible(false);
            }
        });

    }
}


