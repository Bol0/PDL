package com.laroche.git.pdl;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class UIEtudiant extends JFrame implements ActionListener{
	
	private int userID;
	private JButton JBProfil,JBPlanning,JBAbsences,JBDeconnexion;
	private JPanel content,JPProfil,JPPlanning,JPAbsences;
	private String[] listeCard = {"card_profil", "card_planning", "card_absences"};
	private CardLayout cardLayoutCentre = new CardLayout();
	
	public UIEtudiant(int userID) {
		this.userID = userID;
		this.setSize(450, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new BorderLayout(0, 0));
		this.setLocationRelativeTo(null);
		System.out.println(userID);
		
		content = new JPanel();
		JPProfil = new JPanel();
		JPPlanning = new JPanel();
		JPAbsences = new JPanel();
		
		//On definit chaque JPanel : Profil, Planning et absences
		
		//profil :
		int offset_y = 30;
		JPProfil.setLayout(null);
		JLabel lblNom = new JLabel("Nom :");
		lblNom.setBounds(157, 79-offset_y, 287, 16);
		JPProfil.add(lblNom);
		
		JLabel lblPrenom = new JLabel("Prenom :");
		lblPrenom.setBounds(157, 107-offset_y, 287, 16);
		JPProfil.add(lblPrenom);
		
		JLabel lblStatut = new JLabel("Statut :");
		lblStatut.setBounds(157, 135-offset_y, 287, 16);
		JPProfil.add(lblStatut);
		
		JLabel lblFilire = new JLabel("Filière :");
		lblFilire.setBounds(157, 163-offset_y, 287, 16);
		JPProfil.add(lblFilire);
		
		JLabel lblGroupe = new JLabel("Groupe :");
		lblGroupe.setBounds(157, 191-offset_y, 287, 16);
		JPProfil.add(lblGroupe);
		
		JLabel lblIcone = new JLabel(new ImageIcon("drawables/StudentIcone.png"));
		lblIcone.setHorizontalAlignment(SwingConstants.TRAILING);
		lblIcone.setBounds(28, 93-offset_y, 100, 100);
		JPProfil.add(lblIcone);
		
		//JPProfil.setBackground(Color.blue);
		JPPlanning.setBackground(Color.red);
		JPAbsences.setBackground(Color.pink);
		
		JPanel panel = new BackgroundPanel();
		JBProfil = new JButton("Profil");
		JBPlanning = new JButton("Planning");
		JBAbsences = new JButton("Absences");
		JBDeconnexion = new JButton("Déconnexion");
		
		JBProfil.addActionListener(this);
		JBPlanning.addActionListener(this);
		JBAbsences.addActionListener(this);
		JBDeconnexion.addActionListener(this);
		
		panel.add(JBProfil);
		panel.add(JBPlanning);
		panel.add(JBAbsences);
		panel.add(JBDeconnexion);
		
		content.setLayout(cardLayoutCentre);
		content.add(JPProfil,listeCard[0]);
		content.add(JPPlanning,listeCard[1]);
		content.add(JPAbsences,listeCard[2]);
		
		

		this.getContentPane().add(panel, BorderLayout.NORTH);
		this.getContentPane().add(content,BorderLayout.CENTER);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == JBProfil) {
			cardLayoutCentre.show(content, listeCard[0]);
		}
		
		if(e.getSource() == JBPlanning) {
			cardLayoutCentre.show(content,listeCard[1]);
		}
		
		if(e.getSource() == JBAbsences) {
			cardLayoutCentre.show(content,listeCard[2]);
		}
		
		if(e.getSource() == JBDeconnexion) {
			System.out.println("Déconnexion");
			this.setVisible(false);
			this.dispose();
			new UILogin();
		}
	}

}


