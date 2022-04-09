import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Producatori implements ActionListener {
    JFrame frame = new JFrame("Producatori");
    JPanel panel = new JPanel();
    JButton button = new JButton("Editeaza producatori");
    JButton refresh = new JButton("Refresh");
    JLabel label = new JLabel("Producatori");
    Producatori(){
        button.addActionListener(this);
        refresh.addActionListener(this);
        label.setFont(new Font("Serif", Font.BOLD, 28));
        try {
            String query = "select * from Producatori;";
            Statement stmt = Main.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            String query1 = "select count(*) as numarLinii from Producatori;";
            Statement stmt1 = Main.con.createStatement();
            ResultSet rs1 = stmt1.executeQuery(query1);

            rs1.next();

            int numarLinii = rs1.getInt("numarLinii");
            String columns[] = { "ID", "Denumire", "Tara de Origine", "Adresa" };
            String data[][] = new String[numarLinii][4];

            int i=0;
            while (rs.next()) {
                int id = rs.getInt("ProducatorID");
                String denumire = rs.getString("Denumire");
                String taraOrigine = rs.getString("TaraOrigine");
                String adresa = rs.getString("Adresa");
                data[i][0] = id + "";
                data[i][1] = denumire;
                data[i][2] = taraOrigine;
                data[i][3] = adresa;
                i++;
            }

            DefaultTableModel model = new DefaultTableModel(data, columns);
            JTable jt = new JTable(model);
            jt.setRowHeight(25);
            jt.setShowGrid(true);
            jt.setShowVerticalLines(true);
            jt.setFont(new Font("", Font.BOLD, 13));
            TableColumn column = jt.getColumnModel().getColumn(0);
            column.setPreferredWidth(50);
            column.setMaxWidth(70);

            jt.getTableHeader().setFont(new Font("", Font.BOLD, 13));
            JScrollPane scrollPane = new JScrollPane(jt, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            label.setBounds(160, 25, 400, 50);
            button.setBounds(150, 520, 200, 40);
            refresh.setBounds(200, 80, 100, 20);
            button.setFocusable(false);
            button.setBackground(Color.lightGray);
            refresh.setFocusable(false);
            refresh.setBackground(Color.lightGray);
            scrollPane.setBounds(10, 110, 480, 380);
            panel.add(label);
            panel.add(button);
            panel.add(refresh);
            panel.add(scrollPane);
            panel.setLayout(null);

            frame.add(panel);
            frame.setSize(500, 620);
            frame.setVisible(true);
            frame.setResizable(false);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == button)
        {
            editeazaProducatori newWindow = new editeazaProducatori();
        }
        if (actionEvent.getSource() == refresh)
        {
            frame.dispose();
            Producatori producatori = new Producatori();
        }
    }
}