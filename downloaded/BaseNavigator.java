package com.example.thomas.guitartraining.presentation.navigator;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.example.thomas.guitartraining.presentation.activity.StartActivity;
import com.example.thomas.guitartraining.presentation.activity.UserPanelActivity;
import com.example.thomas.guitartraining.presentation.component.navigator.ErrorRendererComponent;

public abstract class BaseNavigator {

    protected Activity activity;
    private ErrorRendererComponent errorRendererComponent;
    private int activityViewId;

    BaseNavigator(Activity activity, ErrorRendererComponent errorRendererComponent, int activityViewId) {
        this.activity = activity;
        this.errorRendererComponent = errorRendererComponent;
        this.activityViewId = activityViewId;
    }

    public void renderError(Throwable throwable, int mode, View view) {
        if (mode == ErrorRendererComponent.ERROR_DISPLAY_MODE_SNACKBAR) {
            if (view != null) {
                errorRendererComponent.displayErrorInFragmentView(throwable, activity, view);
            } else {
                errorRendererComponent.displayError(throwable, activity, activityViewId);
            }
        }
    }

    public void renderErrorString(String error, int mode, View view) {
        if (view != null) {
            errorRendererComponent.displayErrorInFragmentView(error, activity, view);
        } else {
            errorRendererComponent.displayErrorString(error, activity, activityViewId);
        }
    }

    public void launchUserPanelActivity(Activity activity) {
        Intent toUserPanelActivity = new Intent(activity, UserPanelActivity.class);
        if (activity != null) {
            activity.startActivity(toUserPanelActivity);
        }
    }
}
