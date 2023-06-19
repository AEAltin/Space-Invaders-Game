package view;

import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;

public class GeneralPanel extends JPanel{
	
	protected Font font = null;
	private final float defaultFontSize = 32f;
	private Image backgroundImage;
	
	public GeneralPanel() {
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, new File("Fonts\\MachineStd-Bold.otf"));
			GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
			env.registerFont(font);
		}
		catch(Exception e) {
			font = new Font("Serif", Font.BOLD, 24);
			e.printStackTrace();
		}
	}
	public GeneralPanel(String imageFileName) {
		this();
		backgroundImage = null;
		try {
            backgroundImage = ImageIO.read(new File(System.getProperty("user.dir") + "\\Images\\" + imageFileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	@Override
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    g.drawImage(backgroundImage, 0, 0, this);
	}
	public JLabel addLabel(String text, float positionRatioX, float positionRatioY) {
		return addLabel(text, positionRatioX, positionRatioY, defaultFontSize);
	}
	public JLabel addLabel(String text, float positionRatioX, float positionRatioY, float fontSize) {
		JLabel label = new JLabel(text);
		label.setFont(font.deriveFont(fontSize));
		label.setForeground(new Color(188, 253, 196));
		Dimension labelSize = label.getPreferredSize();
		label.setBounds((int)(MainFrame.WIDTH * positionRatioX - labelSize.width/2), (int)(MainFrame.HEIGHT * positionRatioY - labelSize.height/2), labelSize.width, labelSize.height);
		setLayout(null);
		add(label);
		return label;
	}
}