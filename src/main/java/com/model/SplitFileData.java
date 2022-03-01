package com.model;

import lombok.Data;

@Data
public class SplitFileData implements Comparable<SplitFileData> {
    private String filename;
    private int index;
    private String data;
    private String file_type;
    private byte[] file_data;
    private boolean eof = false;

    @Override
    public int compareTo(SplitFileData o) {
        return this.index <= o.index ? -1 : 1;
    }

    @Override
    public String toString() {
        if (file_data != null) {
            return "filename : " + filename +
                    " index : " + index +
                    " file_data : " + file_data.length +
                    " file_type : " + file_type +
                    " eof : " + eof;

        } else {
            return "filename : " + filename +
                    " index : " + index +
                    " file_data : " + 0 +
                    " file_type : " + file_type +
                    " eof : " + eof;
        }
    }
}
