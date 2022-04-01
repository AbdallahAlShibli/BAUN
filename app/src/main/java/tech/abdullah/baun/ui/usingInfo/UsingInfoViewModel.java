package tech.abdullah.baun.ui.usingInfo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UsingInfoViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public UsingInfoViewModel() {
        mText = new MutableLiveData<>();
//        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}