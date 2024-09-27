package de.tubs.cs.ias.fly;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.appsflyer.AppsFlyerLib;

import de.tubs.cs.ias.fly.databinding.CreateObjectBinding;

public class A1_CreateObject extends Fragment {

    private CreateObjectBinding binding;
    private CharSequence text = "2. State: Create Object";

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = CreateObjectBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonCreateObject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.appsFlyer = AppsFlyerLib.getInstance();
                binding.textviewCreateObject.setText(text + " [DONE]");
            }
        });

        binding.buttonCreateObjectNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(A1_CreateObject.this)
                        .navigate(R.id.action_CreateObject_to_InquireConsent);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
