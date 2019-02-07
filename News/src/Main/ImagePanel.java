package Main;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

	  /**
	 * 
	 */
	private static final long serialVersionUID = -1863248325215379026L;
	private BufferedImage img;
	private boolean four;

	  public ImagePanel(Image img, boolean four) {
		  this.four= four;
		  if(!this.four)
			  this.img = createResizedCopy((BufferedImage)img, (int)(50*1.777777777777778), 50,true);
		  else
			  this.img= createResizedCopy((BufferedImage)img, 50, 50,true);
	    
	    setLayout(null);
	  }

	  public void paintComponent(Graphics g) {
		  if(!this.four)
			  g.drawImage(img, (int)-((50*1.777777777777778)-50)/2, 0, null);
		  else
			  g.drawImage(img, 0, 0, null);
	  }
	  
	  private BufferedImage createResizedCopy(Image originalImage, int scaledWidth, int scaledHeight, boolean preserveAlpha)
	    {
	        int imageType = preserveAlpha ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
	        BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight, imageType);
	        Graphics2D g = scaledBI.createGraphics();
	        if (preserveAlpha) {
	            g.setComposite(AlphaComposite.Src);
	        }
	        g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null); 
	        g.dispose();
	        return scaledBI;
	    }

	}