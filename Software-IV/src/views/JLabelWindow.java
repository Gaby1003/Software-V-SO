package views;

import java.awt.*;

import javax.swing.*;

public class JLabelWindow extends JLabel {

	private static final long serialVersionUID = 1L;
	private int arcW;
	private int arcH;

	public JLabelWindow(String text, Font font, Color foreground, Color back) {
		super(text);
		setForeground(foreground);
		setBackground(back);
		setFont(font);
	}
	
	public JLabelWindow(int acrW, int arcH, String icon, int hei, int wei) {
		this.arcH = arcH;
		this.arcW = acrW;
		this.scalable(icon, hei, wei);
		this.setBackground(Constant.COLOR_GRAY);
	}

	public void scalable(String icon, int hei, int wei) {
		Image image = new ImageIcon(getClass().getResource(icon)).getImage();
		Image scalablec = image.getScaledInstance(hei, wei, 100);
		ImageIcon imageIcon = new ImageIcon(scalablec);
		this.setIcon(imageIcon);
	}

	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(getBackground());
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arcW, arcH);
		super.paintComponent(g);
	}

	protected void paintBorder(Graphics g) {
		g.setColor(getBackground());
		g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arcW, arcH);
	}
}
