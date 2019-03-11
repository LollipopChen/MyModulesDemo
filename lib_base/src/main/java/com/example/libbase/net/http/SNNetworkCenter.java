/*
 * SuperNovaFramework
 * SNNetworkCenter
 * Created by Leo.Mok on 2018-04-10.
 * Copyright © 2018 Supernova Software. All rights reserved.
 */

package com.example.libbase.net.http;

import com.example.libbase.console.SNPreferenceConstants;
import com.example.libbase.preferences.SNPreferenceManager;

/**
 * SNNetworkCenter
 *
 * @author Leo.Mok
 * @date 2018/4/10
 */
public class SNNetworkCenter {
    private static final SNNetworkCenter INSTANCE = new SNNetworkCenter();

    private String serverUrl;

    public static SNNetworkCenter getInstance() {
        return INSTANCE;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public String getToken() {
        return SNPreferenceManager.getDefault().getString(SNPreferenceConstants.TOKEN, "");
    }

    public void updateToken(String token) {
        SNPreferenceManager.getDefault().put(SNPreferenceConstants.TOKEN, token);
    }
}
