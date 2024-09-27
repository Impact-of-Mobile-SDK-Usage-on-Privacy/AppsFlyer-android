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
import androidx.navigation.fragment.NavHostFragment;

import com.appsflyer.AppsFlyerLib;
import com.appsflyer.AppsFlyerConsent;
import com.appsflyer.attribution.AppsFlyerRequestListener;

import de.tubs.cs.ias.fly.databinding.InquireConsentBinding;

public class B_InquireConsent extends Fragment {

    public InquireConsentBinding binding;
    private CharSequence text = "3. State: Consent";

    private MainActivity getMainActivity() {
        return (MainActivity) this.getActivity();
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = InquireConsentBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(B_InquireConsent.this)
                        .navigate(R.id.action_InquireConsent_to_Initialize);
            }
        });
        binding.buttonConsent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean hasConsentForDataUsage = true; // Based on actual user consent
                boolean hasConsentForAdsPersonalization = true; // Based on actual user consent
                AppsFlyerConsent gdprUserConsent = AppsFlyerConsent.forGDPRUser(hasConsentForDataUsage, hasConsentForAdsPersonalization);
                AppsFlyerLib.getInstance().setConsentData(gdprUserConsent);
                MainActivity.appsFlyer.start(getMainActivity(), C_Initialize.DEV_KEY,new AppsFlyerRequestListener() { @SuppressLint("RestrictedApi")
                    @Override
                    public void onSuccess() {
                        Log.d(LOG_TAG, "Launch sent successfully, got 200 response code from server");
                    }

                    @SuppressLint("RestrictedApi")
                    @Override
                    public void onError(int i, @NonNull String s) {
                        Log.d(LOG_TAG, "Launch failed to be sent:\n" +
                                "Error code: " + i + "\n"
                                + "Error description: " + s);
                        }
                    });
                binding.textviewInquireConsentPost.setText(text + " (YES)");
            }
        });

        binding.buttonRefuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean hasConsentForDataUsage = false; // Based on actual user consent
                boolean hasConsentForAdsPersonalization = false; // Based on actual user consent
                AppsFlyerConsent gdprUserConsent = AppsFlyerConsent.forGDPRUser(hasConsentForDataUsage, hasConsentForAdsPersonalization);
                AppsFlyerLib.getInstance().setConsentData(gdprUserConsent);
                MainActivity.appsFlyer.start(getMainActivity());
                binding.textviewInquireConsentPost.setText(text + " (NO)");
        }});
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
