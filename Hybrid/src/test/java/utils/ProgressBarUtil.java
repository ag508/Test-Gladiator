package utils;

import javax.swing.*;
import java.awt.*;

public class ProgressBarUtil {
    private JFrame frame;
    private JProgressBar progressBar;
    private JLabel statusLabel;
    private Timer timer;
    private long startTime;
    private int durationMs;
    private int interval = 100;

    public ProgressBarUtil(String title, int durationInSeconds) {
        this.durationMs = durationInSeconds * 1000;

        frame = new JFrame(title);
        frame.setSize(420, 100);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        statusLabel = new JLabel("Starting...", SwingConstants.CENTER);
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);

        frame.add(statusLabel, BorderLayout.NORTH);
        frame.add(progressBar, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public void start(String message) {
        statusLabel.setText(message);
        startTime = System.currentTimeMillis();

        timer = new Timer(interval, e -> {
            long elapsed = System.currentTimeMillis() - startTime;
            int percent = (int) Math.min(100, (elapsed * 100 / durationMs));
            progressBar.setValue(percent);

            if (percent >= 100) {
                ((Timer) e.getSource()).stop();
                statusLabel.setText("✅ Done in " + (elapsed / 1000) + "s");
            }
        });

        timer.start();
    }

    public void complete(String finalMessage) {
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
        long elapsed = (System.currentTimeMillis() - startTime) / 1000;
        progressBar.setValue(100);
        statusLabel.setText(finalMessage + " (⏱ " + elapsed + "s)");
    }

    public void close() {
        if (frame != null) frame.dispose();
    }
}