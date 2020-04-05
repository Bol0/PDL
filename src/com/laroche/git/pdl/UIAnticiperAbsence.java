package com.laroche.git.pdl;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class UIAnticiperAbsence extends JFrame implements ActionListener{

	private int id_etudiant;
	private Absences absence;
	private ArrayList<Seance> seances;
	private JButton JBValider, JBJustificatif;
	private JComboBox JCBJours, JCBHeures;
	private JLabel lblJustificatif, lblCours;
	private ArrayList<Cours> cours;
	private String directory,name;
	private UIEtudiant ob;
	
	public UIAnticiperAbsence(int id_etudiant,ArrayList<Seance> seances,ArrayList<Cours> cours,UIEtudiant ob) {
		this.id_etudiant = id_etudiant;
		this.absence = null;
		this.seances = seances;
		this.cours = cours;
		this.ob = ob;
		name = "Aucun fichier selectioné";
		
		
		this.setSize(400, 300);
		this.getContentPane().setLayout(new BorderLayout(0, 0));
		this.setLocationRelativeTo(null);
		this.setTitle("Anticiper absence");
		lblCours = new JLabel("Aucun cours selectionné");
		
		//ajout du boutton de soumission
		JBValider = new JButton("Soumettre absence");
		JBValider.addActionListener(this);
		JBValider.setEnabled(false);
		this.add(JBValider, BorderLayout.SOUTH);
		
		JPanel panel = new BackgroundPanel();
		this.add(panel, BorderLayout.CENTER);
		
		panel.setLayout(new GridLayout(4, 2, 10, 10));
		
		//ajout de la section jours :
		JLabel lblJours = new JLabel("Jours");
		lblJours.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblJours);
		
		JCBJours = new JComboBox();
		for(int i = 0; i < seances.size(); i++) {
			if(((DefaultComboBoxModel)JCBJours.getModel()).getIndexOf(seances.get(i).getDate()) == -1)
			JCBJours.addItem(seances.get(i).getDate());
		}
		JCBJours.addActionListener(this);
		panel.add(JCBJours);
		
		//ajout de la section heure
		JLabel lblHeure = new JLabel("Heure");
		lblHeure.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblHeure);
		
		JCBHeures = new JComboBox();
		JCBHeures.addActionListener(this);
		updateHeures();
		panel.add(JCBHeures);
		
		//section justificatif
		lblJustificatif = new JLabel(name);
		lblJustificatif.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblJustificatif);
		
		JBJustificatif = new JButton("charger justificatif");
		JBJustificatif.setIcon(new ImageIcon("drawables/upload.png"));
		JBJustificatif.addActionListener(this);
		panel.add(JBJustificatif);
		
		
		lblCours.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblCours);
		updateCours();
		
		this.setVisible(true);
		
	}
	
	private void updateCours() {
		int coursID = -1;
		for(int i = 0; i < seances.size(); i++) {
			if(seances.get(i).getDate() == JCBJours.getSelectedItem() && (seances.get(i).getHeure()+"h30").equals(JCBHeures.getSelectedItem())) {
				coursID = seances.get(i).getIDCours();
			}
		}
		if(coursID != -1) {
			for(int i = 0; i < cours.size(); i++) {
				if(cours.get(i).getID() == coursID) {
					lblCours.setText(cours.get(i).getMatiere());
				}
			}
			
		}
	}
	
	private void updateHeures() {
		JCBHeures.removeAllItems();
		for(int i = 0; i < seances.size(); i++) {
			if(seances.get(i).getDate() == JCBJours.getSelectedItem()) {
				if(((DefaultComboBoxModel)JCBHeures.getModel()).getIndexOf(seances.get(i).getHeure()) == -1)
				JCBHeures.addItem(seances.get(i).getHeure()+"h30");
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == JCBJours) {
			//update les heures et le cours
			updateHeures();
			updateCours();
		}
		
		if(e.getSource() == JCBHeures) {
			updateCours();
		}
		
		if(e.getSource() == JBJustificatif) {
			JFileChooser fc = new JFileChooser();
			 int returnVal = fc.showOpenDialog(null);
		        if(returnVal == JFileChooser.APPROVE_OPTION) {
		            name = fc.getSelectedFile().getName();
		            directory = fc.getSelectedFile().getPath();
		            System.out.println(directory);
		            JBValider.setEnabled(true);
		            lblJustificatif.setText(name);
		        }
		}
		
		if(e.getSource() == JBValider) {
			//on ajoute l'absence a la bdd
			int id_seance = 0;
			for(int i = 0; i < seances.size(); i++) {
				if(seances.get(i).getDate() == JCBJours.getSelectedItem() && (seances.get(i).getHeure()+"h30").equals(JCBHeures.getSelectedItem())) {
					id_seance = seances.get(i).getID();
				}
			}
			new BDDAnticiperAbsence(id_seance,id_etudiant,directory,name);
			
			
			//on ajoute l'absence a la liste
			ob.refreshAbsences();
			
			//on ferme la fenetre
			this.setVisible(false);
			this.dispose();
		}
	}
	
}
