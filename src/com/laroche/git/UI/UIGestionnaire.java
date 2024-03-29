package com.laroche.git.UI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.laroche.git.DAO.AbsencesEleveDAO;
import com.laroche.git.DAO.AuthentificationDAO;
import com.laroche.git.DAO.EtudiantDAO;
import com.laroche.git.DAO.GroupeDAO;
import com.laroche.git.DAO.SeanceDAO;
import com.laroche.git.DAO.listeEtudiantDAO;
import com.laroche.git.DAO.listeGroupeDAO;
import com.laroche.git.DAO.suppEtudiantDAO;
import com.laroche.git.objets.Absences;
import com.laroche.git.objets.Authentification;
import com.laroche.git.objets.Cours;
import com.laroche.git.objets.Etudiant;
import com.laroche.git.objets.Groupe;
import com.laroche.git.objets.Personne;
import com.laroche.git.objets.Seance;



public class UIGestionnaire extends JFrame implements ActionListener{
	
	private int LARGEUR_FENETRE = 625;
	private int HAUTEUR_FENETRE = 450;
	
	private int userID;
	private JButton JBGerer,JBAbsences,JBGroupes,JBDeconnexion;
	private JPanel content,JPGerer,JPAbsences, JPGroupes;
	private String[] listeCard = {"card_gerer", "card_absences", "card_groupes"};
	private CardLayout cardLayoutCentre = new CardLayout();
	
	//On définit les JRadioButton pour "g�rer"
	private ButtonGroup buttonGroup = new ButtonGroup();
    private JRadioButton rdoEtudiant = new JRadioButton("Etudiant(s)");
    private JRadioButton rdoEnseignant = new JRadioButton("Enseignant(s)");
    private JRadioButton rdoGroupe = new JRadioButton("Groupe(s)");
    private JRadioButton rdoCours = new JRadioButton("Cours");
	
    private JButton JBAjout, JBModif, JBSupp;
    
    //Pour tableau du milieu
    private DefaultTableModel valeursTableau,valeursTableauEl, valeursTableauAbs;
    //private DefaultListModel valeursTableauGr;
    private String buttonSelect="etudiant";
    private JTable listeUtilisateur, listeEleves, listeAbs;
    private ArrayList<Absences> listAbsences;
    private JList listeGroupes;
    
    //Pour l'affichage des absences
    private Etudiant etudiant;
    private ArrayList<Seance> totSeances;
    private ArrayList<Cours> cours;
    private JComboBox combo_box_etudiants;
    
    
	public UIGestionnaire(int userID) {
		this.userID = userID;
		this.setSize(LARGEUR_FENETRE, HAUTEUR_FENETRE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new BorderLayout(0, 0));
		this.setLocationRelativeTo(null);
		System.out.println(userID);
		
	    JBAjout = new JButton("Ajouter");
	    JBAjout.addActionListener(this);
	    JBModif = new JButton("Modifier");
	    JBModif.addActionListener(this);
	    JBSupp = new JButton("Supprimer");
	    JBSupp.addActionListener(this);
		
		//On definit chaque JPanel : Gerer, Absences et Groupes
		content = new JPanel();
		JPGerer = new JPanel();
		JPAbsences = new JPanel();
		JPGroupes = new JPanel();
		
		
		//Gerer********************************************************************************************
		
		//Icone du gestionnaire#######################################################################
		JLabel lblIcone = new JLabel(new ImageIcon("drawables/GestionnaireIcon.png"));
		lblIcone.setHorizontalAlignment(SwingConstants.LEADING);
		
		
		//Creation des differentes cellules############################################################
		//On cree nos differents conteneurs de couleur differente
	    JPanel cellIcon = new JPanel();
	    cellIcon.setBackground(Color.YELLOW);
	    cellIcon.add(lblIcone);
	    cellIcon.setPreferredSize(new Dimension(80/5, 50/5));
	    
	    JPanel cellList = new JPanel();
	    cellList.setBackground(Color.black);
	    cellList.setPreferredSize(new Dimension(370/5, 280/5));
	    
	    JPanel cellChoix = new JPanel();
	    cellChoix.setBackground(Color.green);
	    cellChoix.setPreferredSize(new Dimension(80/5, 130/5));
	    
	    JPanel cellAction = new JPanel();
	    cellAction.setBackground(Color.cyan);
	    cellAction.setPreferredSize(new Dimension(450/5, 20/5));
	    
	    JPGerer.setLayout(new GridBagLayout());
	    
	    //L'objet servant a� positionner les composants
	    GridBagConstraints gbc = new GridBagConstraints();
			
	    //On positionne la case de depart du composant
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    //La taille en hauteur et en largeur
	    gbc.gridheight = 2;
	    gbc.gridwidth = 2;
	    gbc.fill = GridBagConstraints.BOTH;
	    
	    gbc.weightx = 0.3;
	    //gbc.weighty = 2/6;
	    gbc.weighty =0.3;
	    
	    JPGerer.add(cellIcon, gbc);
	    //---------------------------------------------
	    gbc.gridx = 2;
	    gbc.gridy = 0;
	    gbc.gridheight = 5;
	    gbc.gridwidth = GridBagConstraints.REMAINDER;
	    gbc.fill = GridBagConstraints.BOTH;
	    
	    gbc.weightx = 0.7;
	    gbc.weighty = 0.7;
	    
	    JPGerer.add(cellList, gbc);
	    //---------------------------------------------
	    gbc.gridx = 0;
	    gbc.gridy = 2;
	    gbc.gridheight = 3;
	    gbc.gridwidth = 2;
	    gbc.fill = GridBagConstraints.BOTH;
	    
	    gbc.weightx = 0.3;
	    gbc.weighty = 0.4;
	    
	    JPGerer.add(cellChoix, gbc);		

	    //---------------------------------------------
	    gbc.gridx = 0;
	    gbc.gridy = 5;
	    gbc.gridheight = 1;
	    gbc.gridwidth = GridBagConstraints.REMAINDER;
	    gbc.fill = GridBagConstraints.BOTH;
	    
	    gbc.weightx = 1;
	    gbc.weighty = 0.3;
	    
	    JPGerer.add(cellAction, gbc);
	    //---------------------------------------------

		JPAbsences.setBackground(Color.red);
		JPGroupes.setBackground(Color.pink);
		
		JPanel panel = new BackgroundPanel();
		JBGerer = new JButton("Gerer");
		JBAbsences = new JButton("Absences");
		JBGroupes = new JButton("Groupes");
		JBDeconnexion = new JButton("Déconnexion");

		JBGerer.addActionListener(this);
		JBAbsences.addActionListener(this);
		JBGroupes.addActionListener(this);
		JBDeconnexion.addActionListener(this);
		
		panel.add(JBGerer);
		panel.add(JBAbsences);
		panel.add(JBGroupes);
		panel.add(JBDeconnexion);
		
		content.setLayout(cardLayoutCentre);
		content.add(JPGerer,listeCard[0]);
		content.add(JPAbsences,listeCard[1]);
		content.add(JPGroupes,listeCard[2]);
		
		
		//Cellule Choix################################################################
		rdoEtudiant.setSelected(true);
		rdoEtudiant.addActionListener(this);
		cellChoix.add(rdoEtudiant);
        
        cellChoix.add(rdoEnseignant);
        rdoEnseignant.addActionListener(this);
        
        cellChoix.add(rdoGroupe);
        rdoGroupe.addActionListener(this);
        
        cellChoix.add(rdoCours);
        rdoCours.addActionListener(this);
        
        buttonGroup.add(rdoEtudiant);
        buttonGroup.add(rdoEnseignant);
        buttonGroup.add(rdoGroupe);
        buttonGroup.add(rdoCours);
        
        
        
        //Cellule Liste############################################################################

    	String[] columnNames = {"Nom","Prenom", "Email", "MDP", "Statut"};
    	String[][] valeurs = new String[5][0];
    	valeursTableau = new DefaultTableModel(valeurs, columnNames);
    	
        

        listeUtilisateur = new JTable(valeursTableau);
        refreshUtilisateur();
               
      	cellList.add(new JScrollPane(listeUtilisateur));
      	
      	//Cellule Action############################################################################
      	
      	//1 ligne sur 3 colonnes
        cellAction.setLayout(new GridLayout(1, 3));
        //On ajoute le bouton au content pane de la JFrame
        
        cellAction.add(JBAjout);
        cellAction.add(JBModif);
        cellAction.add(JBSupp);
       
		
		//***************************************************************************************************************

        //Groupes********************************************************************************************************
      	
        JPGroupes.setLayout(new BorderLayout(0, 0));
  		
  		JPanel panel_listGroup = new JPanel();
  		JPanel panel_listEleves = new JPanel();
  		JPGroupes.add(panel_listGroup, BorderLayout.WEST);
  		JPGroupes.add(panel_listEleves, BorderLayout.CENTER);
 
  		String[] nomsColEl = {"Nom","Prenom"};
    	String[][] valuesEl = new String[2][0];
    	valeursTableauEl = new DefaultTableModel(valuesEl, nomsColEl);
    	
        listeEleves = new JTable(valeursTableauEl);
        
        //On fait une comboBox avec les groupes
        ArrayList<Groupe> nGroup = new ArrayList<Groupe>();
        nGroup = listeGroupeDAO.getList();
        ArrayList<Integer> listnGroup = new ArrayList<Integer>();
        for(Groupe grp : nGroup) {
        	listnGroup.add(grp.getNumero());
        }
        JComboBox combo_box_groupes = new JComboBox(listnGroup.toArray());
        
        refreshEleves(combo_box_groupes);
        
        panel_listEleves.add(new JScrollPane(listeEleves));
      	panel_listGroup.add(combo_box_groupes);
      	
      	//***************************************************************************************************************

        //Absences********************************************************************************************************
      	
        JPAbsences.setLayout(new BorderLayout(0, 0));
  		
  		JPanel panel_listElevesPourAbs = new JPanel();
  		JPanel panel_listAbsences = new JPanel();
  		JPAbsences.add(panel_listElevesPourAbs, BorderLayout.WEST);
  		JPAbsences.add(panel_listAbsences, BorderLayout.CENTER);
 
  		String[] nomsColAbs = {"date","duree","heure","cours","etat","justificatif"};
    	String[][] valeursAbs = new String[6][0];
    	valeursTableauAbs = new DefaultTableModel(valeursAbs, nomsColAbs);
    	
        listeAbs = new JTable(valeursTableauAbs);
        
        //On fait une comboBox avec les �l�ves
        ArrayList<Etudiant> nEleve = new ArrayList<Etudiant>();
        nEleve = listeEtudiantDAO.getList();
        ArrayList<String> listnEleve = new ArrayList<String>();
        for(Etudiant etud : nEleve) {
        	listnEleve.add(etud.getID()+"-"+etud.getNom() + " " + etud.getPrenom());
        }
        combo_box_etudiants = new JComboBox(listnEleve.toArray());
        
        refreshAbsEleve();
        
      	panel_listElevesPourAbs.add(combo_box_etudiants);
      	panel_listAbsences.add(new JScrollPane(listeAbs));
      	
      	
    
        
		
		this.getContentPane().add(panel, BorderLayout.NORTH);
		this.getContentPane().add(content,BorderLayout.CENTER);
		this.setVisible(true);
	}
	
	public void refreshUtilisateur() {
		
		AuthentificationDAO authDao = new AuthentificationDAO();
		ArrayList<Personne> listPersonne = authDao.getListPersonne();
		ArrayList<Authentification> listAuth = authDao.getListAuth();
		
		//Efface les anciennes valeurs
		while(valeursTableau.getRowCount()>0) {
			valeursTableau.removeRow(valeursTableau.getRowCount()-1);
		}
		
		for(int i=0; i<listAuth.size(); i++) {
				String nom = listPersonne.get(i).getNom();
				String prenom = listPersonne.get(i).getPrenom();
				String email = listAuth.get(i).getEmail();
				String mdp = listAuth.get(i).getMDP();
				String statut = listAuth.get(i).getStatut();
				
				String[] valeurs = {nom, prenom, email, mdp, statut};

				if (buttonSelect.equals(listAuth.get(i).getStatut()))
					valeursTableau.addRow(valeurs);
		}
	}

	
	
	public void refreshEleves(JComboBox combo_box_groupes) {
		
		AuthentificationDAO authDao = new AuthentificationDAO();
		ArrayList<Personne> listPersonne = authDao.getListPersonne();
		ArrayList<Authentification> listAuth = authDao.getListAuth();
		ArrayList<Etudiant> listEtudiant = authDao.getListEtudiant();
		
		//Efface les anciennes valeurs
		while(valeursTableauEl.getRowCount()>0) {
			valeursTableauEl.removeRow(valeursTableauEl.getRowCount()-1);
		}
		
		for(int i=0; i<listEtudiant.size(); i++) {
				
				String nom = listEtudiant.get(i).getNom();
				String prenom = listEtudiant.get(i).getPrenom();
				String[] valuesEl = {nom, prenom};

				if (combo_box_groupes.getSelectedItem().equals(listEtudiant.get(i).getGroupe()));
					valeursTableauEl.addRow(valuesEl);
		}
	}
	
	public void refreshAbsEleve() {
		/*
		AuthentificationDAO authDao = new AuthentificationDAO();
		
		ArrayList<Personne> listPersonne = authDao.getListPersonne();
		ArrayList<Authentification> listAuth = authDao.getListAuth();
		ArrayList<Etudiant> listEtudiant = authDao.getListEtudiant();
		
		
		//Efface les anciennes valeurs
		while(valeursTableau.getRowCount()>0) {
			valeursTableau.removeRow(valeursTableau.getRowCount()-1);
		}
		
		for(int i=0; i<listAuth.size(); i++) {
			
			String nom = listEtudiant.get(i).getNom();
			String prenom = listEtudiant.get(i).getPrenom();
			String seance = 
			
					
			
			String[] valeursAbs = {nom,prenom, seance, date, duree_seance};

			valeursTableauAbs.addRow(valeursAbs);
		}*/
		
		totSeances = new ArrayList<Seance>();
		combo_box_etudiants.setSelectedIndex(0);
		this.etudiant = new EtudiantDAO(Integer.parseInt(combo_box_etudiants.getSelectedItem().toString().split("-")[0])).getEtudiant();
		listAbsences = new AbsencesEleveDAO(this.etudiant.getID()).getAbsences();
		
		while(valeursTableauAbs.getRowCount() > 0)
			valeursTableauAbs.removeRow(valeursTableauAbs.getRowCount()-1);
		
		for(int i = 0; i < listAbsences.size(); i++) {
			int indexSeances = 0;
			int indexCours = 0;
			while(listAbsences.get(i).getIdSeance() != totSeances.get(indexSeances).getID()) {
				indexSeances++;
			}
			while(totSeances.get(indexSeances).getIDCours() != cours.get(indexCours).getID()) {
				indexCours++;
			}
			String[] values = new String[6];
			values[0] = totSeances.get(indexSeances).getDate();
			values[1] = totSeances.get(indexSeances).getDuree()+"h";
			values[2] = totSeances.get(indexSeances).getHeure()+"h30";
			values[3] = cours.get(indexCours).getMatiere();
			if(listAbsences.get(i).getEtat()) {
				values[4] = "Justifiee";
			}else {
				values[4] = "Non justifiee";
			}
			
			if(listAbsences.get(i).getJustificatif() == null) {
				values[5] = "Aucun justificatif depose";
			}else {
				values[5] = "Justificatif en ligne";
			}
			valeursTableauAbs.addRow(values);
		}
	}


	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == JBGerer) {
			cardLayoutCentre.show(content, listeCard[0]);

		}
		
		if(e.getSource() == JBAbsences) {
			cardLayoutCentre.show(content,listeCard[1]);
		}
		
		if(e.getSource() == JBGroupes) {
			cardLayoutCentre.show(content,listeCard[2]);
		}
		
		if(e.getSource() == rdoEtudiant) {
			buttonSelect = "etudiant";
        	refreshUtilisateur();
		}
		
		if(e.getSource() == rdoEnseignant) {
			buttonSelect = "enseignant";
        	refreshUtilisateur();
		}
		
		if(e.getSource() == rdoGroupe) {
			buttonSelect = "groupe";
		}
		
		if(e.getSource() == rdoCours) {
			buttonSelect = "cours";
		}
		
		if(e.getSource() == JBAjout) {
			if(buttonSelect.equals("etudiant")) {
				//ouvre la fenetre d'ajout d'un etudiant
				this.setVisible(false);
				new UIAjoutEtudiant(this);
			}else if(buttonSelect.equals("enseignant")){
				//ouvre la fenetre d'ajout d'un enseignant
				this.setVisible(false);
				new UIAjoutEnseignant(this);
			}
			
		}
		
		if(e.getSource() == JBModif) {
			
		}
		
		if(e.getSource() == JBSupp) {
			if(buttonSelect.equals("etudiant") || buttonSelect.contentEquals("enseignant")) {
				//on supprime l'etudiant selectionne dans la liste
				int index = listeUtilisateur.getSelectedRow();
				String email = (String) listeUtilisateur.getModel().getValueAt(index, 2);
				System.out.println("Supression de : " + email);
				new suppEtudiantDAO().deleteEtudiant(email);
				refreshUtilisateur();
			}else{
				
			}
			
			//new suppEtudiantDAO.deteleteEtudiant();
			
		}
		
		if(e.getSource() == JBDeconnexion) {
			System.out.println("D�connexion");
			this.setVisible(false);
			this.dispose();
			new UILogin();
		}
	}

}