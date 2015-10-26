import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

	import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.omg.CORBA.OMGVMCID;
	import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.core.CvType;




	import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

	import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

	import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

	import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
	import java.io.File;

import javax.imageio.ImageIO;


	public class BlackAndWhite {

		public static String matToCSVString(Mat m){
			// Precondition: m is a matrix x*y*1 (gray image)
			Size size = m.size();
			StringBuilder stringBuilder = new StringBuilder();
			byte[] d = new byte[(int) (size.width * size.height)];
			m.get(0, 0, d);
		    for (int x = 0; x < size.height; x++) {
		    	for (int y = 0; y < size.width; y++) {
		    		if (stringBuilder.length()>0)
		    			stringBuilder.append(",");
		    		stringBuilder.append(d[(int) (y * size.height + x)]); 
		        }
		    }
		    return stringBuilder.toString();
		}
		
		public void run() {

			System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

			File folder = new File("/Users/Delilah/Documents/finalproject/testdalal/");
			File[] listOfFiles = folder.listFiles();
			
			File folderBW = new File("/Users/Delilah/Documents/finalproject/testdalal/TEST/BlackAndWhite/");
			File[] listOfFilesBW = folderBW.listFiles();
			
 

			
			//for (int i = 0; i < 2;i++){//listOfFiles.length; i++){
			for (int i = 0; i < listOfFiles.length; i++){
				 
				String picname = (listOfFiles[i]).getName(); //filename in input folder
				//System.out.println(picname);
				
				String bwPath = "/Users/Delilah/Documents/finalproject/testdalal/"; //input folder
			 
				String picPathName = bwPath + picname; //pic filename is the foldername  and the filename
				
				//System.out.println(picPathName);
				 
				Mat image = Imgcodecs.imread(picPathName);
				Mat grayImage = new Mat();
				Imgproc.cvtColor( image, grayImage, Imgproc.COLOR_RGB2GRAY);
				
				Mat smallGrayImage = new Mat ();
				Imgproc.resize(grayImage, smallGrayImage, new Size(59,100));
				System.out.println(matToCSVString(smallGrayImage));
				
				
				/*File input = new File(picPathName);
		        BufferedImage image;
				try {
					image = ImageIO.read(input); //read the image
					
			         byte[] data = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
					 Mat mat = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3); //create mat using the heigh and width of the image
			         mat.put(0, 0, data);
			         Mat mat1 = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3); //creates mat1, grayscaled image
			     	
			        // mgproc.cvtColor(mat, mat1, Imgproc.COLOR_RGB2HSV); //take mat and changes it into mat1 OF GRAYCOLOR
			         Imgproc.cvtColor(mat, mat1, Imgproc.COLOR_RGB2GRAY);
			         
					 String fileName = listOfFiles[i].getName()+"-bw.jpg";	
					 //System.out.println("name of output path and file name " + folderBW +"/"+fileName+"-bw.jpg"); //bw.jpg to the filename and print it
					 
					 byte[] data1 = new byte[mat1.rows()*mat1.cols()*(int)(mat1.elemSize())];
			         mat1.get(0, 0, data1);
			         BufferedImage image1 = new BufferedImage(mat1.cols(), mat1.rows(), 5);
			         image1.getRaster().setDataElements(0, 0, mat1.cols(), mat1.rows(), data1); //builds the output image 
 
			         File ouptut = new File(fileName);
			        // File ouptut = new File("/Users/Delilah/Documents/finalproject/testdalal/TEST/BlackAndWhite2/"+fileName);
			         ImageIO.write(image1, "jpg", ouptut); //outputs image 1 writes it to the folder
					
					
					
					
					
					
					Imgcodecs.imwrite("/Users/Delilah/Documents/finalproject/testdalal/TEST/BlackAndWhite/"+fileName+"-bw.jpg", mat1);
			
				} catch (IOException e) {
					System.out.println("cant read image");
					e.printStackTrace();
				}*/
			}//for all files in the folder

			 
			
		}//run

 
		public static void main (String[] args) {
			BlackAndWhite fd = new BlackAndWhite();
			fd.run();
		}
	}



	
        
