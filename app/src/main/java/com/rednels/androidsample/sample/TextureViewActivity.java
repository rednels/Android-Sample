/*
    Camera Texture recorder
    Copyright (c) 2014 rednels <vcokey@gmail.com>

    Licensed under the LGPL, Version 3.0 (the "License")
*/
package com.rednels.androidsample.sample;

import android.app.Activity;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.net.LocalServerSocket;
import android.net.LocalSocket;
import android.net.LocalSocketAddress;
import android.os.Bundle;
import android.util.Pair;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;

import com.rednels.androidsample.R;

import java.io.FileDescriptor;
import java.io.IOException;
import java.util.List;

/**
 * Created by liwubin on 2014/8/14.
 */
public class TextureViewActivity extends Activity {
    LocalSocket sender;
    Button start, stop;
    TextureView texureView;
    Camera mCamera;
    MediaRecorder mediarecorder;
    SurfaceTexture surfaceTexture;
    private Camera.CameraInfo mBackCameraInfo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_texture_recorder);
        start = (Button) findViewById(R.id.start);
        stop = (Button) findViewById(R.id.stop);
        texureView = (TextureView) findViewById(R.id.textureview);
        initCamera();
//        initLocalSocket();

        surfaceTexture = texureView.getSurfaceTexture();
        texureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
                try {
                    mCamera.setPreviewTexture(texureView.getSurfaceTexture());
                    mCamera.startPreview();
                } catch (IOException ioe) {
                    // Something bad happened
                }
            }

            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {

            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
                if (mCamera != null) {
                    mCamera.stopPreview();
                    mCamera.release();
                    mCamera = null;
                }
                return true;
            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {

            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediarecorder = new MediaRecorder();
                initMediaRecorder("/sdcard/love.mp4");
                try {
                    // 准备录制
                    mediarecorder.prepare();
                    // 开始录制
                    mediarecorder.start();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    mediarecorder.stop();
                    // 释放资源
                    mediarecorder.release();
                    mediarecorder = null;
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initMediaRecorder(String file) {
        mCamera.unlock();
        mediarecorder.setCamera(mCamera);
        mediarecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        // 设置录制视频源为Camera(相机)
        mediarecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
        // 设置录制完成后视频的封装格式THREE_GPP为3gp.MPEG_4为mp4
        mediarecorder
                .setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mediarecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        mediarecorder.setAudioEncodingBitRate(14400);
        // 设置录制的视频编码h263 h264
        mediarecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
        // 设置视频录制的分辨率。必须放在设置编码和格式的后面，否则报错

        mediarecorder.setVideoSize(480, 320);
        mediarecorder.setVideoEncodingBitRate(128*1024);
        // 设置录制的视频帧率。必须放在设置编码和格式的后面，否则报错
        mediarecorder.setVideoFrameRate(25);
//        mediarecorder.setCaptureRate(60);
        mediarecorder.setOrientationHint(90);
        // 设置视频文件输出的路径
        FileDescriptor fd = null;
//        try {
//            RandomAccessFile raf = new RandomAccessFile(file,"rws");
//            raf.seek(raf.length());
//            fd = raf.getFD();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        mediarecorder.setOutputFile(file);
    }

    private void initLocalSocket() {
        LocalSocket receiver = new LocalSocket();
        try {
            LocalServerSocket lss = new LocalServerSocket("H264");
            receiver.connect(new LocalSocketAddress("H264"));
            receiver.setReceiveBufferSize(500000);
            receiver.setSendBufferSize(500000);
            sender = lss.accept();
            sender.setReceiveBufferSize(500000);
            sender.setSendBufferSize(500000);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private void initCamera() {
        Pair<Camera.CameraInfo, Integer> backCamera = getBackCamera();
        final int backCameraId = backCamera.second;
        mBackCameraInfo = backCamera.first;
        mCamera = Camera.open(backCameraId);
        Camera.Parameters parameters = mCamera.getParameters();
        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO);
        parameters.setRecordingHint(true);
        parameters.setPreviewSize(480,320);
        mCamera.setParameters(parameters);
        setCameraDisplayOrientation(this, backCameraId, mCamera);
    }

    /**
     * 获取摄像头
     *
     * @return
     */

    private Pair<Camera.CameraInfo, Integer> getBackCamera() {
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        final int numberOfCameras = Camera.getNumberOfCameras();

        for (int i = 0; i < numberOfCameras; ++i) {
            Camera.getCameraInfo(i, cameraInfo);
            if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                return new Pair<Camera.CameraInfo, Integer>(cameraInfo,
                        Integer.valueOf(i));
            }
        }
        return null;
    }

    /**
     * 计算摄像头方向
     */
    public static void setCameraDisplayOrientation(Activity activity,
                                                   int cameraId, Camera camera) {
        Camera.CameraInfo info =
                new Camera.CameraInfo();
        Camera.getCameraInfo(cameraId, info);
        int rotation = activity.getWindowManager().getDefaultDisplay()
                .getRotation();
        int degrees = 0;
        switch (rotation) {
            case Surface.ROTATION_0:
                degrees = 0;
                break;
            case Surface.ROTATION_90:
                degrees = 90;
                break;
            case Surface.ROTATION_180:
                degrees = 180;
                break;
            case Surface.ROTATION_270:
                degrees = 270;
                break;
        }

        int result;
        if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            result = (info.orientation + degrees) % 360;
            result = (360 - result) % 360;  // compensate the mirror
        } else {  // back-facing
            result = (info.orientation - degrees + 360) % 360;
        }
        camera.setDisplayOrientation(result);
    }
}