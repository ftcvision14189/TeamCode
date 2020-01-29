package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.List;

@Autonomous public class OpenCVTest extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();

    // Camera object used to access webcam
    OpenCvCamera webcam;

    // Scan results
    // 0 means skystone, 1 means yellow stone
    // -1 for debug, but we can keep it like this because if it works, it should change to either 0 or 255
    private static int valDetectMid = -1;
    private static int valDetectLeft = -1;
    private static int valDetectRight = -1;

    private static int valLeftAlign = -1;
    private static int valRightAlign = -1;

    // Detection scan offsets
    private static float detectOffsetX = 3f/16f; // Changing this moves the three circles left or right, range : (-2, 2) not inclusive
    private static float detectOffsetY = 0f/8f; // Changing this moves the circles up or down, range: (-4, 4) not inclusive

    // Alignment scan offsets
    private static float alignOffsetX = 0f/8f;
    private static float alignOffsetY = 2f/8f;

    // Detection scan point positions
    private static float[] midDetectPos = {4f/8f+ detectOffsetX, 4f/8f+ detectOffsetY}; // 0 = col, 1 = row
    private static float[] leftDetectPos = {2f/8f+ detectOffsetX, 4f/8f+ detectOffsetY};
    private static float[] rightDetectPos = {6f/8f+ detectOffsetX, 4f/8f+ detectOffsetY};

    // Alignment scan point positions
    private static float[] leftAlignPos = {1f/8f+ alignOffsetX, 4f/8f+ alignOffsetY};
    private static float[] rightAlignPos = {7f/8f+ alignOffsetX, 4f/8f+ alignOffsetY};

    // Camera frame size
    private final int rows = 640;
    private final int cols = 480;

    // Skystone position
    private static int skystonePos = 0;

    // True if aligned with Skystone
    private static boolean skystoneAligned = false;

    // Name of OpenCV pipeline
    private static String pipelineName;


    @Override
    public void runOpMode() {

        // Setup hardware
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());

        // Setup OpenCV
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        webcam.openCameraDevice();
        webcam.startStreaming(rows, cols, OpenCvCameraRotation.UPRIGHT);
        webcam.setPipeline(new StageSwitchingPipeline());
        pipelineName = "align";


        // Wait for play button to be pressed
        telemetry.addData("Status", "Waiting for start.");
        telemetry.update();
        waitForStart();

        while (opModeIsActive()) {

            // Determine Skystone position
            if (valDetectLeft == 0) {
                skystonePos = 1;
            } else if (valDetectMid == 0) {
                skystonePos = 2;
            } else if (valDetectRight == 0) {
                skystonePos = 3;
            }

            // Check if aligned
            skystoneAligned = ((valLeftAlign == 0) && (valRightAlign == 255));

            // Send telemetry
            telemetry.addData("Skystone Position", skystonePos);
            telemetry.addData("Detection Values", valDetectLeft + " , " + valDetectMid + " , " + valDetectRight);
            telemetry.addData("Skystone Aligned", skystoneAligned);
            telemetry.addData("Alignment Values", valLeftAlign + " , " + valRightAlign);
            telemetry.addData("Elapsed Time", runtime.seconds() + "." + runtime.milliseconds());
            telemetry.addData("View Height", rows);
            telemetry.addData("View Width", cols);
            telemetry.update();
        }
    }


    static class StageSwitchingPipeline extends OpenCvPipeline {
        Mat yCbCrChan2Mat = new Mat();
        Mat thresholdMat = new Mat();
        Mat all = new Mat();
        List<MatOfPoint> contoursList = new ArrayList<>();

        enum Stage { // Color difference. greyscale
            detection, // Outlines and detection markers
            THRESHOLD, // Threshold view
            RAW_IMAGE, // Original image view
        }

        private Stage stageToRenderToViewport = Stage.detection;
        private Stage[] stages = Stage.values();

        @Override
        public void onViewportTapped() {
            /*
             * Note that this method is invoked from the UI thread
             * so whatever we do here, we must do quickly.
             */

            int currentStageNum = stageToRenderToViewport.ordinal();

            int nextStageNum = currentStageNum + 1;

            if(nextStageNum >= stages.length) {
                nextStageNum = 0;
            }

            stageToRenderToViewport = stages[nextStageNum];
        }

        @Override
        public Mat processFrame(Mat input) {
            contoursList.clear();
            /*
             * This pipeline finds the contours of yellow blobs such as the Gold Mineral
             * from the Rover Ruckus game.
             */

            // Blue is opposite of yellow
            // Measure blue on image
            // More blue = less yellow = Skystone = white
            // Less blue = more yellow = normal stone = grey
            Imgproc.cvtColor(input, yCbCrChan2Mat, Imgproc.COLOR_RGB2YCrCb); // Convert RGB to yCbCr
            Core.extractChannel(yCbCrChan2Mat, yCbCrChan2Mat, 2); // takes Cb (blue) difference and stores

            // Threshold Mode
            Imgproc.threshold(yCbCrChan2Mat, thresholdMat, 102, 255, Imgproc.THRESH_BINARY_INV);

            // Outline Mode
            Imgproc.findContours(thresholdMat, contoursList, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);
            yCbCrChan2Mat.copyTo(all);// Copies mat object
            Imgproc.drawContours(all, contoursList, -1, new Scalar(255, 0, 0), 3, 8); // Draw contours

            // Detect yellow at each position
            double[] pixMid = thresholdMat.get((int)(input.rows()* midDetectPos[1]), (int)(input.cols()* midDetectPos[0])); // Get value at mid circle
            valDetectMid = (int)pixMid[0];

            double[] pixLeft = thresholdMat.get((int)(input.rows()* leftDetectPos[1]), (int)(input.cols()* leftDetectPos[0])); // Get value at left circle
            valDetectLeft = (int)pixLeft[0];

            double[] pixRight = thresholdMat.get((int)(input.rows()* rightDetectPos[1]), (int)(input.cols()* rightDetectPos[0])); // Get value at right circle
            valDetectRight = (int)pixRight[0];

            // Create three points
            Point pointMid = new Point((int)(input.cols()* midDetectPos[0]), (int)(input.rows()* midDetectPos[1]));
            Point pointLeft = new Point((int)(input.cols()* leftDetectPos[0]), (int)(input.rows()* leftDetectPos[1]));
            Point pointRight = new Point((int)(input.cols()* rightDetectPos[0]), (int)(input.rows()* rightDetectPos[1]));

            // Create circles at each point
            Imgproc.circle(all, pointMid,5, new Scalar( 255, 0, 0 ),1 ); // Draws mid circle
            Imgproc.circle(all, pointLeft,5, new Scalar( 255, 0, 0 ),1 ); // Draws left circle
            Imgproc.circle(all, pointRight,5, new Scalar( 255, 0, 0 ),1 ); // Draws right circle

            switch (stageToRenderToViewport) {
                case THRESHOLD: {
                    return thresholdMat;
                }

                case detection: {
                    return all;
                }

                case RAW_IMAGE: {
                    return input;
                }

                default: {
                    return input;
                }
            }
        }
    }

    static class AlignmentPipeline extends OpenCvPipeline {
        Mat yCbCrChan2Mat = new Mat();
        Mat thresholdMat = new Mat();
        Mat all = new Mat();
        List<MatOfPoint> contoursList = new ArrayList<>();
        
        @Override
        public Mat processFrame(Mat input) {
            contoursList.clear();
            /*
             * This pipeline checks for two points in front of it that will make the robot line up
             * with a Skystone.
             */
            
            // yCbCr Mat
            Imgproc.cvtColor(input, yCbCrChan2Mat, Imgproc.COLOR_RGB2YCrCb); // Convert RGB to yCbCr
            Core.extractChannel(yCbCrChan2Mat, yCbCrChan2Mat, 2); // takes Cb (blue) difference and stores

            // Threshold Mat
            Imgproc.threshold(yCbCrChan2Mat, thresholdMat, 102, 255, Imgproc.THRESH_BINARY_INV);

            // Output Mat
            Imgproc.findContours(thresholdMat, contoursList, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);
            yCbCrChan2Mat.copyTo(all);// Copies mat object
            Imgproc.drawContours(all, contoursList, -1, new Scalar(255, 0, 0), 3, 8); // Draw contours

            // Detect yellow at each position
            double[] pixLeft = thresholdMat.get((int)(input.rows()* leftAlignPos[1]), (int)(input.cols()* leftAlignPos[0])); // Get value at left circle
            valLeftAlign = (int)pixLeft[0];

            double[] pixRight = thresholdMat.get((int)(input.rows()* rightAlignPos[1]), (int)(input.cols()* rightAlignPos[0])); // Get value at right circle
            valRightAlign = (int)pixRight[0];

            // Create two points
            Point pointLeft = new Point((int)(input.cols()* leftAlignPos[0]), (int)(input.rows()* leftAlignPos[1]));
            Point pointRight = new Point((int)(input.cols()* rightAlignPos[0]), (int)(input.rows()* rightAlignPos[1]));

            // Create circles at each point
            Imgproc.circle(all, pointLeft,5, new Scalar( 255, 0, 0 ),1 ); // Draws left circle
            Imgproc.circle(all, pointRight,5, new Scalar( 255, 0, 0 ),1 ); // Draws right circle

            return all;
        }
    }
}
