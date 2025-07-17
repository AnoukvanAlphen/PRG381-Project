/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.pages;

import db.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class CounselorForm extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(CounselorForm.class.getName());

    /**
     * Creates new form CounselorForm
     */
    public CounselorForm() {
        initComponents();
        tblCounselors.addMouseListener(new java.awt.event.MouseAdapter() {
    public void mouseClicked(java.awt.event.MouseEvent evt) {
        int selectedRow = tblCounselors.getSelectedRow();
        if (selectedRow != -1) {
            txtName.setText(tblCounselors.getValueAt(selectedRow, 0).toString());
            txtSurname.setText(tblCounselors.getValueAt(selectedRow, 1).toString());
            txtSpecialization.setText(tblCounselors.getValueAt(selectedRow, 2).toString());
            cmbAvailability.setSelectedItem(tblCounselors.getValueAt(selectedRow, 3).toString());
            txtName.setForeground(java.awt.Color.BLACK);
            txtSurname.setForeground(java.awt.Color.BLACK);
            txtSpecialization.setForeground(java.awt.Color.BLACK);
        }
    }
});
        cmbAvailability.setModel(new javax.swing.DefaultComboBoxModel<>(
        new String[] {
            "Available on weekdays only",
            "Always available",
            "Unavailable",
            "Only in the afternoons",
            "Only available in the mornings"
        }
        ));
        
    }
    private void loadCounselorsTable() {
    try (Connection con = DatabaseConnection.getInstance().getConnection()) {
        String sql = "SELECT name, surname, specialization, availability FROM counselors";
        PreparedStatement ps = con.prepareStatement(sql);
        java.sql.ResultSet rs = ps.executeQuery();

        // Clear table first
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) tblCounselors.getModel();
        model.setRowCount(0); // clear existing rows

        // Add rows from database
        while (rs.next()) {
            String name = rs.getString("name");
            String surname = rs.getString("surname");
            String specialization = rs.getString("specialization");
            String availability = rs.getString("availability");

            model.addRow(new Object[]{name, surname, specialization, availability});
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error loading counselors: " + e.getMessage());
    }
  }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        javax.swing.JLabel lblHeading = new javax.swing.JLabel();
        javax.swing.JLabel lblName = new javax.swing.JLabel();
        javax.swing.JLabel lblSurname = new javax.swing.JLabel();
        javax.swing.JLabel lblSpecialization = new javax.swing.JLabel();
        javax.swing.JLabel lblAvailability = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtSurname = new javax.swing.JTextField();
        txtSpecialization = new javax.swing.JTextField();
        cmbAvailability = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCounselors = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        btnView = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 51, 51));

        lblHeading.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblHeading.setForeground(new java.awt.Color(255, 255, 255));
        lblHeading.setText("Counselors");

        lblName.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblName.setForeground(new java.awt.Color(255, 255, 255));
        lblName.setText("Name:");

        lblSurname.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblSurname.setForeground(new java.awt.Color(255, 255, 255));
        lblSurname.setText("Surname:");

        lblSpecialization.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblSpecialization.setForeground(new java.awt.Color(255, 255, 255));
        lblSpecialization.setText("Specialization:");

        lblAvailability.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblAvailability.setForeground(new java.awt.Color(255, 255, 255));
        lblAvailability.setText("Availability:");

        txtName.setForeground(new java.awt.Color(204, 204, 204));
        txtName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtName.setText("Enter Name");
        txtName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameActionPerformed(evt);
            }
        });

        txtSurname.setForeground(new java.awt.Color(204, 204, 204));
        txtSurname.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSurname.setText("Enter Surname");
        txtSurname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSurnameActionPerformed(evt);
            }
        });

        txtSpecialization.setForeground(new java.awt.Color(204, 204, 204));
        txtSpecialization.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSpecialization.setText("Specializing in...");
        txtSpecialization.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSpecializationActionPerformed(evt);
            }
        });

        cmbAvailability.setForeground(new java.awt.Color(204, 204, 204));
        cmbAvailability.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbAvailabilityActionPerformed(evt);
            }
        });

        tblCounselors.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Surname", "Specialization", "Availibility"
            }
        ));
        jScrollPane1.setViewportView(tblCounselors);

        btnAdd.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(51, 51, 51));
        btnAdd.setText("Add Counselor");
        btnAdd.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 51)));
        btnAdd.setBorderPainted(false);
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnUpdate.setText("Update Counselor");
        btnUpdate.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 51)));
        btnUpdate.setBorderPainted(false);
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnRemove.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRemove.setText("Remove Counselor");
        btnRemove.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 51)));
        btnRemove.setBorderPainted(false);
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        btnView.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnView.setForeground(new java.awt.Color(51, 51, 51));
        btnView.setText("View Counselors");
        btnView.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 51)));
        btnView.setBorderPainted(false);
        btnView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtName))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblSurname, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSurname))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblSpecialization, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblAvailability, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtSpecialization, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                            .addComponent(cmbAvailability, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblHeading)
                        .addGap(349, 349, 349))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(btnView, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(btnRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(84, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lblHeading, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblSurname, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSurname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblSpecialization, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSpecialization, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblAvailability, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbAvailability, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnView, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(151, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameActionPerformed

    private void txtSurnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSurnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSurnameActionPerformed

    private void txtSpecializationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSpecializationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSpecializationActionPerformed

    private void cmbAvailabilityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAvailabilityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbAvailabilityActionPerformed
    private boolean isValidName(String name) {
    return name.matches("[A-Z][a-zA-Z]*");
    }
    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
    String name = txtName.getText().trim();
    String surname = txtSurname.getText().trim();
    String specialization = txtSpecialization.getText().trim();
    String availability = cmbAvailability.getSelectedItem().toString();
     
    if (!isValidName(name)) {
        JOptionPane.showMessageDialog(this, "Name must start with a capital letter and contain only letters.");
        return;
    }
     if (!isValidName(surname)) {
        JOptionPane.showMessageDialog(this, "Surname must start with a capital letter and contain only letters.");
        return;
    }

    try (Connection con = DatabaseConnection.getInstance().getConnection()) {
        // Check for duplicate
        String checkSql = "SELECT COUNT(*) FROM counselors WHERE name = ? AND surname = ?";
        PreparedStatement checkStmt = con.prepareStatement(checkSql);
        checkStmt.setString(1, name);
        checkStmt.setString(2, surname);
        ResultSet rs = checkStmt.executeQuery();
        rs.next();
        int count = rs.getInt(1);

        if (count > 0) {
            JOptionPane.showMessageDialog(this, "A counselor with this name and surname already exists.");
            return;
        }

        // Proceed to insert
        String sql = "INSERT INTO counselors (name, surname, specialization, availability) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, name);
        ps.setString(2, surname);
        ps.setString(3, specialization);
        ps.setString(4, availability);
        ps.executeUpdate();

        JOptionPane.showMessageDialog(this, "Counselor added successfully.");
        loadCounselorsTable();
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
    int selectedRow = tblCounselors.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Please select a counselor to update.");
        return;
    }

    // Old values from selected row
    String oldName = tblCounselors.getValueAt(selectedRow, 0).toString();
    String oldSurname = tblCounselors.getValueAt(selectedRow, 1).toString();
    String oldSpecialization = tblCounselors.getValueAt(selectedRow, 2).toString();
    String oldAvailability = tblCounselors.getValueAt(selectedRow, 3).toString();

    // New values from textfields
    String newName = txtName.getText().trim();
    String newSurname = txtSurname.getText().trim();
    String newSpecialization = txtSpecialization.getText().trim();
    String newAvailability = cmbAvailability.getSelectedItem().toString();
    if (!isValidName(newName)) {
    JOptionPane.showMessageDialog(this, "Name must start with a capital letter and contain only letters.");
    return;
    }
      if (!isValidName(newSurname)) {
        JOptionPane.showMessageDialog(this, "Surname must start with a capital letter and contain only letters.");
        return;
    }

    // Only check for duplicate if name OR surname changed
    boolean isNameChanged = !newName.equalsIgnoreCase(oldName);
    boolean isSurnameChanged = !newSurname.equalsIgnoreCase(oldSurname);

    if (isNameChanged || isSurnameChanged) {
        // Check if new name-surname combo already exists
        try (Connection con = DatabaseConnection.getInstance().getConnection()) {
            String checkSql = "SELECT COUNT(*) FROM counselors WHERE LOWER(name) = ? AND LOWER(surname) = ?";
            PreparedStatement checkStmt = con.prepareStatement(checkSql);
            checkStmt.setString(1, newName.toLowerCase());
            checkStmt.setString(2, newSurname.toLowerCase());
            java.sql.ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(this, "A counselor with the same name and surname already exists.");
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error checking duplicates: " + e.getMessage());
            return;
        }
    }

    // Perform the update
    try (Connection con = DatabaseConnection.getInstance().getConnection()) {
        String sql = "UPDATE counselors SET name = ?, surname = ?, specialization = ?, availability = ? " +
                     "WHERE name = ? AND surname = ? AND specialization = ? AND availability = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, newName);
        ps.setString(2, newSurname);
        ps.setString(3, newSpecialization);
        ps.setString(4, newAvailability);

        ps.setString(5, oldName);
        ps.setString(6, oldSurname);
        ps.setString(7, oldSpecialization);
        ps.setString(8, oldAvailability);

        int rowsAffected = ps.executeUpdate();
        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(this, "Counselor updated successfully.");
            loadCounselorsTable();
        } else {
            JOptionPane.showMessageDialog(this, "Update failed. Record not found.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error updating counselor: " + e.getMessage());
    }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
          int selectedRow = tblCounselors.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Please select a counselor to remove.");
        return;
    }

    // Get current selected values
    String name = tblCounselors.getValueAt(selectedRow, 0).toString();
    String surname = tblCounselors.getValueAt(selectedRow, 1).toString();
    String specialization = tblCounselors.getValueAt(selectedRow, 2).toString();
    String availability = tblCounselors.getValueAt(selectedRow, 3).toString();

    int confirm = JOptionPane.showConfirmDialog(this, 
        "Are you sure you want to delete this counselor?", 
        "Confirm Delete", 
        JOptionPane.YES_NO_OPTION);

    if (confirm == JOptionPane.YES_OPTION) {
        try (Connection con = DatabaseConnection.getInstance().getConnection()) {
            String sql = "DELETE FROM counselors WHERE name = ? AND surname = ? AND specialization = ? AND availability = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, surname);
            ps.setString(3, specialization);
            ps.setString(4, availability);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Counselor removed successfully.");
                loadCounselorsTable();
            } else {
                JOptionPane.showMessageDialog(this, "Removal failed. Record not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error removing counselor: " + e.getMessage());
        }
    }
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void btnViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewActionPerformed
        loadCounselorsTable();
        if (tblCounselors.getRowCount() == 0) {
        JOptionPane.showMessageDialog(this, "No counselors to display.");
    }
    }//GEN-LAST:event_btnViewActionPerformed

    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new CounselorForm().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnView;
    private javax.swing.JComboBox<String> cmbAvailability;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblCounselors;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtSpecialization;
    private javax.swing.JTextField txtSurname;
    // End of variables declaration//GEN-END:variables
}
