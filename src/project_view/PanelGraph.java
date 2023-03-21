package project_view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

import project_model.Graph;

public class PanelGraph extends JPanel{
	private FileDrop fileDrop;
	private List<Point> listVertex;
	private int boundaryX = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 200;
	private int boundaryY = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 200;
	public PanelGraph(FileDrop fileDrop) {
		this.fileDrop = fileDrop;
	}

	public void createVertex() {
		listVertex = new ArrayList<>();
		Random random = new Random();
		for(int i = 0; i < fileDrop.getGraph().numVertexs(); i++) {
			listVertex.add(new Point(random.nextInt(20, boundaryX - 20), random.nextInt(20, boundaryY - 20)));
		}
	}

	@Override
	protected void paintChildren(Graphics g) {
		super.paintChildren(g);
		
		Graph graph = fileDrop.getGraph();
		
		//Vẽ các đỉnh
		g.setColor(Color.blue);
		for(int i = 0; i < graph.numVertexs(); i++) {
			g.fillOval(listVertex.get(i).x - 10, listVertex.get(i).y - 10, 20, 20);
		}
		
		g.setColor(Color.black);
		for(int i = 0; i < graph.numVertexs(); i++) {
			for(int j = 0; j < graph.numVertexs(); j++) {
				if(graph.getMatrix()[i][j] != 0) {
					g.drawLine(listVertex.get(i).x, listVertex.get(i).y, listVertex.get(j).x, listVertex.get(j).y);
				}
			}
		}
	}
	
	
}
