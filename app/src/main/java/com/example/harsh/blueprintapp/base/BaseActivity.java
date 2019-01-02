package com.example.harsh.blueprintapp.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextThemeWrapper;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.harsh.blueprintapp.R;


public abstract class BaseActivity extends AppCompatActivity {

    private static final String TAG = BaseActivity.class.getSimpleName();
    private Toast toast;
    private Toolbar toolbar;
    private ProgressDialog progress;
    private boolean isAlive;
    private boolean isActive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (BuildConfig.DEBUG)
//            LogUtils.e(TAG, "onCreate of: " + this.getClass().getSimpleName());
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        isAlive = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        isActive = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        isActive = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isAlive = false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected boolean isAlive() {
        return isAlive;
    }

    public boolean isActive() {
        return isActive;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    protected Toolbar setToolbar(int id) {
        return setToolbar(id, false, true);
    }

    /**
     * * Show the custom toolbar
     *
     * @param id       Toolbar id which is added to layout
     * @param showBack True if you want to show the back button, otherwise false
     * @return Custom Toolbar of the screen
     */
    protected Toolbar setToolbar(int id, boolean showBack) {
        return setToolbar(id, showBack, false);
    }

    private void setTitleB(CharSequence title) {
        TextView mTitleText = toolbar.findViewById(R.id.toolbar_title);
        if (mTitleText != null) {
            mTitleText.setText(title);
            mTitleText.setSelected(true);
        }
    }

    private void setSubTitleB(CharSequence title) {
        TextView subTitleTextView = toolbar.findViewById(R.id.toolbar_subtitle);
        if (subTitleTextView != null) {
            if (title.equals("")) {
                subTitleTextView.setVisibility(View.GONE);
            } else {
                subTitleTextView.setText(title);
                subTitleTextView.setVisibility(View.VISIBLE);
            }
        }
    }

    protected Toolbar setToolbar(int id, boolean showBack, boolean showDivider) {
        toolbar = findViewById(id);
        if (toolbar != null) {
            toolbar.setTitle("");
            setTitleB(getTitle());
            setSupportActionBar(toolbar);
            if (showBack) {
                ActionBar actionBar = getSupportActionBar();
                if (actionBar != null) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                }
            }
            View dividerView = findViewById(R.id.toolbar_divider);
            if (!showDivider && dividerView != null)
                dividerView.setVisibility(View.GONE);
        }
        return toolbar;
    }

    public void setTitleBar(String text) {
        setTitleB(text);
    }

    public void setSubTitleBar(String text) {
        setSubTitleB(text);
    }

    public void setDivider(boolean value) {
        View dividerView = findViewById(R.id.toolbar_divider);
        if (dividerView != null) {
            dividerView.setVisibility(value ? View.VISIBLE : View.GONE);
        }
    }

    //Start Activity And finish Previous one
    public void startActivityWithFinish(Class c) {
        startActivityWithFinish(c, null);
    }

    public void startActivityWithFinish(Class c, Bundle bundle) {
        Intent intent = new Intent(this, c);
        if (bundle != null)
            intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    public void startActivityClearTop(Class c) {
        Intent intent = new Intent(this, c);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void startActivityClearTop(Class c, Bundle bundle) {
        Intent intent = new Intent(this, c);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void startActivity(Class c) {
        Intent intent = new Intent(this, c);
        startActivity(intent);
    }

    protected void startActivity(Class c, Bundle bundle) {
        Intent intent = new Intent(this, c);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void showToast(final String message) {
        if (toast != null)
            toast.cancel();
        toast = Toast.makeText(this.getApplicationContext(), message, Toast.LENGTH_LONG);
        toast.show();
    }

    protected void cancelToast() {
        if (toast != null)
            toast.cancel();
    }

    protected void replaceFragment(int container, BaseFragment fragment) {
        replaceFragment(container, fragment, false);
    }

    protected void replaceFragment(int container, BaseFragment fragment, boolean addToBackStack) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(container, fragment);
        if (addToBackStack)
            fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
        fragmentTransaction.commit();
    }

    public void showProgress() {
        hideProgress();
        progress = ProgressDialog.show(new ContextThemeWrapper(this,
                android.R.style.Theme_Holo_Light), "", "", true, false);
        progress.setContentView(R.layout.progressbar);
    }

    public void hideProgress() {
        if (progress != null) {
            progress.dismiss();
        }
    }

    protected void hideKeyboard(View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
