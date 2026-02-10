/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package mv_motors;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import mv_motors.controllers.ModelosJpaController;
import mv_motors.entities.Modelos;

/**
 *
 * @author caiov
 */
public class CadastroModelos extends javax.swing.JFrame {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("MV_MotorsPU");
    ModelosJpaController modelosController = new ModelosJpaController(emf);
    private final List<JTextField> modeloLists;

    private final String[] combustivel = {
        "Gasolina",
        "Etanol",
        "Diesel",
        "Podium",
        "Flex",
        "Eletrico"
    };

    private final String[] transmissao = {
        "Automatico",
        "Manual",
        "CVT"
    };

    /**
     * Creates new form NewJFrame
     */
    public CadastroModelos() {
        initComponents();
        atualizarTabela();

        modeloLists = Arrays.asList(
                textField_marca, // Marca
                textField_motor, // Motor
                textField_nome, // Nome
                textField_cilindradas, // Cilindradas
                textField_capacidadeMotor, // Capacidade do tanque
                textField_consumoMedio, // Consumo médio
                textField_potenciaMotor // Potência Motor
        );

        for (int i = 0; i < combustivel.length; i++) {
            comboBox_combustivel.addItem(combustivel[i]);
        }

        for (int i = 0; i < transmissao.length; i++) {
            comboBox_transmissao.addItem(transmissao[i]);
        }

        button_remover.setEnabled(false);
        button_atualizar.setEnabled(false);
    }

    public void limpar() {
        textField_idModelo.setText(""); // idModelo
        textField_marca.setText(""); // Marca
        textField_motor.setText(""); // Motor
        textField_nome.setText(""); // Nome
        textField_cilindradas.setText(""); // Cilindradas
        textField_capacidadeMotor.setText(""); // Capacidade do tanque
        textField_consumoMedio.setText(""); // Consumo médio
        textField_potenciaMotor.setText(""); // Potência Motor

        checkBox_arCondicionado.setSelected(false); // Ar condicionado
        checkBox_airbagDianteiro.setSelected(false); // Airbag dianteiro
        checkBox_airbagTraseiro.setSelected(false); // Airbag traseiro
        checkBox_direcao.setSelected(false); // Direção hidraulica
        checkBox_vidro.setSelected(false); // Vidro elétrico
        checkBox_camerasLaterais.setSelected(false); // Cameras laterais
        checkBox_cameraTraseira.setSelected(false); // Camera traseira

        // atualizando a tabela...
        atualizarTabela();

        button_inserir.setEnabled(true);        // ativando o botao inserir
        button_remover.setEnabled(false);       // desativando o botao remover
        button_atualizar.setEnabled(false);     // desativando o botao alterar
    }

    private void atualizarTabela() {
        DefaultTableModel model = (DefaultTableModel) table_modelos.getModel();

        model.setRowCount(0); // limpa a tabela

        try {
            List<mv_motors.entities.Modelos> modelos = modelosController.findModelosEntities(); // Assuming Modelos entity is in mv_motors.entities package

            for (mv_motors.entities.Modelos modelo : modelos) {
                Object[] linha = {
                    modelo.getIdModelo(),
                    modelo.getMarca(),
                    modelo.getMotor(),
                    modelo.getNome(),
                    modelo.getCilindradas(),
                    modelo.getTransmissao(),
                    modelo.getCapacidadeDoTanque(),
                    modelo.getConsumoMedio(),
                    modelo.getPotenciaMotor(),
                    modelo.getArCondicionado() ? "Sim" : "Não",
                    modelo.getAirbagDianteiro() ? "Sim" : "Não",
                    modelo.getAirbagTraseiro() ? "Sim" : "Não",
                    modelo.getDirecaoHidraulica() ? "Sim" : "Não",
                    modelo.getVidroEletrico() ? "Sim" : "Não",
                    modelo.getCamerasLaterais() ? "Sim" : "Não",
                    modelo.getCombustivel(),
                    modelo.getCameraTraseira() ? "Sim" : "Não"
                };

                model.addRow(linha);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        button_inserir = new javax.swing.JButton();
        button_limpar = new javax.swing.JButton();
        button_remover = new javax.swing.JButton();
        button_atualizar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_modelos = new javax.swing.JTable();
        textField_idModelo = new javax.swing.JTextField();
        textField_marca = new javax.swing.JTextField();
        textField_motor = new javax.swing.JTextField();
        textField_nome = new javax.swing.JTextField();
        textField_cilindradas = new javax.swing.JTextField();
        textField_capacidadeMotor = new javax.swing.JTextField();
        textField_consumoMedio = new javax.swing.JTextField();
        textField_potenciaMotor = new javax.swing.JTextField();
        checkBox_arCondicionado = new javax.swing.JCheckBox();
        checkBox_airbagDianteiro = new javax.swing.JCheckBox();
        checkBox_airbagTraseiro = new javax.swing.JCheckBox();
        checkBox_direcao = new javax.swing.JCheckBox();
        checkBox_vidro = new javax.swing.JCheckBox();
        checkBox_camerasLaterais = new javax.swing.JCheckBox();
        checkBox_cameraTraseira = new javax.swing.JCheckBox();
        jLabel18 = new javax.swing.JLabel();
        comboBox_transmissao = new javax.swing.JComboBox<>();
        comboBox_combustivel = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Modelos");

        jLabel1.setText("ID Modelo");

        jLabel2.setText("Marca:");

        jLabel3.setText("Motor");

        jLabel4.setText("Nome:");

        jLabel5.setText("Cilindradas:");

        jLabel6.setText("Transmissão:");

        jLabel7.setText("Capacidade do tanque:");

        jLabel8.setText("Consumo médio:");

        jLabel9.setText("Potência Motor:");

        jLabel10.setText("Ar condicionado:");

        jLabel11.setText("Airbag dianteiro:");

        jLabel12.setText("Airbag traseiro:");

        jLabel13.setText("Direção hidraulica:");

        jLabel14.setText("Vidro elétrico:");

        jLabel15.setText("Cameras laterais:");

        jLabel16.setText("Combustível:");

        jLabel17.setText("Camera traseira:");

        button_inserir.setText("Inserir");
        button_inserir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_inserirActionPerformed(evt);
            }
        });

        button_limpar.setText("Limpar");
        button_limpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_limparActionPerformed(evt);
            }
        });

        button_remover.setText("Remover");
        button_remover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_removerActionPerformed(evt);
            }
        });

        button_atualizar.setText("Atualizar");
        button_atualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_atualizarActionPerformed(evt);
            }
        });

        table_modelos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "idModelo", "Marca", "Motor", "Nome", "Cilindradas", "Transmissão", "Capacidade do tanque", "Consumo médio", "Potência motor", "Ar condicionado", "Airbag dianteiro", "Airbag traseiro", "Direção hidraulica", "Vidro elétrico", "Cameras laterais", "Combustível", "Camera traseira"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_modelos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_modelosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_modelos);

        textField_idModelo.setEditable(false);
        textField_idModelo.setToolTipText("Identificador único do modelo no sistema.");
        textField_idModelo.setEnabled(false);

        textField_marca.setToolTipText("Nome da fabricante do veículo.");

        textField_motor.setToolTipText("Tipo ou código do motor.");

        textField_nome.setToolTipText("Nome comercial do modelo.");

        textField_cilindradas.setToolTipText("Volume total dos cilindros do motor (em cm³ ou litros).");
        textField_cilindradas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField_cilindradasActionPerformed(evt);
            }
        });

        textField_capacidadeMotor.setToolTipText("Volume máximo de combustível que o tanque comporta (em litros).");
        textField_capacidadeMotor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField_capacidadeMotorActionPerformed(evt);
            }
        });

        textField_consumoMedio.setToolTipText("Média de consumo de combustível (km/l).");

        textField_potenciaMotor.setToolTipText("Potência do motor (em cavalos ou kW).");

        checkBox_arCondicionado.setToolTipText("Indica se o veículo possui ar-condicionado.");

        checkBox_airbagDianteiro.setToolTipText("Indica presença de airbags frontais.");

        checkBox_airbagTraseiro.setToolTipText("Indica presença de airbags traseiros.");
        checkBox_airbagTraseiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBox_airbagTraseiroActionPerformed(evt);
            }
        });

        checkBox_direcao.setToolTipText("Indica se o veículo possui direção assistida hidraulicamente.");

        checkBox_vidro.setToolTipText("Indica se os vidros são acionados eletricamente.");

        checkBox_camerasLaterais.setToolTipText("Indica se o veículo possui câmeras laterais.");
        checkBox_camerasLaterais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBox_camerasLateraisActionPerformed(evt);
            }
        });

        checkBox_cameraTraseira.setToolTipText("Indica se o veículo possui câmera de ré.");

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/images.jpeg"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(textField_nome, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                                    .addComponent(textField_cilindradas)
                                    .addComponent(textField_capacidadeMotor, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(textField_motor)
                                    .addComponent(comboBox_transmissao, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE))
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(textField_marca, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textField_idModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(checkBox_airbagDianteiro)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(textField_potenciaMotor, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(checkBox_arCondicionado)
                                                    .addComponent(checkBox_cameraTraseira)
                                                    .addComponent(checkBox_camerasLaterais))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel12)
                                                    .addComponent(jLabel13)
                                                    .addComponent(jLabel14))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(checkBox_vidro)
                                                    .addComponent(checkBox_direcao)
                                                    .addComponent(checkBox_airbagTraseiro)))
                                            .addComponent(comboBox_combustivel, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(29, 29, 29)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(button_remover)
                                            .addComponent(button_atualizar)
                                            .addComponent(button_limpar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(textField_consumoMedio, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(29, 29, 29)
                                        .addComponent(button_inserir, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel18)
                        .addGap(0, 136, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(button_inserir)
                            .addComponent(textField_idModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textField_consumoMedio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(textField_marca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16)
                            .addComponent(button_remover)
                            .addComponent(comboBox_combustivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(textField_motor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(textField_potenciaMotor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(button_atualizar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(textField_nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(textField_cilindradas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(comboBox_transmissao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(textField_capacidadeMotor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(checkBox_camerasLaterais, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel15))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel17)
                                            .addComponent(checkBox_cameraTraseira))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(checkBox_arCondicionado)
                                            .addComponent(jLabel10)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(checkBox_airbagTraseiro))
                                            .addComponent(button_limpar))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(checkBox_direcao)
                                            .addComponent(jLabel13))
                                        .addGap(11, 11, 11)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel14)
                                            .addComponent(checkBox_vidro))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(checkBox_airbagDianteiro)))))
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button_inserirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_inserirActionPerformed
        try {
            //Check if all text fields are filled, excluding the ID for new insertion
            if (modeloLists.stream().anyMatch(v -> v.getText().isEmpty())) {
                throw new Exception("Preencha todos os campos obrigatórios!");
            }

            Modelos modelo = new Modelos(
                    textField_marca.getText(), // Marca
                    textField_motor.getText(), // Motor
                    textField_nome.getText(), // Nome
                    Integer.parseInt(textField_cilindradas.getText()), // Cilindradas
                    (String) comboBox_transmissao.getSelectedItem(), // Transmissão
                    new BigDecimal(textField_capacidadeMotor.getText()), // Capacidade do tanque
                    new BigDecimal(textField_consumoMedio.getText()), // Consumo médio
                    new BigDecimal(textField_potenciaMotor.getText()), // Potência Motor 
                    checkBox_arCondicionado.isSelected(), // Ar condicionado
                    checkBox_airbagDianteiro.isSelected(), // Airbag dianteiro
                    checkBox_airbagTraseiro.isSelected(), // Airbag traseiro
                    checkBox_direcao.isSelected(), // Direção hidraulica
                    checkBox_vidro.isSelected(), // Vidro elétrico
                    checkBox_camerasLaterais.isSelected(), // Cameras laterais
                    (String) comboBox_combustivel.getSelectedItem(), // Combustível
                    checkBox_cameraTraseira.isSelected() // Camera traseira
            );

            modelosController.create(modelo);

            JOptionPane.showMessageDialog(
                    this,
                    "Modelo: " + textField_nome.getText() + " inserido com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE
            );

            limpar();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Erro de formato numérico. Verifique os campos de números.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        } catch (mv_motors.controllers.exceptions.IllegalOrphanException ex) {
            JOptionPane.showMessageDialog(this, "O modelo não pode ser removido porque já está relacionado a uma veiculo.", "Erro de Integridade", JOptionPane.WARNING_MESSAGE);
            ex.printStackTrace();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }//GEN-LAST:event_button_inserirActionPerformed

    private void button_limparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_limparActionPerformed
        limpar();
    }//GEN-LAST:event_button_limparActionPerformed

    private void checkBox_airbagTraseiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBox_airbagTraseiroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkBox_airbagTraseiroActionPerformed

    private void button_removerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_removerActionPerformed
        try {
            if (textField_idModelo.getText().isEmpty()) {
                throw new Exception("Selecione um modelo para remover.");
            }
            int idModelo = Integer.parseInt(textField_idModelo.getText());
            modelosController.destroy(idModelo);
            JOptionPane.showMessageDialog(
                    this,
                    "Modelo removido com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE
            );
            limpar();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "ID do Modelo inválido.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }//GEN-LAST:event_button_removerActionPerformed

    private void button_atualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_atualizarActionPerformed
        try {
            if (modeloLists.stream().anyMatch(v -> v.getText().isEmpty()) || textField_idModelo.getText().isEmpty()) {
                throw new Exception("Preencha todos os campos obrigatórios!");
            }

            int idModelo = Integer.parseInt(textField_idModelo.getText());
            mv_motors.entities.Modelos modelo = modelosController.findModelos(idModelo);

            if (modelo == null) {
                throw new Exception("Modelo não encontrado para atualização.");
            }

            modelo.setMarca(textField_marca.getText());
            modelo.setMotor(textField_motor.getText());
            modelo.setNome(textField_nome.getText());
            modelo.setCilindradas(Integer.parseInt(textField_cilindradas.getText()));
            modelo.setTransmissao((String) comboBox_transmissao.getSelectedItem());
            modelo.setCapacidadeDoTanque(new BigDecimal(textField_capacidadeMotor.getText()));
            modelo.setConsumoMedio(new BigDecimal(textField_consumoMedio.getText()));
            modelo.setPotenciaMotor(new BigDecimal(textField_potenciaMotor.getText()));
            modelo.setArCondicionado(checkBox_arCondicionado.isSelected());
            modelo.setAirbagDianteiro(checkBox_airbagDianteiro.isSelected());
            modelo.setAirbagTraseiro(checkBox_airbagTraseiro.isSelected());
            modelo.setDirecaoHidraulica(checkBox_direcao.isSelected());
            modelo.setVidroEletrico(checkBox_vidro.isSelected());
            modelo.setCamerasLaterais(checkBox_camerasLaterais.isSelected());
            modelo.setCombustivel((String) comboBox_combustivel.getSelectedItem());
            modelo.setCameraTraseira(checkBox_cameraTraseira.isSelected());

            modelosController.edit(modelo);

            JOptionPane.showMessageDialog(
                    this,
                    "Modelo: " + textField_nome.getText() + " alterado com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE
            );

            limpar();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Erro de formato numérico. Verifique os campos de números.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );       
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }//GEN-LAST:event_button_atualizarActionPerformed

    private void table_modelosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_modelosMouseClicked
        DefaultTableModel model = (DefaultTableModel) table_modelos.getModel();

        if (evt.getClickCount() == 2) {
            int row = table_modelos.getSelectedRow();

            textField_idModelo.setText(model.getValueAt(row, 0).toString()); // idModelo
            textField_marca.setText(model.getValueAt(row, 1).toString()); // Marca
            textField_motor.setText(model.getValueAt(row, 2).toString()); // Motor
            textField_nome.setText(model.getValueAt(row, 3).toString()); // Nome
            textField_cilindradas.setText(model.getValueAt(row, 4).toString()); // Cilindradas
            comboBox_transmissao.setSelectedItem(model.getValueAt(row, 5).toString()); // Transmissão
            textField_capacidadeMotor.setText(model.getValueAt(row, 6).toString()); // Capacidade do tanque
            textField_consumoMedio.setText(model.getValueAt(row, 7).toString()); // Consumo médio
            textField_potenciaMotor.setText(model.getValueAt(row, 8).toString()); // Potência Motor
            checkBox_arCondicionado.setSelected(model.getValueAt(row, 9).toString().equals("Sim")); // Ar condicionado
            checkBox_airbagDianteiro.setSelected(model.getValueAt(row, 10).toString().equals("Sim")); // Airbag dianteiro
            checkBox_airbagTraseiro.setSelected(model.getValueAt(row, 11).toString().equals("Sim")); // Airbag traseiro
            checkBox_direcao.setSelected(model.getValueAt(row, 12).toString().equals("Sim")); // Direção hidraulica
            checkBox_vidro.setSelected(model.getValueAt(row, 13).toString().equals("Sim")); // Vidro elétrico
            checkBox_camerasLaterais.setSelected(model.getValueAt(row, 14).toString().equals("Sim")); // Cameras laterais
            comboBox_combustivel.setSelectedItem(model.getValueAt(row, 15).toString()); // Combustível
            checkBox_cameraTraseira.setSelected(model.getValueAt(row, 16).toString().equals("Sim")); // Camera traseira

            // Enable update and remove buttons, disable insert
            button_atualizar.setEnabled(true);
            button_remover.setEnabled(true);
            button_inserir.setEnabled(false);
        }
    }//GEN-LAST:event_table_modelosMouseClicked

    private void textField_cilindradasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField_cilindradasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField_cilindradasActionPerformed

    private void textField_capacidadeMotorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField_capacidadeMotorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField_capacidadeMotorActionPerformed

    private void checkBox_camerasLateraisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBox_camerasLateraisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkBox_camerasLateraisActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CadastroModelos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroModelos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroModelos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroModelos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastroModelos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_atualizar;
    private javax.swing.JButton button_inserir;
    private javax.swing.JButton button_limpar;
    private javax.swing.JButton button_remover;
    private javax.swing.JCheckBox checkBox_airbagDianteiro;
    private javax.swing.JCheckBox checkBox_airbagTraseiro;
    private javax.swing.JCheckBox checkBox_arCondicionado;
    private javax.swing.JCheckBox checkBox_cameraTraseira;
    private javax.swing.JCheckBox checkBox_camerasLaterais;
    private javax.swing.JCheckBox checkBox_direcao;
    private javax.swing.JCheckBox checkBox_vidro;
    private javax.swing.JComboBox<String> comboBox_combustivel;
    private javax.swing.JComboBox<String> comboBox_transmissao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table_modelos;
    private javax.swing.JTextField textField_capacidadeMotor;
    private javax.swing.JTextField textField_cilindradas;
    private javax.swing.JTextField textField_consumoMedio;
    private javax.swing.JTextField textField_idModelo;
    private javax.swing.JTextField textField_marca;
    private javax.swing.JTextField textField_motor;
    private javax.swing.JTextField textField_nome;
    private javax.swing.JTextField textField_potenciaMotor;
    // End of variables declaration//GEN-END:variables
}
