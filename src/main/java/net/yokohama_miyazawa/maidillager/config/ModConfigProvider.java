package net.yokohama_miyazawa.maidillager.config;

/*
 * Copyright (c) 2021 Tutorials By Kaupenjoe
 * Slightly modified by Yokohama-Miyazawa 2023
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ModConfigProvider implements SimpleConfig.DefaultConfig {

    private String configContents = "";

    public List<ConfigRow> getConfigsList() {
        return configsList.values().stream().toList();
    }

    public final HashMap<String, ConfigRow> configsList = new HashMap<>();

    public void addKeyValue(ConfigRow<?> keyValue) {
        configsList.put(keyValue.key, keyValue);
        updateContents();
    }

    public void setKeyValue(ConfigRow<?> keyValue) {
        if (configsList.containsKey(keyValue.key)) {
            configsList.replace(keyValue.key, keyValue);
        } else {
            configsList.put(keyValue.key, keyValue);
        }
        updateContents();
    }

    public void updateContents() {
        configContents = "";
        configsList.forEach((key, keyValue) -> {
            configContents += keyValue.key + "=" + keyValue.value + " #"
                    + keyValue.description + " | default: " + keyValue.value + "\n";
        });
    }

    @Override
    public String get() {
        return configContents;
    }
}
