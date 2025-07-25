package dash.form;

import dash.model.Model_Card;
import dash.model.StatusType;
import view.dash.swing.ScrollBar;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Form_Home extends javax.swing.JPanel {

    public Form_Home() {
        initComponents();
        card1.setData(new Model_Card(new ImageIcon(getClass().getResource("/resources/icon/mail.png")), "Appointments", "Book your sessions", ""));
        card2.setData(new Model_Card(new ImageIcon(getClass().getResource("/resources/icon/8.png")), "Councelers", "Checkout our councelers", ""));
        card3.setData(new Model_Card(new ImageIcon(getClass().getResource("/resources/icon/flag.png")), "Feedback", "Give us feedback", ""));
    spTable.setVerticalScrollBar(new ScrollBar());
        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable.getViewport().setBackground(Color.WHITE);
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);

        loadCounselors();
    }

    private void loadCounselors() {
        try {
            Connection con = db.DatabaseConnection.getInstance().getConnection();
            String sql = "SELECT name, surname, specialization, availability FROM counselors";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            LocalDate today = LocalDate.now();
            LocalTime now = LocalTime.now();

            while (rs.next()) {
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String specialization = rs.getString("specialization");
                String availability = rs.getString("availability");
                StatusType status = determineStatus(con, name, surname, availability, today, now);

                table.addRow(new Object[]{name, surname, specialization, availability, status});
            }

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private StatusType determineStatus(Connection con, String name, String surname, String availability, LocalDate today, LocalTime now) {
        try {
            DayOfWeek day = today.getDayOfWeek();

            // Check availability constraints
            switch (availability) {
                case "Unavailable":
                    return StatusType.UNAVAILABLE;
                case "Available on weekdays only":
                    if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY || now.isBefore(LocalTime.of(8, 0)) || now.isAfter(LocalTime.of(17, 0))) {
                    return StatusType.UNAVAILABLE;
                    }
                    break;
                case "Only available in the mornings":
                    if (now.isBefore(LocalTime.of(8, 0)) || now.isAfter(LocalTime.of(11, 59))) {
                        return StatusType.UNAVAILABLE;
                    }
                    break;
                case "Only in the afternoons":
                    if (now.isBefore(LocalTime.of(12, 0)) || now.isAfter(LocalTime.of(17, 0))) {
                        return StatusType.UNAVAILABLE;
                    }
                    break;
                // "Always available" falls through
            }

            // Check if counselor is booked now
            String fullName = name + " " + surname;
            String checkSql = "SELECT time FROM appointments WHERE counselor = ? AND date = ?";
            PreparedStatement stmt = con.prepareStatement(checkSql);
            stmt.setString(1, fullName);
            stmt.setDate(2, java.sql.Date.valueOf(today));
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                LocalTime appointmentTime = rs.getTime("time").toLocalTime();
                LocalTime endTime = appointmentTime.plusHours(1);
                if (!now.isBefore(appointmentTime) && now.isBefore(endTime)) {
                    rs.close();
                    stmt.close();
                    return StatusType.UNAVAILABLE;
                }
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return StatusType.AVALIABLE;
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JLayeredPane();
        card1 = new view.dash.component.Card();
        card2 = new view.dash.component.Card();
        card3 = new view.dash.component.Card();
        panelBorder1 = new view.dash.swing.PanelBorder();
        jLabel1 = new javax.swing.JLabel();
        spTable = new javax.swing.JScrollPane();
        table = new view.dash.swing.Table();

        panel.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        card1.setColor1(new java.awt.Color(0, 255, 0));
        card1.setColor2(new java.awt.Color(0, 153, 0));
        panel.add(card1);

        card2.setColor1(new java.awt.Color(0, 51, 0));
        card2.setColor2(new java.awt.Color(0, 204, 102));
        panel.add(card2);

        card3.setColor1(new java.awt.Color(0, 102, 0));
        card3.setColor2(new java.awt.Color(51, 153, 0));
        panel.add(card3);

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(127, 127, 127));
        jLabel1.setText("Currently Available Counselors");

        spTable.setBorder(null);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Surname", "Speaciality", "Avaliability", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        spTable.setViewportView(table);

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(spTable))
                .addContainerGap())
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 737, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private view.dash.component.Card card1;
    private view.dash.component.Card card2;
    private view.dash.component.Card card3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLayeredPane panel;
    private view.dash.swing.PanelBorder panelBorder1;
    private javax.swing.JScrollPane spTable;
    private view.dash.swing.Table table;
    // End of variables declaration//GEN-END:variables
}
