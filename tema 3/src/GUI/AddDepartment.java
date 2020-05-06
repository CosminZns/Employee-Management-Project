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

public class AddDepartment extends JFrame{

    public AddDepartment(Dimension dim){

        setSize(750, 700);
        setResizable(false);
        setTitle("Add department");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);


        JLabel message = new JLabel();
        message.setForeground(Color.RED);
        message.setBounds(350,350,200,40);
        add(message);

        JLabel departmentName = new JLabel("Introduceti numele departamentului: ");
        departmentName.setBounds(40,50,300,30);
        departmentName.setFont(new Font(departmentName.getName(),Font.BOLD,16));
        JTextField departmentInput = new JTextField();
        departmentInput.setBounds(350,50,250,30);

        JLabel noeName = new JLabel("Introduceti numarul angajatior: ");
        noeName.setBounds(40,150,300,30);
        noeName.setFont(new Font(noeName.getName(),Font.BOLD,16));
        JTextField noeInput = new JTextField();
        noeInput.setBounds(350,150,250,30);

        JLabel managerName = new JLabel("Introduceti numele managerului: ");
        managerName.setBounds(40,250,300,30);
        managerName.setFont(new Font(managerName.getName(),Font.BOLD,16));
        JTextField managerInput = new JTextField();
        managerInput.setBounds(350,250,250,30);

        JButton addButton = new JButton("Add department");
        addButton.setBounds(100,350,200,40);
        JButton backButton = new JButton("return");
        backButton.setBounds(650,620,80,30);

        add(departmentName);
        add(departmentInput);
        add(noeName);
        add(noeInput);
        add(managerName);
        add(managerInput);
        add(addButton);
        add(backButton);

        setLayout(new BorderLayout());
        setVisible(true);


        addButton.addActionListener(new ActionListener() {
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();


            @Override
            public void actionPerformed(ActionEvent e) {
                String sql = "insert into departments values('"+departmentInput.getText()+"',"+noeInput.getText()+",'"+managerInput.getText()+"');";
                int resultt = 0;
                ResultSet result = null;

                try {
                    Connection connection = ConnectionManager.getInstance().getConnection();
                    PreparedStatement writeStatement = connection.prepareStatement(sql);
                    resultt = writeStatement.executeUpdate();
                } catch (SQLException er) {
                    message.setText("Wrong fields!");
                    er.printStackTrace();
                }
                if(resultt!=0) {
                    sql = "select * from departments";
                    try {
                        Connection connection = ConnectionManager.getInstance().getConnection();
                        PreparedStatement writeStatement = connection.prepareStatement(sql);
                        result = writeStatement.executeQuery();
                    } catch (SQLException er) {
                        er.printStackTrace();
                    }

                    String[] columnNames = {"Department name","Number of employees","Manager name"};
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
                    DisplayDepartments displayDepartments = new DisplayDepartments(table, dim);
                    setVisible(false);
                }
                else{
                    message.setText("Wrong fields!");
                }
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
