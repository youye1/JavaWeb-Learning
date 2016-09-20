package cn.youye.uploadTest;

/**
 * 文件上传状态保存类
 */
public class UploadStatus {

    private long bytesRead; //已上传的字节数

    private long contentLength; //所有文件总长度

    private int items;  //正在上传的文件

    private long startTime = System.currentTimeMillis();    //开始长传的时间

    public long getBytesRead() {
        return bytesRead;
    }

    public void setBytesRead(long bytesRead) {
        this.bytesRead = bytesRead;
    }

    public long getContentLength() {
        return contentLength;
    }

    public void setContentLength(long contentLength) {
        this.contentLength = contentLength;
    }

    public int getItems() {
        return items;
    }

    public void setItems(int items) {
        this.items = items;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

}
