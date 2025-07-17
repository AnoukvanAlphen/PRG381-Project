/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view.pages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author bosma
 */
public class FeedbackForm extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FeedbackForm.class.getName());

    /**
     * Creates new form FeedbackForm
     */
    public FeedbackForm() {
        initComponents();
        tblFeedback.getColumnModel().getColumn(0).setMinWidth(0);
        tblFeedback.getColumnModel().getColumn(0).setMaxWidth(0);
        tblFeedback.getColumnModel().getColumn(0).setWidth(0);
        
        populateRatingComboBox();
         tblFeedback.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            int selectedRow = tblFeedback.getSelectedRow();
            if (selectedRow != -1) {
                txtName.setText(tblFeedback.getValueAt(selectedRow, 1).toString());
                txtSurname.setText(tblFeedback.getValueAt(selectedRow, 2).toString());
                cmbRating.setSelectedItem(tblFeedback.getValueAt(selectedRow, 3).toString());
                txtComments.setText(tblFeedback.getValueAt(selectedRow, 4).toString());

                // Get hidden ID column
                int feedbackId = Integer.parseInt(tblFeedback.getValueAt(selectedRow, 0).toString());

                // Fetch email from DB
                try {
                    Connection con = db.DatabaseConnection.getInstance().getConnection();
                    String sql = "SELECT email FROM feedback WHERE id = ?";
                    java.sql.PreparedStatement stmt = con.prepareStatement(sql);
                    stmt.setInt(1, feedbackId);
                    java.sql.ResultSet rs = stmt.executeQuery();

                    if (rs.next()) {
                        String email = rs.getString("email");
                        txtEmail.setText(email);
                    }

                    stmt.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error retrieving email: " + ex.getMessage());
                }

                // Set colors to black
                txtName.setForeground(java.awt.Color.BLACK);
                txtSurname.setForeground(java.awt.Color.BLACK);
                txtEmail.setForeground(java.awt.Color.BLACK);
                txtComments.setForeground(java.awt.Color.BLACK);

                updateCharCount();
            }
        }
    });

        
     
        txtComments.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
    public void insertUpdate(javax.swing.event.DocumentEvent e) {
        updateCharCount();
        
    }
    
    

    public void removeUpdate(javax.swing.event.DocumentEvent e) {
        updateCharCount();
    }

    public void changedUpdate(javax.swing.event.DocumentEvent e) {
        updateCharCount();
    }
});
    }
    private void populateRatingComboBox() {
    cmbRating.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{
        "1 - Very Poor",
        "2 - Poor",
        "3 - Average",
        "4 - Good",
        "5 - Excellent"
    }));
  }
    private void updateCharCount() {
    int maxChars = 200;
    int currentLength = txtComments.getText().length();
    int remaining = maxChars - currentLength;

    if (remaining >= 0) {
        lblCharCount.setText(remaining + " characters remaining");
        lblCharCount.setForeground(new java.awt.Color(255, 255, 255));
    } else {
        lblCharCount.setText("Character limit exceeded!");
        lblCharCount.setForeground(java.awt.Color.RED);
    }
  }
    private void loadFeedbackTable() {
    try {
        Connection con = db.DatabaseConnection.getInstance().getConnection();
        String sql = "SELECT id, name, surname, rating, comments FROM feedback";
        java.sql.PreparedStatement stmt = con.prepareStatement(sql);
        java.sql.ResultSet rs = stmt.executeQuery();

        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) tblFeedback.getModel();
        model.setRowCount(0); // Clear old rows

        while (rs.next()) {
            Object[] row = {
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("surname"),
                rs.getString("rating"),
                rs.getString("comments")
            };
            model.addRow(row);
        }

        stmt.close();
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error loading feedback: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }
    private void clearForm() {
    txtName.setText("");
    txtSurname.setText("");
    txtEmail.setText("");
    cmbRating.setSelectedIndex(0);
    txtComments.setText("");
    lblCharCount.setText("200 characters remaining");
    lblCharCount.setForeground(new java.awt.Color(255, 255, 255));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblHeading = new javax.swing.JLabel();
        javax.swing.JLabel lblName = new javax.swing.JLabel();
        javax.swing.JLabel lblSurname = new javax.swing.JLabel();
        javax.swing.JLabel lblEmail = new javax.swing.JLabel();
        javax.swing.JLabel lblRating = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblFeedback = new javax.swing.JTable();
        txtName = new javax.swing.JTextField();
        txtSurname = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        cmbRating = new javax.swing.JComboBox<>();
        javax.swing.JLabel lblCommnent = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtComments = new javax.swing.JTextArea();
        btnSubmit = new javax.swing.JButton();
        btnView = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        lblCharCount = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 51, 51));

        lblHeading.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblHeading.setForeground(new java.awt.Color(255, 255, 255));
        lblHeading.setText("Feedback");

        lblName.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblName.setForeground(new java.awt.Color(255, 255, 255));
        lblName.setText("Name:");

        lblSurname.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblSurname.setForeground(new java.awt.Color(255, 255, 255));
        lblSurname.setText("Surname:");

        lblEmail.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblEmail.setForeground(new java.awt.Color(255, 255, 255));
        lblEmail.setText("E-mail:");

        lblRating.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblRating.setForeground(new java.awt.Color(255, 255, 255));
        lblRating.setText("Rating:");

        tblFeedback.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "feedback_id", "Name", "Surname", "Rating", "Comments"
            }
        ));
        jScrollPane1.setViewportView(tblFeedback);

        txtName.setForeground(new java.awt.Color(153, 153, 153));
        txtName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtName.setText("Enter name");
        txtName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameActionPerformed(evt);
            }
        });

        txtSurname.setForeground(new java.awt.Color(153, 153, 153));
        txtSurname.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSurname.setText("Enter Surname");
        txtSurname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSurnameActionPerformed(evt);
            }
        });

        txtEmail.setForeground(new java.awt.Color(153, 153, 153));
        txtEmail.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEmail.setText("Enter E-mail");
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        cmbRating.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRatingActionPerformed(evt);
            }
        });

        lblCommnent.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblCommnent.setForeground(new java.awt.Color(255, 255, 255));
        lblCommnent.setText("Comments:");

        txtComments.setColumns(15);
        txtComments.setForeground(new java.awt.Color(204, 204, 204));
        txtComments.setRows(3);
        txtComments.setTabSize(4);
        txtComments.setText("Additional comments");
        jScrollPane2.setViewportView(txtComments);

        btnSubmit.setBackground(new java.awt.Color(102, 255, 102));
        btnSubmit.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSubmit.setText("Submit");
        btnSubmit.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 51)));
        btnSubmit.setBorderPainted(false);
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });

        btnView.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnView.setText("View History");
        btnView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewActionPerformed(evt);
            }
        });

        btnEdit.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEdit.setText("Edit Feedback");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnDelete.setText("Delete Feedback");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        lblCharCount.setForeground(new java.awt.Color(255, 255, 255));
        lblCharCount.setText("200 characters remaining");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnDelete)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblCommnent)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnView))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblCharCount, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(btnEdit)))))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lblHeading)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                                .addComponent(txtName))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(lblRating, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cmbRating, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(lblSurname, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtSurname, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(675, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblHeading, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblName)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblSurname)
                                .addComponent(txtSurname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblRating, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbRating, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblCharCount)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblCommnent, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnView, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25))))
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameActionPerformed

    private void txtSurnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSurnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSurnameActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void cmbRatingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRatingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbRatingActionPerformed

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
       
    String name = txtName.getText().trim();
    String surname = txtSurname.getText().trim();
    String email = txtEmail.getText().trim();
    String rating = (String) cmbRating.getSelectedItem();
    String comments = txtComments.getText().trim();

// Name validation: Start with capital, letters only
    if (!name.matches("[A-Z][a-zA-Z]*")) {
    JOptionPane.showMessageDialog(this, "Name must start with a capital letter and contain only letters.", "Invalid Name", JOptionPane.ERROR_MESSAGE);
    return;
    }

// Surname validation: Start with capital, letters only
    if (!surname.matches("[A-Z][a-zA-Z]*")) {
    JOptionPane.showMessageDialog(this, "Surname must start with a capital letter and contain only letters.", "Invalid Surname", JOptionPane.ERROR_MESSAGE);
    return;
    }

// Email validation
    if (!email.matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$")) {
    JOptionPane.showMessageDialog(this, "Please enter a valid email address.", "Invalid Email", JOptionPane.ERROR_MESSAGE);
    return;
    }

// Rating validation
    if (rating == null || rating.isEmpty()) {
    JOptionPane.showMessageDialog(this, "Please select a rating.", "Missing Rating", JOptionPane.ERROR_MESSAGE);
    return;
    }
    if (txtComments.getText().length() > 200) {
    JOptionPane.showMessageDialog(this, "Comments cannot exceed 500 characters.", "Limit Exceeded", JOptionPane.ERROR_MESSAGE);
    return;
    }
    try {
    Connection con = db.DatabaseConnection.getInstance().getConnection();
    String sql = "INSERT INTO feedback (name, surname, email, rating, comments) VALUES (?, ?, ?, ?, ?)";
    java.sql.PreparedStatement stmt = con.prepareStatement(sql);

    stmt.setString(1, name);
    stmt.setString(2, surname);
    stmt.setString(3, email);
    stmt.setString(4, rating);
    stmt.setString(5, comments);

    int rowsInserted = stmt.executeUpdate();
    if (rowsInserted > 0) {
        JOptionPane.showMessageDialog(this, "Feedback submitted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        loadFeedbackTable(); // Reload table to include new feedback
        clearForm();
    }

    stmt.close();
    } catch (Exception ex) {
    ex.printStackTrace();
    JOptionPane.showMessageDialog(this, "Error saving feedback: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnSubmitActionPerformed

    private void btnViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewActionPerformed
        loadFeedbackTable();
    }//GEN-LAST:event_btnViewActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
         int selectedRow = tblFeedback.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Please select a row to edit.", "No Selection", JOptionPane.WARNING_MESSAGE);
        return;
    }

    String name = txtName.getText().trim();
    String surname = txtSurname.getText().trim();
    String email = txtEmail.getText().trim();
    String rating = (String) cmbRating.getSelectedItem();
    String comments = txtComments.getText().trim();

    // Validation
    if (!name.matches("[A-Z][a-zA-Z]*")) {
        JOptionPane.showMessageDialog(this, "Name must start with a capital letter and contain only letters.", "Invalid Name", JOptionPane.ERROR_MESSAGE);
        return;
    }

    if (!surname.matches("[A-Z][a-zA-Z]*")) {
        JOptionPane.showMessageDialog(this, "Surname must start with a capital letter and contain only letters.", "Invalid Surname", JOptionPane.ERROR_MESSAGE);
        return;
    }

    if (!email.matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$")) {
        JOptionPane.showMessageDialog(this, "Please enter a valid email address.", "Invalid Email", JOptionPane.ERROR_MESSAGE);
        return;
    }

    if (rating == null || rating.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please select a rating.", "Missing Rating", JOptionPane.ERROR_MESSAGE);
        return;
    }

    if (comments.length() > 200) {
        JOptionPane.showMessageDialog(this, "Comments cannot exceed 500 characters.", "Limit Exceeded", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Get values to match original record
    String oldName = tblFeedback.getValueAt(selectedRow, 0).toString();
    String oldSurname = tblFeedback.getValueAt(selectedRow, 1).toString();
    String oldRating = tblFeedback.getValueAt(selectedRow, 2).toString();
    String oldComments = tblFeedback.getValueAt(selectedRow, 3).toString();

   try {
    Connection con = db.DatabaseConnection.getInstance().getConnection();
    int feedbackId = Integer.parseInt(tblFeedback.getValueAt(selectedRow, 0).toString());

    String sql = "UPDATE feedback SET name = ?, surname = ?, email = ?, rating = ?, comments = ? WHERE id = ?";
    java.sql.PreparedStatement stmt = con.prepareStatement(sql);

    stmt.setString(1, name);
    stmt.setString(2, surname);
    stmt.setString(3, email);
    stmt.setString(4, rating);
    stmt.setString(5, comments);
    stmt.setInt(6, feedbackId);

    int updated = stmt.executeUpdate();
    if (updated > 0) {
        JOptionPane.showMessageDialog(this, "Feedback updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        loadFeedbackTable();
        clearForm();
    } else {
        JOptionPane.showMessageDialog(this, "Update failed.", "Error", JOptionPane.ERROR_MESSAGE);
    }

    stmt.close();
    } catch (Exception ex) {
    ex.printStackTrace();
    JOptionPane.showMessageDialog(this, "Error updating feedback: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
         int selectedRow = tblFeedback.getSelectedRow();

    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Please select a feedback entry to delete.", "No Selection", JOptionPane.WARNING_MESSAGE);
        return;
    }

    int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this feedback?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
    if (confirm != JOptionPane.YES_OPTION) {
        return;
    }

    int feedbackId = Integer.parseInt(tblFeedback.getValueAt(selectedRow, 0).toString());

    try {
        Connection con = db.DatabaseConnection.getInstance().getConnection();
        String sql = "DELETE FROM feedback WHERE id = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, feedbackId);

        int affectedRows = stmt.executeUpdate();
        stmt.close();

        if (affectedRows > 0) {
            ((javax.swing.table.DefaultTableModel) tblFeedback.getModel()).removeRow(selectedRow);
            JOptionPane.showMessageDialog(this, "Feedback deleted successfully.", "Deleted", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Could not find feedback entry in database.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error deleting feedback: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnDeleteActionPerformed

    /**
     * @param args the command line arguments
     */
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
        java.awt.EventQueue.invokeLater(() -> new FeedbackForm().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JButton btnView;
    private javax.swing.JComboBox<String> cmbRating;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCharCount;
    private javax.swing.JLabel lblHeading;
    private javax.swing.JTable tblFeedback;
    private javax.swing.JTextArea txtComments;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtSurname;
    // End of variables declaration//GEN-END:variables
}
