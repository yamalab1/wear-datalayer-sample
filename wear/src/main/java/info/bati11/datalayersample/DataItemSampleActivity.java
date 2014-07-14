package info.bati11.datalayersample;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.PutDataMapRequest;
import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.Wearable;

public class DataItemSampleActivity extends Activity implements GoogleApiClient.ConnectionCallbacks {

    private GoogleApiClient mGoogleApiClient;

    private int count = 0;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_item_sample);

        textView = (TextView)findViewById(R.id.counter);
        textView.setText(Integer.toString(count));

        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addConnectionCallbacks(this)
                .addApi(Wearable.API)
                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    public void increment(View v) {
        count++;
        textView.setText(Integer.toString(count));

        PutDataMapRequest dataMap = PutDataMapRequest.create("/dataitem/data");
        dataMap.getDataMap().putInt("key", count);
        PutDataRequest request = dataMap.asPutDataRequest();
        PendingResult<DataApi.DataItemResult> pendingResult =
                Wearable.DataApi.putDataItem(mGoogleApiClient, request);
        pendingResult.setResultCallback(new ResultCallback<DataApi.DataItemResult>() {
            @Override
            public void onResult(DataApi.DataItemResult dataItemResult) {
                Log.d("TAG", "onResult: " + dataItemResult.getStatus().toString());
            }
        });
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.d("TAG", "onConnected");
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d("TAG", "onConnectionSuspended");
    }
}

