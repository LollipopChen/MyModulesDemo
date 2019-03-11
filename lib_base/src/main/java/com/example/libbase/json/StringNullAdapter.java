/*
 * SuperNovaFramework
 * StringNullAdapter
 * Created by Leo.Mok on 2018-03-29.
 * Copyright © 2018 Supernova Software. All rights reserved.
 */

package com.example.libbase.json;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * StringNullAdapter
 *
 * @author leo
 * @date 2018/1/30
 */

public class StringNullAdapter extends TypeAdapter<String> {
    @Override
    public String read(JsonReader reader) throws IOException {
        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull();
            return "";
        }
        return reader.nextString();
    }

    @Override
    public void write(JsonWriter writer, String value) throws IOException {
        if (value == null) {
            writer.nullValue();
            return;
        }
        writer.value(value);
    }
}
