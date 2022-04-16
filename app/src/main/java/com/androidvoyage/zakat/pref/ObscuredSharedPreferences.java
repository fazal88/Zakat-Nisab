package com.androidvoyage.zakat.pref;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Akhil on 01/02/17.
 */
public class ObscuredSharedPreferences implements SharedPreferences {
    private static final String DEBUG = "SharedPreferences";

    private String secret;
    protected SharedPreferences delegate;
    protected Context context;

    public ObscuredSharedPreferences(Context context, String secret, SharedPreferences delegate) {
        this.delegate = delegate;
        this.context = context;
        this.secret = secret;
    }

    public class Editor implements SharedPreferences.Editor {
        protected SharedPreferences.Editor delegate;

        public Editor() {
            this.delegate = ObscuredSharedPreferences.this.delegate.edit();
        }

        @Override
        public Editor putBoolean(String key, boolean value) {
            delegate.putString(key, encrypt(Boolean.toString(value)));
            return this;
        }

        @Override
        public Editor putFloat(String key, float value) {
            delegate.putString(key, encrypt(Float.toString(value)));
            return this;
        }

        @Override
        public Editor putInt(String key, int value) {
            delegate.putString(key, encrypt(Integer.toString(value)));
            return this;
        }

        @Override
        public Editor putLong(String key, long value) {
            delegate.putString(key, encrypt(Long.toString(value)));
            return this;
        }

        @Override
        public Editor putString(String key, String value) {
            delegate.putString(key, encrypt(value));
            return this;
        }

        @Override
        public SharedPreferences.Editor putStringSet(String key, Set<String> values) {
            Set<String> encryptedValues = null;
            if (values != null) {
                encryptedValues = new HashSet<>();
                for (String value : values) {
                    encryptedValues.add(encrypt(value));
                }
            }
            delegate.putStringSet(key, encryptedValues);
            return this;
        }

        @Override
        public void apply() {
            delegate.apply();
        }

        @Override
        public Editor clear() {
            delegate.clear();
            return this;
        }

        @Override
        public boolean commit() {
            return delegate.commit();
        }

        @Override
        public Editor remove(String s) {
            delegate.remove(s);
            return this;
        }
    }

    public Editor edit() {
        return new Editor();
    }


    @Override
    public Map<String, ?> getAll() {
        throw new UnsupportedOperationException(); // left as an exercise to the reader
    }

    @Override
    public boolean getBoolean(String key, boolean defValue) {
        try {
            final String v = delegate.getString(key, null);
            return v != null ? Boolean.parseBoolean(decrypt(v)) : defValue;
        } catch (Exception e) {
            e.printStackTrace();
            delegate.edit().putBoolean(key, defValue).apply();
            return defValue;
        }
    }

    @Override
    public float getFloat(String key, float defValue) {
        try {
            final String v = delegate.getString(key, null);
            return v != null ? Float.parseFloat(decrypt(v)) : defValue;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            delegate.edit().putFloat(key, defValue).apply();
            return defValue;
        }
    }

    @Override
    public int getInt(String key, int defValue) {
        try {
            final String v = delegate.getString(key, null);
            return v != null ? Integer.parseInt(decrypt(v)) : defValue;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            delegate.edit().putInt(key, defValue).apply();
            return defValue;
        }
    }

    @Override
    public long getLong(String key, long defValue) {
        try {
            final String v = delegate.getString(key, null);
            return v != null ? Long.parseLong(decrypt(v)) : defValue;
        } catch (Exception e) {
            e.printStackTrace();
            delegate.edit().putLong(key, defValue).apply();
            return defValue;
        }
    }

    @Override
    public String getString(String key, String defValue) {
        final String v = delegate.getString(key, null);
        return v != null ? decrypt(v) : defValue;
    }

    @Nullable
    @Override
    public Set<String> getStringSet(String key, Set<String> defValues) {
        final Set<String> stringSet = delegate.getStringSet(key, null);
        Set<String> decryptedValues = null;
        if (stringSet != null) {
            decryptedValues = new HashSet<>();
            for (String value : stringSet) {
                decryptedValues.add(decrypt(value));
            }
        }
        return decryptedValues;
    }

    @Override
    public boolean contains(String s) {
        return delegate.contains(s);
    }

    @Override
    public void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        delegate.registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
    }

    @Override
    public void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        delegate.unregisterOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
    }


    protected String encrypt(String value) {

        try {
            return new SharedPreferenceEncryption(secret).encrypt(value);
        } catch (Exception e) {
            if(e.getMessage() != null){
                Log.d(DEBUG, "encrypt: "+e.getMessage());
            }else{
                e.printStackTrace();
            }
        }
        return "";
    }

    protected String decrypt(String value) {
        try {
            return new SharedPreferenceEncryption(secret).decrypt(value);
        } catch (Exception e) {
            if(e.getMessage() != null){
                Log.d(DEBUG, "encrypt: "+e.getMessage());
            }else{
                e.printStackTrace();
            }
        }
        return "";
    }

}