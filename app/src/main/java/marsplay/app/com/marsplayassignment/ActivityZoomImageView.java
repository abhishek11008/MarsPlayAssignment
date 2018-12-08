package marsplay.app.com.marsplayassignment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jsibbold.zoomage.ZoomageView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import marsplay.app.com.marsplayassignment.Adapter.AdapterSliderZoomedImages;
import marsplay.app.com.marsplayassignment.POJO.POJOMedia;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by abhisheksingh on 5/14/18.
 */

public class ActivityZoomImageView extends AppCompatActivity {

    ZoomageView myZoomageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom_imageview);
        myZoomageView = findViewById(R.id.myZoomageView);


        Glide.with(this)
                .load(getIntent().getStringExtra("imagesReceived"))
                .into(myZoomageView);

    }

}
