package com.example.joelin.mwms;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainMenuActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private ViewPager mViewPager;
    private LinearLayout ll_1;
    private GridView view1;

    private List<View> viewList;  //把需要滑动的页卡添加到这个list中
    private ImageView[] imageViews;
    private int currentPosition = 0; // 当前位置

    /*
     * 这里我就偷懒啦、三个切换滑动页面引用的是相同的图标和文字、我就省了去找其他的图片啦
     * 我们可以定义三组数组分别放每个界面的图标和文字内容、
     * 然后修改下 下面的 setGridViewAdapter() 方法 就行啦...
     *
     */
    // 定义图标数组
    private int[] imageRes; /*= {R.drawable.ic_lock_black_24dp, R.drawable.ic_lock_black_24dp,
            R.drawable.ic_lock_black_24dp, R.drawable.ic_lock_black_24dp,
            R.drawable.ic_lock_black_24dp,R.drawable.ic_lock_black_24dp
    };*/
    //定义标题数组
    private String[] itemName; /*= {getString(R.string.menu_item_stock_in), getString(R.string.menu_item_print_label),
            getString(R.string.menu_item_stock_out), getString(R.string.menu_item_stock_return),
            getString(R.string.menu_item_inventory), getString(R.string.menu_item_logout)
    };*/

    public MainMenuActivity() {
    }

    @Override
    protected int getLayoutResourceId(){
        return R.layout.activity_main_menu;
    }

    @Override
    protected void initView(){
        imageRes = new int [] {R.drawable.ic_lock_black_24dp, R.drawable.ic_lock_black_24dp,
                R.drawable.ic_lock_black_24dp, R.drawable.ic_lock_black_24dp,
                R.drawable.ic_lock_black_24dp,R.drawable.ic_lock_black_24dp
        };

        itemName = new String [] {getString(R.string.menu_item_stock_in), getString(R.string.menu_item_print_label),
                getString(R.string.menu_item_stock_out), getString(R.string.menu_item_stock_return),
                getString(R.string.menu_item_inventory), getString(R.string.menu_item_logout)
        };

        mViewPager = (ViewPager) findViewById(R.id.search_viewpager);
        LayoutInflater lf = getLayoutInflater().from(this);
        ll_1 = (LinearLayout) lf.inflate(R.layout.menu_page, null);
        view1 = (GridView) ll_1.findViewById(R.id.myGridView);

        viewList = new ArrayList<View>();  // 将要分页显示的View装入数组中
        viewList.add(ll_1);

        setGridViewAdapter(view1);

        mViewPager.setAdapter(new MyPagerAdapter(viewList));

        LinearLayout llguid = (LinearLayout) findViewById(R.id.llguid);
        imageViews = new ImageView[viewList.size()];
        for(int i= 0;i<viewList.size();i++){
            imageViews[i] = (ImageView) llguid.getChildAt(i);
            imageViews[i].setEnabled(true);
            imageViews[i].setTag(i);
        }
        imageViews[currentPosition].setEnabled(false);
    }

    @Override
    protected void initEvent() {
        view1.setOnItemClickListener(this);
        mViewPager.setOnPageChangeListener(new myOnPageChangeListener());
    }

    @Override
    protected void initMember() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                finish();
                break;
        }

    }
    /**
     *  设置GridView适配器
     */
    private void setGridViewAdapter(GridView mGridView) {
        List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
        int length = itemName.length;
        for(int i=0;i<length;i++){
            HashMap<String,Object> map = new HashMap<String, Object>();
            map.put("ItemImageView", imageRes[i]);
            map.put("ItemTextView", itemName[i]);
            data.add(map);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(this,
                data,
                R.layout.menu_item,
                new String[]{"ItemImageView","ItemTextView"},
                new int[]{R.id.ItemImageView,R.id.itemTextView});
        mGridView.setAdapter(simpleAdapter);
    }

    class MyPagerAdapter extends PagerAdapter {
        List<View> viewList;

        public MyPagerAdapter(List<View> viewList) {
            this.viewList = viewList;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return viewList.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            // TODO Auto-generated method stub
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(View container, int position, Object object) {
            // TODO Auto-generated method stub
            ((ViewPager)container).removeView(viewList.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            // TODO Auto-generated method stub
            container.addView(viewList.get(position));
            // Log.d(ConstantS.TAG, "ViewPager--Position:"+position);
            return viewList.get(position);
        }
    }

    class myOnPageChangeListener implements ViewPager.OnPageChangeListener {

        /**
         *  当滑动状态改变时调用
         */
        @Override
        public void onPageScrollStateChanged(int arg0) {
            // TODO Auto-generated method stub

        }

        /**
         * 当当前页面被滑动时调用
         */
        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // TODO Auto-generated method stub

        }

        /**
         * 当新的页面被选中时调用
         */
        @Override
        public void onPageSelected(int arg0) {

            for(int i=0;i<imageViews.length;i++){
                if(i == arg0){
                    imageViews[arg0].setEnabled(true);
                }else{
                    imageViews[i].setEnabled(false);
                }
            }
        }
    }

}
