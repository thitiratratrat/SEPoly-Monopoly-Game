package allGUI;

import javax.swing.JOptionPane;

public class StartPage{
    public static void main(String args[]) {
        new StartP().setVisible(true);
    }
}

class StartP extends javax.swing.JFrame {

    public StartP() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        logoBg = new javax.swing.JLabel();
        createBtn = new javax.swing.JButton();
        joinBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        nameUser = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SE Poly");
        setBackground(new java.awt.Color(255, 204, 204));
        setIconImages(null);

        logoBg.setIcon(new javax.swing.ImageIcon("src\\allImage\\Asset 11.png")); // NOI18N
        logoBg.setLabelFor(logoBg);

        createBtn.setIcon(new javax.swing.ImageIcon("src\\allImage\\Asset 13.png")); // NOI18N
        createBtn.setBorder(null);
        createBtn.setBorderPainted(false);
        createBtn.setName("createBtn"); // NOI18N
        createBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createBtnActionPerformed(evt);
            }
        });

        joinBtn.setIcon(new javax.swing.ImageIcon("src\\allImage\\Asset 14.png")); // NOI18N
        joinBtn.setToolTipText("");
        joinBtn.setBorder(null);
        joinBtn.setBorderPainted(false);
        joinBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                joinBtnActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Muller Demo ExtraBold", 1, 32)); // NOI18N
        jLabel1.setText("name ");

        nameUser.setFont(new java.awt.Font("Browallia New", 1, 22)); // NOI18N
        nameUser.setText("Enter your name");
        nameUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameUserActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText(":");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nameUser, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(170, 170, 170))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(logoBg, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(144, 144, 144)
                                                .addComponent(createBtn)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 178, Short.MAX_VALUE)
                                                .addComponent(joinBtn)))
                                .addGap(92, 92, 92))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(logoBg, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(nameUser, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(createBtn, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(joinBtn, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(61, 61, 61))
        );

        logoBg.getAccessibleContext().setAccessibleName("logo");

        pack();
    }// </editor-fold>

    private void createBtnActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        System.out.println("name: " + nameUser.getText());
        new CreatePage().setVisible(true);
        dispose();

    }

    private void joinBtnActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        System.out.println("name: " + nameUser.getText());
        int g=-1 ;
        while(g<0 ){
            String roomNumber = JOptionPane.showInputDialog("Enter the room number: ");
            if(roomNumber.length()>0 ){
                g++;
                System.out.println("Hello " + roomNumber);
                new GamePage().setVisible(true);
                dispose();
            }
            else{
                System.out.println("Plese Enter your name!");
            }
        }


    }

    private void nameUserActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    // Variables declaration - do not modify
    private javax.swing.JButton createBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton joinBtn;
    private javax.swing.JLabel logoBg;
    private javax.swing.JTextField nameUser;
    // End of variables declaration
}

