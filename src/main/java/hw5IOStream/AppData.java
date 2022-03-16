package hw5IOStream;

import java.util.Arrays;

public class AppData {
    private String[] header;
    private int[][] data;

    public String getHeader() {
        return Arrays.toString(header);
    }

    public void setHeader(String[] header) {
        this.header = header;
    }

    public String getData() {
        return Arrays.deepToString(data);
    }

    public void setData(int[][] data) {
        this.data = data;
    }

}
