package com.xumpy.painting.telegram.api.model;

public class GetFile {
    private Boolean ok;
    private FileResult result;

    public Boolean getOk() {
        return ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }

    public FileResult getResult() {
        return result;
    }

    public void setResult(FileResult result) {
        this.result = result;
    }
}
