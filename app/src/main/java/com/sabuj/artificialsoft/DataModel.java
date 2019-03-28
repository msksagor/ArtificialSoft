package com.sabuj.artificialsoft;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class DataModel {
    @SerializedName("search_found")
    @Expose
    private int searchFound;
    @SerializedName("error")
    @Expose
    private int error;
    @SerializedName("error_report")
    @Expose
    private String errorReport;
    @SerializedName("search_result")
    @Expose
    private List<SearchResult> searchResult = null;

    public int getSearchFound() {
        return searchFound;
    }

    public void setSearchFound(int searchFound) {
        this.searchFound = searchFound;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getErrorReport() {
        return errorReport;
    }

    public void setErrorReport(String errorReport) {
        this.errorReport = errorReport;
    }

    public List<SearchResult> getSearchResult() {
        return searchResult;
    }

    public void setSearchResult(List<SearchResult> searchResult) {
        this.searchResult = searchResult;
    }



}

