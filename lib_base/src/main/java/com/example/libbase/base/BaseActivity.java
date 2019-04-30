package com.example.libbase.base;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.os.Bundle;
import android.os.LocaleList;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;

import com.crowdfire.cfalertdialog.CFAlertDialog;
import com.example.libbase.R;
import com.example.libbase.bus.Messenger;
import com.example.libbase.utils.LanguageUtils;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Locale;

/**
 * @author ChenQiuE
 * Date：2019/3/1 14:57
 * Email：1077503420@qq.com
 */
public abstract class BaseActivity<V extends ViewDataBinding, VM extends BaseViewModel> extends RxAppCompatActivity implements IBaseListener {

    public static final String ACTION_EXIT_APPLICATION = "exit_application";

    protected V binding;
    protected VM viewModel;
    private int viewModelId;

    private CFAlertDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化接收参数
        initParams();
        //私有的初始化Databinding和ViewModel方法
        initViewDataBinding(savedInstanceState);
        //私有的ViewModel与View的契约事件回调逻辑
        registerUIChangeLiveDataCallBack();
        //页面数据初始化方法
        initData();
        //页面事件监听的方法，一般用于ViewModel层转到View层的事件注册
        initViewObservable();
        //注册Bus
//        viewModel.registerRxBus();
        if (isRegisterEventBus()){
            EventBus.getDefault().register(this);
        }
    }

    protected boolean isRegisterEventBus(){
        return false;
    }

    /**
     * 注册ViewModel与View的契约UI回调事件
     */
    private void registerUIChangeLiveDataCallBack() {
        //加载对话框显示
        viewModel.getUC().getShowDialogEvent().observe(this, this::showDialog);
        //加载对话框消失
        viewModel.getUC().getDismissDialogEvent().observe(this, aVoid -> dismissDialog());
        //跳入新页面
        viewModel.getUC().getStartActivityEvent().observe(this, params -> {
            if(params != null) {
                Class<?> clz = (Class<?>) params.get(BaseViewModel.ParameterField.CLASS);
                Bundle bundle = (Bundle) params.get(BaseViewModel.ParameterField.BUNDLE);
                startActivity(clz, bundle);
            }
        });

        //关闭界面
        viewModel.getUC().getFinishEvent().observe(this, aVoid -> finish());
        //关闭上一层
        viewModel.getUC().getOnBackPressedEvent().observe(this, aVoid -> onBackPressed());
    }

    public void showDialog(String title) {
        if (dialog != null) {
            dialog.show();
        } else {
            CFAlertDialog.Builder builder = new CFAlertDialog.Builder(this,R.style.CustomDialog)
                    .setDialogStyle(CFAlertDialog.CFAlertStyle.ALERT)
                    .setBackgroundColor(ContextCompat.getColor(this,R.color.transparent))
                    .setMessage(title)
                    .setCancelable(false)
                    .addButton(this.getString(R.string.framework_text_dialog_confirm), -1,
                            -1, CFAlertDialog.CFAlertActionStyle.POSITIVE, CFAlertDialog.CFAlertActionAlignment.END,
                            (dialog, which) -> dialog.dismiss());

            dialog = builder.show();
        }
    }

    public void dismissDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    /**
     * 跳转页面
     *
     * @param clz 所跳转的目的Activity类
     */
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(this, clz));
    }

    /**
     * 跳转页面
     *
     * @param clz    所跳转的目的Activity类
     * @param bundle 跳转所携带的信息
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public void startActivityForResult(Class<?> openClass, int requestCode) {
        startActivityForResult(openClass, requestCode, null);
    }

    public void startActivityForResult(Class<?> openClass, int requestCode, Bundle bundle) {
        Intent intent = new Intent(this, openClass);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    public void setResultOk() {
        setResultOk(null);
    }

    public void setResultOk(Bundle bundle) {
        Intent intent = new Intent();
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        setResult(RESULT_OK, intent);
        finish();
    }

    /**
     * 注入绑定
     */
    private void initViewDataBinding(Bundle savedInstanceState){
        //DataBindingUtil类需要在project的build中配置 dataBinding {enabled true }, 同步后会自动关联android.databinding包
        binding = DataBindingUtil.setContentView(this,getLayoutId(savedInstanceState));
        viewModelId = initVariableId();
        viewModel = initViewModel();
        if (viewModel == null){
            Class modelClass;
            Type type = getClass().getGenericSuperclass();
            if (type instanceof ParameterizedType) {
                modelClass = (Class) ((ParameterizedType) type).getActualTypeArguments()[1];
            }else {
                //如果没有指定泛型参数，则默认使用BaseViewModel
                modelClass = BaseViewModel.class;
            }
            viewModel = (VM) createViewModel(this, modelClass);
        }

        //关联ViewModel
        binding.setVariable(viewModelId, viewModel);
        //让ViewModel拥有View的生命周期感应
        getLifecycle().addObserver(viewModel);
        //注入RxLifecycle生命周期
        viewModel.injectLifecycleProvider(this);
    }

    /**
     * 初始化根布局
     *
     * @return 布局layout的id
     */
    public abstract int getLayoutId(Bundle savedInstanceState);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解除Messenger注册
        Messenger.getDefault().unregister(viewModel);
        //解除ViewModel生命周期感应
        getLifecycle().removeObserver(viewModel);
        //解除EventBus绑定
        if (isRegisterEventBus()){
            EventBus.getDefault().unregister(this);
        }
        //解除数据绑定
        if(binding != null){
            binding.unbind();
        }
    }

    public  <T extends ViewModel> T createViewModel(FragmentActivity activity, Class<T> modelClass) {
        return ViewModelProviders.of(activity).get(modelClass);
    }

    /**
     * 初始化ViewModel
     * @return 继承BaseViewModel的ViewModel
     */
    protected VM initViewModel() {
        return null;
    }

    /**
     * 初始化ViewModel的id
     * @return ViewModel的id
     */
    protected abstract int initVariableId();

    /**
     * 初始化界面传递的参数
     */
    @Override
    public void initParams() {

    }

    /**
     * 初始化数据
     */
    @Override
    public void initData() {

    }

    /**
     * 初始化界面观察者监听
     */
    @Override
    public void initViewObservable() {

    }

    /*************↓解决：在8.0以上的系统版本干掉进程后进来，语言切换无效的问题**********************/
    @Override
    protected void attachBaseContext(Context newBase) {
        Context context = languageContext(newBase);
        super.attachBaseContext(context);
    }

    private Context languageContext(Context context) {
        // 8.0及以上使用createConfigurationContext设置configuration
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return updateResources(context);
        } else {
            return context;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private Context updateResources(Context context) {
        Resources resources = context.getResources();
        Locale locale = LanguageUtils.getLocale();
        if (locale == null) {
            return context;
        }
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        configuration.setLocales(new LocaleList(locale));
        return context.createConfigurationContext(configuration);
    }
}
