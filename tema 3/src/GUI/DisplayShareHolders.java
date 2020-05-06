package GUI;

import newdb.ConnectionManager;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class DisplayShareHolders extends JFrame {

    public DisplayShareHolders(JTable table, Dimension dim) {
        setSize(750, 700);
        setResizable(false);
        setVisible(true);
        setTitle("Display shareholders");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);

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
        JButton addButton = new JButton("ADD SHAREHOLDER");
        addButton.setPreferredSize(new Dimension(200, 30));
        JButton delButton = new JButton("DELETE SHAREHOLDER");
        delButton.setPreferredSize(new Dimension(200, 30));
        JButton backButton = new JButton("return");
        backButton.setPreferredSize(new Dimension(100, 30));

        btmPanel.add(addButton);
        btmPanel.add(delButton);
        btmPanel.add(backButton);

        backButton.addActionListener(new ActionListener() {
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

            @Override
            public void actionPerformed(ActionEvent e) {
                Home home = new Home(dim);
                home.setVisible(true);
                setVisible(false);
            }
        });

        addButton.addActionListener(new ActionListener() {
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

            @Override
            public void actionPerformed(ActionEvent e) {
                AddShareholder addShareholder = new AddShareholder(dim);
                addShareholder.setVisible(true);
                setVisible(false);
            }
        });

        delButton.addActionListener(new ActionListener() {
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
                DeleteShareholder deleteShareholder = new DeleteShareholder(table,dim);
                setVisible(false);
            }
        });
    }
}
