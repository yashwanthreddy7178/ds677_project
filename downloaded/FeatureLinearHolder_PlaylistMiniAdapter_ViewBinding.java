// Generated code from Butter Knife. Do not modify!
package com.ldt.musicr.ui.page.featurepage;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.ldt.musicr.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FeatureLinearHolder$PlaylistMiniAdapter_ViewBinding implements Unbinder {
  private FeatureLinearHolder.PlaylistMiniAdapter target;

  private View view7f0a0082;

  @UiThread
  public FeatureLinearHolder$PlaylistMiniAdapter_ViewBinding(
      final FeatureLinearHolder.PlaylistMiniAdapter target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.back_top_header, "field 'mHeaderPanel' and method 'goToPlaylistChildTab'");
    target.mHeaderPanel = view;
    view7f0a0082 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.goToPlaylistChildTab();
      }
    });
    target.mTitle = Utils.findRequiredViewAsType(source, R.id.title, "field 'mTitle'", TextView.class);
    target.mRecyclerView = Utils.findRequiredViewAsType(source, R.id.recycler_view, "field 'mRecyclerView'", RecyclerView.class);
    target.mCount = Utils.findRequiredViewAsType(source, R.id.number, "field 'mCount'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    FeatureLinearHolder.PlaylistMiniAdapter target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mHeaderPanel = null;
    target.mTitle = null;
    target.mRecyclerView = null;
    target.mCount = null;

    view7f0a0082.setOnClickListener(null);
    view7f0a0082 = null;
  }
}
