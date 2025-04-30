package com.price.take_new.presenter;

import android.util.Log;

import com.example.takeretrofit.bean.delivery.DeliveryDatum;
import com.price.take_new.model.GetExpressModel;
import com.price.take_new.service.api.OnExpressListener;
import com.price.take_new.service.modelService.IGetExpressModel;
import com.price.take_new.service.viewService.IGetExpressView;

import java.util.List;

/**
 * Created by price on 2/20/2017.
 */

public class GetExpressPresenter {
    private static final String TAG = "GetExpressPresenter";
    private IGetExpressModel expressModel;
    private IGetExpressView expressView;

    public GetExpressPresenter(IGetExpressView expressView) {
        this.expressView = expressView;
        expressModel = new GetExpressModel();
    }

    public void getExpress(String token, int page, String filter,final int index){
        expressView.showLoading(index);
        expressModel.getExpress(token, page,filter, new OnExpressListener() {
            @Override
            public void onSuccess(List<DeliveryDatum> deliveryDatum, int success) {
                Log.e(TAG,"onSuccess");
                expressView.hideLoading(index);
                expressView.showData(deliveryDatum);
                expressView.showToast("加载成功");
            }

            @Override
            public void onMsg(String msg, int hint) {
                Log.e(TAG,"onMsg");
                expressView.hideLoading(index);
                expressView.showToast(msg);
            }

            @Override
            public void onError(Throwable throwable, int error) {
                Log.e(TAG,"onError");
                expressView.hideLoading(index);
                expressView.showToast(throwable.getMessage());
            }
        });
    }
}
