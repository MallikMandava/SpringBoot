package com.mmandava.samples.AccessWebCam;

import java.awt.image.BufferedImage;
import javax.media.*;
import javax.media.control.FrameGrabbingControl;
import javax.media.format.RGBFormat;
import javax.media.format.VideoFormat;
import javax.media.util.BufferToImage;

public class WebCam {

    CaptureDeviceInfo device;
    MediaLocator ml;
    Player player;
    Component videoScreen;

    public static void main(String args[]) {
        new WebCam();// create a new instance of WebCam in main function
    }

    WebCam() {
        try {
//gets a list of devices how support the given videoformat
            Vector deviceList = CaptureDeviceManager.getDeviceList(new RGBFormat());
            System.out.println(deviceList.toString());

//gets the first device in deviceList
            device = (CaptureDeviceInfo) deviceList.firstElement();

            ml = device.getLocator();

            player = Manager.createRealizedPlayer(ml);

            player.start();

            videoScreen = player.getVisualComponent();
            Frame frm = new Frame();
            frm.setBounds(10, 10, 900, 700);//sets the size of the screen

// setting close operation to the frame
            frm.addWindowListener(new WindowAdapter() {

                public void windowClosing(WindowEvent we) {
                    System.exit(0);
                }
            });

//place player and video screen on the frame
            frm.add(videoScreen, BorderLayout.CENTER);
            frm.add(player.getControlPanelComponent(), BorderLayout.SOUTH);
            frm.setVisible(true);

//capture image
            Thread.sleep(10000);//wait 10 seconds before capturing photo

            FrameGrabbingControl fgc = (FrameGrabbingControl) player.getControl("javax.media.control.FrameGrabbingControl");

            Buffer buf = fgc.grabFrame();//grab the current frame on video screen

            BufferToImage btoi = new BufferToImage((VideoFormat) buf.getFormat());

            Image img = btoi.createImage(buf);

            saveImagetoFile(img, "MyPhoto.jpg");//save the captured image as MyPhoto.jpg

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void saveImagetoFile(Image img, String string) {
        try {
            int w = img.getWidth(null);
            int h = img.getHeight(null);
            BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = bi.createGraphics();

            g2.drawImage(img, 0, 0, null);

            g2.dispose();

            String fileType = string.substring(string.indexOf('.') + 1);

            ImageIO.write(bi, fileType, new File(string));

        } catch (Exception e) {
        }
    }
}
