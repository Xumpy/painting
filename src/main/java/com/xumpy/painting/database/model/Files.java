package com.xumpy.painting.database.model;

public class Files {
    private Integer rowId;
    private String location_base;
    private String file_type;

    public Integer getRowId() {
        return rowId;
    }

    public void setRowId(Integer rowId) {
        this.rowId = rowId;
    }

    public String getLocation_base() {
        return location_base;
    }

    public void setLocation_base(String location_base) {
        this.location_base = location_base;
    }

    public String getFile_type() {
        return file_type;
    }

    public void setFile_type(String file_type) {
        this.file_type = file_type;
    }
}
