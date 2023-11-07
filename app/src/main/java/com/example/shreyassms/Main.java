package com.example.shreyassms;

import androidx.annotation.NonNull;

public interface Main {
    void onRequstPermissionRequest(int requestCode, @NonNull String[] permission, @NonNull int[] grantResults);
}
