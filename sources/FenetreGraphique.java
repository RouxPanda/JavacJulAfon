import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class FenetreGraphique extends JFrame implements ActionListener {
    JFrame fenetre;
    private JPanel Central;
    private JTextField BarRecherche;
    private JButton Valider;
    private JButton MesAlbums;
    private JButton Ajouter;
    private JButton Supprimer;
    private JButton Retour;
    private JPanel PanelMenu;
    private JPanel Regroupe;
    private JPanel PannelRecherche;
    private Ajouter ajouter;
    private Montrage montrage;

    FenetreGraphique(){
        //Initialisation
        fenetre = new JFrame("Bibliothèque");
        fenetre.setSize(1200, 720);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Central = new JPanel();
        Central.setLayout(new BorderLayout(0, 0));

        Border line = new LineBorder(Color.BLACK);
        Border margin = new EmptyBorder(5, 15, 5, 15);
        Border compound = new CompoundBorder(line, margin);

        Regroupe = new JPanel();
        Regroupe.setLayout(new BorderLayout(0, 0));
        Central.add(Regroupe, BorderLayout.CENTER);

        montrage = new Montrage();


        //La barre de recherche
        PannelRecherche = new JPanel();
        PannelRecherche.setBackground(Color.darkGray);
        PannelRecherche.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        Regroupe.add(PannelRecherche, BorderLayout.NORTH);
        Valider = new JButton();
        Valider.setText("Valider");
        Valider.setActionCommand("Valider");
        Valider.addActionListener(this);
        Valider = TransformButton(Valider);
        final JLabel label1 = new JLabel();
        label1.setText("Recherche");
        label1.setForeground(Color.lightGray);
        PannelRecherche.add(label1);
        BarRecherche = new JTextField(30);
        PannelRecherche.add(BarRecherche);
        PannelRecherche.add(Valider);

        JPanel PannelMontrage = new JPanel();
        PannelMontrage.setLayout(new BorderLayout());
        //montrage.ChangementPanel(1);
        PannelMontrage.add(montrage.getPrincipal());

        Regroupe.add(PannelMontrage, BorderLayout.CENTER);


        //Le menu à droite
        PanelMenu = new JPanel();

        PanelMenu.setLayout(new GridBagLayout());
        PanelMenu.setBackground(Color.darkGray);

        Central.add(PanelMenu, BorderLayout.WEST);
        MesAlbums = new JButton();
        MesAlbums.setText("Mes Albums");
        TransformButton(MesAlbums);
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        PanelMenu.add(MesAlbums, gbc);

        Ajouter = new JButton();
        Ajouter.setText("Ajouter");
        Ajouter.setActionCommand("Ajouter");
        Ajouter.addActionListener(this);
        TransformButton(Ajouter);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        PanelMenu.add(Ajouter, gbc);

        Supprimer = new JButton();
        Supprimer.setText("Supprimer");
        TransformButton(Supprimer);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        PanelMenu.add(Supprimer, gbc);

        Retour = new JButton();
        Retour.setText("Retour");
        Retour.setActionCommand("Retour");
        Retour.addActionListener(this);
        TransformButton(Retour);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        PanelMenu.add(Retour, gbc);

        final JLabel label2 = new JLabel();
        label2.setText("     ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        PanelMenu.add(label2, gbc);
        final JLabel label3 = new JLabel();
        label3.setText("     ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        PanelMenu.add(label3, gbc);
        final JLabel label4 = new JLabel();
        label4.setText("     ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        PanelMenu.add(label4, gbc);
        Border bord = new EmptyBorder(0,4,160,4);
        PanelMenu.setBorder(new CompoundBorder(bord,bord));

        fenetre.add(Central);
        fenetre.setVisible(true);

    }

    public void AjouterUnAlbum(Album album){
        montrage.Ajout(album);
    }

    JButton TransformButton(JButton bouton){
        Border line = new LineBorder(Color.WHITE);
        Border margin = new EmptyBorder(5, 15, 5, 15);
        Border compound = new CompoundBorder(line, margin);
        bouton.setForeground(Color.WHITE);
        bouton.setBackground(Color.gray);
        bouton.setBorder(compound);
        return bouton;
    }

        @Override
    public void actionPerformed(ActionEvent evenement){
        if(evenement.getActionCommand().equals("Retour"))
            montrage.RetourArrière();
        if(evenement.getActionCommand().equals("Ajouter")){
            ajouter = new Ajouter(this);
        }
        if(evenement.getActionCommand().equals("Valider")){
            String recherche = BarRecherche.getText();
            String magic = "Le J c'est le S";
            if(!recherche.isEmpty())
                montrage.Recherche(recherche);
            if(recherche.toLowerCase().contains(magic.toLowerCase())) {
                Desktop desktop = java.awt.Desktop.getDesktop();
                try {
                    //specify the protocol along with the URL
                    URI oURL = new URI(
                            "https://www.youtube.com/watch?v=-CVn3-3g_BI");
                    desktop.browse(oURL);
                } catch (URISyntaxException | IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}
