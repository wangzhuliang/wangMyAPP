package zl.wang.cn.com.wangmyapp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import zl.wang.cn.com.wangmyapp.R;
import zl.wang.cn.com.wangmyapp.view.activity.tagview.TagEditActivity;
import zl.wang.cn.com.wangmyapp.view.activity.tagview.TagListActivity;
import zl.wang.cn.com.wangmyapp.view.activity.tagview.utils.DataRepo;
import zl.wang.cn.com.wangmyapp.view.activity.tagview.view.ITagView;
import zl.wang.cn.com.wangmyapp.view.activity.tagview.view.TagTextView;
import zl.wang.cn.com.wangmyapp.view.activity.tagview.view.TagViewGroup;
import zl.wang.cn.com.wangmyapp.view.activity.tagview.views.TagImageView;

public class TagviewgroupActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int CREATE_TAG = 0x001;

    private TagImageView mTagImageView;
    private Button mButton;
    private Button mListBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag_viewgroup);

        DataRepo.initData();
        mTagImageView = (TagImageView) findViewById(R.id.tagImageView);
        mButton = (Button) findViewById(R.id.transButton);
        mListBtn = (Button) findViewById(R.id.listBtn);
        mTagImageView.setImageUrl("http://ci.xiaohongshu.com/0c62c1d9-8183-4410-82cf-80492b88fdad@r_1280w_1280h.jpg");
        mTagImageView.setTagGroupClickListener(mTagGroupClickListener);
        mTagImageView.setTagList(DataRepo.tagGroupList);
        mTagImageView.setOnClickListener(this);
        mButton.setOnClickListener(this);
        mListBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tagImageView:
                mTagImageView.excuteTagsAnimation();
                break;
            case R.id.transButton:
                startActivityForResult(new Intent(TagviewgroupActivity.this, TagEditActivity.class), CREATE_TAG);
                break;
            case R.id.listBtn:
                startActivity(new Intent(TagviewgroupActivity.this, TagListActivity.class));
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == CREATE_TAG) {
            mTagImageView.setTagList(DataRepo.tagGroupList);
        }
    }

    private TagViewGroup.OnTagGroupClickListener mTagGroupClickListener = new TagViewGroup.OnTagGroupClickListener() {
        @Override
        public void onCircleClick(TagViewGroup container) {
            Toast.makeText(TagviewgroupActivity.this, "点击中心圆", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onTagClick(TagViewGroup container, ITagView tag, int position) {
            Toast.makeText(TagviewgroupActivity.this, "点击Tag->" + ((TagTextView) tag).getText().toString(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onLongPress(final TagViewGroup group) {
            Toast.makeText(TagviewgroupActivity.this, "点击中心圆", Toast.LENGTH_SHORT).show();
        }
    };

}
