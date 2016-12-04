package boundary;

import control.CatalogueController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;



/**
 * @author dandi
 * <p>
 * Questa classe &egrave; stata sviluppata come un Singleton.
 * <p>
 */


public class CatalogueBoundary implements Runnable{

    //Singleton
    private static CatalogueBoundary instance = new CatalogueBoundary();
    private  JPanel panel1;
    private  JTextField ResearchTF;
    private  JButton ConfResB;
    private  JList list1;
    private  JButton ElectronicsB;
    private  JButton ClothingB;
    private  JButton BookB;

    private  JTextField titoloTF;
    private  JTextField venditoreTF;
    private  JTextField nomeTF;
    private  JTextField casaEditriceTF;
    private  JTextField autoreTF;
    private  JTextField tipoTF;
    private  JTextField marcaTF;
    private  JTextField modelloTF;
    private  JTextField tagliaTF;

    private  JFrame boundaryCatalogo;
    private  JPanel pan = new JPanel();
    private  JDialog jd = new JDialog();

    private int kind;
    private Thread thread;

    private CatalogueBoundary(){

        boundaryCatalogo = new JFrame();
        boundaryCatalogo.setContentPane(panel1);
        boundaryCatalogo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        boundaryCatalogo.pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        boundaryCatalogo.setLocation(dim.width/2-boundaryCatalogo.getSize().width/2, dim.height/2-boundaryCatalogo.getSize().height/2);
        boundaryCatalogo.setVisible(true);


        //ConfResB.addActionListener(e -> research(0));
        ConfResB.addActionListener(e1 -> {
            this.kind = 0;
            thread = new Thread(this);
            thread.start();
        });


        BookB.addActionListener(e -> {
            jd.setVisible(false);
            pan.removeAll();

            titoloTF = new JTextField(20);
            venditoreTF = new JTextField(20);
            nomeTF = new JTextField(20);
            casaEditriceTF = new JTextField(20);
            autoreTF = new JTextField(20);

            pan.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.PAGE_START;
            gbc.insets = new Insets(10, 0, 0, 10);
            pan.add(new JLabel("titolo"), gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.anchor = GridBagConstraints.PAGE_START;
            gbc.insets = new Insets(10, 10, 10, 10);
            pan.add(titoloTF, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.anchor = GridBagConstraints.PAGE_START;
            gbc.insets = new Insets(10, 0, 0, 10);
            pan.add(new JLabel("venditore"), gbc);

            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.anchor = GridBagConstraints.PAGE_START;
            gbc.insets = new Insets(10, 10, 10, 10);
            pan.add(venditoreTF, gbc);

            gbc.gridx = 0;
            gbc.gridy = 4;
            gbc.anchor = GridBagConstraints.PAGE_START;
            gbc.insets = new Insets(10, 0, 0, 10);
            pan.add(new JLabel("nome"), gbc);

            gbc.gridx = 0;
            gbc.gridy = 5;
            gbc.anchor = GridBagConstraints.PAGE_START;
            gbc.insets = new Insets(10, 10, 10, 10);
            pan.add(nomeTF, gbc);

            gbc.gridx = 0;
            gbc.gridy = 6;
            gbc.anchor = GridBagConstraints.PAGE_START;
            gbc.insets = new Insets(10, 0, 0, 10);
            pan.add(new JLabel("autore"), gbc);

            gbc.gridx = 0;
            gbc.gridy = 7;
            gbc.anchor = GridBagConstraints.PAGE_START;
            gbc.insets = new Insets(10, 10, 10, 10);
            pan.add(autoreTF, gbc);

            gbc.gridx = 0;
            gbc.gridy = 8;
            gbc.anchor = GridBagConstraints.PAGE_START;
            gbc.insets = new Insets(10, 0, 0, 10);
            pan.add(new JLabel("casa editrice"), gbc);

            gbc.gridx = 0;
            gbc.gridy = 9;
            gbc.anchor = GridBagConstraints.PAGE_START;
            gbc.insets = new Insets(10, 10, 10, 10);
            pan.add(casaEditriceTF, gbc);


            JButton okButton = new JButton("OK");
            JButton cancelButton = new JButton("Indietro");

            gbc.gridx = 0;
            gbc.gridy = 10;
            gbc.anchor = GridBagConstraints.FIRST_LINE_START;
            gbc.insets = new Insets(10, 10, 10, 10);
            pan.add(okButton, gbc);
            gbc.gridx = 0;
            gbc.gridy = 10;
            gbc.anchor = GridBagConstraints.FIRST_LINE_END;
            gbc.insets = new Insets(10, 10, 10, 10);
            pan.add(cancelButton, gbc);

            jd.add(pan);

            jd.setTitle("Dettagli aggiuntivi libro");
            jd.pack();

            jd.setVisible(true);
            //okButton.addActionListener(e12 -> research(1));
            okButton.addActionListener(e1 -> {
                this.kind = 1;
                thread = new Thread(this);
                thread.start();
            });

            cancelButton.addActionListener(e12 -> jd.setVisible(false));

        });





        ElectronicsB.addActionListener(e -> {

            jd.setVisible(false);
            pan.removeAll();

            tipoTF = new JTextField(20);
            venditoreTF = new JTextField(20);
            nomeTF = new JTextField(20);
            marcaTF = new JTextField(20);
            modelloTF = new JTextField(20);

            pan.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.PAGE_START;
            gbc.insets = new Insets(10, 0, 0, 10);
            pan.add(new JLabel("tipo"), gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.anchor = GridBagConstraints.PAGE_START;
            gbc.insets = new Insets(10, 10, 10, 10);
            pan.add(tipoTF, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.anchor = GridBagConstraints.PAGE_START;
            gbc.insets = new Insets(10, 0, 0, 10);
            pan.add(new JLabel("venditore"), gbc);

            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.anchor = GridBagConstraints.PAGE_START;
            gbc.insets = new Insets(10, 10, 10, 10);
            pan.add(venditoreTF, gbc);

            gbc.gridx = 0;
            gbc.gridy = 4;
            gbc.anchor = GridBagConstraints.PAGE_START;
            gbc.insets = new Insets(10, 0, 0, 10);
            pan.add(new JLabel("nome"), gbc);

            gbc.gridx = 0;
            gbc.gridy = 5;
            gbc.anchor = GridBagConstraints.PAGE_START;
            gbc.insets = new Insets(10, 10, 10, 10);
            pan.add(nomeTF, gbc);

            gbc.gridx = 0;
            gbc.gridy = 6;
            gbc.anchor = GridBagConstraints.PAGE_START;
            gbc.insets = new Insets(10, 0, 0, 10);
            pan.add(new JLabel("modello"), gbc);

            gbc.gridx = 0;
            gbc.gridy = 7;
            gbc.anchor = GridBagConstraints.PAGE_START;
            gbc.insets = new Insets(10, 10, 10, 10);
            pan.add(modelloTF, gbc);

            gbc.gridx = 0;
            gbc.gridy = 8;
            gbc.anchor = GridBagConstraints.PAGE_START;
            gbc.insets = new Insets(10, 0, 0, 10);
            pan.add(new JLabel("marca"), gbc);

            gbc.gridx = 0;
            gbc.gridy = 9;
            gbc.anchor = GridBagConstraints.PAGE_START;
            gbc.insets = new Insets(10, 10, 10, 10);
            pan.add(marcaTF, gbc);


            JButton okButton = new JButton("OK");
            JButton cancelButton = new JButton("Indietro");

            gbc.gridx = 0;
            gbc.gridy = 10;
            gbc.anchor = GridBagConstraints.FIRST_LINE_START;
            gbc.insets = new Insets(10, 10, 10, 10);
            pan.add(okButton, gbc);
            gbc.gridx = 0;
            gbc.gridy = 10;
            gbc.anchor = GridBagConstraints.FIRST_LINE_END;
            gbc.insets = new Insets(10, 10, 10, 10);
            pan.add(cancelButton, gbc);


            jd.add(pan);

            jd.setTitle("Dettagli aggiuntivi informatica");
            jd.pack();
            jd.setVisible(true);

            //okButton.addActionListener(e12 -> research(2));
            okButton.addActionListener(e1 -> {
                this.kind = 2;
                thread = new Thread(this);
                thread.start();
            });
            cancelButton.addActionListener(e12 -> jd.setVisible(false));

        });






        ClothingB.addActionListener(e -> {
            jd.setVisible(false);
            pan.removeAll();

            tipoTF = new JTextField(20);
            venditoreTF = new JTextField(20);
            nomeTF = new JTextField(20);
            marcaTF = new JTextField(20);
            tagliaTF = new JTextField(20);

            pan.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.PAGE_START;
            gbc.insets = new Insets(10, 0, 0, 10);
            pan.add(new JLabel("tipo"), gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.anchor = GridBagConstraints.PAGE_START;
            gbc.insets = new Insets(10, 10, 10, 10);
            pan.add(tipoTF, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.anchor = GridBagConstraints.PAGE_START;
            gbc.insets = new Insets(10, 0, 0, 10);
            pan.add(new JLabel("venditore"), gbc);

            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.anchor = GridBagConstraints.PAGE_START;
            gbc.insets = new Insets(10, 10, 10, 10);
            pan.add(venditoreTF, gbc);

            gbc.gridx = 0;
            gbc.gridy = 4;
            gbc.anchor = GridBagConstraints.PAGE_START;
            gbc.insets = new Insets(10, 0, 0, 10);
            pan.add(new JLabel("nome"), gbc);

            gbc.gridx = 0;
            gbc.gridy = 5;
            gbc.anchor = GridBagConstraints.PAGE_START;
            gbc.insets = new Insets(10, 10, 10, 10);
            pan.add(nomeTF, gbc);

            gbc.gridx = 0;
            gbc.gridy = 6;
            gbc.anchor = GridBagConstraints.PAGE_START;
            gbc.insets = new Insets(10, 0, 0, 10);
            pan.add(new JLabel("taglia"), gbc);

            gbc.gridx = 0;
            gbc.gridy = 7;
            gbc.anchor = GridBagConstraints.PAGE_START;
            gbc.insets = new Insets(10, 10, 10, 10);
            pan.add(tagliaTF, gbc);

            gbc.gridx = 0;
            gbc.gridy = 8;
            gbc.anchor = GridBagConstraints.PAGE_START;
            gbc.insets = new Insets(10, 0, 0, 10);
            pan.add(new JLabel("marca"), gbc);

            gbc.gridx = 0;
            gbc.gridy = 9;
            gbc.anchor = GridBagConstraints.PAGE_START;
            gbc.insets = new Insets(10, 10, 10, 10);
            pan.add(marcaTF, gbc);


            JButton okButton = new JButton("OK");
            JButton cancelButton = new JButton("Indietro");

            gbc.gridx = 0;
            gbc.gridy = 10;
            gbc.anchor = GridBagConstraints.FIRST_LINE_START;
            gbc.insets = new Insets(10, 10, 10, 10);
            pan.add(okButton, gbc);
            gbc.gridx = 0;
            gbc.gridy = 10;
            gbc.anchor = GridBagConstraints.FIRST_LINE_END;
            gbc.insets = new Insets(10, 10, 10, 10);
            pan.add(cancelButton, gbc);


            jd.add(pan);
            jd.setTitle("Dettagli aggiuntivi Abbigliamento");
            jd.pack();
            jd.setVisible(true);

            //okButton.addActionListener(e12 -> research(3));
            okButton.addActionListener(e1 -> {
                this.kind = 3;
                thread = new Thread(this);
                thread.start();
            });
            cancelButton.addActionListener(e12 -> jd.setVisible(false));
        });
    }

    public static CatalogueBoundary getInstance(){
        return instance;
    }

    private void research(int kind){

        switch (kind) {
            case 0:
                try {
                    CatalogueController.getInstance().createCatalogue(ResearchTF.getText(), 0, "", "", "", "", "", "", "", 0, "", 0 ,"",  list1, boundaryCatalogo);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                break;
            case 1:
                try {
                    CatalogueController.getInstance().createCatalogue(nomeTF.getText(), 0, venditoreTF.getText() ,
                            "Book", casaEditriceTF.getText(), autoreTF.getText(),
                            titoloTF.getText(), "", "", 0, "", 0 ,"",  list1, boundaryCatalogo);

                    jd.setVisible(false);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                break;
            case 2:
                try {
                    CatalogueController.getInstance().createCatalogue(nomeTF.getText(), 0, venditoreTF.getText() ,
                            "Electronics", "", "",
                            "", "", marcaTF.getText(), 0, "", 0 , modelloTF.getText(), list1, boundaryCatalogo);
                    jd.setVisible(false);

                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                break;
            case 3:
                try {
                    int x;
                    try {
                        x = Integer.parseInt(tagliaTF.getText());
                    } catch (NumberFormatException nfe) {
                        x = 0;
                    }
                    CatalogueController.getInstance().createCatalogue(nomeTF.getText(), 0, venditoreTF.getText() ,
                            "Clothing", "", "",
                            "", "", marcaTF.getText(), x, "", 0 ,"",  list1, boundaryCatalogo);

                    this.jd.setVisible(false);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                break;
        }
    }

    @Override
    public void run() {
        research(this.kind);
    }
}
