package com.util;

import java.text.Normalizer;

public class Normalize {
    public static String NFDToNFC(String str) {
        if (!Normalizer.isNormalized(str, Normalizer.Form.NFC)) {
            str = Normalizer.normalize(str, Normalizer.Form.NFC);
        }
        return str;
    }
}
