package de.tubs.cs.ias.fly;

import static androidx.core.content.PackageManagerCompat.LOG_TAG;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import com.appsflyer.AFInAppEventParameterName;
import com.appsflyer.AFInAppEventType;
import com.appsflyer.attribution.AppsFlyerRequestListener;

import java.util.HashMap;

import de.tubs.cs.ias.fly.databinding.CoreFunctionalityBinding;

public class D_CoreFunctionality extends Fragment {

    private MainActivity getMainActivity() {
        return (MainActivity) this.getActivity();
    }

    private CoreFunctionalityBinding binding;
    private CharSequence text = "4. State: Core Functionality";

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = CoreFunctionalityBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    private void appsFlyerCoreFunc() {
        binding.coreFunctionalityInfo.setText(text + " [STARTED]");
        HashMap<String, Object> customData = new HashMap<String, Object>();
        customData.put(AFInAppEventParameterName.ACHIEVEMENT_ID,"42");
        MainActivity.appsFlyer.logEvent(D_CoreFunctionality.this.getMainActivity(), AFInAppEventType.ACHIEVEMENT_UNLOCKED, customData, new AppsFlyerRequestListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onSuccess() {
                Log.d(LOG_TAG,"success");
            }

            @SuppressLint("RestrictedApi")
            @Override
            public void onError(int i, @NonNull String s) {
                Log.d(LOG_TAG,"failure " + s);
            }
        });
        binding.coreFunctionalityInfo.setText(text + " [LOGGED]");
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.coreFunctionalityInfo.setText(text + " [UNFINISHED]");
        binding.buttonCoreFunc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appsFlyerCoreFunc();
           }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

}