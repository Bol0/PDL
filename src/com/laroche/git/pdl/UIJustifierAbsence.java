package com.laroche.git.pdl;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class UIJustifierAbsence extends JFrame implements ActionListener{
	
	private Absences absence;
	private JButton JBUpload, JBDownload, JBValider;
	private JLabel JLUpload, JLDownload;
	private String name, directory;
	private JFileChooser fc;
	private UIEtudiant ob;
	
	
	public UIJustifierAbsence(Absences absence, UIEtudiant ob) {
		this.absence = absence;
		this.ob = ob;
		fc = new JFileChooser();
		
		this.setSize(365, 210);
		this.getContentPane().setLayout(new BorderLayout(0, 0));
		this.setLocationRelativeTo(null);
		this.setTitle("Justificatif");
		
		JBValider = new JButton("Valider");
		JBValider.setEnabled(false);
		this.add(JBValider, BorderLayout.SOUTH);
		
		JPanel panel = new BackgroundPanel();
		this.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(2, 2, 0, 20));
		
		JLDownload = new JLabel("Consulter justificatif :");
		JLDownload.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(JLDownload);
		
		JBDownload = new JButton("Telecharger");
		panel.add(JBDownload);
		
		JLUpload = new JLabel("Soumettre justificatif :");
		JLUpload.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(JLUpload);
		
		JBUpload = new JButton("Charger");
		panel.add(JBUpload);
		
		//on ne peu pas telecharger l'absence si il n'y a aucun justificatif en ligne
		if(absence.getJustificatif() == null) {
			JBDownload.setEnabled(false);
		}
		
		JBUpload.setEnabled(!absence.getEtat()); //on ne peu plus modifier le justificatif si l'absence est justifié
		
		//on set les listeners
		JBDownload.addActionListener(this);
		JBUpload.addActionListener(this);
		JBValider.addActionListener(this);
		
		this.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == JBUpload) {
			 int returnVal = fc.showOpenDialog(null);
		        if(returnVal == JFileChooser.APPROVE_OPTION) {
		            name = fc.getSelectedFile().getName();
		            directory = fc.getSelectedFile().getPath();
		            System.out.println(directory);
		            JBValider.setEnabled(true);
		            JLUpload.setText(name);
		            JBValider.setEnabled(true);
		        }
		}
		
		if(e.getSource() == JBDownload) {
			JFileChooser chooser = new JFileChooser();
		    chooser.setDialogTitle("Fichier de téléchargement");
		    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		    chooser.setAcceptAllFileFilterUsed(false);

		    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
		      System.out.println("getCurrentDirectory(): " + chooser.getSelectedFile());
		      Blob fichier = absence.getJustificatif();
		      String nomFichier = absence.getNomJustificatif();
		      
		      
		    		  try {
		    			  byte data[] = fichier.getBytes(1, (int)fichier.length());
		    			  FileOutputStream out = new FileOutputStream(chooser.getSelectedFile()+"/"+nomFichier);
		    			  out.write(data);
		    			  out.close();
		    			  JBDownload.setEnabled(false);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		    		  
		    }
		}
		
		if(e.getSource() == JBValider) {
			//on ajoute l'absence a la liste
			try {
				BDD db = new BDD();
				db.post("UPDATE \"HR\".\"ABSENCE\" SET NOM_JUSTIFICATIF_ABSENCE = '"+name+"' WHERE id_absence = '"+absence.getIdAbsence()+"'");
				db.postBlob(directory, absence.getIdAbsence());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			ob.refreshAbsences();
			
			//on ferme la fenetre
			this.setVisible(false);
			this.dispose();
		}
		
	}

}
