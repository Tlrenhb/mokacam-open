package org.xutils.http;

public class RequestParams {
    private final String uri;
    private String saveFilePath;

    public RequestParams(String uri) {
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }

    public void setSaveFilePath(String path) {
        this.saveFilePath = path;
    }

    public String getSaveFilePath() {
        return saveFilePath;
    }
}
