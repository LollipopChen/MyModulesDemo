package com.example.libbase.binding.adapter;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.libbase.binding.command.BindingCommand;

/**
 * EditText 绑定监听
 * @author ChenQiuE
 * Date：2019/3/5 16:19
 * Email：1077503420@qq.com
 */
public class EditTextBindingAdapter {

    /**
     * EditText重新获取焦点的事件绑定
     */
    @BindingAdapter(value = {"requestFocus"},requireAll = false)
    public static void requestFocusCommand(EditText editText, final Boolean needRequestFocus){
        if (needRequestFocus){
            editText.setText(editText.getText().toString().length());
            editText.requestFocus();
            InputMethodManager inputMethodManager = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
        }
        editText.setFocusableInTouchMode(needRequestFocus);
    }

    @BindingAdapter(value = {"addTextChanged"},requireAll = false)
    public static void addTextChangedListener(EditText editText, BindingCommand<String> textValue){
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (textValue != null){
                    textValue.execute(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
