package zl.wang.cn.com.wangmyapp.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import zl.wang.cn.com.wangmyapp.R;
import zl.wang.cn.com.wangmyapp.databinding.ActivityDatabindingBinding;
import zl.wang.cn.com.wangmyapp.model.User;

/**
 * Created by lcling on 2018/3/28.
 * databinding快速入门
 */

public class DataBindingActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /*用 DatabindingUtil.setContentView() 来替换掉 setContentView()，
        然后创建一个 user 对象，通过 binding.setUser(user) 与 variable 进行绑定。*/
        ActivityDatabindingBinding binding = DataBindingUtil.setContentView(this,
                R.layout.activity_databinding);
        User user = new User("fei","Liang",10);
        binding.setUser(user);
        binding.setFirstName("aaa");
        binding.setLastName("bbb");
        binding.setPresenter(new Presenter());
    }

    /**
     * 使用类方法
     */
    public static class MyStringUtils {
        public static String capitalize(final String word) {
            if (word.length() > 1) {
                return String.valueOf(word.charAt(0)).toUpperCase() + word.substring(1);
            }
            return word;
        }
    }

    public class Presenter {
        public void onClick(View view) {
            Toast.makeText(DataBindingActivity.this,"第一行点击",Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * Fragment加载布局修改为:
     */
    /*private FragmentHomeBinding mBinding;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentHomeBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }*/


    /**
     * RecyclerView的绑定
     */
    /*public class BindingViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {

        private T mBinding;

        public BindingViewHolder(T binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public T getBinding() {
            return mBinding;
        }
    }*/

    /*接着RecyclerView的Adapter直接使用这个BindingViewHolder，
    在onCreateViewHolder里面生成该ViewHolder:*/
    /*public BindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(mInflater, R.layout.item_xxx,parent, false);
        return new BindingViewHolder(binding);
    }*/

    //在onBindViewHolder里面进行数据绑定和设置Listener：
    /*@Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {
        final String item = mDatas.get(position);
        //给ViewHolder的xml里面的item变量进行数据绑定
        holder.getBinding().setVariable(BR.item, item);
        //建立绑定关系,在主线程中实时更新任何进行了绑定的数据
        holder.getBinding().executePendingBindings();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onItemClick(item);
                }
            }
        });
    }*/

    /*https://github.com/evant/binding-collection-adapter
    https://github.com/radzio/android-data-binding-recyclerview
    https://github.com/markzhai/DataBindingAdapter
    前两者都可以让你少写代码，并且功能十分强大。但是我觉得灵活性是第三个比较好。
    这也是我目前在项目中使用的。这个项目的使用方法比较简单，可以直接去看它的ReadMe，这里就不多说了。*/

}
