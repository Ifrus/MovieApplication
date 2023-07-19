package com.example.movieapplication.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.HashSet;
import java.util.Set;

public class SharedPreferencesHelper<T> {
   /* private SharedPrefences sharedPreferences;

    public  SharedPreferencesHelper(Context context){
        SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void saveData(String key, Set<T> data){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet(key, convertDataToSTringSet(data));
        editor.apply();
    }

    public Set<T> loadData(String key, Set<T> defaultValue){
        Set<String> stringSet = sharedPreferences.getStringSet(key, null);
        return convertStringSeToData(stringSet, defaultValue);
    }

    private Set<String> convertDataToSTringSet(Set<T> data){
        Set<String> stringSet = new HashSet<>();
        for(T item : data){
            stringSet.add(item.toString());
        }
        return stringSet;
    }

    private Set<T> convertStringSeToData(Set<String> stringSet, Set<T> defaultValue){
        if(stringSet == null){
            return defaultValue
        }
        Set<T> data = new HashSet<>();
        for(String item: stringSet){
            data.add((T) item);
        }
        return data;
    }*/
}
