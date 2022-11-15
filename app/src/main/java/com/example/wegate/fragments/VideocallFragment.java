package com.example.wegate.fragments;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.Camera;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.wegate.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;

public class VideocallFragment extends Fragment implements SurfaceHolder.Callback{


    protected static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 0;
    private SurfaceView SurView,SurView2;
    private SurfaceHolder camHolder,camHolder2;
    private boolean previewRunning;
    final Context context = getContext();
    public static Camera camera = null;
    FloatingActionButton endcall;

    public VideocallFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        boolean premium = getArguments().getBoolean("premium");
        View view = inflater.inflate(R.layout.fragment_videocall, container, false);
        endcall = view.findViewById(R.id.endcall);
        endcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(premium){
                    MessageFragmentPremium messageFragmentPremium = new MessageFragmentPremium();
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.FragmentLayout, messageFragmentPremium).commit();
                }else {
                    MessageFragment messageFragment = new MessageFragment();
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.FragmentLayout, messageFragment).commit();
                }
            }
        });


        SurView2 = (SurfaceView)view.findViewById(R.id.surfaceView2);
        camHolder2 = SurView2.getHolder();
        camHolder2.addCallback(this);
        camHolder2.setType(SurfaceHolder.SURFACE_TYPE_NORMAL);

        return view;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        if(previewRunning){
            camera.stopPreview();
        }
        Camera.Parameters camParams = camera.getParameters();
        Camera.Size size = camParams.getSupportedPreviewSizes().get(0);
        camParams.setPreviewSize(size.width, size.height);
        camera.setParameters(camParams);
        try{
            camera.setPreviewDisplay(holder);
            camera.startPreview();
            previewRunning=true;
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void surfaceCreated(SurfaceHolder holder) {
        try{
            camera=Camera.open(1);
            camera.setDisplayOrientation(90);
        }catch(Exception e){
            e.printStackTrace();
            Toast.makeText(getContext(),"Error",Toast.LENGTH_LONG).show();
            getActivity().finish();
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        camera.stopPreview();
        camera.release();
        camera=null;
    }
}