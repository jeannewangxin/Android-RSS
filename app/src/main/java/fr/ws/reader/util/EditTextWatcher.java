package fr.ws.reader.util;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import fr.ws.reader.R;

/**
 * 文本框监听类(重写)
 * by:Yoga 2017/2/19
 */
public class EditTextWatcher implements TextWatcher, View.OnClickListener, View.OnFocusChangeListener {

    public static final int WATCHER_NORMAL = 0;
    public static final int WATCHER_EMAIL = 1;

    private EditText mEt;
    private int mFlag = WATCHER_NORMAL;

    public EditTextWatcher(EditText et) {
        this.mEt = et;
        mEt.addTextChangedListener(this);
        mEt.setOnFocusChangeListener(this);
    }

    public EditTextWatcher(EditText et, int flag) {
        this.mEt = et;
        this.mFlag = flag;
        mEt.addTextChangedListener(this);
        mEt.setOnFocusChangeListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence s, int i, int i1, int i2) {
        if (mFlag == WATCHER_NORMAL) {
            if (mEt.getText().toString().isEmpty()) {
                if (mEt.isFocused()) {
                    //黄色warning
                    mEt.setBackgroundResource(R.drawable.item_bg_warning);
                } else {
                    //红色error
                    mEt.setBackgroundResource(R.drawable.item_bg_error);
                }
            } else {
                //普通normal
                mEt.setBackgroundResource(R.drawable.item_bg_normal);
            }
        } else if (mFlag == WATCHER_EMAIL) {
            if (mEt.getText().toString().isEmpty() || !StringUtils.isEmail(mEt.getText().toString())) {
                if (mEt.isFocused()) {
                    //黄色warning
                    mEt.setBackgroundResource(R.drawable.item_bg_warning);
                } else {
                    //红色error
                    mEt.setBackgroundResource(R.drawable.item_bg_error);
                }
            } else {
                //普通normal
                mEt.setBackgroundResource(R.drawable.item_bg_normal);
            }
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onClick(View v) {
        mEt.setText("");
    }

    public String getText() {
        return mEt.getText().toString();
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (mFlag == WATCHER_NORMAL) {
            if (mEt.getText().toString().isEmpty())
                if (hasFocus)
                    //黄色warning
                    mEt.setBackgroundResource(R.drawable.item_bg_warning);
                else
                    //红色error
                    mEt.setBackgroundResource(R.drawable.item_bg_error);
        } else if (mFlag == WATCHER_EMAIL) {
            if (mEt.getText().toString().isEmpty() || !StringUtils.isEmail(mEt.getText().toString()))
                if (hasFocus)
                    //黄色warning
                    mEt.setBackgroundResource(R.drawable.item_bg_warning);
                else
                    //红色error
                    mEt.setBackgroundResource(R.drawable.item_bg_error);
        }
    }
}
