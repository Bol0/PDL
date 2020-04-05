package com.laroche.git.pdl;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;


public class UIEtudiant extends JFrame implements ActionListener{
	
	private int userID;
	private JButton JBProfil,JBPlanning,JBAbsences,JBNotes,JBDeconnexion,JBJustifier_absence,JBAnticiper_absence;
	private JPanel content,JPProfil,JPPlanning,JPAbsences,JPNotes;
	private String[] listeCard = {"card_profil", "card_planning", "card_absences","card_notes"};
	private CardLayout cardLayoutCentre = new CardLayout();
	private Etudiant profil;
	private Groupe groupe;
	private ArrayList<Cours> cours;
	private JLabel absencesErreur;
	private JTable JTAbsences;
	private ArrayList<Absences> absences;
	private ArrayList<Seance> totSeances;
	private DefaultTableModel modelTableAbsences;
	
	public UIEtudiant(int userID) {
		this.userID = userID;
		totSeances = new ArrayList<Seance>();
		modelTableAbsences = new DefaultTableModel();
		
		//enregistre le profil de l'etudiant
		this.profil = new BDDEtudiant(this.userID).getEtudiant();
		
		//enregistre le groupe de l'étudiant
		groupe = new BDDGroupe(this.profil.getGroupe()).getGroupe();
		
		//récupération des cours
		cours = this.groupe.getCours();
		
		//récupération des absences
		absences = new BDDAbsencesEleve(this.profil.getID()).getAbsences();
		
		
		this.setSize(550, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new BorderLayout(0, 0));
		this.setLocationRelativeTo(null);
		this.setTitle("Etudiant");
		//this.setResizable(false);
		System.out.println(userID);
		
		content = new JPanel();
		JPProfil = new JPanel();
		JPPlanning = new JPanel();
		JPAbsences = new JPanel();
		JPNotes = new JPanel();
		
		//On definit chaque JPanel : Profil, Planning et absences
		
		//affichage du profil			******************************
		JPProfil.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("/Users/bolo/Documents/PDL/phase2/StudentIcone.png"));
		JPProfil.add(lblNewLabel, BorderLayout.WEST);
		
		JPanel panel_2 = new JPanel();
		JPProfil.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new GridLayout(6, 1, 0, 0));
		
		panel_2.add(new JLabel(""));
		
		JLabel lblNom = new JLabel("\tNom : " + profil.getNom());
		panel_2.add(lblNom);
		lblNom.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel lblPrenom = new JLabel("\tPrenom : " + profil.getPrenom());
		panel_2.add(lblPrenom);
		lblPrenom.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblFilire = new JLabel();
		if(profil.getFiliere()) {
			lblFilire.setText("\tFilière : classique");
		}else {
			lblFilire.setText("\tFilière : alternant");
		}
		panel_2.add(lblFilire);
		lblFilire.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblGroupe = new JLabel("\tGroupe : " + profil.getGroupe());
		panel_2.add(lblGroupe);
		lblGroupe.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		//affichage du planning			******************************
		JPPlanning.setLayout(new BorderLayout(0, 0));
		JScrollPane scrollPane = new JScrollPane();
		JPPlanning.add(scrollPane, BorderLayout.WEST);
		
		//liste des cours
		JList listCours = new JList();
		listCours.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultListModel modelListCours = new DefaultListModel();
		modelListCours.addElement("            ");
		
		//ajout des cours a la liste
		for(int i = 0; i < cours.size(); i++) {
			modelListCours.addElement(cours.get(i).getMatiere());
		}
		listCours.setModel(modelListCours);
		scrollPane.setViewportView(listCours);
		
		//nom de la liste
		JLabel lblCours = new JLabel("Cours");
		lblCours.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane.setColumnHeaderView(lblCours);
		
		//planning
		JPanel panel2 = new JPanel();
		JPPlanning.add(panel2, BorderLayout.CENTER);
		panel2.setLayout(new GridLayout(12, 6, 0, 0));
		
		//testtttttttttttttt
		JLabel[][] cellules = new JLabel[6][12];
		
		for(int i = 0; i < 12; i++) {
			for(int ii = 0; ii < 6; ii++) {
				cellules[ii][i] = new JLabel();
				cellules[ii][i].setOpaque(true);
				cellules[ii][i].setHorizontalAlignment(SwingConstants.CENTER);
				//cellules[ii][i].setToolTipText(i+":"+ii);
				panel2.add(cellules[ii][i]);
				//cellules[ii][i].setBorder(BorderFactory.createLineBorder(Color.black));
			}
		}
		cellules[0][0].setText("heures");
		cellules[1][0].setText("Lundi");
		cellules[2][0].setText("Mardi");
		cellules[3][0].setText("Mercredi");
		cellules[4][0].setText("Jeudi");
		cellules[5][0].setText("Vendredi");
		for(int i = 1; i<12; i++) {
			cellules[0][i].setText((7+i)+"h30");
		}
		
		for(int i = 1; i < 6;i++) {
			cellules[i][5].setBackground(Color.GREEN);
		}
		
		for(int i = 0; i < cours.size(); i++) {
			ArrayList<Seance> seances = cours.get(i).getSeances(); 
			for(int ii = 0; ii < seances.size();ii++) {
				totSeances.add(seances.get(ii));
				Seance seance = seances.get(ii);
				int jours = 1;
				int horaire = seance.getHeure()-7;
				switch(seance.getDate()) {
				case "lundi":
					jours = 1;
					break;
				case "mardi":
					jours = 2;
					break;
				case "mercredi":
					jours = 3;
					break;
				case "jeudi":
					jours = 4;
					break;
				case "vendredi":
					jours = 5;
					break;
				default :
					jours = 6;
					break;
				}
				if(jours != 6) {
					for(int duree = 0; duree < seance.getDuree();duree++) {
						cellules[jours][horaire+duree].setText(cours.get(i).getMatiere());
						cellules[jours][horaire+duree].setToolTipText("<html>"+seance.getSalle()+"<br>"+seance.getType()+"</html>");
						cellules[jours][horaire+duree].setBackground(Color.LIGHT_GRAY);
					}
				}
			}
		}
		
		listCours.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				String valeur = (String) listCours.getSelectedValue();
				for(int i = 0; i < 12; i++) {
					for(int ii = 0; ii < 6; ii++) {
						if(cellules[ii][i].getText().contentEquals(valeur)) {
							cellules[ii][i].setBorder(BorderFactory.createLineBorder(Color.RED));
						}else {
							cellules[ii][i].setBorder(null);
						}
					}
				}
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		
		//affichage des absences **************************************
		JPAbsences.setLayout(new BorderLayout());
		
		//ajout du panel du bas avec ses boutons
		JPanel panel3 = new JPanel();
		JPAbsences.add(panel3, BorderLayout.SOUTH);
		panel3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JBAnticiper_absence = new JButton("Anticiper absence");
		panel3.add(JBAnticiper_absence);
		JBAnticiper_absence.addActionListener(this);
		
		JBJustifier_absence = new JButton("Justifier absence");
		panel3.add(JBJustifier_absence);
		JBJustifier_absence.addActionListener(this);
		
		//ajout du label pour l'affichage des erreurs
		absencesErreur = new JLabel();
		absencesErreur.setForeground(Color.RED);
		absencesErreur.setHorizontalAlignment(SwingConstants.CENTER);
		JPAbsences.add(absencesErreur, BorderLayout.NORTH);
		
		//ajout du scrollpane avec le trableau
		JScrollPane scrollPane1 = new JScrollPane();
		JPAbsences.add(scrollPane1, BorderLayout.CENTER);
		
		String [] tabs = {"date","durée","heure","cours","etat","justificatif"};
		String [][] values = new String[absences.size()][0];
		modelTableAbsences = new DefaultTableModel(values,tabs);
		refreshAbsences();
		JTAbsences = new JTable(modelTableAbsences);
		scrollPane1.setViewportView(JTAbsences);
		
		//JPProfil.setBackground(Color.blue);
		//JPPlanning.setBackground(Color.red);
		//JPAbsences.setBackground(Color.pink);
		JPNotes.setBackground(Color.CYAN);
		
		JPanel panel = new BackgroundPanel();
		panel.setLayout(new FlowLayout());
		JBProfil = new JButton("Profil");
		JBPlanning = new JButton("Planning");
		JBAbsences = new JButton("Absences");
		JBNotes = new JButton("Notes");
		JBDeconnexion = new JButton("Déconnexion");
		
		JBProfil.addActionListener(this);
		JBPlanning.addActionListener(this);
		JBAbsences.addActionListener(this);
		JBNotes.addActionListener(this);
		JBDeconnexion.addActionListener(this);
		
		panel.add(JBProfil);
		panel.add(JBPlanning);
		panel.add(JBAbsences);
		panel.add(JBNotes);
		panel.add(JBDeconnexion);
		
		content.setLayout(cardLayoutCentre);
		content.add(JPProfil,listeCard[0]);
		content.add(JPPlanning,listeCard[1]);
		content.add(JPAbsences,listeCard[2]);
		content.add(JPNotes,listeCard[3]);
		
		JBProfil.setEnabled(false);
		this.getContentPane().add(panel, BorderLayout.NORTH);
		this.getContentPane().add(content,BorderLayout.CENTER);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == JBProfil) {
			cardLayoutCentre.show(content, listeCard[0]);
			JBProfil.setEnabled(false);
			JBPlanning.setEnabled(true);
			JBAbsences.setEnabled(true);
			JBNotes.setEnabled(true);
		}
		
		if(e.getSource() == JBPlanning) {
			cardLayoutCentre.show(content,listeCard[1]);
			JBPlanning.setEnabled(false);
			JBProfil.setEnabled(true);
			JBAbsences.setEnabled(true);
			JBNotes.setEnabled(true);
		}
		
		if(e.getSource() == JBAbsences) {
			cardLayoutCentre.show(content,listeCard[2]);
			JBAbsences.setEnabled(false);
			JBPlanning.setEnabled(true);
			JBProfil.setEnabled(true);
			JBNotes.setEnabled(true);
		}
		
		if(e.getSource() == JBNotes) {
			cardLayoutCentre.show(content,listeCard[3]);
			JBAbsences.setEnabled(true);
			JBPlanning.setEnabled(true);
			JBProfil.setEnabled(true);
			JBNotes.setEnabled(false);
		}
		
		if(e.getSource() == JBDeconnexion) {
			System.out.println("Déconnexion");
			this.setVisible(false);
			this.dispose();
			new UILogin();
		}
		
		if(e.getSource() == JBAnticiper_absence) {
			new UIAnticiperAbsence(profil.getID(),totSeances,cours,this);
		}
	}
	
	public void refreshAbsences() {
		
		absences = new BDDAbsencesEleve(this.profil.getID()).getAbsences();
		
		while(modelTableAbsences.getRowCount() > 0)
		modelTableAbsences.removeRow(modelTableAbsences.getRowCount()-1);
		
		for(int i = 0; i < absences.size(); i++) {
			int indexSeances = 0;
			int indexCours = 0;
			while(absences.get(i).getIdSeance() != totSeances.get(indexSeances).getID()) {
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
			if(absences.get(i).getEtat()) {
				values[4] = "Justifié";
			}else {
				values[4] = "Non justifié";
			}
			
			if(absences.get(i).getJustificatif() == null) {
				values[5] = "Aucun justificatif déposé";
			}else {
				values[5] = "Justificatif en ligne";
			}
			modelTableAbsences.addRow(values);
		}
		
	}

}


