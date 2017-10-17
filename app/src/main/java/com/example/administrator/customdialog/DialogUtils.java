package com.example.administrator.customdialog;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;


public class DialogUtils {

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void showQuestionDialog(final Context context, String title, String text,
                                          String confirm, String cancle,
                                          boolean hideCancleTV,
                                          final OnClickYesListener listenerYes,
                                          final OnClickNoListener listenerNo) {

        View view = LayoutInflater.from(context).inflate(R.layout.dialog_message, null);
        TextView confirmButton; //确定按钮
        TextView cancleButton; //取消按钮
        final TextView content; //内容
        confirmButton = (TextView) view.findViewById(R.id.dialog_btn_comfirm);
        cancleButton = (TextView) view.findViewById(R.id.dialog_btn_cancle);
        content = (TextView) view.findViewById(R.id.dialog_txt_content);
        //隐藏一个按钮
        if(hideCancleTV){
            cancleButton.setVisibility(View.GONE);
        }

        if (!isBlank(text)) {
            content.setText(text);
            final Dialog dialog = new Dialog(context);
            dialog.setContentView(view);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

            cancleButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listenerNo != null) {
                        listenerNo.onClickNo(dialog);
                    }
                }
            });
            confirmButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listenerYes != null) {
                        listenerYes.onClickYes(dialog);
                    }
                }
            });


//            dialog.show();
//            Window window = dialog.getWindow();
//            DisplayMetrics dm = context.getResources().getDisplayMetrics();
//            int displayWidth = dm.widthPixels;
//            int displayHeight = dm.heightPixels;
//            android.view.WindowManager.LayoutParams p = window.getAttributes(); //获取对话框当前的参数值
//            p.width = (int) (displayWidth * 0.68); //宽度设置为屏幕的0.5
//            p.height = (int) (displayHeight * 0.24); //高度设置为屏幕的0.5
//            dialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
//            window.setAttributes(p);  //设置生效

            Window dialogWindow = dialog.getWindow();
            dialogWindow.setGravity(Gravity.CENTER);
            dialogWindow.setWindowAnimations(R.style.dialogstyle); // 添加动画
            WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
            DisplayMetrics dm = context.getResources().getDisplayMetrics();
            int displayWidth = dm.widthPixels;
            int displayHeight = dm.heightPixels;
            lp.width = (int) (displayWidth * 0.68); //宽度设置为屏幕的0.5
            lp.height = (int) (displayHeight * 0.25); //高度设置为屏幕的0.5
            view.measure(0, 0);
            lp.alpha = 9f; // 透明度
            dialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
            dialogWindow.setAttributes(lp);
            dialog.show();
        }

    }

    /**
     * 处理字符的方法
     *
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 提问框的 Listener
     */
    public interface OnClickYesListener {
        void onClickYes(Dialog dialog);
    }


    /**
     * 提问框的 Listener
     */
    public interface OnClickNoListener {
        void onClickNo(Dialog dialog);
    }
}
