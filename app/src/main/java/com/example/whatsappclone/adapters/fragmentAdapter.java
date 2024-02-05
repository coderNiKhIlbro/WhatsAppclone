package com.example.whatsappclone.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.whatsappclone.fragments.chats;
import com.example.whatsappclone.fragments.status;

public class fragmentAdapter extends FragmentPagerAdapter {

    public fragmentAdapter(FragmentManager fm)
    {
        super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:return new chats();
            case 1:return new status();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title=null;
        if (position==0){
            title = "Chats";
        }
        if (position==1){
            title = "Status";
        }

        return title;
    }
}

