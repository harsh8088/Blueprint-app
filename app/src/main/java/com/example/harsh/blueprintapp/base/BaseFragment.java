package com.example.harsh.blueprintapp.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.harsh.blueprintapp.R;


public class BaseFragment extends Fragment {

    private static final String TAG = BaseFragment.class.getSimpleName();
    private Toast toast;
    private ProgressDialog progress;
    private boolean isAlive;
    private boolean isActive;
    //  private FirebaseAnalytics firebaseAnalytics;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (BuildConfig.DEBUG)
//            LogUtils.e(TAG, "onCreate of: " + this.getClass().getSimpleName());
        isAlive = true;
//        firebaseAnalytics = FirebaseAnalytics.getInstance(getActivity());
    }

    @Override
    public void onResume() {
        isActive = true;
        super.onResume();
//        FirebaseUtils.recordScreenView(getActivity(), firebaseAnalytics, this.getClass().getSimpleName());
    }

    @Override
    public void onPause() {
        isActive = false;
        super.onPause();
    }

    @Override
    public void onDestroy() {
        isAlive = false;
        super.onDestroy();
    }

    public BaseActivity getBaseActivity() {
        if (getActivity() != null)
            return (BaseActivity) getActivity();
        else
            return null;
    }

    protected boolean isAlive() {
        return isAlive;
    }

    protected boolean isActive() {
        return isActive;
    }

    protected void setTitle(String title) {
        if (getActivity() != null && getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).setTitleBar(title);
        }
    }

    protected void setSubTitle(String title) {
        if (getActivity() != null && getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).setSubTitleBar(title);
        }
    }

    protected void showToast(String msg) {
        if (toast != null)
            toast.cancel();
        toast = Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT);
        toast.show();
    }

    protected void startActivity(Class c) {
        Intent intent = new Intent(getContext(), c);
        startActivity(intent);
    }

    public void startActivityClearTop(Class c) {
        Intent intent = new Intent(getContext(), c);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    protected void startActivityWithFinish(Class c) {
        Intent intent = new Intent(getContext(), c);
        startActivity(intent);
        if (getActivity() != null)
            getActivity().finish();
    }

    protected void startActivity(Class c, Bundle bundle) {
        Intent intent = new Intent(getContext(), c);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    protected void replaceFragment(int container, BaseFragment fragment, boolean addToBackStack) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(container, fragment);
        if (addToBackStack)
            fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
        fragmentTransaction.commit();
    }

    protected void showProgress() {
        if (getActivity() != null) {
            hideProgress();
            progress = ProgressDialog.show(new ContextThemeWrapper(getActivity(),
                    android.R.style.Theme_Holo_Light), "", "", true, false);
            progress.setContentView(R.layout.progressbar);
        }
    }

    protected void hideProgress() {
        if (getActivity() != null) {
            if (progress != null && progress.isShowing())
                progress.dismiss();
        }
    }

    protected void hideKeyboard(View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
