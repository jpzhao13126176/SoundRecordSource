package com.bjtu2013.activity;

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class MakeRecordActivity extends Activity {
	
	private static final String LOG_TAG = "MakeRecordActivity"; 
    private static String mFileName = null; 
    //¼����ť  
    private RecordButton mRecordButton = null; 
    private MediaRecorder mRecorder = null; 
    //�طŰ�ť  
    private PlayButton mPlayButton = null; 
    private MediaPlayer mPlayer = null; 
 
    //��¼����ť��clickʱ���ô˷�������ʼ��ֹͣ¼��  
    private void onRecord(boolean start) { 
        if (start) { 
            startRecording(); 
        } else { 
            stopRecording(); 
        } 
    } 
 
    //�����Ű�ť��clickʱ���ô˷�������ʼ��ֹͣ����  
    private void onPlay(boolean start) { 
        if (start) { 
            startPlaying(); 
        } else { 
            stopPlaying(); 
        } 
    } 
 
    private void startPlaying() { 
        mPlayer = new MediaPlayer(); 
        try { 
            //����Ҫ���ŵ��ļ�  
            mPlayer.setDataSource(mFileName); 
            mPlayer.prepare(); 
            //����
            mPlayer.start(); 
        } catch (IOException e) { 
            Log.e(LOG_TAG, "prepare() failed"); 
        } 
    } 
 
    //ֹͣ����  
    private void stopPlaying() { 
        mPlayer.release(); 
        mPlayer = null; 
    } 
 
    private void startRecording() { 
        mRecorder = new MediaRecorder(); 
        //������ԴΪMicphone  
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC); 
        //���÷�װ��ʽ  
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP); 
        mRecorder.setOutputFile(mFileName); 
        //���ñ����ʽ  
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB); 
 
        try { 
            mRecorder.prepare(); 
        } catch (IOException e) { 
            Log.e(LOG_TAG, "prepare() failed"); 
        } 
 
        mRecorder.start(); 
    } 
 
    private void stopRecording() { 
        mRecorder.stop(); 
        mRecorder.release(); 
        mRecorder = null; 
    } 
 
    //����¼����ť  
    class RecordButton extends Button { 
        boolean mStartRecording = true; 
 
        OnClickListener clicker = new OnClickListener() { 
            public void onClick(View v) { 
                onRecord(mStartRecording); 
                if (mStartRecording) { 
                    setText("Stop recording"); 
                } else { 
                    setText("Start recording"); 
                } 
                mStartRecording = !mStartRecording; 
            } 
        }; 
 
        public RecordButton(Context ctx) { 
            super(ctx); 
            setText("Start recording"); 
            setOnClickListener(clicker); 
        } 
    } 
 
    //���岥�Ű�ť  
    class PlayButton extends Button { 
        boolean mStartPlaying = true; 
 
        OnClickListener clicker = new OnClickListener() { 
            public void onClick(View v) { 
                onPlay(mStartPlaying); 
                if (mStartPlaying) { 
                    setText("Stop playing"); 
                } else { 
                    setText("Start playing"); 
                } 
                mStartPlaying = !mStartPlaying; 
            } 
        }; 
 
        public PlayButton(Context ctx) { 
            super(ctx); 
            setText("Start playing"); 
            setOnClickListener(clicker); 
        } 
    } 
 
    //���췽��  
    public MakeRecordActivity() { 
        mFileName = Environment.getExternalStorageDirectory().getAbsolutePath(); 
        mFileName += "/data/data/com.bjtu2013.soundrecord/file/����.mp3"; 
    } 
 
    @Override 
    public void onCreate(Bundle icicle) { 
        super.onCreate(icicle); 
        //�������  
        LinearLayout ll = new LinearLayout(this); 
        mRecordButton = new RecordButton(this); 
        ll.addView(mRecordButton, 
            new LinearLayout.LayoutParams( 
                ViewGroup.LayoutParams.WRAP_CONTENT, 
                ViewGroup.LayoutParams.WRAP_CONTENT, 
                0)); 
        mPlayButton = new PlayButton(this); 
        ll.addView(mPlayButton, 
            new LinearLayout.LayoutParams( 
                ViewGroup.LayoutParams.WRAP_CONTENT, 
                ViewGroup.LayoutParams.WRAP_CONTENT, 
                0)); 
        setContentView(ll); 
    } 
 
    @Override 
    public void onPause() { 
        super.onPause(); 
        //Activity��ͣʱ�ͷ�¼���Ͳ��Ŷ���  
        if (mRecorder != null) { 
            mRecorder.release(); 
            mRecorder = null; 
        }
 
        if (mPlayer != null) { 
            mPlayer.release(); 
            mPlayer = null; 
        } 
    } 

}
