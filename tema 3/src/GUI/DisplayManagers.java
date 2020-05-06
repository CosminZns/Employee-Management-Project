package GUI;

import newdb.ConnectionManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DisplayManagers extends JFrame {

    public DisplayManagers(JTable table, Dimension dim) {
        setSize(750, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setTitle("Display manager");
        setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);
        setResizable(false);

        Font f = new Font("Arial", Font.BOLD, 14);
        JTableHeader header = table.getTableHeader();
        header.setFont(f);
        header.setReorderingAllowed(false);

        table.setFont(new Font("", 0, 14));
        table.setRowHeight(20);

        JPanel topPanel = new JPanel();
        JPanel btmPanel = new JPanel();

        topPanel.setLayout(new BorderLayout());
        add(topPanel, BorderLayout.CENTER);
        add(btmPanel, BorderLayout.SOUTH);

        JScrollPane scrollPane = new JScrollPane(table);
        topPanel.add(scrollPane);
        JButton addButton = new JButton("ADD MANAGER");
        addButton.setPreferredSize(new Dimension(200, 30));
        JButton delButton = new JButton("DELETE MANAGER");
        delButton.setPreferredSize(new Dimension(200, 30));
        JButton backButton = new JButton("return");
        backButton.setPreferredSize(new Dimension(100, 30));

        btmPanel.add(addButton);
        btmPanel.add(delButton);
        btmPanel.add(backButton);

        addButton.addActionListener(new ActionListener() {
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

            @Override
            public void actionPerformed(ActionEvent e) {
                AddManager addManager = new AddManager(dim);
                addManager.setVisible(true);
                setVisible(false);
            }
        });

        delButton.addActionListener(new ActionListener() {
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
                DeleteManager deleteManager = new DeleteManager(table, dim);
                setVisible(false);
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
