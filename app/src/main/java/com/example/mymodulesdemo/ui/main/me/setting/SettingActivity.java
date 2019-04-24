package com.example.mymodulesdemo.ui.main.me.setting;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import com.crowdfire.cfalertdialog.CFAlertDialog;
import com.example.libbase.base.BaseActivity;
import com.example.libbase.console.LanguageConst;
import com.example.libbase.preferences.LanguageManager;
import com.example.libbase.utils.LanguageUtils;
import com.example.libbase.widget.toast.ToastAlert;
import com.example.login.BR;
import com.example.mymodulesdemo.R;
import com.example.mymodulesdemo.databinding.ActivitySettingBinding;
import com.example.mymodulesdemo.ui.main.MainActivity;

import java.util.Locale;

/**
 * 设置
 * @author ChenQiuE
 * Date：2019/4/10 11:12
 * Email：qiue.chen@supernovachina.com
 */
public class SettingActivity extends BaseActivity<ActivitySettingBinding,SettingViewModel> {

    private int selectIndex = 0;

    @Override
    public int getLayoutId(Bundle savedInstanceState) {
        return R.layout.activity_setting;
    }

    @Override
    protected int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        viewModel.initToolBar("设置");

        switch (LanguageManager.getInstance().getLanguageId()) {
            case LanguageConst.ZH_RCN:
                selectIndex = 0;
                break;
            case LanguageConst.ZH_RTW:
                selectIndex = 1;
                break;
            case LanguageConst.ENGLISH:
                selectIndex = 2;
                break;
            default:
                selectIndex = 0;
                break;
        }
    }

    @Override
    public void initViewObservable() {
        viewModel.uc.languageEvent.observe(this, o -> {
            String[] array = {"简体中文", "繁体中文","English"};
            CFAlertDialog.Builder builder = new CFAlertDialog.Builder(SettingActivity.this);
            builder.setDialogStyle(CFAlertDialog.CFAlertStyle.ALERT);
            builder.setTitle("语言设置");
            builder.setSingleChoiceItems(array,
                    selectIndex, (dialogInterface, index) -> {
                        selectIndex = index;
                        String language ;
                        if (index == 0){
                            language = LanguageConst.ZH_RCN;
                        }else if (index == 1){
                            language = LanguageConst.ZH_RTW;
                        }else if (index == 2){
                            language = LanguageConst.ENGLISH;
                        }else {
                            language = LanguageConst.ZH_RCN;
                        }
                        changeLanguage(language);
                        dialogInterface.dismiss();
                    });
            builder.show();
        });
    }

    /**
     * 切换语言
     * @param language 语言
     */
    private void changeLanguage(String language) {
        LanguageManager.getInstance().setLanguageId(language);

        Locale locale;
        switch (language) {
            case LanguageConst.ZH_RCN:
                // 简体
                locale = Locale.SIMPLIFIED_CHINESE;
                break;

            case LanguageConst.ENGLISH:
                // 英文
                locale = Locale.ENGLISH;
                break;

            default:
                locale = Locale.TAIWAN;
                break;
        }

        if (locale != null) {
            Resources resource = getResources();
            Configuration configuration = resource.getConfiguration();
            configuration.setLocale(locale);

            // 8.0及以上使用createConfigurationContext设置configuration
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                createConfigurationContext(configuration);
            } else {
                DisplayMetrics dm = getResources().getDisplayMetrics();
                getResources().updateConfiguration(configuration, dm);
            }
        }

        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
