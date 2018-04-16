package zl.wang.cn.com.wangmyapp.view.fragment.first.child;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.helper.loadviewhelper.help.OnLoadViewListener;
import com.helper.loadviewhelper.load.LoadViewHelper;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.eventbusactivityscope.EventBusActivityScope;
import me.yokeyword.fragmentation.SupportFragment;
import retrofit2.Response;
import zl.wang.cn.com.wangmyapp.MainActivity;
import zl.wang.cn.com.wangmyapp.R;
import zl.wang.cn.com.wangmyapp.adapter.HomeFirstAdapter;
import zl.wang.cn.com.wangmyapp.bean.Article;
import zl.wang.cn.com.wangmyapp.bean.CMSBean;
import zl.wang.cn.com.wangmyapp.contract.WangContract;
import zl.wang.cn.com.wangmyapp.event.TabSelectedEvent;
import zl.wang.cn.com.wangmyapp.helper.DetailTransition;
import zl.wang.cn.com.wangmyapp.model.listener.OnItemClickListener;
import zl.wang.cn.com.wangmyapp.model.WangTask;
import zl.wang.cn.com.wangmyapp.presenter.WangPresenter;
import zl.wang.cn.com.wangmyapp.utils.CollectionUtil;

/**
 * Created by 99142 on 2018/3/3.
 * homefirst
 *
 */

public class HomeFirstFragment extends SupportFragment implements SwipeRefreshLayout.OnRefreshListener,WangContract.View{

    //private Toolbar mToolbar;
    private RecyclerView mRecy;
    private SwipeRefreshLayout mRefreshLayout;
    private FloatingActionButton mFab;

    private HomeFirstAdapter mAdapter;

    private boolean mInAtTop = true;
    private int mScrollTotal;

    private String[] mTitles = new String[]{
            "Use imagery to express a distinctive voice and exemplify creative excellence.",
            "An image that tells a story is infinitely more interesting and informative.",
            "The most powerful iconic images consist of a few meaningful elements, with minimal distractions.",
            "Properly contextualized concepts convey your message and brand more effectively.",
            "Have an iconic point of focus in your imagery. Focus ranges from a single entity to an overarching composition."
    };

    private int[] mImgRes = new int[]{
            R.mipmap.bg_first, R.mipmap.bg_second, R.mipmap.bg_third, R.mipmap.bg_fourth, R.mipmap.bg_fifth
    };

    public static HomeFirstFragment newInstance() {

        Bundle args = new Bundle();

        HomeFirstFragment fragment = new HomeFirstFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private WangPresenter wangPresenter;
    private WangContract.Presenter mPresenter;
    private String urlImage;
    private String urlText;

    LoadViewHelper helper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_first, container, false);
        EventBusActivityScope.getDefault(_mActivity).register(this);
        //FragmentHomeFirstBinding fragmentHomeFirstBinding = DataBindingUtil.setContentView(getActivity(),R.layout.fragment_home_first);
        //initViewWang(fragmentHomeFirstBinding.getRoot());
        initView(view);
        WangTask wangTask = WangTask.getInstance();
        wangPresenter = new WangPresenter(HomeFirstFragment.this,wangTask);

        Window window = getActivity().getWindow();
        //默认API 最低19，状态栏透明
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR2) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            ViewGroup contentView = window.getDecorView().findViewById(Window.ID_ANDROID_CONTENT);
            contentView.getChildAt(0).setFitsSystemWindows(false);
        }
        //fragmentHomeFirstBinding.getRoot()
        return view;
    }

    /**
     * 在onResume()中，调用了 presenter 得start()方法，获取数据并操作view界面的显示。
     */
    @Override
    public void onResume() {
        super.onResume();
        setPresenter(wangPresenter);
    }

    private void initView(View view) {
        mRecy = (RecyclerView) view.findViewById(R.id.recy);
        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
        mFab = (FloatingActionButton) view.findViewById(R.id.fab);
        helper=new LoadViewHelper( view.findViewById(R.id.aaa));
        //helper.setLoadIng(R.layout.load_ing);

        mRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mRefreshLayout.setOnRefreshListener(this);

        mRecy.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mScrollTotal += dy;
                if (mScrollTotal <= 0) {
                    mInAtTop = true;
                } else {
                    mInAtTop = false;
                }
                if (dy > 5) {
                    mFab.hide();
                } else if (dy < -5) {
                    mFab.show();
                }
            }
        });

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(_mActivity, "Action", Toast.LENGTH_SHORT).show();
            }
        });

        helper.setListener(new OnLoadViewListener() {
            @Override
            public void onRetryClick() {
                mPresenter.setWang("code");
            }
        });
    }

    private void initViewWang(View view) {
        mRecy = (RecyclerView) view.findViewById(R.id.recy);
        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
        mFab = (FloatingActionButton) view.findViewById(R.id.fab);
        helper=new LoadViewHelper( view.findViewById(R.id.aaa));
        //helper.setLoadIng(R.layout.load_ing);

        mRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mRefreshLayout.setOnRefreshListener(this);

        mRecy.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mScrollTotal += dy;
                if (mScrollTotal <= 0) {
                    mInAtTop = true;
                } else {
                    mInAtTop = false;
                }
                if (dy > 5) {
                    mFab.hide();
                } else if (dy < -5) {
                    mFab.show();
                }
            }
        });

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(_mActivity, "Action", Toast.LENGTH_SHORT).show();
            }
        });

        helper.setListener(new OnLoadViewListener() {
            @Override
            public void onRetryClick() {
                mPresenter.setWang("code");
            }
        });
    }

    @Override
    public void onRefresh() {
        mRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                //刷新
                mRefreshLayout.setRefreshing(false);
                mPresenter.setWang("code");
            }
        }, 2000);
    }

    private void scrollToTop() {
        mRecy.smoothScrollToPosition(0);
    }

    /**
     * 选择tab事件
     */
    @Subscribe
    public void onTabSelectedEvent(TabSelectedEvent event) {
        if (event.position != MainActivity.FIRST) return;

        if (mInAtTop) {
            mRefreshLayout.setRefreshing(true);
            onRefresh();
        } else {
            scrollToTop();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBusActivityScope.getDefault(_mActivity).unregister(this);
        if (mPresenter != null) {
            mPresenter.destroy();
            mPresenter = null;
        }
    }

    @Override
    public void setPresenter(WangContract.Presenter presenter) {
        this.mPresenter = presenter;
        mPresenter.setWang("code");
    }

    @Override
    public void getWang(Response<CMSBean> cmsBeanResponse) {
            helper.showContent();
            if (CollectionUtil.isEmpty(cmsBeanResponse.body().getPosts().get(0).getImage())) {
                urlImage = "http://cms.youlin365.com" + cmsBeanResponse.body().getPosts().get(0).getImage();
            }
            String urlName = cmsBeanResponse.body().getPosts().get(0).getMeta_title().toString();
            if (CollectionUtil.isEmpty(cmsBeanResponse.body().getPosts().get(0).getTitle())) {
                urlText = cmsBeanResponse.body().getPosts().get(0).getTitle();
            }
            mAdapter = new HomeFirstAdapter(_mActivity,urlImage,urlText,cmsBeanResponse.body().getPosts());
            LinearLayoutManager manager = new LinearLayoutManager(_mActivity);
            mRecy.setLayoutManager(manager);
            mRecy.setAdapter(mAdapter);

            mAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(int position, View view, RecyclerView.ViewHolder vh,String image) {
                    HomeDetailFragment fragment = HomeDetailFragment.newInstance(mAdapter.getItem(position));

                    // 这里是使用SharedElement的用例
                    // LOLLIPOP(5.0)系统的 SharedElement支持有 系统BUG， 这里判断大于 > LOLLIPOP
                    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
                        setExitTransition(new Fade());
                        fragment.setEnterTransition(new Fade());
                        fragment.setSharedElementReturnTransition(new DetailTransition());
                        fragment.setSharedElementEnterTransition(new DetailTransition());

                        // 25.1.0以下的support包,Material过渡动画只有在进栈时有,返回时没有;
                        // 25.1.0+的support包，SharedElement正常
                        extraTransaction()
                                .addSharedElement(((HomeFirstAdapter.VH) vh).img, getString(R.string.image_transition))
                                .addSharedElement(((HomeFirstAdapter.VH) vh).tvTitle, "tv")
                                .start(fragment);

                        /*extraTransaction().addSharedElement(((HomeFirstAdapter.VH) vh).img,image)
                                .addSharedElement(((HomeFirstAdapter.VH) vh).tvTitle, "tv")
                                .start(fragment);*/
                    } else {
                        start(fragment);
                    }
                }
            });

            // Init Datas
            List<Article> articleList = new ArrayList<>();
            for (int i = 0; i < cmsBeanResponse.body().getPosts().size(); i++) {
                //int index = i % 5;
                Article article = new Article(cmsBeanResponse.body().getPosts().get(i).getTitle(),
                        "http://cms.youlin365.com" +cmsBeanResponse.body().getPosts().get(i).getImage());
                articleList.add(article);
            }
            mAdapter.setDatas(articleList);
    }

    @Override
    public void showLoading() {
        helper.showLoading();
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError() {
        helper.showError();
    }

    /**
     * 过isAdded()判断对应Activity是否销毁。
     * 在Fragment在执行异步耗时操作后，如果调用Activity实例，
     * 应当先使用isActive()方法加以判断
     * @return
     */
    @Override
    public boolean isActive() {
        return isAdded();
    }
}
