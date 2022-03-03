package com.model;

import lombok.Data;

@Data
public class SplitFileData implements Comparable<SplitFileData> {
    private int no;
    private String file_name;
    private int order_index;
    private String file_type;
    private String mime_type;
    private byte[] file_data;
    private boolean eof = false;
    private boolean complete;
    private boolean end;
    private String jsonStr;

    @Override
    public int compareTo(SplitFileData o) {
        return this.order_index <= o.order_index ? -1 : 1;
    }

    @Override
    public String toString() {
        if (file_data != null) {
            return "filename : " + file_name +
                    " index : " + order_index +
                    " file_data : " + file_data.length +
                    " file_type : " + file_type +
                    " eof : " + eof;

        } else {
            return "filename : " + file_name +
                    " index : " + order_index +
                    " file_data : " + 0 +
                    " file_type : " + file_type +
                    " eof : " + eof;
        }
    }
}
