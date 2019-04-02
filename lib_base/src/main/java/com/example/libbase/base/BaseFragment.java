package com.example.libbase.base;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crowdfire.cfalertdialog.CFAlertDialog;
import com.example.libbase.R;
import com.example.libbase.bus.Messenger;
import com.trello.rxlifecycle2.components.support.RxFragment;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;

/**
 * @author ChenQiuE
 * Date：2019/3/5 15:27
 * Email：1077503420@qq.com
 */
public abstract class BaseFragment<V extends ViewDataBinding,VM extends BaseViewModel> extends RxFragment implements IBaseListener {

    protected V binding;
    protected VM viewModel;
    private int viewModelId;

    private CFAlertDialog dialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化接收参数
        initParams();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //解除Messenger注册
        Messenger.getDefault().unregister(viewModel);
        //解除ViewModel生命周期感应
        getLifecycle().removeObserver(viewModel);
//        if (viewModel != null) {
//            viewModel.removeRxBus();
//        }
        if (isRegisterEventBus()){
            EventBus.getDefault().unregister(this);
        }
        if(binding != null){
            binding.unbind();
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, initContentView(inflater, container, savedInstanceState), container, false);
        viewModelId = initVariableId();
        viewModel = initViewModel();
        if (viewModel == null) {
            Class modelClass;
            Type type = getClass().getGenericSuperclass();
            if (type instanceof ParameterizedType) {
                modelClass = (Class) ((ParameterizedType) type).getActualTypeArguments()[1];
            } else {
                //如果没有指定泛型参数，则默认使用BaseViewModel
                modelClass = BaseViewModel.class;
            }
            viewModel = (VM) createViewModel(this, modelClass);
        }
        binding.setVariable(viewModelId, viewModel);
        //让ViewModel拥有View的生命周期感应
        getLifecycle().addObserver(viewModel);
        //注入RxLifecycle生命周期
        viewModel.injectLifecycleProvider(this);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
        viewModel.getUC().getFinishEvent().observe(this, aVoid -> Objects.requireNonNull(getActivity()).finish());
        //关闭上一层
        viewModel.getUC().getOnBackPressedEvent().observe(this, aVoid -> Objects.requireNonNull(getActivity()).onBackPressed());
    }

    public void showDialog(String title) {
        if (dialog != null) {
            dialog.show();
        } else {
            CFAlertDialog.Builder builder = new CFAlertDialog.Builder(getActivity(), R.style.CustomDialog)
                    .setDialogStyle(CFAlertDialog.CFAlertStyle.ALERT)
                    .setBackgroundColor(ContextCompat.getColor(Objects.requireNonNull(getContext()),R.color.transparent))
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
        startActivity(new Intent(getContext(), clz));
    }

    /**
     * 跳转页面
     *
     * @param clz    所跳转的目的Activity类
     * @param bundle 跳转所携带的信息
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent(getContext(), clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * =====================================================================
     **/

    //刷新布局
    public void refreshLayout() {
        if (viewModel != null) {
            binding.setVariable(viewModelId, viewModel);
        }
    }

    /**
     * 初始化根布局
     *
     * @return 布局layout的id
     */
    public abstract int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

    /**
     * 初始化ViewModel的id
     *
     * @return BR的id
     */
    public abstract int initVariableId();

    /**
     * 初始化ViewModel
     *
     * @return 继承BaseViewModel的ViewModel
     */
    public VM initViewModel() {
        return null;
    }


    public boolean isBackPressed() {
        return false;
    }

    protected boolean isRegisterEventBus(){
        return false;
    }

    /**
     * 创建ViewModel
     *
     * @param cls
     * @param <T>
     * @return
     */
    public <T extends ViewModel> T createViewModel(Fragment fragment, Class<T> cls) {
        return ViewModelProviders.of(fragment).get(cls);
    }

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
}
