package zl.wang.cn.com.wangmyapp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import zl.wang.cn.com.wangmyapp.R;
import zl.wang.cn.com.wangmyapp.view.activity.wangrxjava.SimpleExampleActivity;

public class OperatorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operators);
    }

    public void startSimpleActivity(View view) {
        Intent intent = new Intent(OperatorsActivity.this, SimpleExampleActivity.class);
        intent.putExtra("name", "simple");
        startActivity(intent);
    }

    public void startMapActivity(View view) {
        Intent intent = new Intent(OperatorsActivity.this, SimpleExampleActivity.class);
        intent.putExtra("name", "Map");
        startActivity(intent);
    }

    public void startZipActivity(View view) {
        Intent intent = new Intent(OperatorsActivity.this, SimpleExampleActivity.class);
        intent.putExtra("name", "Zip");
        startActivity(intent);
    }

    public void startDisposableActivity(View view) {
        Intent intent = new Intent(OperatorsActivity.this, SimpleExampleActivity.class);
        intent.putExtra("name", "Disposable");
        startActivity(intent);
    }

    public void startTakeActivity(View view) {
        Intent intent = new Intent(OperatorsActivity.this, SimpleExampleActivity.class);
        intent.putExtra("name", "Take");
        startActivity(intent);
    }

    public void startTimerActivity(View view) {
        Intent intent = new Intent(OperatorsActivity.this, SimpleExampleActivity.class);
        intent.putExtra("name", "Timer");
        startActivity(intent);
    }

    public void startIntervalActivity(View view) {
        Intent intent = new Intent(OperatorsActivity.this, SimpleExampleActivity.class);
        intent.putExtra("name", "Interval");
        startActivity(intent);
    }

    public void startSingleObserverActivity(View view) {
        Intent intent = new Intent(OperatorsActivity.this, SimpleExampleActivity.class);
        intent.putExtra("name", "SingleObserver");
        startActivity(intent);
    }

    public void startCompletableObserverActivity(View view) {
        Intent intent = new Intent(OperatorsActivity.this, SimpleExampleActivity.class);
        intent.putExtra("name", "CompletableObserver");
        startActivity(intent);
    }

    public void startFlowableActivity(View view) {
        Intent intent = new Intent(OperatorsActivity.this, SimpleExampleActivity.class);
        intent.putExtra("name", "Flowable");
        startActivity(intent);
    }

    public void startReduceActivity(View view) {
        Intent intent = new Intent(OperatorsActivity.this, SimpleExampleActivity.class);
        intent.putExtra("name", "Reduce");
        startActivity(intent);
    }

    public void startBufferActivity(View view) {
        Intent intent = new Intent(OperatorsActivity.this, SimpleExampleActivity.class);
        intent.putExtra("name", "Buffer");
        startActivity(intent);
    }

    public void startFilterActivity(View view) {
        Intent intent = new Intent(OperatorsActivity.this, SimpleExampleActivity.class);
        intent.putExtra("name", "Filter");
        startActivity(intent);
    }

    public void startSkipActivity(View view) {
        Intent intent = new Intent(OperatorsActivity.this, SimpleExampleActivity.class);
        intent.putExtra("name", "Skip");
        startActivity(intent);
    }

    public void startScanActivity(View view) {
        Intent intent = new Intent(OperatorsActivity.this, SimpleExampleActivity.class);
        intent.putExtra("name", "Scan");
        startActivity(intent);
    }

    public void startReplayActivity(View view) {
        Intent intent = new Intent(OperatorsActivity.this, SimpleExampleActivity.class);
        intent.putExtra("name", "Replay");
        startActivity(intent);
    }

    public void startConcatActivity(View view) {
        Intent intent = new Intent(OperatorsActivity.this, SimpleExampleActivity.class);
        intent.putExtra("name", "Concat");
        startActivity(intent);
    }

    public void startMergeActivity(View view) {
        Intent intent = new Intent(OperatorsActivity.this, SimpleExampleActivity.class);
        intent.putExtra("name", "Merge");
        startActivity(intent);
    }

    public void startDeferActivity(View view) {
        Intent intent = new Intent(OperatorsActivity.this, SimpleExampleActivity.class);
        intent.putExtra("name", "Defer");
        startActivity(intent);
    }

    public void startDistinctActivity(View view) {
        Intent intent = new Intent(OperatorsActivity.this, SimpleExampleActivity.class);
        intent.putExtra("name", "Defer");
        startActivity(intent);
    }

    public void startLastOperatorActivity(View view) {
        Intent intent = new Intent(OperatorsActivity.this, SimpleExampleActivity.class);
        intent.putExtra("name", "Last");
        startActivity(intent);
    }

    public void startReplaySubjectActivity(View view) {
        Intent intent = new Intent(OperatorsActivity.this, SimpleExampleActivity.class);
        intent.putExtra("name", "ReplaySubject");
        startActivity(intent);
    }

    public void startPublishSubjectActivity(View view) {
        Intent intent = new Intent(OperatorsActivity.this, SimpleExampleActivity.class);
        intent.putExtra("name", "PublishSubject");
        startActivity(intent);
    }

    public void startBehaviorSubjectActivity(View view) {
        Intent intent = new Intent(OperatorsActivity.this, SimpleExampleActivity.class);
        intent.putExtra("name", "BehaviorSubject");
        startActivity(intent);
    }

    public void startAsyncSubjectActivity(View view) {
        Intent intent = new Intent(OperatorsActivity.this, SimpleExampleActivity.class);
        intent.putExtra("name", "AsyncSubject");
        startActivity(intent);
    }

    public void startThrottleFirstActivity(View view) {
        Intent intent = new Intent(OperatorsActivity.this, SimpleExampleActivity.class);
        intent.putExtra("name", "ThrottleFirst");
        startActivity(intent);
    }

    public void startThrottleLastActivity(View view) {
        Intent intent = new Intent(OperatorsActivity.this, SimpleExampleActivity.class);
        intent.putExtra("name", "ThrottleLast");
        startActivity(intent);
    }

    public void startDebounceActivity(View view) {
        Intent intent = new Intent(OperatorsActivity.this, SimpleExampleActivity.class);
        intent.putExtra("name", "Debounce");
        startActivity(intent);
    }

    public void startWindowActivity(View view) {
        Intent intent = new Intent(OperatorsActivity.this, SimpleExampleActivity.class);
        intent.putExtra("name", "Window");
        startActivity(intent);
    }

    public void startDelayActivity(View view) {
        Intent intent = new Intent(OperatorsActivity.this, SimpleExampleActivity.class);
        intent.putExtra("name", "delay");
        startActivity(intent);
    }
}