package com.model;

import lombok.Data;

@Data
public class SplitFileData implements Comparable<SplitFileData> {
    private String filename;
    private int index;
    private String data;
    private boolean eof = false;

    public String toString() {
        if (data != null) {
            return "filename : " + filename + " " + "index : " + index + " " + "eof : " + eof + " " + "data : " + data.length();
        } else {
            return "filename : " + filename + " " + "index : " + index + " " + "eof : " + eof + " " + "data : " + 0;
        }
    }

    @Override
    public int compareTo(SplitFileData o) {
        return this.index <= o.index ? -1 : 1;
    }
}
