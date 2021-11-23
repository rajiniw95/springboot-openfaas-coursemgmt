package selfScalingHistogram;

import org.HdrHistogram.*;

import java.net.DatagramSocket;
import java.net.SocketException;

import java.io.FileNotFoundException;
import java.io.PrintStream;

public class App {

    // A Histogram covering the range from 1 nsec to 1 hour with 3 decimal point resolution:
    static Histogram histogram = new Histogram(3600000000000L, 3);

    static public volatile DatagramSocket socket;

    static long WARMUP_TIME_MSEC = 5000;
    static long RUN_TIME_MSEC = 20000;


    static void recordTimeToCreateAndCloseDatagramSocket() {
        long startTime = System.nanoTime();
        try {
            socket = new DatagramSocket();
        } catch (SocketException ex) {
        } finally {
            socket.close();
        }
        long endTime = System.nanoTime();
        histogram.recordValue(endTime - startTime);
    }

    public static void main(String[] args) {        
        long startTime = System.currentTimeMillis();
        long now;

        do {
            recordTimeToCreateAndCloseDatagramSocket();
            now = System.currentTimeMillis();
        } while (now - startTime < WARMUP_TIME_MSEC);

        histogram.reset();

        do {
            recordTimeToCreateAndCloseDatagramSocket();
            now = System.currentTimeMillis();
        } while (now - startTime < RUN_TIME_MSEC);
        
        // define file name of output desitination
        String filename = "histogram_output.txt";     
        
        // Write Output Percentile Distribution to PrintStream
        try(PrintStream ps = new PrintStream(filename)){
        	ps.println("Recorded latencies [in usec] for Create+Close of a DatagramSocket:");
            	histogram.outputPercentileDistribution(ps, 1000.0);
            	ps.flush();
        } catch (FileNotFoundException e) {
            	e.printStackTrace();
        }
        
        System.out.println("Self Scaling Histogram Created and Analyzed");
    }
}
