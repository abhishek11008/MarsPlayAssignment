package marsplay.app.com.marsplayassignment.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jsibbold.zoomage.ZoomageView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import marsplay.app.com.marsplayassignment.ActivityZoomImageView;
import marsplay.app.com.marsplayassignment.POJO.POJOMedia;
import marsplay.app.com.marsplayassignment.R;

/**
 * Created by abhisheksingh on 5/15/18.
 */

public class AdapterSliderZoomedImages extends RecyclerView.Adapter<AdapterSliderZoomedImages.ViewHolder> {
    private static final String TAG = AdapterSliderZoomedImages.class.getSimpleName();
    private Context mContext;
    ArrayList<POJOMedia> mediaList;
    LayoutInflater inflat;

    //Constructor
    public AdapterSliderZoomedImages(Context c, ArrayList<POJOMedia> list){
        inflat = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mContext = c;
        mediaList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder vh;
        View view;

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_select_image, parent, false);
        vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final AdapterSliderZoomedImages.ViewHolder holder, final int position) {
        try {
            Glide.with(mContext)
                    .load(mediaList.get(position).getImgUrl())
                    .into(holder.imageView);

            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext,ActivityZoomImageView.class);
                    intent.putExtra("imagesReceived",mediaList.get(position).getImgUrl());
                    mContext.startActivity(intent);
                }
            });

        }catch (Exception e){
        }
    }

    @Override
    public int getItemCount() {
        return mediaList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        //VideoView videoView;
        CheckBox imageCheckBox;//,videoCheckBox;


        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            //videoView = itemView.findViewById(R.id.videoView);
            imageCheckBox = itemView.findViewById(R.id.imageCheckBox);
            // videoCheckBox = itemView.findViewById(R.id.videoCheckBox);
        }
    }
}