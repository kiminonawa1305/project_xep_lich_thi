package project_view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.TransferHandler;

import project_model.Graph;
import project_model.UnGraph;

public class FileDrop extends JFrame {
	private JPanel contentPane;
    private JLabel dropLabel;
    private Graph graph;
    private FileDropHandler dropHandler;
	private PanelGraph panelGraph;

    public FileDrop() throws IOException {
    	graph = new UnGraph();
    	this.init();
    	this.setVisible(true);
    }
    
    public Graph getGraph() {
		return graph;
	}

	public JPanel getContentPane() {
		return contentPane;
	}

	public PanelGraph getPanelGraph() {
		return panelGraph;
	}

	public FileDropHandler getDropHandler() {
		return dropHandler;
	}

	public void init() {
		this.setTitle("Kéo thả file");
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 200, (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 200);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        
        panelGraph = new PanelGraph(this);

        dropLabel = new JLabel("Drag a file here");
        dropLabel.setHorizontalAlignment(JLabel.CENTER);
        dropLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        dropHandler = new FileDropHandler(this);
        dropLabel.setTransferHandler(dropHandler);
        contentPane.add(dropLabel, BorderLayout.CENTER);
        this.setContentPane(contentPane);
    }
	
	public JLabel getDropLabel() {
		return dropLabel;
	}
	
	public void setPanelGraph() {
		panelGraph.setTransferHandler(dropHandler);
		this.setContentPane(panelGraph);
		this.repaint();
		this.validate();
	}

	public void setDropLabel(JLabel dropLabel) {
		this.dropLabel = dropLabel;
	}
	
	  public static void main(String[] args) throws IOException {
	    	FileDrop test = new FileDrop();
	    }
}

