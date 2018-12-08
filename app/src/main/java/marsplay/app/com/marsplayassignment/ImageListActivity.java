package marsplay.app.com.marsplayassignment;

import android.app.ProgressDialog;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import marsplay.app.com.marsplayassignment.Adapter.AdapterSliderZoomedImages;
import marsplay.app.com.marsplayassignment.POJO.POJOMedia;

public class ImageListActivity extends AppCompatActivity {

    // The S3 client used for getting the list of objects in the bucket
    private AmazonS3Client s3;
    // An adapter to show the objects
    AdapterSliderZoomedImages simpleAdapter;
    private Util util;
    RecyclerView galleryRecyclerView;
    ArrayList<POJOMedia> imageUrlList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Image List");
        toolbar.setTitleTextColor(getResources().getColor(R.color.defaultwhite));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_arrow_left_white_24dp);
        upArrow.setColorFilter(getResources().getColor(R.color.defaultwhite), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        galleryRecyclerView = findViewById(R.id.galleryRecyclerView);
        util = new Util();
        initData();
        initUI();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh the file list.
        new GetFileListTask().execute();
    }

    private void initData() {
        // Gets the default S3 client.
        s3 = util.getS3Client(ImageListActivity.this);
    }

    private void initUI() {
    }

    /**
     * This async task queries S3 for all files in the given bucket so that they
     * can be displayed on the screen
     */
    private class GetFileListTask extends AsyncTask<Void, Void, Void> {
        // The list of objects we find in the S3 bucket
        private List<S3ObjectSummary> s3ObjList;
        // A dialog to let the user know we are retrieving the files
        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            imageUrlList = new ArrayList<>();
            dialog = ProgressDialog.show(ImageListActivity.this,
                    getString(R.string.refreshing),
                    getString(R.string.please_wait));
        }

        @Override
        protected Void doInBackground(Void... inputs) {
            // Queries files in the bucket from S3.
            s3ObjList = s3.listObjects(Constants.BUCKET_NAME).getObjectSummaries();
            for (S3ObjectSummary summary : s3ObjList) {
                POJOMedia pojoMedia = new POJOMedia();
                pojoMedia.setImgUrl("https://s3-us-west-2.amazonaws.com/mpassignment/"+ summary.getKey());
                imageUrlList.add(pojoMedia);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 3);
            galleryRecyclerView.setLayoutManager(gridLayoutManager);
            simpleAdapter = new AdapterSliderZoomedImages(ImageListActivity.this,imageUrlList);
            galleryRecyclerView.setAdapter(simpleAdapter);
            simpleAdapter.notifyDataSetChanged();
            dialog.dismiss();
            simpleAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
