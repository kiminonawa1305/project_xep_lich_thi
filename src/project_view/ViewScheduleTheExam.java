package project_view;

import java.awt.*;
import java.util.Date;

import javax.swing.*;

public class ViewScheduleTheExam extends JFrame {
	private final int HEIGHT = 978, WIDTH=1739;
	private JPanel listExam, panelFrame;
	private JTable table;

	public ViewScheduleTheExam() {
		this.init();
		this.setVisible(true);
	}

	public void init() {
		this.setTitle("Schedule The Exam");
		this.setSize(WIDTH, HEIGHT);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		
		JPanel panelThuTren = new JPanel();
		JPanel panelThuDuoi = new JPanel();
		panelThuTren.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		panelThuDuoi.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		
		for (int i = 2; i < 9; i++) {
			JLabel text = new JLabel();
			
			text.setPreferredSize(new Dimension(180, 50));
			text.setHorizontalAlignment(JLabel.CENTER);
			text.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			text.setBackground(Color.decode("#007bff"));
			text.setOpaque(true);
			
			JLabel text1 = new JLabel("Thứ " + i);
			text1.setPreferredSize(new Dimension(180, 50));
			text1.setHorizontalAlignment(JLabel.CENTER);
			text1.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			text1.setBackground(Color.decode("#007bff"));
			text1.setOpaque(true);
			
			panelThuTren.add(text);
			panelThuDuoi.add(text1);
		}
		
		JPanel panelCaTrai = new JPanel();
		JPanel panelCaPhai = new JPanel();
		panelCaTrai.setLayout(new GridLayout(0, 1));
		panelCaPhai.setLayout(new GridLayout(0, 1));
		for (int i = 1; i < 5; i++) {
			JLabel text = new JLabel("Ca " + i);
			text.setPreferredSize(new Dimension(132, 200));
			text.setHorizontalAlignment(JLabel.CENTER);
			text.setBackground(Color.decode("#007bff"));
			text.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			text.setOpaque(true);
			
			JLabel text1 = new JLabel();
			text1.setPreferredSize(new Dimension(134, 200));
			text1.setBackground(Color.decode("#007bff"));
			text1.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			text1.setOpaque(true);
			
			panelCaTrai.add(text);
			panelCaPhai.add(text1);
		}

		this.panelFrame = new JPanel();
		this.panelFrame.setLayout(new BorderLayout());
		this.panelFrame.setOpaque(true);
		this.panelFrame.setBackground(Color.decode("#444654"));
		this.panelFrame.setPreferredSize(new Dimension(WIDTH - 215, HEIGHT));
		this.panelFrame.add(panelThuTren, BorderLayout.NORTH);
		this.panelFrame.add(panelThuDuoi, BorderLayout.SOUTH);
		this.panelFrame.add(panelCaPhai, BorderLayout.EAST);
		this.panelFrame.add(panelCaTrai, BorderLayout.WEST);
//		this.panelFrame.add(panelFrame, BorderLayout.CENTER);
		
		this.listExam = new JPanel(new GridLayout(0, 1));
		this.listExam.setOpaque(true);
		this.listExam.setBackground(Color.decode("#007bff"));
		for (int i = 0; i < 100; i++) {
			JLabel text = new JLabel("Môn học " + (i + 1));
			text.setPreferredSize(new Dimension(180, 50));
			text.setBorder(BorderFactory.createLineBorder(Color.white));
			text.setHorizontalAlignment(JLabel.CENTER);
			this.listExam.add(text);
		}

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(listExam);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(200, 300));

		this.add(panelFrame, BorderLayout.WEST);
		this.add(scrollPane, BorderLayout.EAST);
	}

}
