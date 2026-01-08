package com.leilie.platformgame;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;

public class AudioManager {
    private AudioRecord audioRecord;
    private boolean isRecording = false;
    private Thread recordingThread;
    private AudioListener listener;
    private static final int SAMPLE_RATE = 44100;
    private static final int BUFFER_SIZE = AudioRecord.getMinBufferSize(
        SAMPLE_RATE, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT);

    public interface AudioListener {
        void onVolumeChanged(float volume);
    }

    public AudioManager(AudioListener listener) {
        this.listener = listener;
    }

    public void startRecording() {
        try {
            audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC,
                SAMPLE_RATE, AudioFormat.CHANNEL_IN_MONO,
                AudioFormat.ENCODING_PCM_16BIT, BUFFER_SIZE);
            
            audioRecord.startRecording();
            isRecording = true;
            
            recordingThread = new Thread(() -> {
                short[] buffer = new short[BUFFER_SIZE];
                while (isRecording) {
                    int read = audioRecord.read(buffer, 0, buffer.length);
                    if (read > 0) {
                        float amplitude = calculateAmplitude(buffer, read);
                        if (listener != null) {
                            listener.onVolumeChanged(amplitude);
                        }
                    }
                }
            });
            recordingThread.start();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    public void stopRecording() {
        isRecording = false;
        if (audioRecord != null) {
            audioRecord.stop();
            audioRecord.release();
            audioRecord = null;
        }
    }

    private float calculateAmplitude(short[] buffer, int length) {
        long sum = 0;
        for (int i = 0; i < length; i++) {
            sum += Math.abs(buffer[i]);
        }
        float average = (float) sum / length;
        return Math.min(average / 1000f, 1.5f);
    }
}
