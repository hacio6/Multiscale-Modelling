/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multiscalemodelling;

import java.awt.Color;
import java.awt.Graphics2D;
//import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

/**
 *
 * @author hacio
 */
public class MultiscaleModellingFrame extends javax.swing.JFrame {

    int width, height;
    int grains, inclusions, sizeOfInclusions;
    int randomX, randomY;
    boolean play;
    Cell[][] matrix, matrix2;
    //Image offScrImg;
    BufferedImage offScrImg;
    //Graphics offScrGraph;
    Graphics2D offScrGraph;
    boolean isFull;
    //int counter;
    //BufferedImage image;
    Map<Integer, Integer> neighbours = new HashMap<Integer, Integer>();
    List<HashMap.Entry<Integer, Integer>> mostFrequent;

    /**
     * Creates new form MultiscaleMdoellingFrame
     */
    public MultiscaleModellingFrame() {

        initComponents();
        startButton.setEnabled(false);
        jProgressBar1.setStringPainted(true);
        jProgressBar1.setString(0 + "% Complete");
        play = false;
        isFull = false;
        //offScrImg = createImage(jPanel1.getWidth(), jPanel1.getHeight());
        offScrImg = new BufferedImage(jPanel1.getWidth(), jPanel1.getHeight(), BufferedImage.TYPE_INT_RGB);
        //offScrGraph = offScrImg.getGraphics();
        offScrGraph = offScrImg.createGraphics();

        Timer time = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                if (play) {
                    if (typeOfNeighborhoodComboBox.getSelectedItem().toString().equals("von Neumann")) {
                        vonNeumann();
                    } else if (typeOfNeighborhoodComboBox.getSelectedItem().toString().equals("Moore")) {
                        moore();
                    } else if (typeOfNeighborhoodComboBox.getSelectedItem().toString().equals("Moore 2")) {
                        moore2();
                    }
                    refresh();
                    isFullMatrix();
                    checkProgress();
                }
            }
        };
        time.scheduleAtFixedRate(task, 0, 100);
        refresh();
    }

    void checkProgress() {
        int counter = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (matrix[i][j].id != -1) {
                    counter++;
                }
            }
        }
        jProgressBar1.setValue((int) ((counter * 100) / (width * height)));
        if ((int) ((counter * 100) / (width * height)) == 100) {
            jProgressBar1.setString("100% Completed!");
        } else {
            jProgressBar1.setString((int) ((counter * 100) / (width * height)) + "% Complete");
        }
    }

    void refresh() {
        offScrGraph.setColor(jPanel1.getBackground());
        offScrGraph.fillRect(0, 0, jPanel1.getWidth(), jPanel1.getHeight());
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (matrix[i][j].id != -1) {
                    offScrGraph.setColor(matrix[i][j].color);
                    int x = i * jPanel1.getWidth() / width;
                    int y = j * jPanel1.getHeight() / height;
                    offScrGraph.fillRect(x, y, jPanel1.getWidth() / width, jPanel1.getHeight() / height);
                }
            }
        }
        /* offScrGraph.setColor(Color.LIGHT_GRAY);
        for (int i = 0; i < height; i++) {
            int y = i * jPanel1.getHeight() / height;
            offScrGraph.drawLine(0, y, jPanel1.getWidth(), y);
        }
        for (int i = 0; i < width; i++) {
            int x = i * jPanel1.getWidth() / width;
            offScrGraph.drawLine(x, 0, x, jPanel1.getHeight());
        }*/
        jPanel1.getGraphics().drawImage(offScrImg, 0, 0, jPanel1);
    }

    /**
     * This method is called from within the constructor to the form. WARNING:
     * Do NOT modify this code. The content of this method is always regenerated
     * by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        startButton = new javax.swing.JButton();
        createButton = new javax.swing.JButton();
        addGrainsButton = new javax.swing.JButton();
        numbersOfGrainsTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        amountOfInclusionsTextField = new javax.swing.JTextField();
        sizeOfInclusionsTextField = new javax.swing.JTextField();
        typeOfInclusionComboBox = new javax.swing.JComboBox<>();
        addInclusionsButton = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar();
        widthTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        heightTextField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        typeOfNeighborhoodComboBox = new javax.swing.JComboBox<>();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        microstructureMenu = new javax.swing.JMenu();
        importMenuItem = new javax.swing.JMenuItem();
        exportMenuItem = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(700, 700));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });
        jPanel1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                jPanel1ComponentResized(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 801, Short.MAX_VALUE)
        );

        startButton.setText("Start");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        createButton.setText("Create");
        createButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createButtonActionPerformed(evt);
            }
        });

        addGrainsButton.setText("Add grains");
        addGrainsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addGrainsButtonActionPerformed(evt);
            }
        });

        numbersOfGrainsTextField.setText("30");

        jLabel1.setText("Numbers of grains");

        jLabel2.setText("Amount of inclusions");

        jLabel3.setText("Size of inclusions");

        jLabel4.setText("Type of inclusion");

        amountOfInclusionsTextField.setText("6");

        sizeOfInclusionsTextField.setText("5");

        typeOfInclusionComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "circular", "square" }));
        typeOfInclusionComboBox.setToolTipText("");
        typeOfInclusionComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                typeOfInclusionComboBoxItemStateChanged(evt);
            }
        });

        addInclusionsButton.setText("Add inclusions");
        addInclusionsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addInclusionsButtonActionPerformed(evt);
            }
        });

        widthTextField.setText("200");

        jLabel5.setText("Width");

        jLabel6.setText("Height");

        heightTextField.setText("200");

        jLabel7.setText("Type of neighborhood");

        typeOfNeighborhoodComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "von Neumann", "Moore", "Moore 2" }));

        fileMenu.setText("File");

        microstructureMenu.setText("Microstructure");

        importMenuItem.setText("Import");
        importMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importMenuItemActionPerformed(evt);
            }
        });
        microstructureMenu.add(importMenuItem);

        exportMenuItem.setText("Export");
        exportMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportMenuItemActionPerformed(evt);
            }
        });
        microstructureMenu.add(exportMenuItem);

        fileMenu.add(microstructureMenu);

        jMenuBar1.add(fileMenu);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(276, 276, 276)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(22, 22, 22)
                                        .addComponent(widthTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4)
                                        .addComponent(addInclusionsButton)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel2)
                                        .addComponent(addGrainsButton)
                                        .addComponent(jLabel1)
                                        .addComponent(createButton)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 121, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(sizeOfInclusionsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(amountOfInclusionsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(18, 18, 18)
                                        .addComponent(heightTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(startButton, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(typeOfNeighborhoodComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(typeOfInclusionComboBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(numbersOfGrainsTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 801, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(widthTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(heightTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addComponent(createButton)
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(numbersOfGrainsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(addGrainsButton)
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(amountOfInclusionsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(sizeOfInclusionsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(typeOfInclusionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addComponent(addInclusionsButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(typeOfNeighborhoodComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(startButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void vonNeumann() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (matrix[i][j].id != -1) {
                    if (matrix[i][j].color == Color.BLACK) {
                        matrix2[i][j] = matrix[i][j];
                    } else if (i > 0 && i < width - 1 && j > 0 && j < height - 1) {
                        matrix2[i][j] = matrix[i][j];
                        if (matrix[i][j - 1].id == -1) {
                            matrix2[i][j - 1] = matrix[i][j];
                        }
                        if (matrix[i + 1][j].id == -1) {
                            matrix2[i + 1][j] = matrix[i][j];
                        }
                        if (matrix[i][j + 1].id == -1) {
                            matrix2[i][j + 1] = matrix[i][j];
                        }
                        if (matrix[i - 1][j].id == -1) {
                            matrix2[i - 1][j] = matrix[i][j];
                        }
                    } else if (i == 0 && i < width - 1 && j > 0 && j < height - 1) {
                        matrix2[i][j] = matrix[i][j];
                        if (matrix[i][j - 1].id == -1) {
                            matrix2[i][j - 1] = matrix[i][j];
                        }
                        if (matrix[i + 1][j].id == -1) {
                            matrix2[i + 1][j] = matrix[i][j];
                        }
                        if (matrix[i][j + 1].id == -1) {
                            matrix2[i][j + 1] = matrix[i][j];
                        }
                    } else if (i > 0 && i < width - 1 && j == 0 && j < height - 1) {
                        matrix2[i][j] = matrix[i][j];
                        if (matrix[i + 1][j].id == -1) {
                            matrix2[i + 1][j] = matrix[i][j];
                        }
                        if (matrix[i][j + 1].id == -1) {
                            matrix2[i][j + 1] = matrix[i][j];
                        }
                        if (matrix[i - 1][j].id == -1) {
                            matrix2[i - 1][j] = matrix[i][j];
                        }
                    } else if (i > 0 && i == width - 1 && j > 0 && j < height - 1) {
                        matrix2[i][j] = matrix[i][j];
                        if (matrix[i][j - 1].id == -1) {
                            matrix2[i][j - 1] = matrix[i][j];
                        }
                        if (matrix[i][j + 1].id == -1) {
                            matrix2[i][j + 1] = matrix[i][j];
                        }
                        if (matrix[i - 1][j].id == -1) {
                            matrix2[i - 1][j] = matrix[i][j];
                        }
                    } else if (i > 0 && i < width - 1 && j > 0 && j == height - 1) {
                        matrix2[i][j] = matrix[i][j];
                        if (matrix[i][j - 1].id == -1) {
                            matrix2[i][j - 1] = matrix[i][j];
                        }
                        if (matrix[i + 1][j].id == -1) {
                            matrix2[i + 1][j] = matrix[i][j];
                        }
                        if (matrix[i - 1][j].id == -1) {
                            matrix2[i - 1][j] = matrix[i][j];
                        }
                    } else if (i == 0 && j == 0) {
                        matrix2[i][j] = matrix[i][j];
                        if (matrix[i + 1][j].id == -1) {
                            matrix2[i + 1][j] = matrix[i][j];
                        }
                        if (matrix[i][j + 1].id == -1) {
                            matrix2[i][j + 1] = matrix[i][j];
                        }
                    } else if (i == width - 1 && j == 0) {
                        matrix2[i][j] = matrix[i][j];
                        if (matrix[i][j + 1].id == -1) {
                            matrix2[i][j + 1] = matrix[i][j];
                        }
                        if (matrix[i - 1][j].id == -1) {
                            matrix2[i - 1][j] = matrix[i][j];
                        }
                    } else if (i == 0 && j == height - 1) {
                        matrix2[i][j] = matrix[i][j];
                        if (matrix[i][j - 1].id == -1) {
                            matrix2[i][j - 1] = matrix[i][j];
                        }
                        if (matrix[i + 1][j].id == -1) {
                            matrix2[i + 1][j] = matrix[i][j];
                        }
                    } else if (i == width - 1 && j == height - 1) {
                        matrix2[i][j] = matrix[i][j];
                        if (matrix[i][j - 1].id == -1) {
                            matrix2[i][j - 1] = matrix[i][j];
                        }
                        if (matrix[i - 1][j].id == -1) {
                            matrix2[i - 1][j] = matrix[i][j];
                        }
                    }
                }
                if (matrix[i][j].id == -1) {
                    if (i == 0 && j == 0) {
                        matrix2[i][j] = matrix[i + 1][j + 1];
                    }
                    if (i == width - 1 && j == 0) {
                        matrix2[i][j] = matrix[i - 1][j + 1];
                    }
                    if (i == 0 && j == height - 1) {
                        matrix2[i][j] = matrix[i + 1][j - 1];
                    }
                    if (i == width - 1 && j == height - 1) {
                        matrix2[i][j] = matrix[i - 1][j - 1];
                    }
                }
            }
        }
        change(matrix, matrix2, width, height);
    }

    public void moore() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (matrix[i][j].id != -1) {
                    if (matrix[i][j].color == Color.BLACK) {
                        matrix2[i][j] = matrix[i][j];
                    } else if (i > 0 && i < width - 1 && j > 0 && j < height - 1) {
                        matrix2[i][j] = matrix[i][j];
                        if (matrix[i][j - 1].id == -1) {
                            matrix2[i][j - 1] = matrix[i][j];
                        }
                        if (matrix[i + 1][j - 1].id == -1) {
                            matrix2[i + 1][j - 1] = matrix[i][j];
                        }
                        if (matrix[i + 1][j].id == -1) {
                            matrix2[i + 1][j] = matrix[i][j];
                        }
                        if (matrix[i + 1][j + 1].id == -1) {
                            matrix2[i + 1][j + 1] = matrix[i][j];
                        }
                        if (matrix[i][j + 1].id == -1) {
                            matrix2[i][j + 1] = matrix[i][j];
                        }
                        if (matrix[i - 1][j + 1].id == -1) {
                            matrix2[i - 1][j + 1] = matrix[i][j];
                        }
                        if (matrix[i - 1][j].id == -1) {
                            matrix2[i - 1][j] = matrix[i][j];
                        }
                        if (matrix[i - 1][j - 1].id == -1) {
                            matrix2[i - 1][j - 1] = matrix[i][j];
                        }
                    } else if (i == 0 && i < width - 1 && j > 0 && j < height - 1) {
                        matrix2[i][j] = matrix[i][j];
                        if (matrix[i][j - 1].id == -1) {
                            matrix2[i][j - 1] = matrix[i][j];
                        }
                        if (matrix[i + 1][j - 1].id == -1) {
                            matrix2[i + 1][j - 1] = matrix[i][j];
                        }
                        if (matrix[i + 1][j].id == -1) {
                            matrix2[i + 1][j] = matrix[i][j];
                        }
                        if (matrix[i + 1][j + 1].id == -1) {
                            matrix2[i + 1][j + 1] = matrix[i][j];
                        }
                        if (matrix[i][j + 1].id == -1) {
                            matrix2[i][j + 1] = matrix[i][j];
                        }
                    } else if (i > 0 && i < width - 1 && j == 0 && j < height - 1) {
                        matrix2[i][j] = matrix[i][j];
                        if (matrix[i + 1][j].id == -1) {
                            matrix2[i + 1][j] = matrix[i][j];
                        }
                        if (matrix[i + 1][j + 1].id == -1) {
                            matrix2[i + 1][j + 1] = matrix[i][j];
                        }
                        if (matrix[i][j + 1].id == -1) {
                            matrix2[i][j + 1] = matrix[i][j];
                        }
                        if (matrix[i - 1][j + 1].id == -1) {
                            matrix2[i - 1][j + 1] = matrix[i][j];
                        }
                        if (matrix[i - 1][j].id == -1) {
                            matrix2[i - 1][j] = matrix[i][j];
                        }
                    } else if (i > 0 && i == width - 1 && j > 0 && j < height - 1) {
                        matrix2[i][j] = matrix[i][j];
                        if (matrix[i][j - 1].id == -1) {
                            matrix2[i][j - 1] = matrix[i][j];
                        }
                        if (matrix[i - 1][j - 1].id == -1) {
                            matrix2[i - 1][j - 1] = matrix[i][j];
                        }
                        if (matrix[i][j + 1].id == -1) {
                            matrix2[i][j + 1] = matrix[i][j];
                        }
                        if (matrix[i - 1][j + 1].id == -1) {
                            matrix2[i - 1][j + 1] = matrix[i][j];
                        }
                        if (matrix[i - 1][j].id == -1) {
                            matrix2[i - 1][j] = matrix[i][j];
                        }
                    } else if (i > 0 && i < width - 1 && j > 0 && j == height - 1) {
                        matrix2[i][j] = matrix[i][j];
                        if (matrix[i][j - 1].id == -1) {
                            matrix2[i][j - 1] = matrix[i][j];
                        }
                        if (matrix[i + 1][j - 1].id == -1) {
                            matrix2[i + 1][j - 1] = matrix[i][j];
                        }
                        if (matrix[i + 1][j].id == -1) {
                            matrix2[i + 1][j] = matrix[i][j];
                        }
                        if (matrix[i - 1][j].id == -1) {
                            matrix2[i - 1][j] = matrix[i][j];
                        }
                        if (matrix[i - 1][j - 1].id == -1) {
                            matrix2[i - 1][j - 1] = matrix[i][j];
                        }
                    } else if (i == 0 && j == 0) {
                        matrix2[i][j] = matrix[i][j];
                        if (matrix[i + 1][j].id == -1) {
                            matrix2[i + 1][j] = matrix[i][j];
                        }
                        if (matrix[i + 1][j + 1].id == -1) {
                            matrix2[i + 1][j + 1] = matrix[i][j];
                        }
                        if (matrix[i][j + 1].id == -1) {
                            matrix2[i][j + 1] = matrix[i][j];
                        }
                    } else if (i == width - 1 && j == 0) {
                        matrix2[i][j] = matrix[i][j];
                        if (matrix[i][j + 1].id == -1) {
                            matrix2[i][j + 1] = matrix[i][j];
                        }
                        if (matrix[i - 1][j + 1].id == -1) {
                            matrix2[i - 1][j + 1] = matrix[i][j];
                        }
                        if (matrix[i - 1][j].id == -1) {
                            matrix2[i - 1][j] = matrix[i][j];
                        }
                    } else if (i == 0 && j == height - 1) {
                        matrix2[i][j] = matrix[i][j];
                        if (matrix[i][j - 1].id == -1) {
                            matrix2[i][j - 1] = matrix[i][j];
                        }
                        if (matrix[i + 1][j - 1].id == -1) {
                            matrix2[i + 1][j - 1] = matrix[i][j];
                        }
                        if (matrix[i + 1][j].id == -1) {
                            matrix2[i + 1][j] = matrix[i][j];
                        }
                    } else if (i == width - 1 && j == height - 1) {
                        matrix2[i][j] = matrix[i][j];
                        if (matrix[i][j - 1].id == -1) {
                            matrix2[i][j - 1] = matrix[i][j];
                        }
                        if (matrix[i - 1][j - 1].id == -1) {
                            matrix2[i - 1][j - 1] = matrix[i][j];
                        }
                        if (matrix[i - 1][j].id == -1) {
                            matrix2[i - 1][j] = matrix[i][j];
                        }
                    }
                }
                if (matrix[i][j].id == -1) {
                    if (i == 0 && j == 0) {
                        matrix2[i][j] = matrix[i + 1][j + 1];
                    }
                    if (i == width - 1 && j == 0) {
                        matrix2[i][j] = matrix[i - 1][j + 1];
                    }
                    if (i == 0 && j == height - 1) {
                        matrix2[i][j] = matrix[i + 1][j - 1];
                    }
                    if (i == width - 1 && j == height - 1) {
                        matrix2[i][j] = matrix[i - 1][j - 1];
                    }
                }
            }
        }
        change(matrix, matrix2, width, height);
    }

    public void moore2() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (matrix[i][j].color == Color.BLACK) {
                        matrix2[i][j] = matrix[i][j];
                    }
                if (matrix[i][j].id == -1) {
                    neighbours = new HashMap<>();
                    if (matrix[i][j].color == Color.BLACK) {
                        matrix2[i][j] = matrix[i][j];
                    } else if (i > 0 && i < width - 1 && j > 0 && j < height - 1) {
                        // matrix2[i][j] = matrix[i][j];
                        if (matrix[i][j - 1].id != -1 && matrix[i][j - 1].id != -16777216) {
                            if (neighbours.get(matrix[i][j - 1].id) == null) {
                                neighbours.put(matrix[i][j - 1].id, 0);
                            }
                            neighbours.put(matrix[i][j - 1].id, neighbours.get(matrix[i][j - 1].id) + 1);
                        }
                        if (matrix[i + 1][j - 1].id != -1 && matrix[i + 1][j - 1].id != -16777216) {
                            if (neighbours.get(matrix[i + 1][j - 1].id) == null) {
                                neighbours.put(matrix[i + 1][j - 1].id, 0);
                            }
                            neighbours.put(matrix[i + 1][j - 1].id, neighbours.get(matrix[i + 1][j - 1].id) + 1);
                        }
                        if (matrix[i + 1][j].id != -1 && matrix[i + 1][j].id != -16777216) {
                            if (neighbours.get(matrix[i + 1][j].id) == null) {
                                neighbours.put(matrix[i + 1][j].id, 0);
                            }
                            neighbours.put(matrix[i + 1][j].id, neighbours.get(matrix[i + 1][j].id) + 1);
                        }
                        if (matrix[i + 1][j + 1].id != -1 && matrix[i + 1][j + 1].id != -16777216) {
                            if (neighbours.get(matrix[i + 1][j + 1].id) == null) {
                                neighbours.put(matrix[i + 1][j + 1].id, 0);
                            }
                            neighbours.put(matrix[i + 1][j + 1].id, neighbours.get(matrix[i + 1][j + 1].id) + 1);
                        }
                        if (matrix[i][j + 1].id != -1 && matrix[i][j + 1].id != -16777216) {
                            if (neighbours.get(matrix[i][j + 1].id) == null) {
                                neighbours.put(matrix[i][j + 1].id, 0);
                            }
                            neighbours.put(matrix[i][j + 1].id, neighbours.get(matrix[i][j + 1].id) + 1);
                        }
                        if (matrix[i - 1][j + 1].id != -1 && matrix[i - 1][j + 1].id != -16777216) {
                            if (neighbours.get(matrix[i - 1][j + 1].id) == null) {
                                neighbours.put(matrix[i - 1][j + 1].id, 0);
                            }
                            neighbours.put(matrix[i - 1][j + 1].id, neighbours.get(matrix[i - 1][j + 1].id) + 1);
                        }
                        if (matrix[i - 1][j].id != -1 && matrix[i - 1][j].id != -16777216) {
                            if (neighbours.get(matrix[i - 1][j].id) == null) {
                                neighbours.put(matrix[i - 1][j].id, 0);
                            }
                            neighbours.put(matrix[i - 1][j].id, neighbours.get(matrix[i - 1][j].id) + 1);
                        }
                        if (matrix[i - 1][j - 1].id != -1 && matrix[i - 1][j - 1].id != -16777216) {
                            if (neighbours.get(matrix[i - 1][j - 1].id) == null) {
                                neighbours.put(matrix[i - 1][j - 1].id, 0);
                            }
                            neighbours.put(matrix[i - 1][j - 1].id, neighbours.get(matrix[i - 1][j - 1].id) + 1);
                        }
                    } else if (i == 0 && i < width - 1 && j > 0 && j < height - 1) {
                        // matrix2[i][j] = matrix[i][j];
                        if (matrix[i][j - 1].id != -1 && matrix[i][j - 1].id != -16777216) {
                            if (neighbours.get(matrix[i][j - 1].id) == null) {
                                neighbours.put(matrix[i][j - 1].id, 0);
                            }
                            neighbours.put(matrix[i][j - 1].id, neighbours.get(matrix[i][j - 1].id) + 1);
                        }
                        if (matrix[i + 1][j - 1].id != -1 && matrix[i + 1][j - 1].id != -16777216) {
                            if (neighbours.get(matrix[i + 1][j - 1].id) == null) {
                                neighbours.put(matrix[i + 1][j - 1].id, 0);
                            }
                            neighbours.put(matrix[i + 1][j - 1].id, neighbours.get(matrix[i + 1][j - 1].id) + 1);
                        }
                        if (matrix[i + 1][j].id != -1 && matrix[i + 1][j].id != -16777216) {
                            if (neighbours.get(matrix[i + 1][j].id) == null) {
                                neighbours.put(matrix[i + 1][j].id, 0);
                            }
                            neighbours.put(matrix[i + 1][j].id, neighbours.get(matrix[i + 1][j].id) + 1);
                        }
                        if (matrix[i + 1][j + 1].id != -1 && matrix[i + 1][j + 1].id != -16777216) {
                            if (neighbours.get(matrix[i + 1][j + 1].id) == null) {
                                neighbours.put(matrix[i + 1][j + 1].id, 0);
                            }
                            neighbours.put(matrix[i + 1][j + 1].id, neighbours.get(matrix[i + 1][j + 1].id) + 1);
                        }
                        if (matrix[i][j + 1].id != -1 && matrix[i][j + 1].id != -16777216) {
                            if (neighbours.get(matrix[i][j + 1].id) == null) {
                                neighbours.put(matrix[i][j + 1].id, 0);
                            }
                            neighbours.put(matrix[i][j + 1].id, neighbours.get(matrix[i][j + 1].id) + 1);
                        }
                    } else if (i > 0 && i < width - 1 && j == 0 && j < height - 1) {
                        //matrix2[i][j] = matrix[i][j];
                        if (matrix[i + 1][j].id != -1 && matrix[i + 1][j].id != -16777216) {
                            if (neighbours.get(matrix[i + 1][j].id) == null) {
                                neighbours.put(matrix[i + 1][j].id, 0);
                            }
                            neighbours.put(matrix[i + 1][j].id, neighbours.get(matrix[i + 1][j].id) + 1);
                        }
                        if (matrix[i + 1][j + 1].id != -1 && matrix[i + 1][j + 1].id != -16777216) {
                            if (neighbours.get(matrix[i + 1][j + 1].id) == null) {
                                neighbours.put(matrix[i + 1][j + 1].id, 0);
                            }
                            neighbours.put(matrix[i + 1][j + 1].id, neighbours.get(matrix[i + 1][j + 1].id) + 1);
                        }
                        if (matrix[i][j + 1].id != -1 && matrix[i][j + 1].id != -16777216) {
                            if (neighbours.get(matrix[i][j + 1].id) == null) {
                                neighbours.put(matrix[i][j + 1].id, 0);
                            }
                            neighbours.put(matrix[i][j + 1].id, neighbours.get(matrix[i][j + 1].id) + 1);
                        }
                        if (matrix[i - 1][j + 1].id != -1 && matrix[i - 1][j + 1].id != -16777216) {
                            if (neighbours.get(matrix[i - 1][j + 1].id) == null) {
                                neighbours.put(matrix[i - 1][j + 1].id, 0);
                            }
                            neighbours.put(matrix[i - 1][j + 1].id, neighbours.get(matrix[i - 1][j + 1].id) + 1);
                        }
                        if (matrix[i - 1][j].id != -1 && matrix[i - 1][j].id != -16777216) {
                            if (neighbours.get(matrix[i - 1][j].id) == null) {
                                neighbours.put(matrix[i - 1][j].id, 0);
                            }
                            neighbours.put(matrix[i - 1][j].id, neighbours.get(matrix[i - 1][j].id) + 1);
                        }
                    } else if (i > 0 && i == width - 1 && j > 0 && j < height - 1) {
                        //matrix2[i][j] = matrix[i][j];
                        if (matrix[i][j - 1].id != -1 && matrix[i][j - 1].id != -16777216) {
                            if (neighbours.get(matrix[i][j - 1].id) == null) {
                                neighbours.put(matrix[i][j - 1].id, 0);
                            }
                            neighbours.put(matrix[i][j - 1].id, neighbours.get(matrix[i][j - 1].id) + 1);
                        }
                        if (matrix[i - 1][j - 1].id != -1 && matrix[i - 1][j - 1].id != -16777216) {
                            if (neighbours.get(matrix[i - 1][j - 1].id) == null) {
                                neighbours.put(matrix[i - 1][j - 1].id, 0);
                            }
                            neighbours.put(matrix[i - 1][j - 1].id, neighbours.get(matrix[i - 1][j - 1].id) + 1);
                        }
                        if (matrix[i][j + 1].id != -1 && matrix[i][j + 1].id != -16777216) {
                            if (neighbours.get(matrix[i][j + 1].id) == null) {
                                neighbours.put(matrix[i][j + 1].id, 0);
                            }
                            neighbours.put(matrix[i][j + 1].id, neighbours.get(matrix[i][j + 1].id) + 1);
                        }
                        if (matrix[i - 1][j + 1].id != -1 && matrix[i - 1][j + 1].id != -16777216) {
                            if (neighbours.get(matrix[i - 1][j + 1].id) == null) {
                                neighbours.put(matrix[i - 1][j + 1].id, 0);
                            }
                            neighbours.put(matrix[i - 1][j + 1].id, neighbours.get(matrix[i - 1][j + 1].id) + 1);
                        }
                        if (matrix[i - 1][j].id != -1 && matrix[i - 1][j].id != -16777216) {
                            if (neighbours.get(matrix[i - 1][j].id) == null) {
                                neighbours.put(matrix[i - 1][j].id, 0);
                            }
                            neighbours.put(matrix[i - 1][j].id, neighbours.get(matrix[i - 1][j].id) + 1);
                        }
                    } else if (i > 0 && i < width - 1 && j > 0 && j == height - 1) {
                        // matrix2[i][j] = matrix[i][j];
                        if (matrix[i][j - 1].id != -1 && matrix[i][j - 1].id != -16777216) {
                            if (neighbours.get(matrix[i][j - 1].id) == null) {
                                neighbours.put(matrix[i][j - 1].id, 0);
                            }
                            neighbours.put(matrix[i][j - 1].id, neighbours.get(matrix[i][j - 1].id) + 1);
                        }
                        if (matrix[i + 1][j - 1].id != -1 && matrix[i + 1][j - 1].id != -16777216) {
                            if (neighbours.get(matrix[i + 1][j - 1].id) == null) {
                                neighbours.put(matrix[i + 1][j - 1].id, 0);
                            }
                            neighbours.put(matrix[i + 1][j - 1].id, neighbours.get(matrix[i + 1][j - 1].id) + 1);
                        }
                        if (matrix[i + 1][j].id != -1 && matrix[i + 1][j].id != -16777216) {
                            if (neighbours.get(matrix[i + 1][j].id) == null) {
                                neighbours.put(matrix[i + 1][j].id, 0);
                            }
                            neighbours.put(matrix[i + 1][j].id, neighbours.get(matrix[i + 1][j].id) + 1);
                        }
                        if (matrix[i - 1][j].id != -1 && matrix[i - 1][j].id != -16777216) {
                            if (neighbours.get(matrix[i - 1][j].id) == null) {
                                neighbours.put(matrix[i - 1][j].id, 0);
                            }
                            neighbours.put(matrix[i - 1][j].id, neighbours.get(matrix[i - 1][j].id) + 1);
                        }
                        if (matrix[i - 1][j - 1].id != -1 && matrix[i - 1][j - 1].id != -16777216) {
                            if (neighbours.get(matrix[i - 1][j - 1].id) == null) {
                                neighbours.put(matrix[i - 1][j - 1].id, 0);
                            }
                            neighbours.put(matrix[i - 1][j - 1].id, neighbours.get(matrix[i - 1][j - 1].id) + 1);
                        }
                    } else if (i == 0 && j == 0) {
                        // matrix2[i][j] = matrix[i][j];
                        if (matrix[i + 1][j].id != -1 && matrix[i + 1][j].id != -16777216) {
                            if (neighbours.get(matrix[i + 1][j].id) == null) {
                                neighbours.put(matrix[i + 1][j].id, 0);
                            }
                            neighbours.put(matrix[i + 1][j].id, neighbours.get(matrix[i + 1][j].id) + 1);
                        }
                        if (matrix[i + 1][j + 1].id != -1 && matrix[i + 1][j + 1].id != -16777216) {
                            if (neighbours.get(matrix[i + 1][j + 1].id) == null) {
                                neighbours.put(matrix[i + 1][j + 1].id, 0);
                            }
                            neighbours.put(matrix[i + 1][j + 1].id, neighbours.get(matrix[i + 1][j + 1].id) + 1);
                        }
                        if (matrix[i][j + 1].id != -1 && matrix[i][j + 1].id != -16777216) {
                            if (neighbours.get(matrix[i][j + 1].id) == null) {
                                neighbours.put(matrix[i][j + 1].id, 0);
                            }
                            neighbours.put(matrix[i][j + 1].id, neighbours.get(matrix[i][j + 1].id) + 1);
                        }
                    } else if (i == width - 1 && j == 0) {
                        //matrix2[i][j] = matrix[i][j];
                        if (matrix[i][j + 1].id != -1 && matrix[i][j + 1].id != -16777216) {
                            if (neighbours.get(matrix[i][j + 1].id) == null) {
                                neighbours.put(matrix[i][j + 1].id, 0);
                            }
                            neighbours.put(matrix[i][j + 1].id, neighbours.get(matrix[i][j + 1].id) + 1);
                        }
                        if (matrix[i - 1][j + 1].id != -1 && matrix[i - 1][j + 1].id != -16777216) {
                            if (neighbours.get(matrix[i - 1][j + 1].id) == null) {
                                neighbours.put(matrix[i - 1][j + 1].id, 0);
                            }
                            neighbours.put(matrix[i - 1][j + 1].id, neighbours.get(matrix[i - 1][j + 1].id) + 1);
                        }
                        if (matrix[i - 1][j].id != -1 && matrix[i - 1][j].id != -16777216) {
                            if (neighbours.get(matrix[i - 1][j].id) == null) {
                                neighbours.put(matrix[i - 1][j].id, 0);
                            }
                            neighbours.put(matrix[i - 1][j].id, neighbours.get(matrix[i - 1][j].id) + 1);
                        }
                    } else if (i == 0 && j == height - 1) {
                        //matrix2[i][j] = matrix[i][j];
                        if (matrix[i][j - 1].id != -1 && matrix[i][j - 1].id != -16777216) {
                            if (neighbours.get(matrix[i][j - 1].id) == null) {
                                neighbours.put(matrix[i][j - 1].id, 0);
                            }
                            neighbours.put(matrix[i][j - 1].id, neighbours.get(matrix[i][j - 1].id) + 1);
                        }
                        if (matrix[i + 1][j - 1].id != -1 && matrix[i + 1][j - 1].id != -16777216) {
                            if (neighbours.get(matrix[i + 1][j - 1].id) == null) {
                                neighbours.put(matrix[i + 1][j - 1].id, 0);
                            }
                            neighbours.put(matrix[i + 1][j - 1].id, neighbours.get(matrix[i + 1][j - 1].id) + 1);
                        }
                        if (matrix[i + 1][j].id != -1 && matrix[i + 1][j].id != -16777216) {
                            if (neighbours.get(matrix[i + 1][j].id) == null) {
                                neighbours.put(matrix[i + 1][j].id, 0);
                            }
                            neighbours.put(matrix[i + 1][j].id, neighbours.get(matrix[i + 1][j].id) + 1);
                        }
                    } else if (i == width - 1 && j == height - 1) {
                        // matrix2[i][j] = matrix[i][j];
                        if (matrix[i][j - 1].id != -1 && matrix[i][j - 1].id != -16777216) {
                            if (neighbours.get(matrix[i][j - 1].id) == null) {
                                neighbours.put(matrix[i][j - 1].id, 0);
                            }
                            neighbours.put(matrix[i][j - 1].id, neighbours.get(matrix[i][j - 1].id) + 1);
                        }
                        if (matrix[i - 1][j - 1].id != -1 && matrix[i - 1][j - 1].id != -16777216) {
                            if (neighbours.get(matrix[i - 1][j - 1].id) == null) {
                                neighbours.put(matrix[i - 1][j - 1].id, 0);
                            }
                            neighbours.put(matrix[i - 1][j - 1].id, neighbours.get(matrix[i - 1][j - 1].id) + 1);
                        }
                        if (matrix[i - 1][j].id != -1 && matrix[i - 1][j].id != -16777216) {
                            if (neighbours.get(matrix[i - 1][j].id) == null) {
                                neighbours.put(matrix[i - 1][j].id, 0);
                            }
                            neighbours.put(matrix[i - 1][j].id, neighbours.get(matrix[i - 1][j].id) + 1);
                        }
                    }
                    
                    if (!neighbours.isEmpty()) {
                        //if (neighbours.get(matrix[i][j].id) != null && neighbours.get(matrix[i][j].id) == -16777216) neighbours.remove(matrix[i][j].id);
                        mostFrequent = new ArrayList<>();
                        Map.Entry<Integer, Integer> maxEntry = null;

                        for (Map.Entry<Integer, Integer> entry : neighbours.entrySet()) {
                            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) >= 0) {
                                maxEntry = entry;
                                mostFrequent.add(maxEntry);

                            }
                        }
                        if (!mostFrequent.isEmpty()) {
                            Random random = new Random();

                            int temp = Math.abs(random.nextInt()) % mostFrequent.size();
                            matrix2[i][j].id = mostFrequent.get(temp).getKey();
                            matrix2[i][j].color = new Color(matrix2[i][j].id);
                        }
                    }

                }

                /* if (matrix[i][j].id == -1) {
                    if (i == 0 && j == 0) {
                        matrix2[i][j] = matrix[i + 1][j + 1];
                    }
                    if (i == width - 1 && j == 0) {
                        matrix2[i][j] = matrix[i - 1][j + 1];
                    }
                    if (i == 0 && j == height - 1) {
                        matrix2[i][j] = matrix[i + 1][j - 1];
                    }
                    if (i == width - 1 && j == height - 1) {
                        matrix2[i][j] = matrix[i - 1][j - 1];
                    }
                }*/
            }
        }
        change(matrix, matrix2, width, height);

    }

    public boolean isFullMatrix() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (matrix[i][j].id == -1) {
                    return false;
                }
            }
        }
        play = !play;
        startButton.setText("Finished");
        startButton.setEnabled(false);
        addGrainsButton.setEnabled(false);
        addInclusionsButton.setEnabled(true);
        return true;
    }

    public void change(Cell[][] a, Cell[][] b, int width, int height) {

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                a[i][j].color = b[i][j].color;
                a[i][j].id = b[i][j].id;
            }
        }
    }

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked

        int i = width * evt.getX() / jPanel1.getWidth();
        int j = height * evt.getY() / jPanel1.getHeight();

        if (matrix[i][j].id == -1) {
            matrix[i][j].color = Cell.randomColor();
            matrix[i][j].id = Cell.idColor(matrix[i][j].color);

        } else if (matrix[i][j].id != -1) {
            matrix[i][j].id = -1;
        }

        refresh();
    }//GEN-LAST:event_jPanel1MouseClicked

    private void jPanel1ComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel1ComponentResized

        //offScrImg = createImage(jPanel1.getWidth(), jPanel1.getHeight());
        //offScrGraph = offScrImg.getGraphics();
        offScrImg = new BufferedImage(jPanel1.getWidth(), jPanel1.getHeight(), BufferedImage.TYPE_INT_RGB);
        offScrGraph = offScrImg.createGraphics();
        refresh();
    }//GEN-LAST:event_jPanel1ComponentResized

    private void createButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createButtonActionPerformed

        width = Integer.parseInt(widthTextField.getText());
        height = Integer.parseInt(heightTextField.getText());
        matrix = new Cell[width][height];
        matrix2 = new Cell[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                matrix[i][j] = new Cell();
                matrix2[i][j] = new Cell();
            }
        }
        refresh();
        startButton.setText("Start");
        startButton.setEnabled(true);
        addGrainsButton.setEnabled(true);
        //jProgressBar1.setStringPainted(true);
        jProgressBar1.setValue(0);
        jProgressBar1.setString(0 + "% Complete");
    }//GEN-LAST:event_createButtonActionPerformed

    private void addGrainsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addGrainsButtonActionPerformed

        Random random = new Random();
        randomX = 0;
        randomY = 0;
        grains = Integer.parseInt(numbersOfGrainsTextField.getText());
        while (grains > 0) {
            randomX = Math.abs(random.nextInt()) % width;
            randomY = Math.abs(random.nextInt()) % height;

            if (matrix[randomX][randomY].id == -1) {
                matrix[randomX][randomY].color = Cell.randomColor();
                matrix[randomX][randomY].id = Cell.idColor(matrix[randomX][randomY].color);
                grains--;
            }

        }
        refresh();
    }//GEN-LAST:event_addGrainsButtonActionPerformed

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed

        play = !play;
        if (play) {
            startButton.setText("Pause");
            addInclusionsButton.setEnabled(false);

        } else {
            startButton.setText("Resume");
        }
    }//GEN-LAST:event_startButtonActionPerformed

    private void exportMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportMenuItemActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            boolean isTxt = file.getName().endsWith(".txt");
            if (isTxt) {
                try {
                    PrintWriter printWriter = new PrintWriter(new FileWriter(file));
                    //printWriter.println(width + " " + height);
                    for (int i = 0; i < width; i++) {
                        for (int j = 0; j < height; j++) {
                            printWriter.println(i + " " + j + " " + matrix[i][j].id);
                        }
                    }
                    printWriter.close();
                } catch (IOException e) {
                    System.out.println(e);
                }
            } else {
                try {
                    ImageIO.write(offScrImg, "png", file);
                } catch (IOException ex) {
                    java.util.logging.Logger.getLogger(MultiscaleModellingFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_exportMenuItemActionPerformed

    private void importMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importMenuItemActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            boolean isTxt = file.getName().endsWith(".txt");
            if (isTxt) {
                BufferedReader bufferReader = null;
                try {
                    bufferReader = new BufferedReader(new FileReader(file));
                    String line = null;
                    String splited = null;
                    for (int i = 0; i < width; i++) {
                        for (int j = 0; j < height; j++) {
                            // f0.println(i + " " + j + " " + matrix[i][j].id);
                            line = bufferReader.readLine();
                            splited = line;
                            String[] splitedArray = null;
                            splitedArray = splited.split(" ");
                            matrix[i][j].id = Integer.parseInt(splitedArray[2]);
                            matrix[i][j].color = new Color(matrix[i][j].id);
                            if (matrix[i][j].id == -16777216) {
                                matrix[i][j].color = Color.BLACK;
                            }
                        }
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (bufferReader != null) {
                            bufferReader.close();
                            refresh();
                        }
                    } catch (IOException e) {
                    }
                }
            } else {
                try {
                    offScrImg = ImageIO.read(file);
                    for (int i = 0; i < width; i++) {
                        for (int j = 0; j < height; j++) {

                            matrix[i][j].id = offScrImg.getRGB(i * (jPanel1.getWidth() / width), j * (jPanel1.getHeight() / height));
                            matrix[i][j].color = new Color(matrix[i][j].id);
                            if (matrix[i][j].id == -16777216) {
                                matrix[i][j].color = Color.BLACK;
                            }
                        }
                    }
                    refresh();
                } catch (IOException ex) {
                    java.util.logging.Logger.getLogger(MultiscaleModellingFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_importMenuItemActionPerformed

    private void addInclusionsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addInclusionsButtonActionPerformed
        // TODO add your handling code here:

        Random random = new Random();
        randomX = 0;
        randomY = 0;
        inclusions = Integer.parseInt(amountOfInclusionsTextField.getText());
        sizeOfInclusions = Integer.parseInt(sizeOfInclusionsTextField.getText());
        if (startButton.getText().equals("Start")) {
            if (typeOfInclusionComboBox.getSelectedItem().toString().equals("circular")) {
                while (inclusions > 0) {
                    randomX = Math.abs(random.nextInt()) % width;
                    randomY = Math.abs(random.nextInt()) % height;

                    if (randomX > sizeOfInclusions && randomX < width - sizeOfInclusions
                            && randomY > sizeOfInclusions && randomY < height - sizeOfInclusions) {
                        if (matrix[randomX + sizeOfInclusions][randomY + sizeOfInclusions].color != Color.BLACK
                                && matrix[randomX + sizeOfInclusions][randomY].color != Color.BLACK
                                && matrix[randomX][randomY + sizeOfInclusions].color != Color.BLACK
                                && matrix[randomX + sizeOfInclusions][randomY - sizeOfInclusions].color != Color.BLACK
                                && matrix[randomX - sizeOfInclusions][randomY + sizeOfInclusions].color != Color.BLACK
                                && matrix[randomX - sizeOfInclusions][randomY].color != Color.BLACK
                                && matrix[randomX][randomY - sizeOfInclusions].color != Color.BLACK
                                && matrix[randomX - sizeOfInclusions][randomY - sizeOfInclusions].color != Color.BLACK) {
                            int x = sizeOfInclusions;
                            int y = 0;
                            int xChange = 1 - (sizeOfInclusions << 1);
                            int yChange = 0;
                            int radiusError = 0;

                            while (x >= y) {
                                for (int i = randomX - x; i <= randomX + x; i++) {
                                    matrix[i][randomY + y].color = Color.BLACK;
                                    matrix[i][randomY + y].id = Cell.idColor(matrix[i][randomY + y].color);
                                    matrix[i][randomY - y].color = Color.BLACK;
                                    matrix[i][randomY - y].id = Cell.idColor(matrix[i][randomY - y].color);
                                }
                                for (int i = randomX - y; i <= randomX + y; i++) {
                                    matrix[i][randomY + x].color = Color.BLACK;
                                    matrix[i][randomY + x].id = Cell.idColor(matrix[i][randomY + x].color);
                                    matrix[i][randomY - x].color = Color.BLACK;
                                    matrix[i][randomY - x].id = Cell.idColor(matrix[i][randomY - x].color);
                                }

                                y++;
                                radiusError += yChange;
                                yChange += 2;
                                if (((radiusError << 1) + xChange) > 0) {
                                    x--;
                                    radiusError += xChange;
                                    xChange += 2;
                                }
                            }
                            inclusions--;
                        }
                    }
                }
            }
            if (typeOfInclusionComboBox.getSelectedItem().toString().equals("square")) {
                while (inclusions > 0) {
                    randomX = Math.abs(random.nextInt()) % width;
                    randomY = Math.abs(random.nextInt()) % height;

                    if (randomX > sizeOfInclusions && randomX < width - sizeOfInclusions
                            && randomY > sizeOfInclusions && randomY < height - sizeOfInclusions) {
                        if (matrix[randomX + sizeOfInclusions][randomY + sizeOfInclusions].color != Color.BLACK
                                && matrix[randomX + sizeOfInclusions][randomY].color != Color.BLACK
                                && matrix[randomX][randomY + sizeOfInclusions].color != Color.BLACK
                                && matrix[randomX + sizeOfInclusions][randomY - sizeOfInclusions].color != Color.BLACK
                                && matrix[randomX - sizeOfInclusions][randomY + sizeOfInclusions].color != Color.BLACK
                                && matrix[randomX - sizeOfInclusions][randomY].color != Color.BLACK
                                && matrix[randomX][randomY - sizeOfInclusions].color != Color.BLACK
                                && matrix[randomX - sizeOfInclusions][randomY - sizeOfInclusions].color != Color.BLACK) {
                            int a = (int) (sizeOfInclusions / Math.sqrt(2));
                            for (int i = randomX - (a / 2); i < randomX + (a / 2); i++) {
                                for (int j = randomY - (a / 2); j < randomY + (a / 2); j++) {
                                    matrix[i][j].color = Color.BLACK;
                                    matrix[i][j].id = Cell.idColor(matrix[i][j].color);
                                }
                            }
                            inclusions--;
                        }
                    }
                }
            }
            refresh();
        } else if (startButton.getText().equals("Finished")) {
            if (typeOfInclusionComboBox.getSelectedItem().toString().equals("circular")) {
                while (inclusions > 0) {
                    randomX = Math.abs(random.nextInt()) % width;
                    randomY = Math.abs(random.nextInt()) % height;

                    if (randomX > sizeOfInclusions && randomX < width - sizeOfInclusions
                            && randomY > sizeOfInclusions && randomY < height - sizeOfInclusions) {
                        if ((matrix[randomX][randomY].id != matrix[randomX + 1][randomY + 1].id
                                || matrix[randomX][randomY].id != matrix[randomX + 1][randomY].id
                                || matrix[randomX][randomY].id != matrix[randomX][randomY + 1].id
                                || matrix[randomX][randomY].id != matrix[randomX + 1][randomY - 1].id
                                || matrix[randomX][randomY].id != matrix[randomX - 1][randomY + 1].id
                                || matrix[randomX][randomY].id != matrix[randomX - 1][randomY].id
                                || matrix[randomX][randomY].id != matrix[randomX][randomY - 1].id
                                || matrix[randomX][randomY].id != matrix[randomX - 1][randomY - 1].id)
                                && matrix[randomX + sizeOfInclusions][randomY + sizeOfInclusions].color != Color.BLACK
                                && matrix[randomX + sizeOfInclusions][randomY].color != Color.BLACK
                                && matrix[randomX][randomY + sizeOfInclusions].color != Color.BLACK
                                && matrix[randomX + sizeOfInclusions][randomY - sizeOfInclusions].color != Color.BLACK
                                && matrix[randomX - sizeOfInclusions][randomY + sizeOfInclusions].color != Color.BLACK
                                && matrix[randomX - sizeOfInclusions][randomY].color != Color.BLACK
                                && matrix[randomX][randomY - sizeOfInclusions].color != Color.BLACK
                                && matrix[randomX - sizeOfInclusions][randomY - sizeOfInclusions].color != Color.BLACK) {

                            int x = sizeOfInclusions;
                            int y = 0;
                            int xChange = 1 - (sizeOfInclusions << 1);
                            int yChange = 0;
                            int radiusError = 0;

                            while (x >= y) {
                                for (int i = randomX - x; i <= randomX + x; i++) {
                                    matrix[i][randomY + y].color = Color.BLACK;
                                    matrix[i][randomY + y].id = Cell.idColor(matrix[i][randomY + y].color);
                                    matrix[i][randomY - y].color = Color.BLACK;
                                    matrix[i][randomY - y].id = Cell.idColor(matrix[i][randomY - y].color);
                                }
                                for (int i = randomX - y; i <= randomX + y; i++) {
                                    matrix[i][randomY + x].color = Color.BLACK;
                                    matrix[i][randomY + x].id = Cell.idColor(matrix[i][randomY + x].color);
                                    matrix[i][randomY - x].color = Color.BLACK;
                                    matrix[i][randomY - x].id = Cell.idColor(matrix[i][randomY - x].color);
                                }
                                y++;
                                radiusError += yChange;
                                yChange += 2;
                                if (((radiusError << 1) + xChange) > 0) {
                                    x--;
                                    radiusError += xChange;
                                    xChange += 2;
                                }
                            }
                            inclusions--;
                        }
                    }
                }
                refresh();
            }
            if (typeOfInclusionComboBox.getSelectedItem().toString().equals("square")) {
                while (inclusions > 0) {
                    randomX = Math.abs(random.nextInt()) % width;
                    randomY = Math.abs(random.nextInt()) % height;

                    if (randomX > sizeOfInclusions && randomX < width - sizeOfInclusions
                            && randomY > sizeOfInclusions && randomY < height - sizeOfInclusions) {
                        if ((matrix[randomX][randomY].id != matrix[randomX + 1][randomY + 1].id
                                || matrix[randomX][randomY].id != matrix[randomX + 1][randomY].id
                                || matrix[randomX][randomY].id != matrix[randomX][randomY + 1].id
                                || matrix[randomX][randomY].id != matrix[randomX + 1][randomY - 1].id
                                || matrix[randomX][randomY].id != matrix[randomX - 1][randomY + 1].id
                                || matrix[randomX][randomY].id != matrix[randomX - 1][randomY].id
                                || matrix[randomX][randomY].id != matrix[randomX][randomY - 1].id
                                || matrix[randomX][randomY].id != matrix[randomX - 1][randomY - 1].id)
                                && matrix[randomX + sizeOfInclusions][randomY + sizeOfInclusions].color != Color.BLACK
                                && matrix[randomX + sizeOfInclusions][randomY].color != Color.BLACK
                                && matrix[randomX][randomY + sizeOfInclusions].color != Color.BLACK
                                && matrix[randomX + sizeOfInclusions][randomY - sizeOfInclusions].color != Color.BLACK
                                && matrix[randomX - sizeOfInclusions][randomY + sizeOfInclusions].color != Color.BLACK
                                && matrix[randomX - sizeOfInclusions][randomY].color != Color.BLACK
                                && matrix[randomX][randomY - sizeOfInclusions].color != Color.BLACK
                                && matrix[randomX - sizeOfInclusions][randomY - sizeOfInclusions].color != Color.BLACK) {

                            int a = (int) (sizeOfInclusions / Math.sqrt(2));
                            for (int i = randomX - (a / 2); i < randomX + (a / 2); i++) {
                                for (int j = randomY - (a / 2); j < randomY + (a / 2); j++) {
                                    matrix[i][j].color = Color.BLACK;
                                    matrix[i][j].id = Cell.idColor(matrix[i][j].color);
                                }
                            }
                            inclusions--;
                        }
                    }
                }
                refresh();
            }
        }
    }//GEN-LAST:event_addInclusionsButtonActionPerformed

    private void typeOfInclusionComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_typeOfInclusionComboBoxItemStateChanged
        // TODO add your handling code here:
        if (typeOfInclusionComboBox.getSelectedItem().toString().equals("circular")) {
            jLabel3.setText("Radius r");
        } else if (typeOfInclusionComboBox.getSelectedItem().toString().equals("square")) {
            jLabel3.setText("Diagonal d");
        }
    }//GEN-LAST:event_typeOfInclusionComboBoxItemStateChanged

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
            java.util.logging.Logger.getLogger(MultiscaleModellingFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MultiscaleModellingFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MultiscaleModellingFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MultiscaleModellingFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MultiscaleModellingFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addGrainsButton;
    private javax.swing.JButton addInclusionsButton;
    private javax.swing.JTextField amountOfInclusionsTextField;
    private javax.swing.JButton createButton;
    private javax.swing.JMenuItem exportMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JTextField heightTextField;
    private javax.swing.JMenuItem importMenuItem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JMenu microstructureMenu;
    private javax.swing.JTextField numbersOfGrainsTextField;
    private javax.swing.JTextField sizeOfInclusionsTextField;
    private javax.swing.JButton startButton;
    private javax.swing.JComboBox<String> typeOfInclusionComboBox;
    private javax.swing.JComboBox<String> typeOfNeighborhoodComboBox;
    private javax.swing.JTextField widthTextField;
    // End of variables declaration//GEN-END:variables
}
