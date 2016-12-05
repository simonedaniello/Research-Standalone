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
 * Il costruttore implementa l'interfaccia di base
 * research fa partire il controller
 * getInstance ritorna l'istanza
 * Actions implementa tutti i listener
 * <p>
 */


public class CatalogueBoundary implements Runnable{

    //Singleton
    private static CatalogueBoundary instance = new CatalogueBoundary();
    private  JFrame boundaryCatalogo;
    private JTextField ResearchTF;
    private JList list1;

    private  JTextField titoloTF;
    private  JTextField venditoreTF;
    private  JTextField nomeTF;
    private  JTextField casaEditriceTF;
    private  JTextField autoreTF;
    private  JTextField tipoTF;
    private  JTextField marcaTF;
    private  JTextField modelloTF;
    private  JTextField tagliaTF;


    private  JPanel pan = new JPanel(new GridBagLayout());
    private  JDialog jd = new JDialog();

    private int kind;
    //private Thread thread;

    private CatalogueBoundary(){
        ResearchTF = new JTextField(20);
        JButton confResB = new JButton("OK");
        list1 = new JList();
        JButton electronicsB = new JButton("Informatica");
        JButton clothingB = new JButton("Abbigliamento");
        JButton bookB = new JButton("libri");

        boundaryCatalogo = new JFrame();
        GridBagConstraints gbc = new GridBagConstraints();
        GridBagConstraints gbc2 = new GridBagConstraints();
        JPanel mainJpanel = new JPanel(new GridBagLayout());
        JPanel secondatyJpanel = new JPanel(new GridBagLayout());

        JSeparator sep = new JSeparator(JSeparator.HORIZONTAL);
        sep.setPreferredSize(new Dimension(20,1));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.insets = new Insets(10, 10, 10, 10);
        mainJpanel.add(new JLabel("Ricerca"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.PAGE_END;
        gbc.insets = new Insets(10, 10, 10, 10);
        mainJpanel.add(sep, gbc);


        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.insets = new Insets(10, 10, 10, 10);
        mainJpanel.add(ResearchTF, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.insets = new Insets(10, 10, 10, 10);
        mainJpanel.add(confResB, gbc);

        //---------------------

        gbc2.gridx = 0;
        gbc2.gridy = 0;
        gbc2.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc2.insets = new Insets(10, 10, 10, 10);
        secondatyJpanel.add(electronicsB, gbc2);

        gbc2.gridx = 0;
        gbc2.gridy = 1;
        gbc2.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc2.insets = new Insets(10, 10, 10, 10);
        secondatyJpanel.add(clothingB, gbc2);

        gbc2.gridx = 0;
        gbc2.gridy = 2;
        gbc2.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc2.insets = new Insets(10, 10, 10, 10);
        secondatyJpanel.add(bookB, gbc2);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.insets = new Insets(10, 10, 10, 10);
        mainJpanel.add(secondatyJpanel, gbc);

        JSeparator sep2 = new JSeparator(JSeparator.VERTICAL);
        sep2.setPreferredSize(new Dimension(20,1));

        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.weighty = 1;
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.insets = new Insets(10, 10, 10, 10);
        mainJpanel.add(sep2, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.insets = new Insets(10, 10, 10, 10);
        mainJpanel.add(list1, gbc);

        boundaryCatalogo.add(mainJpanel);
        boundaryCatalogo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        boundaryCatalogo.setLocation(dim.width/2-boundaryCatalogo.getSize().width/2, dim.height/2-boundaryCatalogo.getSize().height/2);
        boundaryCatalogo.setSize(dim.width / 8, dim.height / 8);
        boundaryCatalogo.pack();
        boundaryCatalogo.setVisible(true);


        Actions researchAction = new Actions(0);
        Actions BookAction = new Actions(1);
        Actions electronicsAction = new Actions(2);
        Actions clothingAction = new Actions(3);

        confResB.addActionListener(researchAction);
        bookB.addActionListener(BookAction);
        electronicsB.addActionListener(electronicsAction);
        clothingB.addActionListener(clothingAction);

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


    private class Actions implements ActionListener
    {
        int azione;
        private GridBagConstraints gbc;
        private JButton okButton = new JButton("OK");
        private JButton cancelButton = new JButton("Indietro");

        private Thread thread;


        private Actions(int azione){
            this.azione = azione;
        }

        public void actionPerformed(ActionEvent event){
            switch (this.azione) {
                case 0:
                    CatalogueBoundary.getInstance().kind = 0;
                    thread = new Thread(CatalogueBoundary.getInstance());
                    thread.start();
                    break;
                case 1:
                    CatalogueBoundary.getInstance().jd.setVisible(false);
                    CatalogueBoundary.getInstance().pan.removeAll();

                    CatalogueBoundary.getInstance().titoloTF = new JTextField(20);
                    CatalogueBoundary.getInstance().venditoreTF = new JTextField(20);
                    CatalogueBoundary.getInstance().nomeTF = new JTextField(20);
                    CatalogueBoundary.getInstance().casaEditriceTF = new JTextField(20);
                    CatalogueBoundary.getInstance().autoreTF = new JTextField(20);

                    CatalogueBoundary.getInstance().pan.setLayout(new GridBagLayout());
                    gbc = new GridBagConstraints();

                    gbc.gridx = 0;
                    gbc.gridy = 0;
                    gbc.anchor = GridBagConstraints.PAGE_START;
                    gbc.insets = new Insets(10, 0, 0, 10);
                    CatalogueBoundary.getInstance().pan.add(new JLabel("titolo"), gbc);

                    gbc.gridx = 0;
                    gbc.gridy = 1;
                    gbc.anchor = GridBagConstraints.PAGE_START;
                    gbc.insets = new Insets(10, 10, 10, 10);
                    CatalogueBoundary.getInstance().pan.add(CatalogueBoundary.getInstance().titoloTF, gbc);

                    gbc.gridx = 0;
                    gbc.gridy = 2;
                    gbc.anchor = GridBagConstraints.PAGE_START;
                    gbc.insets = new Insets(10, 0, 0, 10);
                    CatalogueBoundary.getInstance().pan.add(new JLabel("venditore"), gbc);

                    gbc.gridx = 0;
                    gbc.gridy = 3;
                    gbc.anchor = GridBagConstraints.PAGE_START;
                    gbc.insets = new Insets(10, 10, 10, 10);
                    CatalogueBoundary.getInstance().pan.add(CatalogueBoundary.getInstance().venditoreTF, gbc);

                    gbc.gridx = 0;
                    gbc.gridy = 4;
                    gbc.anchor = GridBagConstraints.PAGE_START;
                    gbc.insets = new Insets(10, 0, 0, 10);
                    CatalogueBoundary.getInstance().pan.add(new JLabel("nome"), gbc);

                    gbc.gridx = 0;
                    gbc.gridy = 5;
                    gbc.anchor = GridBagConstraints.PAGE_START;
                    gbc.insets = new Insets(10, 10, 10, 10);
                    CatalogueBoundary.getInstance().pan.add(CatalogueBoundary.getInstance().nomeTF, gbc);

                    gbc.gridx = 0;
                    gbc.gridy = 6;
                    gbc.anchor = GridBagConstraints.PAGE_START;
                    gbc.insets = new Insets(10, 0, 0, 10);
                    CatalogueBoundary.getInstance().pan.add(new JLabel("autore"), gbc);

                    gbc.gridx = 0;
                    gbc.gridy = 7;
                    gbc.anchor = GridBagConstraints.PAGE_START;
                    gbc.insets = new Insets(10, 10, 10, 10);
                    CatalogueBoundary.getInstance().pan.add(CatalogueBoundary.getInstance().autoreTF, gbc);

                    gbc.gridx = 0;
                    gbc.gridy = 8;
                    gbc.anchor = GridBagConstraints.PAGE_START;
                    gbc.insets = new Insets(10, 0, 0, 10);
                    CatalogueBoundary.getInstance().pan.add(new JLabel("casa editrice"), gbc);

                    gbc.gridx = 0;
                    gbc.gridy = 9;
                    gbc.anchor = GridBagConstraints.PAGE_START;
                    gbc.insets = new Insets(10, 10, 10, 10);
                    CatalogueBoundary.getInstance().pan.add(CatalogueBoundary.getInstance().casaEditriceTF, gbc);

                    gbc.gridx = 0;
                    gbc.gridy = 10;
                    gbc.anchor = GridBagConstraints.FIRST_LINE_START;
                    gbc.insets = new Insets(10, 10, 10, 10);
                    CatalogueBoundary.getInstance().pan.add(okButton, gbc);

                    gbc.gridx = 0;
                    gbc.gridy = 10;
                    gbc.anchor = GridBagConstraints.FIRST_LINE_END;
                    gbc.insets = new Insets(10, 10, 10, 10);
                    CatalogueBoundary.getInstance().pan.add(cancelButton, gbc);

                    CatalogueBoundary.getInstance().jd.add(CatalogueBoundary.getInstance().pan);

                    CatalogueBoundary.getInstance().jd.setTitle("Dettagli aggiuntivi libro");
                    CatalogueBoundary.getInstance().jd.pack();

                    CatalogueBoundary.getInstance().jd.setVisible(true);
                    //okButton.addActionListener(e12 -> research(1));
                    okButton.addActionListener(e1 -> {
                        CatalogueBoundary.getInstance().kind= 1;
                        thread = new Thread(CatalogueBoundary.getInstance());
                        thread.start();
                    });

                    cancelButton.addActionListener(e12 -> jd.setVisible(false));

                    break;
                case 2:
                    CatalogueBoundary.getInstance().jd.setVisible(false);
                    CatalogueBoundary.getInstance().pan.removeAll();

                    CatalogueBoundary.getInstance().tipoTF = new JTextField(20);
                    CatalogueBoundary.getInstance().venditoreTF = new JTextField(20);
                    CatalogueBoundary.getInstance().nomeTF = new JTextField(20);
                    CatalogueBoundary.getInstance().marcaTF = new JTextField(20);
                    CatalogueBoundary.getInstance().modelloTF = new JTextField(20);

                    CatalogueBoundary.getInstance().pan.setLayout(new GridBagLayout());
                    gbc = new GridBagConstraints();

                    gbc.gridx = 0;
                    gbc.gridy = 0;
                    gbc.anchor = GridBagConstraints.PAGE_START;
                    gbc.insets = new Insets(10, 0, 0, 10);
                    CatalogueBoundary.getInstance().pan.add(new JLabel("tipo"), gbc);

                    gbc.gridx = 0;
                    gbc.gridy = 1;
                    gbc.anchor = GridBagConstraints.PAGE_START;
                    gbc.insets = new Insets(10, 10, 10, 10);
                    CatalogueBoundary.getInstance().pan.add(CatalogueBoundary.getInstance().tipoTF, gbc);

                    gbc.gridx = 0;
                    gbc.gridy = 2;
                    gbc.anchor = GridBagConstraints.PAGE_START;
                    gbc.insets = new Insets(10, 0, 0, 10);
                    CatalogueBoundary.getInstance().pan.add(new JLabel("venditore"), gbc);

                    gbc.gridx = 0;
                    gbc.gridy = 3;
                    gbc.anchor = GridBagConstraints.PAGE_START;
                    gbc.insets = new Insets(10, 10, 10, 10);
                    CatalogueBoundary.getInstance().pan.add(CatalogueBoundary.getInstance().venditoreTF, gbc);

                    gbc.gridx = 0;
                    gbc.gridy = 4;
                    gbc.anchor = GridBagConstraints.PAGE_START;
                    gbc.insets = new Insets(10, 0, 0, 10);
                    CatalogueBoundary.getInstance().pan.add(new JLabel("nome"), gbc);

                    gbc.gridx = 0;
                    gbc.gridy = 5;
                    gbc.anchor = GridBagConstraints.PAGE_START;
                    gbc.insets = new Insets(10, 10, 10, 10);
                    CatalogueBoundary.getInstance().pan.add(CatalogueBoundary.getInstance().nomeTF, gbc);

                    gbc.gridx = 0;
                    gbc.gridy = 6;
                    gbc.anchor = GridBagConstraints.PAGE_START;
                    gbc.insets = new Insets(10, 0, 0, 10);
                    CatalogueBoundary.getInstance().pan.add(new JLabel("modello"), gbc);

                    gbc.gridx = 0;
                    gbc.gridy = 7;
                    gbc.anchor = GridBagConstraints.PAGE_START;
                    gbc.insets = new Insets(10, 10, 10, 10);
                    CatalogueBoundary.getInstance().pan.add(CatalogueBoundary.getInstance().modelloTF, gbc);

                    gbc.gridx = 0;
                    gbc.gridy = 8;
                    gbc.anchor = GridBagConstraints.PAGE_START;
                    gbc.insets = new Insets(10, 0, 0, 10);
                    CatalogueBoundary.getInstance().pan.add(new JLabel("marca"), gbc);

                    gbc.gridx = 0;
                    gbc.gridy = 9;
                    gbc.anchor = GridBagConstraints.PAGE_START;
                    gbc.insets = new Insets(10, 10, 10, 10);
                    CatalogueBoundary.getInstance().pan.add(CatalogueBoundary.getInstance().marcaTF, gbc);


                    gbc.gridx = 0;
                    gbc.gridy = 10;
                    gbc.anchor = GridBagConstraints.FIRST_LINE_START;
                    gbc.insets = new Insets(10, 10, 10, 10);
                    CatalogueBoundary.getInstance().pan.add(okButton, gbc);
                    gbc.gridx = 0;
                    gbc.gridy = 10;
                    gbc.anchor = GridBagConstraints.FIRST_LINE_END;
                    gbc.insets = new Insets(10, 10, 10, 10);
                    CatalogueBoundary.getInstance().pan.add(cancelButton, gbc);


                    CatalogueBoundary.getInstance().jd.add(CatalogueBoundary.getInstance().pan);

                    CatalogueBoundary.getInstance().jd.setTitle("Dettagli aggiuntivi informatica");
                    CatalogueBoundary.getInstance().jd.pack();
                    CatalogueBoundary.getInstance().jd.setVisible(true);

                    //okButton.addActionListener(e12 -> research(2));
                    okButton.addActionListener(e1 -> {
                        CatalogueBoundary.getInstance().kind = 2;
                        thread = new Thread(CatalogueBoundary.getInstance());
                        thread.start();
                    });
                    cancelButton.addActionListener(e12 -> jd.setVisible(false));

                    break;
                case 3:
                    CatalogueBoundary.getInstance().jd.setVisible(false);
                    CatalogueBoundary.getInstance().pan.removeAll();

                    CatalogueBoundary.getInstance().tipoTF = new JTextField(20);
                    CatalogueBoundary.getInstance().venditoreTF = new JTextField(20);
                    CatalogueBoundary.getInstance(). nomeTF = new JTextField(20);
                    CatalogueBoundary.getInstance().marcaTF = new JTextField(20);
                    CatalogueBoundary.getInstance().tagliaTF = new JTextField(20);

                    CatalogueBoundary.getInstance().pan.setLayout(new GridBagLayout());
                    gbc = new GridBagConstraints();

                    gbc.gridx = 0;
                    gbc.gridy = 0;
                    gbc.anchor = GridBagConstraints.PAGE_START;
                    gbc.insets = new Insets(10, 0, 0, 10);
                    CatalogueBoundary.getInstance().pan.add(new JLabel("tipo"), gbc);

                    gbc.gridx = 0;
                    gbc.gridy = 1;
                    gbc.anchor = GridBagConstraints.PAGE_START;
                    gbc.insets = new Insets(10, 10, 10, 10);
                    CatalogueBoundary.getInstance().pan.add(CatalogueBoundary.getInstance().tipoTF, gbc);

                    gbc.gridx = 0;
                    gbc.gridy = 2;
                    gbc.anchor = GridBagConstraints.PAGE_START;
                    gbc.insets = new Insets(10, 0, 0, 10);
                    CatalogueBoundary.getInstance().pan.add(new JLabel("venditore"), gbc);

                    gbc.gridx = 0;
                    gbc.gridy = 3;
                    gbc.anchor = GridBagConstraints.PAGE_START;
                    gbc.insets = new Insets(10, 10, 10, 10);
                    CatalogueBoundary.getInstance().pan.add(CatalogueBoundary.getInstance().venditoreTF, gbc);

                    gbc.gridx = 0;
                    gbc.gridy = 4;
                    gbc.anchor = GridBagConstraints.PAGE_START;
                    gbc.insets = new Insets(10, 0, 0, 10);
                    CatalogueBoundary.getInstance().pan.add(new JLabel("nome"), gbc);

                    gbc.gridx = 0;
                    gbc.gridy = 5;
                    gbc.anchor = GridBagConstraints.PAGE_START;
                    gbc.insets = new Insets(10, 10, 10, 10);
                    CatalogueBoundary.getInstance().pan.add(CatalogueBoundary.getInstance().nomeTF, gbc);

                    gbc.gridx = 0;
                    gbc.gridy = 6;
                    gbc.anchor = GridBagConstraints.PAGE_START;
                    gbc.insets = new Insets(10, 0, 0, 10);
                    CatalogueBoundary.getInstance().pan.add(new JLabel("taglia"), gbc);

                    gbc.gridx = 0;
                    gbc.gridy = 7;
                    gbc.anchor = GridBagConstraints.PAGE_START;
                    gbc.insets = new Insets(10, 10, 10, 10);
                    CatalogueBoundary.getInstance().pan.add(CatalogueBoundary.getInstance().tagliaTF, gbc);

                    gbc.gridx = 0;
                    gbc.gridy = 8;
                    gbc.anchor = GridBagConstraints.PAGE_START;
                    gbc.insets = new Insets(10, 0, 0, 10);
                    CatalogueBoundary.getInstance().pan.add(new JLabel("marca"), gbc);

                    gbc.gridx = 0;
                    gbc.gridy = 9;
                    gbc.anchor = GridBagConstraints.PAGE_START;
                    gbc.insets = new Insets(10, 10, 10, 10);
                    CatalogueBoundary.getInstance().pan.add(CatalogueBoundary.getInstance().marcaTF, gbc);


                    gbc.gridx = 0;
                    gbc.gridy = 10;
                    gbc.anchor = GridBagConstraints.FIRST_LINE_START;
                    gbc.insets = new Insets(10, 10, 10, 10);
                    CatalogueBoundary.getInstance().pan.add(okButton, gbc);
                    gbc.gridx = 0;
                    gbc.gridy = 10;
                    gbc.anchor = GridBagConstraints.FIRST_LINE_END;
                    gbc.insets = new Insets(10, 10, 10, 10);
                    CatalogueBoundary.getInstance().pan.add(cancelButton, gbc);


                    CatalogueBoundary.getInstance().jd.add(CatalogueBoundary.getInstance().pan);
                    CatalogueBoundary.getInstance().jd.setTitle("Dettagli aggiuntivi Abbigliamento");
                    CatalogueBoundary.getInstance().jd.pack();
                    CatalogueBoundary.getInstance().jd.setVisible(true);

                    //okButton.addActionListener(e12 -> research(3));
                    okButton.addActionListener(e1 -> {
                        CatalogueBoundary.getInstance().kind = 3;
                        thread = new Thread(CatalogueBoundary.getInstance());
                        thread.start();
                    });
                    cancelButton.addActionListener(e12 -> jd.setVisible(false));
                    break;
            }
        }
    }
}
