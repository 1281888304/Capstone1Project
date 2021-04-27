import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.logindatabase.R;
import com.example.logindatabase.ui.powder.PowderViewModel;

//public class PowderFragment extends Fragment {
//
//    private PowderViewModel mViewModel;
//
//    public static com.example.logindatabase.ui.powder.PowderFragment newInstance() {
//        return new com.example.logindatabase.ui.powder.PowderFragment();
//    }
//
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
//                             @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.powder_fragment, container, false);
//    }
//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        mViewModel = new ViewModelProvider(this).get(PowderViewModel.class);
//        // TODO: Use the ViewModel
//    }
//
//}