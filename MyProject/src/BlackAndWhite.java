import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

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
	public static String matToCSVString(Mat m) {
		// Precondition: m is a matrix x*y*1 (gray image)
		Size size = m.size();
		StringBuilder stringBuilder = new StringBuilder();
		byte[] d = new byte[(int) (size.width * size.height)];
		m.get(0, 0, d);
		for (int x = 0; x < size.height; x++) {
			for (int y = 0; y < size.width; y++) {
				if (stringBuilder.length() > 0)
					stringBuilder.append(",");
				stringBuilder.append(d[(int) (y * size.height + x)]);
			}
		}
		return stringBuilder.toString();
	}

	public void run() throws FileNotFoundException, UnsupportedEncodingException {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

		File folderPos = new File("/Users/Delilah/Documents/finalproject/POSV/");
		File[] listOfFilesPos = folderPos.listFiles();

		File folderNeg = new File("/Users/Delilah/Documents/finalproject/NEG/");
		File[] listOfFilesNeg = folderNeg.listFiles();
		
		PrintWriter printwriter = new PrintWriter("/Users/Delilah/Documents/finalproject/test.csv", "UTF-8");

		// for (int i = 0; i < 2;i++){//listOfFiles.length; i++){
		for (int i = 0; i < listOfFilesPos.length; i++) {
			String picname = (listOfFilesPos[i]).getName(); // filename in input
															// folder
			// System.out.println(picname);
			String PosPath = "/Users/Delilah/Documents/finalproject/POSV/"; // input
																			// folder
			String picPathName = PosPath + picname; // pic filename is the
													// foldername and the
													// filename
			// System.out.println(picPathName);
			String positiveSamples = "N";
			Mat image = Imgcodecs.imread(picPathName);
			Mat grayImage = new Mat();
			Imgproc.cvtColor(image, grayImage, Imgproc.COLOR_RGB2GRAY);

			Mat smallGrayImage = new Mat();
			Imgproc.resize(grayImage, smallGrayImage, new Size(59, 100));
			//System.out.println(positiveSamples + ","
			//		+ matToCSVString(smallGrayImage)); // adding the string "N"
														// to matToCSNString
			
			printwriter.println(positiveSamples + ","
					+ matToCSVString(smallGrayImage));
		}

		for (int i = 0; i < listOfFilesNeg.length; i++) {
			String picname = (listOfFilesNeg[i]).getName(); // filename in input
															// folder
			// System.out.println(picname);
			String PosPath = "/Users/Delilah/Documents/finalproject/NEG/"; // input
																			// folder
			String picPathName = PosPath + picname; // pic filename is the
													// foldername and the
													// filename
			// System.out.println(picPathName);
			String negativeSamples = "Y";
			Mat image = Imgcodecs.imread(picPathName);
			Mat grayImage = new Mat();
			Imgproc.cvtColor(image, grayImage, Imgproc.COLOR_RGB2GRAY);

			Mat smallGrayImage = new Mat();
			Imgproc.resize(grayImage, smallGrayImage, new Size(59, 100));
			//System.out.println(negativeSamples + ","
			//		+ matToCSVString(smallGrayImage));
			
			printwriter.println(negativeSamples + ","
					+ matToCSVString(smallGrayImage));
		}
	}

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		BlackAndWhite fd = new BlackAndWhite();
		fd.run();
	}
}
